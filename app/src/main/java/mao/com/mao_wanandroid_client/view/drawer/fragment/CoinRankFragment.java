package mao.com.mao_wanandroid_client.view.drawer.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.base.fragment.BaseDialogFragment;
import mao.com.mao_wanandroid_client.model.modelbean.rank.RankData;
import mao.com.mao_wanandroid_client.presenter.drawer.CoinRankContract;
import mao.com.mao_wanandroid_client.presenter.drawer.CoinRankPresenter;
import mao.com.mao_wanandroid_client.view.drawer.adapter.CoinRankAdapter;

/**
 * @author maoqitian
 * @Description: 积分排行榜
 * @date 2019/9/11 0011 11:39
 */
public class CoinRankFragment extends BaseDialogFragment <CoinRankPresenter> implements CoinRankContract.CoinRankView {


    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tv_page_title)
    TextView mPageTitle;
    @BindView(R.id.coin_rank_recycleview)
    RecyclerView mRecyclerView;
    @BindView(R.id.inflate_view)
    SmartRefreshLayout mSmartRefreshLayout;

    List<RankData> mRankDataList;

    private RecyclerView.LayoutManager layoutManager;
    CoinRankAdapter mAdapter;

    //下拉刷新头部
    private MaterialHeader mMaterialHeader;


    public static CoinRankFragment newInstance() {

        Bundle args = new Bundle();
        CoinRankFragment fragment = new CoinRankFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //dialogFragment全屏
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.DialogFullScreen);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.coin_rank_fragment_layout;
    }

    @Override
    protected void initViewAndData() {
        mRankDataList = new ArrayList<>();
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        mToolbar.setNavigationOnClickListener(v -> dismiss());
        mPageTitle.setText(getString(R.string.coin_rank_text));
        mMaterialHeader = (MaterialHeader)mSmartRefreshLayout.getRefreshHeader();
        //拖动Header的时候是否同时拖动内容（默认true）
        mSmartRefreshLayout.setEnableHeaderTranslationContent(false);
        mMaterialHeader.setColorSchemeResources(R.color.colorPrimary,android.R.color.holo_green_light,android.R.color.holo_red_light,android.R.color.holo_blue_light);
        initRecyclerView();
        mPresenter.getCoinRank();
        if(mPresenter.getLoginStatus()){
           mAdapter.setUserName(mPresenter.getLoginUserName());
        }
    }

    private void initRecyclerView() {
        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new CoinRankAdapter(R.layout.rank_item_cardview_layout);
        mRecyclerView.setAdapter(mAdapter);
        //刷新 加载更多监听
        setSmartRefreshLayoutListener();
    }

    private void setSmartRefreshLayoutListener() {
        mSmartRefreshLayout.setOnRefreshListener(refreshLayout -> {
            Log.e("毛麒添","下拉刷新");
            mPresenter.getCoinRank();
            refreshLayout.autoRefresh();
        });
        //加载更多
        mSmartRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            Log.e("毛麒添","加载更多");
            mPresenter.getLoadMoreRankData();
            refreshLayout.autoLoadMore();
        });
    }

    @Override
    public void showCoinRankData(List<RankData> rankDataList,boolean isRefresh) {
        if(isRefresh){
            mAdapter.addData(rankDataList);
        }else {
            mRankDataList.clear();
            mRankDataList.addAll(rankDataList);
            mAdapter.replaceData(mRankDataList);
        }
        finishRefreshLoad();
    }

    private void finishRefreshLoad() {
        mSmartRefreshLayout.finishRefresh();
        mSmartRefreshLayout.finishLoadMore();
    }

    @Override
    public void showErrorMsg(String errorMsg) {
        super.showErrorMsg(errorMsg);
        finishRefreshLoad();
    }
}
