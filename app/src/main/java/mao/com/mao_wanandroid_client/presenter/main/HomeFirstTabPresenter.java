package mao.com.mao_wanandroid_client.presenter.main;


import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.application.MyApplication;
import mao.com.mao_wanandroid_client.base.presenter.RxBasePresenter;
import mao.com.mao_wanandroid_client.compoent.RxBus;
import mao.com.mao_wanandroid_client.compoent.event.LoginStatusEvent;
import mao.com.mao_wanandroid_client.core.http.DataClient;
import mao.com.mao_wanandroid_client.core.http.control.BaseObserver;
import mao.com.mao_wanandroid_client.core.http.control.RxSchedulers;
import mao.com.mao_wanandroid_client.model.ResponseBody;
import mao.com.mao_wanandroid_client.model.banner.HomePageBannerModel;
import mao.com.mao_wanandroid_client.model.home.HomeArticleData;
import mao.com.mao_wanandroid_client.model.home.HomeArticleListData;
import mao.com.mao_wanandroid_client.model.login.LoginData;


/**
 * @author maoqitian
 * @Description: 首页 第一个tab Presenter
 * @date 2019/5/7 0007 11:47
 */
public class HomeFirstTabPresenter extends RxBasePresenter<HomePageFirstTabContract.HomePageFirstTabView> implements HomePageFirstTabContract.HomeFirstTabFragmentPresenter {

    private DataClient mDataClient;
    //当前页码 实际下拉加载更多获取数据 填入该页面即可
    private int curPage = 0;
    @Inject
    public HomeFirstTabPresenter(DataClient dataClient) {
        super(dataClient);
        this.mDataClient = dataClient;
    }

    @Override
    public void attachView(HomePageFirstTabContract.HomePageFirstTabView view) {
        super.attachView(view);
        addEventSubscribe(RxBus.getDefault().toFlowable(LoginStatusEvent.class).subscribe(new Consumer<LoginStatusEvent>() {
            @Override
            public void accept(LoginStatusEvent loginStatusEvent) throws Exception {
                // 登录成功 重新加载数据
                Log.e("毛麒添","登录事件");
                if(loginStatusEvent.isLogin()){ //登录
                    getAllHomePageData(loginStatusEvent.isLogin());
                }else if(!loginStatusEvent.isLogin() && loginStatusEvent.isSignOut()){ //退出登录
                    getAllHomePageData(loginStatusEvent.isSignOut());
                }else {
                    getAllHomePageData(false);
                }
            }
        }));

    }

    @Override
    public void getHomeFirstPageData(boolean isRefreshData) {
        if(mDataClient.getLoginStatus()){
            //已经登录过，自动登录
            Log.e("毛麒添","自动登录密码 ：" + mDataClient.getLoginPassword());
            Observable<ResponseBody<LoginData>> loginDataObservable = mDataClient.postLoginData(mDataClient.getLoginUserName(),mDataClient.getLoginPassword());
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
        getAllHomePageData(isRefreshData);
    }

    private void getAllHomePageData(boolean isRefreshData) {
        //首页第一个 tab  banner
        Observable<ResponseBody<List<HomePageBannerModel>>> responseBodyObservable = mDataClient.GetHomePageBannerData();
        //获取 首页Banner 数据
        responseBodyObservable.compose(RxSchedulers.observableIO2Main())
                .subscribe(new BaseObserver<List<HomePageBannerModel>>() {
                    @Override
                    public void onSuccess(List<HomePageBannerModel> result) {
                        mView.showHomePageBanner(isRefreshData,result);
                    }

                    @Override
                    public void onFailure(Throwable e, String errorMsg) {
                        mView.showError();
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
                                            mView.showError();
                                        }
                                    });


        getHomeArticleListData(0,isRefreshData);
    }

    private void getHomeArticleListData(int pageNum,boolean isRefreshData) {
        //首页第一个 tab  文章
        Observable<ResponseBody<HomeArticleListData>> homeArticleListDataObservable = mDataClient.HomeArticleListData(pageNum);
        homeArticleListDataObservable.compose(RxSchedulers.observableIO2Main())
                .subscribe(new BaseObserver<HomeArticleListData>() {
                    @Override
                    public void onSuccess(HomeArticleListData result) {
                        curPage = result.getCurPage();
                        mView.showHomeArticleList(isRefreshData,result);
                    }

                    @Override
                    public void onFailure(Throwable e, String errorMsg) {
                        mView.showError();
                    }
                });
    }

    //首页第二个 tab
   /* @Override
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
    }*/

    /*@Override
    public void addArticleCollect(int position,HomeArticleData homeArticleData) {
        Observable<ResponseBody<String>> responseBodyObservable = mDataClient.addCollectInsideListData(homeArticleData.getId());
        responseBodyObservable.compose(RxSchedulers.observableIO2Main())
                              .subscribe(new BaseObserver<String>() {
                                  @Override
                                  public void onSuccess(String result) {
                                      homeArticleData.setCollect(true);
                                      mView.showAddArticleCollectStatus(position,homeArticleData, MyApplication.getInstance().getApplicationContext().getString(R.string.collection_success));
                                  }

                                  @Override
                                  public void onFailure(Throwable e, String errorMsg) {
                                      mView.showAddArticleCollectStatus(position,null, MyApplication.getInstance().getApplicationContext().getString(R.string.collection_fail));
                                  }
                              });
    }

    @Override
    public void cancelArticleCollect(int position, HomeArticleData homeArticleData) {
        Observable<ResponseBody<String>> responseBodyObservable = mDataClient.cancelCollectArticleListData(homeArticleData.getId());
        responseBodyObservable.compose(RxSchedulers.observableIO2Main())
                              .subscribe(new BaseObserver<String>() {
                                  @Override
                                  public void onSuccess(String result) {
                                      homeArticleData.setCollect(false);
                                      mView.showCancelArticleCollectStatus(position,homeArticleData, MyApplication.getInstance().getApplicationContext().getString(R.string.cancle_collection_success));
                                  }

                                  @Override
                                  public void onFailure(Throwable e, String errorMsg) {
                                      mView.showCancelArticleCollectStatus(position,null, MyApplication.getInstance().getApplicationContext().getString(R.string.cancle_collection_fail));
                                  }
                              });

    }*/

    //下拉刷新
    @Override
    public void getRefreshPage() {
        getAllHomePageData(true);
    }
    //上拉加载更多
    @Override
    public void getLoadMorePage() {
        getHomeArticleListData(curPage,true);
    }
}
