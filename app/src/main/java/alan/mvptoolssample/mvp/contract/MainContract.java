package alan.mvptoolssample.mvp.contract;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.jess.arms.mvp.IView;
import com.jess.arms.mvp.IModel;

import java.util.ArrayList;
import java.util.List;

import alan.mvptoolssample.mvp.ui.adapter.MyPagerAdapter;

/**
 * ================================================================
 * 创建时间：2017-12-12 17:01:12
 * 创建人：赵文贇
 * 文件描述：
 * 看淡身边的虚伪，静心宁神做好自己。路那么长，无愧走好每一步。
 * ================================================================
 */
public interface MainContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {

        FragmentManager getFragmentManager_();

        void setAdapter(MyPagerAdapter mPagerAdapter);

        CommonTabLayout getTabLayout();

        ViewPager getViewPager();
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {

        List<Fragment> getFragments();

        String[] getTitles();

        ArrayList<CustomTabEntity>  getTibles();
    }
}
