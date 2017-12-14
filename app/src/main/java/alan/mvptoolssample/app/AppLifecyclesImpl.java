/**
 * Copyright 2017 JessYan
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package alan.mvptoolssample.app;

import android.app.Application;
import android.content.Context;

import com.blankj.utilcode.util.Utils;
import com.jess.arms.base.delegate.AppLifecycles;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.logger.AndroidLogAdapter;
import com.jess.arms.utils.logger.Logger;
import com.jess.arms.utils.logger.PrettyFormatStrategy;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import alan.mvptoolssample.MVPSampleConfig;
import alan.mvptoolssample.app.greendao.DaoMaster;
import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * ================================================
 * 展示 {@link AppLifecycles} 的用法
 * <p>
 * Created by JessYan on 04/09/2017 17:12
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * ================================================
 */
public class AppLifecyclesImpl implements AppLifecycles {

    @Override
    public void attachBaseContext(Context base) {
//        MultiDex.install(base);  //这里比 onCreate 先执行,常用于 MultiDex 初始化,插件化框架的初始化
    }

    @Override
    public void onCreate(Application application) {
        initReleaseData(application);
    }

    @Override
    public void onTerminate(Application application) {

    }

    private void initReleaseData(Application application) {
        /*初始化日志打印*/
        initLog();

        /*工具类初始化*/
        Utils.init(application);
//
        //leakCanary内存泄露检查
        ArmsUtils.obtainAppComponentFromContext(application).extras().put(RefWatcher.class.getName(), MVPSampleConfig.USE_CANARY ? LeakCanary.install(application) : RefWatcher.DISABLED);
        //扩展 AppManager ZFConfig
        ArmsUtils.obtainAppComponentFromContext(application).appManager().setHandleListener((appManager, message) -> {
            switch (message.what) {
                //case 0:
                //do something ...
                //   break;
            }
        });
        /*数据库初始化*/
        initDB(application);
//
//        /*初始化友盟SDK*/
//        initUMengSDK(application);
//
//        /*初始化百度地图*/
//        SDKInitializer.initialize(application);
//
//        NineGridView.setImageLoader(new NineGridView.ImageLoader() {
//            @Override
//            public void onDisplayImage(Context context, ImageView imageView, String url) {
//                GlideArms.with(context).load(url).into(imageView);
//            }
//
//            @Override
//            public Bitmap getCacheImage(String url) {
//                return null;
//            }
//        });
//        Timber.d("初始化完成");
    }

//    private void initUMengSDK(Application application) {
//        UMConfigure.init(application.getApplicationContext(), UMConfigure.DEVICE_TYPE_PHONE, "fdd4df0beed56cae3ae67d9202853230");
//        UMConfigure.setLogEnabled(true);
//        UMConfigure.setEncryptEnabled(true);
//        PushAgent mPushAgent = PushAgent.getInstance(application);
//        //注册推送服务，每次调用register方法都会回调该接口
//        mPushAgent.register(new IUmengRegisterCallback() {
//
//            @Override
//            public void onSuccess(String deviceToken) {
//                //注册成功会返回device token
//                insertDeviceTokenToDB(application, deviceToken);
//            }
//
//            @Override
//            public void onFailure(String s, String s1) {
//                Timber.e("友盟推送注册失败,s = " + s + ",s1 = " + s1);
//                ArmsUtils.snackbarText("推送注册失败,请退出软件重试," + s1);
//            }
//        });
//        MiPushRegistar.register(application, "2882303761517632753", "5661763222753");
//        HuaWeiRegister.register(application);
//        mPushAgent.setMessageHandler((mContext, mUMessage) -> {
//            if (TextUtils.isEmpty(mUMessage.display_type)) return;
//            switch (mUMessage.display_type) {
//                case "notification":
//                    //发出通知
//                    sendNotification(application, mUMessage);
//                    break;
//                case "custom":
//                    //处理自定义透传消息
//                    doSomething(application, mUMessage);
//                    break;
//            }
//        });
//        // 使用两种推送方式 ： 所有的通知 都使用系统的样式 (避免退出app后清空了context而导致的无法弹出通知栏)
//        // app内部使用推送全部使用自定义模式(避免弹出通知栏，全部由app接手处理,比如:推送下线，等待。)
//    }
//
//    /**
//     * 自定义透传消息
//     *
//     * @param mApplication
//     * @param mUMessage
//     */
//    private void doSomething(Application mApplication, UMessage mUMessage) {
//        PushBean mPushBean = null;
//        try {
//            mPushBean = ((App) mApplication).getAppComponent().gson().fromJson(mUMessage.custom, PushBean.class);
//        } catch (Exception mE) {
//            mE.printStackTrace();
//            Timber.e(mE);
//        }
//        if (mPushBean == null) return;
//        //处理自定义通知
//        Timber.d(mPushBean.toString());
//        if (mPushBean.getErrcode().equals("1")) {
//            Activity mActivity = ((App) mApplication).getAppComponent().appManager().getTopActivity();
//            SweetAlertDialog mSweetAlertDialog = new SweetAlertDialog(mActivity, SweetAlertDialog.ERROR_TYPE)
//                    .setTitleText("下线通知")
//                    .setContentText("您的账号在其他设备登录\n\n如果这不是您本人操作，那么您的密码可能已经泄露，请及时至个人中心修改密码");
//            Observable.create(new ObservableOnSubscribe<Object>() {
//                @Override
//                public void subscribe(ObservableEmitter<Object> e) throws Exception {
//                    mSweetAlertDialog.show();
//                    ArmsUtils.obtainAppComponentFromContext(mApplication).extras().put("session", "");//清空session
//                    LoginCount mLastLoginAcount = DBUtils.getInstance(mApplication).getLastLoginAcount();
//                    DBUtils.getInstance(mApplication).insertLoginAcount(mLastLoginAcount.getAcount(), "");//清空用户名
//                }
//            }).subscribeOn(AndroidSchedulers.mainThread()).subscribe();
//            mSweetAlertDialog.setDisMissListener(() -> {
//                Intent mIntent = new Intent(mActivity, LoginActivity.class);
//                mIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//                ArmsUtils.startActivity(mActivity, mIntent);
//                ArmsUtils.killAll();
//            });
//        }
//    }
//
//    /**
//     * 将获取到的DeviceToken写入数据库
//     *
//     * @param mDeviceToken
//     */
//    private void insertDeviceTokenToDB(Application mApplication, String mDeviceToken) {
//        DBUtils.getInstance(mApplication).insertDeviceToken(mDeviceToken);
//    }
//
//    /**
//     * 发出通知
//     *
//     * @param mApplication
//     * @param mUMessage
//     */
//    private void sendNotification(Application mApplication, UMessage mUMessage) {
////        Activity mActivity = ((App) mApplication).getAppComponent().appManager().getTopActivity();
//        Intent intent = new Intent(mApplication, SplashActivity.class);
//        PendingIntent pintent = PendingIntent.getActivity(mApplication, 0, intent, 0);
//        Notification.Builder builder = new Notification.Builder(mApplication);
//        builder.setSmallIcon(R.mipmap.ic_launcher);// 设置图标
//        builder.setTicker("来自执法App的推送消息");// 手机状态栏的提示
//        builder.setWhen(System.currentTimeMillis());// 设置时间
//        builder.setContentTitle(mUMessage.title);// 设置标题
//        builder.setContentText(mUMessage.text);// 设置通知内容
//        builder.setContentIntent(pintent);// 点击后的意图
////        builder.setDefaults(Notification.DEFAULT_SOUND);//设置提示声音
//        // builder.setDefaults(Notification.DEFAULT_LIGHTS);//指示灯 需要权限
////        builder.setDefaults(Notification.DEFAULT_VIBRATE);//震动效果 需要权限
//        builder.setDefaults(Notification.DEFAULT_ALL);// 设置三种所有
//        builder.setAutoCancel(true);
//        Notification notification = builder.build();// 4.1以上 包括4.1
//        NotificationManager mManager = (NotificationManager) mApplication.getSystemService(Context.NOTIFICATION_SERVICE);
//        if (mManager != null) mManager.notify(Config.NOTIFICTION_ID_SYSTEM, notification);
//    }

