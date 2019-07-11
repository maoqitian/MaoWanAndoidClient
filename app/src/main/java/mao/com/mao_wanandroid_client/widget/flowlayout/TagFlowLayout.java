package mao.com.mao_wanandroid_client.widget.flowlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * @author maoqitian
 * @Description tag 流式布局
 *  测量与子view 的摆放已经由FlowLayout完成，TagFlowLayout只需要关心数据
 * @Time 2019/7/10 0010 23:08
 */
public class TagFlowLayout extends FlowLayout implements TagAdapter.OnDataSetChangedListener {


    private TagAdapter mAdapter;
    /**
     * 设置可以选择多少个tag  默认单选
     * 设置为零 则没有单选和多选效果，纯展示tag
     */
    private int mMaxSelectCount = 1;

    public TagFlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public void setMaxSelectCount(int maxSelectCount) {
        this.mMaxSelectCount = maxSelectCount;
    }

    /**
     * 类似 RecycleView 的方式设置数据给 TagFlowLayout
     * @param adapter
     */
    public void  setAdapter(TagAdapter adapter){
         this.mAdapter = adapter;

         mAdapter.setOnDataSetChangedListener(this);

         //设置了adapter 则说明数据发生了变化
        onDataChanged();
    }
    //数据发生变化，根据外部使用者传递的数据来创建我们的流式布局的子view,并完成数据绑定加入到TagFlowLayout
    @Override
    public void onDataChanged() {
         removeAllViews();
         TagAdapter tagAdapter = mAdapter;
         for (int i = 0; i < tagAdapter.getItemCount(); i++) {
             View view = tagAdapter.createView(LayoutInflater.from(getContext()), this, i);
             tagAdapter.bindView(view,i);
             addView(view);
             //用户预设置状态 进行处理
             if(view.isSelected()){
                tagAdapter.onItemSelected(view,i);
             }else {
                 tagAdapter.onItemUnSelected(view,i);
             }
             bindViewMethod(view,i);
         }
    }

    /**
     * 单选，用户选择下一个则上一个选中的自动取消选择
     * 多选，上一个选中的不会自动取消选择，用户需要自己手动反选
     * @param view
     * @param position
     */
    private void bindViewMethod(View view, int position) {

        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //补偿 itemView 的点击事件
                mAdapter.onTagItemViewClick(v,position);

                // 不可选择
                if(mMaxSelectCount <= 0){
                    return;
                }
                //可以单选或者多选
                if(!v.isSelected()){
                    //如果view 没有被选中
                    if(getSelectChildCount() >= mMaxSelectCount){
                        //已经选中的view数量 大于最大选择数量
                        //单选 情况 获取已经选择view 取消选择
                        if(getSelectChildCount() == 1){
                           View selectView = getSelectChildView();
                           if(selectView != null){
                               selectView.setSelected(false);
                               mAdapter.onItemSelected(selectView,getSelectChildPosition(selectView));
                           }
                        }else {
                            //多选的情况大于最大选择数
                            mAdapter.tipForSelectMax(v,mMaxSelectCount);
                            return;
                        }
                    }
                }

                if(v.isSelected()){
                   //被选中
                   v.setSelected(false);
                   mAdapter.onItemUnSelected(v,position);
                }else {
                    //未被选中
                    v.setSelected(true);
                    mAdapter.onItemSelected(v,position);
                }
            }
        });
    }

    /**
     * 获取选中子View 的位置集合
     * @return
     */
    public List<Integer> getSelectPositionItemList(){
        List<Integer> selectPositionItemList = new ArrayList<>();
        for (int i = 0; i < getChildCount(); i++) {
            View itemView = getChildAt(i);
            if(itemView.isSelected()){
                selectPositionItemList.add(i);
            }
        }
        return selectPositionItemList;
    }

    //获取被选中的子view position
    private int getSelectChildPosition(View selectView) {
        for (int i = 0; i < getChildCount(); i++) {
            View itemView = getChildAt(i);
            if(itemView == selectView){
                return i;
            }
        }
        return 0;
    }

    //获取被选中的子View
    private View getSelectChildView() {
        for (int i = 0; i < getChildCount(); i++) {
            View itemView = getChildAt(i);
            if(itemView.isSelected()){
                return itemView;
            }
        }
        return null;
    }

    /**
     * 获取被选中的子view 数量
     * @return
     */
    private int getSelectChildCount() {
        int count = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View itemView = getChildAt(i);
            if(itemView.isSelected()){
               count++;
            }
        }
        return count;
    }

}
