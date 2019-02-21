package mao.com.mao_wanandroid_client.view.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;

import mao.com.mao_wanandroid_client.R;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
}
