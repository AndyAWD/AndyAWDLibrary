package tw.com.andyawd.andytool.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import android.Manifest;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.jakewharton.rxbinding3.view.RxView;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import kotlin.Unit;
import pub.devrel.easypermissions.EasyPermissions;
import tw.com.andyawd.andyawdlibrary.AWDConstants;
import tw.com.andyawd.andyawdlibrary.AWDPermissionsFailAlertDialog;
import tw.com.andyawd.andytool.R;

public class PermissionsTransformerActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks{

    private AppCompatButton btApt_PermissionsStart;
    private AppCompatTextView tvApt_PermissionsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permissions_transformer);
        Objects.requireNonNull(getSupportActionBar()).hide();

        initComponent();
    }

    private void initComponent() {
        btApt_PermissionsStart = findViewById(R.id.btApt_PermissionsStart);
        tvApt_PermissionsText = findViewById(R.id.tvApt_PermissionsText);

        RxView.clicks(btApt_PermissionsStart).throttleFirst(AWDConstants.ONE_INT, TimeUnit.SECONDS).subscribe(btApt_PermissionsStart_Click);
    }

    private void permissionsStart() {
        String[] perms = {
                Manifest.permission.READ_CALENDAR,
                Manifest.permission.WRITE_CALENDAR,
                Manifest.permission.CAMERA,
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.WRITE_CONTACTS,
                Manifest.permission.GET_ACCOUNTS,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.CALL_PHONE,
                Manifest.permission.READ_CALL_LOG,
                Manifest.permission.WRITE_CALL_LOG,
                Manifest.permission.ADD_VOICEMAIL,
                Manifest.permission.USE_SIP,
                Manifest.permission.PROCESS_OUTGOING_CALLS,
                Manifest.permission.BODY_SENSORS,
                Manifest.permission.RECEIVE_SMS,
                Manifest.permission.READ_SMS,
                Manifest.permission.RECEIVE_WAP_PUSH,
                Manifest.permission.RECEIVE_MMS,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
        };

        if (!EasyPermissions.hasPermissions(PermissionsTransformerActivity.this, perms)) {
            EasyPermissions.requestPermissions(this, getResources().getString(R.string.pleaseOpenPermissions), AWDConstants.PERMISSIONS_CAMERA, perms);
            return;
        }

        tvApt_PermissionsText.setText("權限通過");
    }

    private Observer<? super Unit> btApt_PermissionsStart_Click = new Observer<Unit>() {

        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(Unit unit) {
            permissionsStart();
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        switch (requestCode) {
            case AWDConstants.PERMISSIONS_CAMERA:
                permissionsStart();
                break;
            default:
                break;
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(PermissionsTransformerActivity.this, perms)) {
            new AWDPermissionsFailAlertDialog(PermissionsTransformerActivity.this, perms);
        }
    }
}
