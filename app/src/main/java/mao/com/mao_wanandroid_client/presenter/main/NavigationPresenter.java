package mao.com.mao_wanandroid_client.presenter.main;

import javax.inject.Inject;

import mao.com.mao_wanandroid_client.base.presenter.RxBasePresenter;
import mao.com.mao_wanandroid_client.core.http.DataClient;

/**
 * @author maoqitian
 * @Description
 * @Time 2019/5/8 0008 23:30
 */
public class NavigationPresenter
        extends RxBasePresenter<NavigationContract.NavigationView>
        implements NavigationContract.NavigationFragmentPresenter {

    private DataClient mDataClient;
    @Inject
    public NavigationPresenter(DataClient dataClient) {
        super(dataClient);
        this.mDataClient = dataClient;
    }

    @Override
    public void attachView(NavigationContract.NavigationView view) {
        super.attachView(view);
    }
}
