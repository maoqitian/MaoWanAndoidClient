package mao.com.mao_wanandroid_client.presenter.main;

import javax.inject.Inject;

import io.reactivex.Observable;
import mao.com.mao_wanandroid_client.base.presenter.RxBasePresenter;
import mao.com.mao_wanandroid_client.model.http.DataClient;
import mao.com.mao_wanandroid_client.model.http.control.BaseObserver;
import mao.com.mao_wanandroid_client.model.http.control.RxSchedulers;
import mao.com.mao_wanandroid_client.model.modelbean.ResponseBody;
import mao.com.mao_wanandroid_client.model.modelbean.home.HomeArticleListData;

/**
 * @author maoqitian
 * @Description: 公众号详情页 Presenter
 * @date 2019/7/15 0015 17:53
 */
public class OfficialAccountsDetailPresenter extends RxBasePresenter<OfficialAccountsDetailContract.OfficialAccountsDetailView> implements OfficialAccountsDetailContract.OfficialAccountsDetailActivityPresenter {


    private DataClient mDataClient;
    private int curPage = 1;
    @Inject
    public OfficialAccountsDetailPresenter(DataClient dataClient) {
        super(dataClient);
        this.mDataClient = dataClient;
    }


    @Override
    public void attachView(OfficialAccountsDetailContract.OfficialAccountsDetailView view) {
        super.attachView(view);
    }

    @Override
    public void getWxArticleHistory(int id) {
        getWxArticleHistoryData(id,true,1);
    }

    private void getWxArticleHistoryData(int id, boolean isRefresh,int pageNum) {
        Observable<ResponseBody<HomeArticleListData>> wxArticleHistory = mDataClient.getWxArticleHistory(id, pageNum);
        wxArticleHistory.compose(RxSchedulers.observableIO2Main())
                .subscribe(new BaseObserver<HomeArticleListData>() {
                    @Override
                    public void onSuccess(HomeArticleListData result) {
                        mView.showWxArticleHistoryData(result.getDatas(),isRefresh);
                        curPage = result.getCurPage();
                    }

                    @Override
                    public void onFailure(Throwable e, String errorMsg) {

                    }
                });
    }


    @Override
    public void getRefreshPage(int id) {
        getWxArticleHistoryData(id,true,1);
    }

    @Override
    public void getLoadMorePage(int id) {
        getWxArticleHistoryData(id,false,curPage + 1);
    }

}
