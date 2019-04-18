package mao.com.mao_wanandroid_client.view.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.base.activity.BaseActivity;
import mao.com.mao_wanandroid_client.presenter.main.MainContract;
import mao.com.mao_wanandroid_client.presenter.main.MainPresenter;


public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.MainView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        //测试网络模块。获取首页Banner数据
       /* NetworkUtils.getInstance().getApiService(ApiService.class,ApiService.HOST,true).
                GetHomePageBannerData()
                .compose(RxSchedulers.<ResponseBody<List<HomePageBannerModel>>>observableIO2Main(this))
                .subscribe(new ProgressObserver<List<HomePageBannerModel>>(this,"正在加载首页Banner数据") {
                    @Override
                    public void onSuccess(List<HomePageBannerModel> result) {
                        Log.e("mao",result.toString());
                    }

                    @Override
                    public void onFailure(Throwable e, String errorMsg) {
                        Log.e("mao",e.getMessage()+errorMsg);
                    }
                });*/
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    //再点一次退出程序
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            doubleClickExit();
        }
        return false;
    }

    private static Boolean mIsExit = false;

    private void doubleClickExit() {
        Timer exitTimer = null;
        if (!mIsExit) {
            mIsExit = true;
            Toast.makeText(getApplicationContext(),"再点一次退出应用",Toast.LENGTH_SHORT).show();
            exitTimer = new Timer();
            exitTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    mIsExit = false;
                }
            }, 2000);
        } else {
            finish();
            System.exit(0);
        }
    }
}
