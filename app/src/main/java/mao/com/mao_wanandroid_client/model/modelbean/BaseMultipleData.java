package mao.com.mao_wanandroid_client.model.modelbean;

/**
 * @author maoqitian
 * @Description: multiple data 个人获取多个
 * @date 2019/8/29 0029 16:25
 */
public class BaseMultipleData<T,V> {

    private T coinInfo;

    private V shareArticles;

    public T getData1() {
        return coinInfo;
    }

    public void setData1(T coinInfo) {
        this.coinInfo = coinInfo;
    }

    public V getData2() {
        return shareArticles;
    }

    public void setData2(V data2) {
        this.shareArticles = data2;
    }

    @Override
    public String toString() {
        return "BaseMultipleData{" +
                "data1=" + coinInfo +
                ", data2=" + shareArticles +
                '}';
    }
}
