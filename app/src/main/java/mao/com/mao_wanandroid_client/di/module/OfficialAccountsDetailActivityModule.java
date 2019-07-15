package mao.com.mao_wanandroid_client.di.module;



import dagger.Binds;
import dagger.Module;
import mao.com.mao_wanandroid_client.di.scope.ActivityScope;
import mao.com.mao_wanandroid_client.presenter.main.OfficialAccountsDetailContract;
import mao.com.mao_wanandroid_client.presenter.main.OfficialAccountsDetailPresenter;


/**
 * @author maoqitian
 * @Description OfficialAccountsDetailActivity 公众号详情可以提供的注入对象Module
 * @Time 2019/3/27 0027 23:59
 */
@Module
public abstract class OfficialAccountsDetailActivityModule {
    @ActivityScope
    @Binds
    abstract OfficialAccountsDetailContract.OfficialAccountsDetailActivityPresenter bindPresenter(OfficialAccountsDetailPresenter presenter);
}


