package com.magicalxu.countdowntextview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.magicalxu.library.CountDownTextView;

public class MainActivity extends Activity implements CountDownTextView.onReSend{

  CountDownTextView mBtnGreen;
  CountDownTextView mBtnYellow;
  CountDownTextView mBtnNormal;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    initView();
    initEvent();
  }

  private void initView() {
    mBtnGreen = (CountDownTextView) findViewById(R.id.id_button_green);
    mBtnYellow = (CountDownTextView) findViewById(R.id.id_button_yellow);
    mBtnNormal = (CountDownTextView) findViewById(R.id.id_button_normal);

    mBtnGreen.setText("CountDownTimer");
    mBtnYellow.setText("TextSwitcher");
    mBtnNormal.setText("获取验证码");
  }

  private void initEvent() {

    mBtnGreen.setResendListener(this);
    mBtnYellow.setResendListener(this);
    mBtnNormal.setResendListener(this);
  }

  public void sendCode(View view) {

    mBtnGreen.start();
    mBtnYellow.start();
    mBtnNormal.start();
  }

  @Override
  public void onResend(View view) {

    Toast.makeText(this,"验证码已发送！",Toast.LENGTH_SHORT).show();
    ((CountDownTextView) view).start();
  }
}
