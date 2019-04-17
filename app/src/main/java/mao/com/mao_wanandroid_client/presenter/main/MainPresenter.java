package mao.com.mao_wanandroid_client.presenter.main;

import javax.inject.Inject;

import mao.com.mao_wanandroid_client.base.presenter.RxBasePresenter;
import mao.com.mao_wanandroid_client.core.http.DataClient;

/**
 * @author maoqitian
 * @Description
 * @Time 2019/2/21 0021 21:03
 */
public class MainPresenter extends RxBasePresenter<MainContract.MainView> implements MainContract.MainActivityPresenter {

    @Inject //@Inject注解表示Dagger2 可以从这获取Presenter 实例
    public MainPresenter(DataClient dataClient) {
        super(dataClient);
    }


}
