package mao.com.mao_wanandroid_client.presenter.main;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import mao.com.mao_wanandroid_client.base.presenter.RxBasePresenter;
import mao.com.mao_wanandroid_client.compoent.RxBus;
import mao.com.mao_wanandroid_client.compoent.event.AutoLoginStatusEvent;
import mao.com.mao_wanandroid_client.compoent.event.LoginStatusEvent;
import mao.com.mao_wanandroid_client.core.http.DataClient;
import mao.com.mao_wanandroid_client.core.http.control.BaseObserver;
import mao.com.mao_wanandroid_client.core.http.control.RxSchedulers;
import mao.com.mao_wanandroid_client.model.ResponseBody;

/**
 * @author maoqitian
 * @Description
 * @Time 2019/2/21 0021 21:03
 */
public class MainPresenter extends RxBasePresenter<MainContract.MainView> implements MainContract.MainActivityPresenter {

    private DataClient mDataClient;

    @Inject //@Inject注解表示Dagger2 可以从这获取Presenter 实例
    public MainPresenter(DataClient dataClient) {
        super(dataClient);
        this.mDataClient = dataClient;
    }


    @Override
    public void attachView(MainContract.MainView view) {
        super.attachView(view);
        //登录状态订阅，登录成功之后改变侧边栏显示
        addEventSubscribe(RxBus.getDefault().toFlowable(LoginStatusEvent.class)
                .subscribe(loginStatusEvent -> {
                    if (loginStatusEvent.isLogin()) {
                        //登录成功
                        mView.showLoginView();
                    } else {
                        //登录失败
                        mView.showLogoutView();
                    }
                }));
        //自动登录状态订阅，登录成功之后改变侧边栏显示
        addEventSubscribe(RxBus.getDefault().toFlowable(AutoLoginStatusEvent.class)
                .subscribe(AutoLoginStatusEvent -> {
                    if (AutoLoginStatusEvent.isLogin()) {
                        //登录成功
                        mView.showLoginView();
                    } else {
                        //登录失败
                        mView.showLogoutView();
                    }
                }));

    }
    //退出登录
    @Override
    public void getSingOut() {
        Observable<ResponseBody<String>> loginOut = mDataClient.getSignOut();
        loginOut.compose(RxSchedulers.observableIO2Main())
                .subscribe(new BaseObserver<String>() {
                    @Override
                    public void onSuccess(String result) {
                         mDataClient.setLoginStatus(false);
                         mView.showSingOutSuccess();
                    }

                    @Override
                    public void onFailure(Throwable e, String errorMsg) {
                        mView.showSingOutFail(errorMsg);
                    }
                });
    }
}
