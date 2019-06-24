package mao.com.mao_wanandroid_client.presenter.main;



import javax.inject.Inject;

import io.reactivex.Observable;
import mao.com.mao_wanandroid_client.base.presenter.RxBasePresenter;
import mao.com.mao_wanandroid_client.core.http.DataClient;
import mao.com.mao_wanandroid_client.core.http.control.BaseObserver;
import mao.com.mao_wanandroid_client.core.http.control.RxSchedulers;
import mao.com.mao_wanandroid_client.model.ResponseBody;
import mao.com.mao_wanandroid_client.model.home.HomeArticleListData;

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

    @Override
    public void getHomeLatestProjectListDate() {
        Observable<ResponseBody<HomeArticleListData>> responseBodyObservable = mDataClient.HomeArticleListProjectData(0);
        responseBodyObservable.compose(RxSchedulers.observableIO2Main())
                .subscribe(new BaseObserver<HomeArticleListData>() {
                    @Override
                    public void onSuccess(HomeArticleListData result) {
                        mView.showHomeLatestProjectList(result);
                    }

                    @Override
                    public void onFailure(Throwable e, String errorMsg) {

                    }
                });
    }
}
