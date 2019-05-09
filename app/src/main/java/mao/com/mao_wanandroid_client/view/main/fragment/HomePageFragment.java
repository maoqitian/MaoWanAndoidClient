package mao.com.mao_wanandroid_client.view.main.fragment;

import android.util.Log;

import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.base.fragment.RootBaseFragment;
import mao.com.mao_wanandroid_client.presenter.main.MainContract;
import mao.com.mao_wanandroid_client.presenter.main.MainPresenter;
import me.yokeyword.fragmentation.ISupportFragment;

/**
 * @author maoqitian
 * @Description 首页Fragment
 * @Time 2019/5/4 0004 16:47
 */
public class HomePageFragment extends RootBaseFragment<MainPresenter> implements MainContract.MainView {


    public static HomePageFragment newInstance() {
        return new HomePageFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.home_page_fragment_layout;
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        Log.e("毛麒添","当前页面状态"+currentState);
        showLoading();
    }
}
