package mao.com.mao_wanandroid_client.presenter.main;


import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import mao.com.mao_wanandroid_client.base.presenter.RxBasePresenter;
import mao.com.mao_wanandroid_client.core.http.DataClient;
import mao.com.mao_wanandroid_client.core.http.control.BaseObserver;
import mao.com.mao_wanandroid_client.core.http.control.RxSchedulers;
import mao.com.mao_wanandroid_client.model.ResponseBody;
import mao.com.mao_wanandroid_client.model.banner.HomePageBannerModel;
import mao.com.mao_wanandroid_client.model.home.HomeArticleData;
import mao.com.mao_wanandroid_client.model.home.HomeArticleListData;
import mao.com.mao_wanandroid_client.model.login.LoginData;
import mao.com.mao_wanandroid_client.utils.MD5Utils;

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

                    }
                });

        //置顶文章
        Observable<ResponseBody<List<HomeArticleData>>> homeTopArticleDataObservable = mDataClient.HomeTopArticleData();
        homeTopArticleDataObservable.compose(RxSchedulers.observableIO2Main())
                                    .subscribe(new BaseObserver<List<HomeArticleData>>() {
                                        @Override
                                        public void onSuccess(List<HomeArticleData> result) {
                                            mView.showTopArticleList(result);
                                        }

                                        @Override
                                        public void onFailure(Throwable e, String errorMsg) {

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
        if(mDataClient.getLoginStatus()){
           //已经登录过，自动登录
            Log.e("毛麒添","自动登录密码 ：" + MD5Utils.encodeByMD5(mDataClient.getLoginPassword()));
            Observable<ResponseBody<LoginData>> loginDataObservable = mDataClient.postLoginData(mDataClient.getLoginUserName(), MD5Utils.encodeByMD5(mDataClient.getLoginPassword()));
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
                            mDataClient.setLoginStatus(false);
                            mView.showAutoLoginFail(errorMsg);
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
