package mao.com.mao_wanandroid_client.model.http.api;

import java.util.Date;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.annotations.SchedulerSupport;
import mao.com.mao_wanandroid_client.model.modelbean.BaseMultipleData;
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
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Api接口
 */
public interface ApiService {

    String HOST = "https://www.wanandroid.com";

    /**
     * 首页相关接口
     */

    /**
     * 获取首页文章数据列表
     * @return
     */
    @GET("/article/list/{pageNum}/json")
    Observable<ResponseBody<HomeArticleListData>>HomeArticleListData(@Path("pageNum")int pageNum );

    /**
     * 最新项目tab (首页的第二个tab)
     * @param pageNum 页码，拼接在连接中，从0开始。
     * @return
     */
    @GET("/article/listproject/{pageNum}/json")
    Observable<ResponseBody<HomeArticleListData>>HomeArticleListProjectData(@Path("pageNum")int pageNum );

    /**
     * 获取首页Banner数据
     * @return
     */
    @GET("/banner/json")
    Observable<ResponseBody<List<HomePageBannerModel>>>GetHomePageBannerData();

    /**
     * 获取常用网站数据
     * @return
     */
    @GET("/friend/json")
    Observable<ResponseBody<List<CommonWebData>>>GetFriendUseWebData();

    /**
     * 置顶文章
     * @return
     */
    @GET("/article/top/json")
    Observable<ResponseBody<List<HomeArticleData>>>HomeTopArticleData();

    /**
     * 搜索热词
     * @return
     */
    @GET("/hotkey/json")
    Observable<ResponseBody<List<HotKeyData>>>GetHotKeyData();

    /**
     * 体系
     */

    /**
     * 知识体系数据
     * @return
     */
    @GET("/tree/json")
    Observable<ResponseBody<List<KnowledgeHierarchyData>>> getKnowledgeHierarchyData();

    /**
     * 根据知识体系 id 获取知识体系下的文章
     * @param pageNum 页码：拼接在链接上，从0开始
     * @param cid 分类的id 上述二级目录的id
     * @return
     */
    @GET("/article/list/{pageNum}/json")
    Observable<ResponseBody<HomeArticleListData>> getKnowledgeTreeDetailData(@Path("pageNum") int pageNum, @Query("cid") int cid);

    /**
     * 导航
     */

    /**
     * 获取导航数据
     * @return
     */
    @GET("/navi/json")
    Observable<ResponseBody<List<NavigationListData>>>getNavigationListData();

    /**
     * 项目
     */

    /**
     * 项目分类 项目为包含一个分类，该接口返回整个分类。
     * @return
     */
    @GET("/project/tree/json")
    Observable<ResponseBody<List<ProjectClassifyData>>>getProjectClassifyData();

    /**
     * 项目列表数据 某一个分类下项目列表数据，分页展示
     * @param pageNum 拼接在链接中，从1开始。
     * @param cid 分类的id，上面项目分类接口
     * @return
     */
    @GET("/project/list/{pageNum}/json")
    Observable<ResponseBody<ProjectListData>> getProjectListData(@Path("pageNum") int pageNum,@Query("cid") int cid);

    /**
     * 登录与注册
     */

    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @POST("/user/login")
    @FormUrlEncoded
    Observable<ResponseBody<LoginData>> postLoginData(
            @Field("username")String username,
            @Field("password")String password);

    /**
     * 注册
     * @param username 用户名
     * @param password 密码
     * @param repassword 确认密码
     * @return
     */
    @POST("/user/register")
    @FormUrlEncoded
    Observable<ResponseBody<LoginData>> postSignUpData(
            @Field("username")String username,
            @Field("password")String password,
            @Field("repassword")String repassword
    );

    /**
     * 退出登录
     * 访问了 logout 后，服务端会让客户端清除 Cookie（即cookie max-Age=0），
     * 如果客户端 Cookie 实现合理，可以实现自动清理，如果本地做了用户账号密码和保存，
     * 及时清理。
     */
    @GET("/user/logout/json")
    Observable<ResponseBody<String>> getSignOut();

    /**
     *  收藏
     */
    /**
     * 收藏文章列表
     * @param pageNum 页码：拼接在链接中，从0开始。
     * @return
     */
    @GET("/lg/collect/list/{pageNum}/json")
    Observable<ResponseBody<CollectListData>>getCollectListData(@Path("pageNum")int pageNum);

    /**
     * 收藏站内文章
     * @param articleId 文章id，拼接在链接中。
     * @return
     */
    @POST("lg/collect/{articleId}/json")
    Observable<ResponseBody<String>>getCollectInsideListData(@Path("articleId") int articleId);

