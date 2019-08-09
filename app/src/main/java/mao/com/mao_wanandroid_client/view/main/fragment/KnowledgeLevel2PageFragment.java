package mao.com.mao_wanandroid_client.view.main.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.application.Constants;
import mao.com.mao_wanandroid_client.base.fragment.RootBaseFragment;
import mao.com.mao_wanandroid_client.model.modelbean.home.HomeArticleData;
import mao.com.mao_wanandroid_client.model.modelbean.home.HomeArticleListData;
import mao.com.mao_wanandroid_client.presenter.main.Level2PageContract;
import mao.com.mao_wanandroid_client.presenter.main.Level2PagePresenter;
import mao.com.mao_wanandroid_client.utils.StartDetailPage;
import mao.com.mao_wanandroid_client.view.main.adapter.HomePageAdapter;

/**
 * @author maoqitian
 * @Description
 * @Time 2019/6/30 0030 14:02
 */
public class KnowledgeLevel2PageFragment extends RootBaseFragment<Level2PagePresenter>
        implements Level2PageContract.Level2PageView,
        BaseQuickAdapter.OnItemClickListener {


    @BindView(R.id.inflate_view)
    SmartRefreshLayout smartRefreshLayout;

    @BindView(R.id.level2_page_recyclerview)
    RecyclerView mRecyclerView;

    private RecyclerView.LayoutManager layoutManager;
    private HomePageAdapter mAdapter;
    List<HomeArticleData> homeArticleDataList;

    int superChapterId;

    //下拉刷新头部
    private MaterialHeader mMaterialHeader;

    public static KnowledgeLevel2PageFragment newInstance(int cid) {

        Bundle args = new Bundle();
        args.putInt(Constants.BUNDLE_TAG_SUPER_CID,cid);
        KnowledgeLevel2PageFragment fragment = new KnowledgeLevel2PageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.level2_page_fragment_layout;
    }

    @Override
    protected void initView() {
        mMaterialHeader = (MaterialHeader)smartRefreshLayout.getRefreshHeader();
        //拖动Header的时候是否同时拖动内容（默认true）
        smartRefreshLayout.setEnableHeaderTranslationContent(false);
        mMaterialHeader.setColorSchemeResources(R.color.colorPrimary,android.R.color.holo_green_light,android.R.color.holo_red_light,android.R.color.holo_blue_light);
        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(_mActivity);
        mRecyclerView.setLayoutManager(layoutManager);
        // specify an adapter
        homeArticleDataList = new ArrayList<>();
        mAdapter = new HomePageAdapter(R.layout.article_item_cardview_layout);
        mAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(mAdapter);
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
        setSmartRefreshLayoutListener();
    }

    private void setSmartRefreshLayoutListener() {
        smartRefreshLayout.setOnRefreshListener(refreshLayout -> {
            Log.e("毛麒添","下拉加载");
            mPresenter.getRefreshPage(superChapterId);
            refreshLayout.autoRefresh();
        });
        //加载更多
        smartRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            Log.e("毛麒添","加载更多");
            mPresenter.getLoadMorePage(superChapterId);
            refreshLayout.autoLoadMore();
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

    @Override
    protected void initEventAndData() {
        if(getArguments()!=null){
          superChapterId = getArguments().getInt(Constants.BUNDLE_TAG_SUPER_CID);
        }
        mPresenter.getSuperChapterArticleData(superChapterId);
        smartRefreshLayout.autoRefresh();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        HomeArticleData homeArticleData = (HomeArticleData) adapter.getItem(position);
        StartDetailPage.start(_mActivity,homeArticleData, Constants.PAGE_WEB_COLLECT,Constants.ACTION_PAGE_DETAIL_ACTIVITY);
    }

    @Override
    public void ShowSuperChapterArticle(boolean isRefresh, HomeArticleListData mHomeArticleListData) {
        if(isRefresh){
            //下拉刷新 或者 第一次加载
            homeArticleDataList.clear();
            homeArticleDataList.addAll(mHomeArticleListData.getDatas());
            mAdapter.replaceData(homeArticleDataList);
        }else {
            //加载更多
            mAdapter.addData(mHomeArticleListData.getDatas());
        }
        smartRefreshLayout.finishRefresh();
        smartRefreshLayout.finishLoadMore();
    }

    @Override
    public void showAddArticleCollectStatus(int position, HomeArticleData homeArticleData, String msg) {
        showCollectStatus(position,homeArticleData,msg);
    }

    @Override
    public void showCancelArticleCollectStatus(int position, HomeArticleData homeArticleData, String msg) {
        smartRefreshLayout.finishLoadMore();
        showCollectStatus(position,homeArticleData,msg);
    }

    @Override
    public void showLoadDataMessage(String msg) {
        smartRefreshLayout.finishLoadMore();
        Toast.makeText(_mActivity,msg,Toast.LENGTH_SHORT).show();
    }

    //显示收藏 或取消 收藏之后的状态
    private void showCollectStatus(int position,HomeArticleData homeArticleData,String msg){
        if(homeArticleData!=null && mAdapter!=null){
            mAdapter.setData(position,homeArticleData);
        }
        Toast.makeText(_mActivity,msg,Toast.LENGTH_SHORT).show();
    }
}
