package alan.mvptoolssample.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import alan.mvptoolssample.mvp.contract.F_4Contract;
import alan.mvptoolssample.mvp.model.F_4Model;

/**
 * ================================================================
 * 创建时间：2017-12-12 17:27:53
 * 创建人：赵文贇
 * 文件描述：
 * 看淡身边的虚伪，静心宁神做好自己。路那么长，无愧走好每一步。
 * ================================================================
 */
@Module
public class F_4Module {
    private F_4Contract.View view;

    /**
     * 构建F_4Module时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public F_4Module(F_4Contract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    F_4Contract.View provideF_4View() {
        return this.view;
    }

    @ActivityScope
    @Provides
    F_4Contract.Model provideF_4Model(F_4Model model) {
        return model;
    }
}