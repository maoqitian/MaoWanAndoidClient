package mao.com.mao_wanandroid_client.di.module;



import dagger.Binds;
import dagger.Module;
import mao.com.mao_wanandroid_client.di.scope.FragmentScope;
import mao.com.mao_wanandroid_client.presenter.main.HomePageContract;
import mao.com.mao_wanandroid_client.presenter.main.HomePagePresenter;
import mao.com.mao_wanandroid_client.presenter.main.Level2PageContract;
import mao.com.mao_wanandroid_client.presenter.main.Level2PagePresenter;


/**
 * @author maoqitian
 * @Description KnowledgeLevel2PageFragment 可以提供的注入对象Module
 * @Time 2019/3/27 0027 23:59
 */
@Module
public abstract class KnowledgeLevel2PageFragmentModule {
    @FragmentScope
    @Binds
    abstract Level2PageContract.KnowledgeLevel2PageFragmentPresenter bindPresenter(Level2PagePresenter presenter);
}


