package mao.com.mao_wanandroid_client.presenter.drawer;

import mao.com.mao_wanandroid_client.base.BaseView;
import mao.com.mao_wanandroid_client.base.presenter.AbstractBasePresenter;

/**
 * @author maoqitian
 * @Description: 我的收藏页面
 * @date 2019/7/26 0026 16:04
 */
public interface CollectionPageContract {

    interface CollectionPageView extends BaseView{
    }
    

    interface CollectionPageFragmentPresenter extends AbstractBasePresenter<CollectionPageView>{

    }
}

