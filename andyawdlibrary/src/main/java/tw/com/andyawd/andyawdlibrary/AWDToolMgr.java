package tw.com.andyawd.andyawdlibrary;

import android.annotation.SuppressLint;
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
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Base64;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
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
    @SuppressLint("MissingPermission")
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
    @SuppressLint("MissingPermission")
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

    /**
     * 手機有沒有使用代理伺服器
     * 有使用 true
     * 沒有使用 false
     * @return
     */
    public boolean isPhoneSetProxy() {
        if (TextUtils.isEmpty(System.getProperty("http.proxyHost"))) {
            if (TextUtils.isEmpty(System.getProperty("http.proxyPort"))) {
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }
}
