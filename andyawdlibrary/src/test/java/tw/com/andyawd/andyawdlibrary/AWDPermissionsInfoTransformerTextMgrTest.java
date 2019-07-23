package tw.com.andyawd.andyawdlibrary;

import org.junit.Test;

import static org.junit.Assert.*;

public class AWDPermissionsInfoTransformerTextMgrTest {

    @Test
    public void transformerInfo() {

        assertEquals("日曆", AWDPermissionsInfoTransformerTextMgr.getInstance().getTransformerInfo("android.permission.READ_CALENDAR"));
        assertEquals("日曆", AWDPermissionsInfoTransformerTextMgr.getInstance().getTransformerInfo("android.permission.WRITE_CALENDAR"));

        assertEquals("相機", AWDPermissionsInfoTransformerTextMgr.getInstance().getTransformerInfo("android.permission.CAMERA"));

        assertEquals("聯絡人", AWDPermissionsInfoTransformerTextMgr.getInstance().getTransformerInfo("android.permission.READ_CONTACTS"));
        assertEquals("聯絡人", AWDPermissionsInfoTransformerTextMgr.getInstance().getTransformerInfo("android.permission.WRITE_CONTACTS"));
        assertEquals("聯絡人", AWDPermissionsInfoTransformerTextMgr.getInstance().getTransformerInfo("android.permission.GET_ACCOUNTS"));

        assertEquals("位置", AWDPermissionsInfoTransformerTextMgr.getInstance().getTransformerInfo("android.permission.ACCESS_FINE_LOCATION"));
        assertEquals("位置", AWDPermissionsInfoTransformerTextMgr.getInstance().getTransformerInfo("android.permission.ACCESS_COARSE_LOCATION"));

        assertEquals("麥克風", AWDPermissionsInfoTransformerTextMgr.getInstance().getTransformerInfo("android.permission.RECORD_AUDIO"));

        assertEquals("電話", AWDPermissionsInfoTransformerTextMgr.getInstance().getTransformerInfo("android.permission.READ_PHONE_STATE"));
        assertEquals("電話", AWDPermissionsInfoTransformerTextMgr.getInstance().getTransformerInfo("android.permission.CALL_PHONE"));
        assertEquals("電話", AWDPermissionsInfoTransformerTextMgr.getInstance().getTransformerInfo("android.permission.READ_CALL_LOG"));
        assertEquals("電話", AWDPermissionsInfoTransformerTextMgr.getInstance().getTransformerInfo("android.permission.WRITE_CALL_LOG"));
        assertEquals("電話", AWDPermissionsInfoTransformerTextMgr.getInstance().getTransformerInfo("android.permission.ADD_VOICEMAIL"));
        assertEquals("電話", AWDPermissionsInfoTransformerTextMgr.getInstance().getTransformerInfo("android.permission.USE_SIP"));
        assertEquals("電話", AWDPermissionsInfoTransformerTextMgr.getInstance().getTransformerInfo("android.permission.PROCESS_OUTGOING_CALLS"));

        assertEquals("人體感應器", AWDPermissionsInfoTransformerTextMgr.getInstance().getTransformerInfo("android.permission.BODY_SENSORS"));
        assertEquals("人體感應器", AWDPermissionsInfoTransformerTextMgr.getInstance().getTransformerInfo("com.android.voicemail.permission.ADD_VOICEMAIL"));

        assertEquals("簡訊", AWDPermissionsInfoTransformerTextMgr.getInstance().getTransformerInfo("android.permission.RECEIVE_SMS"));
        assertEquals("簡訊", AWDPermissionsInfoTransformerTextMgr.getInstance().getTransformerInfo("android.permission.READ_SMS"));
        assertEquals("簡訊", AWDPermissionsInfoTransformerTextMgr.getInstance().getTransformerInfo("android.permission.RECEIVE_WAP_PUSH"));
        assertEquals("簡訊", AWDPermissionsInfoTransformerTextMgr.getInstance().getTransformerInfo("android.permission.RECEIVE_MMS"));

        assertEquals("儲存", AWDPermissionsInfoTransformerTextMgr.getInstance().getTransformerInfo("android.permission.READ_EXTERNAL_STORAGE"));
        assertEquals("儲存", AWDPermissionsInfoTransformerTextMgr.getInstance().getTransformerInfo("android.permission.WRITE_EXTERNAL_STORAGE"));

        assertEquals("未知的權限", AWDPermissionsInfoTransformerTextMgr.getInstance().getTransformerInfo("123456"));
    }
}