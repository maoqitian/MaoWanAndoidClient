package mao.com.mao_wanandroid_client.presenter.main;


import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.internal.observers.BlockingBaseObserver;
import mao.com.mao_wanandroid_client.base.presenter.RxBasePresenter;
import mao.com.mao_wanandroid_client.core.http.DataClient;
import mao.com.mao_wanandroid_client.core.http.control.BaseObserver;
import mao.com.mao_wanandroid_client.core.http.control.RxSchedulers;
import mao.com.mao_wanandroid_client.model.ResponseBody;
import mao.com.mao_wanandroid_client.model.banner.HomePageBannerModel;
import mao.com.mao_wanandroid_client.model.home.HomeArticleListData;

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
    }

    @Override
    public void getHomePageBanner() {
        Observable<ResponseBody<List<HomePageBannerModel>>> responseBodyObservable = mDataClient.GetHomePageBannerData();
        //获取 首页Banner 数据
        responseBodyObservable.compose(RxSchedulers.observableIO2Main())
                .subscribe(new BaseObserver<List<HomePageBannerModel>>() {
                    @Override
                    public void onSuccess(List<HomePageBannerModel> result) {
                        mView.showHomePageBanner(result);
                    }

                    @Override
                    public void onFailure(Throwable e, String errorMsg) {
                        mView.showError();
                    }
                });
    }

    @Override
    public void getHomeArticleListData() {
        Observable<ResponseBody<HomeArticleListData>> responseBodyObservable = mDataClient.HomeArticleListData(0);
        responseBodyObservable.compose(RxSchedulers.observableIO2Main())
                .subscribe(new BaseObserver<HomeArticleListData>() {
                    @Override
                    public void onSuccess(HomeArticleListData result) {
                        mView.showHomeArticleList(result);
                    }

                    @Override
                    public void onFailure(Throwable e, String errorMsg) {

                    }
                });
    }
}
