package tw.com.andyawd.andyawdlibrary;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * Created by andydai on 2018/9/13.
 * mode 0 : 基本顯示
 * mode 1 : 文字上色
 * mode 2 : 背景上色
 * mode 3 : 文字背景上色
 */

public class AWDSnackbarMgr {

    private static Snackbar snackbar;
//    private static Snackbar snackbar1;
//    private static Snackbar snackbar2;
//    private static Snackbar snackbar3;

    public static final int Basic = 0;
    public static final int TextColor = 1;
    public static final int BackgroundColor = 2;
    public static final int TextBackgroundColor = 3;
    public static final int Layout = 4;
    public static final int LENGTH_SHORT = 2000;
    public static final int LENGTH_LONG = 3500;

    private View view;
    private View vSnackbar;
    private initi builder;
    private TextView tvSnackbarText;

    public AWDSnackbarMgr(initi builder) {
        this.builder = builder;
    }

    public void show(int mode) {

        if (null == snackbar) {
            if (mode >= Basic) {
                snackbar = Snackbar.make(builder.mView, builder.mMessage, builder.mDuration);
            }

            if (null != builder.mContext) {
                if (mode >= TextColor) {
                    if (0 != builder.mTextColor) {
                        vSnackbar = snackbar.getView();
                        tvSnackbarText = (TextView) vSnackbar.findViewById(android.support.design.R.id.snackbar_text);
                        tvSnackbarText.setTextColor(ContextCompat.getColor(builder.mContext, builder.mTextColor));
                    } else {
                        Log.d("AWDSnackbarMgr", "TextColor : " + String.valueOf(builder.mTextColor));
                    }
                }

                if (mode >= BackgroundColor) {
                    if (0 != builder.mBackgroundColor) {
                        vSnackbar = snackbar.getView();
                        tvSnackbarText = (TextView) vSnackbar.findViewById(android.support.design.R.id.snackbar_text);
                        vSnackbar.setBackgroundColor(ContextCompat.getColor(builder.mContext, builder.mBackgroundColor));
                    } else {
                        Log.d("AWDSnackbarMgr", "BacakgroundColor : " + builder.mBackgroundColor);
                    }
                }

                if (mode >= TextBackgroundColor) {
                    if (0 != builder.mBackgroundColor) {
                        vSnackbar = snackbar.getView();
                        tvSnackbarText = (TextView) vSnackbar.findViewById(android.support.design.R.id.snackbar_text);
                        tvSnackbarText.setTextColor(ContextCompat.getColor(builder.mContext, builder.mTextColor));
                        vSnackbar.setBackgroundColor(ContextCompat.getColor(builder.mContext, builder.mBackgroundColor));
                    } else {
                        Log.d("AWDSnackbarMgr", "BacakgroundColor : " + builder.mBackgroundColor);
                    }
                }

                if (mode >= Layout) {
                    if (0 != builder.mLayout) {
                        Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) vSnackbar;
                        View vSnackbarLayout = LayoutInflater.from(builder.mContext).inflate(builder.mLayout, null);
                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                        layoutParams.gravity = Gravity.CENTER_VERTICAL;
                        snackbarLayout.addView(vSnackbarLayout, 0, layoutParams);
                    } else {
                        Log.d("AWDSnackbarMgr", "Layout : " + String.valueOf(builder.mLayout));
                    }
                }
            } else {
                Log.d("AWDSnackbarMgr", "mContext : " + String.valueOf(builder.mContext));
            }
        }
        snackbar.show();

        switch (builder.mDuration) {
            case Snackbar.LENGTH_SHORT:
                SnackbarToNull(LENGTH_SHORT);
                break;
            case Snackbar.LENGTH_LONG:
                SnackbarToNull(LENGTH_LONG);
                break;
            case Snackbar.LENGTH_INDEFINITE:
                break;
        }
    }

    private void SnackbarToNull(int timer) {
        CountDownTimer countDownTimer = new CountDownTimer(timer, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                snackbar = null;
            }
        };
        countDownTimer.start();
    }

    public void ClearMemory() {
        System.gc();
    }

    public void dismiss() {
        if (null != snackbar) {
            snackbar.dismiss();
            snackbar = null;
        }
    }

    public static class initi {

        private View mView;
        private Context mContext;   //只有基本的話不用Context
        private String mMessage;    //設定文字訊息
        private int mDuration = Snackbar.LENGTH_LONG;  //設定顯示方式，預設短時間(LENGTH_SHORT)、還有長時間(LENGTH_LONG)、固定顯示(LENGTH_INDEFINITE)
        private int mTextColor;     //設定文字顏色
        private int mBackgroundColor;   //設定背景顏色
        private int mLayout;

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

        public initi setBacakgroundColor(int bacakgroundColor) {
            this.mBackgroundColor = bacakgroundColor;
            return this;
        }

        public initi setLayout(int layout) {
            this.mLayout = layout;
            return this;
        }

        public AWDSnackbarMgr build() {
            return new AWDSnackbarMgr(this);
        }
    }
}
