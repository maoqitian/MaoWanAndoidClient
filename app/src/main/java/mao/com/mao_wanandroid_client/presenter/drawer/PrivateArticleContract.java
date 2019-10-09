package mao.com.mao_wanandroid_client.presenter.drawer;

import java.util.List;

import mao.com.mao_wanandroid_client.base.BaseView;
import mao.com.mao_wanandroid_client.base.presenter.AbstractBasePresenter;
import mao.com.mao_wanandroid_client.model.modelbean.frienduser.CommonWebData;

/**
 * @author maoqitian
 * @Description: 自己分享文章
 * @date 2019/7/26 0026 16:04
 */
public interface PrivateArticleContract {

    interface PrivateArticleView extends BaseView{
        //void showCommonWebData(List<CommonWebData> commonWebDataList);
    }
    

    interface  PrivateArticleFragmentPresenter extends AbstractBasePresenter<PrivateArticleView>{
         //void getCommonWebData();
    }
}

