package tw.com.andyawd.andytool.base;

import android.app.Application;
import android.content.Context;

/**
 * Created by andydai on 2018/10/22.
 */

public class AWDPApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();

    }

    public static Context getContext() {
        return context;
    }
}
