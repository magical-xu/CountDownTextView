# CountDownTextView
利用 TextSwitcher 和 CountDownTimer 简单封装的验证码倒计时控件    
     
文字切换效果默认为淡入淡出，可以自己进行修改，自定义属性支持设置可用及不可用时背景
、提示文字、字大小、颜色、总时长（秒）、间隙时间(秒)

### 使用方法   

1.在XML文件中引用

```
<com.magicalxu.library.CountDownTextView
      android:id="@+id/id_button_normal"
      android:layout_width="150dp"
      android:layout_height="50dp"
      android:layout_marginTop="40dp"
      magical:disable_background="@drawable/shape_login_btn_bg_disable"
      magical:enable_background="@drawable/shape_login_btn_bg"
      magical:gap_time="1"
      magical:text_color="@android:color/white"
      magical:text_size="16sp"
      magical:tip_text="点击重发"
      magical:total_time="3" />
```
    
2.开始计时调用 start() 方法    

3.设置重发事件监听     
```
mBtnNormal.setResendListener(new CountDownTextView.onReSend() {
      @Override
      public void onResend(View view) {
        //计时结束后 点击事件回调
      }
});
```    
### 自定义切换动画    

将源码中 configSwitcher() 方法中的动画替换    
```
setInAnimation(getContext(), android.R.anim.fade_in);
setOutAnimation(getContext(), android.R.anim.fade_out);
```    
     
        

