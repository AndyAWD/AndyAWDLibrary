package tw.com.andyawd.andyawdlibrary;

import android.util.Log;

/**
 * Created by andydai on 2018/4/2.
 */

public class AWDPermissionsInfoTransformerTextMgr {

    public static AWDPermissionsInfoTransformerTextMgr getInstance() {
        return AWDPermissionsInfoTransformerTextMgrHolder.awdPermissionsInfoTransformerTextMgr;
    }

    private static class AWDPermissionsInfoTransformerTextMgrHolder {
        private static final AWDPermissionsInfoTransformerTextMgr awdPermissionsInfoTransformerTextMgr = new AWDPermissionsInfoTransformerTextMgr();
    }

    public String getTransformerInfo(String perms) {
        switch (perms) {
            case "android.permission.READ_CALENDAR":
            case "android.permission.WRITE_CALENDAR":
                return "日曆";
            case "android.permission.CAMERA":
                return "相機";
            case "android.permission.READ_CONTACTS":
            case "android.permission.WRITE_CONTACTS":
            case "android.permission.GET_ACCOUNTS":
                return "聯絡人";
            case "android.permission.ACCESS_FINE_LOCATION":
            case "android.permission.ACCESS_COARSE_LOCATION":
                return "位置";
            case "android.permission.RECORD_AUDIO":
                return "麥克風";
            case "android.permission.READ_PHONE_STATE":
            case "android.permission.CALL_PHONE":
            case "android.permission.READ_CALL_LOG":
            case "android.permission.WRITE_CALL_LOG":
            case "com.android.voicemail.permission.ADD_VOICEMAIL":
            case "android.permission.ADD_VOICEMAIL":
            case "android.permission.USE_SIP":
            case "android.permission.PROCESS_OUTGOING_CALLS":
                return "電話";
            case "android.permission.BODY_SENSORS":
                return "人體感應器";
            case "android.permission.RECEIVE_SMS":
            case "android.permission.READ_SMS":
            case "android.permission.RECEIVE_WAP_PUSH":
            case "android.permission.RECEIVE_MMS":
                return "簡訊";
            case "android.permission.READ_EXTERNAL_STORAGE":
            case "android.permission.WRITE_EXTERNAL_STORAGE":
                return "儲存";
            default:
                return "未知的權限";
        }
    }
}
