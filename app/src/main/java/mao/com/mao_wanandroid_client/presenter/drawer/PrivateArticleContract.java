package mao.com.mao_wanandroid_client.presenter.drawer;

import android.content.Context;

import java.util.List;

import mao.com.mao_wanandroid_client.base.BaseView;
import mao.com.mao_wanandroid_client.base.presenter.AbstractBasePresenter;
import mao.com.mao_wanandroid_client.model.modelbean.frienduser.CommonWebData;
import mao.com.mao_wanandroid_client.model.modelbean.home.HomeArticleData;

/**
 * @author maoqitian
 * @Description: 自己分享文章
 * @date 2019/7/26 0026 16:04
 */
public interface PrivateArticleContract {

    interface PrivateArticleView extends BaseView{
        void showPrivateArticleData(boolean isLoadMore,List<HomeArticleData> articleDataList);
    }
    

    interface  PrivateArticleFragmentPresenter extends AbstractBasePresenter<PrivateArticleView>{
         void getPrivateArticleData();

         void getUserShareArticlesData(int userId);

         void getUserArticleDelete(Context context,int id);

        void getUserShareArticlesMoreData(int userId);

        void getPrivateArticleMoreData();
    }
}

