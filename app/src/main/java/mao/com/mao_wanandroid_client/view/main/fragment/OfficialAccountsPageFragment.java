package mao.com.mao_wanandroid_client.view.main.fragment;

import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.base.fragment.RootBaseFragment;
import mao.com.mao_wanandroid_client.presenter.main.OfficialAccountsContract;
import mao.com.mao_wanandroid_client.presenter.main.OfficialAccountsPresenter;

/**
 * @author maoqitian
 * @Description 公众号 page
 * @Time 2019/5/8 0008 23:19
 */
public class OfficialAccountsPageFragment extends RootBaseFragment<OfficialAccountsPresenter> implements OfficialAccountsContract.OfficialAccountsView {

    @Override
    protected int getLayoutId() {
        return R.layout.official_accounts_fragment_layout;
    }
}
