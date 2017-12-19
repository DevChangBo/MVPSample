package alan.mvptoolssample.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import alan.mvptoolssample.di.module.BannerModule;

import alan.mvptoolssample.mvp.ui.activity.BannerActivity;

/**
 * ================================================================
 * 创建时间：2017-12-19 16:02:16
 * 创建人：赵文贇
 * 文件描述：
 * 看淡身边的虚伪，静心宁神做好自己。路那么长，无愧走好每一步。
 * ================================================================
 */
@ActivityScope
@Component(modules = BannerModule.class, dependencies = AppComponent.class)
public interface BannerComponent {
    void inject(BannerActivity activity);
}