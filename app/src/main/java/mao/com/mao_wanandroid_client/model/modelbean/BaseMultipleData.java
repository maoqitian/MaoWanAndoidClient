package mao.com.mao_wanandroid_client.model.modelbean;

/**
 * @author maoqitian
 * @Description: base multiple data 数据多个
 * @date 2019/8/29 0029 16:25
 */
public class BaseMultipleData<T,V> {

    private T data1;

    private V data2;

    public T getData1() {
        return data1;
    }

    public void setData1(T data1) {
        this.data1 = data1;
    }

    public V getData2() {
        return data2;
    }

    public void setData2(V data2) {
        this.data2 = data2;
    }

    @Override
    public String toString() {
        return "BaseMultipleData{" +
                "data1=" + data1 +
                ", data2=" + data2 +
                '}';
    }
}
