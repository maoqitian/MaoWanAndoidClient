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
        void showAddCollectWebFail(String msg);

        void showUpdateCollectWebSuccess(int position,WebBookMark webBookMark,String msg);
        void showUpdateCollectWebFail(String msg);

        void showDeleteCollectWebSuccess(int position,String msg);
        void showDeleteCollectWebFail(String msg);

    }
    

    interface  CollectWebFragmentPresenter extends AbstractBasePresenter<CollectionWeb>{
         void getCollectWebData();

        /**
         * 添加收藏网站
         * @param context
         * @param name
         * @param link
         */
         void getAddCollectWebData(Context context, String name,String link);

        /**
         * 更新收藏网站
         * @param context
         * @param webBookMark
         * @param position
         */
         void getUpdateCollectWebData(Context context,WebBookMark webBookMark,int position);

        /**
         * 删除收藏网站
         * @param context
         * @param Id
         * @param position
         */
         void getDeleteCollectWebData(Context context, int id, int position);


    }
}

