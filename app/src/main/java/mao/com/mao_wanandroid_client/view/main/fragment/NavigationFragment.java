package mao.com.mao_wanandroid_client.view.main.fragment;

import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.base.fragment.RootBaseFragment;
import mao.com.mao_wanandroid_client.model.modelbean.navigation.NavigationListData;
import mao.com.mao_wanandroid_client.presenter.main.NavigationContract;
import mao.com.mao_wanandroid_client.presenter.main.NavigationPresenter;
import mao.com.mao_wanandroid_client.view.main.adapter.NavigationViewAdapter;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;
import q.rorbin.verticaltablayout.widget.TabView;

/**
 * @author maoqitian
 * @Description 导航  VerticalTabLayout +  recyclerView 联动
 * 1、监听recyclerView滑动到的位置，tablayout切换到对应标签
 * 2、tablayout各标签点击，recyclerView可滑动到对应区域
 * @Time 2019/5/8 0008 23:33
 */
public class NavigationFragment extends RootBaseFragment<NavigationPresenter> implements NavigationContract.NavigationView {


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


    private VerticalTabLayout.OnTabSelectedListener tabSelectedListener;
    private RecyclerView.OnScrollListener onScrollListener;
    
    //用于recyclerView滑动到指定的位置
    private boolean canScroll;
    //是否点击了 Tab
    private boolean isClickTab;
    //指向位置
    private int indexPosition;
    //VerticalTabLayout 点击获取位置让 RecycleView滑动到相应位置
    private int scrollToPosition;

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

    }

    private void VerticalTabLayoutWithRecyclerView() {
         onScrollListener =new RecyclerView.OnScrollListener() {
            // RecyclerView 滚动状态变化时回调
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (canScroll && (newState == RecyclerView.SCROLL_STATE_IDLE)) {
                    canScroll = false;
                    //moveToPosition(layoutManager, recyclerView, scrollToPosition);
                    RecyclerViewSmoothScroll();
                }
                //RecyclerView 滑动关联 VerticalTabLayout
                RecyclerViewWithTabLayout(newState);
            }
            // RecyclerView 滚动时回调
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(canScroll){
                    canScroll = false;
                    RecyclerViewSmoothScroll();
                }
            }
        };
        tabSelectedListener = new VerticalTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabView tab, int position) {
                isClickTab = false;
                //点击标签，使recyclerView滑动
                moveToPosition(layoutManager, mRecyclerView, position);
            }

            @Override
            public void onTabReselected(TabView tab, int position) {

            }
        };
        mRecyclerView.addOnScrollListener(onScrollListener);
        verticalTabLayout.addOnTabSelectedListener(tabSelectedListener);
    }
    //滑动到对应位置
    private void RecyclerViewSmoothScroll() {
        int indexPositionDistance = scrollToPosition - layoutManager.findFirstVisibleItemPosition();
        if (indexPositionDistance >= 0 && indexPositionDistance < mRecyclerView.getChildCount()) {
            int top = mRecyclerView.getChildAt(indexPositionDistance).getTop();
            mRecyclerView.smoothScrollBy(0, top);
        }
    }

    private void RecyclerViewWithTabLayout(int newState) {
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            if (isClickTab) {
                //点击tab 不执行以下操作
                isClickTab = false;
                return;
            }
            int firstPosition = layoutManager.findFirstVisibleItemPosition();
            if (indexPosition != firstPosition) {
                indexPosition = firstPosition;
                //使 TabLayout 选择正确的item
                setTabLayoutChecked(indexPosition);
            }
        }
    }

    private void setTabLayoutChecked(int position) {
        if (isClickTab) {
            isClickTab = false;
        } else {
            if (verticalTabLayout == null) {
                return;
            }
            verticalTabLayout.setTabSelected(indexPosition);
        }
        indexPosition = position;
    }
    //VerticalTabLayout 点击使 RecyclerView 滑动到对应位置
    private void moveToPosition(LinearLayoutManager layoutManager, RecyclerView recyclerView, int position) {
        // 第一个可见的view的位置
        int firstItem = layoutManager.findFirstVisibleItemPosition();
        // 最后一个可见的view的位置
        int lastItem = layoutManager.findLastVisibleItemPosition();
        if (position <= firstItem) {
            // 如果跳转位置firstItem 之前(滑出屏幕的情况)，就smoothScrollToPosition可以直接跳转，
            recyclerView.smoothScrollToPosition(position);
        } else if (position <= lastItem) {
            // 跳转位置在firstItem 之后，lastItem 之间（显示在当前屏幕），smoothScrollBy来滑动到指定位置
            int top = recyclerView.getChildAt(position - firstItem).getTop();
            recyclerView.smoothScrollBy(0, top);
        } else {
            // 如果要跳转的位置在lastItem 之后，则先调用smoothScrollToPosition将要跳转的位置滚动到可见位置
            // 再通过onScrollStateChanged控制再次调用当前moveToPosition方法，执行上一个判断中的方法
            recyclerView.smoothScrollToPosition(position);
            scrollToPosition = position;
            canScroll = true;
        }
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
        showLoading();
        mPresenter.getNavigationData();
    }


    @Override
    public void showNavigationListData(List<NavigationListData> navigationListData) {
        showNormal();
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
                        .setTextColor(ContextCompat.getColor(_mActivity,R.color.textColorPress), ContextCompat.getColor(_mActivity,R.color.textColorPrimary))
                        .build();
            }

            @Override
            public int getBackground(int position) {
                return -1;
            }
        });
        mAdapter.replaceData(mNavigationListData);
        VerticalTabLayoutWithRecyclerView();
    }

    @Override
    public void reload() {
        showLoading();
        mPresenter.getNavigationData();
    }
}
