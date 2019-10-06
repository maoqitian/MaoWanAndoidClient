package mao.com.mao_wanandroid_client.presenter.drawer;

import android.content.Context;

import mao.com.mao_wanandroid_client.base.BaseView;
import mao.com.mao_wanandroid_client.base.presenter.AbstractBasePresenter;
import mao.com.mao_wanandroid_client.model.modelbean.webmark.WebBookMark;

/**
 * @author maoqitian
 * @Description: 分享文章 Dialog
 * @date 2019/7/26 0026 16:04
 */
public interface ArticleShareDialogContract {

    interface ArticleShareDialogView extends BaseView{
        /*void showCollectionWebData(List<WebBookMark> collectionWebDataList);


        void showAddCollectWebSuccess(WebBookMark webBookMark, String msg);
        void showAddCollectWebFail(String msg);

        void showUpdateCollectWebSuccess(int position, WebBookMark webBookMark, String msg);
        void showUpdateCollectWebFail(String msg);

        void showDeleteCollectWebSuccess(int position, String msg);
        void showDeleteCollectWebFail(String msg);*/

    }


    interface  ArticleShareDialogFragmentPresenter extends AbstractBasePresenter<ArticleShareDialogView>{
        /**
         * 分享文章
         * @param context
         * @param title
         * @param link
         */
        void getAddCollectWebData(Context context, String title, String link);


    }
}

