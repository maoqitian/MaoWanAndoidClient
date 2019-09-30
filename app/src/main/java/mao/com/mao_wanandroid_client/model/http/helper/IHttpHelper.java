package mao.com.mao_wanandroid_client.model.http.helper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import mao.com.mao_wanandroid_client.model.modelbean.ResponseBody;
import mao.com.mao_wanandroid_client.model.modelbean.banner.HomePageBannerModel;
import mao.com.mao_wanandroid_client.model.modelbean.collect.CollectData;
import mao.com.mao_wanandroid_client.model.modelbean.collect.CollectListData;
import mao.com.mao_wanandroid_client.model.modelbean.frienduser.CommonWebData;
import mao.com.mao_wanandroid_client.model.modelbean.home.HomeArticleData;
import mao.com.mao_wanandroid_client.model.modelbean.home.HomeArticleListData;
import mao.com.mao_wanandroid_client.model.modelbean.login.LoginData;
import mao.com.mao_wanandroid_client.model.modelbean.navigation.NavigationListData;
import mao.com.mao_wanandroid_client.model.modelbean.project.ProjectClassifyData;
import mao.com.mao_wanandroid_client.model.modelbean.project.ProjectListData;
import mao.com.mao_wanandroid_client.model.modelbean.BaseListData;
import mao.com.mao_wanandroid_client.model.modelbean.rank.CoinRecordData;
import mao.com.mao_wanandroid_client.model.modelbean.rank.RankData;
import mao.com.mao_wanandroid_client.model.modelbean.search.HotKeyData;
import mao.com.mao_wanandroid_client.model.modelbean.knowlegetree.KnowledgeHierarchyData;
import mao.com.mao_wanandroid_client.model.modelbean.todo.TodoData;
import mao.com.mao_wanandroid_client.model.modelbean.webmark.WebBookMark;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;


/**
 * @author maoqitian
 * @Description 网络请求帮助接口
 * @Time 2018/11/12 0012 20:23
 */
public interface IHttpHelper {

    /**
     * 获取首页文章数据列表
     * @param pageNum 页码 从0开始
     * @return
     */
    Observable<ResponseBody<HomeArticleListData>>HomeArticleListData(int pageNum);
    /**
     * 获取首页Banner数据
     * @return
     */
    Observable<ResponseBody<List<HomePageBannerModel>>> GetHomePageBannerData();
    /**
     * 最新项目tab (首页的第二个tab)
     * @param pageNum 页码，拼接在连接中，从0开始。
     * @return
     */
    Observable<ResponseBody<HomeArticleListData>>HomeArticleListProjectData(int pageNum);
    /**
     * 获取常用网站数据
     * @return
     */
    Observable<ResponseBody<List<CommonWebData>>>GetFriendUseWebData();
    /**
     * 搜索热词
     * @return
     */
    Observable<ResponseBody<List<HotKeyData>>>GetHotKeyData();

    /**
     * 置顶文章
     * @return
     */
    Observable<ResponseBody<List<HomeArticleData>>>HomeTopArticleData();

    /**
     * 知识体系数据
     * @return
     */
    Observable<ResponseBody<List<KnowledgeHierarchyData>>> getKnowledgeHierarchyData();
    /**
     * 根据知识体系 id 获取知识体系下的文章
     * @param pageNum 页码：拼接在链接上，从0开始
     * @param cid 分类的id 上述二级目录的id
     * @return
     */
    Observable<ResponseBody<HomeArticleListData>> getKnowledgeTreeDetailData(int pageNum, int cid);
    /**
     * 获取导航数据(网站 个人博客 社区等网站导航数据)
     * @return
     */
    Observable<ResponseBody<List<NavigationListData>>>getNavigationListData();
    /**
     * 项目分类 项目为包含一个分类，该接口返回整个分类。
     * @return
     */
    Observable<ResponseBody<List<ProjectClassifyData>>>getProjectClassifyData();
    /**
     * 项目列表数据 某一个分类下项目列表数据，分页展示
     * @param pageNum 拼接在链接中，从1开始。
     * @param cid 分类的id，上面项目分类接口
     * @return
     */
    Observable<ResponseBody<ProjectListData>> getProjectListData(int pageNum, int cid);

    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @return
     */
    Observable<ResponseBody<LoginData>> postLoginData(String username, String password);
    /**
     * 注册
     * @param username 用户名
     * @param password 密码
     * @param repassword 确认密码
     * @return
     */
    Observable<ResponseBody<LoginData>> postSignUpData(
            String username, String password, String repassword
    );

    /**
     * 退出登录
     * @return
     */
    Observable<ResponseBody<String>> getSignOut();
    /**
     * 收藏文章列表
     * @param pageNum 页码：拼接在链接中，从0开始。
     * @return
     */
    Observable<ResponseBody<CollectListData>>getCollectListData(int pageNum);
    /**
     * 收藏站内文章
     * @param articleId 文章id，拼接在链接中。
     * @return
     */
    Observable<ResponseBody<String>>addCollectInsideListData(int articleId);
    /**
     * 收藏站外文章
     * @param title
     * @param author
     * @param link
     * @return
     */
    Observable<ResponseBody<CollectData>>addCollectOutsideListData(
            String title, String author, String link);
    /**
     * 文章列表 取消收藏
     * @param articleId 文章id:拼接在链接上
     * @return
     */
    Observable<ResponseBody<String>>cancelCollectArticleListData(int articleId);

