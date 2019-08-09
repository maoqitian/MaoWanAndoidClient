package mao.com.mao_wanandroid_client.presenter.main;

import java.util.List;

import mao.com.mao_wanandroid_client.base.BaseView;
import mao.com.mao_wanandroid_client.base.presenter.AbstractBasePresenter;
import mao.com.mao_wanandroid_client.model.modelbean.knowlegetree.KnowledgeHierarchyData;

/**
 * @author maoqitian
 * @Description:
 * @date 2019/5/7 0007 11:47
 */
public interface KnowledgeHierarchyContract {

    interface KnowledgeHierarchyView extends BaseView {

        void showKnowledgeHierarchyData(List<KnowledgeHierarchyData> knowledgeHierarchyData);

    }


    interface KnowledgeHierarchyFragmentPresenter extends AbstractBasePresenter<KnowledgeHierarchyView> {

        void getKnowledgeHierarchyData();
    }
}
