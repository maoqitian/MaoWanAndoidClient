package mao.com.mao_wanandroid_client.presenter.main;

import javax.inject.Inject;

import mao.com.mao_wanandroid_client.base.presenter.RxBasePresenter;
import mao.com.mao_wanandroid_client.core.http.DataClient;

/**
 * @author maoqitian
 * @Description: 公众号详情页 Presenter
 * @date 2019/7/15 0015 17:53
 */
public class OfficialAccountsDetailPresenter extends RxBasePresenter<OfficialAccountsDetailContract.OfficialAccountsDetailView> implements OfficialAccountsDetailContract.OfficialAccountsDetailActivityPresenter {


    private DataClient mDataClient;
    @Inject
    public OfficialAccountsDetailPresenter(DataClient dataClient) {
        super(dataClient);
        this.mDataClient = dataClient;
    }


    @Override
    public void attachView(OfficialAccountsDetailContract.OfficialAccountsDetailView view) {
        super.attachView(view);
    }
}
