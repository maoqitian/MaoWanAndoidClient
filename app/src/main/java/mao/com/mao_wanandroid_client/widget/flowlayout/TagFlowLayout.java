package mao.com.mao_wanandroid_client.widget.flowlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

/**
 * @author maoqitian
 * @Description tag 流式布局
 *  测量与子view 的摆放已经由FlowLayout完成，TagFlowLayout只需要关心数据
 * @Time 2019/7/10 0010 23:08
 */
public class TagFlowLayout extends FlowLayout {


    private TagAdapter mAdapter;

    public TagFlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public void  setAdapter(TagAdapter adapter){
         this.mAdapter = adapter;
         //设置了adapter 则说明数据发生了变化
         onDataChange();
    }
    //数据发生变化，根据外部使用者传递的数据来创建我们的流式布局的子view,并完成数据绑定加入到TagFlowLayout
    private void onDataChange() {
         removeAllViews();
         TagAdapter tagAdapter = mAdapter;
         for (int i = 0; i < tagAdapter.getItemCount(); i++) {
             View view = tagAdapter.createView(LayoutInflater.from(getContext()), this, i);
             tagAdapter.bindView(view,i);
             addView(view);
         }
    }

}
