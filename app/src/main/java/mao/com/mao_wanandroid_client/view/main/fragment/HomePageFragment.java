package mao.com.mao_wanandroid_client.view.main.fragment;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;

import butterknife.BindView;
import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.base.fragment.RootBaseFragment;
import mao.com.mao_wanandroid_client.model.banner.HomePageBannerModel;
import mao.com.mao_wanandroid_client.presenter.main.HomePageContract;
import mao.com.mao_wanandroid_client.presenter.main.HomePagePresenter;
import mao.com.mao_wanandroid_client.presenter.main.MainContract;
import mao.com.mao_wanandroid_client.presenter.main.MainPresenter;
import me.yokeyword.fragmentation.ISupportFragment;

/**
 * @author maoqitian
 * @Description 首页Fragment
 * @Time 2019/5/4 0004 16:47
 */
public class HomePageFragment extends RootBaseFragment<HomePagePresenter>
        implements HomePageContract.HomePageView,
        OnItemClickListener {

    @BindView(R.id.view_base_normal)
    private SmartRefreshLayout mSmartRefreshLayout;
    @BindView(R.id.home_page_recyclerview)
    private RecyclerView mRecyclerView;
    //轮播图控件
    private ConvenientBanner<HomePageBannerModel> mConvenientBanner;

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
        Log.e("毛麒添","当前页面状态"+currentState);
        showLoading();
    }


    @Override
    public void showHomePageBanner(List<HomePageBannerModel> bannerModelList) {
       showNormal();
       Log.e("毛麒添","首页banner 数据 "+bannerModelList.toString());
    }

    //ConvenientBanner item 点击回调
    @Override
    public void onItemClick(int position) {
        
    }
}
