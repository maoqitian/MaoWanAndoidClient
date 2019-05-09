package mao.com.mao_wanandroid_client.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import mao.com.mao_wanandroid_client.R;

/**
 * @author maoqitian
 * @Description 自定义加载动画中的 图形(圆形 正方形 三角形)
 * @Time 2018/12/25 0025 20:13
 */
public class ShapeView extends View {

    private Shape mCurrentShape = Shape.Circle;
    Paint mPaint;
    private Path mPath;

    public ShapeView(Context context) {
        super(context);
        init();
    }

    public ShapeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ShapeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init(){
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 只保证是正方形
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(Math.min(width, height), Math.min(width, height));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        switch (mCurrentShape){
            case Circle:
                 // 画圆形
                int center = getWidth() / 2;
                mPaint.setColor(ContextCompat.getColor(getContext(), R.color.circle));
                canvas.drawCircle(center, center, center, mPaint);
                break;
            case Square:
                // 画正方形
                mPaint.setColor(ContextCompat.getColor(getContext(), R.color.rect));
                canvas.drawRect(0, 0, getWidth(), getHeight(), mPaint);
                break;
            case Triangle:
                // 画三角  Path 画路线
                mPaint.setColor(ContextCompat.getColor(getContext(), R.color.triangle));
                if (mPath == null) {
                    // 画路径
                    mPath = new Path();
                    mPath.moveTo(getWidth() >> 1, 0);
                    mPath.lineTo(0, (float) ((getWidth() / 2) * Math.sqrt(3)));
                    mPath.lineTo(getWidth(), (float) ((getWidth() / 2) * Math.sqrt(3)));
                    // path.lineTo(getWidth()/2,0);
                    mPath.close();// 把路径闭合
                }
                canvas.drawPath(mPath, mPaint);
                break;
        }
    }


    public void exchangeShape(){
        switch (mCurrentShape){
            case Circle:
                mCurrentShape = Shape.Square;
                break;
            case Square:
                mCurrentShape = Shape.Triangle;
                break;
            case Triangle:
                mCurrentShape = Shape.Circle;
                break;
        }
        // 不断重新绘制形状
        invalidate();
    }

    public enum Shape {
        Circle, //圆形
        Square, //正方形
        Triangle //三角形
    }

    public Shape getCurrentShape() {
        return mCurrentShape;
    }
}
