package mao.com.mao_wanandroid_client.presenter.welcome;

import mao.com.mao_wanandroid_client.base.presenter.RxBasePresenter;
import mao.com.mao_wanandroid_client.core.http.DataClient;

/**
 * @author maoqitian
 * @Description 闪屏页Presenter
 * @Time 2019/2/21 0021 21:01
 */
public class WelcomePresenter extends RxBasePresenter<WelcomeContract.WelcomeView> implements WelcomeContract.WelcomePresenter{


    public WelcomePresenter(DataClient dataClient) {
        super(dataClient);
    }

    @Override
    public void attachView(WelcomeContract.WelcomeView view) {
        super.attachView(view);
        view.jumpToMainActivity();

    }
}
