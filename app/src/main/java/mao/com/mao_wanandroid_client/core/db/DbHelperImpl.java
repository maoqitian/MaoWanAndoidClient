package mao.com.mao_wanandroid_client.core.db;

import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import mao.com.mao_wanandroid_client.application.MyApplication;
import mao.com.mao_wanandroid_client.core.dao.DaoSession;
import mao.com.mao_wanandroid_client.core.dao.SearchHistoryData;
import mao.com.mao_wanandroid_client.core.dao.SearchHistoryDataDao;

/**
 * @author maoqitian
 * @Description 搜索历史数据 数据库操作
 * @Time 2018/10/30 0030 23:43
 */
public class DbHelperImpl implements DbHelper {

    //最多保存10条历史数据
    private static final int SEARCH_HISTORY_LIST_SIZE = 10;

    private DaoSession daoSession;
    private List<SearchHistoryData> mSearchHistoryDataList;
    private SearchHistoryData mSearchHistoryData;
    private String data;

    @Inject //注解@Inject用在构造方法上表示告诉Dagger 2可以使用此构造方法提供需要的实例
    public DbHelperImpl(){
        daoSession=MyApplication.getInstance().getDaoSession();
    }


    @Override
    public List<SearchHistoryData> addSearchHistoryData(String data) {
        this.data=data;
        mSearchHistoryDataList = getSearchHistoryDataList();
        mSearchHistoryData = createSearchHistoryData(data);
        if(historyDataForward()){
            return mSearchHistoryDataList;
        }
        //搜索历史数据不存在则加入到mSearchHistoryDataList（是否超过list的大小）
        if(mSearchHistoryDataList.size()<SEARCH_HISTORY_LIST_SIZE){
            getSearchHistoryDataDao().insert(mSearchHistoryData);
        }else {
            //删除最早保存的数据
            mSearchHistoryDataList.remove(0);
            mSearchHistoryDataList.add(mSearchHistoryData);
            getSearchHistoryDataDao().deleteAll();
            getSearchHistoryDataDao().insertInTx(mSearchHistoryDataList);
        }
        return mSearchHistoryDataList;
    }

    /**
     * 查询数据库 当前搜索的数据是否已经存在数据库中，存在则将该数据放到列表第一位
     * @return
     */
    private boolean historyDataForward() {
        Iterator<SearchHistoryData> iterator = mSearchHistoryDataList.iterator();
        while(iterator.hasNext()){
            SearchHistoryData existData = iterator.next();
            if(existData.getData().equals(data)){
                //如果存在，则放到list 第一位
                mSearchHistoryDataList.remove(existData);
                mSearchHistoryDataList.add(mSearchHistoryData);
                getSearchHistoryDataDao().deleteAll();
                getSearchHistoryDataDao().insertInTx(mSearchHistoryDataList);
                return true;
            }
        }
        return false;
    }

    private SearchHistoryData createSearchHistoryData(String data) {
        SearchHistoryData searchHistoryData=new SearchHistoryData();
        searchHistoryData.setData(data);
        searchHistoryData.setDate(System.currentTimeMillis());
        return searchHistoryData;
    }

    private List<SearchHistoryData> getSearchHistoryDataList() {
        return getSearchHistoryDataDao().loadAll();
    }

    @Override
    public List<SearchHistoryData> loadAllSearchHistoryData() {
        return getSearchHistoryDataDao().loadAll();
    }

    @Override
    public void clearAllSearchHistoryData() {
         getSearchHistoryDataDao().deleteAll();
    }

    private SearchHistoryDataDao getSearchHistoryDataDao(){
        return daoSession.getSearchHistoryDataDao();
    }
}
