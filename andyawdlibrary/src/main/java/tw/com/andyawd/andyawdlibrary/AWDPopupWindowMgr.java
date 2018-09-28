package tw.com.andyawd.andyawdlibrary;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
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

    private Context mContext;
    private PopupWindow popupWindow;
    private View view;

    public AWDPopupWindowMgr(initi builder) {
        mContext = builder.mContext;
        view = LayoutInflater.from(mContext).inflate(builder.mShowView, null);
        popupWindow = new PopupWindow(view, builder.mWidth, builder.mHeight, builder.mFocusable);
        popupWindow.setOutsideTouchable(builder.mOutSideClickClose);
        popupWindow.setBackgroundDrawable(builder.mColorDrawable);
        popupWindow.setSoftInputMode(builder.mMode);
        popupWindow.setAnimationStyle(builder.mAnimStyle);
    }

    /**
     * @param parent  主頁面(父類別)Xml
     * @param gravity 是否置中，不知道要打什麼的話輸入"Gravity.BOTTOM"
     * @param x       不知道要打什麼的話輸入"0"
     * @param y       不知道要打什麼的話輸入"0"
     * @return
     */
    public AWDPopupWindowMgr showAtLocation(int parent, int gravity, int x, int y) {
        if (popupWindow != null) {
            View vShowAtLocation = LayoutInflater.from(mContext).inflate(parent, null);
            popupWindow.showAtLocation(vShowAtLocation, gravity, x, y);
        }
        return this;
    }

    public AWDPopupWindowMgr showAtLocation(int parent) {
        if (popupWindow != null) {
            View vShowAtLocation = LayoutInflater.from(mContext).inflate(parent, null);
            popupWindow.showAtLocation(vShowAtLocation, Gravity.BOTTOM, 0, 0);
        }
        return this;
    }

    /**
     * @param targeParent 彈跳試窗會出現在這個View的下面
     * @param gravity     是否置中，不知道要打什麼的話輸入"Gravity.BOTTOM"
     * @param x           不知道要打什麼的話輸入"0"
     * @param y           不知道要打什麼的話輸入"0"
     * @return
     */
    public AWDPopupWindowMgr showAsDropDown(View targeParent, int gravity, int x, int y) {
        if (popupWindow != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                popupWindow.showAsDropDown(targeParent, x, y, gravity);
            }
        }
        return this;
    }

    public AWDPopupWindowMgr showAsDropDown(View targeParent) {
        if (popupWindow != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                popupWindow.showAsDropDown(targeParent, 0, 0, Gravity.BOTTOM);
            }
        }
        return this;
    }

    /**
     * @param view 取得元件
     * @return
     */
    public View findViewById(int view) {
        if (popupWindow != null) {
            return this.view.findViewById(view);
        }
        return null;
    }

    public void setFocusListener(int view, View.OnFocusChangeListener listener) {
        View vFocusListener = findViewById(view);
        vFocusListener.setOnFocusChangeListener(listener);
    }

    public void dismiss() {
        if (popupWindow != null) {
            popupWindow.dismiss();
        }
    }

    public static class initi {

        private Context mContext;
        private int mShowView;    //設定Xml
        private int mWidth = WindowManager.LayoutParams.MATCH_PARENT;   //設定寬，預設手機寬
        private int mHeight = WindowManager.LayoutParams.WRAP_CONTENT;  //設定高，預設Xml高
        private boolean mFocusable = true; //是否取得焦點
        private boolean mOutSideClickClose = true;  //是否取消外部點擊
        private int mAnimStyle = R.style.popupWindowAnim; //設定動畫
        private int mMode = WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE;    //適配虛擬鍵
        private ColorDrawable mColorDrawable = new ColorDrawable(0x00000000);   //設定背景，預設透明

        public initi setContext(Context context) {
            this.mContext = context;
            return this;
        }

        public initi setShowView(int showView) {
            this.mShowView = showView;
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

        public initi setFocusable(boolean focusable) {
            this.mFocusable = focusable;
            return this;
        }

        public initi setOutSideClickClose(boolean outSideClickClose) {
            this.mOutSideClickClose = outSideClickClose;
            return this;
        }

        public initi setAnimStyle(int animStyle) {
            this.mAnimStyle = animStyle;
            return this;
        }

        public initi setBackground(ColorDrawable colorDrawable) {
            this.mColorDrawable = colorDrawable;
            return this;
        }

        public initi setSoftInputMode(int mode) {
            this.mMode = mode;
            return this;
        }

        public AWDPopupWindowMgr build() {
            return new AWDPopupWindowMgr(this);
        }
    }
}
