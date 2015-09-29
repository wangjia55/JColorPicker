package com.larswerkman.holocolorpicker;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.jacob.color.picker.OnSaturationListener;
import com.jacob.color.picker.R;

/**
 * Package : com.larswerkman.holocolorpicker
 * Author : jacob
 * Date : 15-9-28
 * Description : 这个类是用来xxx
 */
public class ColorPanelView extends View {
    private Bitmap mBitmapBg;
    private Bitmap mBitmapThumb;
    private int width;
    private int height;
    private int thumbWidth;
    private int thumbHeight;

    private Rect mRectF;
    private Paint mBitmapPaint;

    private float startX;
    private float startY;
    private int saturation = 50;
    private OnSaturationListener mSaturationListener;

    public ColorPanelView(Context context) {
        this(context, null);
    }

    public ColorPanelView(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public ColorPanelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mBitmapBg = BitmapFactory.decodeResource(getResources(), R.mipmap.colortemp);
        mBitmapThumb = BitmapFactory.decodeResource(getResources(), R.mipmap.colorthumb);
        width = mBitmapBg.getWidth();
        height = mBitmapBg.getHeight();

        thumbWidth = mBitmapThumb.getWidth();
        thumbHeight = mBitmapThumb.getHeight();

        mBitmapPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBitmapPaint.setStrokeCap(Paint.Cap.ROUND);

        mRectF = new Rect(0, 0, width, height);

        startX = width / 2;
        startY = height/ 2;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(width, height);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mBitmapBg, null, mRectF, mBitmapPaint);
        canvas.drawBitmap(mBitmapThumb, startX - thumbWidth / 2, startY - thumbHeight / 2, mBitmapPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = x;
                startY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                startX = x;
                startY = y;
                if (startX < 0) {
                    startX = 0;
                }

                if (startY < 0) {
                    startY = 0;
                }

                if (startX > width) {
                    startX = width;
                }

                if (startY > height) {
                    startY = height;
                }
                invalidate();
                saturation = (int) ((startX) * 100f / width);
                if (mSaturationListener != null) {
                    mSaturationListener.onSaturationChanged(saturation);
                }
                break;
        }

        return true;
    }

    public void setOnSaturationChangeListener(OnSaturationListener onSaturationListener) {
        this.mSaturationListener = onSaturationListener;
    }
}
