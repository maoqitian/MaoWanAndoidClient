package mao.com.mao_wanandroid_client.di.component;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import mao.com.mao_wanandroid_client.application.MyApplication;
import mao.com.mao_wanandroid_client.di.module.CreateBaseActivityModule;
import mao.com.mao_wanandroid_client.di.module.MyAppModule;

/**
 * @author maoqitian
 * @Description
 * @Time 2019/3/21 0021 20:50
 * AndroidSupportInjectionModule  帮我们将 Android 中四大组件以及Fragment进行绑定
 */
@Component(modules = {
        MyAppModule.class,
        CreateBaseActivityModule.class,
        AndroidSupportInjectionModule.class
})
public interface MyAppComponent extends AndroidInjector<MyApplication> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<MyApplication> {}

}
