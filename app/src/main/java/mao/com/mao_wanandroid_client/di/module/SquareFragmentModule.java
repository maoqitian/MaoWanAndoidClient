package mao.com.mao_wanandroid_client.di.module;



import dagger.Binds;
import dagger.Module;
import mao.com.mao_wanandroid_client.di.scope.FragmentScope;
import mao.com.mao_wanandroid_client.presenter.drawer.SquareContract;
import mao.com.mao_wanandroid_client.presenter.drawer.SquarePresenter;


/**
 * @author maoqitian
 * @Description SquareFragment 可以提供的注入对象Module
 * @Time 2019/3/27 0027 23:59
 */
@Module
public abstract class SquareFragmentModule {
    @FragmentScope
    @Binds
    abstract SquareContract.SquareFragmentPresenter bindPresenter(SquarePresenter presenter);
}


