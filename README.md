# CountDownTextView
利用 TextSwitcher 和 CountDownTimer 简单封装的验证码倒计时控件  

### 效果图  
  ![](https://github.com/magical-xu/CountDownTextView/raw/master/screenshot/CountDownTextView.gif)  

自定义属性支持设置可用及不可用时背景、提示文字、初始文字、字大小、颜色、总时长（秒）、进入退出动画

### 使用方法   

1.添加jitpack 并添加依赖  
```
root build.gradle:  
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}  
  
your project:  
dependencies {
	        compile 'com.github.magical-xu:CountDownTextView:v1.0.2'
	}
```

2.在XML文件中引用

```
<com.magicalxu.library.CountDownTextView
      android:id="@+id/id_button_yellow"
      android:layout_width="150dp"
      android:layout_height="50dp"
      android:layout_marginTop="20dp"
      magical:anim_in="@android:anim/slide_in_left"
      magical:anim_out="@android:anim/slide_out_right"
      magical:disable_background="@drawable/shape_bg_yellow_disable"
      magical:enable_background="@drawable/shape_bg_yellow_normal"
      magical:init_text="TextSwitcher"
      magical:text_color="@android:color/holo_red_light"
      magical:text_size="12sp"
      magical:tip_text="点击发送"
      magical:total_time="40" />
```
    
3.可用方法  

  开始计时调用 start() 方法
  失败或手动取消调用 cancel()方法  
  重置为初始状态调用 reset()方法

4.设置重发事件监听     
```
mBtnNormal.setResendListener(new CountDownTextView.onReSend() {
      @Override
      public void onResend(View view) {
        //计时结束后 点击事件回调
      }
});
```       
      
