package mao.com.mao_wanandroid_client.widget;

import android.content.Context;
import android.util.AttributeSet;

import com.airbnb.lottie.LottieAnimationView;

/**
 * @author maoqitian
 * @Description: 收藏特性按钮  使用 Lottie 库
 * @date 2019/6/21 0021 17:22
 */
public class HeartView extends LottieAnimationView {
    public HeartView(Context context) {
        super(context);
    }

    public HeartView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HeartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public final void toggleWishlisted() {
        this.setActivated(!this.isActivated());
    }


    public void setActivated(boolean activated) {
        super.setActivated(activated);
        this.setSpeed(activated ? 1.0F : -2.0F);
        this.setProgress(0.0F);
        this.playAnimation();
    }
}
