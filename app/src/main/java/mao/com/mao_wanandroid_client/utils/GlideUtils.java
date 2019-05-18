package mao.com.mao_wanandroid_client.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

/**
 * @author maoqitian
 * @Description Glide 帮助类
 * @Time 2019/5/18 0018 21:51
 */
public class GlideUtils {

    public static void showBannerImage(Context context,ImageView imageView, String url){
        RequestOptions requestOptions =new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
        Glide.with(context).load(url).apply(requestOptions).into(imageView);
    }

}
