package mao.com.mao_wanandroid_client.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import mao.com.mao_wanandroid_client.view.welcome.WelcomeActivity;

/**
 * @author maoqitian
 * @Description WelcomeActivity 可以提供的注入对象Module
 * @Time 2019/3/27 0027 23:59
 */
@Module
public class WelcomeActivityModule {
    @Singleton
    @Provides
    public WelcomeActivity providerWelcomeActivity(){
        return new WelcomeActivity();
    }
}
