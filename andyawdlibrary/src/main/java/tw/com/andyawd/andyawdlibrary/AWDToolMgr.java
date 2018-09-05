package tw.com.andyawd.andyawdlibrary;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Vibrator;
import android.support.design.widget.Snackbar;
import android.util.Base64;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by andydai on 2018/1/16.
 */

public class AWDToolMgr {

    private static Toast toast;
    private static Snackbar snackbar;

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
     * 吐司訊息2
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
            View vSnackbar = snackbar.getView();
            TextView tvSnackbarText = (TextView) vSnackbar.findViewById(android.support.design.R.id.snackbar_text);
        } else {

        }
        snackbar.show();
    }

    public void Snackbar(View view, String showMessage, int textColor, int durationSnackbar) {
        if (null == snackbar) {
            snackbar = Snackbar.make(view, showMessage, durationSnackbar);
            View vSnackbar = snackbar.getView();
            TextView tvSnackbarText = (TextView) vSnackbar.findViewById(android.support.design.R.id.snackbar_text);
            tvSnackbarText.setTextColor(textColor);
        } else {

        }
        snackbar.show();
    }

    public void Snackbar(View view, String showMessage, int textColor, int bacakgroundColor, int durationSnackbar) {
        if (null == snackbar) {
            snackbar = Snackbar.make(view, showMessage, durationSnackbar);
            View vSnackbar = snackbar.getView();
            TextView tvSnackbarText = (TextView) vSnackbar.findViewById(android.support.design.R.id.snackbar_text);
            tvSnackbarText.setTextColor(textColor);
            vSnackbar.setBackgroundColor(bacakgroundColor);
        } else {

        }
        snackbar.show();
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
        String strBase64Image = Base64.encodeToString(imageBytes, Base64.NO_WRAP);

        return strBase64Image;
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
}
