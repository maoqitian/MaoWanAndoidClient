package mao.com.mao_wanandroid_client.presenter.main;


import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.internal.observers.BlockingBaseObserver;
import mao.com.mao_wanandroid_client.base.presenter.RxBasePresenter;
import mao.com.mao_wanandroid_client.core.http.DataClient;
import mao.com.mao_wanandroid_client.core.http.control.RxSchedulers;
import mao.com.mao_wanandroid_client.model.ResponseBody;
import mao.com.mao_wanandroid_client.model.banner.HomePageBannerModel;

/**
 * @author maoqitian
 * @Description: 首页 第二个 tab Presenter
 * @date 2019/5/7 0007 11:47
 */
public class HomeSecondTabPresenter extends RxBasePresenter<HomePageSecondTabContract.HomePageSecondTabView>
        implements HomePageSecondTabContract.HomeSecondTabFragmentPresenter {

    private DataClient mDataClient;

    @Inject
    public HomeSecondTabPresenter(DataClient dataClient) {
        super(dataClient);
        this.mDataClient = dataClient;
    }

    @Override
    public void attachView(HomePageSecondTabContract.HomePageSecondTabView view) {
        super.attachView(view);
    }
}
