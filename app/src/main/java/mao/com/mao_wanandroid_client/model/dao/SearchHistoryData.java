package mao.com.mao_wanandroid_client.model.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author maoqitian
 * @Description 搜索历史数据 greenDao数据库对应实体类
 * @Time 2018/11/1 0001 20:48
 */
@Entity
public class SearchHistoryData {
    @Id(autoincrement = true) //主键 自增
    private Long id;

    //数据
    private String data;

    //日期
    private long date;

    @Generated(hash = 528097393)
    public SearchHistoryData(Long id, String data, long date) {
        this.id = id;
        this.data = data;
        this.date = date;
    }

    @Generated(hash = 1885562190)
    public SearchHistoryData() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getData() {
        return this.data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public long getDate() {
        return this.date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
