package com.arcsoft.testpath.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.Random;

/**
 * Created by lbb9756 on 2017/5/11.
 */

public class MyView extends View {


    private Paint mPaint;
    private Path mPath;

    private float mWidth;
    private float mHeight;
    private float mCenterX;
    private float mCenterY;

    private float mWaveX;
    private float mWaveY;
    private float mCtx;
    private float mCty;

    private boolean mProcessWave;

    private float mSign;

    private Random mRandom;

    private boolean mBeyondV;

    private boolean mIsEven;

    public MyView(Context context) {
        this(context, null);

    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }


    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPath = new Path();

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.parseColor("#AEEEEE"));

        mProcessWave = true;
        mCtx = mCty = 0f;
        mWaveX = mWaveY = 0;
        mSign = 1.f;

        mRandom = new Random();
        mBeyondV = false;
        mIsEven = true;


    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        mWaveY = mCenterY = 1f/8f * getHeight();
        mCty = -1f/18f* getHeight();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();

        mCenterX = mWidth / 2;
        mCenterY = mHeight / 2;
    }


    @Override
    protected void onDraw(Canvas canvas) {


        mPath.moveTo(-1 / 4F * mWidth, mWaveY);
        mPath.quadTo(mCtx, mCty, mWidth + 4f / mWidth, mWaveY);
        mPath.lineTo(mWidth + 4f / mWidth, mHeight);
        mPath.lineTo(-1 / 4f * mWidth, mHeight);
        mPath.close();

        canvas.drawPath(mPath, mPaint);

        if (mCtx > mWidth + 1 / 3f * mWidth) {
            mProcessWave = false;
        } else if (mCtx < -1 / 3 * mWidth) {
            mProcessWave = true;
        }

        mCtx = mProcessWave ? mCtx + 20 : mCtx - 20;

        if (mWaveY > mHeight) mIsEven = false;
        if (mWaveY < 0) mIsEven = true;

        if (mCtx < mHeight) {
            mCty += 4;
            mWaveY += 4;
        }


        mPath.reset();
        postInvalidate();
//       postInvalidateDelayed(300);

//        mPath.moveTo(300,300);
//        mPath.lineTo(100,200);
//        mPath.lineTo(100,300);
//        mPath.lineTo(200,300);
//        mPath.close();

        //mPath.moveTo(10,20);

        //RectF rectF = new RectF(300,200,500,400);
//        mPath.addRect(rectF, Path.Direction.CW);
//        mPath.moveTo(0,0);
        // mPath.arcTo(rectF,0,90,true);
//        mPath.addArc(rectF,0,90);
//        mPath.moveTo(10,160);
//        mPath.lineTo(410,160);

//        mPath.quadTo(100,300,300,500);
//        canvas.drawPath(mPath,mPaint);

//        mPath.addPath(mPath,200,400);


        // mPath.addCircle(mCenterX,mCenterY,200, Path.Direction.CCW);

        // canvas.drawPath(mPath,mPaint);
        // mPaint.setTextSize(70);
        // canvas.drawTextOnPath("1234567890",mPath,10,0,mPaint);

//        canvas.drawCircle(mCenterX,mCenterY,100,mPaint);
    }
}
