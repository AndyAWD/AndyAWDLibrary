package tw.com.andyawd.andyawdlibrary;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by andydai on 2018/1/16.
 */

public class AWDToolMgr {

    private static Toast toast;
    private static Snackbar snackbar;
    private static Snackbar snackbarTextColor;
    private static Snackbar snackbarTextColorBackground;

    private PackageManager packageManager;
    private List<PackageInfo> lstPackageManager = new ArrayList<>();
    private List<String> lstPackageName = new ArrayList<>();
    private Vibrator vibrator;

    private AWDToolMgr() {

    }

    public static AWDToolMgr getInstance() {
        return AWDToolMgrHolder.awdToolMgr;
    }

    private static class AWDToolMgrHolder {
        private static final AWDToolMgr awdToolMgr = new AWDToolMgr();
    }

    /**
     * 吐司訊息
     *
     * @param message
     */
    public void Toast(Context context, String message, int durationToast) {
        if (toast == null) {
            toast = Toast.makeText(context, message, durationToast);
        } else {
            toast.setText(message);
        }
        toast.show();
        context = null;
    }

    /**
     * 底層訊息
     *
     * @param view
     * @param showMessage
     */
    public void Snackbar(View view, String showMessage, int durationSnackbar) {
        if (null == snackbar) {
            snackbar = Snackbar.make(view, showMessage, durationSnackbar);
        }
        snackbar.show();
    }

    public void snackbarTextColor(View view, String showMessage, int textColor, int durationSnackbar) {
        if (null == snackbarTextColor) {
            snackbarTextColor = Snackbar.make(view, showMessage, durationSnackbar);
            View vSnackbar = snackbarTextColor.getView();
            TextView tvSnackbarText = (TextView) vSnackbar.findViewById(android.support.design.R.id.snackbar_text);
            tvSnackbarText.setTextColor(textColor);
        }
        snackbarTextColor.show();
    }

    public void snackbarTextColorBackground(View view, String showMessage, ColorStateList colorStateList, int bacakgroundColor, int durationSnackbar) {
        if (null == snackbarTextColorBackground) {
            snackbarTextColorBackground = Snackbar.make(view, showMessage, durationSnackbar);
            View vSnackbar = snackbarTextColorBackground.getView();
            TextView tvSnackbarText = (TextView) vSnackbar.findViewById(android.support.design.R.id.snackbar_text);
            tvSnackbarText.setTextColor(colorStateList);
            vSnackbar.setBackgroundColor(bacakgroundColor);
        }
        snackbarTextColorBackground.show();
    }

    /**
     * 圖檔轉Bas64
     *
     * @param pathName
     * @param quality
     * @return
     */
    public String ImageTransformBase64Text(String pathName, int quality) {
        Bitmap bitmap = BitmapFactory.decodeFile(pathName);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);
        byte[] imageBytes = baos.toByteArray();
        String base64Image = Base64.encodeToString(imageBytes, Base64.NO_WRAP);

        return base64Image;
    }

    /**
     * Bas64轉圖檔
     */
    public Bitmap Base64TextTransformImage(String base64Text) {
        byte[] bytes = Base64.decode(base64Text, Base64.NO_WRAP);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    /**
     * 取得這隻手機已經安裝的檔案名稱
     *
     * @param context
     * @return
     */
    public List<String> getPageNameList(Context context) {
        packageManager = context.getPackageManager();
        lstPackageManager = packageManager.getInstalledPackages(0);

        if (lstPackageManager != null) {
            int i = 0;
            for (PackageInfo packageInfo : lstPackageManager) {
                lstPackageName.add(packageInfo.packageName);
                i++;
            }
            context = null;
            return lstPackageName;
        } else {
            lstPackageName.add("");
            context = null;
            return lstPackageName;
        }
    }

    /**
     * TextView Icon上色
     *
     * @param context
     * @param textView
     * @param color
     */
    public void setTextViewDrawableColor(Context context, TextView textView, int color) {
        for (Drawable drawable : textView.getCompoundDrawables()) {
            if (drawable != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    drawable.setColorFilter(new PorterDuffColorFilter(context.getColor(color), PorterDuff.Mode.SRC_IN));
                }
            }
        }

        context = null;
    }

    /**
     * 千分位樣式
     *
     * @param number
     * @return
     */
    public String setThousandBitStyle(String number) {
        try {
            DecimalFormat decimalFormat = new DecimalFormat("###,###,###,###");
            double dblNumber = Double.parseDouble(number);
            String strNumber = decimalFormat.format(dblNumber);
            return strNumber;
        } catch (Exception e) {
            return "0";
        }
    }

    /**
     * X 面骰模擬器
     *
     * @return
     */
    public String DiceRandomSimulator(int diceCount) {
        String strRandom = "";
        Random random = new Random();
        strRandom = String.valueOf(random.nextInt(diceCount) + 1);
        return strRandom;
    }

    /**
     * 簡單震動，會給一個0.1秒，強度50的震動
     *
     * @param context
     */
    public void setVibrator(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("Vibrator", MODE_PRIVATE);
        boolean strVibratorSelect = sharedPreferences.getBoolean("Vibrator", false);
        vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);

        if (vibrator.hasVibrator()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createOneShot(100, 50));
            } else {
                vibrator.vibrate(100);
            }
        }
        context = null;
    }

    /**
     * 自定震動
     *
     * @param context
     * @param VibratorTimer     設定毫秒數
     * @param VibratorAmplitude 設定震動強度0~255
     */
    public void setVibrator(Context context, int VibratorTimer, int VibratorAmplitude) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("Vibrator", MODE_PRIVATE);
        boolean strVibratorSelect = sharedPreferences.getBoolean("Vibrator", false);
        vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);

        if (vibrator.hasVibrator()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createOneShot(VibratorTimer, VibratorAmplitude));
            } else {
                vibrator.vibrate(VibratorTimer);
            }
        }
        context = null;
    }
}