    /**
     * 收藏站外文章
     * @param title
     * @param author
     * @param link
     * @return
     */
    @POST("/lg/collect/add/json")
    @FormUrlEncoded
    Observable<ResponseBody<CollectData>>getCollectOutsideListData(
            @Field("title")String title,
            @Field("author")String author,
            @Field("link")String link
    );

    /**
     * 文章列表 页面调用  取消收藏
     * @param articleId 文章id:拼接在链接上
     * @return
     */
    @POST("/lg/uncollect_originId/{articleId}/json")
    Observable<ResponseBody<String>>cancelCollectArticleListData(
            @Path("articleId") int articleId
    );

    /**
     * 我的收藏页面  取消收藏（该页面包含自己录入的内容）
     * @param articleId 文章id:拼接在链接上
     * @param originId  originId:列表页下发，无则为-1 （originId 代表的是你收藏之前的那篇文章本身的id； 但是收藏支持主动添加，这种情况下，没有originId则为-1）
     * @return
     */
    @POST("/lg/uncollect/{articleId}/json")
    @FormUrlEncoded
    Observable<ResponseBody<String>>cancelCollectArticlePageData(
            @Path("articleId") int articleId,
            @Field("originId") int originId
    );

    /**
     * 获取收藏网站列表
     */
    @GET("/lg/collect/usertools/json")
    Observable<ResponseBody<List<WebBookMark>>> getWebBookMark();

    /**
     * 添加收藏网址
     * @param name 网站名称
     * @param link 网站链接
     * @return
     */
    @POST("/lg/collect/addtool/json")
    @FormUrlEncoded
    Observable<ResponseBody<WebBookMark>> addWebBookMark(
            @Field("name")String name,
            @Field("link")String link
    );

    /**
     * 编辑收藏网站
     * @param id 收藏网站 id
     * @param name 网站名称
     * @param link 网站链接
     * @return
     */
    @POST("/lg/collect/updatetool/json")
    @FormUrlEncoded
    Observable<ResponseBody<WebBookMark>> updateWebBookMark(
            @Field("id")int id,
            @Field("name")String name,
            @Field("link")String link
    );

    /**
     * 删除收藏网站
     * @param id 收藏网站 id
     * @return
     */
    @POST("/lg/collect/deletetool/json")
    @FormUrlEncoded
    Observable<ResponseBody<String>> deleteWebBookMark(
            @Field("id")int id
    );

    /**
     * 搜索
     * @param pageNum 页码：拼接在链接上，从0开始
     * @param keyWord 搜索关键词
     * @return
     */
    @POST("/article/query/{pageNum}/json")
    @FormUrlEncoded
    Observable<ResponseBody<HomeArticleListData>> getSearchKeyWordData(
            @Path("pageNum") int pageNum,
            @Field("k") String keyWord
    );

    /**
     * 公众号 tab 相关接口
     */
    /**
     * 获取公众号列表
     * @return
     */
    @GET("/wxarticle/chapters/json")
    Observable<ResponseBody<List<KnowledgeHierarchyData>>> getWxArticle();

    /**
     * 查看某个公众号历史数据
     * @param id 公众号 ID：拼接在 url 中，eg:405
     * @param pageNum 公众号页码：拼接在url 中，eg:1
     * @return
     */
    @GET("/wxarticle/list/{WxId}/{pageNum}/json")
    Observable<ResponseBody<HomeArticleListData>>getWxArticleHistory(
            @Path("WxId") int id,@Path("pageNum") int pageNum);

    /**
     * 在某个公众号中搜索历史文章
     * @param id 公众号 ID：拼接在 url 中，eg:405
     * @param pageNum 公众号页码：拼接在url 中，eg:1
     * @param key 搜索关键词 k : 字符串，eg:Java
     * @return
     */
    @GET("/wxarticle/list/{WxId}/{pageNum}/json")
    Observable<ResponseBody<HomeArticleListData>>getWxArticleHistoryByKey(
            @Path("WxId") int id,@Path("pageNum") int pageNum,
            @Query("k") String key);

    /**
     * TODO接口
     */

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
    @POST("/lg/todo/add/json")
    @FormUrlEncoded
    Observable<ResponseBody<TodoData>>addTodo(@Field("title") String title,
                                              @Field("content") String content,
                                              @Field("date") Date date,
                                              @Field("type") int type,
                                              @Field("priority") int priority);

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
    @POST("/lg/todo/update/{id}/json")
    @FormUrlEncoded
    Observable<ResponseBody<TodoData>>updateTodo(@Path("id") int id,@Field("title") String title,
                                                 @Field("content") String content,
                                                 @Field("date") Date date,
                                                 @Field("status") int status,
                                                 @Field("type") int type,
                                                 @Field("priority") int priority);

