package mao.com.mao_wanandroid_client.view.main;

import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.ButtonBarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.SimpleMultiPurposeListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.application.Constants;
import mao.com.mao_wanandroid_client.base.activity.BaseActivity;
import mao.com.mao_wanandroid_client.presenter.main.UserCenterContract;
import mao.com.mao_wanandroid_client.presenter.main.UserCenterPresenter;
import mao.com.mao_wanandroid_client.utils.StatusBarUtil;
import mao.com.mao_wanandroid_client.utils.ToolsUtils;
import mao.com.mao_wanandroid_client.view.drawer.fragment.CollectionFragment;
import mao.com.mao_wanandroid_client.view.drawer.fragment.CollectionWebFragment;
import mao.com.mao_wanandroid_client.view.main.adapter.HomeTabPageAdapter;

/**
 * @author maoqitian
 * @Description 个人中心  SmartRefreshLayout + ViewPager + RecyclerView 动效结合 CoordinatorLayout + AppBarLayout + CollapsingToolbarLayout
 * @Time 2019/7/22 0022 23:22
 */
public class UserCenterActivity extends BaseActivity<UserCenterPresenter> implements UserCenterContract.UserCenterView,AppBarLayout.OnOffsetChangedListener {

    @BindView(R.id.user_toolbar)
    Toolbar mToolBar;
    //toolbar1 状态栏卡住 tablayout 不向上滑动
    @BindView(R.id.toolbar1)
    Toolbar mToolBar1;
    @BindView(R.id.appbar_layout)
    AppBarLayout mAppbarLayout;
    @BindView(R.id.sr_refresh)
    SmartRefreshLayout mSmartRefreshLayout;
    @BindView(R.id.iv_parallax)
    ImageView ivParallax;
    @BindView(R.id.buttonBarLayout)
    ButtonBarLayout mButtonBarLayout;

    @BindView(R.id.tv_nickname)
    TextView tvNickName;
    @BindView(R.id.tv_toolbar_name)
    TextView tvTbNickName;
    @BindView(R.id.tv_user_center_rank)
    TextView tvUserCenterRank;
    @BindView(R.id.tv_user_center_coin)
    TextView tvUserCenterCoin;


    @BindView(R.id.collection_tab)
    TabLayout mCollectionTab;
    @BindView(R.id.collection_view_pager)
    ViewPager mViewPager;

    List<String> mTitle;
    List<Fragment> mFragments;

    private int mOffset = 0;
    private int mScrollY = 0;

    HomeTabPageAdapter mAdapter;

    @Override
    protected void initToolbar() {
        mToolBar.setNavigationOnClickListener(v -> finish());
        // 状态栏透明并加入状态栏宽度
        StatusBarUtil.setTranslucentForImageView(this,0,mToolBar);
        StatusBarUtil.setTranslucentForImageView(this,0,mToolBar1);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_user_center_layout;
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        tvNickName.setText(mPresenter.getLoginUserName());
        tvTbNickName.setText(mPresenter.getLoginUserName());
        refreshListener();
        mAppbarLayout.addOnOffsetChangedListener(this);
        mToolBar.setBackgroundColor(0);
        initCollectionView();
        mPresenter.getCoinAndRank();
    }

    @Override
    public void showCoinAndRank(int coin) {
        tvUserCenterCoin.setText("积分："+coin);
        int mCoin = coin;
        if(mCoin>100){
            mCoin = (coin-(coin%100))/100+1;
        }else {
            mCoin = 1;
        }
        tvUserCenterRank.setText("lv "+mCoin);
    }

    private void refreshListener() {
        mSmartRefreshLayout.setOnMultiPurposeListener(new SimpleMultiPurposeListener(){
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(3000);
                mPresenter.getCoinAndRank();
                mAdapter.notifyDataSetChanged();
                ToolsUtils.setIndicatorWidth(mCollectionTab,getResources().getDimensionPixelSize(R.dimen.dp_30));
            }

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore(2000);
            }
            //处理头部下拉刷新
            @Override
            public void onHeaderMoving(RefreshHeader header, boolean isDragging, float percent, int offset, int headerHeight, int maxDragHeight) {
                mOffset = offset / 2;
                ivParallax.setTranslationY(mOffset - mScrollY);
                mToolBar.setAlpha(1 - Math.min(percent, 1));
            }
        });
    }

    private void initCollectionView() {
        mTitle = new ArrayList<>();
        mFragments = new ArrayList<>();
        mTitle.add(getString(R.string.collection_article));
        mTitle.add(getString(R.string.collection_web));
        mFragments.add(CollectionFragment.newInstance(Constants.COLLECTION_NOT_REFRESH_TYPE));
        mFragments.add(CollectionWebFragment.newInstance(Constants.COLLECTION_NOT_REFRESH_TYPE));

        //下划线间距
        ToolsUtils.setIndicatorWidth(mCollectionTab,getResources().getDimensionPixelSize(R.dimen.dp_30));
        mAdapter = new HomeTabPageAdapter(getSupportFragmentManager(),mTitle,mFragments);
        mViewPager.setAdapter(mAdapter);
        mCollectionTab.setupWithViewPager(mViewPager);
    }


    //头图 toolbar 动效处理
    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        //获取最大偏移值
        int scrollRangle = appBarLayout.getTotalScrollRange();
        ivParallax.setTranslationY(verticalOffset);
        /**
         * 这个数值可以自己定义 显示 toolbar 用户名称
         */
        if (verticalOffset < -250) {
            tvTbNickName.setVisibility(View.VISIBLE);
        } else {
            tvTbNickName.setVisibility(View.GONE);
        }
        int mAlpha = (int) Math.abs(255f / scrollRangle * verticalOffset);
        int color = ContextCompat.getColor(appBarLayout.getContext(), R.color.colorPrimary)&0x00ffffff;
        StatusBarUtil.setColorNoTranslucentLightMode(this,((mAlpha) << 24) | color);
        //顶部title渐变效果
        mToolBar.setBackgroundColor(((mAlpha) << 24) | color);
    }
}
