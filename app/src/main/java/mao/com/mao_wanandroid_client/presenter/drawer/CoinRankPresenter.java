package mao.com.mao_wanandroid_client.presenter.drawer;



import javax.inject.Inject;

import io.reactivex.Observable;
import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.application.MyApplication;
import mao.com.mao_wanandroid_client.base.presenter.RxBasePresenter;
import mao.com.mao_wanandroid_client.model.http.DataClient;
import mao.com.mao_wanandroid_client.model.http.control.BaseObserver;
import mao.com.mao_wanandroid_client.model.http.control.RxSchedulers;
import mao.com.mao_wanandroid_client.model.modelbean.ResponseBody;
import mao.com.mao_wanandroid_client.model.modelbean.rank.CoinBaseListData;
import mao.com.mao_wanandroid_client.model.modelbean.rank.RankData;

/**
 * @author maoqitian
 * @Description 积分排行榜 presenter
 * @Time 2019/8/24 0024 15:09
 */
public class CoinRankPresenter extends RxBasePresenter<CoinRankContract.CoinRankView> implements CoinRankContract.CoinRankFragmentPresenter{

    private DataClient mDataClient;

    private int curPage = 1;

    @Inject
    public CoinRankPresenter(DataClient dataClient) {
        super(dataClient);
        this.mDataClient = dataClient;
    }

    @Override
    public void attachView(CoinRankContract.CoinRankView view) {
        super.attachView(view);
    }

    @Override
    public void getCoinRank() {
        getCoinRankData(1,false);
    }

    private void getCoinRankData(int pageNum, boolean isRefresh) {
        Observable<ResponseBody<CoinBaseListData<RankData>>> coinRank = mDataClient.getCoinRank(pageNum);
        coinRank.compose(RxSchedulers.observableIO2Main())
                .subscribe(new BaseObserver<CoinBaseListData<RankData>>() {
                    @Override
                    public void onSuccess(CoinBaseListData<RankData> result) {
                        if(result.getDatas().size()!= 0){
                            curPage = result.getCurPage();
                            mView.showCoinRankData(result.getDatas(),isRefresh);
                        }else {
                            mView.showErrorMsg(MyApplication.getInstance().getString(R.string.not_load_more_msg));
                        }
                    }

                    @Override
                    public void onFailure(Throwable e, String errorMsg) {
                        mView.showErrorMsg(errorMsg);
                    }
                });
    }

    @Override
    public void getLoadMoreRankData() {
        getCoinRankData(curPage+1,true);
    }
}
