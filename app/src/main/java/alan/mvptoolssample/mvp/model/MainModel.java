package alan.mvptoolssample.mvp.model;

import android.app.Application;
import android.support.v4.app.Fragment;

import com.flyco.tablayout.listener.CustomTabEntity;
import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import alan.mvptoolssample.R;
import alan.mvptoolssample.mvp.contract.MainContract;
import alan.mvptoolssample.mvp.model.en.en.TabEntity;
import alan.mvptoolssample.mvp.ui.fragment.F_1Fragment;
import alan.mvptoolssample.mvp.ui.fragment.F_2Fragment;
import alan.mvptoolssample.mvp.ui.fragment.F_3Fragment;
import alan.mvptoolssample.mvp.ui.fragment.F_4Fragment;

/**
 * ================================================================
 * 创建时间：2017-12-12 17:01:12
 * 创建人：赵文贇
 * 文件描述：
 * 看淡身边的虚伪，静心宁神做好自己。路那么长，无愧走好每一步。
 * ================================================================
 */
@ActivityScope
public class MainModel extends BaseModel implements MainContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public MainModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
        super(repositoryManager);
        this.mGson = gson;
        this.mApplication = application;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public List<Fragment> getFragments() {
        List<Fragment> mFragmentList = new ArrayList<>();
        mFragmentList.add(F_1Fragment.newInstance());
        mFragmentList.add(F_2Fragment.newInstance());
        mFragmentList.add(F_3Fragment.newInstance());
        mFragmentList.add(F_4Fragment.newInstance());
        return mFragmentList;
    }

    @Override
    public String[] getTitles() {
        return new String[]{"首页", "消息", "联系人", "更多"};
    }

    @Override
    public ArrayList<CustomTabEntity> getTibles() {
        ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
        mTabEntities.add(new TabEntity("首页", R.mipmap.tab_home_select, R.mipmap.tab_home_unselect));
        mTabEntities.add(new TabEntity("消息", R.mipmap.tab_speech_select,R.mipmap.tab_speech_unselect));
        mTabEntities.add(new TabEntity("联系人", R.mipmap.tab_contact_select,R.mipmap.tab_contact_unselect));
        mTabEntities.add(new TabEntity("更多", R.mipmap.tab_more_select,R.mipmap.tab_more_unselect));
        return mTabEntities;
    }
}