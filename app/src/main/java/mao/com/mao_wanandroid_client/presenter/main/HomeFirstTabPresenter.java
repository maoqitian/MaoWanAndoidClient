package mao.com.mao_wanandroid_client.presenter.main;


import android.util.Log;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function3;
import io.reactivex.internal.observers.BlockingBaseObserver;
import mao.com.mao_wanandroid_client.application.Constants;
import mao.com.mao_wanandroid_client.base.presenter.RxBasePresenter;
import mao.com.mao_wanandroid_client.compoent.RxBus;
import mao.com.mao_wanandroid_client.compoent.event.LoginStatusEvent;
import mao.com.mao_wanandroid_client.model.http.DataClient;
import mao.com.mao_wanandroid_client.model.http.control.BaseObserver;
import mao.com.mao_wanandroid_client.model.http.control.RxSchedulers;
import mao.com.mao_wanandroid_client.model.modelbean.ResponseBody;
import mao.com.mao_wanandroid_client.model.modelbean.banner.HomePageBannerModel;
import mao.com.mao_wanandroid_client.model.modelbean.home.HomeArticleData;
import mao.com.mao_wanandroid_client.model.modelbean.home.HomeArticleListData;
import mao.com.mao_wanandroid_client.model.modelbean.login.LoginData;


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
                    getHomeFirstPageData(loginStatusEvent.isLogin());
                    //getAllHomePageData(loginStatusEvent.isLogin());
                }else if(!loginStatusEvent.isLogin() && loginStatusEvent.isSignOut()){ //退出登录
                    //getAllHomePageData(loginStatusEvent.isSignOut());
                    getHomeFirstPageData(loginStatusEvent.isSignOut());
                }else {
                    getHomeFirstPageData(false);
                    //getAllHomePageData(false);
                }
            }
        }));

    }

    @Override
    public void getHomeFirstPageData(boolean isRefreshData) {
        Observable<ResponseBody<List<HomePageBannerModel>>> homePageBannerObservable = mDataClient.GetHomePageBannerData();
        Observable<ResponseBody<List<HomeArticleData>>> homeTopArticleDataObservable = mDataClient.HomeTopArticleData();
        Observable<ResponseBody<HomeArticleListData>> homeArticleListDataObservable = mDataClient.HomeArticleListData(0);
        //通过 Observable.zip组合首页 加载的各种数据
            addEventSubscribe(Observable.zip(homePageBannerObservable, homeTopArticleDataObservable, homeArticleListDataObservable,
                    new Function3<ResponseBody<List<HomePageBannerModel>>, ResponseBody<List<HomeArticleData>>, ResponseBody<HomeArticleListData>, HashMap<String, Object>>() {
                        @Override
                        public HashMap<String, Object> apply( ResponseBody<List<HomePageBannerModel>> homePageBannerResponseBody, ResponseBody<List<HomeArticleData>> homeTopArticleResponseBody, ResponseBody<HomeArticleListData> homeArticleListResponseBody) throws Exception {
                            //组合数据
                            return makeResponseDataMap(homePageBannerResponseBody,homeTopArticleResponseBody,homeArticleListResponseBody);
                        }
                    }).compose(RxSchedulers.observableIO2Main())
                    .subscribeWith(new BlockingBaseObserver<HashMap<String, Object>>() {
                        @Override
                        public void onNext(HashMap<String, Object> responseBodyHashMap) {
                            ResponseBody<List<HomePageBannerModel>> homePageBannerResponseBody = (ResponseBody<List<HomePageBannerModel>>) responseBodyHashMap.get(Constants.RESPONSE_BANNER_TYPE);
                            ResponseBody<List<HomeArticleData>> homeTopArticleResponseBody = (ResponseBody<List<HomeArticleData>>) responseBodyHashMap.get(Constants.RESPONSE_TOP_ARTICLE_TYPE);
                            ResponseBody<HomeArticleListData> homeArticleListResponseBody = (ResponseBody<HomeArticleListData>) responseBodyHashMap.get(Constants.RESPONSE_ARTICLE_TYPE);
                            if(homePageBannerResponseBody!=null){
                                mView.showHomePageBanner(isRefreshData,homePageBannerResponseBody.getData());
                            }
                            if(homeTopArticleResponseBody!=null){
                                mView.showTopArticleList(homeTopArticleResponseBody.getData());
                            }
                            if(homeArticleListResponseBody!=null){
                                curPage = homeArticleListResponseBody.getData().getCurPage();
                                mView.showHomeArticleList(isRefreshData,homeArticleListResponseBody.getData());
                            }
                        }
                        @Override
                        public void onError(Throwable e) {
                            mView.showError();
                        }
                    }));

    }

    private HashMap<String, Object> makeResponseDataMap(ResponseBody<List<HomePageBannerModel>> homePageBannerData,
                                                        ResponseBody<List<HomeArticleData>> homeTopArticleData,
                                                        ResponseBody<HomeArticleListData> homeArticleListData){
        HashMap<String, Object> responseMap= new HashMap<>();
        responseMap.put(Constants.RESPONSE_BANNER_TYPE,homePageBannerData);
        responseMap.put(Constants.RESPONSE_TOP_ARTICLE_TYPE,homeTopArticleData);
        responseMap.put(Constants.RESPONSE_ARTICLE_TYPE,homeArticleListData);
        return responseMap;

    }

    /*@Override
    public void getHomeFirstPageData(boolean isRefreshData) {
        if(mDataClient.getLoginStatus()){
            //已经登录过，自动登录
            //Log.e("毛麒添","自动登录密码 ：" + mDataClient.getLoginPassword());
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
    }*/

    /*private void getAllHomePageData(boolean isRefreshData) {
        //首页第一个 tab  banner
        Observable<ResponseBody<List<HomePageBannerModel>>> homePageBannerObservable = mDataClient.GetHomePageBannerData();
        //获取 首页Banner 数据
        homePageBannerObservable.compose(RxSchedulers.observableIO2Main())
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

    }*/

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
        getHomeFirstPageData(true);
        //getAllHomePageData(true);
    }
    //上拉加载更多
    @Override
    public void getLoadMorePage() {
        getHomeArticleListData(curPage,true);
    }
}
