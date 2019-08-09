package mao.com.mao_wanandroid_client.model.db;

import java.util.List;

import mao.com.mao_wanandroid_client.model.dao.SearchHistoryData;

/**
 * @author maoqitian
 * @Description
 * @Time 2018/10/28 0028 23:57
 */
public interface DbHelper {

    /**
     * 添加搜索历史数据
     * @param data
     * @return
     */
    List<SearchHistoryData> addSearchHistoryData(String data);

    /**
     * 加载所有历史数据
     * @return
     */
    List<SearchHistoryData> loadAllSearchHistoryData();

    /**
     * 清理所有搜索历史
     */
    void clearAllSearchHistoryData();
}
