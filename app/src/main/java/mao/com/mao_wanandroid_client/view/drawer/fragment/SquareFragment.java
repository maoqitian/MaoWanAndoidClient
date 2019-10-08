package mao.com.mao_wanandroid_client.view.drawer.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.application.Constants;
import mao.com.mao_wanandroid_client.base.fragment.BaseFragment;
import mao.com.mao_wanandroid_client.base.fragment.RootBaseFragment;
import mao.com.mao_wanandroid_client.model.modelbean.home.HomeArticleData;
import mao.com.mao_wanandroid_client.presenter.drawer.SquareContract;
import mao.com.mao_wanandroid_client.presenter.drawer.SquarePresenter;
import mao.com.mao_wanandroid_client.utils.StartDetailPage;
import mao.com.mao_wanandroid_client.utils.ToastUtils;
import mao.com.mao_wanandroid_client.view.main.adapter.HomePageAdapter;

/**
 * @Description: 广场
 * @Author: maoqitian
 * @Date: 2019-10-04 13:01
 */
public class SquareFragment extends RootBaseFragment<SquarePresenter> implements
        SquareContract.SquareView,
        BaseQuickAdapter.OnItemClickListener {


    @BindView(R.id.inflate_view)
    SmartRefreshLayout mSmartRefreshLayout;
    @BindView(R.id.square_recyclerview)
    RecyclerView mRecyclerView;

    private RecyclerView.LayoutManager layoutManager;
    private HomePageAdapter mAdapter;

    List<HomeArticleData> mHomeArticleDataList;

    //下拉刷新头部
    private MaterialHeader mMaterialHeader;


    public static SquareFragment newInstance() {

        Bundle args = new Bundle();

        SquareFragment fragment = new SquareFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void initView() {
        mMaterialHeader = (MaterialHeader)mSmartRefreshLayout.getRefreshHeader();
        //拖动Header的时候是否同时拖动内容（默认true）
        mSmartRefreshLayout.setEnableHeaderTranslationContent(false);
        mMaterialHeader.setColorSchemeResources(R.color.colorPrimary,android.R.color.holo_green_light,android.R.color.holo_red_light,android.R.color.holo_blue_light);

        mHomeArticleDataList =new ArrayList<>();
        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(_mActivity);
        mRecyclerView.setLayoutManager(layoutManager);

        mAdapter = new HomePageAdapter(R.layout.article_item_cardview_layout);
        mAdapter.setUserShare(true);

        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
        setHomePageItemClickListener();
        //下拉刷新
        setSmartRefreshLayoutListener();
    }

    private void setSmartRefreshLayoutListener() {
        mSmartRefreshLayout.setOnRefreshListener(refreshLayout -> {
            Log.e("毛麒添","下拉刷新");
            mPresenter.getSquareArticleList();
            refreshLayout.autoRefresh();
        });
        //加载更多
        mSmartRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            Log.e("毛麒添","加载更多");
            mPresenter.getLoadSquareArticleListData();
            refreshLayout.autoLoadMore();
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
                    StartDetailPage.start(_mActivity,homeArticleData, Constants.RESULT_CODE_HOME_PAGE,Constants.ACTION_KNOWLEDGE_LEVEL2_ACTIVITY);
                    break;

                case R.id.tv_author_name:
                case R.id.image_author_icon:
                    ToastUtils.showToast("点击了广场用户信息");
                    Intent intent = new Intent(Constants.ACTION_USER_CENTER_ACTIVITY);
                    intent.putExtra(Constants.USER_ID,homeArticleData.getUserId());
                    intent.putExtra(Constants.PAGE_TYPE,Constants.SQUARE_USER_TYPE);
                    startActivity(intent);
                    break;
            }
        });
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        showLoading();
        mPresenter.getSquareArticleList();

    }



    @Override
    protected int getLayoutId() {
        return R.layout.square_fragment_layout;
    }

    @Override
    public void showSquareArticleData(boolean isLoadData, List<HomeArticleData> datas) {

        if(isLoadData){
            mAdapter.addData(datas);
        }else {
            mHomeArticleDataList.clear();
            mHomeArticleDataList.addAll(datas);
            mAdapter.replaceData(mHomeArticleDataList);
            showNormal();
        }
        mSmartRefreshLayout.finishLoadMore();
        mSmartRefreshLayout.finishRefresh();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        HomeArticleData homeArticleData = (HomeArticleData) adapter.getItem(position);
        StartDetailPage.start(_mActivity,homeArticleData, Constants.PAGE_WEB_COLLECT,Constants.ACTION_PAGE_DETAIL_ACTIVITY);
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
        ToastUtils.showToast(msg);
    }

    @Override
    public void showCancelArticleCollectStatus(int position, HomeArticleData homeArticleData,String msg) {
        showCollectStatus(position,homeArticleData,msg);
    }

    @Override
    public void reload() {
        super.reload();
        mPresenter.getSquareArticleList();
    }

    @Override
    public void showError() {
        super.showError();
        mSmartRefreshLayout.finishLoadMore();
        mSmartRefreshLayout.finishRefresh();
    }
}