    private void initLog() {
        if (MVPSampleConfig.LOG_DEBUG) {
            /*初始化日志打印工具,Timber内部可以动态的切换成任何日志框架(打印策略)进行日志打印,内部可以做到同时使用多个策略,比如添加三个策略,一个打印日志,一个将日志保存本地,一个将日志上传服务器*/
            Logger.addLogAdapter(new AndroidLogAdapter(PrettyFormatStrategy.newBuilder()
                    .showThreadInfo(true)  // (Optional) Whether to show thread info or not. Default true
                    .methodCount(1)         // (Optional) How many method line to show. Default 2
                    .tag(MVPSampleConfig.LOG_TAG)
                    .methodOffset(5).build()));
            Timber.plant(new Timber.DebugTree() {
                @Override
                protected void log(int priority, String tag, String message, Throwable t) {
                    Logger.log(priority, tag, message, t);
                }
            });
            ButterKnife.setDebug(true);
        }
    }

    private void initDB(Application application) {
        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(application.getApplicationContext(), MVPSampleConfig.DBNAME);

        /*将数据库操作对象写入全局，得到app实例后可使用数据库名称获取对应自定义参数*/
        ArmsUtils.obtainAppComponentFromContext(application.getApplicationContext()).extras().put(MVPSampleConfig.DBNAME, new DaoMaster(openHelper.getWritableDb()).newSession());
    }

}
