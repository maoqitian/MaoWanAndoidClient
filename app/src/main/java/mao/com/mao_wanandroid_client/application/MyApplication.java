package mao.com.mao_wanandroid_client.application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatDelegate;

import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshInitializer;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.core.dao.DaoMaster;
import mao.com.mao_wanandroid_client.core.dao.DaoSession;
import mao.com.mao_wanandroid_client.core.sp.SharedPreferenceHelperImpl;
import mao.com.mao_wanandroid_client.di.component.DaggerAppComponent;
import mao.com.mao_wanandroid_client.di.module.MyAppModule;

/**
 * @author maoqitian
 * @Description
 * @Time 2018/9/30 0030 16:39
 */
public class MyApplication extends DaggerApplication {

    /*@Inject
    DispatchingAndroidInjector<Activity> mAndroidInjector;*/

    static {//使用static代码段可以防止内存泄漏

        //设置全局默认配置（优先级最低，会被其他设置覆盖）
        SmartRefreshLayout.setDefaultRefreshInitializer(new DefaultRefreshInitializer() {
            @Override
            public void initialize(@NonNull Context context, @NonNull RefreshLayout layout) {
                //开始设置全局的基本参数（可以被下面的DefaultRefreshHeaderCreator覆盖）
                layout.setDisableContentWhenLoading(false);//是否在加载的时候禁止列表的操作
                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white); //主题\强调颜色资源Id
            }
        });
    }

    //双重效验锁实现单例
    //private static volatile MyApplication mInstance;
    private static  MyApplication mInstance;
    private DaoSession mDaoSession;

    SharedPreferences mSharedPreferences;


    public static synchronized MyApplication getInstance() {
       /* if (mInstance == null) {
            synchronized (MyApplication.class) {
                if (mInstance == null) {
                    mInstance = new MyApplication();
                }
            }
        }*/
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance  = this;
        initGreenDao();
        /*DaggerAppComponent.builder().
                myAppModule(new MyAppModule(this))
                .build();*/
        //设置主题样式
        mSharedPreferences=MyApplication.getInstance().getSharedPreferences(Constants.SHAREDPREFERENCES_NAME,Context.MODE_PRIVATE);
        int nightMode = mSharedPreferences.getInt(Constants.SP_NIGHT_MODE,1);
        AppCompatDelegate.setDefaultNightMode(nightMode);
    }


    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        //return DaggerAppComponent.builder().myAppModule(new MyAppModule()).build();
        return DaggerAppComponent.create();
    }

   /* @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().create(this);
    }*/
    //初始化GreenDao 数据库
    private void initGreenDao() {
        DaoMaster.DevOpenHelper devOpenHelper=new DaoMaster.DevOpenHelper(this,Constants.DB_NAME,null);
        SQLiteDatabase database = devOpenHelper.getWritableDatabase();
        DaoMaster daoMaster=new DaoMaster(database);
        mDaoSession=daoMaster.newSession();
    }

    public DaoSession getDaoSession(){
        return mDaoSession;
    }

   /* @Override
    public AndroidInjector<Activity> activityInjector() {
        return mAndroidInjector;
    }*/
}
