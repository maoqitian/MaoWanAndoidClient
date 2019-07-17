package mao.com.mao_wanandroid_client.presenter.main;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import mao.com.mao_wanandroid_client.base.presenter.RxBasePresenter;
import mao.com.mao_wanandroid_client.core.http.DataClient;
import mao.com.mao_wanandroid_client.core.http.control.BaseObserver;
import mao.com.mao_wanandroid_client.core.http.control.RxSchedulers;
import mao.com.mao_wanandroid_client.model.ResponseBody;

/**
 * @author maoqitian
 * @Description 搜索
 * @Time 2019/5/8 0008 23:30
 */
public class SearchPagePresenter extends RxBasePresenter<SearchPageContract.SearchPageView>
        implements SearchPageContract.SearchFragmentPresenter {

    private DataClient mDataClient;
    @Inject
    public SearchPagePresenter(DataClient dataClient) {
        super(dataClient);
        this.mDataClient = dataClient;
    }

    @Override
    public void attachView(SearchPageContract.SearchPageView view) {
        super.attachView(view);
    }

}
