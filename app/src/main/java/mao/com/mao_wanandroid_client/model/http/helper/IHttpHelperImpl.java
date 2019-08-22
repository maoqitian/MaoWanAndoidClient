package mao.com.mao_wanandroid_client.model.http.helper;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import mao.com.mao_wanandroid_client.model.http.api.ApiService;
import mao.com.mao_wanandroid_client.model.http.tools.NetworkUtils;
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
import mao.com.mao_wanandroid_client.model.modelbean.search.HotKeyData;
import mao.com.mao_wanandroid_client.model.modelbean.knowlegetree.KnowledgeHierarchyData;
import mao.com.mao_wanandroid_client.model.modelbean.webmark.WebBookMark;


/**
 * @author maoqitian
 * @Description 网络请求帮助实现类 获取数据 Observable
 * @Time 2018/11/12 0012 20:25
 */
public class IHttpHelperImpl implements IHttpHelper{

    public NetworkUtils mNetworkUtils;

    @Inject
    public IHttpHelperImpl(NetworkUtils networkUtils){
        this.mNetworkUtils=networkUtils;
    }

    private ApiService getApiServiceGson(){
        return mNetworkUtils.getApiService(ApiService.class,ApiService.HOST,true);
    }

    private ApiService getApiServiceNotGson(){
        return mNetworkUtils.getApiService(ApiService.class,ApiService.HOST,false);
    }
    /**
     * 获取首页文章数据列表
     * @param pageNum 页码 从0开始
     * @return
     */
    @Override
    public Observable<ResponseBody<HomeArticleListData>> HomeArticleListData(int pageNum) {
        return getApiServiceGson().HomeArticleListData(pageNum);
    }

    /**
     * 获取首页Banner数据
     * @return
     */
    @Override
    public Observable<ResponseBody<List<HomePageBannerModel>>> GetHomePageBannerData() {
        return getApiServiceGson().GetHomePageBannerData();
    }
    /**
     * 最新项目tab (首页的第二个tab)
     * @param pageNum 页码，拼接在连接中，从0开始。
     * @return
     */
    @Override
    public Observable<ResponseBody<HomeArticleListData>> HomeArticleListProjectData(int pageNum) {
        return getApiServiceGson().HomeArticleListProjectData(pageNum);
    }

    /**
     * 获取常用网站数据
     * @return
     */
    @Override
    public Observable<ResponseBody<List<CommonWebData>>> GetFriendUseWebData() {
        return getApiServiceGson().GetFriendUseWebData();
    }

    /**
     * 搜索热词
     * @return
     */
    @Override
    public Observable<ResponseBody<List<HotKeyData>>> GetHotKeyData() {
        return getApiServiceGson().GetHotKeyData();
    }

    /**
     * 置顶文章
     * @return
     */
    @Override
    public Observable<ResponseBody<List<HomeArticleData>>> HomeTopArticleData() {
        return getApiServiceGson().HomeTopArticleData();
    }

    /**
     * 知识体系数据
     * @return
     */
    @Override
    public Observable<ResponseBody<List<KnowledgeHierarchyData>>> getKnowledgeHierarchyData() {
        return getApiServiceGson().getKnowledgeHierarchyData();
    }
    /**
     * 根据知识体系 id 获取知识体系下的文章
     * @param pageNum 页码：拼接在链接上，从0开始
     * @param cid 分类的id 上述二级目录的id
     * @return
     */
    @Override
    public Observable<ResponseBody<HomeArticleListData>> getKnowledgeTreeDetailData(int pageNum, int cid) {
        return getApiServiceGson().getKnowledgeTreeDetailData(pageNum,cid);
    }
    /**
     * 获取导航数据(网站 个人博客 社区等网站导航数据)
     * @return
     */
    @Override
    public Observable<ResponseBody<List<NavigationListData>>> getNavigationListData() {
        return getApiServiceGson().getNavigationListData();
    }

    /**
     * 项目分类 项目为包含一个分类，该接口返回整个分类。
     * @return
     */
    @Override
    public Observable<ResponseBody<List<ProjectClassifyData>>> getProjectClassifyData() {
        return getApiServiceGson().getProjectClassifyData();
    }

