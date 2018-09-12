package tw.com.andyawd.andyawdlibrary;

import android.app.Application;
import android.content.Context;

/**
 * Created by andydai on 2018/9/12.
 */

public class AWDApplication extends Application {

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
