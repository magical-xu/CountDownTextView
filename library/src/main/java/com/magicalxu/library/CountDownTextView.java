/*
 * Copyright 2017 magical_xu <magicalxu666@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.magicalxu.library;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.TextSwitcher;
import android.widget.TextView;

/**
 * Created by magical on 17/3/21.
 * Description : 倒计时控件
 */

public class CountDownTextView extends TextSwitcher
        implements TextSwitcher.ViewFactory, View.OnClickListener {

    private int TOTAL_MILLS;    //默认60s
    private String tipString;   //提示文字
    private String initString;  //初始文字

    private float TEXT_SIZE;    //默认16sp
    private int TEXT_COLOR;     //默认白色

    private int BgEnableResId;  //可用时背景
    private int BgDisableResId; //不可用时背景

    private int animIn;         //文字进入动画
    private int animOut;        //文字退出动画

    private CountDownTimer timer;
    private onReSend mListener;

    private boolean countDownFinish;

    public CountDownTextView(Context context) {
        this(context, null);
    }

    public CountDownTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        checkAttrs(attrs);
        configSwitcher();
        configTimer();
        setOnClickListener(this);

        countDownFinish = true; //允许第一次点击
        setBackgroundResource(BgEnableResId);
        setText(initString);
    }

    /**
     * 检查自定义属性
     */
    private void checkAttrs(AttributeSet attrs) {

        float defaultTextSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16.0f,
                Resources.getSystem().getDisplayMetrics());

        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.CountDownTextView);

        //文字
        TEXT_SIZE = ta.getDimension(R.styleable.CountDownTextView_text_size, defaultTextSize);
        TEXT_COLOR = ta.getColor(R.styleable.CountDownTextView_text_color, Color.WHITE);

        //背景
        BgEnableResId = ta.getResourceId(R.styleable.CountDownTextView_enable_background,
                R.drawable.shape_login_btn_bg);
        BgDisableResId = ta.getResourceId(R.styleable.CountDownTextView_disable_background,
                R.drawable.shape_login_btn_bg_disable);

        //动画
        animIn = ta.getResourceId(R.styleable.CountDownTextView_anim_in, android.R.anim.fade_in);
        animOut = ta.getResourceId(R.styleable.CountDownTextView_anim_out, android.R.anim.fade_out);

        //总时长 （秒）
        TOTAL_MILLS = ta.getInteger(R.styleable.CountDownTextView_total_time, 60);

        //提示及初始文字
        tipString = ta.getString(R.styleable.CountDownTextView_tip_text);
        if (TextUtils.isEmpty(tipString)) {
            tipString = getContext().getString(R.string.count_down_text_click_to_resend);
        }
        initString = ta.getString(R.styleable.CountDownTextView_init_text);
        if (TextUtils.isEmpty(initString)) {
            initString = getContext().getString(R.string.count_down_text_init);
        }
        ta.recycle();
    }

    /**
     * 开始计时
     */
    public void start() {
        timer.start();
        setBackgroundResource(BgDisableResId);
    }

    /**
     * 取消定時器
     */
    public void cancel() {
        timer.cancel();
        timer.onFinish();
    }

    /**
     * 重置为初始状态
     */
    public void reset() {
        timer.cancel();
        countDownFinish = true;
        String show = initString;
        setText(show);
        setBackgroundResource(BgEnableResId);
    }

    /**
     * 配置 CountDownTimer
     * 默认60s 间隙 1s
     */
    private void configTimer() {
        if (null == timer) {
            timer = new CountDownTimer(TOTAL_MILLS * 1000, 1000) {
                @Override
                public void onTick(long l) {
                    onGapCallback(l);
                }

                @Override
                public void onFinish() {
                    onCountDownFinish();
                }
            };
        }
    }

    /**
     * 计时完毕回调
     */
    private void onCountDownFinish() {

        countDownFinish = true;
        String show = tipString;
        setText(show);
        setBackgroundResource(BgEnableResId);
    }

    /**
     * 间隙的回调
     *
     * @param rest 剩余时间
     */
    private void onGapCallback(long rest) {

        countDownFinish = false;
        String show = rest / 1000 + "s";
        setText(show);
    }

    /**
     * 配置 TextSwitcher 切换动画
     */
    private void configSwitcher() {
        setFactory(this);
        setInAnimation(getContext(), animIn);
        setOutAnimation(getContext(), animOut);
    }

    private int px2sp(float pxValue) {
        final float fontScale = Resources.getSystem().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    @Override
    public View makeView() {
        TextView textView = new TextView(getContext());
        textView.setMaxLines(1);
        textView.setEllipsize(TextUtils.TruncateAt.END);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(TEXT_COLOR);
        textView.setTextSize(px2sp(TEXT_SIZE));
        int padding = TypedValue.complexToDimensionPixelSize(10,
                Resources.getSystem().getDisplayMetrics());
        textView.setPadding(padding, padding, padding, padding);

        LayoutParams params =
                new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        params.gravity = Gravity.CENTER;
        textView.setLayoutParams(params);
        return textView;
    }

    @Override
    public void onClick(View view) {
        if (countDownFinish && mListener != null) {
            mListener.onResend(view);
        }
    }

    public interface onReSend {
        void onResend(View view);
    }

    /**
     * 重发事件
     */
    public void setResendListener(onReSend listener) {
        this.mListener = listener;
    }
}