    /**
     *  删除一个TODO
     * @param id 拼接在链接上，为唯一标识
     * @return
     */
    @POST("/lg/todo/delete/{id}/json")
    Observable<ResponseBody<String>>deleteTodo(@Path("id") int id);

    /**
     * 仅更新完成状态TODO
     * @param id 拼接在链接上，为唯一标识
     * @param status 0或1，传1代表未完成到已完成，反之则反之。 只会变更status，未完成->已经完成 or 已经完成->未完成。
     * @return
     */
    @POST("/lg/todo/done/{id}/json")
    @FormUrlEncoded
    Observable<ResponseBody<TodoData>>updateDoneTodo(@Path("id") int id, @Field("status") int status);

    /**
     * 根据条件 获取 TODO列表
     * @param pageNum 页码从1开始，拼接在url 上
     * @param param status 状态， 1-完成；0未完成; 默认全部展示；
     *              type 创建时传入的类型, 默认全部展示
     *              priority 创建时传入的优先级；默认全部展示
     *              orderby 1:完成日期顺序；2.完成日期逆序；3.创建日期顺序；4.创建日期逆序(默认)；
     * @return
     */
    /*@GET("/lg/todo/v2/list/{pageNum}/json")
    Observable<ResponseBody<TodoData>>getTodoListData(@Path("pageNum") int pageNum,
                                                      @Query("status") int status,
                                                      @Query("type") int type,
                                                      @Query("priority") int priority,
                                                      @Query("orderby") int orderby);*/
    @GET("/lg/todo/v2/list/{pageNum}/json")
    Observable<ResponseBody<TodoData>>getTodoListData(@Path("pageNum") int pageNum,
                                                      @QueryMap Map<String,Integer> param);
    /**
     * 积分接口
     */

    /**
     * 积分排行榜接口
     * @param pageNum 页码：拼接在url 中，从1开始
     */
    @GET("/coin/rank/{pageNum}/json")
    Observable<ResponseBody<BaseListData<RankData>>>getCoinRank(@Path("pageNum") int pageNum);

    /**
     * 获取个人积分，需要登录后访问
     */
    @GET("/lg/coin/userinfo/json")
    Observable<ResponseBody<RankData>>getCoinCount();

    /**
     * 获取个人积分获取列表，需要登录后访问
     * @param pageNum 页码：拼接在url 中，从1开始
     */
    @GET("/lg/coin/list/{pageNum}/json")
    Observable<ResponseBody<BaseListData<CoinRecordData>>>getPersonalCoinList(@Path("pageNum") int pageNum);


    /**
     * 广场接口
     */

    /**
     * 广场列表数据
     * @param pageNum 页码拼接在url上从0开始
     * @return
     */
    @GET("/user_article/list/{pageNum}/json")
    Observable<ResponseBody<BaseListData<HomeArticleData>>>getUserArticleList(@Path("pageNum") int pageNum);


    /**
     * 分享人对应列表数据
     * @param id 分享人 id
     * @param pageNum 页码拼接在url上从1开始
     * @return
     */
    @GET("/user/{id}/share_articles/{pageNum}/json")
    Observable<ResponseBody<BaseMultipleData<RankData,BaseListData<HomeArticleData>>>>getUserShareArticlesData(@Path("id") int id,@Path("pageNum") int pageNum);

    /**
     * 自己的分享的文章列表
     * @param pageNum 页码，从1开始
     * @return
     */
    @GET("/user/lg/private_articles/{pageNum}/json")
    Observable<ResponseBody<BaseMultipleData<RankData,BaseListData<HomeArticleData>>>>getPrivateShareArticlesData(@Path("pageNum") int pageNum);

    /**
     * 删除自己分享的文章
     * @param id 文章id，拼接在链接上
     * @return
     */
    @POST("/lg/user_article/delete/{id}/json")
    Observable<ResponseBody<String>>getUserArticleDelete(@Path("id") int id);

    /**
     * 分享文章
     * @param title 文章标题
     * @param link 文章链接
     * @return
     */
    @POST("/lg/user_article/add/json")
    @FormUrlEncoded
    Observable<ResponseBody<String>>getUserArticleShare(@Field("title") String title,
                                                       @Field("link") String link);


}
