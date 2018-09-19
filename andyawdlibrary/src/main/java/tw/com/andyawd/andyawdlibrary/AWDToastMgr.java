package tw.com.andyawd.andyawdlibrary;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.content.ContextCompat;
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
    public static final boolean Log_On = true;
    public static final boolean Log_Off = false;
    private static final int NoSetting = 529;

    private static Toast toast;
    private initi builder;
    private TextView tvToast;
    private LinearLayout.LayoutParams layoutParams;
    private LinearLayout linearLayout;

    private boolean blnLogSwitch = Log_Off;

    public AWDToastMgr(initi builder) {
        this.builder = builder;
    }

    public void show(String message) {
        if (null != builder.mContext) {
            if (null == toast) {

                toast = Toast.makeText(builder.mContext, message, builder.mDuration);
                linearLayout = (LinearLayout) toast.getView();
                tvToast = (TextView) linearLayout.findViewById(android.R.id.message);
                layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                layoutParams.gravity = Gravity.CENTER_VERTICAL;

                if (NoSetting != builder.mGravity && NoSetting != builder.mXOffset && NoSetting != builder.mYOffset) {
                    toast.setGravity(builder.mGravity, builder.mXOffset, builder.mYOffset);
                } else {
                    if (blnLogSwitch) {
                        Log.d("AWDToastMgr", "mYOffset : " + String.valueOf(builder.mYOffset));
                    }
                }

                if (NoSetting != builder.mTextColor) {
                    tvToast.setTextColor(ContextCompat.getColor(builder.mContext, builder.mTextColor));
                } else {
                    if (blnLogSwitch) {
                        Log.d("AWDToastMgr", "mTextColor : " + String.valueOf(builder.mTextColor));
                    }
                }

                if (NoSetting != builder.mTextBackgroundColor) {
                    tvToast.setBackgroundColor(ContextCompat.getColor(builder.mContext, builder.mTextBackgroundColor));
                } else {
                    if (blnLogSwitch) {
                        Log.d("AWDToastMgr", "mTextBackgroundColor : " + String.valueOf(builder.mTextBackgroundColor));
                    }
                }

                if (0 != builder.mTextSize) {
                    tvToast.setTextSize(builder.mTextSize);
                } else {
                    if (blnLogSwitch) {
                        Log.d("AWDToastMgr", "mTextSize : " + String.valueOf(builder.mTextSize));
                    }
                }

                if (NoSetting != builder.mBackgroundColor) {
                    linearLayout.setBackgroundColor(ContextCompat.getColor(builder.mContext, builder.mBackgroundColor));
                } else {
                    if (blnLogSwitch) {
                        Log.d("AWDToastMgr", "mBackgroundColor : " + String.valueOf(builder.mBackgroundColor));
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
                    linearLayout.addView(imageView, 0, layoutParams);
                } else {
                    if (blnLogSwitch) {
                        Log.d("AWDToastMgr", "mBacakgroundPicture : " + String.valueOf(builder.mBacakgroundPicture));
                    }
                }

                if (NoSetting != builder.mLayout) {

                } else {
                    if (blnLogSwitch) {
                        Log.d("AWDToastMgr", "mLayout : " + String.valueOf(builder.mLayout));
                    }
                }

            } else {
                toast.setText(message);
            }

            toast.show();

        } else {
            if (blnLogSwitch) {
                Log.d("AWDToastMgr", "mTextColor : " + String.valueOf(builder.mTextColor));
            }
        }
    }

    public void setLog(boolean Log_OnOff) {
        this.blnLogSwitch = Log_OnOff;
    }

    public static class initi {

        private Context mContext = null;
        private int mDuration = Toast.LENGTH_SHORT;  //設定顯示方式，預設短時間(LENGTH_SHORT)、還有長時間(LENGTH_LONG)
        private int mGravity = NoSetting;   //起始位置
        private int mXOffset = NoSetting;   //橫向偏移量，X軸
        private int mYOffset = NoSetting;   //縱向偏移量，Y軸
        private int mLayout = NoSetting;    //設定自定頁面
        private int mBackgroundColor = NoSetting;   //設定吐司背景顏色
        private int mBacakgroundPicture = NoSetting;    //設定吐司背景圖片
        private int mTextColor = NoSetting;     //設定文字顏色
        private int mTextSize = 0;   //設定文字大小
        private int mTextBackgroundColor = NoSetting;   //設定文字背景顏色

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

        public initi setBackgroundColor(int bacakgroundColor) {
            this.mBackgroundColor = bacakgroundColor;
            return this;
        }

        public initi setBacakgroundPicture(int bacakgroundPicture) {
            this.mBacakgroundPicture = bacakgroundPicture;
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

        public initi setTextBacakgroundColor(int textbacakgroundColor) {
            this.mTextBackgroundColor = textbacakgroundColor;
            return this;
        }

        public AWDToastMgr build() {
            return new AWDToastMgr(this);
        }
    }
}
