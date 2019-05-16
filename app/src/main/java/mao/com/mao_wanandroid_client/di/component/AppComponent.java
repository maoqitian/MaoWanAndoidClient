package mao.com.mao_wanandroid_client.di.component;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import mao.com.mao_wanandroid_client.application.MyApplication;
import mao.com.mao_wanandroid_client.di.module.ActivityBindingModule;
import mao.com.mao_wanandroid_client.di.module.FragmentBindingModule;
import mao.com.mao_wanandroid_client.di.module.MyAppModule;

/**
 * @author maoqitian
 * @Description
 * @Time 2019/3/21 0021 20:50
 * AndroidSupportInjectionModule  帮我们将 Android 中四大组件以及Fragment进行绑定
 */
@Singleton
@Component(modules = {
        MyAppModule.class,
        ActivityBindingModule.class,
        FragmentBindingModule.class,
        AndroidSupportInjectionModule.class
})
public interface AppComponent extends AndroidInjector<MyApplication> {


}
/*public interface AppComponent  {

   *//* @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<MyApplication> {}*//*

   void Inject(MyApplication myApplication);
}*/
