package mao.com.mao_wanandroid_client.base.presenter;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import mao.com.mao_wanandroid_client.base.BaseView;
import mao.com.mao_wanandroid_client.core.http.DataClient;

/**
 * @author maoqitian
 * @Description 基于Rx的Presenter封装,控制订阅的生命周期
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

    protected void addSubscribe(Disposable disposable) {
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
         addSubscribe(disposable);
    }

    @Override
    public boolean getNightModeState() {
        return mDataClient.getNightModeState();
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
    public String getLoginAccount() {
        return mDataClient.getLoginUserName();
    }

    @Override
    public void setLoginAccount(String account) {
       mDataClient.setLoginUserName(account);
    }

    @Override
    public void setLoginPassword(String password) {
       mDataClient.setLoginPassword(password);
    }

    @Override
    public int getCurrentPage() {
        return mDataClient.getCurrentPage();
    }

}
