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
import mao.com.mao_wanandroid_client.model.login.LoginData;

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
    public void getHomeFirstPageData() {
        //首页第一个 tab  banner
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

        //首页第一个 tab  文章
        Observable<ResponseBody<HomeArticleListData>> homeArticleListDataObservable = mDataClient.HomeArticleListData(0);
        homeArticleListDataObservable.compose(RxSchedulers.observableIO2Main())
                .subscribe(new BaseObserver<HomeArticleListData>() {
                    @Override
                    public void onSuccess(HomeArticleListData result) {
                        mView.showHomeArticleList(result);
                    }

                    @Override
                    public void onFailure(Throwable e, String errorMsg) {
                        mView.showError();
                    }
                });
        if(getLoginUserName()!=null){
           //用户名不为空，自动登录
            Observable<ResponseBody<LoginData>> loginDataObservable = mDataClient.postLoginData(mDataClient.getLoginUserName(), mDataClient.getLoginPassword());
            loginDataObservable.compose(RxSchedulers.observableIO2Main())
                    .subscribe(new BaseObserver<LoginData>() {
                        @Override
                        public void onSuccess(LoginData result) {
                            mDataClient.setLoginUserName(result.getUsername());
                            mDataClient.setLoginStatus(true);
                            mView.showAutoLoginSuccess();
                        }

                        @Override
                        public void onFailure(Throwable e, String errorMsg) {
                            mDataClient.setLoginUserName("");
                            mView.showAutoLoginFail();
                        }
                    });
        }

    }

    //首页第二个 tab
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
