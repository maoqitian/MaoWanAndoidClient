package mao.com.mao_wanandroid_client.view.main.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.application.Constants;
import mao.com.mao_wanandroid_client.base.fragment.RootBaseFragment;
import mao.com.mao_wanandroid_client.model.modelbean.home.HomeArticleData;
import mao.com.mao_wanandroid_client.presenter.main.HomePageSecondTabContract;
import mao.com.mao_wanandroid_client.presenter.main.HomeSecondTabPresenter;
import mao.com.mao_wanandroid_client.utils.StartDetailPage;
import mao.com.mao_wanandroid_client.view.main.adapter.HomeLatestProjectAdapter;

/**
 * @author maoqitian
 * @Description 首页第二个 tab Fragment (最新项目)
 * @Time 2019/5/21 0021 21:06
 */
public class HomeSecondTabFragment extends RootBaseFragment<HomeSecondTabPresenter>
        implements HomePageSecondTabContract.HomePageSecondTabView, BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.inflate_view)
    SmartRefreshLayout mSmartRefreshLayout;
    @BindView(R.id.home_page_recyclerview)
    RecyclerView mRecyclerView;

    private RecyclerView.LayoutManager layoutManager;
    private HomeLatestProjectAdapter mLatestProjectAdapter;

    private List<HomeArticleData> mHomeArticleDataList;

    private String mTabTitle;
    // 该id在获取该分类下项目时需要用到
    private int projectId;

    /**
     *
     * @param tabName tab name
     * @param id 该id在获取该分类下项目时需要用到
     * @return
     */
    public static Fragment newInstance(String tabName,int id) {
        Bundle args = new Bundle();
        args.putString(Constants.TAG_TAB_NAME,tabName);
        args.putInt(Constants.BUNDLE_PROJECT_ID,id);
        HomeSecondTabFragment fragment = new HomeSecondTabFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView() {
        super.initView();
        initSecondTabRecyclerView();
    }

    private void initSecondTabRecyclerView() {
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(_mActivity);
        mRecyclerView.setLayoutManager(layoutManager);
        // specify an adapter
        mHomeArticleDataList = new ArrayList<>();
        initLatestProjectPage();
    }

    //最新项目页面 init
    private void initLatestProjectPage() {
        mLatestProjectAdapter = new HomeLatestProjectAdapter(R.layout.article_project_item_cardview_layout);
        mLatestProjectAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(mLatestProjectAdapter);
        setSmartRefreshLayoutListener();
        setHomePageItemClickListener();
    }

    private void setHomePageItemClickListener() {
        mLatestProjectAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            HomeArticleData homeArticleData= (HomeArticleData) adapter.getItem(position);
            switch (view.getId()){
                case R.id.image_project_collect: //点击收藏
                    Log.e("毛麒添","点击收藏");
                    if(homeArticleData!=null){
                        addOrCancelCollect(position,homeArticleData);
                    }
                    break;
                case R.id.tv_project_tag:
                    //点击项目tag
                    StartDetailPage.start(_mActivity,homeArticleData,Constants.RESULT_CODE_HOME_PAGE,Constants.ACTION_KNOWLEDGE_LEVEL2_ACTIVITY);
                    break;
            }
        });
    }
    //收藏或者取消收藏
    /*private void addOrCancelCollect(int position, HomeArticleData homeArticleData) {
        if(!mPresenter.getLoginStatus()){
            StartDetailPage.start(_mActivity,null, Constants.PAGE_LOGIN,Constants.ACTION_LOGIN_ACTIVITY);
            return;
        }
        if(!homeArticleData.isCollect()){
            //收藏
            mPresenter.addArticleCollect(position,homeArticleData);
        }else {
            //取消收藏
            mPresenter.cancelArticleCollect(position,homeArticleData);
        }
    }*/

    private void setSmartRefreshLayoutListener() {
        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                Log.e("毛麒添","下拉加载");
                mPresenter.getRefreshPage(projectId);
                refreshLayout.autoRefresh();
            }
        });
        //加载更多
        mSmartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                Log.e("毛麒添","加载更多");
                mPresenter.getLoadMorePage(projectId);
                refreshLayout.autoLoadMore();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.home_first_tab_fragment_layout;
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        showLoading();
        if (getArguments() != null) {
            mTabTitle = getArguments().getString(Constants.TAG_TAB_NAME);
            projectId = getArguments().getInt(Constants.BUNDLE_PROJECT_ID);
            Log.e("毛麒添","首页mTabTitle "+mTabTitle);
        }
        mPresenter.getProjectListDate(false,projectId);
        if(-1 != projectId){
            //项目模块不显示tag
            mLatestProjectAdapter.isShowTag(false);
        }
    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        HomeArticleData homeArticleData = (HomeArticleData) adapter.getItem(position);
        StartDetailPage.start(_mActivity,homeArticleData, Constants.PAGE_WEB_COLLECT,Constants.ACTION_PAGE_DETAIL_ACTIVITY);
    }

    @Override
    public void showHomeLatestProjectList(boolean isRefreshData,List<HomeArticleData> homeArticleDataList) {
        Log.e("毛麒添","首页 最新项目 数据 "+homeArticleDataList.toString());
        if(isRefreshData){ //加载更多
            mLatestProjectAdapter.addData(homeArticleDataList);
        }else {
            mHomeArticleDataList.clear();
            mHomeArticleDataList.addAll(homeArticleDataList);
            mLatestProjectAdapter.replaceData(homeArticleDataList);
            showNormal();
        }
        mSmartRefreshLayout.finishRefresh();
        mSmartRefreshLayout.finishLoadMore();

    }

    @Override
    public void showAddArticleCollectStatus(int position, HomeArticleData homeArticleData, String msg) {
        showCollectStatus(position,homeArticleData,msg);
    }

    @Override
    public void showCancelArticleCollectStatus(int position, HomeArticleData homeArticleData, String msg) {
        showCollectStatus(position,homeArticleData,msg);
    }
    //显示收藏 或取消 收藏之后的状态
    private void showCollectStatus(int position,HomeArticleData homeArticleData,String msg){
        if(homeArticleData!=null && mLatestProjectAdapter!=null){
            mLatestProjectAdapter.setData(position,homeArticleData);
        }
        Toast.makeText(_mActivity,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void reload() {
        showLoading();
        mPresenter.getProjectListDate(false,projectId);
    }
}
