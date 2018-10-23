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
 * Created by andydai on 2018/3/21.
 * 覺得PopupWindow每次都要打一堆碼很麻煩嗎，來用這個吧
 * 如何設定︰如果只要用基本的功能只需要設定Context(.setContext)、要顯示的Xml(.setShowView)，最後再執行(.build)就好
 * 範例︰new HLPopupWindowMgr.initi().setContext(TCLivePlayerActivity.this).setShowView(R.layout.dialog_hlgetgiftlistv2_mk3).build();
 * <p>
 * 如何顯示︰showAtLocation(主頁面(父類別)Xml, Gravity.BOTTOM, 0, 0)
 */

public class AWDPopupWindowMgr {

    private static final int NoSetting = 529;
    public static final boolean Log_On = true;
    public static final boolean Log_Off = false;
    public static final int Dismiss = 0;
    public static final int NoDismiss = 1;

    private PopupWindow mPopupWindow;
    private View vPopupWindow;
    private initi builder;
    private setAWDPopupWindowMgrV2Listener listener;    //命名

    private boolean blnLogSwitch = Log_Off;

    public interface setAWDPopupWindowMgrV2Listener {    //建立interface
        void OnDismiss();

        void OnBackgroundTouch(View view, MotionEvent motionEvent);
    }

    public void setHLPopupWindowMgrV2Listener(setAWDPopupWindowMgrV2Listener listener) {    //初始化interface
        this.listener = listener;
    }

    public AWDPopupWindowMgr(initi builder) {
        this.builder = builder;
        if (null != builder.mContext && AWDPopupWindowMgr.NoSetting != builder.mLayout) {

            vPopupWindow = LayoutInflater.from(builder.mContext).inflate(builder.mLayout, null);
            vPopupWindow.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);

            mPopupWindow = new PopupWindow(vPopupWindow, builder.mWidth, builder.mHeight);
            mPopupWindow.setAnimationStyle(builder.mAnimStyle);     //設定動畫
            mPopupWindow.setSoftInputMode(builder.mMode);           //適配虛擬鍵
            mPopupWindow.setBackgroundDrawable(builder.mDrawable);  //背景

            switch (builder.mClickBackgroundToDismiss) {
                case AWDPopupWindowMgr.Dismiss:
                    mPopupWindow.setFocusable(true);        //獲得焦點
                    mPopupWindow.setTouchable(true);        //響應點擊事件
                    mPopupWindow.setOutsideTouchable(true); //響應外部點擊事件
                    break;
                case AWDPopupWindowMgr.NoDismiss:
                    mPopupWindow.setFocusable(true);            //獲得焦點
                    mPopupWindow.setTouchable(true);            //響應點擊事件
                    mPopupWindow.setOutsideTouchable(false);    //響應外部點擊事件
                    break;
            }
        } else {
            if (blnLogSwitch) {
                Log.d("HLPopupWindowMgr", "mContext : " + String.valueOf(builder.mContext) + "\n" + "mLayout : " + String.valueOf(builder.mLayout));
            }
        }

        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                if (null != listener) {
                    listener.OnDismiss();
                } else {
                    if (blnLogSwitch) {
                        Log.d("HLPopupWindowMgr", "listener : " + String.valueOf(listener));
                    }
                }
            }
        });

        mPopupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (null != listener) {
                    listener.OnBackgroundTouch(view, motionEvent);
                } else {
                    if (blnLogSwitch) {
                        Log.d("HLPopupWindowMgr", "listener : " + String.valueOf(listener));
                    }
                }
                return false;
            }
        });
    }

    /**
     * 懶人的showAtLocation寫法
     *
     * @param fatherLayout
     * @return
     */
    public AWDPopupWindowMgr showAtLocation(int fatherLayout) {
        if (mPopupWindow != null) {
            View vShowAtLocation = LayoutInflater.from(builder.mContext).inflate(fatherLayout, null);
            mPopupWindow.showAtLocation(vShowAtLocation, Gravity.BOTTOM, 0, 0);
        }
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
        if (mPopupWindow != null) {
            View vShowAtLocation = LayoutInflater.from(builder.mContext).inflate(fatherLayout, null);
            mPopupWindow.showAtLocation(vShowAtLocation, gravity, x, y);
        }
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
        if (mPopupWindow != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                mPopupWindow.showAsDropDown(targeView, x, y, gravity);
            }
        }
        return this;
    }

    /**
     * @param view 取得元件
     * @return
     */
    public View findViewById(int view) {
        if (mPopupWindow != null) {
            return this.vPopupWindow.findViewById(view);
        }
        return null;
    }

    /**
     * 取得PopupWindow整體的寬
     *
     * @return
     */
    public int getPopupWindowWidth() {
        return vPopupWindow != null ? vPopupWindow.getMeasuredWidth() : 0;
    }

    /**
     * 取得PopupWindow整體的高
     *
     * @return
     */
    public int getPopupWindowHeight() {
        return vPopupWindow != null ? vPopupWindow.getMeasuredHeight() : 0;
    }

    /**
     * 關閉彈跳視窗
     */
    public void dismiss() {
        if (mPopupWindow != null) {
            mPopupWindow.dismiss();
        }
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

    public static class initi {

        private Context mContext;
        private int mLayout = AWDPopupWindowMgr.NoSetting;                         //設定Xml
        private int mWidth = WindowManager.LayoutParams.MATCH_PARENT;               //設定寬，預設手機寬
        private int mHeight = WindowManager.LayoutParams.WRAP_CONTENT;              //設定高，預設Xml高
        private int mAnimStyle = R.style.popupWindowAnim;                        //設定動畫
        private int mMode = WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE;    //適配虛擬鍵
        private Drawable mDrawable = new ColorDrawable(0x00000000);                 //設定背景，預設透明
        private int mClickBackgroundToDismiss = AWDPopupWindowMgr.Dismiss;         //點背景時要不要關閉彈跳視窗

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
