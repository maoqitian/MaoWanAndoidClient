package mao.com.mao_wanandroid_client.di.component;

import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;
import mao.com.mao_wanandroid_client.di.module.MyAppModule;

/**
 * @author maoqitian
 * @Description
 * @Time 2019/3/21 0021 20:50
 */
@Component(modules = {
        MyAppModule.class,
        AndroidSupportInjectionModule.class
})
public interface MyAppComponent {
}
