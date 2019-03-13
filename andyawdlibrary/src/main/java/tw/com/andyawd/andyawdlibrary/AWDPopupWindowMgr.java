package tw.com.andyawd.andyawdlibrary;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

/**
 * Created by andydai on 2018/10/2.
 * 第二版PopupWindowMgr，以後維護這版，第一版不維護囉
 * <p>
 * 如何設定︰如果只要用基本的功能只需要設定Context(.setContext)、要顯示的Xml(.setShowView)，最後再執行(.build)就好
 * 範例︰new AWDPopupWindowMgr.initi()2.setContext(TCLivePlayerActivity.this).setShowView(R.layout.dialog_hlgetgiftlistv2_mk3).build();
 * <p>
 * 從底往上顯示︰showAtLocation(主頁面(父類別)Xml, Gravity.BOTTOM, 0, 0)，或懶人寫法showAtLocation(主頁面(父類別)Xml)
 * 指定View往上顯示︰showAsDropDown(指定View, Gravity.BOTTOM, 0, getPopupWindowHeight())
 * 指定View往下上顯示︰showAsDropDown(指定View, Gravity.BOTTOM, 0, -getPopupWindowHeight())
 */

public class AWDPopupWindowMgr {
    private static final String TAG = AWDToastMgr.class.getSimpleName();

    /**
     * Logcat中開啟訊息
     */
    public static final boolean LOG_ON = true;

    /**
     * Logcat中關閉訊息，預設
     */
    public static final boolean LOG_OFF = false;

    /**
     * 資源檔預設值，用來判斷有沒有另外設定
     */
    private static final int NO_SETTING = 529;

    /**
     *
     */
    public static final int DISMISS = 0;

    /**
     *
     */
    public static final int NO_DISMISS = 1;

    /**
     * Logcat會用到的常數
     */
    private static final String CONTEXT = "context : ";
    private static final String LISTENER = "listener : ";
    private static final String POPUPWINDOW = "popupWindow : ";
    private static final String SDK19 = "SDK低於19";

    private PopupWindow popupWindow;
    private View view;
    private initi builder;
    private init init;
    private boolean isLogOpen = LOG_OFF;

    private setAWDPopupWindowListener listener;    //命名

    public interface setAWDPopupWindowListener {    //建立interface
        void OnDismiss();

        void OnBackgroundTouch(View view, MotionEvent motionEvent);
    }

    public void setAWDPopupWindowListener(setAWDPopupWindowListener listener) {    //初始化interface
        this.listener = listener;
    }

    public AWDPopupWindowMgr(init init) {
        this.init = init;

        if (null == init.context && AWDPopupWindowMgr.NO_SETTING == init.layout) {
            if (isLogOpen) {
                Log.d(TAG, CONTEXT + String.valueOf(init.context));
            }
            return;
        }

        view = LayoutInflater.from(init.context).inflate(init.layout, null);
        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);

        popupWindow = new PopupWindow(view, init.width, init.height);
        popupWindow.setAnimationStyle(init.animStyle);     //設定動畫
        popupWindow.setSoftInputMode(init.mode);           //適配虛擬鍵
        popupWindow.setBackgroundDrawable(init.drawable);  //背景
        popupWindow.setOnDismissListener(popupWindow_Dismiss);
        popupWindow.setTouchInterceptor(popupWindow_Touch);

        if (AWDPopupWindowMgr.DISMISS == init.clickBackgroundToDismiss){
            popupWindow.setFocusable(true);        //獲得焦點
            popupWindow.setTouchable(true);        //響應點擊事件
            popupWindow.setOutsideTouchable(true); //響應外部點擊事件
            return;
        }

