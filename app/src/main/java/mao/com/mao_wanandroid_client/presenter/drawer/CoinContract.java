package mao.com.mao_wanandroid_client.presenter.drawer;
import java.util.List;

import mao.com.mao_wanandroid_client.base.BaseView;
import mao.com.mao_wanandroid_client.base.presenter.AbstractBasePresenter;
import mao.com.mao_wanandroid_client.model.modelbean.rank.CoinRecordData;

/**
 * @author maoqitian
 * @Description: 积分
 * @date 2019/7/26 0026 16:04
 */
public interface CoinContract {

    interface CoinView extends BaseView{
        void showCoinCount(int coin);

        void showPersonalCoinList(List<CoinRecordData> coinDataList);

        void showFail(String msg);
    }


    interface CoinFragmentPresenter extends AbstractBasePresenter<CoinView>{

          void getCoinCount();

          void getPersonalCoinList();
    }

}

