package mao.com.mao_wanandroid_client.di.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import mao.com.mao_wanandroid_client.di.scope.ActivityScope;
import mao.com.mao_wanandroid_client.view.welcome.WelcomeActivity;

/**
 * @author maoqitian
 * @Description 用于生成Activity注入器的Module，使用@ContributesAndroidInjector注解并指定modules为
 * @Time 2019/4/14 0014 14:09
 */
@Module
public abstract class ActivityBindingModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = WelcomeActivityModule.class)
    abstract WelcomeActivity contributeWelcomeActivity();
}
