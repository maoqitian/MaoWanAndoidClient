package mao.com.mao_wanandroid_client.view.main.fragment;

import android.util.Log;

import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.base.fragment.BaseFragment;
import mao.com.mao_wanandroid_client.base.fragment.RootBaseFragment;
import mao.com.mao_wanandroid_client.presenter.main.OfficialAccountsContract;
import mao.com.mao_wanandroid_client.presenter.main.OfficialAccountsPresenter;

/**
 * @author maoqitian
 * @Description 公众号 page
 * @Time 2019/5/8 0008 23:19
 */
public class OfficialAccountsPageFragment extends BaseFragment<OfficialAccountsPresenter> implements OfficialAccountsContract.OfficialAccountsView {

    @Override
    protected int getLayoutId() {
        return R.layout.official_accounts_fragment_layout;
    }
    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        //Log.e("毛麒添","当前页面状态"+currentState);
    }
}
