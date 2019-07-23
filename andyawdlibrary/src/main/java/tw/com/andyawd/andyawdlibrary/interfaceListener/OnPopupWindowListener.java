package tw.com.andyawd.andyawdlibrary.interfaceListener;

import android.view.MotionEvent;
import android.view.View;

public interface OnPopupWindowListener {
    void OnDismiss();

    void OnBackgroundTouch(View view, MotionEvent motionEvent);
}
