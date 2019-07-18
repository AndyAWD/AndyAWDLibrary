package tw.com.andyawd.andyawdlibrary;

import androidx.annotation.NonNull;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

/**
 * Created by andydai on 2018/7/27.
 *
 * 如何新增頁面
 * private AWDViewPagerActivityAdapter awdViewPagerActivityAdapter;
 * private ArrayList<CPObjectData.AWDObjectData> lstViewPager = new ArrayList<>();
 * private LocalActivityManager localActivityManager;
 *
 * localActivityManager = new LocalActivityManager(.this, false);
 * localActivityManager.dispatchCreate(savedInstanceState);*
 *
 * Intent iViewPagerOne = new Intent(.this, .class);
 * View vViewPagerOne = localActivityManager.startActivity("ViewPagerOneKey", iViewPagerOne).getDecorView();
 * lstViewPager.add(new CPObjectData.HomepageList(vViewPagerOne, (CPAppCompatActivity) localActivityManager.getActivity("ViewPagerOneKey")));
 *
 * awdViewPagerActivityAdapter = new AWDViewPagerActivityAdapter();
 * awdViewPagerActivityAdapter.copy(lstViewPager);
 *
 * 都9102年了還有人用ViewPager
 */

@Deprecated
public class AWDViewPagerActivityAdapter extends PagerAdapter {

    private ArrayList<AWDObjectData.ViewPagerActivity> mViewPagerActivity = new ArrayList<>();

    @Override
    public int getCount() {
        return mViewPagerActivity != null ? mViewPagerActivity.size() : 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return object == view;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        container.addView(mViewPagerActivity.get(position).getView());
        return mViewPagerActivity.get(position).getView();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    /**
     * 新增一筆頁面
     */
    public void add(AWDObjectData.ViewPagerActivity pagesViewActivity) {
        mViewPagerActivity.add(pagesViewActivity);
        notifyDataSetChanged();
    }

    /**
     * 刪除全部頁面
     */
    public void removeAll() {
        mViewPagerActivity = new ArrayList<>();
        notifyDataSetChanged();
    }

    /**
     * 抓目前頁面數量
     * @return
     */
    public int getViewPagerActivitySize() {
        if (null != mViewPagerActivity) {
            return mViewPagerActivity.size();
        } else {
            return 0;
        }
    }
}
