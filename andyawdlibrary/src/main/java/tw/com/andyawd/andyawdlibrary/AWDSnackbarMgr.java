package tw.com.andyawd.andyawdlibrary;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.design.widget.BaseTransientBottomBar;
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
 * <p>
 * Activity取View可以用getWindow().getDecorView()
 */

public class AWDSnackbarMgr {

    private static Snackbar snackbar;

    public static final boolean Log_On = true;
    public static final boolean Log_Off = false;

    public static final boolean GC_On = true;
    public static final boolean GC_Off = false;

    private static final boolean Show_On = true;
    private static final boolean Show_Off = false;

    public static final String ActionButtonText = "Button";

    private View vSnackbar;
    private initi builder;
    private TextView tvSnackbarText;
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
        if (null != builder.mView && null != builder.mMessage && 529 != builder.mDuration) {
            if (null != builder.mActionClickListener) {
                if (0 != builder.mActionTextColor) {
                    snackbar = Snackbar.make(builder.mView, builder.mMessage, builder.mDuration).addCallback(snackbar_Callback).setAction(builder.mActionText, snackbar_Action).setActionTextColor(builder.mActionTextColor);
                } else {
                    snackbar = Snackbar.make(builder.mView, builder.mMessage, builder.mDuration).addCallback(snackbar_Callback).setAction(builder.mActionText, snackbar_Action);
                }
            } else {
                snackbar = Snackbar.make(builder.mView, builder.mMessage, builder.mDuration).addCallback(snackbar_Callback);
            }
            blnSnackbarIsCanShow = Show_On;

            vSnackbar = snackbar.getView();
            tvSnackbarText = (TextView) vSnackbar.findViewById(android.support.design.R.id.snackbar_text);

            if (null != builder.mContext) {
                if (0 != builder.mTextColor) {
                    tvSnackbarText.setTextColor(ContextCompat.getColor(builder.mContext, builder.mTextColor));
                } else {
                    if (blnLogSwitch) {
                        Log.d("AWDSnackbarMgr", "Mode_TextColor : " + String.valueOf(builder.mTextColor));
                    }
                }

                if (0 != builder.mBackgroundColor) {
                    vSnackbar.setBackgroundColor(ContextCompat.getColor(builder.mContext, builder.mBackgroundColor));
                } else {
                    if (blnLogSwitch) {
                        Log.d("AWDSnackbarMgr", "BacakgroundColor : " + builder.mBackgroundColor);
                    }
                }

                if (0 != builder.mLayout) {
                    Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) vSnackbar;
                    View vSnackbarLayout = LayoutInflater.from(builder.mContext).inflate(builder.mLayout, null);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    layoutParams.gravity = Gravity.CENTER_VERTICAL;
                    snackbarLayout.addView(vSnackbarLayout, 0, layoutParams);
                } else {
                    if (blnLogSwitch) {
                        Log.d("AWDSnackbarMgr", "Mode_Layout : " + String.valueOf(builder.mLayout));
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
            Log.d("AWDSnackbarMgr", "snackbar_Action_001");
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
        private View mView = null;
        private Context mContext = null;   //只有基本的話不用Context
        private String mMessage = null;    //設定文字訊息
        private int mDuration = 529;  //設定顯示方式，預設短時間(Duration_LENGTH_SHORT)、還有長時間(Duration_LENGTH_LONG)、固定顯示(LENGTH_INDEFINITE)
        private int mTextColor = 0;     //設定文字顏色
        private int mBackgroundColor = 0;   //設定背景顏色
        private int mLayout = 0;    //設定自定頁面
        private String mActionText = null;  //設定按鈕文字
        private int mActionTextColor = 0;   //設定按鈕文字顏色
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

        public initi setBacakgroundColor(int bacakgroundColor) {
            this.mBackgroundColor = bacakgroundColor;
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
