package mao.com.mao_wanandroid_client.presenter.main;


import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import mao.com.mao_wanandroid_client.base.presenter.RxBasePresenter;
import mao.com.mao_wanandroid_client.model.http.DataClient;
import mao.com.mao_wanandroid_client.model.http.control.BaseObserver;
import mao.com.mao_wanandroid_client.model.http.control.RxSchedulers;
import mao.com.mao_wanandroid_client.model.modelbean.ResponseBody;
import mao.com.mao_wanandroid_client.model.modelbean.knowlegetree.KnowledgeHierarchyData;

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

    @Override
    public void getKnowledgeHierarchyData() {
        Observable<ResponseBody<List<KnowledgeHierarchyData>>> knowledgeHierarchyData = mDataClient.getKnowledgeHierarchyData();
        knowledgeHierarchyData.compose(RxSchedulers.observableIO2Main())
                              .subscribe(new BaseObserver<List<KnowledgeHierarchyData>>() {
                                  @Override
                                  public void onSuccess(List<KnowledgeHierarchyData> result) {
                                      mView.showKnowledgeHierarchyData(result);
                                  }

                                  @Override
                                  public void onFailure(Throwable e, String errorMsg) {
                                      mView.showError();
                                  }
                              });
    }
}
