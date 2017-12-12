package alan.mvptoolssample.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import alan.mvptoolssample.di.module.MainModule;

import alan.mvptoolssample.mvp.ui.activity.MainActivity;

/**
 * ================================================================
 * 创建时间：2017-12-12 17:01:12
 * 创建人：赵文贇
 * 文件描述：
 * 看淡身边的虚伪，静心宁神做好自己。路那么长，无愧走好每一步。
 * ================================================================
 */
@ActivityScope
@Component(modules = MainModule.class, dependencies = AppComponent.class)
public interface MainComponent {
    void inject(MainActivity activity);
}