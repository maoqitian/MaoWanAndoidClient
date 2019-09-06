package mao.com.mao_wanandroid_client.di.module;



import dagger.Binds;
import dagger.Module;
import mao.com.mao_wanandroid_client.di.scope.ActivityScope;
import mao.com.mao_wanandroid_client.presenter.main.UserCenterContract;
import mao.com.mao_wanandroid_client.presenter.main.UserCenterPresenter;


/**
 * @author maoqitian
 * @Description UserCenterActivity 可以提供的注入对象Module
 * @Time 2019/3/27 0027 23:59
 */
@Module
public abstract class UserCenterActivityModule {
    @ActivityScope
    @Binds
    abstract UserCenterContract.UserCenterActivityPresenter bindPresenter(UserCenterPresenter presenter);
}


