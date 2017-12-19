package alan.mvptoolssample.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import alan.mvptoolssample.mvp.contract.BannerContract;

/**
 * ================================================================
 * 创建时间：2017-12-19 16:02:16
 * 创建人：赵文贇
 * 文件描述：
 * 看淡身边的虚伪，静心宁神做好自己。路那么长，无愧走好每一步。
 * ================================================================
 */
@ActivityScope
public class BannerModel extends BaseModel implements BannerContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public BannerModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
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
}