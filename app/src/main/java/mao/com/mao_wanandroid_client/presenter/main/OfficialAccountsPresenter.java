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
 * @Description 公众号
 * @Time 2019/5/8 0008 23:24
 */
public class OfficialAccountsPresenter extends
        RxBasePresenter<OfficialAccountsContract.OfficialAccountsView>
        implements OfficialAccountsContract.OfficialAccountsFragmentPresenter {

    private DataClient mDataClient;
    @Inject
    public OfficialAccountsPresenter(DataClient dataClient) {
        super(dataClient);
        this.mDataClient = dataClient;
    }

    @Override
    public void attachView(OfficialAccountsContract.OfficialAccountsView view) {
        super.attachView(view);
    }

    @Override
    public void getOfficialAccountsListData() {
        Observable<ResponseBody<List<KnowledgeHierarchyData>>> wxArticle = mDataClient.getWxArticle();
        wxArticle.compose(RxSchedulers.observableIO2Main())
                 .subscribe(new BaseObserver<List<KnowledgeHierarchyData>>() {
                     @Override
                     public void onSuccess(List<KnowledgeHierarchyData> result) {
                           mView.showOfficialAccountsList(result);
                     }

                     @Override
                     public void onFailure(Throwable e, String errorMsg) {
                           mView.showError();
                     }
                 });
    }
}
