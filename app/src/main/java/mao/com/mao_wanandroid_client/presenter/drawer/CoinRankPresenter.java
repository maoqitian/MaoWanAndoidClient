package mao.com.mao_wanandroid_client.presenter.drawer;



import javax.inject.Inject;

import io.reactivex.Observable;
import mao.com.mao_wanandroid_client.base.presenter.RxBasePresenter;
import mao.com.mao_wanandroid_client.model.http.DataClient;
import mao.com.mao_wanandroid_client.model.http.control.BaseObserver;
import mao.com.mao_wanandroid_client.model.http.control.RxSchedulers;
import mao.com.mao_wanandroid_client.model.modelbean.ResponseBody;
import mao.com.mao_wanandroid_client.model.modelbean.rank.CoinBaseListData;
import mao.com.mao_wanandroid_client.model.modelbean.rank.CoinRecordData;
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
        Observable<ResponseBody<CoinBaseListData<RankData>>> coinRank = mDataClient.getCoinRank(1);
        coinRank.compose(RxSchedulers.observableIO2Main())
                .subscribe(new BaseObserver<CoinBaseListData<RankData>>() {
                    @Override
                    public void onSuccess(CoinBaseListData<RankData> result) {
                         mView.showCoinRankData(result.getDatas());
                    }

                    @Override
                    public void onFailure(Throwable e, String errorMsg) {

                    }
                });
    }
}
