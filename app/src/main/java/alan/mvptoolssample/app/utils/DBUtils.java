package alan.mvptoolssample.app.utils;

import android.content.Context;

import com.jess.arms.base.App;

import java.util.List;

import alan.mvptoolssample.MVPSampleConfig;
import alan.mvptoolssample.app.greendao.DaoSession;
import alan.mvptoolssample.mvp.model.dbbean.User;

/**
 * ================================================================
 * 创建时间：2017/12/14 15:17
 * 创建人：赵文贇
 * 文件描述：
 * 看淡身边的虚伪，静心宁神做好自己。路那么长，无愧走好每一步。
 * ================================================================
 */
public class DBUtils {
    private static DBUtils mDBUtils;
    private DaoSession mDaoSession;

    private DBUtils(Context mContext) {
        this.mDaoSession = (DaoSession) ((App) mContext.getApplicationContext()).getAppComponent().extras().get(MVPSampleConfig.DBNAME);
    }

    public static DBUtils getInstance(Context context) {
        mDBUtils = mDBUtils == null ? new DBUtils(context) : mDBUtils;
        return mDBUtils;
    }

    public void insertUser(User mUser) {
        mDaoSession.getUserDao().insertOrReplace(mUser);
    }

    public List<User> getUsers() {
        return mDaoSession.getUserDao().loadAll();
    }
}
