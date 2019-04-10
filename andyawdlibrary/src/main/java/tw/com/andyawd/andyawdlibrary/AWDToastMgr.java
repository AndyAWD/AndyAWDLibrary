package tw.com.andyawd.andyawdlibrary;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;

import androidx.core.content.ContextCompat;

import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;

/**
 * Created by andydai on 2018/9/18.
 */

public class AWDToastMgr {
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
     * Show the view or text notification for a short period of time.  This time
     * could be user-definable.  This is the default.
     */
    public static final int LENGTH_SHORT = 0;

    /**
     * Show the view or text notification for a long period of time.  This time
     * could be user-definable.
     */
    public static final int LENGTH_LONG = 1;

    /**
     * 資源檔預設值，用來判斷有沒有另外設定
     */
    private static final int NO_SETTING = 529;

    /**
     * Logcat會用到的字串
     */
    private static final String NO_TEXT = "";
    private static final String CONTEXT = "context : ";
    private static final String GRAVITY = "gravity : ";
    private static final String X_OFFSET = "xOffset : ";
    private static final String Y_OFFSET = "yOffset : ";
    private static final String SEPARATION_LINE = " / ";
    private static final String TEXT_COLOR = "textColor : ";
    private static final String TEXT_GRAVITY = "textGravity : ";
    private static final String TEXT_BACKGROUND_COLOR = "textBackgroundColor :";
    private static final String TEXT_SIZE = "textSize : ";
    private static final String BACKGROUND_COLOR = "backgroundColor : ";
    private static final String BACKGROUND_PICTURE = "backgroundPicture : ";
    private static final String LAYOUT = "layout : ";
    private static final String TOAST = "toast : ";

    private static Toast toast;
    private View vToast;
    private init init;
    private TextView tvToast;
    private LinearLayout.LayoutParams layoutParams;
    private LinearLayout linearLayout;

    public AWDToastMgr(init init) {
        this.init = init;
        checkContext();
        setToastViewLayoutInflater();
    }

    private void setToastSetting() {
        checkContext();
        initToastInfo();
        setToastView();
        setToastGravity();
        setToastTextColor();
        setToastTextGravity();
        setToastTextBackgroundColor();
        setToastTextSize();
        setToastBackgroundColor();
        setToastBackgroundPicture();
    }

    /**
     * 檢查Context
     */
    private void checkContext() {
        if (null == init.context) {
            return;
        }
    }

    /**
     * 設定吐司layout的id
     */
    private void setToastViewLayoutInflater() {
        if (NO_SETTING != init.layout) {
            vToast = LayoutInflater.from(init.context).inflate(init.layout, null);
        }
    }

    /**
     * 初始化吐司
     */
    private void initToastInfo() {
        toast = Toast.makeText(init.context, NO_TEXT, init.duration);
        linearLayout = (LinearLayout) toast.getView();
        tvToast = (TextView) linearLayout.findViewById(android.R.id.message);
        layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        layoutParams.gravity = Gravity.CENTER_VERTICAL;
    }

    /**
     * 設定吐司layout
     */
    private void setToastView() {
        if (NO_SETTING != init.layout) {
            toast.setView(vToast);
        }
    }

    /**
     * 設定吐司位置和偏移量
     */
    private void setToastGravity() {
        if (NO_SETTING != init.gravity && NO_SETTING != init.xOffset && NO_SETTING != init.yOffset) {
            toast.setGravity(init.gravity, init.xOffset, init.yOffset);
        }
    }

    /**
     * 設定吐司文字顏色
     */
    private void setToastTextColor() {
        if (NO_SETTING != init.textColor) {
            tvToast.setTextColor(ContextCompat.getColor(init.context, init.textColor));
        }
    }

    /**
     * 設定吐司文字位置和偏移量
     */
    private void setToastTextGravity() {
        if (NO_SETTING != init.textGravity) {
            tvToast.setGravity(init.textGravity);
        }
    }

    /**
     * 設定吐司文字背景顏色
     */
    private void setToastTextBackgroundColor() {
        if (NO_SETTING != init.textBackgroundColor) {
            tvToast.setBackgroundColor(ContextCompat.getColor(init.context, init.textBackgroundColor));
        }
    }

