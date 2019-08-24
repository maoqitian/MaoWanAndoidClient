package mao.com.mao_wanandroid_client.view.main.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
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
import mao.com.mao_wanandroid_client.compoent.RxBus;
import mao.com.mao_wanandroid_client.compoent.event.AutoLoginStatusEvent;
import mao.com.mao_wanandroid_client.compoent.event.LoginStatusEvent;
import mao.com.mao_wanandroid_client.model.modelbean.banner.HomePageBannerModel;
import mao.com.mao_wanandroid_client.model.modelbean.home.HomeArticleData;
import mao.com.mao_wanandroid_client.model.modelbean.home.HomeArticleListData;
import mao.com.mao_wanandroid_client.presenter.main.HomeFirstTabPresenter;
import mao.com.mao_wanandroid_client.presenter.main.HomePageFirstTabContract;
import mao.com.mao_wanandroid_client.utils.StartDetailPage;
import mao.com.mao_wanandroid_client.view.main.adapter.HomePageAdapter;
import mao.com.mao_wanandroid_client.view.main.holder.BannerHolderView;

/**
 * @author maoqitian
 * @Description 首页第一个tab Fragment
 * @Time 2019/5/21 0021 21:05
 */
public class HomeFirstTabFragment extends RootBaseFragment<HomeFirstTabPresenter>
        implements HomePageFirstTabContract.HomePageFirstTabView,
        OnItemClickListener, BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.inflate_view)
    SmartRefreshLayout mSmartRefreshLayout;
    @BindView(R.id.home_page_recyclerview)
    RecyclerView mRecyclerView;
    //轮播图控件
    ConvenientBanner<HomePageBannerModel> mConvenientBanner;

    private RecyclerView.LayoutManager layoutManager;
    private HomePageAdapter mAdapter;
    //private HomeLatestProjectAdapter mLatestProjectAdapter;
    List<HomeArticleData> homeArticleDataList;

    List<HomePageBannerModel> mBannerModelList;

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
        args.putString(Constants.TAG_TAB_NAME,tabName);
        HomeFirstTabFragment fragment = new HomeFirstTabFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        showLoading();
        if (getArguments() != null) {
            mTabTitle = getArguments().getString(Constants.TAG_TAB_NAME);
            Log.e("毛麒添","首页mTabTitle "+mTabTitle);
        }
        mPresenter.getHomeFirstPageData(false);
        /*if(getString(R.string.page_home_recommend).equals(mTabTitle)){
            //获取 首页第一个tab 数据
            initHomePage();
            showLoading();
            mPresenter.getHomeFirstPageData(false);
        }else if (getString(R.string.latest_project).equals(mTabTitle)){
            initLatestProjectPage();
            showLoading();
            mPresenter.getHomeLatestProjectListDate();
        }else {
            Log.e("毛麒添","加载错误");
        }*/
    }
    /*//最新项目页面 init
    private void initLatestProjectPage() {
        mLatestProjectAdapter = new HomeLatestProjectAdapter(R.layout.Article_project_item_cardview_layout,homeArticleDataList);
        mLatestProjectAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(mLatestProjectAdapter);
    }*/
    //首页推荐页面 init
    private void initHomePage() {
        mAdapter = new HomePageAdapter(R.layout.article_item_cardview_layout);
        mAdapter.setOnItemClickListener(this);
        LinearLayout bannerViewLayout = (LinearLayout) LayoutInflater.from(_mActivity).inflate(R.layout.home_banner_view_layout,null);
        mConvenientBanner = bannerViewLayout.findViewById(R.id.convenient_banner);
        bannerViewLayout.removeView(mConvenientBanner);
        mAdapter.addHeaderView(mConvenientBanner);
        mRecyclerView.setAdapter(mAdapter);
        setSmartRefreshLayoutListener();
        setHomePageItemClickListener();
    }

    private void setSmartRefreshLayoutListener() {
        //设置 Header 为 贝塞尔雷达 样式
        //mSmartRefreshLayout.setRefreshHeader(new BezierRadarHeader(_mActivity).setEnableHorizontalDrag(true));
        //设置 Footer 为 球脉冲 样式
        /*mSmartRefreshLayout.setRefreshFooter(new BallPulseFooter(_mActivity).setSpinnerStyle(SpinnerStyle.Scale));
        mSmartRefreshLayout.setEnableLoadMore(true);*/
        //mSmartRefreshLayout.setEnableClipFooterWhenFixedBehind(true);//内容不满屏幕的时候也开启加载更多
        //下拉加载
        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                Log.e("毛麒添","下拉加载");
                mPresenter.getRefreshPage();
                refreshLayout.autoRefresh();
            }
        });
        //加载更多
        mSmartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                Log.e("毛麒添","加载更多");
                mPresenter.getLoadMorePage();
                refreshLayout.autoLoadMore();
            }
        });
    }

    private void setHomePageItemClickListener() {
        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            HomeArticleData homeArticleData= (HomeArticleData) adapter.getItem(position);
            switch (view.getId()){
                case R.id.image_collect: //点击收藏
                    Log.e("毛麒添","点击收藏");
                    if(homeArticleData!=null){
                        addOrCancelCollect(position,homeArticleData);
                    }
                    break;
                case R.id.tv_super_chapterName:
                    //点击 知识体系 tag
                    StartDetailPage.start(_mActivity,homeArticleData,Constants.RESULT_CODE_HOME_PAGE,Constants.ACTION_KNOWLEDGE_LEVEL2_ACTIVITY);
                    break;
            }
        });
    }
    //收藏或者取消收藏
    /*private void addOrCancelCollect(int position,HomeArticleData homeArticleData) {
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

    private void initFirstTabRecyclerView() {
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(_mActivity);
        mRecyclerView.setLayoutManager(layoutManager);
        // specify an adapter
        homeArticleDataList = new ArrayList<>();
        mBannerModelList = new ArrayList<>();
        initHomePage();
    }

    //ConvenientBanner item 点击回调
    @Override
    public void onItemClick(int position) {
        //Toast.makeText(_mActivity,"点击banner ",Toast.LENGTH_SHORT).show();
        HomePageBannerModel homePageBannerModel = mBannerModelList.get(position);
        HomeArticleData homeArticleData = new HomeArticleData();
        homeArticleData.setTitle(homePageBannerModel.getTitle());
        homeArticleData.setLink(homePageBannerModel.getUrl());
        StartDetailPage.start(_mActivity,homeArticleData, Constants.PAGE_WEB_NOT_COLLECT,Constants.ACTION_PAGE_DETAIL_ACTIVITY);
    }

    @Override
    public void showHomePageBanner(boolean isRefreshData,List<HomePageBannerModel> bannerModelList) {
        /*if (isRefreshData){
            showLoading();
        }*/
        Log.e("毛麒添","首页banner 数据 "+bannerModelList.toString());
        mBannerModelList.clear();
        mBannerModelList = bannerModelList;
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

    //置顶文章
    @Override
    public void showTopArticleList(List<HomeArticleData> homeTopArticleList) {
        homeArticleDataList.clear();
        homeArticleDataList.addAll(homeTopArticleList);
    }

    // 首页文章数据
    @Override
    public void showHomeArticleList(boolean isRefreshData,HomeArticleListData homeArticleListData) {
        if(isRefreshData){ //登录 退出登录 刷新 下拉刷新 上来加载更多
            homeArticleDataList.addAll(homeArticleListData.getDatas());
            mAdapter.replaceData(homeArticleDataList);
            mSmartRefreshLayout.finishRefresh();
            mSmartRefreshLayout.finishLoadMore();
        }else {
            Log.e("毛麒添","首页ArticleList数据 "+homeArticleListData.toString());
            homeArticleDataList.addAll(homeArticleListData.getDatas());
            mAdapter.addData(homeArticleDataList);
            showNormal();
        }
    }

   /* @Override
    public void showHomeLatestProjectList(HomeArticleListData homeArticleListData) {
        Log.e("毛麒添","首页 最新项目 数据 "+homeArticleListData.toString());
        homeArticleDataList.clear();
        homeArticleDataList = homeArticleListData.getDatas();
        mLatestProjectAdapter.addData(homeArticleDataList);
        showNormal();
    }*/

    @Override
    public void showAutoLoginSuccess() {
        Log.e("毛麒添","自动登录成功");
        RxBus.getDefault().post(new AutoLoginStatusEvent(true));
    }

    @Override
    public void showAutoLoginFail(String errorMsg) {
        Log.e("毛麒添","自动登录失败 ：" + errorMsg);
        //发送自动登录状态到事件总线
        RxBus.getDefault().post(new LoginStatusEvent(false,false));
    }

    @Override
    public void showAddArticleCollectStatus(int position,HomeArticleData homeArticleData,String msg) {
        showCollectStatus(position,homeArticleData,msg);
    }
    //显示收藏 或取消 收藏之后的状态
    private void showCollectStatus(int position,HomeArticleData homeArticleData,String msg){
        if(homeArticleData!=null && mAdapter!=null){
            mAdapter.setData(position,homeArticleData);
        }
        Toast.makeText(_mActivity,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showCancelArticleCollectStatus(int position, HomeArticleData homeArticleData,String msg) {
        showCollectStatus(position,homeArticleData,msg);
    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        //Toast.makeText(_mActivity,"首页数据 被点击 BaseQuickAdapter :"+adapter.getItem(position).toString(),Toast.LENGTH_SHORT).show();
        HomeArticleData homeArticleData = (HomeArticleData) adapter.getItem(position);
        StartDetailPage.start(_mActivity,homeArticleData, Constants.PAGE_WEB_COLLECT,Constants.ACTION_PAGE_DETAIL_ACTIVITY);
    }

    @Override
    public void showError() {
        super.showError();
        mSmartRefreshLayout.finishRefresh();
        mSmartRefreshLayout.finishLoadMore();
    }

    @Override
    public void reload() {
        //从新加载页面
        showLoading();
        mPresenter.getHomeFirstPageData(false);
    }
}
