package mao.com.mao_wanandroid_client.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author maoqitian
 * @Description: 自定义流式布局  参考鸿洋大佬视频 https://www.imooc.com/learn/1141
 * @date 2019/7/4 0004 9:45
 */
public class FlowLayout extends ViewGroup {

    //存放所有的view  List<View> 代表每一行的view 集合
    private List<List<View>> mAllView = new ArrayList<>();
    //每一行的行高 集合
    private List<Integer> mLineHeightList = new ArrayList<>();

    //使用系统属性来获取我们的自定义属性值
    private static final int[] LL = new int[]{android.R.attr.maxLines};

    //显示最大行数
    private int maxLine;

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs,LL);
        maxLine = typedArray.getInt(0,Integer.MAX_VALUE);
        typedArray.recycle();
    }


    //宽度确定
    //高度不定
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mAllView.clear();
        mLineHeightList.clear();

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);


        /**
         * 测量 三种模式 mode:
         * UNSPECIFIED 父控件不约束子view 要多宽高随意发挥 一般用于可以滑动的父控件
         * EXACTLY ： 确定的宽高
         * AT_MOST ： 取决于父控件的宽高 父容器有多大就是多大
         */

        //获取宽高与模式
        //高度已经确定，则不用管他的模式
        int sizeWith = MeasureSpec.getSize(widthMeasureSpec);
        //int modeWith = MeasureSpec.getMode(widthMeasureSpec);

        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);


        int lineWidth = 0; //行宽
        int lineHeight = 0; //行高

        int height = 0; //总高

        //一行所有的 view
        List<View> lineViews = new ArrayList<>();


           //根据子view 的高度来确定 FlowLayout的高度，遍历所有子view占据的高度来设置给 FlowLayout
            for (int i = 0; i <getChildCount() ; i++) {
                View childView = getChildAt(i);

                if(childView.getVisibility() == View.GONE){
                    //view 隐藏则不进行操作
                    continue;
                }

                //每个子view 测量
                measureChild(childView,widthMeasureSpec,heightMeasureSpec);
                MarginLayoutParams mLP= (MarginLayoutParams) childView.getLayoutParams();

                //子view 宽高
                int childWidth = childView.getMeasuredWidth() + mLP.leftMargin + mLP.rightMargin;
                int childHeight = childView.getMeasuredHeight() + mLP.topMargin + mLP.bottomMargin;

                if(lineWidth + childWidth > sizeWith - (getPaddingLeft()+getPaddingRight())){
                    //当前宽度和 大于容器宽度 换行(出去设置 padding 的影响)
                    height += lineHeight;

                    //换行保存 新的一行
                    mLineHeightList.add(lineHeight);
                    mAllView.add(lineViews);
                    //新的一行
                    lineViews = new ArrayList<>();
                    lineViews.add(childView);

                    //重置宽高度
                    lineWidth = childWidth;
                    lineHeight = childHeight;

                }else {
                    //未换行
                    lineWidth += childWidth;
                    lineHeight = Math.max(lineHeight,childHeight); //行高等于 行高或者子View 的最大值

                    lineViews.add(childView);

                }
                //最后一行 加上行高度
                if(i == getChildCount()-1){
                    height += lineHeight;
                    //最后一行无换行 则多补上一行
                    mLineHeightList.add(lineHeight);
                    mAllView.add(lineViews);
                }
            }

        if(maxLine < mLineHeightList.size()){
            height = getMaxLinesHeight();
        }

        if(modeHeight == MeasureSpec.EXACTLY ){ //如果高度确定 则不再需要测量
            height = sizeHeight;
        }else if(modeHeight == MeasureSpec.AT_MOST) {  //不确定
            height = Math.min(sizeHeight,height);
            height = height + getPaddingTop() + getPaddingBottom();
        }else if(modeHeight == MeasureSpec.UNSPECIFIED){ //子view 不受 flowlayout 约束
            height = height + getPaddingTop() + getPaddingBottom();
        }

        //调用 setMeasuredDimension(sizeWith,height) 确定宽高度
        setMeasuredDimension(sizeWith,height);


    }
    //根据设置的最大行数效验高度 只显示几行高度
    private int getMaxLinesHeight() {
        int height = 0;
        for (int i = 0; i < maxLine; i++) {
            height += mLineHeightList.get(i);
        }
        return height;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //摆放 view
        //初始位置
        int left = getPaddingLeft();
        int top = getPaddingTop();
        //遍历我们所有view
        for (int i = 0; i < mAllView.size(); i++) {
            //每一行 view
            List<View> lineViews = mAllView.get(i);
            //行高
            int lineHeight = mLineHeightList.get(i);

            for (int j = 0; j < lineViews.size(); j++) {
                View child = lineViews.get(j);
                MarginLayoutParams mLP = (MarginLayoutParams) child.getLayoutParams();
                //每个子 view 的位置
                int lc = left + mLP.leftMargin;
                int tc = top + mLP.topMargin;
                int rc = lc + child.getMeasuredWidth();
                int bc = tc + child.getMeasuredHeight();

                child.layout(lc,tc,rc,bc);

                //摆放一个之后平移
                left += child.getMeasuredWidth() + mLP.leftMargin + mLP.rightMargin;
            }

            //每一行摆放完成
            left = getPaddingLeft();
            top += lineHeight;

        }
    }


    //动态添加view 加载的方法
    //child 没有设置 MarginLayoutParams
    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
    }

    //inflater 加载
    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(),attrs);
    }

    //add view 加载
    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    // add view 加载
    @Override
    protected boolean checkLayoutParams(LayoutParams p) {
        return p instanceof MarginLayoutParams;
    }
}