    /**
     * 設定吐司文字大小
     */
    private void setToastTextSize() {
        if (0 != init.textSize) {
            tvToast.setTextSize(init.textSize);
        }
    }

    /**
     * 設定吐司背景顏色
     */
    private void setToastBackgroundColor() {
        if (NO_SETTING != init.backgroundColor) {
            linearLayout.setBackgroundColor(ContextCompat.getColor(init.context, init.backgroundColor));
        }
    }

    /**
     * 設定吐司圖案
     */
    private void setToastBackgroundPicture() {
        if (NO_SETTING != init.backgroundPicture) {
            ImageView imageView = new ImageView(init.context);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            options.inPurgeable = true;
            options.inInputShareable = true;
            InputStream inputStream = init.context.getResources().openRawResource(init.backgroundPicture);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream, null, options);
            BitmapDrawable bitmapDrawable = new BitmapDrawable(init.context.getResources(), bitmap);
            imageView.setBackgroundDrawable(bitmapDrawable);
            linearLayout.addView(imageView, 0, layoutParams);
        }
    }

    /**
     * @param id 取得元件
     */
    public View findViewById(int id) {
        return this.vToast.findViewById(id);
    }

    /**
     * 設定吐司顯示的方式
     */
    private void setToastShow(String message) {
        if (null == toast) {
            return;
        }

        if (NO_SETTING != init.layout) {
            toast.show();
            return;
        }

        if (!TextUtils.isEmpty(message)) {
            toast.setText(message);
        }

        toast.show();
    }

    /**
     * 顯示文字吐司，如果有設定Layout那就不顯示文字只顯示Layout
     */
    public void show(String message) {
        setToastSetting();
        setToastShow(message);
    }

    /**
     * 顯示沒文字的吐司，如果有設定Layout那就不顯示文字只顯示Layout
     */
    public void show() {
        setToastSetting();
        setToastShow(NO_TEXT);
    }

    public static class init {
        private Context context = null;
        private int duration = Toast.LENGTH_SHORT;  //設定顯示方式，預設短時間(LENGTH_SHORT)、還有長時間(LENGTH_LONG)
        private int gravity = NO_SETTING;   //起始位置
        private int xOffset = NO_SETTING;   //橫向偏移量，X軸
        private int yOffset = NO_SETTING;   //縱向偏移量，Y軸
        private int layout = NO_SETTING;    //設定自定頁面Layout
        private int backgroundColor = NO_SETTING;   //設定吐司背景顏色
        private int backgroundPicture = NO_SETTING;    //設定吐司背景圖片
        private int textColor = NO_SETTING;     //設定文字顏色
        private int textSize = 0;   //設定文字大小
        private int textBackgroundColor = NO_SETTING;   //設定文字背景顏色
        private int textGravity = NO_SETTING;   //設定文字位置

        public init(Context context) {
            this.context = context;
        }

        public init setDuration(int duration) {
            this.duration = duration;
            return this;
        }

        public init setGravity(int gravity, int xOffset, int yOffset) {
            this.gravity = gravity;
            this.xOffset = xOffset;
            this.yOffset = yOffset;
            return this;
        }

        public init setLayout(int layout) {
            this.layout = layout;
            return this;
        }

        public init setBackgroundColor(int backgroundColor) {
            this.backgroundColor = backgroundColor;
            return this;
        }

        public init setBackgroundPicture(int backgroundPicture) {
            this.backgroundPicture = backgroundPicture;
            return this;
        }

        public init setTextColor(int textColor) {
            this.textColor = textColor;
            return this;
        }

        public init setTextSize(int textSize) {
            this.textSize = textSize;
            return this;
        }

        public init setTextBackgroundColor(int textbackgroundColor) {
            this.textBackgroundColor = textbackgroundColor;
            return this;
        }

        public init setTextGravity(int textGravity) {
            this.textGravity = textGravity;
            return this;
        }

        public AWDToastMgr build() {
            return new AWDToastMgr(this);
        }
    }
}
