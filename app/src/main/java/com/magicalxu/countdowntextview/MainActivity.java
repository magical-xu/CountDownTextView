package com.magicalxu.countdowntextview;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.magicalxu.library.CountDownTextView;

public class MainActivity extends Activity implements CountDownTextView.ISendListener {

    CountDownTextView mBtnWx;
    CountDownTextView mBtnTT;
    CountDownTextView mBtnNormal;
    CountDownTextView mBtnOrange;

    EditText mObserveInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initEvent();
    }

    private void initView() {
        mBtnWx = findViewById(R.id.id_button_wx);
        mBtnTT = findViewById(R.id.id_button_tt);
        mBtnNormal = findViewById(R.id.id_button_normal);
        mBtnOrange = findViewById(R.id.id_button_orange);

        mObserveInput = findViewById(R.id.id_observe_input);
    }

    private void initEvent() {

        mBtnWx.setSendListener(this);
        mBtnTT.setSendListener(this);
        mBtnNormal.setSendListener(this);
        mBtnOrange.setSendListener(this);

        mObserveInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                checkInitState();
            }
        });
        checkInitState();
    }

    private void checkInitState() {

        if (mBtnOrange.isProcessing()) {
            return;
        }

        String check = mObserveInput.getText().toString().trim();
        mBtnOrange.textHint(!(check.length() > 0));
    }

    @Override
    public void onSend(View view) {

        switch (view.getId()) {
            case R.id.id_button_normal:
                break;
            case R.id.id_button_wx:
                break;
            case R.id.id_button_tt:
                break;
            case R.id.id_button_orange:
                break;
        }

        Toast.makeText(this, "验证码已发送！", Toast.LENGTH_SHORT).show();
        ((CountDownTextView) view).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //防止内存泄漏
        mBtnWx.cancel();
        mBtnTT.cancel();
        mBtnNormal.cancel();
        mBtnOrange.cancel();
    }
}
