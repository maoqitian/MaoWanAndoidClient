package mao.com.mao_wanandroid_client.view.main.fragment;

import android.content.ContextWrapper;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.base.fragment.BaseFragment;
import mao.com.mao_wanandroid_client.base.fragment.RootBaseFragment;
import mao.com.mao_wanandroid_client.model.navigation.NavigationListData;
import mao.com.mao_wanandroid_client.presenter.main.NavigationContract;
import mao.com.mao_wanandroid_client.presenter.main.NavigationPresenter;
import mao.com.mao_wanandroid_client.view.main.adapter.NavigationViewAdapter;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;
import q.rorbin.verticaltablayout.widget.TabView;

/**
 * @author maoqitian
 * @Description 导航
 * @Time 2019/5/8 0008 23:33
 */
public class NavigationFragment  extends BaseFragment<NavigationPresenter> implements NavigationContract.NavigationView {


    @BindView(R.id.vertical_tab)
    VerticalTabLayout verticalTabLayout;
    @BindView(R.id.nav_recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.nav_smart_refresh)
    SmartRefreshLayout mSmartRefreshLayout;

    private LinearLayoutManager layoutManager;
    List<NavigationListData> mNavigationListData;

    NavigationViewAdapter mAdapter;

    //下拉刷新头部
    private MaterialHeader mMaterialHeader;

    private int currentPosition = 0;
    private VerticalTabLayout.OnTabSelectedListener tabSelectedListener;

    private boolean isClick = false;

    @Override
    protected int getLayoutId() {
        return R.layout.navigation_fragment_layout;
    }

    @Override
    protected void initView() {
        initRecycleView();
    }

    private void initRecycleView() {
        mNavigationListData = new ArrayList<>();
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(_mActivity);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new NavigationViewAdapter(R.layout.nav_item_cardview_layout);
        mRecyclerView.setAdapter(mAdapter);
        SmartRefreshLayoutListener();
        VerticalTabLayoutWithRecyclerView();
    }

    private int recyclerHeight;

    private void VerticalTabLayoutWithRecyclerView() {
        tabSelectedListener = new VerticalTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabView tab, int position) {
                isClick = true;
                LinearLayoutManager l = (LinearLayoutManager) mRecyclerView.getLayoutManager();
                int firstPosition = l.findFirstVisibleItemPosition();
                int lastPosition = l.findLastVisibleItemPosition();
                if (position > lastPosition) {
                    mRecyclerView.smoothScrollToPosition(position);
                } else if (position < firstPosition) {
                    mRecyclerView.smoothScrollToPosition(position);
                } else {
                    int top = mRecyclerView.getChildAt(position - firstPosition).getTop();
                    mRecyclerView.smoothScrollBy(0, top);
                }
            }

            @Override
            public void onTabReselected(TabView tab, int position) {

            }
        };
        RecyclerView.OnScrollListener onScrollListener =new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == 0) {
                    isClick = false;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!isClick) {
                    LinearLayoutManager l = (LinearLayoutManager) recyclerView.getLayoutManager();
                    int firstPosition = l.findFirstVisibleItemPosition();
                    int lastPosition = l.findLastVisibleItemPosition();
                    int allItems = l.getItemCount();
                    verticalTabLayout.setVerticalScrollbarPosition(firstPosition);
                    Log.i("zkd", "[TablayoutWithRecyclerActivity][onScrollStateChanged]==> firstPosition : " + firstPosition + ",\nlastPosition:" + lastPosition + ",\nall:" + allItems);
                }
            }
        };
        verticalTabLayout.addOnTabSelectedListener(tabSelectedListener);
        mRecyclerView.addOnScrollListener(onScrollListener);
    }

    private void SmartRefreshLayoutListener() {
        mMaterialHeader = (MaterialHeader)mSmartRefreshLayout.getRefreshHeader();
        //拖动Header的时候是否同时拖动内容（默认true）
        mSmartRefreshLayout.setEnableHeaderTranslationContent(false);
        mMaterialHeader.setColorSchemeResources(R.color.colorPrimary,android.R.color.holo_green_light,android.R.color.holo_red_light,android.R.color.holo_blue_light);
        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mPresenter.getNavigationData();
                refreshLayout.autoRefresh();
            }
        });
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        //Log.e("毛麒添","当前页面状态"+currentState);
        mSmartRefreshLayout.autoRefresh();
        mPresenter.getNavigationData();
    }


    @Override
    public void showNavigationListData(List<NavigationListData> navigationListData) {
        mSmartRefreshLayout.finishRefresh();
        mNavigationListData.clear();
        mNavigationListData.addAll(navigationListData);
        verticalTabLayout.setTabAdapter(new TabAdapter() {
            @Override
            public int getCount() {
                return navigationListData.size();
            }

            @Override
            public ITabView.TabBadge getBadge(int position) {
                return null;
            }

            @Override
            public ITabView.TabIcon getIcon(int position) {
                return null;
            }

            @Override
            public ITabView.TabTitle getTitle(int position) {
                return new ITabView.TabTitle.Builder()
                        .setContent(mNavigationListData.get(position).getName())
                        .setTextColor(ContextCompat.getColor(_mActivity,R.color.colorPrimaryDark), ContextCompat.getColor(_mActivity,R.color.color_white))
                        .build();
            }

            @Override
            public int getBackground(int position) {
                return -1;
            }
        });
        mAdapter.replaceData(mNavigationListData);
    }


}
