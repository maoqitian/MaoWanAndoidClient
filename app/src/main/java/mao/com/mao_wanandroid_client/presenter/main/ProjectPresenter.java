package mao.com.mao_wanandroid_client.presenter.main;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import mao.com.mao_wanandroid_client.base.presenter.RxBasePresenter;
import mao.com.mao_wanandroid_client.model.http.DataClient;
import mao.com.mao_wanandroid_client.model.http.control.BaseObserver;
import mao.com.mao_wanandroid_client.model.http.control.RxSchedulers;
import mao.com.mao_wanandroid_client.model.modelbean.ResponseBody;
import mao.com.mao_wanandroid_client.model.modelbean.project.ProjectClassifyData;

/**
 * @author maoqitian
 * @Description 项目
 * @Time 2019/5/8 0008 23:30
 */
public class ProjectPresenter
        extends RxBasePresenter<ProjectContract.ProjectView>
        implements ProjectContract.ProjectFragmentPresenter {

    private DataClient mDataClient;
    @Inject
    public ProjectPresenter(DataClient dataClient) {
        super(dataClient);
        this.mDataClient = dataClient;
    }

    @Override
    public void attachView(ProjectContract.ProjectView view) {
        super.attachView(view);
    }

    @Override
    public void getProjectClassifyData() {
        Observable<ResponseBody<List<ProjectClassifyData>>> projectClassifyData = mDataClient.getProjectClassifyData();
        projectClassifyData.compose(RxSchedulers.observableIO2Main())
                           .subscribe(new BaseObserver<List<ProjectClassifyData>>() {
                               @Override
                               public void onSuccess(List<ProjectClassifyData> result) {
                                   mView.showProjectClassifyData(result);
                               }

                               @Override
                               public void onFailure(Throwable e, String errorMsg) {
                                   mView.showErrorMsg(errorMsg);
                               }
                           });
    }
}
