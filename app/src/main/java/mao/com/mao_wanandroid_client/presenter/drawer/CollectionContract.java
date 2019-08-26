package mao.com.mao_wanandroid_client.presenter.drawer;

import android.content.Context;

import java.util.List;

import mao.com.mao_wanandroid_client.base.BaseView;
import mao.com.mao_wanandroid_client.base.presenter.AbstractBasePresenter;
import mao.com.mao_wanandroid_client.model.modelbean.collect.CollectData;

/**
 * @author maoqitian
 * @Description: 收藏文章
 * @date 2019/7/26 0026 16:04
 */
public interface CollectionContract {

    interface CollectionView extends BaseView{
        void showCollectListData(List<CollectData> collectDataList,boolean isRefresh);
        void showLoadDataMessage(String msg);

        void showAddCollectData(CollectData collectData);
        /**
         * 取消收藏成功
         * @param position
         */
        void showCancelCollectArticleSuccess(int position,String msg);
        void showCancelCollectArticleFail(String msg);
    }


    interface CollectionFragmentPresenter extends AbstractBasePresenter<CollectionView>{
        void getCollectListData();
        void getLoadCollectListData();

        /**
         *
         * @param context 上下文
         * @param articleId
         * @param originId
         * @param position 取消收藏文章位置
         */
        void getCancelCollectArticleData(Context context,int articleId, int originId,int position);
    }

}

