package alan.mvptoolssample.mvp.presenter;

import android.Manifest;
import android.app.Application;
import android.content.DialogInterface;
import android.support.v4.view.ViewPager;

import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.utils.DialogUtils;
import com.jess.arms.utils.PermissionUtil;
import com.jess.arms.widget.dialog.SweetAlertDialog;

import java.util.ArrayList;
import java.util.Random;

import alan.mvptoolssample.mvp.ui.adapter.MyPagerAdapter;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import javax.inject.Inject;

import alan.mvptoolssample.mvp.contract.MainContract;


/**
 * ================================================================
 * 创建时间：2017-12-12 17:01:12
 * 创建人：赵文贇
 * 文件描述：
 * 看淡身边的虚伪，静心宁神做好自己。路那么长，无愧走好每一步。
 * ================================================================
 */
@ActivityScope
public class MainPresenter extends BasePresenter<MainContract.Model, MainContract.View> implements DialogInterface.OnCancelListener {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;
    private MyPagerAdapter mPagerAdapter;

    @Inject
    public MainPresenter(MainContract.Model model, MainContract.View rootView
            , RxErrorHandler handler, Application application
            , ImageLoader imageLoader, AppManager appManager) {
        super(model, rootView);
        this.mErrorHandler = handler;
        this.mApplication = application;
        this.mImageLoader = imageLoader;
        this.mAppManager = appManager;
    }


    public void initData() {
        mPagerAdapter = new MyPagerAdapter(mRootView.getFragmentManager_(), mModel.getFragments(), mModel.getTitles());
        mRootView.setAdapter(mPagerAdapter);
        tl_2();
        getPermission();
//        mRootView.showDialog("提示", "初始化完成", SweetAlertDialog.SUCCESS_TYPE);
    }

    public DialogInterface.OnCancelListener getCancleListener() {
        return this;
    }


    @Override
    public void onCancel(DialogInterface mDialogInterface) {
        unDispose();
        mRootView.showMessage("操作被取消");
    }


    private void tl_2() {
        mRootView.getTabLayout().setTabData(mModel.getTibles());
        mRootView.getTabLayout().setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mRootView.getViewPager().setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
                if (position == 0) {
                    mRootView.getTabLayout().showMsg(0, new Random().nextInt(100) + 1);
//                    UnreadMsgUtils.show(mTabLayout_2.getMsgView(0), mRandom.nextInt(100) + 1);
                }
            }
        });

        mRootView.getViewPager().addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mRootView.getTabLayout().setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mRootView.getViewPager().setCurrentItem(0);
    }

    private void getPermission() {
        PermissionUtil.requestPermission(new PermissionUtil.RequestPermission() {
            @Override
            public void onRequestPermissionSuccess() {
                mRootView.showDialog("提示", "权限获取成功", SweetAlertDialog.SUCCESS_TYPE);
            }

            @Override
            public void onRequestPermissionFailure() {
                mRootView.showDialog("提示", "权限获取失败", SweetAlertDialog.ERROR_TYPE);
            }
        }, mRootView.getRxPermission(), mErrorHandler, Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
        this.mPagerAdapter = null;
    }

}
