package mao.com.mao_wanandroid_client.model.modelbean.rank;

/**
 * @author maoqitian
 * @Description: rank 数据
 * @date 2019/8/29 0029 16:26
 */
public class RankData {


    /**
     * coinCount : 621
     * rank : 26
     * userId : 863
     * username : m**qitian
     */

    private int coinCount;
    private int rank;
    private int userId;
    private String username;

    public int getCoinCount() {
        return coinCount;
    }

    public void setCoinCount(int coinCount) {
        this.coinCount = coinCount;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
