package mao.com.mao_wanandroid_client.presenter.main;



import javax.inject.Inject;

import io.reactivex.Observable;
import mao.com.mao_wanandroid_client.base.presenter.RxBasePresenter;
import mao.com.mao_wanandroid_client.model.http.DataClient;
import mao.com.mao_wanandroid_client.model.http.control.BaseObserver;
import mao.com.mao_wanandroid_client.model.http.control.RxSchedulers;
import mao.com.mao_wanandroid_client.model.modelbean.ResponseBody;
import mao.com.mao_wanandroid_client.model.modelbean.home.HomeArticleListData;
import mao.com.mao_wanandroid_client.model.modelbean.project.ProjectListData;

/**
 * @author maoqitian
 * @Description: 首页 第二个 tab Presenter
 * @date 2019/5/7 0007 11:47
 */
public class HomeSecondTabPresenter extends RxBasePresenter<HomePageSecondTabContract.HomePageSecondTabView>
        implements HomePageSecondTabContract.HomeSecondTabFragmentPresenter {

    private DataClient mDataClient;
    private int curPage = 0;
    @Inject
    public HomeSecondTabPresenter(DataClient dataClient) {
        super(dataClient);
        this.mDataClient = dataClient;
    }

    @Override
    public void attachView(HomePageSecondTabContract.HomePageSecondTabView view) {
        super.attachView(view);
    }

    @Override
    public void getProjectListDate(boolean isRefreshData,int projectId) {
        if(-1 == projectId){
            //首页最新项目
            getHomeLatestProjectDate(0,isRefreshData);
        }else {
            getProjectDate(projectId,1,isRefreshData);
        }
    }
    //获取项目模块数据
    private void getProjectDate(int projectId,int pageNum, boolean isRefreshData){
        Observable<ResponseBody<ProjectListData>> projectListData = mDataClient.getProjectListData(pageNum, projectId);
        projectListData.compose(RxSchedulers.observableIO2Main())
                       .subscribe(new BaseObserver<ProjectListData>() {
                           @Override
                           public void onSuccess(ProjectListData result) {
                               curPage = result.getCurPage()+1;
                               mView.showHomeLatestProjectList(isRefreshData,result.getDatas());
                           }

                           @Override
                           public void onFailure(Throwable e, String errorMsg) {
                               mView.showError();
                           }
                       });
    }

    //获取首页最新项目数据
    private void getHomeLatestProjectDate(int pageNum, boolean isRefreshData) {
        Observable<ResponseBody<HomeArticleListData>> responseBodyObservable = mDataClient.HomeArticleListProjectData(pageNum);
        responseBodyObservable.compose(RxSchedulers.observableIO2Main())
                .subscribe(new BaseObserver<HomeArticleListData>() {
                    @Override
                    public void onSuccess(HomeArticleListData result) {
                        curPage = result.getCurPage();
                        mView.showHomeLatestProjectList(isRefreshData,result.getDatas());
                    }

                    @Override
                    public void onFailure(Throwable e, String errorMsg) {
                        mView.showError();
                    }
                });
    }

   /* //收藏项目
    @Override
    public void addArticleCollect(int position, HomeArticleData homeArticleData) {
        Observable<ResponseBody<String>> responseBodyObservable = mDataClient.addCollectInsideListData(homeArticleData.getId());
        responseBodyObservable.compose(RxSchedulers.observableIO2Main())
                .subscribe(new BaseObserver<String>() {
                    @Override
                    public void onSuccess(String result) {
                        homeArticleData.setCollect(true);
                        mView.showAddArticleCollectStatus(position,homeArticleData, MyApplication.getInstance().getApplicationContext().getString(R.string.collection_success));
                    }

                    @Override
                    public void onFailure(Throwable e, String errorMsg) {
                        mView.showAddArticleCollectStatus(position,null, MyApplication.getInstance().getApplicationContext().getString(R.string.collection_fail));
                    }
                });
    }
    //取消 收藏项目
    @Override
    public void cancelArticleCollect(int position, HomeArticleData homeArticleData) {
        Observable<ResponseBody<String>> responseBodyObservable = mDataClient.cancelCollectArticleListData(homeArticleData.getId());
        responseBodyObservable.compose(RxSchedulers.observableIO2Main())
                .subscribe(new BaseObserver<String>() {
                    @Override
                    public void onSuccess(String result) {
                        homeArticleData.setCollect(false);
                        mView.showCancelArticleCollectStatus(position,homeArticleData, MyApplication.getInstance().getApplicationContext().getString(R.string.cancle_collection_success));
                    }

                    @Override
                    public void onFailure(Throwable e, String errorMsg) {
                        mView.showCancelArticleCollectStatus(position,null, MyApplication.getInstance().getApplicationContext().getString(R.string.cancle_collection_fail));
                    }
                });
    }*/
    //刷新页面
    @Override
    public void getRefreshPage(int projectId) {
        if(-1 == projectId){
            //首页最新项目
            getHomeLatestProjectDate(0,false);
        }else {
            getProjectDate(projectId,1,false);
        }
    }
    //加载更多
    @Override
    public void getLoadMorePage(int projectId) {
        if(-1 == projectId){
            //首页最新项目
            getHomeLatestProjectDate(curPage,true);
        }else {
            getProjectDate(projectId,curPage,true);
        }

    }
}
