package com.magicalxu.countdowntextview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.magicalxu.library.CountDownTextView;

public class MainActivity extends Activity implements CountDownTextView.onReSend {

    CountDownTextView mBtnGreen;
    CountDownTextView mBtnYellow;
    CountDownTextView mBtnNormal;
    CountDownTextView mBtnOrange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initEvent();
    }

    private void initView() {
        mBtnGreen = findViewById(R.id.id_button_green);
        mBtnYellow = findViewById(R.id.id_button_yellow);
        mBtnNormal = findViewById(R.id.id_button_normal);
        mBtnOrange = findViewById(R.id.id_button_orange);
    }

    private void initEvent() {

        mBtnGreen.setResendListener(this);
        mBtnYellow.setResendListener(this);
        mBtnNormal.setResendListener(this);
        mBtnOrange.setResendListener(this);
    }

    public void sendCode(View view) {

        mBtnGreen.start();
        mBtnYellow.start();
        mBtnNormal.start();
        mBtnOrange.start();
        mBtnOrange.postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, "接口请求失败！", Toast.LENGTH_SHORT).show();
                mBtnOrange.reset();
            }
        }, 1500);
    }

    @Override
    public void onResend(View view) {

        Toast.makeText(this, "验证码已发送！", Toast.LENGTH_SHORT).show();
        ((CountDownTextView) view).start();
    }
}
