package mao.com.mao_wanandroid_client.presenter.main;

import mao.com.mao_wanandroid_client.base.BaseView;
import mao.com.mao_wanandroid_client.base.presenter.AbstractBasePresenter;

/**
 * @author maoqitian
 * @Description
 * @Time 2019/5/8 0008 23:36
 */
public interface ProjectContract {

    interface ProjectView extends BaseView{}

    interface ProjectFragmentPresenter extends AbstractBasePresenter<ProjectView>{}
}
