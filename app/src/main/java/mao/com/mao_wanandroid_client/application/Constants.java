package mao.com.mao_wanandroid_client.application;

import android.graphics.Color;

import java.io.File;

import mao.com.mao_wanandroid_client.R;

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


    public static final String ARTICLE_LINK = "article_link";

    public static final String ARTICLE_TITLE = "article_title";

    public static final String ARTICLE_ID = "article_id";

    public static final String IS_COLLECT = "is_collect";

    public static final String IS_COMMON_SITE = "is_common_site";

    public static final String IS_COLLECT_PAGE = "is_collect_page";

    public static final String CHAPTER_ID = "chapter_id";

    public static final String IS_SINGLE_CHAPTER = "is_single_chapter";

    public static final String CHAPTER_NAME = "is_chapter_name";

    public static final String SUPER_CHAPTER_NAME = "super_chapter_name";

    public static final String DB_NAME = "mao_wanandroid.db";



    /**
     * SharedPreferences
     */
    public static final String  SHAREDPREFERENCES_NAME= "sharepreference_name";

    public static final String SP_ACCOUNT = "account";

    public static final String SP_PASSWORD = "password";

    public static final String SP_LOGIN_STATUS = "login_status";

    public static final String SP_AUTO_CACHE = "auto_cache";

    public static final String SP_NO_IMAGE = "no_image";

    public static final String SP_NIGHT_MODE = "night_mode";

    public static final String SP_CURRENT_PAGE = "current_page";

    public static final String SP_PROJECT_CURRENT_PAGE = "project_current_page";


    //页面隐式调用
    public static final String ACTION_MAIN_ACTIVITY="com.mao.mao_wanandroid.action.MAIN_ACTIVITY"; // 主页面

}
