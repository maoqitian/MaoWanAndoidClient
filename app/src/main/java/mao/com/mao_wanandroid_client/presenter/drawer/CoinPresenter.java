package mao.com.mao_wanandroid_client.presenter.drawer;



import javax.inject.Inject;

import io.reactivex.Observable;
import mao.com.mao_wanandroid_client.base.presenter.RxBasePresenter;
import mao.com.mao_wanandroid_client.model.http.DataClient;
import mao.com.mao_wanandroid_client.model.http.control.BaseObserver;
import mao.com.mao_wanandroid_client.model.http.control.RxSchedulers;
import mao.com.mao_wanandroid_client.model.modelbean.ResponseBody;
import mao.com.mao_wanandroid_client.model.modelbean.BaseListData;
import mao.com.mao_wanandroid_client.model.modelbean.rank.CoinRecordData;
import mao.com.mao_wanandroid_client.model.modelbean.rank.RankData;

/**
 * @author maoqitian
 * @Description 积分 presenter
 * @Time 2019/8/24 0024 15:09
 */
public class CoinPresenter extends RxBasePresenter<CoinContract.CoinView> implements CoinContract.CoinFragmentPresenter{

    private DataClient mDataClient;

    @Inject
    public CoinPresenter(DataClient dataClient) {
        super(dataClient);
        this.mDataClient = dataClient;
    }


    @Override
    public void attachView(CoinContract.CoinView view) {
        super.attachView(view);
    }

    @Override
    public void getCoinCount() {
        Observable<ResponseBody<RankData>> coinCount = mDataClient.getCoinCount();
        coinCount.compose(RxSchedulers.observableIO2Main())
                .subscribe(new BaseObserver<RankData>() {
                    @Override
                    public void onSuccess(RankData result) {
                        mView.showCoinCount(result);
                    }

                    @Override
                    public void onFailure(Throwable e, String errorMsg) {
                        mView.showErrorMsg(errorMsg);
                    }
                });
    }

    @Override
    public void getPersonalCoinList() {
        Observable<ResponseBody<BaseListData<CoinRecordData>>> personalCoinList = mDataClient.getPersonalCoinList(1);
        personalCoinList.compose(RxSchedulers.observableIO2Main())
                        .subscribe(new BaseObserver<BaseListData<CoinRecordData>>() {
                            @Override
                            public void onSuccess(BaseListData<CoinRecordData> result) {
                                mView.showPersonalCoinList(result.getDatas());
                            }

                            @Override
                            public void onFailure(Throwable e, String errorMsg) {
                                mView.showErrorMsg(errorMsg);
                            }
                        });
    }
}
