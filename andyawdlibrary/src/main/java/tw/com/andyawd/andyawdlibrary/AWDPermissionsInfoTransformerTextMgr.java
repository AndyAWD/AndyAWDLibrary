package tw.com.andyawd.andyawdlibrary;

/**
 * Created by andydai on 2018/4/2.
 */

public class AWDPermissionsInfoTransformerTextMgr {

    private String text;

    public String TransformerInfo(String perms) {

        switch (perms) {
            case "android.permission.READ_CALENDAR":
            case "android.permission.WRITE_CALENDAR":
                text = "日曆";
                break;
            case "android.permission.CAMERA":
                text = "相機";
                break;
            case "android.permission.READ_CONTACTS":
            case "android.permission.WRITE_CONTACTS":
            case "android.permission.GET_ACCOUNTS":
                text = "聯絡人";
                break;
            case "android.permission.ACCESS_FINE_LOCATION":
            case "android.permission.ACCESS_COARSE_LOCATION":
                text = "位置";
                break;
            case "android.permission.RECORD_AUDIO":
                text = "麥克風";
                break;
            case "android.permission.READ_PHONE_STATE":
            case "android.permission.CALL_PHONE":
            case "android.permission.READ_CALL_LOG":
            case "android.permission.WRITE_CALL_LOG":
            case "android.permission.ADD_VOICEMAIL":
            case "android.permission.USE_SIP":
            case "android.permission.PROCESS_OUTGOING_CALLS":
                text = "電話";
                break;
            case "android.permission.BODY_SENSORS":
                text = "身體感應器";
                break;
            case "android.permission.RECEIVE_SMS":
            case "android.permission.READ_SMS":
            case "android.permission.RECEIVE_WAP_PUSH":
            case "android.permission.RECEIVE_MMS":
                text = "簡訊";
                break;
            case "android.permission.READ_EXTERNAL_STORAGE":
            case "android.permission.WRITE_EXTERNAL_STORAGE":
                text = "儲存";
                break;
            default:
                text = "未知的權限";
                break;
        }

        return text;
    }
}
