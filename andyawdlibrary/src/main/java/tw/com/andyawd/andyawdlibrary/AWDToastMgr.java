package tw.com.andyawd.andyawdlibrary;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;

import androidx.core.content.ContextCompat;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
     * Logcat會用到的常數
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
    private boolean isLogOpen = LOG_OFF;

    @Deprecated
    private static final int NoSetting = 529;
    @Deprecated
    public static final boolean Log_On = true;
    @Deprecated
    public static final boolean Log_Off = false;
    @Deprecated
    private boolean blnLogSwitch = Log_Off;
    @Deprecated
    private initi builder;

    public AWDToastMgr(init init) {
        this.init = init;

        if (null == init.context) {
            if (isLogOpen) {
                Log.d(TAG, CONTEXT + String.valueOf(init.context));
            }
            return;
        }

        toast = Toast.makeText(init.context, NO_TEXT, init.duration);
        linearLayout = (LinearLayout) toast.getView();
        tvToast = (TextView) linearLayout.findViewById(android.R.id.message);
        layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER_VERTICAL;

        if (NO_SETTING != init.gravity && NO_SETTING != init.xOffset && NO_SETTING != init.yOffset) {
            toast.setGravity(init.gravity, init.xOffset, init.yOffset);
        } else {
            if (isLogOpen) {
                Log.d(TAG, GRAVITY + String.valueOf(init.gravity) + SEPARATION_LINE + X_OFFSET + String.valueOf(init.xOffset) + SEPARATION_LINE + Y_OFFSET + String.valueOf(init.yOffset));
            }
        }

        if (NO_SETTING != init.textColor) {
            tvToast.setTextColor(ContextCompat.getColor(init.context, init.textColor));
        } else {
            if (isLogOpen) {
                Log.d(TAG, TEXT_COLOR + String.valueOf(init.textColor));
            }
        }

        if (NO_SETTING != init.textGravity) {
            tvToast.setGravity(init.textGravity);
        } else {
            if (isLogOpen) {
                Log.d(TAG, TEXT_GRAVITY + String.valueOf(init.textGravity));
            }
        }

        if (NO_SETTING != init.textBackgroundColor) {
            tvToast.setBackgroundColor(ContextCompat.getColor(init.context, init.textBackgroundColor));
        } else {
            if (isLogOpen) {
                Log.d(TAG, TEXT_BACKGROUND_COLOR + String.valueOf(init.textBackgroundColor));
            }
        }

        if (0 != init.textSize) {
            tvToast.setTextSize(init.textSize);
        } else {
            if (isLogOpen) {
                Log.d(TAG, TEXT_SIZE + String.valueOf(init.textSize));
            }
        }

        if (NO_SETTING != init.backgroundColor) {
            linearLayout.setBackgroundColor(ContextCompat.getColor(init.context, init.backgroundColor));
        } else {
            if (isLogOpen) {
                Log.d(TAG, BACKGROUND_COLOR + String.valueOf(init.backgroundColor));
            }
        }

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
        } else {
            if (isLogOpen) {
                Log.d(TAG, BACKGROUND_PICTURE + String.valueOf(init.backgroundPicture));
            }
        }

        if (NO_SETTING != init.layout) {
            vToast = LayoutInflater.from(init.context).inflate(init.layout, null);
            toast.setView(vToast);
        } else {
            if (isLogOpen) {
                Log.d(TAG, LAYOUT + String.valueOf(init.layout));
            }
        }
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

    /**
     * 顯示文字吐司，如果有設定Layout那就不顯示文字只顯示Layout
     *
     * @param message
     */
    public void show(String message) {
        if (null == toast) {
            if (isLogOpen) {
                Log.d(TAG, TOAST + String.valueOf(toast));
            }
            return;
        }

        if (NO_SETTING != init.layout) {
            toast.show();
            return;
        }

        toast.setText(message);
        toast.show();
    }

    /**
     * 顯示沒文字的吐司，如果有設定Layout那就不顯示文字只顯示Layout
     */
    public void show() {
        if (null == toast) {
            if (isLogOpen) {
                Log.d(TAG, TOAST + String.valueOf(toast));
            }
            return;
        }

        if (NO_SETTING != init.layout) {
            toast.show();
            return;
        }

        toast.setText(NO_TEXT);
        toast.show();
    }


    /**
     * @param view 取得元件
     * @return
     */
    public View findViewById(int view) {
        if (null == toast) {
            if (isLogOpen) {
                Log.d(TAG, TOAST + String.valueOf(vToast));
            }
            return null;
        }

        return this.vToast.findViewById(view);
    }

    /**
     * 設定Log開關
     *
     * @param Log_OnOff
     */
    public void setLog(boolean Log_OnOff) {
        this.blnLogSwitch = Log_OnOff;
        this.isLogOpen = Log_OnOff;
    }


    @Deprecated
    public AWDToastMgr(initi builder) {
        this.builder = builder;

        if (null == builder.mContext) {
            if (isLogOpen) {
                Log.d(TAG, "mContext : " + String.valueOf(builder.mContext));
            }
            return;
        }

        toast = Toast.makeText(builder.mContext, "", builder.mDuration);
        linearLayout = (LinearLayout) toast.getView();
        tvToast = (TextView) linearLayout.findViewById(android.R.id.message);
        layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER_VERTICAL;

        if (NO_SETTING != builder.mGravity && NO_SETTING != builder.mXOffset && NO_SETTING != builder.mYOffset) {
            toast.setGravity(builder.mGravity, builder.mXOffset, builder.mYOffset);
        } else {
            if (isLogOpen) {
                Log.d("AWDToastMgr", "mYOffset : " + String.valueOf(builder.mYOffset));
            }
        }


        if (null != builder.mContext) {
            toast = Toast.makeText(builder.mContext, "", builder.mDuration);

            linearLayout = (LinearLayout) toast.getView();
            tvToast = (TextView) linearLayout.findViewById(android.R.id.message);
            layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.gravity = Gravity.CENTER_VERTICAL;

            if (NO_SETTING != builder.mGravity && NO_SETTING != builder.mXOffset && NO_SETTING != builder.mYOffset) {
                toast.setGravity(builder.mGravity, builder.mXOffset, builder.mYOffset);
            } else {
                if (isLogOpen) {
                    Log.d("AWDToastMgr", "mYOffset : " + String.valueOf(builder.mYOffset));
                }
            }

            if (NO_SETTING != builder.mTextColor) {
                tvToast.setTextColor(ContextCompat.getColor(builder.mContext, builder.mTextColor));
            } else {
                if (isLogOpen) {
                    Log.d("AWDToastMgr", "mTextColor : " + String.valueOf(builder.mTextColor));
                }
            }

            if (NO_SETTING != builder.mTextGravity) {
                tvToast.setGravity(builder.mTextGravity);
            } else {
                if (isLogOpen) {
                    Log.d("AWDToastMgr", "mTextGravity : " + String.valueOf(builder.mTextGravity));
                }
            }

            if (NO_SETTING != builder.mTextBackgroundColor) {
                tvToast.setBackgroundColor(ContextCompat.getColor(builder.mContext, builder.mTextBackgroundColor));
            } else {
                if (isLogOpen) {
                    Log.d("AWDToastMgr", "mTextBackgroundColor : " + String.valueOf(builder.mTextBackgroundColor));
                }
            }

            if (0 != builder.mTextSize) {
                tvToast.setTextSize(builder.mTextSize);
            } else {
                if (isLogOpen) {
                    Log.d("AWDToastMgr", "mTextSize : " + String.valueOf(builder.mTextSize));
                }
            }

            if (NO_SETTING != builder.mBackgroundColor) {
                linearLayout.setBackgroundColor(ContextCompat.getColor(builder.mContext, builder.mBackgroundColor));
            } else {
                if (isLogOpen) {
                    Log.d("AWDToastMgr", "mBackgroundColor : " + String.valueOf(builder.mBackgroundColor));
                }
            }

            if (NO_SETTING != builder.mBackgroundPicture) {
                ImageView imageView = new ImageView(builder.mContext);
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inPreferredConfig = Bitmap.Config.RGB_565;
                options.inPurgeable = true;
                options.inInputShareable = true;
                InputStream inputStream = builder.mContext.getResources().openRawResource(builder.mBackgroundPicture);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream, null, options);
                BitmapDrawable bitmapDrawable = new BitmapDrawable(builder.mContext.getResources(), bitmap);
                imageView.setBackgroundDrawable(bitmapDrawable);
                linearLayout.addView(imageView, 0, layoutParams);
            } else {
                if (isLogOpen) {
                    Log.d("AWDToastMgr", "mBackgroundPicture : " + String.valueOf(builder.mBackgroundPicture));
                }
            }

            if (NO_SETTING != builder.mLayout) {
                vToast = LayoutInflater.from(builder.mContext).inflate(builder.mLayout, null);
                toast.setView(vToast);
            } else {
                if (isLogOpen) {
                    Log.d("AWDToastMgr", "mLayout : " + String.valueOf(builder.mLayout));
                }
            }

        } else {
            if (isLogOpen) {
                Log.d("AWDToastMgr", "mTextColor : " + String.valueOf(builder.mTextColor));
            }
        }
    }

    @Deprecated
    public static class initi {
        private Context mContext = null;
        private int mDuration = Toast.LENGTH_SHORT;  //設定顯示方式，預設短時間(LENGTH_SHORT)、還有長時間(LENGTH_LONG)
        private int mGravity = NoSetting;   //起始位置
        private int mXOffset = NoSetting;   //橫向偏移量，X軸
        private int mYOffset = NoSetting;   //縱向偏移量，Y軸
        private int mLayout = NoSetting;    //設定自定頁面Layout
        private int mBackgroundColor = NoSetting;   //設定吐司背景顏色
        private int mBackgroundPicture = NoSetting;    //設定吐司背景圖片
        private int mTextColor = NoSetting;     //設定文字顏色
        private int mTextSize = 0;   //設定文字大小
        private int mTextBackgroundColor = NoSetting;   //設定文字背景顏色
        private int mTextGravity = NoSetting;   //設定文字位置

        public initi setContext(Context context) {
            this.mContext = context;
            return this;
        }

        public initi setDuration(int duration) {
            this.mDuration = duration;
            return this;
        }

        public initi setGravity(int gravity, int xOffset, int yOffset) {
            this.mGravity = gravity;
            this.mXOffset = xOffset;
            this.mYOffset = yOffset;
            return this;
        }

        public initi setLayout(int layout) {
            this.mLayout = layout;
            return this;
        }

        public initi setBackgroundColor(int backgroundColor) {
            this.mBackgroundColor = backgroundColor;
            return this;
        }

        public initi setBackgroundPicture(int backgroundPicture) {
            this.mBackgroundPicture = backgroundPicture;
            return this;
        }

        public initi setTextColor(int textColor) {
            this.mTextColor = textColor;
            return this;
        }

        public initi setTextSize(int textSize) {
            this.mTextSize = textSize;
            return this;
        }

        public initi setTextBackgroundColor(int textbackgroundColor) {
            this.mTextBackgroundColor = textbackgroundColor;
            return this;
        }

        public initi setTextGravity(int textGravity) {
            this.mTextGravity = textGravity;
            return this;
        }

        public AWDToastMgr build() {
            return new AWDToastMgr(this);
        }
    }
}
