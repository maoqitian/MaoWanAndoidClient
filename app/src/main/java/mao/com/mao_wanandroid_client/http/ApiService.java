package mao.com.mao_wanandroid_client.http;

import java.util.List;

import io.reactivex.Observable;
import mao.com.mao_wanandroid_client.model.ResponseBody;
import mao.com.mao_wanandroid_client.model.banner.HomePageBannerModel;
import mao.com.mao_wanandroid_client.model.frienduser.FriendUseWebData;
import mao.com.mao_wanandroid_client.model.home.HomeArticleListData;
import mao.com.mao_wanandroid_client.model.navigation.NavigationListData;
import mao.com.mao_wanandroid_client.model.project.ProjectClassifyData;
import mao.com.mao_wanandroid_client.model.project.ProjectListData;
import mao.com.mao_wanandroid_client.model.search.HotKeyData;
import mao.com.mao_wanandroid_client.model.tree.KnowledgeHierarchyData;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Api接口
 */
public interface ApiService {

    String HOST = "http://www.wanandroid.com";

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
     *  搜索热词
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
    Observable<ResponseBody<HomeArticleListData>> getKnowledgeTreeDetialData(@Path("pageNum") int pageNum, @Query("cid") int cid);

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

    
}
