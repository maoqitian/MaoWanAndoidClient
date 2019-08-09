package mao.com.mao_wanandroid_client.view.main;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.application.Constants;
import mao.com.mao_wanandroid_client.base.activity.BaseActivity;
import mao.com.mao_wanandroid_client.model.modelbean.home.HomeArticleData;
import mao.com.mao_wanandroid_client.model.modelbean.knowlegetree.KnowledgeHierarchyData;
import mao.com.mao_wanandroid_client.presenter.main.OfficialAccountsDetailContract;
import mao.com.mao_wanandroid_client.presenter.main.OfficialAccountsDetailPresenter;
import mao.com.mao_wanandroid_client.utils.StartDetailPage;
import mao.com.mao_wanandroid_client.utils.StatusBarUtil;
import mao.com.mao_wanandroid_client.utils.ToolsUtils;
import mao.com.mao_wanandroid_client.view.main.adapter.HomePageAdapter;
import mao.com.mao_wanandroid_client.view.main.fragment.SearchFragment;

/**
 * @author maoqitian
 * @Description: 公众号详情页
 * @date 2019/7/15 0015 17:39
 */
public class OfficialAccountsDetailActivity extends BaseActivity<OfficialAccountsDetailPresenter> implements
        OfficialAccountsDetailContract.OfficialAccountsDetailView, BaseQuickAdapter.OnItemClickListener,
        View.OnClickListener {


    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tv_page_title)
    TextView mTextTitle;
    @BindView(R.id.official_accounts_detail_recycleview)
    RecyclerView mRecyclerView;
    @BindView(R.id.official_accounts_detail_smartRefresh)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.iv_search)
    ImageView mSearch;


    KnowledgeHierarchyData mKnowledgeHierarchyData;

    private RecyclerView.LayoutManager layoutManager;
    private HomePageAdapter mAdapter;
    List<HomeArticleData> mHomeArticleDataList;
    //公众号id
    private int id = 0;

    //下拉刷新头部
    private MaterialHeader mMaterialHeader;

    SearchFragment mSearchFragment;

    @Override
    protected void onViewCreated() {
        super.onViewCreated();
        mHomeArticleDataList = new ArrayList<>();
        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new HomePageAdapter(R.layout.article_item_cardview_layout);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
        mMaterialHeader = (MaterialHeader)smartRefreshLayout.getRefreshHeader();
        //拖动Header的时候是否同时拖动内容（默认true）
        smartRefreshLayout.setEnableHeaderTranslationContent(false);
        mMaterialHeader.setColorSchemeResources(R.color.colorPrimary,android.R.color.holo_green_light,android.R.color.holo_red_light,android.R.color.holo_blue_light);
        setSmartRefreshLayoutListener();
        setAdapterItemChildListener();
    }

    private void setAdapterItemChildListener() {
        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            HomeArticleData homeArticleData= (HomeArticleData) adapter.getItem(position);
            //点击收藏
            if (view.getId() == R.id.image_collect) {
                Log.e("毛麒添", "点击收藏");
                if (homeArticleData != null) {
                    addOrCancelCollect(position, homeArticleData);
                }
            }
        });
    }

    private void setSmartRefreshLayoutListener() {
        smartRefreshLayout.setOnRefreshListener(refreshLayout -> {
            Log.e("毛麒添","下拉加载");
            mPresenter.getRefreshPage(id);
            refreshLayout.autoRefresh();
        });
        //加载更多
        smartRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            Log.e("毛麒添","加载更多");
            mPresenter.getLoadMorePage(id);
            refreshLayout.autoLoadMore();
        });
    }


    @Override
    protected void initToolbar() {
        mToolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(mToolbar);
        //出去默认标题
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        StatusBarUtil.setColorNoTranslucentLightMode(this, ContextCompat.getColor(this,R.color.colorPrimary));
        mToolbar.setNavigationOnClickListener(v -> finish());
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_official_accounts_detial_layout;
    }

    @Override
    protected void initEventAndData() {
        getIntentInitViewData();
    }

    private void getIntentInitViewData() {
        mSearch.setVisibility(View.VISIBLE);
        mSearch.setOnClickListener(this);
        Intent intent = getIntent();
        if (intent != null){
            mKnowledgeHierarchyData = (KnowledgeHierarchyData) intent.getSerializableExtra(Constants.KNOWLEDGE_DATA);
            //Log.e("毛麒添","mKnowledgeHierarchyData"+mKnowledgeHierarchyData.toString());
            mTextTitle.setText(mKnowledgeHierarchyData.getName());
            id = mKnowledgeHierarchyData.getId();
        }
        if(ToolsUtils.isNetworkConnected() && id != 0){
            mPresenter.getWxArticleHistory(id);
            smartRefreshLayout.autoRefresh();
        }
    }

    @Override
    public void showWxArticleHistoryData(List<HomeArticleData> homeArticleDataList,boolean isRefresh) {
        if(isRefresh){
            //下拉刷新 或者 第一次加载
            mHomeArticleDataList.clear();
            mHomeArticleDataList.addAll(homeArticleDataList);
            mAdapter.replaceData(homeArticleDataList);
        }else {
            //加载更多
            mAdapter.addData(homeArticleDataList);
        }
        smartRefreshLayout.finishRefresh();
        smartRefreshLayout.finishLoadMore();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        HomeArticleData homeArticleData = (HomeArticleData) adapter.getItem(position);
        StartDetailPage.start(this,homeArticleData, Constants.PAGE_WEB_COLLECT,Constants.ACTION_PAGE_DETAIL_ACTIVITY);
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
        if(homeArticleData!=null && mAdapter!=null){
            mAdapter.setData(position,homeArticleData);
        }
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        //公众号搜索
        if(v.getId() == R.id.iv_search){
            if (mSearchFragment == null) {
                mSearchFragment = SearchFragment.newInstance(Constants.RESULT_CODE_OFFICIAL_ACCOUNTS_PAGE,id,mKnowledgeHierarchyData.getName());
            }
            if (!isDestroyed() && mSearchFragment.isAdded()) {
                mSearchFragment.dismiss();
            }
            mSearchFragment.show(getSupportFragmentManager(),"showOfficialSearch");
        }
    }
}
