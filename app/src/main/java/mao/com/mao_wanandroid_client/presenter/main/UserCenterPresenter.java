package mao.com.mao_wanandroid_client.presenter.main;

import javax.inject.Inject;

import mao.com.mao_wanandroid_client.base.presenter.RxBasePresenter;
import mao.com.mao_wanandroid_client.model.http.DataClient;

/**
 * @author maoqitian
 * @Description 用户中心
 * @Time 2019/5/8 0008 23:30
 */
public class UserCenterPresenter extends RxBasePresenter<UserCenterContract.UserCenterView>
        implements UserCenterContract.UserCenterActivityPresenter {

    private DataClient mDataClient;
    @Inject
    public UserCenterPresenter(DataClient dataClient) {
        super(dataClient);
        this.mDataClient = dataClient;
    }

    @Override
    public void attachView(UserCenterContract.UserCenterView view) {
        super.attachView(view);
    }
}
