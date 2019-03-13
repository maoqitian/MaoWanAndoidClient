package mao.com.mao_wanandroid_client.di;

import dagger.Module;
import dagger.Provides;
import mao.com.mao_wanandroid_client.core.db.DbHelper;
import mao.com.mao_wanandroid_client.core.http.DataClient;
import mao.com.mao_wanandroid_client.core.http.helper.IHttpHelper;
import mao.com.mao_wanandroid_client.core.sp.SharedPreferenceHelper;
import mao.com.mao_wanandroid_client.model.banner.HomePageBannerModel;

/**
 * @author maoqitian
 * @Description 测试dagger2
 * @Time 2019/3/5 0005 22:42
 */
@Module
public class MyModule {

    /*@Provides
    public DataClient provideDataClient(IHttpHelper mIHttpHelper,
                                        SharedPreferenceHelper mSharedPreferenceHelper,
                                        DbHelper mDbHelper){
        return new DataClient(mIHttpHelper,mSharedPreferenceHelper,mDbHelper);
    }*/
    @Provides
    public HomePageBannerModel provideHomePageBannerModel(){
       return new HomePageBannerModel();
    }
}
