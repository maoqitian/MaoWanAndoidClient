package mao.com.mao_wanandroid_client.view.main.fragment;

import android.util.Log;

import com.bigkoo.convenientbanner.listener.OnItemClickListener;

import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.base.fragment.RootBaseFragment;
import mao.com.mao_wanandroid_client.presenter.main.HomeFirstTabPresenter;
import mao.com.mao_wanandroid_client.presenter.main.HomePageFirstTabContract;
import mao.com.mao_wanandroid_client.presenter.main.HomePageSecondTabContract;
import mao.com.mao_wanandroid_client.presenter.main.HomeSecondTabPresenter;

/**
 * @author maoqitian
 * @Description 首页第二个 tab Fragment (最新项目)
 * @Time 2019/5/21 0021 21:06
 */
public class HomeSecondTabFragment extends RootBaseFragment<HomeSecondTabPresenter>
        implements HomePageSecondTabContract.HomePageSecondTabView{

    @Override
    protected int getLayoutId() {
        return R.layout.home_second_tab_fragment_layout;
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        Log.e("毛麒添","HomeSecondTabFragment 当前页面状态"+currentState);
    }
}
