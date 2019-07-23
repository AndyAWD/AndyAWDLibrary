package tw.com.andyawd.andyawdlibrary;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
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

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import tw.com.andyawd.andyawdlibrary.interfaceListener.OnPopupWindowListener;

/**
 * Created by andydai on 2018/10/2.
 * 從底往上顯示︰showAtLocation(主頁面(父類別)Xml, Gravity.BOTTOM, 0, 0)，或懶人寫法showAtLocation(主頁面(父類別)Xml)
 * 指定View往上顯示︰showAsDropDown(指定View, Gravity.BOTTOM, 0, getPopupWindowHeight())
 * 指定View往下上顯示︰showAsDropDown(指定View, Gravity.BOTTOM, 0, -getPopupWindowHeight())
 */

public class AWDPopupWindowMgr {
    private static final String TAG = AWDToastMgr.class.getSimpleName();

    /**
     * 資源檔預設值，用來判斷有沒有另外設定
     */
    private static final int NO_SETTING = 529;

    private PopupWindow popupWindow;
    private View view;
    private init init;
    private OnPopupWindowListener listener;    //命名

    public void setOnPopupWindowListener(OnPopupWindowListener listener) {    //初始化interface
        this.listener = listener;
    }

    public AWDPopupWindowMgr(init init) {
        this.init = init;

        if (null == init.context && AWDPopupWindowMgr.NO_SETTING == init.layout) {
            return;
        }

        view = LayoutInflater.from(init.context).inflate(init.layout, null);
        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);

        popupWindow = new PopupWindow(view, init.width, init.height);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        popupWindow.setOutsideTouchable(init.isOutsideTouchable); //響應外部點擊事件
        popupWindow.setAnimationStyle(init.animStyle);     //設定動畫
        popupWindow.setSoftInputMode(init.mode);           //適配虛擬鍵
        popupWindow.setFocusable(init.isFocusable);        //獲得焦點
        popupWindow.setTouchable(init.isTouchable);        //響應點擊事件

        popupWindow.setOnDismissListener(popupWindow_Dismiss);
        popupWindow.setTouchInterceptor(popupWindow_Touch);
    }

    private final PopupWindow.OnDismissListener popupWindow_Dismiss = new PopupWindow.OnDismissListener() {
        @Override
        public void onDismiss() {
            if (null == listener) {
                return;
            }

            listener.OnDismiss();
        }
    };

    private final View.OnTouchListener popupWindow_Touch = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (null == listener) {
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
        if (null == popupWindow) {
            return null;
        }

        if (null == init.context) {
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
        if (null == popupWindow) {
            return null;
        }

        if (null == init.context) {
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
        if (null == popupWindow) {
            return null;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            popupWindow.showAsDropDown(targeView, x, y, gravity);
        }

        return this;
    }

    /**
     * @param view 取得元件
     * @return
     */
    public View findViewById(int view) {
        if (null == popupWindow) {
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
        if (null == popupWindow) {
            return;
        }

        popupWindow.dismiss();
    }

    public static class init {
        private Context context;
        private int layout = AWDPopupWindowMgr.NO_SETTING;                         //設定Xml
        private int width = WindowManager.LayoutParams.MATCH_PARENT;               //設定寬，預設手機寬
        private int height = WindowManager.LayoutParams.WRAP_CONTENT;              //設定高，預設Xml高
        private int animStyle = R.style.popupWindowAnim;                        //設定動畫
        private int mode = WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE;    //適配虛擬鍵
        private boolean isFocusable = true;
        private boolean isTouchable = true;
        private boolean isOutsideTouchable = true;

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

        public init setSoftInputMode(int mode) {
            this.mode = mode;
            return this;
        }

        public init setFocusable(boolean isFocusable) {
            this.isFocusable = isFocusable;
            return this;
        }

        public init setTouchable(boolean isTouchable) {
            this.isTouchable = isTouchable;
            return this;
        }

        public init setOutsideTouchable(boolean isOutsideTouchable) {
            this.isOutsideTouchable = isOutsideTouchable;
            return this;
        }

        public AWDPopupWindowMgr build() {
            return new AWDPopupWindowMgr(this);
        }
    }
}