        if (AWDPopupWindowMgr.NO_DISMISS == init.clickBackgroundToDismiss){
            popupWindow.setFocusable(true);            //獲得焦點
            popupWindow.setTouchable(true);            //響應點擊事件
            popupWindow.setOutsideTouchable(false);    //響應外部點擊事件
            return;
        }
    }

    private final PopupWindow.OnDismissListener popupWindow_Dismiss = new PopupWindow.OnDismissListener() {
        @Override
        public void onDismiss() {
            if (null == listener) {
                if (isLogOpen) {
                    Log.d(TAG, LISTENER + String.valueOf(listener));
                }
                return;
            }

            listener.OnDismiss();
        }
    };

    private final View.OnTouchListener popupWindow_Touch = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (null == listener) {
                if (isLogOpen) {
                    Log.d(TAG, LISTENER + String.valueOf(listener));
                }
                return false;
            }

            listener.OnBackgroundTouch(view, event);

            return false;
        }
    };

    /**
     * 懶人的showAtLocation寫法
     */
    public AWDPopupWindowMgr showAtLocation(int fatherLayout) {
        if (null == popupWindow){
            if (isLogOpen) {
                Log.d(TAG, POPUPWINDOW + String.valueOf(popupWindow));
            }
            return null;
        }

        if (null == init.context){
            if (isLogOpen) {
                Log.d(TAG, CONTEXT + String.valueOf(init.context));
            }
            return null;
        }

        View vShowAtLocation = LayoutInflater.from(init.context).inflate(fatherLayout, null);
        popupWindow.showAtLocation(vShowAtLocation, Gravity.BOTTOM, 0, 0);

        return this;
    }

    /**
     * @param fatherLayout 主頁面(父類別)Xml
     * @param gravity      是否置中，不知道要打什麼的話輸入"Gravity.BOTTOM"
     * @param x            不知道要打什麼的話輸入"0"
     * @param y            不知道要打什麼的話輸入"0"
     * @return
     */
    public AWDPopupWindowMgr showAtLocation(int fatherLayout, int gravity, int x, int y) {
        if (null == popupWindow){
            if (isLogOpen) {
                Log.d(TAG, POPUPWINDOW + String.valueOf(popupWindow));
            }
            return null;
        }

        if (null == init.context){
            if (isLogOpen) {
                Log.d(TAG, CONTEXT + String.valueOf(init.context));
            }
            return null;
        }

        View vShowAtLocation = LayoutInflater.from(init.context).inflate(fatherLayout, null);
        popupWindow.showAtLocation(vShowAtLocation, gravity, x, y);

        return this;
    }

    /**
     * @param targeView 彈跳試窗的目標View
     * @param gravity   是否置中，不知道要打什麼的話輸入"Gravity.BOTTOM"
     * @param x         不知道要打什麼的話輸入"0"
     * @param y         不知道要打什麼的話輸入"0"，輸入正數是往下顯示，輸入負數是往上顯示
     * @return
     */
    public AWDPopupWindowMgr showAsDropDown(View targeView, int gravity, int x, int y) {
        if (null == popupWindow){
            if (isLogOpen) {
                Log.d(TAG, POPUPWINDOW + String.valueOf(popupWindow));
            }
            return null;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            popupWindow.showAsDropDown(targeView, x, y, gravity);
        } else {
            if (isLogOpen) {
                Log.d(TAG, SDK19);
            }
        }

        return this;
    }

    /**
     * @param view 取得元件
     * @return
     */
    public View findViewById(int view) {
        if (null == popupWindow){
            if (isLogOpen) {
                Log.d(TAG, POPUPWINDOW + String.valueOf(popupWindow));
            }
            return null;
        }

        return this.view.findViewById(view);
    }

    /**
     * 取得PopupWindow整體的寬
     *
     * @return
     */
    public int getPopupWindowWidth() {
        return view.getMeasuredWidth();
    }

    /**
     * 取得PopupWindow整體的高
     *
     * @return
     */
    public int getPopupWindowHeight() {
        return view.getMeasuredHeight();
    }

    /**
     * 關閉彈跳視窗
     */
    public void dismiss() {
        if (null == popupWindow){
            if (isLogOpen) {
                Log.d(TAG, POPUPWINDOW + String.valueOf(popupWindow));
            }
            return;
        }

        popupWindow.dismiss();
    }

    /**
     * 再觀察，用不到就刪
     *
     * @param view
     * @param listener
     */
    public void setFocusListener(int view, View.OnFocusChangeListener listener) {
        View vFocusListener = findViewById(view);
        vFocusListener.setOnFocusChangeListener(listener);
    }

    public static class init {
        private Context context;
        private int layout = AWDPopupWindowMgr.NO_SETTING;                         //設定Xml
        private int width = WindowManager.LayoutParams.MATCH_PARENT;               //設定寬，預設手機寬
        private int height = WindowManager.LayoutParams.WRAP_CONTENT;              //設定高，預設Xml高
        private int animStyle = R.style.popupWindowAnim;                        //設定動畫
        private int mode = WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE;    //適配虛擬鍵
        private Drawable drawable = new ColorDrawable(0x00000000);                 //設定背景，預設透明
        private int clickBackgroundToDismiss = AWDPopupWindowMgr.DISMISS;         //點背景時要不要關閉彈跳視窗

        public init(Context context) {
            this.context = context;
        }

        public init setLayout(int layout) {
            this.layout = layout;
            return this;
        }

        public init setWidth(int width) {
            this.width = width;
            return this;
        }

        public init setHeight(int height) {
            this.height = height;
            return this;
        }

        public init setAnimStyle(int animStyle) {
            this.animStyle = animStyle;
            return this;
        }

        public init setBackground(Drawable drawable) {
            this.drawable = drawable;
            return this;
        }

        public init setSoftInputMode(int mode) {
            this.mode = mode;
            return this;
        }

        public init setClickBackground(int selectDismiss) {
            this.clickBackgroundToDismiss = selectDismiss;
            return this;
        }

        public AWDPopupWindowMgr build() {
            return new AWDPopupWindowMgr(this);
        }
    }




    @Deprecated
    public AWDPopupWindowMgr(initi builder) {
        this.builder = builder;

        if (null != builder.mContext && AWDPopupWindowMgr.NO_SETTING != builder.mLayout) {

            view = LayoutInflater.from(builder.mContext).inflate(builder.mLayout, null);
            view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);

            popupWindow = new PopupWindow(view, builder.mWidth, builder.mHeight);
            popupWindow.setAnimationStyle(builder.mAnimStyle);     //設定動畫
            popupWindow.setSoftInputMode(builder.mMode);           //適配虛擬鍵
            popupWindow.setBackgroundDrawable(builder.mDrawable);  //背景

            switch (builder.mClickBackgroundToDismiss) {
                case AWDPopupWindowMgr.DISMISS:
                    popupWindow.setFocusable(true);        //獲得焦點
                    popupWindow.setTouchable(true);        //響應點擊事件
                    popupWindow.setOutsideTouchable(true); //響應外部點擊事件
                    break;
                case AWDPopupWindowMgr.NO_DISMISS:
                    popupWindow.setFocusable(true);            //獲得焦點
                    popupWindow.setTouchable(true);            //響應點擊事件
                    popupWindow.setOutsideTouchable(false);    //響應外部點擊事件
                    break;
            }
        } else {
            if (isLogOpen) {
                Log.d("AWDPopupWindowMgr", "mContext : " + String.valueOf(builder.mContext) + "\n" + "mLayout : " + String.valueOf(builder.mLayout));
            }
        }

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                if (null != listener) {
                    listener.OnDismiss();
                } else {
                    if (isLogOpen) {
                        Log.d("AWDPopupWindowMgr", "listener : " + String.valueOf(listener));
                    }
                }
            }
        });

        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (null != listener) {
                    listener.OnBackgroundTouch(view, motionEvent);
                } else {
                    if (isLogOpen) {
                        Log.d("AWDPopupWindowMgr", "listener : " + String.valueOf(listener));
                    }
                }
                return false;
            }
        });
    }

    @Deprecated
    public static class initi {
        private Context mContext;
        private int mLayout = AWDPopupWindowMgr.NO_SETTING;                         //設定Xml
        private int mWidth = WindowManager.LayoutParams.MATCH_PARENT;               //設定寬，預設手機寬
        private int mHeight = WindowManager.LayoutParams.WRAP_CONTENT;              //設定高，預設Xml高
        private int mAnimStyle = R.style.popupWindowAnim;                        //設定動畫
        private int mMode = WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE;    //適配虛擬鍵
        private Drawable mDrawable = new ColorDrawable(0x00000000);                 //設定背景，預設透明
        private int mClickBackgroundToDismiss = AWDPopupWindowMgr.DISMISS;         //點背景時要不要關閉彈跳視窗

        public initi setContext(Context context) {
            this.mContext = context;
            return this;
        }

        public initi setLayout(int layout) {
            this.mLayout = layout;
            return this;
        }

        public initi setWidth(int width) {
            this.mWidth = width;
            return this;
        }

        public initi setHeight(int height) {
            this.mHeight = height;
            return this;
        }

        public initi setAnimStyle(int animStyle) {
            this.mAnimStyle = animStyle;
            return this;
        }

        public initi setBackground(Drawable drawable) {
            this.mDrawable = drawable;
            return this;
        }

        public initi setSoftInputMode(int mode) {
            this.mMode = mode;
            return this;
        }

        public initi setClickBackground(int selectDismiss) {
            this.mClickBackgroundToDismiss = selectDismiss;
            return this;
        }

        public AWDPopupWindowMgr build() {
            return new AWDPopupWindowMgr(this);
        }
    }
}
