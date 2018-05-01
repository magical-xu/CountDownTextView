# CountDownTextView
A simple verify code countdown widget that using TextSwitcher and CountDownTimer.  

### Advantages  
1.Simple to use  
2.Good encapsulation and high cohesion  

### GIFS   
 <img src="https://github.com/magical-xu/CountDownTextView/raw/master/screenshot/CountDownTextView.gif" width="380" height="720" alt="sample1"/> 
 <img src="https://github.com/magical-xu/CountDownTextView/raw/master/screenshot/sample.gif" width="380" height="720" alt="sample2"/>

### Attributes  
  
|name|format|description|
|:---:|:---:|:---:|
| text_size | dimension |set the text size
| text_color | color |the text color can use a selector
| tip_text | string |the tips string showing when countdown finish
| init_text | string |the string showing by the first time
| gap_string_format | string |the tips when countdown is processing (use string format)
| enable_background | reference |the background when widget is enable and default is transparent
| disable_background | reference |the background when widget is disable and default is transparent 
| total_time | integer |the total time that will be countdown
| anim_in | reference |the anim when view switch in and default is android.R.anim.fade_in
| anim_out | reference |the anim when view switch out and default is android.R.anim.fade_out
| android:animateFirstView | boolean |if anim the view when first show      

### How to use    

1.add JitPack in project's build.gradle   
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
	        compile 'com.github.magical-xu:CountDownTextView:the latest version'
	     }
```

2.sample use in xml

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
    
3.public method 

[1] start() : start to countdown  
[2] cancel(): cancel the internal timer    
[3] reSet() : reset to the init state ( not the parent method "reset()" )  
[4] textHint(): set true to show the dark color or false the light  
[5] isProcessing(): if the widget is countdown at the time       

4.set the click listener  
```
mBtnNormal.setSendListener(new CountDownTextView.ISendListener() {
      @Override
      public void onSend(View view) {
        // the event when click widget  
      }
});
```        
5.note  

to avoid memory leak , u need to invoke #cancel() method in method like "onDestory"   

### change log  

-> v1.0.4(2018.5.2)  
  refactory the name  
  add some attribute     

### License  

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
      
