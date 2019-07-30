package mao.com.mao_wanandroid_client.core.http;

import java.util.List;

import io.reactivex.Observable;
import mao.com.mao_wanandroid_client.core.dao.SearchHistoryData;
import mao.com.mao_wanandroid_client.core.db.DbHelper;
import mao.com.mao_wanandroid_client.core.http.helper.IHttpHelper;
import mao.com.mao_wanandroid_client.core.sp.SharedPreferenceHelper;
import mao.com.mao_wanandroid_client.model.ResponseBody;
import mao.com.mao_wanandroid_client.model.banner.HomePageBannerModel;
import mao.com.mao_wanandroid_client.model.collect.CollectData;
import mao.com.mao_wanandroid_client.model.collect.CollectListData;
import mao.com.mao_wanandroid_client.model.frienduser.CommonWebData;
import mao.com.mao_wanandroid_client.model.home.HomeArticleData;
import mao.com.mao_wanandroid_client.model.home.HomeArticleListData;
import mao.com.mao_wanandroid_client.model.login.LoginData;
import mao.com.mao_wanandroid_client.model.navigation.NavigationListData;
import mao.com.mao_wanandroid_client.model.project.ProjectClassifyData;
import mao.com.mao_wanandroid_client.model.project.ProjectListData;
import mao.com.mao_wanandroid_client.model.search.HotKeyData;
import mao.com.mao_wanandroid_client.model.knowlegetree.KnowledgeHierarchyData;
import mao.com.mao_wanandroid_client.model.webmark.webBookMark;

/**
 * @author maoqitian
 * @Description 数据获取
 * @Time 2018/10/14 0014 15:45
 */
public class DataClient implements IHttpHelper,SharedPreferenceHelper,DbHelper {

    private IHttpHelper mIHttpHelper;
    private SharedPreferenceHelper mSharedPreferenceHelper;
    private DbHelper mDbHelper;

    public DataClient(IHttpHelper mIHttpHelper,
                      SharedPreferenceHelper mSharedPreferenceHelper,
                      DbHelper mDbHelper){
        this.mIHttpHelper=mIHttpHelper;
        this.mSharedPreferenceHelper=mSharedPreferenceHelper;
        this.mDbHelper=mDbHelper;
    }


    /**
     * 搜索历史数据  GreenDao 数据库缓存
     * @param data
     * @return
     */
    @Override
    public List<SearchHistoryData> addSearchHistoryData(String data) {
        return mDbHelper.addSearchHistoryData(data);
    }

    @Override
    public List<SearchHistoryData> loadAllSearchHistoryData() {
        return mDbHelper.loadAllSearchHistoryData();
    }

    @Override
    public void clearAllSearchHistoryData() {
        mDbHelper.clearAllSearchHistoryData();
    }

    /**
     * 网络数据请求
     */

    @Override
    public Observable<ResponseBody<HomeArticleListData>> HomeArticleListData(int pageNum) {
        return mIHttpHelper.HomeArticleListData(pageNum);
    }

    @Override
    public Observable<ResponseBody<List<HomePageBannerModel>>> GetHomePageBannerData() {
        return mIHttpHelper.GetHomePageBannerData();
    }

    @Override
    public Observable<ResponseBody<HomeArticleListData>> HomeArticleListProjectData(int pageNum) {
        return mIHttpHelper.HomeArticleListProjectData(pageNum);
    }

    @Override
    public Observable<ResponseBody<List<CommonWebData>>> GetFriendUseWebData() {
        return mIHttpHelper.GetFriendUseWebData();
    }

    @Override
    public Observable<ResponseBody<List<HotKeyData>>> GetHotKeyData() {
        return mIHttpHelper.GetHotKeyData();
    }

    @Override
    public Observable<ResponseBody<List<HomeArticleData>>> HomeTopArticleData() {
        return mIHttpHelper.HomeTopArticleData();
    }

    @Override
    public Observable<ResponseBody<List<KnowledgeHierarchyData>>> getKnowledgeHierarchyData() {
        return mIHttpHelper.getKnowledgeHierarchyData();
    }

    @Override
    public Observable<ResponseBody<HomeArticleListData>> getKnowledgeTreeDetailData(int pageNum, int cid) {
        return mIHttpHelper.getKnowledgeTreeDetailData(pageNum,cid);
    }

    @Override
    public Observable<ResponseBody<List<NavigationListData>>> getNavigationListData() {
        return mIHttpHelper.getNavigationListData();
    }

    @Override
    public Observable<ResponseBody<List<ProjectClassifyData>>> getProjectClassifyData() {
        return mIHttpHelper.getProjectClassifyData();
    }

