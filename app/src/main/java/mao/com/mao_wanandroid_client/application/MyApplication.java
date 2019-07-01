package mao.com.mao_wanandroid_client.application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

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

        /*//全局设置默认的 Header
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                //开始设置全局的基本参数（这里设置的属性只跟下面的MaterialHeader绑定，其他Header不会生效，能覆盖DefaultRefreshInitializer的属性和Xml设置的属性）
                layout.setEnableHeaderTranslationContent(false);//拖动Header的时候是否同时拖动内容（默认true）
                return new MaterialHeader(context).setColorSchemeResources(R.color.colorPrimary,android.R.color.holo_green_light,android.R.color.holo_red_light,android.R.color.holo_blue_light);
            }
        });*/
    }

    //双重效验锁实现单例
    //private static volatile MyApplication mInstance;
    private static  MyApplication mInstance;
    private DaoSession mDaoSession;



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
