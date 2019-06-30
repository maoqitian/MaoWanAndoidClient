package mao.com.mao_wanandroid_client.core.http.api;

import java.util.List;

import io.reactivex.Observable;
import mao.com.mao_wanandroid_client.model.ResponseBody;
import mao.com.mao_wanandroid_client.model.banner.HomePageBannerModel;
import mao.com.mao_wanandroid_client.model.collect.CollectData;
import mao.com.mao_wanandroid_client.model.collect.CollectListData;
import mao.com.mao_wanandroid_client.model.frienduser.FriendUseWebData;
import mao.com.mao_wanandroid_client.model.home.HomeArticleData;
import mao.com.mao_wanandroid_client.model.home.HomeArticleListData;
import mao.com.mao_wanandroid_client.model.login.LoginData;
import mao.com.mao_wanandroid_client.model.navigation.NavigationListData;
import mao.com.mao_wanandroid_client.model.project.ProjectClassifyData;
import mao.com.mao_wanandroid_client.model.project.ProjectListData;
import mao.com.mao_wanandroid_client.model.search.HotKeyData;
import mao.com.mao_wanandroid_client.model.knowlegetree.KnowledgeHierarchyData;
import mao.com.mao_wanandroid_client.model.webmark.webBookMark;
import mao.com.mao_wanandroid_client.model.wechat.OfficialAccountsModel;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

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
    Observable<ResponseBody<List<FriendUseWebData>>>GetFriendUseWebData();

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
    Observable<ResponseBody<NavigationListData>>getNavigationListData();

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
    Observable<ResponseBody<List<webBookMark>>> getWebBookMark();

    /**
     * 添加收藏网址
     * @param name 网站名称
     * @param link 网站链接
     * @return
     */
    @POST("/lg/collect/addtool/json")
    @FormUrlEncoded
    Observable<ResponseBody<webBookMark>> addWebBookMark(
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
    Observable<ResponseBody<webBookMark>> updateWebBookMark(
            @Field("id")String id,
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
    Observable<ResponseBody> deleteWebBookMark(
            @Field("id")String id
    );

    /**
     * 搜索
     * @param pageNum 页码：拼接在链接上，从0开始
     * @param keyWord 搜索关键词
     * @return
     */
    @POST("/article/query/{pageNum}/json")
    @FormUrlEncoded
    Observable<ResponseBody<ProjectListData>> getSearchKeyWordData(
            @Path("pageNum") int pageNum,
            @Field("k") String keyWord
    );

    /**
     * todo 接口
     */

    /**
     * 公众号 tab 相关接口
     */
    /**
     * 获取公众号列表
     * @return
     */
    @GET("/wxarticle/chapters/json")
    Observable<ResponseBody<List<OfficialAccountsModel>>> getWxArticle();

}
