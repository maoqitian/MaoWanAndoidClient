package mao.com.mao_wanandroid_client.view.welcome;

import android.content.Intent;

import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.base.activity.BaseActivity;
import mao.com.mao_wanandroid_client.presenter.welcome.WelcomeContract;
import mao.com.mao_wanandroid_client.presenter.welcome.WelcomePresenter;
import mao.com.mao_wanandroid_client.view.main.MainActivity;

/**
 * @author maoqitian
 * @Description 闪屏页
 * @Time 2019/2/21 0021 20:24
 */
public class WelcomeActivity extends BaseActivity<WelcomePresenter> implements WelcomeContract.WelcomeView {




    @Override
    protected int getLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    public void jumpToMainActivity() {
      Intent intent = new Intent(this, MainActivity.class);
      startActivity(intent);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
