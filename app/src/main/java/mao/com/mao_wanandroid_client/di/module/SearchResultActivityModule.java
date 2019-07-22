package mao.com.mao_wanandroid_client.di.module;



import dagger.Binds;
import dagger.Module;
import mao.com.mao_wanandroid_client.di.scope.ActivityScope;
import mao.com.mao_wanandroid_client.di.scope.FragmentScope;
import mao.com.mao_wanandroid_client.presenter.main.SearchPageContract;
import mao.com.mao_wanandroid_client.presenter.main.SearchPagePresenter;
import mao.com.mao_wanandroid_client.presenter.main.SearchResultContract;
import mao.com.mao_wanandroid_client.presenter.main.SearchResultPresenter;


/**
 * @author maoqitian
 * @Description SearchResultActivity 可以提供的注入对象Module
 * @Time 2019/3/27 0027 23:59
 */
@Module
public abstract class SearchResultActivityModule {
    @ActivityScope
    @Binds
    abstract SearchResultContract.SearchResultActivityPresenter bindPresenter(SearchResultPresenter presenter);
}


