package mao.com.mao_wanandroid_client.di.module;



import dagger.Binds;
import dagger.Module;
import mao.com.mao_wanandroid_client.di.scope.ActivityScope;
import mao.com.mao_wanandroid_client.presenter.main.MainContract;
import mao.com.mao_wanandroid_client.presenter.main.MainPresenter;
import mao.com.mao_wanandroid_client.presenter.welcome.WelcomeContract;
import mao.com.mao_wanandroid_client.presenter.welcome.WelcomePresenter;


/**
 * @author maoqitian
 * @Description MainActivity 可以提供的注入对象Module
 * @Time 2019/3/27 0027 23:59
 */
@Module
public abstract class MainActivityModule {
    @ActivityScope
    @Binds
    abstract MainContract.MainActivityPresenter bindPresenter(MainPresenter presenter);
}


