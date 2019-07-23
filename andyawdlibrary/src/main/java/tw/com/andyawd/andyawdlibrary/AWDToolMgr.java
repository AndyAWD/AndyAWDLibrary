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

import com.google.android.material.snackbar.Snackbar;

import androidx.core.content.ContextCompat;

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

    public static AWDToolMgr getInstance() {
        return AWDToolMgrHolder.awdToolMgr;
    }

    private static class AWDToolMgrHolder {
        private static final AWDToolMgr awdToolMgr = new AWDToolMgr();
    }

    /**
     * 圖檔轉Bas64
     */
    public String imageTransformBase64(String pathName, int quality) {
        Bitmap bitmap = BitmapFactory.decodeFile(pathName);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, byteArrayOutputStream);

        return Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.NO_WRAP);
    }

    /**
     * Bas64轉圖檔
     */
    public Bitmap base64TransformImage(String base64Text) {
        byte[] bytes = Base64.decode(base64Text, Base64.NO_WRAP);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    /**
     * 取得這隻手機已經安裝的檔案名稱
     */
    public List<String> getPageNameList(Context context) {
        List<PackageInfo> packageManagerList;
        List<String> packageNameString = new ArrayList<>();

        packageManagerList = context.getPackageManager().getInstalledPackages(0);

        if (null == packageManagerList){
            packageNameString.add(AWDConstants.EMPTY_STRING);
            return packageNameString;
        }

        for (PackageInfo info : packageManagerList) {
            packageNameString.add(info.packageName);
        }

        context = null;
        return packageNameString;
    }

    /**
     * TextView Icon上色
     */
    public void setTextViewDrawableColor(Context context, TextView textView, int color) {
        for (Drawable drawable : textView.getCompoundDrawables()) {
            if (drawable == null) {
                return;
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                drawable.setColorFilter(new PorterDuffColorFilter(context.getColor(color), PorterDuff.Mode.SRC_IN));
            }
        }

        context = null;
    }

    /**
     * X 面骰模擬器
     */
    public String getDiceRandomSimulator(int diceCount) {
        Random random = new Random();
        return String.valueOf(random.nextInt(diceCount) + 1);
    }

    /**
     * 簡單震動，會給一個0.1秒，強度50的震動
     */
    @SuppressLint("MissingPermission")
    public void setVibrator(Context context) {
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);

        if (!vibrator.hasVibrator()) {
            context = null;
            return;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(100, 50));
        } else {
            vibrator.vibrate(100);
        }

        context = null;
    }

    /**
     * 自定震動
     */
    @SuppressLint("MissingPermission")
    public void setVibrator(Context context, int vibratorTimer, int vibratorAmplitude) {
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);

        if (!vibrator.hasVibrator()) {
            context = null;
            return;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            if (vibratorAmplitude > 255) {
                vibratorAmplitude = 255;
            }

            vibrator.vibrate(VibrationEffect.createOneShot(vibratorTimer, vibratorAmplitude));
        } else {
            vibrator.vibrate(vibratorTimer);
        }

        context = null;
    }

    /**
     * 手機有沒有使用代理伺服器
     */
    public boolean isPhoneSetProxy() {
        if (TextUtils.isEmpty(System.getProperty(AWDConstants.HTTP_PROXY_HOST)) ||
                TextUtils.isEmpty(System.getProperty(AWDConstants.HTTP_PROXY_PORT))) {
            return false;
        } else {
            return true;
        }
    }
}
