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

    /**
     * 每个 ItemView 的点击事件 （用户需要的时候自己实现）
     * @param v
     * @param position
     */
    public void onTagItemViewClick(View v, int position) {

    }

    /**
     * 通知 到达了最大选择数（用户需要的时候自己实现）
     * @param v
     * @param mMaxSelectCount
     */
    public void tipForSelectMax(View v, int mMaxSelectCount) {

    }

    /**
     * item 被选中 可以根据自己的需求灵活设置 UI 样式
     * @param v
     * @param position
     */
    public void onItemSelected(View v, int position) {

    }

    /**
     * item 不被选中 可以根据自己的需求灵活设置 UI 样式
     * @param v
     * @param position
     */
    public void onItemUnSelected(View v, int position) {

    }

    //接口回调方式 调用数据更新
    private OnDataSetChangedListener mListener;

    public void setOnDataSetChangedListener(OnDataSetChangedListener listener){
        mListener = listener;
    }

    public static interface OnDataSetChangedListener{
       void onDataChanged();
    }

    /**
     * 数据刷新
     * TagAdapter.notifyDataSetChanged
     */
    public void notifyDataSetChanged(){
       if(mListener != null){
           mListener.onDataChanged();
       }
    }
}
