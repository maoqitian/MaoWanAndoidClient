package mao.com.mao_wanandroid_client.core.http;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
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

    private Retrofit.Builder mRetrfitBuilder;
    private static NetworkUtils mInstance;

    public static NetworkUtils getmInstance() {
         if(mInstance==null){
             mInstance=new NetworkUtils();
         }
        return mInstance;
    }

    private NetworkUtils(){
       mRetrfitBuilder=new Retrofit.Builder()
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
            return mRetrfitBuilder.addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(url)
                    .build().create(service);
        }else {
            Log.i(TGA,"not useGson url"+url);
            return mRetrfitBuilder.addConverterFactory(ScalarsConverterFactory.create())
                    .baseUrl(url)
                    .build().create(service);
        }
    }


    public OkHttpClient getOkHttpClient(){
        OkHttpClient.Builder clientBuilder=new OkHttpClient.Builder();
        OkHttpClient client=clientBuilder
                .connectTimeout(30, TimeUnit.MINUTES)
                .readTimeout(30, TimeUnit.MINUTES)
                .writeTimeout(30, TimeUnit.MINUTES)
                .build();
        return client;
    }
}
