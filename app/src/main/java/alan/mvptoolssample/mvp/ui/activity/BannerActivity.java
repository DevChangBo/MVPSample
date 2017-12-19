package alan.mvptoolssample.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.DialogUtils;

import java.util.List;

import alan.mvptoolssample.R;
import alan.mvptoolssample.di.component.DaggerBannerComponent;
import alan.mvptoolssample.di.module.BannerModule;
import alan.mvptoolssample.mvp.contract.BannerContract;
import alan.mvptoolssample.mvp.presenter.BannerPresenter;
import butterknife.BindView;
import cn.bingoogolapple.bgabanner.BGABanner;

import static com.jess.arms.utils.Preconditions.checkNotNull;

/**
 * ================================================================
 * 创建时间：2017-12-19 16:02:16
 * 创建人：赵文贇
 * 文件描述：轮播图演示
 * 看淡身边的虚伪，静心宁神做好自己。路那么长，无愧走好每一步。
 * ================================================================
 */
public class BannerActivity extends BaseActivity<BannerPresenter> implements BannerContract.View {


    @BindView(R.id.banner_guide_background)
    BGABanner mBannerGuideBackground;
    @BindView(R.id.banner_guide_foreground)
    BGABanner mBannerGuideForeground;
    @BindView(R.id.tv_guide_skip)
    TextView mTvGuideSkip;
    @BindView(R.id.btn_guide_enter)
    Button mBtnGuideEnter;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerBannerComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .bannerModule(new BannerModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.activity_banner; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        initData();
        initListener();
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


    /**
     * 显示对话框
     *
     * @param title      标题
     * @param content    内容
     * @param dialogType 对话框类型 成功？失败 SweetAlertDialog.ERROR_TYPE...
     */
    @Override
    public void showDialog(String title, String content, int dialogType) {
        mDialog = DialogUtils.getInstance().getDialog(this, title, content, dialogType, false, sweetAlertDialog -> mDialog.dismiss());
        mDialog.show();
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


    private void initData() {
//开启自动播放
//        mBannerGuideBackground.setAutoPlayAble(true);
//        mBannerGuideForeground.setAutoPlayAble(true);

        //设置背景图
        mBannerGuideBackground.setData(R.mipmap.uoko_guide_background_1, R.mipmap.uoko_guide_background_2, R.mipmap.uoko_guide_background_3);
        //设置背景图对应的文案
        mBannerGuideForeground.setData(R.mipmap.uoko_guide_foreground_1, R.mipmap.uoko_guide_foreground_2, R.mipmap.uoko_guide_foreground_3);

    }

    private void initListener() {
        //将跳过按钮与进入按钮加入控制器设置点击事件
        mBannerGuideForeground.setEnterSkipViewIdAndDelegate(R.id.btn_guide_enter, R.id.tv_guide_skip, new BGABanner.GuideDelegate() {
            @Override
            public void onClickEnterOrSkip() {
                showMessage("跳过");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 如果开发者的引导页主题是透明的，需要在界面可见时给背景 Banner 设置一个白色背景，避免滑动过程中两个 Banner 都设置透明度后能看到 Launcher
        mBannerGuideBackground.setBackgroundResource(android.R.color.white);
    }
}
