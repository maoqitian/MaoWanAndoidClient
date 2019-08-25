package mao.com.mao_wanandroid_client.di.module;



import dagger.Binds;
import dagger.Module;
import mao.com.mao_wanandroid_client.di.scope.FragmentScope;
import mao.com.mao_wanandroid_client.presenter.drawer.CollectionDialogContract;
import mao.com.mao_wanandroid_client.presenter.drawer.CollectionDialogPresenter;
import mao.com.mao_wanandroid_client.presenter.main.SearchPageContract;
import mao.com.mao_wanandroid_client.presenter.main.SearchPagePresenter;


/**
 * @author maoqitian
 * @Description CollectionDialogFragment 可以提供的注入对象Module
 * @Time 2019/3/27 0027 23:59
 */
@Module
public abstract class CollectionDialogFragmentModule {
    @FragmentScope
    @Binds
    abstract CollectionDialogContract.CollectionDialogFragmentPresenter bindPresenter(CollectionDialogPresenter presenter);
}


