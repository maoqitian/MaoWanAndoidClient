package mao.com.mao_wanandroid_client.di.module;



import dagger.Binds;
import dagger.Module;
import mao.com.mao_wanandroid_client.di.scope.FragmentScope;
import mao.com.mao_wanandroid_client.presenter.drawer.CollectionContract;
import mao.com.mao_wanandroid_client.presenter.drawer.CollectionPresenter;


/**
 * @author maoqitian
 * @Description CollectionFragment 可以提供的注入对象Module
 * @Time 2019/3/27 0027 23:59
 */
@Module
public abstract class CollectionFragmentModule {
    @FragmentScope
    @Binds
    abstract CollectionContract.CollectionFragmentPresenter bindPresenter(CollectionPresenter presenter);
}


