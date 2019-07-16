# AndyAWDLibrary
[![Build Status](https://travis-ci.org/AndyAWD/AndyAWDLibrary.svg?branch=master)](https://travis-ci.org/AndyAWD/AndyAWDLibrary)  [![](https://jitpack.io/v/AndyAWD/AndyAWDLibrary.svg)](https://jitpack.io/#AndyAWD/AndyAWDLibrary)
***
### 我的專案工具
還是需要一個自己的工具箱，管理起來才方便啊。
***  
### 常用功能
1. AWDNoScrollViewPager：無法滑動的ViewPager。
2. AWDEditText：監聽返回鍵的EditText。
3. AWDScrollView：監聽滑動狀態的ScrollView。
4. AWDSnackbarMgr：重新封裝的Snackbar，使用鏈式設定。
5. AWDToastMgr：重新封裝的Toast，使用鏈式設定。
6. AWDSquareImageView：正方形ImageView，設定寬就好，高用wrap_content。
7. AWDViewPagerAdapter：繼承AWDViewPager的ViewPagerAdapter。
8. AWDViewPagerActivityAdapter：繼承Activity的ViewPagerAdapter。
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
###AWDEditText使用方法

    AWDEditText = = findViewById(R.id.AWDEditText);
    AWDEditText.setOnKeycodeBackListener(new AWDOnKeycodeBackListener() {
        @Override
        public void KeycodeBack() {
                
        }
    });