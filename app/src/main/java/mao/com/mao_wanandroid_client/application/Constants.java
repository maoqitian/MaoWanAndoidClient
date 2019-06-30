package mao.com.mao_wanandroid_client.application;

import android.graphics.Color;

import java.io.File;

import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.model.home.HomeArticleData;

/**
 * @author maoqitian
 * @Description 常量类
 * @Time 2018/10/25 0025 17:30
 */
public class Constants {


    /**
     * 登录获取的 Cookie
     */
    public static final String COOKIE = "Cookie";

    /**
     * Path 路径
     */
    public static final String PATH_DATA = MyApplication.getInstance().getCacheDir().getAbsolutePath() + File.separator + "data";
    /**
     * 网络缓存路径
     */
    public static final String PATH_NET_CACHE = PATH_DATA + "/netCache";

    /**
     * Tag fragment classify
     */
    public static final int TYPE_MAIN_PAGER = 0;

    public static final int TYPE_KNOWLEDGE = 1;

    public static final int TYPE_NAVIGATION = 2;

    public static final int TYPE_PROJECT = 3;

    public static final int TYPE_COLLECT = 4;

    public static final int TYPE_SETTING = 5;


    /**
     * Bottom Navigation tab classify
     */
    public static final int TAB_ONE = 0;

    /**
     * Intent params
     */
    public static final String ARG_PARAM1 = "param1";

    public static final String ARG_PARAM2 = "param2";

    /**
     * Phone MANUFACTURER
     */
    public static final String SAMSUNG = "samsung";

    /**
     * Tab colors
     */
    public static final int[] TAB_COLORS = new int[]{
            Color.parseColor("#90C5F0"),
            Color.parseColor("#91CED5"),
            Color.parseColor("#F88F55"),
            Color.parseColor("#C0AFD0"),
            Color.parseColor("#E78F8F"),
            Color.parseColor("#67CCB7"),
            Color.parseColor("#F6BC7E")
    };


    /**
     * Main Pager
     */
    public static final String SEARCH_TEXT = "search_text";

    public static final String MENU_BUILDER = "MenuBuilder";

    public static final String LOGIN_DATA = "login_data";

    public static final String BANNER_DATA = "banner_data";

    public static final String ARTICLE_DATA = "article_data";

    /**
     * Refresh theme color
     */
    public static final int BLUE_THEME = R.color.colorPrimary;

    /**
     * Avoid double click time area
     */
    public static final long CLICK_TIME_AREA = 1000;

    public static final long DOUBLE_INTERVAL_TIME = 2000;


    public static final String DB_NAME = "mao_wanandroid.db";



    /**
     * SharedPreferences
     */
    public static final String  SHAREDPREFERENCES_NAME= "sharepreference_name";

    public static final String SP_USER_NAME = "user_name";

    public static final String SP_PASSWORD = "password";

    public static final String SP_LOGIN_STATUS = "login_status";

    public static final String SP_AUTO_CACHE = "auto_cache";

    public static final String SP_NO_IMAGE = "no_image";

    public static final String SP_NIGHT_MODE = "night_mode";

    public static final String SP_CURRENT_PAGE = "current_page";

    public static final String SP_PROJECT_CURRENT_PAGE = "project_current_page";



    //页面跳转类型 能够收藏（是否显示收藏）
    public static final String PAGE_MAIN = "PAGE_MAIN";

    public static final String PAGE_WEB_COLLECT = "PAGE_TYPE_WEB_COLLECT";
    //页面跳转类型 不能收藏（是否显示收藏）例如：首页 banner
    public static final String PAGE_WEB_NOT_COLLECT = "PAGE_TYPE_WEB_NOT_COLLECT";

    public static final String PAGE_LOGIN = "PAGE_LOGIN";
    public static final String PAGE_SIGN_UP = "PAGE_SIGN_UP";


    //页面隐式调用
    // 主页面
    public static final String ACTION_MAIN_ACTIVITY = "com.mao.mao_wanandroid.action.MAIN_ACTIVITY";
    //详情页
    public static final String ACTION_PAGE_DETAIL_ACTIVITY = "com.mao.mao_wanandroid.action.PAGE_DETAIL_ACTIVITY";
    //知识体系二级页
    public static final String ACTION_KNOWLEDGE_LEVEL2_ACTIVITY = "com.mao.mao_wanandroid.action.KNOWLEDGE_LEVEL2";
    //登录页
    public static final String ACTION_LOGIN_ACTIVITY = "com.mao.mao_wanandroid.action.LOGIN_ACTIVITY";
    //注册页
    public static final String ACTION_SIGN_UP_ACTIVITY = "com.mao.mao_wanandroid.action.SIGN_UP_ACTIVITY";



    //页面跳转 code 标识是来自首页哪个模块跳转
    public static final String RESULT_CODE_HOME_PAGE = "result_code_home_page";

    //Bundle 数据 tag

    //首页文章数据 常量
    public static final String HOME_ARTICLE_DATA = "homeArticleData";
    //跳转标识
    public static final String PAGE_TYPE = "pageType";

    //知识体系 一级 cid
    public static final String BUNDLE_TAG_CID = "chapter_cid";
    //知识体系 二级 cid
    public static final String BUNDLE_TAG_SUPER_CID = "super_chapter_cid";

}
