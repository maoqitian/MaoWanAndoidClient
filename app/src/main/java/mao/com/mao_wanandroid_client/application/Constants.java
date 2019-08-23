package mao.com.mao_wanandroid_client.application;
import java.io.File;
/**
 * @author maoqitian
 * @Description 常量类
 * @Time 2018/10/25 0025 17:30
 */
public class Constants {


    /**
     * Path 路径
     */
    public static final String PATH_DATA = MyApplication.getInstance().getCacheDir().getAbsolutePath() + File.separator + "data";
    /**
     * 网络缓存路径
     */
    public static final String PATH_NET_CACHE = PATH_DATA + "/netCache";

    /**
     * 应用缓存路径
     */
    public static final String PATH_CACHE_DATA = MyApplication.getInstance().getCacheDir().getAbsolutePath();

    /**
     * 搜索历史数据库名称
     */
    public static final String DB_NAME = "mao_wanandroid.db";

    /**
     * fragment tag
     */
    public static final String TAG_HOME = "HomePageFragment";
    public static final String TAG_KNOWLEGER = "KnowledgeHierarchyPageFragment";
    public static final String TAG_OFFICIAL = "OfficialAccountsPageFragment";
    public static final String TAG_PROJECT = "ProjectFragment";
    public static final String TAG_NAVIGATION = "NavigationFragment";
    public static final String TAG_COLLECTION = "CollectionFragment";
    /**
     * SharedPreferences
     */
    public static final String  SHAREDPREFERENCES_NAME= "mao_wanandroid_sharepreference";

    public static final String SP_USER_NAME = "user_name";

    public static final String SP_PASSWORD = "password";

    public static final String SP_LOGIN_STATUS = "login_status";

    public static final String SP_AUTO_CACHE = "auto_cache";

    public static final String SP_NO_IMAGE = "no_image";

    public static final String SP_NIGHT_MODE = "night_mode";

    public static final String SP_CURRENT_PAGE = "current_page";

    public static final String SP_PROJECT_CURRENT_PAGE = "project_current_page";


    //RecycleView item 类型
    //头部类型
    public static final int TYPE_HEADER = 1;
    //数据类型
    public static final int TYPE_DATA = 2;




    //页面跳转类型 进入主页
    public static final String PAGE_MAIN = "PAGE_MAIN";
    //能够收藏（是否显示收藏）
    public static final String PAGE_WEB_COLLECT = "PAGE_TYPE_WEB_COLLECT";
    //页面跳转类型 不能收藏（是否显示收藏）例如：首页 banner
    public static final String PAGE_WEB_NOT_COLLECT = "PAGE_TYPE_WEB_NOT_COLLECT";

    public static final String PAGE_LOGIN = "PAGE_LOGIN";
    public static final String PAGE_SIGN_UP = "PAGE_SIGN_UP";
    //公众号详情页
    public static final String PAGE_OFFICIAL_ACCOUNTS_DETAIL = "PAGE_OFFICIAL_ACCOUNTS_DETAIL";


    //页面隐式调用
    // 主页面
    public static final String ACTION_MAIN_ACTIVITY = "com.mao.mao_wanandroid.action.MAIN_ACTIVITY";
    //详情页
    public static final String ACTION_PAGE_DETAIL_ACTIVITY = "com.mao.mao_wanandroid.action.PAGE_DETAIL_ACTIVITY";
    //知识体系二级页
    public static final String ACTION_KNOWLEDGE_LEVEL2_ACTIVITY = "com.mao.mao_wanandroid.action.KNOWLEDGE_LEVEL2";
    //公众号详情页
    public static final String ACTION_OFFICIAL_ACCOUNTS_ACTIVITY = "com.mao.mao_wanandroid.action.OFFICIAL_ACCOUNTS";
    //登录页
    public static final String ACTION_LOGIN_ACTIVITY = "com.mao.mao_wanandroid.action.LOGIN_ACTIVITY";
    //注册页
    public static final String ACTION_SIGN_UP_ACTIVITY = "com.mao.mao_wanandroid.action.SIGN_UP_ACTIVITY";
    //搜索结果
    public static final String ACTION_SEARCH_RESULT_ACTIVITY = "com.mao.mao_wanandroid.action.SEARCH_RESULT";


    //页面跳转 code 标识是来自 哪个模块跳转
    //首页跳转
    public static final String RESULT_CODE_HOME_PAGE = "result_code_home_page";
    //知识体系 跳转
    public static final String RESULT_CODE_KNOWLEDGE_PAGE = "result_code_knowledge_page";
    //微信公众号详情页 跳转
    public static final String RESULT_CODE_OFFICIAL_ACCOUNTS_PAGE = "official_accounts_detail_page";

    public static final String TAG_TAB_NAME = "tabName";

    //Bundle 数据 tag
    //首页文章数据 常量
    public static final String HOME_ARTICLE_DATA = "homeArticleData";
    //知识体系 数据 常量
    public static final String KNOWLEDGE_DATA = "knowledgeData";
    //跳转标识
    public static final String PAGE_TYPE = "pageType";
    //公众号id 标识
    public static final String WX_ID = "wxid";
    //公众号名称 标识
    public static final String WX_NAME = "wxname";
    //知识体系 一级 cid
    public static final String BUNDLE_TAG_CID = "chapter_cid";
    //知识体系 二级 cid
    public static final String BUNDLE_TAG_SUPER_CID = "super_chapter_cid";
    //获取项目模块 项目列表数据 id
    public static final String BUNDLE_PROJECT_ID = "projectId";

    //设置 item type
    // 清除缓存
    public static final String SETTINGS_CLEAR_CACHE_TYPE = "settings_clear_cache_type";
    // 夜间模式
    public static final String SETTINGS_NIGHT_MODE_TYPE = "settings_night_mode_type";
    // 版本
    public static final String SETTINGS_VERSION_TYPE = "settings_version_type";

    /**
     * 首页多个请求响应 type
     */
    public static final String RESPONSE_BANNER_TYPE = "homePageBannerObservable";
    public static final String RESPONSE_TOP_ARTICLE_TYPE = "homeTopArticleDataObservable";
    public static final String RESPONSE_ARTICLE_TYPE = "homeArticleListDataObservable";

    //Dialog
    public static final String COLLECTION_WEB_TYPE = "collection_web_type";
    public static final String COLLECTION_ARTICLE_TYPE = "collection_article_type";
}
