package alan.mvptoolssample.mvp.contract;


import com.jess.arms.mvp.IView;
import com.jess.arms.mvp.IModel;

import alan.mvptoolssample.mvp.model.en.en.BaseJson;
import alan.mvptoolssample.mvp.model.en.en.LoginBean;
import io.reactivex.Observable;
import retrofit2.http.Query;

/**
 * ================================================================
 * 创建时间：2017-12-12 17:26:07
 * 创建人：赵文贇
 * 文件描述：
 * 看淡身边的虚伪，静心宁神做好自己。路那么长，无愧走好每一步。
 * ================================================================
 */
public interface F_1Contract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {
        String getUn();

        String getpsw();
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {
        Observable<BaseJson<LoginBean>> doLogin(String userName, String psw);

        void insertUserId(String mId);
    }
}