    /**
     * 项目列表数据 某一个分类下项目列表数据，分页展示
     * @param pageNum 拼接在链接中，从1开始。
     * @param cid 分类的id，上面项目分类接口
     * @return
     */
    @Override
    public Observable<ResponseBody<ProjectListData>> getProjectListData(int pageNum, int cid) {
        return getApiServiceGson().getProjectListData(pageNum,cid);
    }
    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @Override
    public Observable<ResponseBody<LoginData>> postLoginData(String username, String password) {
        return getApiServiceGson().postLoginData(username,password);
    }
    /**
     * 注册
     * @param username 用户名
     * @param password 密码
     * @param repassword 确认密码
     * @return
     */
    @Override
    public Observable<ResponseBody<LoginData>> postSignUpData(String username, String password, String repassword) {
        return getApiServiceGson().postSignUpData(username,password,repassword);
    }
    /**
     * 退出登录
     * @return
     */
    @Override
    public Observable<ResponseBody<String>> getSignOut() {
        return getApiServiceGson().getSignOut();
    }
    /**
     * 收藏文章列表
     * @param pageNum 页码：拼接在链接中，从0开始。
     * @return
     */
    @Override
    public Observable<ResponseBody<CollectListData>> getCollectListData(int pageNum) {
        return getApiServiceGson().getCollectListData(pageNum);
    }
    /**
     * 收藏站内文章
     * @param articleId 文章id，拼接在链接中。
     * @return
     */
    @Override
    public Observable<ResponseBody<String>> addCollectInsideListData(int articleId) {
        return getApiServiceGson().getCollectInsideListData(articleId);
    }
    /**
     * 收藏站外文章
     * @param title
     * @param author
     * @param link
     * @return
     */
    @Override
    public Observable<ResponseBody<CollectData>> addCollectOutsideListData(String title, String author, String link) {
        return getApiServiceGson().getCollectOutsideListData(title,author,link);
    }
    /**
     * 文章列表 取消收藏
     * @param articleId 文章id:拼接在链接上
     * @return
     */
    @Override
    public Observable<ResponseBody<String>> cancelCollectArticleListData(int articleId) {
        return getApiServiceGson().cancelCollectArticleListData(articleId);
    }
    /**
     * 我的收藏页面  取消收藏（该页面包含自己录入的内容）
     * @param articleId 文章id:拼接在链接上
     * @param originId  originId:列表页下发，无则为-1 （originId 代表的是你收藏之前的那篇文章本身的id； 但是收藏支持主动添加，这种情况下，没有originId则为-1）
     * @return
     */
    @Override
    public Observable<ResponseBody<String>> cancelCollectArticlePageData(int articleId, int originId) {
        return getApiServiceGson().cancelCollectArticlePageData(articleId,-1);
    }
    /**
     * 获取收藏网站列表
     */
    @Override
    public Observable<ResponseBody<List<WebBookMark>>> getWebBookMark() {
        return getApiServiceGson().getWebBookMark();
    }
    /**
     * 添加收藏网址
     * @param name 网站名称
     * @param link 网站链接
     * @return
     */
    @Override
    public Observable<ResponseBody<WebBookMark>> addWebBookMark(String name, String link) {
        return getApiServiceGson().addWebBookMark(name,link);
    }
    /**
     * 编辑收藏网站
     * @param id 收藏网站 id
     * @param name 网站名称
     * @param link 网站链接
     * @return
     */
    @Override
    public Observable<ResponseBody<WebBookMark>> updateWebBookMark(int id, String name, String link) {
        return getApiServiceGson().updateWebBookMark(id,name,link);
    }
    /**
     * 删除收藏网站
     * @param id 收藏网站 id
     * @return
     */
    @Override
    public Observable<ResponseBody<String>> deleteWebBookMark(int id) {
        return getApiServiceGson().deleteWebBookMark(id);
    }
    /**
     * 搜索
     * @param pageNum 页码：拼接在链接上，从0开始
     * @param keyWord 搜索关键词
     * @return
     */
    @Override
    public Observable<ResponseBody<HomeArticleListData>> getSearchKeyWordData(int pageNum, String keyWord) {
        return getApiServiceGson().getSearchKeyWordData(pageNum,keyWord);
    }

    /**
     * 公众号相关
     */
    /**
     * 获取公众号列表
     * @return
     */
    @Override
    public Observable<ResponseBody<List<KnowledgeHierarchyData>>> getWxArticle() {
        return getApiServiceGson().getWxArticle();
    }
    /**
     * 查看某个公众号历史数据
     * @param id 公众号 ID：拼接在 url 中，eg:405
     * @param pageNum 公众号页码：拼接在url 中，eg:1
     * @return
     */
    @Override
    public Observable<ResponseBody<HomeArticleListData>> getWxArticleHistory(int id, int pageNum) {
        return getApiServiceGson().getWxArticleHistory(id,pageNum);
    }
    /**
     * 在某个公众号中搜索历史文章
     * @param id 公众号 ID：拼接在 url 中，eg:405
     * @param pageNum 公众号页码：拼接在url 中，eg:1
     * @param key 搜索关键词 k : 字符串，eg:Java
     * @return
     */
    @Override
    public Observable<ResponseBody<HomeArticleListData>> getWxArticleHistoryByKey(int id, int pageNum, String key) {
        return getApiServiceGson().getWxArticleHistoryByKey(id,pageNum,key);
    }


}
