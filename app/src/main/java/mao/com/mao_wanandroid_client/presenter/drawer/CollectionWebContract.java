package mao.com.mao_wanandroid_client.presenter.drawer;

import android.content.Context;

import java.util.List;

import mao.com.mao_wanandroid_client.base.BaseView;
import mao.com.mao_wanandroid_client.base.presenter.AbstractBasePresenter;

import mao.com.mao_wanandroid_client.model.modelbean.webmark.WebBookMark;

/**
 * @author maoqitian
 * @Description: 收藏网站
 * @date 2019/7/26 0026 16:04
 */
public interface CollectionWebContract {

    interface CollectionWeb extends BaseView{
        void showCollectionWebData(List<WebBookMark> collectionWebDataList);


        void showAddCollectWebSuccess(WebBookMark webBookMark,String msg);
        void showUpdateCollectWebSuccess(int position,WebBookMark webBookMark,String msg);
        void showDeleteCollectWebSuccess(int position,String msg);

        void showCollectionWebFailStatus(String msg);

    }
    

    interface  CollectWebFragmentPresenter extends AbstractBasePresenter<CollectionWeb>{
         void getCollectWebData();
        /**
         * 删除收藏网站
         * @param context
         * @param id
         * @param position
         */
         void getDeleteCollectWebData(Context context, int id, int position);


    }
}

