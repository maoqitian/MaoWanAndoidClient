package mao.com.mao_wanandroid_client.di.module;



import dagger.Binds;
import dagger.Module;
import mao.com.mao_wanandroid_client.di.scope.ActivityScope;
import mao.com.mao_wanandroid_client.presenter.login.LoginContract;
import mao.com.mao_wanandroid_client.presenter.login.LoginPresenter;


/**
 * @author maoqitian
 * @Description LoginActivity 可以提供的注入对象Module
 * @Time 2019/3/27 0027 23:59
 */
@Module
public abstract class LoginActivityModule {
    @ActivityScope
    @Binds
    abstract LoginContract.LoginActivityPresenter bindPresenter(LoginPresenter presenter);
}


