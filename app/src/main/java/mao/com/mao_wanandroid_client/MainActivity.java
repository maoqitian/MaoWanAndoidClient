package mao.com.mao_wanandroid_client;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import mao.com.mao_wanandroid_client.bean.HomePageBannerModel;
import mao.com.mao_wanandroid_client.http.ApiService;
import mao.com.mao_wanandroid_client.http.NetworkUtils;
import mao.com.mao_wanandroid_client.http.ProgressObserver;
import mao.com.mao_wanandroid_client.http.ResponseBody;
import mao.com.mao_wanandroid_client.http.RxSchedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NetworkUtils.getmInstance().getApiService(ApiService.class,ApiService.HOST,true).
                GetHomePageBannerData()
                .compose(RxSchedulers.<ResponseBody<HomePageBannerModel>>observableIO2Main(this))
                .subscribe(new ProgressObserver<HomePageBannerModel>(this) {
                    @Override
                    public void onSuccess(HomePageBannerModel result) {
                        Log.e("mao",result.toString());
                    }

                    @Override
                    public void onFailure(Throwable e, String errorMsg) {
                        Log.e("mao",e.getMessage()+errorMsg);
                    }
                });
    }
}
