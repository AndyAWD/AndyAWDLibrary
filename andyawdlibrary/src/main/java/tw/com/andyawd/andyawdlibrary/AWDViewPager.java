package tw.com.andyawd.andyawdlibrary;

import android.content.Context;
import android.widget.RelativeLayout;

/**
 * Created by andydai on 2018/3/16.
 * 都9102年了還有人用ViewPager
 */

@Deprecated
public abstract class AWDViewPager extends RelativeLayout {

    public AWDViewPager(Context context) {
        super(context);
    }
    public abstract void refreshView(String refresh);

}
