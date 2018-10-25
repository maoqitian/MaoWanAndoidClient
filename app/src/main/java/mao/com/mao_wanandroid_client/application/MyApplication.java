package mao.com.mao_wanandroid_client.application;

import android.app.Application;

/**
 * @author maoqitian
 * @Description
 * @Time 2018/9/30 0030 16:39
 */
public class MyApplication extends Application {

    //双重效验锁实现单例
    private static volatile MyApplication mInstance;

    private MyApplication(){
    }

    public static MyApplication getInstance() {
        if (mInstance == null) {
            synchronized (MyApplication.class) {
                if (mInstance == null) {
                    mInstance = new MyApplication();
                }
            }
        }
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
