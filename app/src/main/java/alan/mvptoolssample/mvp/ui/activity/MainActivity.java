package alan.mvptoolssample.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import com.flyco.tablayout.CommonTabLayout;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.DialogUtils;

import alan.mvptoolssample.R;
import alan.mvptoolssample.di.component.DaggerMainComponent;
import alan.mvptoolssample.di.module.MainModule;
import alan.mvptoolssample.mvp.contract.MainContract;
import alan.mvptoolssample.mvp.presenter.MainPresenter;
import alan.mvptoolssample.mvp.ui.adapter.MyPagerAdapter;
import butterknife.BindView;

import static com.jess.arms.utils.Preconditions.checkNotNull;

/**
 * ================================================================
 * 创建时间：2017-12-12 17:01:12
 * 创建人：赵文贇
 * 文件描述：
 * 看淡身边的虚伪，静心宁神做好自己。路那么长，无愧走好每一步。
 * ================================================================
 */
public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {


    @BindView(R.id.vp_main)
    ViewPager mVpMain;
    @BindView(R.id.ctl)
    CommonTabLayout mCtl;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .mainModule(new MainModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.activity_main; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mPresenter.initData();
    }


    @Override
    public void showLoading() {
        loadingDialog = DialogUtils.getInstance().getLoadingDialog(this, mPresenter.getCancleListener());
    }

    @Override
    public void hideLoading() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }


    @Override
    public FragmentManager getFragmentManager_() {
        return getSupportFragmentManager();
    }

    @Override
    public void setAdapter(MyPagerAdapter mPagerAdapter) {
        mVpMain.setAdapter(mPagerAdapter);
    }

    @Override
    public CommonTabLayout getTabLayout() {
        return mCtl;
    }

    @Override
    public ViewPager getViewPager() {
        return mVpMain;
    }
}
