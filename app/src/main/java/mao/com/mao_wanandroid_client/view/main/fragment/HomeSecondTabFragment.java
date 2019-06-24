package mao.com.mao_wanandroid_client.view.main.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.application.Constants;
import mao.com.mao_wanandroid_client.base.fragment.RootBaseFragment;
import mao.com.mao_wanandroid_client.model.home.HomeArticleData;
import mao.com.mao_wanandroid_client.model.home.HomeArticleListData;
import mao.com.mao_wanandroid_client.presenter.main.HomeFirstTabPresenter;
import mao.com.mao_wanandroid_client.presenter.main.HomePageFirstTabContract;
import mao.com.mao_wanandroid_client.presenter.main.HomePageSecondTabContract;
import mao.com.mao_wanandroid_client.presenter.main.HomeSecondTabPresenter;
import mao.com.mao_wanandroid_client.utils.StartDetailPage;
import mao.com.mao_wanandroid_client.view.main.adapter.HomeLatestProjectAdapter;
import mao.com.mao_wanandroid_client.view.main.adapter.HomePageAdapter;

/**
 * @author maoqitian
 * @Description 首页第二个 tab Fragment (最新项目)
 * @Time 2019/5/21 0021 21:06
 */
public class HomeSecondTabFragment extends RootBaseFragment<HomeSecondTabPresenter>
        implements HomePageSecondTabContract.HomePageSecondTabView, BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.view_base_normal)
    SmartRefreshLayout mSmartRefreshLayout;
    @BindView(R.id.home_page_recyclerview)
    RecyclerView mRecyclerView;

    private RecyclerView.LayoutManager layoutManager;
    private HomeLatestProjectAdapter mLatestProjectAdapter;

    private List<HomeArticleData> homeArticleDataList;

    private String mTabTitle;

    public static Fragment newInstance(String tabName) {
        Bundle args = new Bundle();
        args.putString("tabName",tabName);
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
        homeArticleDataList = new ArrayList<>();
        initLatestProjectPage();
    }

    //最新项目页面 init
    private void initLatestProjectPage() {
        mLatestProjectAdapter = new HomeLatestProjectAdapter(R.layout.artical_project_item_cardview_layout,homeArticleDataList);
        mLatestProjectAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(mLatestProjectAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.home_first_tab_fragment_layout;
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        if (getArguments() != null) {
            mTabTitle = getArguments().getString("tabName");
            Log.e("毛麒添","首页mTabTitle "+mTabTitle);
        }
        showLoading();
        mPresenter.getHomeLatestProjectListDate();
    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        HomeArticleData homeArticleData = (HomeArticleData) adapter.getItem(position);
        StartDetailPage.start(_mActivity,homeArticleData, Constants.PAGE_WEB_COLLECT,Constants.ACTION_PAGE_DETAIL_ACTIVITY);
    }

    @Override
    public void showHomeLatestProjectList(HomeArticleListData homeArticleListData) {
        Log.e("毛麒添","首页 最新项目 数据 "+homeArticleListData.toString());
        homeArticleDataList.clear();
        homeArticleDataList = homeArticleListData.getDatas();
        mLatestProjectAdapter.addData(homeArticleDataList);
        showNormal();
    }
}
