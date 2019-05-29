package mao.com.mao_wanandroid_client.view.main.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.base.fragment.RootBaseFragment;
import mao.com.mao_wanandroid_client.model.banner.HomePageBannerModel;
import mao.com.mao_wanandroid_client.model.home.HomeArticleData;
import mao.com.mao_wanandroid_client.model.home.HomeArticleListData;
import mao.com.mao_wanandroid_client.presenter.main.HomeFirstTabPresenter;
import mao.com.mao_wanandroid_client.presenter.main.HomePageFirstTabContract;
import mao.com.mao_wanandroid_client.view.main.adapter.HomeLatestProjectAdapter;
import mao.com.mao_wanandroid_client.view.main.adapter.HomePageAdapter;
import mao.com.mao_wanandroid_client.view.main.hloder.BannerHolderView;

/**
 * @author maoqitian
 * @Description 首页第一个tab Fragment
 * @Time 2019/5/21 0021 21:05
 */
public class HomeFirstTabFragment extends RootBaseFragment<HomeFirstTabPresenter>
        implements HomePageFirstTabContract.HomePageFirstTabView,
        OnItemClickListener {

    @BindView(R.id.view_base_normal)
    SmartRefreshLayout mSmartRefreshLayout;
    @BindView(R.id.home_page_recyclerview)
    RecyclerView mRecyclerView;
    //轮播图控件
    ConvenientBanner<HomePageBannerModel> mConvenientBanner;

    private RecyclerView.LayoutManager layoutManager;
    private HomePageAdapter mAdapter;
    private HomeLatestProjectAdapter mLatestProjectAdapter;
    private List<HomeArticleData> homeArticleDataList;

    private String mTabTitle;
    @Override
    protected int getLayoutId() {
        return R.layout.home_first_tab_fragment_layout;
    }

    @Override
    protected void initView() {
        super.initView();
        initFirstTabRecyclerView();
    }

    public static HomeFirstTabFragment newInstance(String tabName) {
        Bundle args = new Bundle();
        args.putString("tabName",tabName);
        HomeFirstTabFragment fragment = new HomeFirstTabFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        if (getArguments() != null) {
            mTabTitle = getArguments().getString("tabName");
            Log.e("毛麒添","首页mTabTitle "+mTabTitle);
        }
        if(getString(R.string.page_home_recommend).equals(mTabTitle)){
            //获取 banner 数据
            initHomePage();
            mPresenter.getHomePageBanner();
            mPresenter.getHomeArticleListData();
            showLoading();
        }else if (getString(R.string.latest_project).equals(mTabTitle)){
            initLatestProjectPage();
            mPresenter.getHomeLatestProjectListDate();
            showLoading();
        }else {
            Log.e("毛麒添","加载错误");
        }
    }
    //最新项目页面 init
    private void initLatestProjectPage() {
        mLatestProjectAdapter = new HomeLatestProjectAdapter(R.layout.artical_project_item_cardview_layout,homeArticleDataList);
        mRecyclerView.setAdapter(mLatestProjectAdapter);
    }
    //首页推荐页面 init
    private void initHomePage() {
        mAdapter = new HomePageAdapter(R.layout.artical_item_cardview_layout,homeArticleDataList);
        LinearLayout bannerViewLayout = (LinearLayout) LayoutInflater.from(_mActivity).inflate(R.layout.home_banner_view_layout,null);
        mConvenientBanner = bannerViewLayout.findViewById(R.id.convenient_banner);
        bannerViewLayout.removeView(mConvenientBanner);
        mAdapter.addHeaderView(mConvenientBanner);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initFirstTabRecyclerView() {
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(_mActivity);
        mRecyclerView.setLayoutManager(layoutManager);
        // specify an adapter
        homeArticleDataList = new ArrayList<>();
    }

    //ConvenientBanner item 点击回调
    @Override
    public void onItemClick(int position) {

        Toast.makeText(_mActivity,"点击banner ",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showHomePageBanner(List<HomePageBannerModel> bannerModelList) {
        showNormal();
        Log.e("毛麒添","首页banner 数据 "+bannerModelList.toString());
        mConvenientBanner.setPages(new CBViewHolderCreator() {
            @Override
            public Holder createHolder(View itemView) {
                return new BannerHolderView(itemView,_mActivity);
            }

            @Override
            public int getLayoutId() {
                return R.layout.item_banner_view;
            }
        },bannerModelList)
                .setPageIndicator(new int[]{R.drawable.ic_circle_normal,R.drawable.ic_circle_press}) //指示器圆点样式
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL) //设置指示器的方向
                .setOnItemClickListener(this); // 点击事件
        mConvenientBanner.startTurning();
    }


    @Override
    public void showHomeArticleList(HomeArticleListData homeArticleListData) {
        Log.e("毛麒添","首页ArticleList数据 "+homeArticleListData.toString());
        homeArticleDataList.clear();
        homeArticleDataList = homeArticleListData.getDatas();
        mAdapter.addData(homeArticleDataList);
        showNormal();
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
