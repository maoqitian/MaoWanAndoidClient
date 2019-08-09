package mao.com.mao_wanandroid_client.presenter.main;

import javax.inject.Inject;

import mao.com.mao_wanandroid_client.base.presenter.RxBasePresenter;
import mao.com.mao_wanandroid_client.model.http.DataClient;

/**
 * @author maoqitian
 * @Description 搜索结果
 * @Time 2019/5/8 0008 23:30
 */
public class SearchResultPresenter extends RxBasePresenter<SearchResultContract.SearchResultView>
        implements SearchResultContract.SearchResultActivityPresenter {

    private DataClient mDataClient;
    @Inject
    public SearchResultPresenter(DataClient dataClient) {
        super(dataClient);
        this.mDataClient = dataClient;
    }

    @Override
    public void attachView(SearchResultContract.SearchResultView view) {
        super.attachView(view);
    }
}
