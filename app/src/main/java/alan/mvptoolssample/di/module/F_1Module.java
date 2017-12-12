package alan.mvptoolssample.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import alan.mvptoolssample.mvp.contract.F_1Contract;
import alan.mvptoolssample.mvp.model.F_1Model;

/**
 * ================================================================
 * 创建时间：2017-12-12 17:26:07
 * 创建人：赵文贇
 * 文件描述：
 * 看淡身边的虚伪，静心宁神做好自己。路那么长，无愧走好每一步。
 * ================================================================
 */
@Module
public class F_1Module {
    private F_1Contract.View view;

    /**
     * 构建F_1Module时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public F_1Module(F_1Contract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    F_1Contract.View provideF_1View() {
        return this.view;
    }

    @ActivityScope
    @Provides
    F_1Contract.Model provideF_1Model(F_1Model model) {
        return model;
    }
}