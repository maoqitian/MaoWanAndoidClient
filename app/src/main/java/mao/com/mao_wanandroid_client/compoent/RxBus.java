package mao.com.mao_wanandroid_client.compoent;

import io.reactivex.Flowable;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.PublishProcessor;

/**
 * @author maoqitian
 * @Description 方便组件之间通信 有背压处理的 RxBus
 * @Time 2018/10/25 0025 18:04
 */
public class RxBus {

    private final FlowableProcessor<Object> mBus;

    private RxBus(){
        //toSerialized() 方法保证线程安全
        mBus=PublishProcessor.create().toSerialized();
    }

    private static class Holder{
        private static final RxBus RX_BUS=new RxBus();
    }
    public static RxBus getDefault(){
        return Holder.RX_BUS;
    }
    // 提供了一个新的事件
    public void post(Object obj){
        mBus.onNext(obj);
    }

    /**
     * 根据传递的 tClass 类型返回特定类型(tClass)的 被观察者
     * @param tClass 自己定义的订阅消息数据类
     * @param <T>
     * @return
     */
    public <T> Flowable<T> toFlowable(Class<T> tClass) {
        return mBus.ofType(tClass);
    }

    public Flowable<Object> toFlowable() {
        return mBus;
    }
    //是否订阅
    public boolean hasSubscribers() {
        return mBus.hasSubscribers();
    }
}
