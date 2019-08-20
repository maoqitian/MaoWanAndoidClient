package mao.com.mao_wanandroid_client.presenter.drawer;

import java.util.List;

import mao.com.mao_wanandroid_client.base.BaseView;
import mao.com.mao_wanandroid_client.base.presenter.AbstractBasePresenter;

import mao.com.mao_wanandroid_client.model.modelbean.webmark.webBookMark;

/**
 * @author maoqitian
 * @Description: 收藏网站
 * @date 2019/7/26 0026 16:04
 */
public interface CollectionWebContract {

    interface CollectionWeb extends BaseView{
        void showCollectionWebData(List<webBookMark> collectionWebDataList);
    }
    

    interface  CollectWebFragmentPresenter extends AbstractBasePresenter<CollectionWeb>{
         void getCollectWebData();
    }
}

