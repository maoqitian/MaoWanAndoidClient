package mao.com.mao_wanandroid_client.view.welcome;


import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.application.Constants;
import mao.com.mao_wanandroid_client.base.activity.BaseActivity;
import mao.com.mao_wanandroid_client.presenter.welcome.WelcomeContract;
import mao.com.mao_wanandroid_client.presenter.welcome.WelcomePresenter;
import mao.com.mao_wanandroid_client.utils.ScreenUtils;
import mao.com.mao_wanandroid_client.utils.StartDetailPage;

/**
 * @author maoqitian
 * @Description 闪屏页
 * @Time 2019/2/21 0021 20:24
 */
public class WelcomeActivity extends BaseActivity<WelcomePresenter> implements WelcomeContract.WelcomeView , View.OnClickListener {

    //测试 dagger2
    /*@Inject
    HomePageBannerModel model;*/

    @BindView(R.id.jump_btn)
    Button mJumpbtn;

    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //DaggerMyComponent.create().inJectWelcomeActivity(this);
        Log.e("毛麒添","onCreate()");
        ScreenUtils.hideBottomUIMenu(WelcomeActivity.this);
        mJumpbtn.setOnClickListener(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    public void jumpToMainActivity() {
        countDownTimer =new MyCountDownTimer(5000+200,1000);
        countDownTimer.start();
        mJumpbtn.setVisibility(View.VISIBLE);
    }

    private void goMainActivity() {
        StartDetailPage.start(this,null, Constants.PAGE_MAIN,Constants.ACTION_MAIN_ACTIVITY);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null){
            countDownTimer.cancel();
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            ScreenUtils.hideBottomUIMenu(WelcomeActivity.this);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.jump_btn) {
            goMainActivity();
        }
    }


    /**
     * 倒计时计时器
     */
    private class MyCountDownTimer extends CountDownTimer {

        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            mJumpbtn.setText("跳过(" + millisUntilFinished / 1000 + "s)");
        }

        @Override
        public void onFinish() {
            mJumpbtn.setText("跳过(" + 0 + "s)");
            goMainActivity();
        }
    }

}
