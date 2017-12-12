package alan.mvptoolssample.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import alan.mvptoolssample.mvp.contract.F_3Contract;
import alan.mvptoolssample.mvp.model.F_3Model;

/**
 * ================================================================
 * 创建时间：2017-12-12 17:27:37
 * 创建人：赵文贇
 * 文件描述：
 * 看淡身边的虚伪，静心宁神做好自己。路那么长，无愧走好每一步。
 * ================================================================
 */
@Module
public class F_3Module {
    private F_3Contract.View view;

    /**
     * 构建F_3Module时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public F_3Module(F_3Contract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    F_3Contract.View provideF_3View() {
        return this.view;
    }

    @ActivityScope
    @Provides
    F_3Contract.Model provideF_3Model(F_3Model model) {
        return model;
    }
}