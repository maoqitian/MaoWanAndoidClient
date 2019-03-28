package mao.com.mao_wanandroid_client.di.component;

import dagger.Component;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import mao.com.mao_wanandroid_client.base.activity.BaseActivity;

/**
 * @author maoqitian
 * @Description
 * @Time 2019/3/27 0027 23:37
 */
@Subcomponent(modules = {AndroidInjectionModule.class})
public interface BaseActivityComponent extends AndroidInjector<BaseActivity> {
    @Subcomponent.Builder
    abstract class BaseActivityBuilder extends AndroidInjector.Builder<BaseActivity> {}
}
