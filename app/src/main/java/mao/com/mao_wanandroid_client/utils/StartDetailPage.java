package mao.com.mao_wanandroid_client.utils;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import mao.com.mao_wanandroid_client.application.Constants;
import mao.com.mao_wanandroid_client.model.home.HomeArticleData;

/**
 * @author maoqitian
 * @Description: Activity 跳转路由
 * @date 2019/6/3 0003 4:21
 */
public class StartDetailPage{
    /**
     *
     * @param context
     * @param unit
     * @param pageType 跳转类型
     */
    public static void start(final Context context, HomeArticleData unit,String pageType) {

        if(Constants.PAGE_WEB_COLLECT.equals(pageType) || Constants.PAGE_WEB_NOT_COLLECT.equals(pageType)) {//WEB 详情 页面
            Intent intent = new Intent(Constants.ACTION_PAGE_DETAIL_ACTIVITY);
            intent.putExtra(Constants.HOME_ARTICLE_DATA, unit);
            intent.putExtra(Constants.PAGE_TYPE, pageType);
            context.startActivity(intent);
            return;
        }
        if(Constants.PAGE_LOGIN.equals(pageType)){
            Intent intent = new Intent(Constants.ACTION_LOGIN_ACTIVITY);
            context.startActivity(intent);
            return;
        }
        Toast.makeText(context, "该页面暂未实现", Toast.LENGTH_SHORT).show();
    }

}
