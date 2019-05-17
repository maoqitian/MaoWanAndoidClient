package mao.com.mao_wanandroid_client.presenter.main;

import javax.inject.Inject;

import mao.com.mao_wanandroid_client.base.presenter.RxBasePresenter;
import mao.com.mao_wanandroid_client.core.http.DataClient;

/**
 * @author maoqitian
 * @Description 公众号
 * @Time 2019/5/8 0008 23:24
 */
public class OfficialAccountsPresenter extends
        RxBasePresenter<OfficialAccountsContract.OfficialAccountsView>
        implements OfficialAccountsContract.OfficialAccountsFragmentPresenter {
    private DataClient mDataClient;
    @Inject
    public OfficialAccountsPresenter(DataClient dataClient) {
        super(dataClient);
        this.mDataClient = dataClient;
    }

    @Override
    public void attachView(OfficialAccountsContract.OfficialAccountsView view) {
        super.attachView(view);
    }
}
