package mao.com.mao_wanandroid_client.di.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import mao.com.mao_wanandroid_client.di.scope.FragmentScope;
import mao.com.mao_wanandroid_client.view.main.fragment.HomePageFragment;


/**
 * @author maoqitian
 * @Description 用于生成 Fragment 注入器的Module，使用@ContributesAndroidInjector注解并指定modules为
 * @Time 2019/4/14 0014 14:09
 */
@Module
public abstract class FragmentBindingModule {
    @FragmentScope
    @ContributesAndroidInjector(modules = HomePageFragmentModule.class)
    abstract HomePageFragment contributeHomePageFragment();

}
