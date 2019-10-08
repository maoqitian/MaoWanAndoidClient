package mao.com.mao_wanandroid_client.presenter.main;

import mao.com.mao_wanandroid_client.base.BaseView;
import mao.com.mao_wanandroid_client.base.presenter.AbstractBasePresenter;

/**
 * @author maoqitian
 * @Description: UserCenterActivity Contract
 * @date 2019/7/17 0017 11:24
 */
public interface UserCenterContract {

    interface UserCenterView extends BaseView {

    }

    interface UserCenterActivityPresenter extends AbstractBasePresenter<UserCenterView> {

        void getUserShareArticlesData(int id,int pageNum);

    }
}