    @Override
    public Observable<ResponseBody<ProjectListData>> getProjectListData(int pageNum, int cid) {
        return mIHttpHelper.getProjectListData(pageNum,cid);
    }

    @Override
    public Observable<ResponseBody<LoginData>> postLoginData(String username, String password) {
        return mIHttpHelper.postLoginData(username,password);
    }

    @Override
    public Observable<ResponseBody<LoginData>> postSignUpData(String username, String password, String repassword) {
        return mIHttpHelper.postSignUpData(username,password,repassword);
    }

    @Override
    public Observable<ResponseBody<String>> getSignOut() {
        return mIHttpHelper.getSignOut();
    }

    @Override
    public Observable<ResponseBody<CollectListData>> getCollectListData(int pageNum) {
        return mIHttpHelper.getCollectListData(pageNum);
    }

    @Override
    public Observable<ResponseBody<String>> addCollectInsideListData(int articleId) {
        return mIHttpHelper.addCollectInsideListData(articleId);
    }

    @Override
    public Observable<ResponseBody<CollectData>> addCollectOutsideListData(String title, String author, String link) {
        return mIHttpHelper.addCollectOutsideListData(title,author,link);
    }

    @Override
    public Observable<ResponseBody<String>> cancelCollectArticleListData(int articleId) {
        return mIHttpHelper.cancelCollectArticleListData(articleId);
    }

    @Override
    public Observable<ResponseBody<String>> cancelCollectArticlePageData(int articleId, int originId) {
        return mIHttpHelper.cancelCollectArticlePageData(articleId,originId);
    }

    @Override
    public Observable<ResponseBody<List<webBookMark>>> getWebBookMark() {
        return mIHttpHelper.getWebBookMark();
    }

    @Override
    public Observable<ResponseBody<webBookMark>> addWebBookMark(String name, String link) {
        return mIHttpHelper.addWebBookMark(name,link);
    }

    @Override
    public Observable<ResponseBody<webBookMark>> updateWebBookMark(String id, String name, String link) {
        return mIHttpHelper.updateWebBookMark(id,name,link);
    }

    @Override
    public Observable<ResponseBody> deleteWebBookMark(String id) {
        return mIHttpHelper.deleteWebBookMark(id);
    }

    @Override
    public Observable<ResponseBody<HomeArticleListData>> getSearchKeyWordData(int pageNum, String keyWord) {
        return mIHttpHelper.getSearchKeyWordData(pageNum,keyWord);
    }

    @Override
    public Observable<ResponseBody<List<KnowledgeHierarchyData>>> getWxArticle() {
        return mIHttpHelper.getWxArticle();
    }

    @Override
    public Observable<ResponseBody<HomeArticleListData>> getWxArticleHistory(int id, int pageNum) {
        return mIHttpHelper.getWxArticleHistory(id,pageNum);
    }

    @Override
    public Observable<ResponseBody<HomeArticleListData>> getWxArticleHistoryByKey(int id, int pageNum, String key) {
        return mIHttpHelper.getWxArticleHistoryByKey(id,pageNum,key);
    }

    @Override
    public void setLoginUserName(String userName) {
        mSharedPreferenceHelper.setLoginUserName(userName);
    }

    @Override
    public void setLoginPassword(String password) {
        mSharedPreferenceHelper.setLoginPassword(password);
    }

    @Override
    public String getLoginUserName() {
        return mSharedPreferenceHelper.getLoginUserName();
    }

    @Override
    public String getLoginPassword() {
        return mSharedPreferenceHelper.getLoginPassword();
    }

    @Override
    public void setLoginStatus(boolean loginStatus) {
        mSharedPreferenceHelper.setLoginStatus(loginStatus);
    }

    @Override
    public boolean getLoginStatus() {
        return mSharedPreferenceHelper.getLoginStatus();
    }

    @Override
    public void setCookie(String domain, String cookie) {

    }

    @Override
    public String getCookie(String domain) {
        return null;
    }

    @Override
    public void setCurrentPage(int position) {

    }

    @Override
    public int getCurrentPage() {
        return 0;
    }

    @Override
    public void setProjectCurrentPage(int position) {

    }

    @Override
    public int getProjectCurrentPage() {
        return 0;
    }

    @Override
    public boolean getAutoCacheState() {
        return false;
    }

    @Override
    public boolean getNoImageState() {
        return false;
    }

    @Override
    public boolean getNightModeState() {
        return false;
    }

    @Override
    public void setNightModeState(boolean b) {

    }

    @Override
    public void setNoImageState(boolean b) {

    }

    @Override
    public void setAutoCacheState(boolean b) {

    }
}
