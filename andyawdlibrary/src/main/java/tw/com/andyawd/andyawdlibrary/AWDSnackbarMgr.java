package tw.com.andyawd.andyawdlibrary;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.CountDownTimer;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import androidx.core.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.InputStream;


/**
 * Created by andydai on 2018/9/13.
 *
 * Activity取View可以用getWindow().getDecorView()
 */

public class AWDSnackbarMgr {
    public static final boolean Log_On = true;
    public static final boolean Log_Off = false;
    public static final boolean GC_On = true;
    public static final boolean GC_Off = false;
    private static final boolean Show_On = true;
    private static final boolean Show_Off = false;
    private static final int NoSetting = 529;

    private static Snackbar snackbar;
    private View vSnackbar;
    private View vSnackbarLayout;
    private initi builder;
    private TextView tvSnackbarText;
    private Snackbar.SnackbarLayout snackbarLayout;
    private LinearLayout.LayoutParams layoutParams;

    private boolean blnLogSwitch = Log_On;
    private boolean blnSnackbarIsCanShow = Show_Off;

    public interface setOnActionClickListener {
        void Action();
    }

    public AWDSnackbarMgr(initi builder) {
        this.builder = builder;
        InitiSnackbar();
    }

    private void InitiSnackbar() {
        if (null != builder.mView && null != builder.mMessage && NoSetting != builder.mDuration) {
            if (null != builder.mActionClickListener) {
                if (NoSetting != builder.mActionTextColor) {
                    snackbar = Snackbar.make(builder.mView, builder.mMessage, builder.mDuration).addCallback(snackbar_Callback).setAction(builder.mActionText, snackbar_Action).setActionTextColor(builder.mActionTextColor);
                } else {
                    snackbar = Snackbar.make(builder.mView, builder.mMessage, builder.mDuration).addCallback(snackbar_Callback).setAction(builder.mActionText, snackbar_Action);
                }
            } else {
                snackbar = Snackbar.make(builder.mView, builder.mMessage, builder.mDuration).addCallback(snackbar_Callback);
            }

            blnSnackbarIsCanShow = Show_On;

            vSnackbar = snackbar.getView();
            tvSnackbarText = (TextView) vSnackbar.findViewById(com.google.android.material.R.id.snackbar_text);
            snackbarLayout = (Snackbar.SnackbarLayout) vSnackbar;
            if (null != builder.mContext){
                vSnackbarLayout = LayoutInflater.from(builder.mContext).inflate(builder.mLayout, null);
            }
            layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.gravity = Gravity.CENTER_VERTICAL;

            if (null != builder.mContext) {
                if (NoSetting != builder.mTextColor) {
                    tvSnackbarText.setTextColor(ContextCompat.getColor(builder.mContext, builder.mTextColor));
                } else {
                    if (blnLogSwitch) {
                        Log.d("AWDSnackbarMgr", "mTextColor : " + String.valueOf(builder.mTextColor));
                    }
                }

                if (NoSetting != builder.mBackgroundColor) {
                    vSnackbar.setBackgroundColor(ContextCompat.getColor(builder.mContext, builder.mBackgroundColor));
                } else {
                    if (blnLogSwitch) {
                        Log.d("AWDSnackbarMgr", "mBackgroundColor : " + builder.mBackgroundColor);
                    }
                }

                if (NoSetting != builder.mTextSize) {
                    tvSnackbarText.setTextSize(builder.mTextSize);
                } else {
                    if (blnLogSwitch) {
                        Log.d("AWDSnackbarMgr", "mTextSize : " + String.valueOf(builder.mTextSize));
                    }
                }

                if (NoSetting != builder.mLayout && null != builder.mContext) {
                    snackbarLayout.addView(vSnackbarLayout, 0, layoutParams);
                } else {
                    if (blnLogSwitch) {
                        Log.d("AWDSnackbarMgr", "mLayout : " + String.valueOf(builder.mLayout));
                    }
                }

                if (NoSetting != builder.mBacakgroundPicture) {
                    ImageView imageView = new ImageView(builder.mContext);
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inPreferredConfig = Bitmap.Config.RGB_565;
                    options.inPurgeable = true;
                    options.inInputShareable = true;
                    InputStream inputStream = builder.mContext.getResources().openRawResource(builder.mBacakgroundPicture);
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream, null, options);
                    BitmapDrawable bitmapDrawable = new BitmapDrawable(builder.mContext.getResources(), bitmap);
                    imageView.setBackgroundDrawable(bitmapDrawable);
                    snackbarLayout.addView(imageView, 0, layoutParams);
                } else {
                    if (blnLogSwitch) {
                        Log.d("AWDSnackbarMgr", "mBacakgroundPicture : " + String.valueOf(builder.mBacakgroundPicture));
                    }
                }

            } else {
                if (blnLogSwitch) {
                    Log.d("AWDSnackbarMgr", "mContext : " + String.valueOf(builder.mContext));
                }
            }
        } else {
            if (blnLogSwitch) {
                Log.d("AWDSnackbarMgr", "View : " + builder.mView + " / Message : " + builder.mMessage + " / Duration : " + builder.mDuration);
            }
            blnSnackbarIsCanShow = Show_Off;
        }
    }

    public void show() {
        if (blnSnackbarIsCanShow) {
            if (null == snackbar) {
                InitiSnackbar();
            }
            snackbar.show();
        } else {
            return;
        }
    }

    public void setLog(boolean Log_OnOff) {
        this.blnLogSwitch = Log_OnOff;
    }

    public void ClearMemory() {
        System.gc();
    }

    private void SnackbarDismiss() {
        snackbar.dismiss();
        snackbar = null;
    }

    public void dismiss(boolean GC_OnOff) {
        if (null != snackbar) {
            if (GC_OnOff) {
                ClearMemory();
                SnackbarDismiss();
            } else {
                SnackbarDismiss();
            }
        }
    }

    public void dismiss() {
        if (null != snackbar) {
            SnackbarDismiss();
        }
    }

    private View.OnClickListener snackbar_Action = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            builder.mActionClickListener.Action();
        }
    };

    private BaseTransientBottomBar.BaseCallback<Snackbar> snackbar_Callback = new BaseTransientBottomBar.BaseCallback<Snackbar>() {
        @Override
        public void onDismissed(Snackbar transientBottomBar, int event) {
            super.onDismissed(transientBottomBar, event);
            snackbar = null;
        }

        @Override
        public void onShown(Snackbar transientBottomBar) {
            super.onShown(transientBottomBar);

        }
    };

    public static class initi {
        private View mView = null;  //SnackBar依附的View
        private Context mContext = null;   //只有基本的話不用Context
        private String mMessage = null;    //設定文字訊息
        private int mDuration = NoSetting;  //設定顯示方式，預設短時間(Duration_LENGTH_SHORT)、還有長時間(Duration_LENGTH_LONG)、固定顯示(LENGTH_INDEFINITE)
        private int mTextColor = NoSetting;     //設定文字顏色
        private int mTextSize = NoSetting;   //設定文字大小
        private int mBackgroundColor = NoSetting;   //設定背景顏色
        private int mBacakgroundPicture = NoSetting;    //設定SnackBar背景圖片
        private int mLayout = NoSetting;    //設定自定頁面
        private String mActionText = null;  //設定按鈕文字
        private int mActionTextColor = NoSetting;   //設定按鈕文字顏色
        private setOnActionClickListener mActionClickListener;  //按鈕監聽

        public initi setView(View view) {
            this.mView = view;
            return this;
        }

        public initi setContext(Context context) {
            this.mContext = context;
            return this;
        }

        public initi setMessage(String message) {
            this.mMessage = message;
            return this;
        }

        public initi setDuration(int duration) {
            this.mDuration = duration;
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

        public initi setBacakgroundColor(int bacakgroundColor) {
            this.mBackgroundColor = bacakgroundColor;
            return this;
        }

        public initi setBacakgroundPicture(int bacakgroundPicture) {
            this.mBacakgroundPicture = bacakgroundPicture;
            return this;
        }

        public initi setLayout(int layout) {
            this.mLayout = layout;
            return this;
        }

        public initi setActionText(String buttonText) {
            this.mActionText = buttonText;
            return this;
        }

        public initi setActionTextColor(int buttonTextColor) {
            this.mActionTextColor = buttonTextColor;
            return this;
        }

        public initi setOnActionClickListener(setOnActionClickListener ActionClickListener) {
            this.mActionClickListener = ActionClickListener;
            return this;
        }

        public AWDSnackbarMgr build() {
            return new AWDSnackbarMgr(this);
        }
    }
}
