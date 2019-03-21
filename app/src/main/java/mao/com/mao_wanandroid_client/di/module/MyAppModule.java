package mao.com.mao_wanandroid_client.di.module;

import dagger.Module;
import mao.com.mao_wanandroid_client.application.MyApplication;

/**
 * @author maoqitian
 * @Description
 * @Time 2019/3/21 0021 20:51
 */
@Module
public class MyAppModule {

    private MyApplication application;

    public MyAppModule(MyApplication myApplication){
        this.application = myApplication;
    }


}
