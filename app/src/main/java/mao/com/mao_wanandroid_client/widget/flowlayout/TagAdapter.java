package mao.com.mao_wanandroid_client.widget.flowlayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author maoqitian
 * @Description adapter 抽象类，不用接口的原因是使用adapter时不必实现所有的抽象方法，可以根据需求酌情实现自己的adapter
 * @Time 2019/7/10 0010 23:15
 */
public abstract class TagAdapter {

    /**
     * 子元素的数量
     * @return
     */
    public abstract int getItemCount();
    /**
     * 创建子View
     * @return
     */
    public abstract View createView(LayoutInflater inflater, ViewGroup parent,int position);

    /**
     * 绑定数据 根据外部数据确定子view 显示数据
     * @param view
     * @param position
     */
    public abstract void bindView(View view,int position);

}
