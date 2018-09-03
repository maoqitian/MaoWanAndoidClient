package mao.com.mao_wanandroid_client.http;

import io.reactivex.Observable;
import mao.com.mao_wanandroid_client.bean.HomePageBannerModel;
import retrofit2.http.GET;

/**
 * Api接口
 */
public interface ApiService {

    String HOST = "http://www.wanandroid.com";

    /**
     * 获取首页Banner数据
     * @return
     */
    @GET("/")
    Observable<ResponseBody<HomePageBannerModel>>GetHomePageBannerData();
}
