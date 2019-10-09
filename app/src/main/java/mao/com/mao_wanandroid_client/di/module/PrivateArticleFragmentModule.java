package mao.com.mao_wanandroid_client.di.module;



import dagger.Binds;
import dagger.Module;
import mao.com.mao_wanandroid_client.di.scope.FragmentScope;
import mao.com.mao_wanandroid_client.presenter.drawer.CollectionContract;
import mao.com.mao_wanandroid_client.presenter.drawer.CollectionPresenter;
import mao.com.mao_wanandroid_client.presenter.drawer.PrivateArticleContract;
import mao.com.mao_wanandroid_client.presenter.drawer.PrivateArticlePresenter;


/**
 * @author maoqitian
 * @Description PrivateArticleFragment 可以提供的注入对象Module
 * @Time 2019/3/27 0027 23:59
 */
@Module
public abstract class PrivateArticleFragmentModule {
    @FragmentScope
    @Binds
    abstract PrivateArticleContract.PrivateArticleFragmentPresenter bindPresenter(PrivateArticlePresenter presenter);
}


