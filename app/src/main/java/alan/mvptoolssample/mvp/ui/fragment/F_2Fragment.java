package alan.mvptoolssample.mvp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.DialogUtils;

import alan.mvptoolssample.R;
import alan.mvptoolssample.di.component.DaggerF_2Component;
import alan.mvptoolssample.di.module.F_2Module;
import alan.mvptoolssample.mvp.contract.F_2Contract;
import alan.mvptoolssample.mvp.presenter.F_2Presenter;
import alan.mvptoolssample.mvp.ui.activity.BannerActivity;
import butterknife.OnClick;
import timber.log.Timber;

import static com.jess.arms.utils.Preconditions.checkNotNull;

/**
 * ================================================================
 * 创建时间：2017-12-12 17:27:04
 * 创建人：赵文贇
 * 文件描述：
 * 看淡身边的虚伪，静心宁神做好自己。路那么长，无愧走好每一步。
 * ================================================================
 */
public class F_2Fragment extends BaseFragment<F_2Presenter> implements F_2Contract.View {


    public static F_2Fragment newInstance() {
        F_2Fragment fragment = new F_2Fragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {
        DaggerF_2Component //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .f_2Module(new F_2Module(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_f_2, container, false);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        Timber.d("消息……");
    }

    /**
     * 此方法是让外部调用使fragment做一些操作的,比如说外部的activity想让fragment对象执行一些方法,
     * 建议在有多个需要让外界调用的方法时,统一传Message,通过what字段,来区分不同的方法,在setData
     * 方法中就可以switch做不同的操作,这样就可以用统一的入口方法做不同的事
     * <p>
     * 使用此方法时请注意调用时fragment的生命周期,如果调用此setData方法时onCreate还没执行
     * setData里却调用了presenter的方法时,是会报空的,因为dagger注入是在onCreated方法中执行的,然后才创建的presenter
     * 如果要做一些初始化操作,可以不必让外部调setData,在initData中初始化就可以了
     *
     * @param data
     */

    @Override
    public void setData(Object data) {

    }


    @Override
    public void showLoading() {
        loadingDialog = DialogUtils.getInstance().getLoadingDialog(getActivity(), mPresenter.getCancleListener());
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

    }

    @OnClick({R.id.bt_1, R.id.bt_2, R.id.bt_3, R.id.bt_4, R.id.bt_5, R.id.bt_6, R.id.bt_7, R.id.bt_8})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_1:
                Timber.d("轮播图演示");
                launchActivity(new Intent(getActivity(), BannerActivity.class));
                break;
            case R.id.bt_2:
                Timber.d("预留功能2");
                break;
            case R.id.bt_3:
                Timber.d("预留功能3");
                break;
            case R.id.bt_4:
                Timber.d("预留功能4");
                break;
            case R.id.bt_5:
                Timber.d("预留功能5");
                break;
            case R.id.bt_6:
                Timber.d("预留功能6");
                break;
            case R.id.bt_7:
                Timber.d("预留功能7");
                break;
            case R.id.bt_8:
                Timber.d("预留功能8");
                break;
        }
    }
}
