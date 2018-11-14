package mao.com.mao_wanandroid_client.core.http.helper;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import mao.com.mao_wanandroid_client.core.http.api.ApiService;
import mao.com.mao_wanandroid_client.core.http.tools.NetworkUtils;
import mao.com.mao_wanandroid_client.model.ResponseBody;
import mao.com.mao_wanandroid_client.model.banner.HomePageBannerModel;
import mao.com.mao_wanandroid_client.model.home.HomeArticleListData;

/**
 * @author maoqitian
 * @Description 网络请求帮助实现类 获取数据 Observable
 * @Time 2018/11/12 0012 20:25
 */
public class IHttpHelperImpl implements IHttpHelper{

    private NetworkUtils mNetworkUtils;

    @Inject
    public IHttpHelperImpl(NetworkUtils networkUtils){
        this.mNetworkUtils=networkUtils;
    }

    private ApiService getApiServiceGson(){
        return mNetworkUtils.getApiService(ApiService.class,ApiService.HOST,true);
    }

    private ApiService getApiServiceNotGson(){
        return mNetworkUtils.getApiService(ApiService.class,ApiService.HOST,false);
    }
    /**
     * 获取首页文章数据列表
     * @param pageNum 页码 从0开始
     * @return
     */
    @Override
    public Observable<ResponseBody<HomeArticleListData>> HomeArticleListData(int pageNum) {
        return getApiServiceGson().HomeArticleListData(pageNum);
    }

    /**
     * 获取首页Banner数据
     * @return
     */
    @Override
    public Observable<ResponseBody<List<HomePageBannerModel>>> GetHomePageBannerData() {
        return getApiServiceGson().GetHomePageBannerData();
    }


}
