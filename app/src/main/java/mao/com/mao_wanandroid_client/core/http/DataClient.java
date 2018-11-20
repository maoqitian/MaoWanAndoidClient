package mao.com.mao_wanandroid_client.core.http;

import java.util.List;

import io.reactivex.Observable;
import mao.com.mao_wanandroid_client.core.dao.SearchHistoryData;
import mao.com.mao_wanandroid_client.core.db.DbHelper;
import mao.com.mao_wanandroid_client.core.http.helper.IHttpHelper;
import mao.com.mao_wanandroid_client.core.sp.SharedPreferenceHelper;
import mao.com.mao_wanandroid_client.model.ResponseBody;
import mao.com.mao_wanandroid_client.model.banner.HomePageBannerModel;
import mao.com.mao_wanandroid_client.model.frienduser.FriendUseWebData;
import mao.com.mao_wanandroid_client.model.home.HomeArticleListData;
import mao.com.mao_wanandroid_client.model.login.LoginData;
import mao.com.mao_wanandroid_client.model.navigation.NavigationListData;
import mao.com.mao_wanandroid_client.model.project.ProjectClassifyData;
import mao.com.mao_wanandroid_client.model.project.ProjectListData;
import mao.com.mao_wanandroid_client.model.search.HotKeyData;
import mao.com.mao_wanandroid_client.model.tree.KnowledgeHierarchyData;
import mao.com.mao_wanandroid_client.model.webmark.webBookMark;

/**
 * @author maoqitian
 * @Description 数据获取
 * @Time 2018/10/14 0014 15:45
 */
public class DataClient implements IHttpHelper,SharedPreferenceHelper,DbHelper {


    @Override
    public List<SearchHistoryData> addSearchHistoryData(String data) {
        return null;
    }

    @Override
    public List<SearchHistoryData> loadAllSearchHistoryData() {
        return null;
    }

    @Override
    public void clearAllSearchHistoryData() {

    }

    @Override
    public Observable<ResponseBody<HomeArticleListData>> HomeArticleListData(int pageNum) {
        return null;
    }

    @Override
    public Observable<ResponseBody<List<HomePageBannerModel>>> GetHomePageBannerData() {
        return null;
    }

    @Override
    public Observable<ResponseBody<List<FriendUseWebData>>> GetFriendUseWebData() {
        return null;
    }

    @Override
    public Observable<ResponseBody<List<HotKeyData>>> GetHotKeyData() {
        return null;
    }

    @Override
    public Observable<ResponseBody<List<KnowledgeHierarchyData>>> getKnowledgeHierarchyData() {
        return null;
    }

    @Override
    public Observable<ResponseBody<HomeArticleListData>> getKnowledgeTreeDetialData(int pageNum, int cid) {
        return null;
    }

    @Override
    public Observable<ResponseBody<NavigationListData>> getNavigationListData() {
        return null;
    }

    @Override
    public Observable<ResponseBody<List<ProjectClassifyData>>> getProjectClassifyData() {
        return null;
    }

    @Override
    public Observable<ResponseBody<ProjectListData>> getProjectListData(int pageNum, int cid) {
        return null;
    }

    @Override
    public Observable<ResponseBody<LoginData>> postLoginData(String username, String password) {
        return null;
    }

    @Override
    public Observable<ResponseBody<LoginData>> postRegisterData(String username, String password, String repassword) {
        return null;
    }

    @Override
    public Observable<ResponseBody<String>> getLoginOut() {
        return null;
    }

    @Override
    public Observable<ResponseBody<HomeArticleListData>> getCollectListData(int pageNum) {
        return null;
    }

    @Override
    public Observable<ResponseBody<HomeArticleListData>> getCollectInsideListData(int articleId) {
        return null;
    }

    @Override
    public Observable<ResponseBody<HomeArticleListData>> getCollectOutsideListData(String title, String author, String link) {
        return null;
    }

    @Override
    public Observable<ResponseBody<HomeArticleListData>> cancelCollectArticleListData(int articleId) {
        return null;
    }

    @Override
    public Observable<ResponseBody<HomeArticleListData>> cancelCollectArticlePageData(int articleId, int originId) {
        return null;
    }

    @Override
    public Observable<ResponseBody<List<webBookMark>>> getWebBookMark() {
        return null;
    }

    @Override
    public Observable<ResponseBody<webBookMark>> addWebBookMark(String name, String link) {
        return null;
    }

    @Override
    public Observable<ResponseBody<webBookMark>> updateWebBookMark(String id, String name, String link) {
        return null;
    }

    @Override
    public Observable<ResponseBody> deleteWebBookMark(String id) {
        return null;
    }

    @Override
    public Observable<ResponseBody<ProjectListData>> getSearchKeyWordData(int pageNum, String keyWord) {
        return null;
    }

    @Override
    public void setLoginAccount(String account) {

    }

    @Override
    public void setLoginPassword(String password) {

    }

    @Override
    public String getLoginAccount() {
        return null;
    }

    @Override
    public String getLoginPassword() {
        return null;
    }

    @Override
    public void setLoginStatus(boolean loginStatus) {

    }

    @Override
    public boolean getLoginStatus() {
        return false;
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
