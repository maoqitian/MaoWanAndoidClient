package mao.com.mao_wanandroid_client.di.module;



import dagger.Binds;
import dagger.Module;
import mao.com.mao_wanandroid_client.di.scope.FragmentScope;
import mao.com.mao_wanandroid_client.presenter.drawer.CoinContract;
import mao.com.mao_wanandroid_client.presenter.drawer.CoinPresenter;
import mao.com.mao_wanandroid_client.presenter.drawer.CoinRankContract;
import mao.com.mao_wanandroid_client.presenter.drawer.CoinRankPresenter;


/**
 * @author maoqitian
 * @Description CoinRankFragment可以提供的注入对象Module
 * @Time 2019/3/27 0027 23:59
 */
@Module
public abstract class CoinRankFragmentModule {
    @FragmentScope
    @Binds
    abstract CoinRankContract.CoinRankFragmentPresenter bindPresenter(CoinRankPresenter presenter);
}