    /**
     * 我的收藏页面 取消收藏（该页面包含自己录入的内容）
     * @param articleId 文章id:拼接在链接上
     * @param originId  originId:列表页下发，无则为-1 （originId 代表的是你收藏之前的那篇文章本身的id； 但是收藏支持主动添加，这种情况下，没有originId则为-1）
     * @return
     */
    Observable<ResponseBody<String>>cancelCollectArticlePageData(
            int articleId, int originId
    );
    /**
     * 获取收藏网站列表
     */
    Observable<ResponseBody<List<WebBookMark>>> getWebBookMark();
    /**
     * 添加收藏网址
     * @param name 网站名称
     * @param link 网站链接
     * @return
     */
    Observable<ResponseBody<WebBookMark>> addWebBookMark(
            String name, String link);
    /**
     * 编辑收藏网站
     * @param id 收藏网站 id
     * @param name 网站名称
     * @param link 网站链接
     * @return
     */
    Observable<ResponseBody<WebBookMark>> updateWebBookMark(
            int id, String name, String link);

    /**
     * 删除收藏网站
     * @param id 收藏网站 id
     * @return
     */
    Observable<ResponseBody<String>> deleteWebBookMark(
            int id);
    /**
     * 搜索
     * @param pageNum 页码：拼接在链接上，从0开始
     * @param keyWord 搜索关键词
     * @return
     */
    Observable<ResponseBody<HomeArticleListData>> getSearchKeyWordData(
             int pageNum, String keyWord
    );

    /**
     * 获取公众号列表
     * @return
     */
    Observable<ResponseBody<List<KnowledgeHierarchyData>>> getWxArticle();

    /**
     * 查看某个公众号历史数据
     * @param id 公众号 ID：拼接在 url 中，eg:405
     * @param pageNum 公众号页码：拼接在url 中，eg:1
     * @return
     */
    Observable<ResponseBody<HomeArticleListData>>getWxArticleHistory(
            int id, int pageNum);

    /**
     * 在某个公众号中搜索历史文章
     * @param id 公众号 ID：拼接在 url 中，eg:405
     * @param pageNum 公众号页码：拼接在url 中，eg:1
     * @param key 搜索关键词 k : 字符串，eg:Java
     * @return
     */
    Observable<ResponseBody<HomeArticleListData>>getWxArticleHistoryByKey(
             int id, int pageNum, String key);


    /**
     * 积分排行榜接口
     * @param pageNum 页码：拼接在url 中，从1开始
     */
    Observable<ResponseBody<BaseListData<RankData>>>getCoinRank(int pageNum);

    /**
     * 获取个人积分，需要登录后访问
     */
    Observable<ResponseBody<Integer>>getCoinCount();

    /**
     * 获取个人积分获取列表，需要登录后访问
     * @param pageNum 页码：拼接在url 中，从1开始
     */
    Observable<ResponseBody<BaseListData<CoinRecordData>>>getPersonalCoinList(int pageNum);

    /**
     * 新增一个TODO
     * @param title 新增标题（必须）
     * @param content 新增详情（必须）
     * @param date  2019-09-24 预定完成时间（不传默认当天，建议传）
     * @param type  大于0的整数（可选）type 可以用于，在app 中预定义几个类别：例如 工作1；生活2；娱乐3；新增的时候传入1，2，3，
     *              查询的时候，传入type 进行筛选,如果不设置type则为 0，未来无法做 type=0的筛选，会显示全部（筛选 type 必须为大于 0 的整数）
     * @param priority 大于0的整数（可选） priority 主要用于定义优先级，在app 中预定义几个优先级：重要（1） 一般（2）等
     *                 查询的时候，传入priority 进行筛选
     *
     * @return
     */
    Observable<ResponseBody<TodoData>>addTodo( String title, String content, Date date, int type, int priority);

    /**
     * 更新一个TODO
     * @param id 拼接在链接上，为唯一标识，列表数据返回时，每个todo 都会有个id标识 （必须）
     * @param title 更新标题 （必须）
     * @param content 新增详情（必须）
     * @param date  2018-08-01（必须）
     * @param status 0为未完成，1为完成 如果有当前状态没有携带，会被默认值更新，比如当前todo status=1，更新时没有带上，会认为被重置。
     *               当更新 status=1时，会自动设置服务器当前时间为完成时间。
     * @param type 同todo新增接口
     * @param priority 同todo新增接口
     * @return
     */
    Observable<ResponseBody<TodoData>>updateTodo( int id, String title, String content, Date date, int status, int type, int priority);

    /**
     *  删除一个TODO
     * @param id 拼接在链接上，为唯一标识
     * @return
     */
    Observable<ResponseBody<String>>deleteTodo( int id);

    /**
     * 仅更新完成状态TODO
     * @param id 拼接在链接上，为唯一标识
     * @param status 0或1，传1代表未完成到已完成，反之则反之。 只会变更status，未完成->已经完成 or 已经完成->未完成。
     * @return
     */
    Observable<ResponseBody<TodoData>>updateDoneTodo(int id, int status);

    /**
     * 根据条件 获取 TODO列表
     * @param pageNum 页码从1开始，拼接在url 上
     * @param param status 状态， 1-完成；0未完成; 默认全部展示；
     *              type 创建时传入的类型, 默认全部展示
     *              priority 创建时传入的优先级；默认全部展示
     *              orderby 1:完成日期顺序；2.完成日期逆序；3.创建日期顺序；4.创建日期逆序(默认)；
     * @return
     */
    Observable<ResponseBody<TodoData>>getTodoListData(int pageNum, Map<String,Integer> param);

}
