package mao.com.mao_wanandroid_client.di.module;



import dagger.Binds;
import dagger.Module;
import mao.com.mao_wanandroid_client.di.scope.FragmentScope;
import mao.com.mao_wanandroid_client.presenter.main.HomeFirstTabPresenter;
import mao.com.mao_wanandroid_client.presenter.main.HomePageFirstTabContract;
import mao.com.mao_wanandroid_client.presenter.main.HomePageSecondTabContract;
import mao.com.mao_wanandroid_client.presenter.main.HomeSecondTabPresenter;


/**
 * @author maoqitian
 * @Description HomeFirstTabFragment 可以提供的注入对象Module
 * @Time 2019/3/27 0027 23:59
 */
@Module
public abstract class HomeFirstTabFragmentModule {
    @FragmentScope
    @Binds
    abstract HomePageFirstTabContract.HomeFirstTabFragmentPresenter bindPresenter(HomeFirstTabPresenter presenter);
}


