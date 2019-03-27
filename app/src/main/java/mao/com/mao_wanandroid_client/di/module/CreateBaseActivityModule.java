package mao.com.mao_wanandroid_client.di.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import mao.com.mao_wanandroid_client.di.component.BaseActivityComponent;
import mao.com.mao_wanandroid_client.view.welcome.WelcomeActivity;

/**
 * @author maoqitian
 * @Description @ContributesAndroidInjector 注解获取我们需要注入的实现BaseActivity实例
 * （相当于注入mvp 中的 View）
 * @Time 2019/3/27 0027 23:46
 */
@Module(subcomponents = BaseActivityComponent.class)
public abstract class CreateBaseActivityModule {

    @ContributesAndroidInjector(modules = WelcomeActivityModule.class)
    abstract WelcomeActivity contributesWelcomeActivityInjector();

}
