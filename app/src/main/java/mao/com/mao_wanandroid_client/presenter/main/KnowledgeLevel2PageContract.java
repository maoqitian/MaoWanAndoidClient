package mao.com.mao_wanandroid_client.presenter.main;

import mao.com.mao_wanandroid_client.base.BaseView;
import mao.com.mao_wanandroid_client.base.presenter.AbstractBasePresenter;

/**
 * @author maoqitian
 * @Description: 知识体系 二级页
 * @date 2019/6/28 0028 17:42
 */
public interface KnowledgeLevel2PageContract {

    interface KnowledgeLevel2PageView extends BaseView {

        void showLevel2PageView();
    }

    interface KnowledgeLevel2PageActivityPresenter extends AbstractBasePresenter<KnowledgeLevel2PageContract.KnowledgeLevel2PageView> {

    }
}
