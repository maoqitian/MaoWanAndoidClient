package mao.com.mao_wanandroid_client.di.module;



import dagger.Binds;
import dagger.Module;
import mao.com.mao_wanandroid_client.di.scope.FragmentScope;
import mao.com.mao_wanandroid_client.presenter.main.SearchPageContract;
import mao.com.mao_wanandroid_client.presenter.main.SearchPagePresenter;


/**
 * @author maoqitian
 * @Description SearchFragment 可以提供的注入对象Module
 * @Time 2019/3/27 0027 23:59
 */
@Module
public abstract class SearchFragmentModule {
    @FragmentScope
    @Binds
    abstract SearchPageContract.SearchFragmentPresenter bindPresenter(SearchPagePresenter presenter);
}


