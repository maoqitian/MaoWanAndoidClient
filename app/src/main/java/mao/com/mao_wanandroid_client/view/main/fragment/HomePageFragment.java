package mao.com.mao_wanandroid_client.view.main.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
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
import mao.com.mao_wanandroid_client.base.fragment.BaseFragment;
import mao.com.mao_wanandroid_client.base.fragment.RootBaseFragment;
import mao.com.mao_wanandroid_client.model.banner.HomePageBannerModel;
import mao.com.mao_wanandroid_client.presenter.main.HomePageContract;
import mao.com.mao_wanandroid_client.presenter.main.HomePagePresenter;
import mao.com.mao_wanandroid_client.utils.ToolsUtils;
import mao.com.mao_wanandroid_client.view.main.adapter.HomePageAdapter;
import mao.com.mao_wanandroid_client.view.main.adapter.HomeTabPageAdapter;
import mao.com.mao_wanandroid_client.view.main.hloder.BannerHolderView;
import mao.com.mao_wanandroid_client.widget.EnhanceTabLayout;


/**
 * @author maoqitian
 * @Description 首页Fragment
 * @Time 2019/5/4 0004 16:47
 */
public class HomePageFragment extends BaseFragment<HomePagePresenter>
        implements HomePageContract.HomePageView{

    @BindView(R.id.home_tab)
    TabLayout mHomeTab;
   /* @BindView(R.id.enhance_tabLayout)
    EnhanceTabLayout mEhomeTab;*/
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    List<String> mTitle;
    List<Fragment> mFragments;

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
    }


    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        Log.e("毛麒添","HomePageFragment initEventAndData");
        //showLoading();
        //initPage();
    }

    private void initPage() {
        mTitle = new ArrayList<>();
        mFragments = new ArrayList<>();
        mTitle.add(getString(R.string.page_home_recommend));
        mTitle.add(getString(R.string.latest_project));

        for (String title:mTitle) {
            mFragments.add(HomeFirstTabFragment.newInstance(title));
        }
        ToolsUtils.setIndicatorWidth(mHomeTab,160);
       /* mEhomeTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        for(int i=0;i<mTitle.size();i++){
            mEhomeTab.addTab(mTitle.get(i));
        }*/
        // 在activity中使用时创建对象需传入getSupportFragmentManager()作为参数，
        // 在fragment中使用时需要传入getChildFragmentManager()作为参数
        mViewPager.setAdapter(new HomeTabPageAdapter(getChildFragmentManager(),mTitle,mFragments));
        //mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mEhomeTab.getTabLayout()));
        mHomeTab.setupWithViewPager(mViewPager);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        //mConvenientBanner.stopTurning();
    }

    @Override
    public void showHomePageView() {
        initPage();
    }



    /*//ConvenientBanner item 点击回调
    @Override
    public void onItemClick(int position) {
        Toast.makeText(_mActivity,"点击banner ",Toast.LENGTH_SHORT).show();
    }*/
}
