package mao.com.mao_wanandroid_client.presenter.drawer;

import java.util.List;

import mao.com.mao_wanandroid_client.base.BaseView;
import mao.com.mao_wanandroid_client.base.presenter.AbstractBasePresenter;
import mao.com.mao_wanandroid_client.model.collect.CollectData;

/**
 * @author maoqitian
 * @Description:
 * @date 2019/7/26 0026 16:04
 */
public interface CollectionContract {

    interface CollectionView extends BaseView{
        void showCollectListData(List<CollectData> collectDataList,boolean isRefresh);
        void showLoadDataMessage(String msg);
    }


    interface CollectionFragmentPresenter extends AbstractBasePresenter<CollectionView>{
        void getCollectListData();
        void getLoadCollectListData();
    }

}

