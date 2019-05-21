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
 * @Description: 首页 第一个tab Presenter
 * @date 2019/5/7 0007 11:47
 */
public class HomeFirstTabPresenter extends RxBasePresenter<HomePageFirstTabContract.HomePageFirstTabView> implements HomePageFirstTabContract.HomeFirstTabFragmentPresenter {

    private DataClient mDataClient;

    @Inject
    public HomeFirstTabPresenter(DataClient dataClient) {
        super(dataClient);
        this.mDataClient = dataClient;
    }

    @Override
    public void attachView(HomePageFirstTabContract.HomePageFirstTabView view) {
        super.attachView(view);
        Observable<ResponseBody<List<HomePageBannerModel>>> responseBodyObservable = mDataClient.GetHomePageBannerData();
        responseBodyObservable.compose(RxSchedulers.observableIO2Main())
                .subscribe(new BlockingBaseObserver<ResponseBody<List<HomePageBannerModel>>>() {
                    @Override
                    public void onNext(ResponseBody<List<HomePageBannerModel>> listResponseBody) {
                        mView.showHomePageBanner(listResponseBody.getData());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

}
