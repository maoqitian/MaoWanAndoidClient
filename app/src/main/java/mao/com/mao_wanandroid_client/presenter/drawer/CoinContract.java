package mao.com.mao_wanandroid_client.presenter.drawer;
import java.util.List;

import mao.com.mao_wanandroid_client.base.BaseView;
import mao.com.mao_wanandroid_client.base.presenter.AbstractBasePresenter;
import mao.com.mao_wanandroid_client.model.modelbean.rank.CoinRecordData;
import mao.com.mao_wanandroid_client.model.modelbean.rank.RankData;

/**
 * @author maoqitian
 * @Description: 积分
 * @date 2019/7/26 0026 16:04
 */
public interface CoinContract {

    interface CoinView extends BaseView{
        void showCoinCount(RankData rankData);

        void showPersonalCoinList(List<CoinRecordData> coinDataList);

    }


    interface CoinFragmentPresenter extends AbstractBasePresenter<CoinView>{

          void getCoinCount();

          void getPersonalCoinList();
    }

}

