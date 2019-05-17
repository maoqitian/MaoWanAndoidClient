package mao.com.mao_wanandroid_client.presenter.main;


import javax.inject.Inject;

import mao.com.mao_wanandroid_client.base.presenter.RxBasePresenter;
import mao.com.mao_wanandroid_client.core.http.DataClient;

/**
 * @author maoqitian
 * @Description: 知识体系
 * @date 2019/5/7 0007 11:47
 */
public class KnowledgeHierarchyPresenter extends RxBasePresenter<KnowledgeHierarchyContract.KnowledgeHierarchyView> implements KnowledgeHierarchyContract.KnowledgeHierarchyFragmentPresenter {

    private DataClient mDataClient;

    @Inject
    public KnowledgeHierarchyPresenter(DataClient dataClient) {
        super(dataClient);
        this.mDataClient = dataClient;
    }

    @Override
    public void attachView(KnowledgeHierarchyContract.KnowledgeHierarchyView view) {
        super.attachView(view);

    }
}
