package mao.com.mao_wanandroid_client.presenter.main;


import javax.inject.Inject;

import mao.com.mao_wanandroid_client.base.presenter.RxBasePresenter;
import mao.com.mao_wanandroid_client.model.http.DataClient;

/**
 * @author maoqitian
 * @Description: 知识体系
 * @date 2019/5/7 0007 11:47
 */
public class KnowledgeLevel2PagePresenter extends RxBasePresenter<KnowledgeLevel2PageContract.KnowledgeLevel2PageView> implements KnowledgeLevel2PageContract.KnowledgeLevel2PageActivityPresenter {

    private DataClient mDataClient;

    @Inject
    public KnowledgeLevel2PagePresenter(DataClient dataClient) {
        super(dataClient);
        this.mDataClient = dataClient;
    }

    @Override
    public void attachView(KnowledgeLevel2PageContract.KnowledgeLevel2PageView view) {
        super.attachView(view);
        view.showLevel2PageView();
    }
}
