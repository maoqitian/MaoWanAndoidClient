package mao.com.mao_wanandroid_client.view.main.fragment;

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

import java.util.List;

import butterknife.BindView;
import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.base.fragment.RootBaseFragment;
import mao.com.mao_wanandroid_client.model.banner.HomePageBannerModel;
import mao.com.mao_wanandroid_client.presenter.main.HomePageContract;
import mao.com.mao_wanandroid_client.presenter.main.HomePagePresenter;
import mao.com.mao_wanandroid_client.view.main.adapter.HomePageAdapter;
import mao.com.mao_wanandroid_client.view.main.hloder.BannerHolderView;


/**
 * @author maoqitian
 * @Description 首页Fragment
 * @Time 2019/5/4 0004 16:47
 */
public class HomePageFragment extends RootBaseFragment<HomePagePresenter>
        implements HomePageContract.HomePageView,
        OnItemClickListener {

    @BindView(R.id.view_base_normal)
    SmartRefreshLayout mSmartRefreshLayout;
    @BindView(R.id.home_page_recyclerview)
    RecyclerView mRecyclerView;
    //轮播图控件
    ConvenientBanner<HomePageBannerModel> mConvenientBanner;

    private RecyclerView.LayoutManager layoutManager;
    private HomePageAdapter mAdapter;

    public static HomePageFragment newInstance() {
        return new HomePageFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.home_page_fragment_layout;
    }

    @Override
    protected void initView() {
        super.initView();
        initRecyclerView();
    }

    private void initRecyclerView() {
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(_mActivity);
        mRecyclerView.setLayoutManager(layoutManager);
        // specify an adapter
        mAdapter = new HomePageAdapter(R.layout.item_cardview_layout);
        LinearLayout bannerViewLayout = (LinearLayout) LayoutInflater.from(_mActivity).inflate(R.layout.home_banner_view_layout,null);
        mConvenientBanner = bannerViewLayout.findViewById(R.id.convenient_banner);
        bannerViewLayout.removeView(mConvenientBanner);
        mAdapter.addHeaderView(mConvenientBanner);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        Log.e("毛麒添","当前页面状态"+currentState);
        showLoading();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mConvenientBanner.stopTurning();
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
               .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT) //设置指示器的方向
               .setOnItemClickListener(this); // 点击事件
        mConvenientBanner.startTurning();
    }

    //ConvenientBanner item 点击回调
    @Override
    public void onItemClick(int position) {
        Toast.makeText(_mActivity,"点击banner ",Toast.LENGTH_SHORT).show();
    }
}
