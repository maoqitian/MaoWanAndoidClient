package mao.com.mao_wanandroid_client.view.login;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.application.Constants;
import mao.com.mao_wanandroid_client.base.activity.BaseActivity;
import mao.com.mao_wanandroid_client.presenter.login.SignUpContract;
import mao.com.mao_wanandroid_client.presenter.login.SignUpPresenter;
import mao.com.mao_wanandroid_client.utils.EditTextUtils;
import mao.com.mao_wanandroid_client.utils.StartDetailPage;
import mao.com.mao_wanandroid_client.utils.StatusBarUtil;
import mao.com.mao_wanandroid_client.utils.ToolsUtils;

/**
 * @author maoqitian
 * @Description: 注册
 * @date 2019/6/14 0014 10:44
 */
public class SignUpActivity extends BaseActivity<SignUpPresenter> implements SignUpContract.SignUpView, View.OnClickListener {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.edit_sign_up_user_name)
    EditText mUserSignUpName;
    @BindView(R.id.edit_user_sign_up_password)
    EditText mSignUpPassword;
    @BindView(R.id.edit_user_sign_up_repassword)
    EditText mReSignUpPassword;
    @BindView(R.id.bt_sing_up_now)
    Button mSignUp;

    private String userName;
    private String password;
    private String repassword;

    @Override
    protected int getLayout() {
        return R.layout.activity_sign_up_layout;
    }

    @Override
    protected void initToolbar() {
        mToolbar.setTitle(getString(R.string.sign_up));
        mToolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        StatusBarUtil.setColorNoTranslucentLightMode(this, ContextCompat.getColor(this,R.color.colorPrimary));
        mToolbar.setNavigationOnClickListener(v -> finish());
    }

    @Override
    protected void initEventAndData() {
        //监听所有 editText
        EditTextUtils.textChangeListener textChangeListener = new EditTextUtils.textChangeListener(mSignUp);
        textChangeListener.addAllEditText(mUserSignUpName,mSignUpPassword,mReSignUpPassword);
        mSignUp.setEnabled(false);
        mSignUp.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //设置焦点并弹出键盘
        ToolsUtils.showSoftInput(mUserSignUpName);
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
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_sing_up_now ://注册完成登录
                userName = mUserSignUpName.getText().toString().trim();
                password = mSignUpPassword.getText().toString().trim();
                repassword = mReSignUpPassword.getText().toString().trim();
                if(!password.equals(repassword)){
                    Toast.makeText(this,getString(R.string.passwordInconsistency),Toast.LENGTH_SHORT).show();
                }else {
                    //注册
                    mPresenter.getSignUpLogin(this,userName,password,repassword);
                }
                break;
        }
    }

    @Override
    public void showSignUpSuccess() {
        StartDetailPage.start(this,null,Constants.PAGE_MAIN,Constants.ACTION_MAIN_ACTIVITY);
        finish();
    }

    @Override
    public void showSignUpFail(String errorMsg) {
        Toast.makeText(this,errorMsg,Toast.LENGTH_SHORT).show();
    }
}
