package mao.com.mao_wanandroid_client.di.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import mao.com.mao_wanandroid_client.di.scope.ActivityScope;
import mao.com.mao_wanandroid_client.view.login.LoginActivity;
import mao.com.mao_wanandroid_client.view.login.SignUpActivity;
import mao.com.mao_wanandroid_client.view.main.KnowledgeLevel2PageActivity;
import mao.com.mao_wanandroid_client.view.main.MainActivity;
import mao.com.mao_wanandroid_client.view.main.OfficialAccountsDetailActivity;
import mao.com.mao_wanandroid_client.view.main.SearchResultActivity;
import mao.com.mao_wanandroid_client.view.pagedetail.PageDetailActivity;
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

    @ActivityScope
    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity contributeMainActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = PageDetailActivityModule.class)
    abstract PageDetailActivity contributePageDetailActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = LoginActivityModule.class)
    abstract LoginActivity contributeLoginActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = SignUpActivityModule.class)
    abstract SignUpActivity contributeSignUpActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = KnowledgeLevel2PageActivityModule.class)
    abstract KnowledgeLevel2PageActivity contributeKnowledgeLevel2PageActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = OfficialAccountsDetailActivityModule.class)
    abstract OfficialAccountsDetailActivity contributeOfficialAccountsDetailActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = SearchResultActivityModule.class)
    abstract SearchResultActivity contributeSearchResultActivity();
}
