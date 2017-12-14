package alan.mvptoolssample.mvp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.DialogUtils;

import java.util.List;

import alan.mvptoolssample.R;
import alan.mvptoolssample.app.utils.DBUtils;
import alan.mvptoolssample.di.component.DaggerF_1Component;
import alan.mvptoolssample.di.module.F_1Module;
import alan.mvptoolssample.mvp.contract.F_1Contract;
import alan.mvptoolssample.mvp.model.dbbean.User;
import alan.mvptoolssample.mvp.presenter.F_1Presenter;
import butterknife.BindView;
import butterknife.OnClick;
import timber.log.Timber;

import static com.jess.arms.utils.Preconditions.checkNotNull;

/**
 * ================================================================
 * 创建时间：2017-12-12 17:26:07
 * 创建人：赵文贇
 * 文件描述：
 * 看淡身边的虚伪，静心宁神做好自己。路那么长，无愧走好每一步。
 * ================================================================
 */
public class F_1Fragment extends BaseFragment<F_1Presenter> implements F_1Contract.View {


    @BindView(R.id.et_1)
    EditText mEt1;
    @BindView(R.id.et_2)
    EditText mEt2;
    @BindView(R.id.bt)
    Button mBt;

    public static F_1Fragment newInstance() {
        F_1Fragment fragment = new F_1Fragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {
        DaggerF_1Component //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .f_1Module(new F_1Module(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_f_1, container, false);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        Timber.d("首页……");
        User mUser
                = new User();
        mUser.setUserName("首页");
        User mUser2
                = new User();
        mUser2.setUserName("首页2");
        DBUtils.getInstance(getContext()).insertUser(mUser);
        DBUtils.getInstance(getContext()).insertUser(mUser2);


        List<User> users = DBUtils.getInstance(getContext()).getUsers();
        for (int i = 0; i < users.size(); i++) {
            Timber.d("User:" + users.get(i).toString());
        }
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


    @OnClick(R.id.bt)
    public void onClick() {
        mPresenter.dologin();
    }

    @Override
    public String getUn() {
        return mEt1.getText().toString();
    }

    @Override
    public String getpsw() {
        return mEt2.getText().toString();
    }
}
