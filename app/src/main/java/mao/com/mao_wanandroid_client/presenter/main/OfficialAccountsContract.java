package mao.com.mao_wanandroid_client.presenter.main;

import java.util.List;

import mao.com.mao_wanandroid_client.base.BaseView;
import mao.com.mao_wanandroid_client.base.presenter.AbstractBasePresenter;
import mao.com.mao_wanandroid_client.model.modelbean.knowlegetree.KnowledgeHierarchyData;

/**
 * @author maoqitian
 * @Description
 * @Time 2019/5/8 0008 23:21
 */
public interface OfficialAccountsContract {

    interface OfficialAccountsView extends BaseView{
        void showOfficialAccountsList(List<KnowledgeHierarchyData> officialAccountsListData );
    }

    interface OfficialAccountsFragmentPresenter extends AbstractBasePresenter<OfficialAccountsView>{

        void getOfficialAccountsListData();

    }
}
