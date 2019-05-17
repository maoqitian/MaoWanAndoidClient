package mao.com.mao_wanandroid_client.di.module;



import dagger.Binds;
import dagger.Module;
import mao.com.mao_wanandroid_client.di.scope.FragmentScope;
import mao.com.mao_wanandroid_client.presenter.main.ProjectContract;
import mao.com.mao_wanandroid_client.presenter.main.ProjectPresenter;


/**
 * @author maoqitian
 * @Description NavigationFragment 可以提供的注入对象Module
 * @Time 2019/3/27 0027 23:59
 */
@Module
public abstract class ProjectFragmentModule {
    @FragmentScope
    @Binds
    abstract ProjectContract.ProjectFragmentPresenter bindPresenter(ProjectPresenter presenter);
}


