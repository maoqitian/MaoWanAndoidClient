package mao.com.mao_wanandroid_client.utils;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import mao.com.mao_wanandroid_client.application.Constants;
import mao.com.mao_wanandroid_client.model.modelbean.home.HomeArticleData;
import mao.com.mao_wanandroid_client.model.modelbean.knowlegetree.KnowledgeHierarchyData;

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
    public static void start(final Context context, HomeArticleData unit, String pageType, String activityType) {

        if(Constants.PAGE_WEB_COLLECT.equals(pageType) || Constants.PAGE_WEB_NOT_COLLECT.equals(pageType)) {//WEB 详情 页面
            Intent intent = new Intent(activityType);
            intent.putExtra(Constants.HOME_ARTICLE_DATA, unit);
            intent.putExtra(Constants.PAGE_TYPE, pageType);
            context.startActivity(intent);
            return;
        }
        if(Constants.RESULT_CODE_HOME_PAGE.equals(pageType)) {// 知识体系二级 页面
            Intent intent = new Intent(activityType);
            intent.putExtra(Constants.HOME_ARTICLE_DATA, unit);
            intent.putExtra(Constants.PAGE_TYPE, pageType);
            context.startActivity(intent);
            return;
        }
        if(Constants.PAGE_MAIN.equals(pageType)||Constants.PAGE_LOGIN.equals(pageType)||Constants.PAGE_SIGN_UP.equals(pageType)){
            Intent intent = new Intent(activityType);
            context.startActivity(intent);
            return;
        }
        Toast.makeText(context, "该页面暂未实现", Toast.LENGTH_SHORT).show();
    }

    public static void start2(final Context context, KnowledgeHierarchyData unit, String pageType, String activityType) {

        if(Constants.PAGE_OFFICIAL_ACCOUNTS_DETAIL.equals(pageType)) {//公众号详情页
            Intent intent = new Intent(activityType);
            intent.putExtra(Constants.KNOWLEDGE_DATA, unit);
            context.startActivity(intent);
            return;
        }
        if(Constants.RESULT_CODE_KNOWLEDGE_PAGE.equals(pageType)) {//知识体系模块跳转知识体系详情
            Intent intent = new Intent(activityType);
            intent.putExtra(Constants.KNOWLEDGE_DATA, unit);
            intent.putExtra(Constants.PAGE_TYPE, Constants.RESULT_CODE_KNOWLEDGE_PAGE);
            context.startActivity(intent);
            return;
        }

        Toast.makeText(context, "该页面暂未实现", Toast.LENGTH_SHORT).show();
    }

}
