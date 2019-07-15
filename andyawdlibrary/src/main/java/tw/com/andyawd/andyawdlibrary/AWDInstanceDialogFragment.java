package tw.com.andyawd.andyawdlibrary;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import java.util.logging.Logger;

/**
 * 全域單例DialogFragment
 */

public class AWDInstanceDialogFragment extends DialogFragment {

    /**
     * DialogFragment一開始一定是關的，所以初始值為false
     */
    private static boolean isDialogFragmentShow = false;

    /**
     * 靜態內部單例
     */
    private static class AWDInstanceDialogFragmentHolder {
        private static final AWDInstanceDialogFragment AWD_INSTANCE_DIALOG_FRAGMENT = new AWDInstanceDialogFragment();
    }

    /**
     * 帶Bundle的啟動
     */
    public static AWDInstanceDialogFragment getInstance(Bundle bundle) {
        AWDInstanceDialogFragmentHolder.AWD_INSTANCE_DIALOG_FRAGMENT.setArguments(bundle);
        return AWDInstanceDialogFragmentHolder.AWD_INSTANCE_DIALOG_FRAGMENT;
    }

    /**
     * 不帶Bundle的啟動
     */
    public static AWDInstanceDialogFragment getInstance() {
        return AWDInstanceDialogFragmentHolder.AWD_INSTANCE_DIALOG_FRAGMENT;
    }

    /**
     * 從生命周期去判斷是否啟動
     */
    private static void setIsDialogFragmentShow(boolean isDialogFragmentShow) {
        AWDInstanceDialogFragment.isDialogFragmentShow = isDialogFragmentShow;
    }

    @Override
    public void onDetach() {
        setIsDialogFragmentShow(false);
        super.onDetach();
    }

    @Override
    public void show(@NonNull FragmentManager manager, @Nullable String tag) {
        if (isDialogFragmentShow) {
            return;
        }

        setIsDialogFragmentShow(true);

        try {
            super.show(manager, tag);
        } catch (Exception e) {
            Logger.getLogger(e.getMessage());
        }
    }

    @Override
    public void dismiss() {
        try {
            super.dismiss();
        } catch (Exception e) {
            Logger.getLogger(e.getMessage());
        }
    }
}
