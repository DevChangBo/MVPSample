package alan.mvptoolssample.mvp.presenter;

import android.app.Application;
import android.content.DialogInterface;

import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.utils.RxLifecycleUtils;
import com.jess.arms.widget.dialog.SweetAlertDialog;
import com.trello.rxlifecycle2.LifecycleTransformer;

import alan.mvptoolssample.app.impl.OnHttpRequest;
import alan.mvptoolssample.app.utils.RxUtils;
import alan.mvptoolssample.mvp.model.en.en.BaseJson;
import alan.mvptoolssample.mvp.model.en.en.LoginBean;
import io.reactivex.disposables.Disposable;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import javax.inject.Inject;

import alan.mvptoolssample.mvp.contract.F_1Contract;


/**
 * ================================================================
 * 创建时间：2017-12-12 17:26:07
 * 创建人：赵文贇
 * 文件描述：
 * 看淡身边的虚伪，静心宁神做好自己。路那么长，无愧走好每一步。
 * ================================================================
 */
@ActivityScope
public class F_1Presenter extends BasePresenter<F_1Contract.Model, F_1Contract.View> implements DialogInterface.OnCancelListener {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;

    @Inject
    public F_1Presenter(F_1Contract.Model model, F_1Contract.View rootView
            , RxErrorHandler handler, Application application
            , ImageLoader imageLoader, AppManager appManager) {
        super(model, rootView);
        this.mErrorHandler = handler;
        this.mApplication = application;
        this.mImageLoader = imageLoader;
        this.mAppManager = appManager;
    }


    public DialogInterface.OnCancelListener getCancleListener() {
        return this;
    }


    @Override
    public void onCancel(DialogInterface mDialogInterface) {
        unDispose();
        mRootView.showMessage("操作被取消");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }

    public void dologin() {
        //网络请求
        String u = mRootView.getUn();
        String p = mRootView.getpsw();

        RxUtils.doHttpRequest(mModel.doLogin(u, p), new OnHttpRequest<BaseJson<LoginBean>, LoginBean>() {
            @Override
            public void onStart(Disposable disposable) {
                //显示进度条  请求发起之前
                addDispose(disposable);
                mRootView.showLoading();
            }

            @Override
            public void onFinally() {
                //请求结束 无论成功失败
                mRootView.hideLoading();
            }

            @Override
            public LifecycleTransformer<BaseJson<LoginBean>> getCompose() {
                //绑定view生命周期
                return RxLifecycleUtils.bindToLifecycle(mRootView);
            }

            @Override
            public RxErrorHandler getRxErrorHandler() {
                //统一的错误处理
                return mErrorHandler;
            }

            @Override
            public void onSucc(LoginBean mLoginBean) {
                mRootView.showDialog("提示","登录成功", SweetAlertDialog.SUCCESS_TYPE);
                mModel.insertUserId(mLoginBean.getId());
            }

            @Override
            public void onError(String errMsg) {
                //失败  说的是后台给你返回明确的错误代码 或者错误的msg  这里的错误不是网络请求的错误 或者网络异常的错误
                mRootView.showMessage(errMsg);
            }
        });
    }
}
