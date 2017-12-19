package alan.mvptoolssample.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import alan.mvptoolssample.mvp.contract.BannerContract;
import alan.mvptoolssample.mvp.model.BannerModel;

/**
 * ================================================================
 * 创建时间：2017-12-19 16:02:16
 * 创建人：赵文贇
 * 文件描述：
 * 看淡身边的虚伪，静心宁神做好自己。路那么长，无愧走好每一步。
 * ================================================================
 */
@Module
public class BannerModule {
    private BannerContract.View view;

    /**
     * 构建BannerModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public BannerModule(BannerContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    BannerContract.View provideBannerView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    BannerContract.Model provideBannerModel(BannerModel model) {
        return model;
    }
}