package mao.com.mao_wanandroid_client.presenter.main;

import javax.inject.Inject;

import mao.com.mao_wanandroid_client.base.presenter.RxBasePresenter;
import mao.com.mao_wanandroid_client.core.http.DataClient;

/**
 * @author maoqitian
 * @Description
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
}
