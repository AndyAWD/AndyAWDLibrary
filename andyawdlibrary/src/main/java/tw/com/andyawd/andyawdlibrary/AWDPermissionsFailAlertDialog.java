package tw.com.andyawd.andyawdlibrary;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

import androidx.appcompat.app.AlertDialog;

import java.util.HashSet;
import java.util.List;

/**
 * Created by andydai on 2018/4/2.，
 */

public class AWDPermissionsFailAlertDialog {

    public AWDPermissionsFailAlertDialog(final Context context, List<String> permsList){

        HashSet<String> permsHashSet = new HashSet<>();
        StringBuilder stringBuilder = new StringBuilder();

        for (String perm : permsList) {
            permsHashSet.add(AWDPermissionsInfoTransformerTextMgr.getInstance().getTransformerInfo(perm));
        }

        for (String s : permsHashSet) {
            stringBuilder.append(s);
            stringBuilder.append("\n");
        }

        Dialog dialog = new AlertDialog.Builder(context)
                .setTitle("權限不足")
                .setCancelable(false)
                .setMessage("請先開啟相關權限再使用此功能\n" + stringBuilder)
                .setPositiveButton("確認", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", context.getPackageName(), null);
                        intent.setData(uri);

                        context.startActivity(intent);
                    }
                })
                .show();
    }
}
