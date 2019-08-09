package mao.com.mao_wanandroid_client.presenter.main;


import javax.inject.Inject;

import mao.com.mao_wanandroid_client.base.presenter.RxBasePresenter;
import mao.com.mao_wanandroid_client.model.http.DataClient;

/**
 * @author maoqitian
 * @Description: 首页 Presenter
 * @date 2019/5/7 0007 11:47
 */
public class HomePagePresenter extends RxBasePresenter<HomePageContract.HomePageView> implements HomePageContract.HomePageFragmentPresenter {

    private DataClient mDataClient;

    @Inject
    public HomePagePresenter(DataClient dataClient) {
        super(dataClient);
        this.mDataClient = dataClient;
    }

    @Override
    public void attachView(HomePageContract.HomePageView view) {
        super.attachView(view);
        mView.showHomePageView();
    }
}
