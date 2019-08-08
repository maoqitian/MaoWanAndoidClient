package mao.com.mao_wanandroid_client.utils.webview;

import android.content.Context;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/**
 * @author maoqitian
 * @Description: 视频全屏 holder
 * @date 2019/8/8 0008 11:12
 */
public class FullscreenHolder extends FrameLayout {

    public FullscreenHolder(Context ctx) {
        super(ctx);
        setBackgroundColor(ctx.getResources().getColor(android.R.color.black));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }
}
