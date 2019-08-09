package mao.com.mao_wanandroid_client.presenter.pagedetail;

import javax.inject.Inject;

import mao.com.mao_wanandroid_client.base.presenter.RxBasePresenter;
import mao.com.mao_wanandroid_client.model.http.DataClient;

/**
 * @author maoqitian
 * @Description
 * @Time 2019/6/1 0001 16:57
 */
public class PageDetailPresenter extends RxBasePresenter<PageDetailContract.PageDetailView>
               implements PageDetailContract.PageDetailActivityPresenter{

    private DataClient mDataClient;

    @Inject
    public PageDetailPresenter(DataClient dataClient) {
        super(dataClient);
        this.mDataClient = dataClient;
    }

    @Override
    public void attachView(PageDetailContract.PageDetailView view) {
        super.attachView(view);
    }
}
