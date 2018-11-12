package mao.com.mao_wanandroid_client;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import java.util.List;

import mao.com.mao_wanandroid_client.core.http.api.ApiService;
import mao.com.mao_wanandroid_client.core.http.tools.NetworkUtils;
import mao.com.mao_wanandroid_client.core.http.httpcontrol.ProgressObserver;
import mao.com.mao_wanandroid_client.core.http.httpcontrol.RxSchedulers;
import mao.com.mao_wanandroid_client.model.banner.HomePageBannerModel;

import mao.com.mao_wanandroid_client.model.ResponseBody;


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

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
