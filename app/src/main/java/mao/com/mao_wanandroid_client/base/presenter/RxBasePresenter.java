package mao.com.mao_wanandroid_client.base.presenter;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import mao.com.mao_wanandroid_client.base.BaseView;
import mao.com.mao_wanandroid_client.http.DataClient;

/**
 * @author maoqitian
 * @Description 基于Rx的Presenter封装,控制订阅的生命周期
 * @Time 2018/10/25 0025 17:22
 */
public class RxBasePresenter<T extends BaseView> implements AbstractBasePresenter<T>{

    private T mView;
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

    }

    @Override
    public boolean getNightModeState() {
        return false;
    }

    @Override
    public void setLoginStatus(boolean loginStatus) {

    }

    @Override
    public boolean getLoginStatus() {
        return false;
    }

    @Override
    public String getLoginAccount() {
        return null;
    }

    @Override
    public void setLoginAccount(String account) {

    }

    @Override
    public void setLoginPassword(String password) {

    }

    @Override
    public int getCurrentPage() {
        return 0;
    }

}
