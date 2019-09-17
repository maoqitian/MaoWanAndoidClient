package mao.com.mao_wanandroid_client.presenter.drawer;
import java.util.List;

import mao.com.mao_wanandroid_client.base.BaseView;
import mao.com.mao_wanandroid_client.base.presenter.AbstractBasePresenter;
import mao.com.mao_wanandroid_client.model.modelbean.rank.CoinRecordData;
import mao.com.mao_wanandroid_client.model.modelbean.rank.RankData;

/**
 * @author maoqitian
 * @Description: 积分排行榜
 * @date 2019/7/26 0026 16:04
 */
public interface CoinRankContract {

    interface CoinRankView extends BaseView{
        void showCoinRankData(List<RankData> rankDataList,boolean isRefresh);
    }


    interface CoinRankFragmentPresenter extends AbstractBasePresenter<CoinRankView>{
        void getCoinRank();

        void getLoadMoreRankData();
    }

}

