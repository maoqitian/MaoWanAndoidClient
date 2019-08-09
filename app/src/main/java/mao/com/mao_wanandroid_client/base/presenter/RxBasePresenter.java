package mao.com.mao_wanandroid_client.base.presenter;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.application.MyApplication;
import mao.com.mao_wanandroid_client.base.BaseView;
import mao.com.mao_wanandroid_client.model.http.DataClient;
import mao.com.mao_wanandroid_client.model.http.control.BaseObserver;
import mao.com.mao_wanandroid_client.model.http.control.RxSchedulers;
import mao.com.mao_wanandroid_client.model.modelbean.ResponseBody;
import mao.com.mao_wanandroid_client.model.modelbean.home.HomeArticleData;

/**
 * @author maoqitian
 * @Description 基于Rx的Presenter封装,控制 事件订阅的生命周期
 * @Time 2018/10/25 0025 17:22
 */
public class RxBasePresenter<T extends BaseView> implements AbstractBasePresenter<T>{

    protected T mView;
    /**
     * 一个disposable的容器，可以容纳多个disposable 防止订阅之后没有取消订阅的内存泄漏
     */
    private CompositeDisposable compositeDisposable;
    private DataClient mDataClient;


    public RxBasePresenter(DataClient dataClient){
        this.mDataClient=dataClient;
    }

    // 将订阅时间 event 加入到 disposable的容器中
    protected void addEventSubscribe(Disposable disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }

    @Override
    public void attachView(T view) {
       this.mView=view;
    }

    @Override
    public void detachView() {
        this.mView = null;
        if(compositeDisposable!=null){
            compositeDisposable.clear();
        }
    }

    @Override
    public void addRxBindingSubscribe(Disposable disposable) {
        addEventSubscribe(disposable);
    }

    @Override
    public int getThemeMode() {
        return mDataClient.getNightMode();
    }

    @Override
    public void setThemeMode(int mode) {
        mDataClient.setNightMode(mode);
    }


    @Override
    public void setLoginStatus(boolean loginStatus) {
        mDataClient.setLoginStatus(loginStatus);
    }

    @Override
    public boolean getLoginStatus() {
        return mDataClient.getLoginStatus();
    }

    @Override
    public String getLoginUserName() {
        return mDataClient.getLoginUserName();
    }

    @Override
    public void setLoginUserName(String userName) {
       mDataClient.setLoginUserName(userName);
    }

    @Override
    public void setLoginPassword(String password) {
       mDataClient.setLoginPassword(password);
    }

    @Override
    public int getCurrentPage() {
        return mDataClient.getCurrentPage();
    }
    //收藏 与 取消收藏
    @Override
    public void addArticleCollect(int position, HomeArticleData homeArticleData) {
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
    }

}
