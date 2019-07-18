# AndyAWDLibrary
[![Build Status](https://travis-ci.org/AndyAWD/AndyAWDLibrary.svg?branch=master)](https://travis-ci.org/AndyAWD/AndyAWDLibrary)  [![](https://jitpack.io/v/AndyAWD/AndyAWDLibrary.svg)](https://jitpack.io/#AndyAWD/AndyAWDLibrary)
***
### 我的專案工具
還是需要一個自己的工具箱，管理起來才方便啊。
***  
### 常用功能
1. AWDToastMgr：重新封裝的Toast，使用鏈式。
2. AWDDateFormat：日期格式轉換器，使用單例。
3. AWDEditText：監聽返回鍵的EditText。
4. AWDConstraintRadioGroup：可以用ConstraintLayout來排RadioButton

4. AWDSnackbarMgr：重新封裝的Snackbar，使用鏈式設定。
6. AWDSquareImageView：正方形ImageView，設定寬就好，高用wrap_content。
9. AWDPermissionsInfoTransformerTextMgr：權限翻譯成中文。
10. AWDToolMgr：還沒有大到可以拆成Class的工具，有圖檔轉Bas64｀取得這隻手機已經安裝的檔案名稱｀TextView Icon上色｀千分位樣式｀X 面骰模擬器｀簡單震動｀自定震動｀手機有沒有使用代理伺服器。
11. AWDConstants：符號常數SYMBOL開頭、權限回傳PERMISSIONS開頭、查IP網址IPIFY、藍牙UUID BLUETOOTH_UUID。
    
***
### 引用網址

    allprojects {
        repositories {
            maven { url 'https://jitpack.io' }
        }
    }

    dependencies {
        implementation 'com.github.AndyAWD:AndyAWDLibrary:1.4.0'
    }
***

##### AWDToastMgr使用方法

    //基本用法，有時間鎖
    private AWDToastMgr toastBase;
    
    toastBase = new AWDToastMgr.init(context).build();
    toastBase.show("Toast Show ! ");
    
    //或者，無時間鎖
    new AWDToastMgr.init(context).build().show("Toast Show ! ");
    
    //基本顯示 + 文字顏色
    toastBaseTextColor = new AWDToastMgr
            .init(ToastShowActivity.this)
            .setTextColor(R.color.dodgerblue)
            .build();
    
    //基本顯示 + 文字顏色 + 背景顏色
    toastBaseTextColorBackground = new AWDToastMgr
            .init(ToastShowActivity.this)
            .setBackgroundColor(R.color.metro_91d100)
            .setTextColor(R.color.dodgerblue)
            .build();
            
    //基本顯示 + 文字顏色 + 繪製背景
    toastBaseTextColorDrawableBackground = new AWDToastMgr
            .init(ToastShowActivity.this)
            .setBackgroundDrawable(R.drawable.rounded_hollow_rounded_ff83bf_ffb5d2_radius20_angle270)
            .setTextColor(R.color.dodgerblue)
            .build();
    
    //基本顯示 + 文字顏色 + 繪製背景 + 文字放大
    toastBaseTextColorDrawableBackgroundTextSize = new AWDToastMgr
            .init(ToastShowActivity.this)
            .setBackgroundDrawable(R.drawable.rounded_fc6392_f9baac_radius18_angle270)
            .setTextColor(R.color.dodgerblue)
            .setTextSize(50)
            .build();
    
    //基本顯示 + 背景偏移
    toastBaseGravity = new AWDToastMgr
            .init(ToastShowActivity.this)
            .setGravity(Gravity.CENTER, 100, 300)
            .build();
    
    //基本顯示 + 文字偏移
    toastBaseTextGravity = new AWDToastMgr
            .init(ToastShowActivity.this)
            .setTextGravity(Gravity.LEFT)
            .build();
    
    //基本顯示 + 背景顏色 + 文字背景
    toastBaseBackgroundTextBackgroundText = new AWDToastMgr
            .init(ToastShowActivity.this)
            .setBackgroundColor(R.color.metro_91d100)
            .setTextBackgroundColor(R.color.dodgerblue)
            .build();
    
    //顯示圖片
    toastPicture = new AWDToastMgr
            .init(ToastShowActivity.this)
            .setBackgroundPicture(R.drawable.alertbox_1x)
            .build();
    
    //顯示佈局
    toastLayout = new AWDToastMgr
            .init(ToastShowActivity.this)
            .setLayout(R.layout.view_api_error)
            .build();

##### AWDDateFormat使用方法            
    
    /*
     * Locale.TAIWAN 語系預設 
     * inputDate 輸入的日期
     * inputDatePattern 輸入的日期格式，可以從AWDConstants.DATETIME_FORMAT去找想要的格式
     * youWantDatePattern 你想要的日期格式，可以從AWDConstants.DATETIME_FORMAT去找想要的格式
     */
    AWDDateFormat.getInstance().getDateFormat("yyyy/MM/dd hh:mm:ss aa", "2018-05-29 16:30:54", AWDConstants.DATETIME_FORMAT_01, Locale.ENGLISH);
    
##### AWDConstraintRadioGroup使用方法

    AWDConstraintRadioGroup = findViewById(R.id.AWDConstraintRadioGroup);
    
    AWDConstraintRadioGroup.setOnCheckedChangeListener(new AWDConstraintRadioGroup.OnCheckedChangeListener() {
        @Override
            public void onCheckedChanged(AWDConstraintRadioGroup group, int checkedId) {
                    
            }
    });
        
##### AWDEditText使用方法

    AWDEditText = findViewById(R.id.AWDEditText);
    AWDEditText.setOnKeycodeBackListener(new AWDOnKeycodeBackListener() {
        @Override
        public void KeycodeBack() {
                
        }
    });