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
    private int mPanelWidth;
    private int mPanelHeight;
    private int thumbWidth;
    private int thumbHeight;

    private Rect mRectF;
    private Paint mBitmapPaint;

    private float startX;
    private float startY;
    private int margin;
    private int mSaturation = 50;
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
        mPanelWidth = mBitmapBg.getWidth();
        mPanelHeight = mBitmapBg.getHeight();

        thumbWidth = mBitmapThumb.getWidth();
        thumbHeight = mBitmapThumb.getHeight();

        mBitmapPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBitmapPaint.setStrokeCap(Paint.Cap.ROUND);
        margin = thumbWidth / 2;
        mRectF = new Rect(margin, margin, mPanelWidth + margin, mPanelHeight + margin);

        startX = (mPanelWidth + margin * 2) / 2;
        startY = (mPanelHeight + margin * 2) / 2;


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(mPanelWidth + margin * 2, mPanelHeight + margin * 2);
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
                if (startX < margin) {
                    startX = margin;
                }

                if (startY < margin) {
                    startY = margin;
                }

                if (startX > mPanelWidth +margin) {
                    startX = mPanelWidth +margin;
                }

                if (startY > mPanelHeight +margin) {
                    startY = mPanelHeight +margin;
                }
                invalidate();
                mSaturation = (int) ((startX-margin) * 100f / mPanelWidth);
                if (mSaturationListener != null) {
                    mSaturationListener.onSaturationChanged(mSaturation);
                }
                break;
        }

        return true;
    }

    public void setOnSaturationChangeListener(OnSaturationListener onSaturationListener) {
        this.mSaturationListener = onSaturationListener;
    }
}
