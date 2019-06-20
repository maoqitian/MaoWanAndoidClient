package mao.com.mao_wanandroid_client.widget.layoutbehavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

/**
 * @author maoqitian
 * @Description https://juejin.im/post/5a30f763f265da43062ac357
 * @Time 2019/6/20 0020 22:55
 */
public class FabBehavior extends CoordinatorLayout.Behavior {

    public FabBehavior() {
    }

    public FabBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private boolean visible = true;//是否可见


    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        //被观察者（RecyclerView）发生滑动的开始的时候回调的
        //nestedScrollAxes:滑动关联轴，现在只关心垂直的滑动。
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL || super.onStartNestedScroll(coordinatorLayout, child, directTargetChild,
                target, nestedScrollAxes);
    }


    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
        //被观察者滑动的时候回调的
        if (dyConsumed > 0 && visible) {
            //show
            visible = false;
            onHide(child);
        } else if (dyConsumed < 0) {
            //hide
            visible = true;
            onShow(child);
        }
    }

    public void onHide(View view) {
        // 隐藏动画--属性动画
        if (view instanceof Toolbar){
            ViewCompat.animate(view).translationY(-(view.getHeight() * 2)).setInterpolator(new AccelerateInterpolator(3));
        }else if (view instanceof FloatingActionButton){
            ViewCompat.animate(view).translationY(view.getHeight() * 2).setInterpolator(new AccelerateInterpolator(3));
        }else{

        }

    }

    public void onShow(View view) {
        // 显示动画--属性动画
        ViewCompat.animate(view).translationY(0).setInterpolator(new DecelerateInterpolator(3));

    }


}

