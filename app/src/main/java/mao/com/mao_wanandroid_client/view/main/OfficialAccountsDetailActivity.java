package mao.com.mao_wanandroid_client.view.main;

import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.base.activity.BaseActivity;
import mao.com.mao_wanandroid_client.presenter.main.OfficialAccountsDetailContract;
import mao.com.mao_wanandroid_client.presenter.main.OfficialAccountsDetailPresenter;

/**
 * @author maoqitian
 * @Description: 公众号详情页
 * @date 2019/7/15 0015 17:39
 */
public class OfficialAccountsDetailActivity extends BaseActivity<OfficialAccountsDetailPresenter> implements
        OfficialAccountsDetailContract.OfficialAccountsDetailView {


    @Override
    protected int getLayout() {
        return R.layout.activity_official_accounts_detial_layout;
    }
}
