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

        /**
         * 不管分享是否成功 ， 关闭 dialog 而不是直接关闭（这样网络请求会被中断接收不到返回状态）
         */
        void showConfirmShareStatus();

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

