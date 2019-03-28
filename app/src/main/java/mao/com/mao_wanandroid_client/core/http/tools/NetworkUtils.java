package mao.com.mao_wanandroid_client.core.http.tools;

import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import mao.com.mao_wanandroid_client.BuildConfig;
import mao.com.mao_wanandroid_client.application.Constants;
import mao.com.mao_wanandroid_client.core.http.cookie.CookieManager;
import mao.com.mao_wanandroid_client.utils.ToolsUtils;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * @author maoqitian
 * @Description 网络请求工具类 retrofit+okhttp
 * @Time 2018/9/3 0003 22:55
 */
public class NetworkUtils {

    private final static String TGA="NetworkUtils";

    private Retrofit.Builder mRetrofitBuilder;
    //双重效验锁实现单例
    /*private static volatile NetworkUtils mInstance;

    public static NetworkUtils getInstance() {
        if(mInstance==null){
            synchronized (NetworkUtils.class){
                if(mInstance==null){
                    mInstance=new NetworkUtils();
                }
            }
        }
        return mInstance;
    }*/
    @Inject
    public NetworkUtils(){
       mRetrofitBuilder=new Retrofit.Builder()
               .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
               .client(getOkHttpClient());
    }

    /**
     * 获取网络请求对象
     * @param service 对应的Api 接口类
     * @param url url
     * @param useGson 是否使用 gson 解析
     * @param <T>
     * @return
     */
    public <T> T getApiService(final Class<T> service,String url, boolean useGson){
        if(useGson){
            Log.i(TGA,"useGson url"+url);
            return mRetrofitBuilder.addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(url)
                    .build().create(service);
        }else {
            Log.i(TGA,"not useGson url"+url);
            return mRetrofitBuilder.addConverterFactory(ScalarsConverterFactory.create())
                    .baseUrl(url)
                    .build().create(service);
        }
    }


    public OkHttpClient getOkHttpClient(){
        OkHttpClient.Builder clientBuilder=new OkHttpClient.Builder();
        if(BuildConfig.DEBUG){
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            //加入日志拦截器
            clientBuilder.addInterceptor(loggingInterceptor);
        }
        File netCachePath =new File(Constants.PATH_NET_CACHE);
        Cache netCache = new Cache(netCachePath,1024 * 1024 * 50);
        Interceptor netCacheInterceptor= chain -> {
            Request request = chain.request();
            if(!ToolsUtils.isNetworkConnected()){
                //如果没有网络
                //仅仅使用缓存
             request =request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
            }
            Response response = chain.proceed(request);
            if(ToolsUtils.isNetworkConnected()){
                //如果有网络
                int maxAge = 0;
                // 有网络时, 不缓存, 最大保存时长为0
                response.newBuilder()
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .removeHeader("Pragma")
                        .build();
            }else {
                // 无网络时，设置超时为2周
                int maxStale = 60 * 60 * 24 * 14;
                response.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .removeHeader("Pragma")
                        .build();
            }
            return response;
        };
        //加入缓存拦截器
        clientBuilder.addInterceptor(netCacheInterceptor);
        clientBuilder.addNetworkInterceptor(netCacheInterceptor);
        clientBuilder.cache(netCache);
        //设置超时时间
        clientBuilder.connectTimeout(30, TimeUnit.MINUTES)
                .readTimeout(30, TimeUnit.MINUTES)
                .writeTimeout(30, TimeUnit.MINUTES)
                //错误重连
                .retryOnConnectionFailure(true);
        // cookie 操作处理
        clientBuilder.cookieJar(CookieManager.getInstance());
        return clientBuilder.build();
    }
}
