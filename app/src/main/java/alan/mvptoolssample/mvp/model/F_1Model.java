package alan.mvptoolssample.mvp.model;

import android.app.Application;
import android.database.Observable;

import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import org.greenrobot.greendao.DbUtils;

import javax.inject.Inject;

import alan.mvptoolssample.mvp.contract.F_1Contract;
import alan.mvptoolssample.mvp.model.api.service.UserService;
import alan.mvptoolssample.mvp.model.en.en.BaseJson;
import alan.mvptoolssample.mvp.model.en.en.LoginBean;

/**
 * ================================================================
 * 创建时间：2017-12-12 17:26:07
 * 创建人：赵文贇
 * 文件描述：
 * 看淡身边的虚伪，静心宁神做好自己。路那么长，无愧走好每一步。
 * ================================================================
 */
@ActivityScope
public class F_1Model extends BaseModel implements F_1Contract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public F_1Model(IRepositoryManager repositoryManager, Gson gson, Application application) {
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
    public io.reactivex.Observable<BaseJson<LoginBean>> doLogin(String userName, String psw) {
        return mRepositoryManager.obtainRetrofitService(UserService.class).doLogin(userName, psw);
    }

    @Override
    public void insertUserId(String mId) {
//        DbUtils.inser(mId);
    }
}