package alan.mvptoolssample.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import alan.mvptoolssample.di.module.F_3Module;

import alan.mvptoolssample.mvp.ui.fragment.F_3Fragment;

/**
 * ================================================================
 * 创建时间：2017-12-12 17:27:37
 * 创建人：赵文贇
 * 文件描述：
 * 看淡身边的虚伪，静心宁神做好自己。路那么长，无愧走好每一步。
 * ================================================================
 */
@ActivityScope
@Component(modules = F_3Module.class, dependencies = AppComponent.class)
public interface F_3Component {
    void inject(F_3Fragment fragment);
}