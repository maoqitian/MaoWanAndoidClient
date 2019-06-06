package mao.com.mao_wanandroid_client.view.login;

import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;

import butterknife.BindView;
import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.base.activity.BaseActivity;
import mao.com.mao_wanandroid_client.presenter.login.LoginContract;
import mao.com.mao_wanandroid_client.presenter.login.LoginPresenter;

/**
 * @author maoqitian
 * @Description: 登录
 * @date 2019/6/6 0006 11:14
 */
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.LoginView {


    @BindView(R.id.toolbar)
    Toolbar mToolbar;


    @Override
    protected void initToolbar() {
        mToolbar.setTitle(getString(R.string.signin));
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_login_layout;
    }

}
