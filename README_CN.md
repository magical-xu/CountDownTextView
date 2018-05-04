# CountDownTextView

一个使用 TextSwitcher 和 CountDownTimer 简单封装的验证码倒计时控件

### 优点

良好的封装和高度内聚

### GIFS   
 <img src="https://github.com/magical-xu/CountDownTextView/raw/master/screenshot/CountDownTextView.gif" width="380" height="720" alt="sample1"/> 
 <img src="https://github.com/magical-xu/CountDownTextView/raw/master/screenshot/sample.gif" width="380" height="720" alt="sample2"/>

### 属性
  
|name|format|description|
|:---:|:---:|:---:|
| text_size | dimension |文字大小
| text_color | color |文字颜色，支持selector变色
| tip_text | string |计时结束后显示的提示文字
| init_text | string |初始的提示文字
| gap_string_format | string |计时中显示的文字，默认 xx秒 可以自定义格式 (use string format)
| enable_background | reference |可点击时背景色，默认透明
| disable_background | reference |不可点击时背景色，默认透明
| total_time | integer |倒计时总时长
| anim_in | reference |倒计时切换进入动画 默认淡入 android.R.anim.fade_in
| anim_out | reference |倒计时切换退出动画 默认淡出 android.R.anim.fade_out
| android:animateFirstView | boolean |界面刚显示时是否执行一次动画，默认true

### 使用

1.添加依赖
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
	        implementation 'com.github.magical-xu:CountDownTextView:v1.0.4.1'
	     }
```

2.xml中使用

```
<com.magicalxu.library.CountDownTextView
        android:id="@+id/id_button_orange"
        android:layout_width="120dp"
        android:layout_height="match_parent"
        android:animateFirstView="false"
        magical:anim_in="@android:anim/slide_in_left"
        magical:anim_out="@android:anim/slide_out_right"
        magical:disable_background="@drawable/shape_disable"
        magical:enable_background="@drawable/shape_enable"
        magical:text_color="@color/selector_verify_code_text_color"
        magical:text_size="14sp"
        magical:total_time="10" />
```
    
3.对外方法

[1] start() : 开始计时  
[2] cancel(): 取消计时，界面销毁时调用以防内存泄漏  
[3] reSet() : 重置为初始状态 ( not the parent method "reset()" )  
[4] textHint(): 设置文字为暗色：true 设置文字为亮色：false 需配合selector  
[5] isProcessing(): 是否正在计时  

4.设置点击监听
```
mBtnNormal.setSendListener(new CountDownTextView.ISendListener() {
      @Override
      public void onSend(View view) {
        // the event when click widget  
      }
});
```        
5.注意事项

为了避免内存泄漏，需要在类似 onDestory()的方法中 调用 #cancel()方法 取消内部定时器

### 版本变动记录

> v1.0.4.1(2018.5.2)  
>>refactory the name      
>>add some attribute     

### License  

```  
 Copyright 2017 magical_xu <magicalxu666@gmail.com>
 
 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 
     http://www.apache.org/licenses/LICENSE-2.0
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.  
 ```  
      
