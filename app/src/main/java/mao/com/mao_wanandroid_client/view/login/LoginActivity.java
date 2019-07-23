package mao.com.mao_wanandroid_client.view.login;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.application.Constants;
import mao.com.mao_wanandroid_client.base.activity.BaseActivity;
import mao.com.mao_wanandroid_client.presenter.login.LoginContract;
import mao.com.mao_wanandroid_client.presenter.login.LoginPresenter;
import mao.com.mao_wanandroid_client.utils.EditTextUtils;
import mao.com.mao_wanandroid_client.utils.StartDetailPage;
import mao.com.mao_wanandroid_client.utils.StatusBarUtil;
import mao.com.mao_wanandroid_client.utils.ToolsUtils;

/**
 * @author maoqitian
 * @Description: 登录
 * @date 2019/6/6 0006 11:14
 */
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.LoginView, View.OnClickListener {


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

    private String userName;
    private String password;

    @Override
    protected void initToolbar() {
        mToolbar.setTitle(getString(R.string.signin));
        mToolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        StatusBarUtil.setColorNoTranslucentLightMode(this, ContextCompat.getColor(this,R.color.colorPrimary));
        mToolbar.setNavigationOnClickListener(v -> finish());
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_login_layout;
    }

    @Override
    protected void initEventAndData() {
        listenerEditText();
        //设置 登陆按钮开始不能点击
        mLogin.setEnabled(false);
        //监听事件
        mLogin.setOnClickListener(this);
        mRegister.setOnClickListener(this);
    }
    //监听 editText 状态
    private void listenerEditText() {
        EditTextUtils.textChangeListener textChangeListener = new EditTextUtils.textChangeListener(mLogin);
        textChangeListener.addAllEditText(mUserName,mPassword);
        /*EditTextUtils.setChangeListener(isAllHaveText -> {
            if(isAllHaveText){ //都已经有内容 登陆按钮可以点击
                mLogin.setBackgroundResource(R.drawable.button_shape_normal_bg);
            }else { //有一个editText无内容 登陆按钮不可以点击
                mLogin.setBackgroundResource(R.drawable.button_shape_gray_bg);
            }
        });*/
    }

    @Override
    public void showLoginSuccess() {
        //关闭登录页面
        Toast.makeText(this,getString(R.string.login_success),Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void showLoginFail(String errorMsg) {
        Toast.makeText(this,errorMsg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
       switch (view.getId()){
           case R.id.bt_sing_in: //登陆
               userName = mUserName.getText().toString().trim();
               password = mPassword.getText().toString().trim();
               mPresenter.getPostLogin(this,userName,password);
               Log.e("毛麒添","点击了登陆" );
               break;
           case R.id.bt_sing_up: //注册
               StartDetailPage.start(this,null, Constants.PAGE_SIGN_UP,Constants.ACTION_SIGN_UP_ACTIVITY);
               break;
       }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //设置焦点并弹出键盘
        ToolsUtils.showSoftInput(mUserName);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(ToolsUtils.isSoftShowing(this)){ //如果软键盘没退出
            Log.e("毛麒添","小键盘没关闭" );
            ToolsUtils.hideKeyboard(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
