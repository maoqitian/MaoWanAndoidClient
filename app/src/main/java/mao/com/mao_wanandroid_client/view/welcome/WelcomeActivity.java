package mao.com.mao_wanandroid_client.view.welcome;


import android.os.Bundle;
import android.support.annotation.Nullable;

import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.application.Constants;
import mao.com.mao_wanandroid_client.base.activity.BaseActivity;
import mao.com.mao_wanandroid_client.presenter.welcome.WelcomeContract;
import mao.com.mao_wanandroid_client.presenter.welcome.WelcomePresenter;
import mao.com.mao_wanandroid_client.utils.StartDetailPage;

/**
 * @author maoqitian
 * @Description 闪屏页
 * @Time 2019/2/21 0021 20:24
 */
public class WelcomeActivity extends BaseActivity<WelcomePresenter> implements WelcomeContract.WelcomeView {

    //测试 dagger2
    /*@Inject
    HomePageBannerModel model;*/

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //DaggerMyComponent.create().inJectWelcomeActivity(this);
       // Log.e("毛麒添","DaggerMyComponent :" +model.hashCode());


    }

    @Override
    protected int getLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    public void jumpToMainActivity() {
        StartDetailPage.start(this,null, Constants.PAGE_MAIN,Constants.ACTION_MAIN_ACTIVITY);
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
