package mao.com.mao_wanandroid_client.di.module;



import dagger.Binds;
import dagger.Module;
import mao.com.mao_wanandroid_client.di.scope.ActivityScope;
import mao.com.mao_wanandroid_client.presenter.login.LoginContract;
import mao.com.mao_wanandroid_client.presenter.login.LoginPresenter;
import mao.com.mao_wanandroid_client.presenter.main.KnowledgeLevel2PageContract;
import mao.com.mao_wanandroid_client.presenter.main.KnowledgeLevel2PagePresenter;


/**
 * @author maoqitian
 * @Description KnowledgeLevel2PageActivity 可以提供的注入对象Module
 * @Time 2019/3/27 0027 23:59
 */
@Module
public abstract class KnowledgeLevel2PageActivityModule {
    @ActivityScope
    @Binds
    abstract KnowledgeLevel2PageContract.KnowledgeLevel2PageActivityPresenter bindPresenter(KnowledgeLevel2PagePresenter presenter);
}


