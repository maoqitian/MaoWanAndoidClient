package mao.com.mao_wanandroid_client.model.modelbean.rank;

/**
 * @author maoqitian
 * @Description: 积分记录 data
 * @date 2019/8/29 0029 16:51
 */
public class CoinRecordData {


    /**
     * coinCount : 23
     * date : 1567008171000
     * desc : 2019-08-29 00:02:51 签到,积分：10 + 13
     * id : 33638
     * type : 1
     * userId : 863
     * userName : maoqitian
     */

    private int coinCount;
    private long date;
    private String desc;
    private int id;
    private int type;
    private int userId;
    private String userName;

    public int getCoinCount() {
        return coinCount;
    }

    public void setCoinCount(int coinCount) {
        this.coinCount = coinCount;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
