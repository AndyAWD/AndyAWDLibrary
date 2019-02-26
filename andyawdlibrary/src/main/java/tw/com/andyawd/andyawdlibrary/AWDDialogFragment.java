package tw.com.andyawd.andyawdlibrary;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * 全域單例DialogFragment
 */

public class AWDDialogFragment extends DialogFragment {

    private static boolean isDialogFragmentShow = false;
    private static boolean isDialogFragmentOnDetach = true;

    /**
     * 靜態內部單例
     */
    private static class AWDDialogFragmentHolder {
        private static final AWDDialogFragment awdDialogFragment = new AWDDialogFragment();
    }

    /**
     * 帶Bundle的啟動
     *
     * @param bundle
     * @return
     */
    public static AWDDialogFragment getInstance(Bundle bundle) {
        AWDDialogFragmentHolder.awdDialogFragment.setArguments(bundle);
        return AWDDialogFragmentHolder.awdDialogFragment;
    }

    public static AWDDialogFragment getInstance() {
        return AWDDialogFragmentHolder.awdDialogFragment;
    }

    public boolean isDialogFragmentShow() {
        return isDialogFragmentShow;
    }

    public boolean isDialogFragmentOnDetach() {
        return isDialogFragmentOnDetach;
    }

    private void switchControll(boolean open) {
        if (open) {
            isDialogFragmentOnDetach = true;
            isDialogFragmentShow = false;
        } else {
            isDialogFragmentOnDetach = false;
            isDialogFragmentShow = true;
        }
    }

    @Override
    public void onDetach() {
        switchControll(true);
        super.onDetach();
    }

    @Override
    public void show(@NonNull FragmentManager manager, @Nullable String tag) {
        if (isDialogFragmentShow()) {
            return;
        }
        switchControll(false);
        super.show(manager, tag);
    }

    @Override
    public void dismiss() {
        try {
            super.dismiss();
        } catch (Exception e) {

        }
    }
}
