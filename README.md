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
4. AWDConstraintRadioGroup：可以用ConstraintLayout來排RadioButton。
5. AWDPermissionsFailAlertDialog：簡單的權限未提供AlertDialog
6. AWDPermissionsInfoTransformerTextMgr：權限翻譯成中文，使用單例。
7. AWDLog：可以做權限切換和解析Json格式的Log
8. AWDSquareImageView：正方形ImageView。
9. AWDConstants：符號常數SYMBOL開頭、權限回傳PERMISSIONS開頭、查IP網址IPIFY、藍牙UUID BLUETOOTH_UUID。
10. color.xml：十六進位值色碼、Modern / Modern色碼表。
11. AWDThousandBitStyleMgr：千分位樣式，使用單例。
12. AWDToolMgr：還沒有大到可以拆成Class的工具和實驗性工具都放在這。
    
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
### 版本紀錄
2.0.0

把功能都翻過一次，版號大耀進！

***
##### 1.AWDToastMgr使用方法

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

##### 2.AWDDateFormat使用方法            
    
    /**
     * Locale.TAIWAN 語系預設 
     * inputDate 輸入的日期
     * inputDatePattern 輸入的日期格式，可以從AWDConstants.DATETIME_FORMAT去找想要的格式
     * youWantDatePattern 你想要的日期格式，可以從AWDConstants.DATETIME_FORMAT去找想要的格式
     */
    AWDDateFormat.getInstance().getDateFormat("yyyy/MM/dd hh:mm:ss aa", "2018-05-29 16:30:54", AWDConstants.DATETIME_FORMAT_01, Locale.ENGLISH);
    
##### 3.AWDEditText使用方法

    AWDEditText = findViewById(R.id.AWDEditText);
    AWDEditText.setOnKeycodeBackListener(new AWDOnKeycodeBackListener() {
        @Override
        public void KeycodeBack() {
                
        }
    });
        
##### 4.AWDConstraintRadioGroup使用方法

    AWDConstraintRadioGroup = findViewById(R.id.AWDConstraintRadioGroup);
    
    AWDConstraintRadioGroup.setOnCheckedChangeListener(new AWDConstraintRadioGroup.OnCheckedChangeListener() {
        @Override
            public void onCheckedChanged(AWDConstraintRadioGroup group, int checkedId) {
                    
            }
    });
         
##### 5.AWDPermissionsFailAlertDialog使用方法

    /**
     * 需要搭配'pub.devrel:easypermissions:2.0.0'使用     
     */
    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(PermissionsTransformerActivity.this, perms)) {
            new AWDPermissionsFailAlertDialog(PermissionsTransformerActivity.this, perms);
        }
    }
    
##### 6.AWDPermissionsInfoTransformerTextMgr使用方法

    //傳入權限字串就好
    AWDPermissionsInfoTransformerTextMgr.getInstance().getTransformerInfo("android.permission.CAMERA")
    
##### 7.AWDLog使用方法
    
    //可以先在Application把Log設定等級
    AWDLog.setLogLevel(AWDConstants.LOG_VERBOSE);
    
    //然後就一般使用就好
    AWDLog.v("Log Message");
    AWDLog.d("Log Message");
    AWDLog.w("Log Message");
    AWDLog.i("Log Message");
    AWDLog.e("Log Message");
    AWDLog.api("maho","{\"returnValue\":0,\"returnMsg\":\"執行成功\"}");
    
##### 8.AWDSquareImageView使用方法

    /**
     * 基本上就是要自己長的設定成wrap_content
     * 然後lockLayoutSide設定要固定的寬或高
     * 如果lockLayoutSide沒設定，那就是預設指定寬度，高度自己長
     */
    <tw.com.andyawd.andyawdlibrary.AWDSquareImageView
            android:id="@+id/aivAsi_HeightSquareImage"
            android:layout_width="160dp"
            android:layout_height="wrap_content"
            app:lockLayoutSide="width" />
    
    <tw.com.andyawd.andyawdlibrary.AWDSquareImageView
            android:id="@+id/aivAsi_HeightSquareImage"
            android:layout_width="wrap_content"
            android:layout_height="160dp"
            app:lockLayoutSide="height" />    

##### 11.AWDThousandBitStyleMgr使用方法
 
    /**
     * 先設定想要的千分位樣式
     * 也可以去AWDConstants.THOUSAND_FORMAT找
     */ 
    AWDThousandBitStyleMgr.getInstance().setThousandBitStyle("###,###.##");
    
    //然後再傳入數字，就會印出想要的樣式了，會四捨五入
    AWDThousandBitStyleMgr.getInstance().getThousandBitStyle("987654321.12345") 

