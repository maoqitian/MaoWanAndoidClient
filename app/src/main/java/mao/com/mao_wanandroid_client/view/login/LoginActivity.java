package mao.com.mao_wanandroid_client.view.login;

import android.graphics.Color;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

    @BindView(R.id.edit_user_name)
    EditText mUserName;
    @BindView(R.id.edit_user_password)
    EditText mPassword;
    @BindView(R.id.bt_sing_in)
    Button mLogin;
    @BindView(R.id.bt_sing_up)
    Button mRegister;

    @Override
    protected void initToolbar() {
        mToolbar.setTitle(getString(R.string.signin));
        mToolbar.setTitleTextColor(Color.WHITE);
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

    @Override
    protected void initEventAndData() {
      mLogin.setBackgroundResource(R.drawable.button_shape_gray_bg);
      mLogin.setEnabled(false);
    }

    @Override
    public void showLoginSuccess() {

    }
}
