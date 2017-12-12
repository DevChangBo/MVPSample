package alan.mvptoolssample.app.impl;

import com.trello.rxlifecycle2.LifecycleTransformer;

import io.reactivex.disposables.Disposable;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/**
 * ================================================================
 * 创建时间：2017/9/3 上午11:52
 * 创建人：Alan
 * 文件描述：对网络请求简单封装后返回业务操作的入口
 * 看淡身边的虚伪，静心宁神做好自己。路那么长，无愧走好每一步。
 * ================================================================
 */
public interface OnHttpRequest<T,F> {
    /**
     * 请求之前回调
     *
     * @param disposable 调用disposable.dispose();切断其关注的事件
     */
    void onStart(Disposable disposable);

    /**
     * 请求结束后调用(无论成功失败)
     */
    void onFinally();

    /**
     * 将view加入生命周期统一管理
     *
     * @return tips:RxUtils.bindToLifecycle(mRootView);
     */
     LifecycleTransformer<T> getCompose();

    /**
     * 全局异常捕获
     *
     * @return
     */
    RxErrorHandler getRxErrorHandler();

    /**
     * 网络请求成功的回调
     */
    void onSucc(F f);

    /**
     * 网络请求失败的回调
     *
     * @param errMsg 导致失败的原因
     */
    void onError(String errMsg);

}
