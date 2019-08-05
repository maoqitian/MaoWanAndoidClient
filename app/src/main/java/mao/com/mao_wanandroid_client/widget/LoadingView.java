package mao.com.mao_wanandroid_client.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;

import mao.com.mao_wanandroid_client.R;

/**
 * @author maoqitian
 * @Description 酷炫加载view ( 参照地址 https://www.imooc.com/article/51132)
 * @Time 2018/12/25 0025 20:14
 */
public class LoadingView extends LinearLayout {

    private ShapeView shapeView;
    // 中间的阴影
    private View mShadowView;
    private int mTranslationDistance = 0;
    // 动画执行的时间
    private final long ANIMATOR_DURATION = 500;
    // 是否停止动画
    private boolean mIsStopAnimator = false;


    public LoadingView(Context context) {
        super(context);
        initLayout();
    }

    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLayout();
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLayout();
    }

    private void initLayout() {
        mTranslationDistance = dip2px(80);
        //  加载写好的 loading_view
        //  实例化View
        // this 代表把 loading_view 加载到 LoadingView 中
        inflate(getContext(), R.layout.loading_view_layout,this);
        shapeView =  findViewById(R.id.shape_view);
        mShadowView = findViewById(R.id.shadow_view);

        // onResume 之后View绘制流程执行完毕之后
        //post(this::startFallAnimator);

    }
    // 开始下落动画
    public void startFallAnimator() {
        if (mIsStopAnimator) {
            return;
        }
        // 动画作用在谁的身上
        // 下落位移动画
        ObjectAnimator translationAnimator = ObjectAnimator.ofFloat(shapeView, "translationY", 0, mTranslationDistance);
        // 配合中间阴影缩小
        ObjectAnimator scaleAnimator = ObjectAnimator.ofFloat(mShadowView, "scaleX", 1f, 0.3f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(ANIMATOR_DURATION);
        // 下落的速度应该是越来越快
        animatorSet.setInterpolator(new AccelerateInterpolator());
        animatorSet.playTogether(translationAnimator, scaleAnimator);
        animatorSet.start();
        // 下落完之后就上抛了，监听动画执行完毕
        // 是一种思想，在 Adapter 中的 BannerView 写过
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                // 改变形状
                shapeView.exchangeShape();
                // 下落完之后就上抛了
                startUpAnimator();
                // 开始旋转
            }
        });
    }

    /**
     * 开始执行上抛动画
     */
    private void startUpAnimator() {
        if (mIsStopAnimator) {
            return;
        }
        //Log.e("TAG", "startUpAnimator" + this);
        // 动画作用在谁的身上
        // 下落位移动画
        ObjectAnimator translationAnimator = ObjectAnimator.ofFloat(shapeView, "translationY", mTranslationDistance, 0);
        // 配合中间阴影缩小
        ObjectAnimator scaleAnimator = ObjectAnimator.ofFloat(mShadowView, "scaleX", 0.3f, 1f);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(ANIMATOR_DURATION);
        animatorSet.setInterpolator(new DecelerateInterpolator());
        animatorSet.playTogether(translationAnimator, scaleAnimator);
        // 先执行 translationAnimator 接着执行 scaleAnimator
        // 上抛完之后就下落了，监听动画执行完毕
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                // 上抛完之后就下落了
                startFallAnimator();
            }

            @Override
            public void onAnimationStart(Animator animation) {
                // 开始旋转
                startRotationAnimator();
            }
        });
        // 执行 -> 监听的 onAnimationStart 方法
        animatorSet.start();
    }
    /**
     * 上抛的时候需要旋转
     */
    private void startRotationAnimator() {
        ObjectAnimator rotationAnimator = null;
        switch (shapeView.getCurrentShape()) {
            case Circle:
            case Square:
                // 180
                rotationAnimator = ObjectAnimator.ofFloat(shapeView, "rotation", 0, 180);
                break;
            case Triangle:
                // 120
                rotationAnimator = ObjectAnimator.ofFloat(shapeView, "rotation", 0, -120);
                break;
        }
        rotationAnimator.setDuration(ANIMATOR_DURATION);
        rotationAnimator.setInterpolator(new DecelerateInterpolator());
        rotationAnimator.start();
    }
    @Override
    public void setVisibility(int visibility) {
        // 不要再去排放和计算，少走一些系统的源码
        super.setVisibility(visibility);
        if(visibility == GONE){
            // 清理动画
            shapeView.clearAnimation();
            mShadowView.clearAnimation();
            // 把LoadingView从父布局移除
            /*ViewGroup parent = (ViewGroup) getParent();
            if (parent != null) {
                parent.removeView(this);// 从父布局移除
                removeAllViews();// 移除自己所有的View
            }*/
            mIsStopAnimator = true;
        }else if(visibility == VISIBLE){
            mIsStopAnimator = false;
        }
    }

    private int dip2px(int dip) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, getResources().getDisplayMetrics());
    }




}
