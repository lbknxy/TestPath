package com.arcsoft.testpath.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by lbb9756 on 2017/5/11.
 */

public class MyView2 extends android.support.v7.widget.AppCompatImageView {


    private Paint mPaint;
    private Path mPP;
    private PointF mLast;
    private float DEFAULT_Y;
    private float newY;

    public MyView2(Context context) {
        super(context);
        init();
    }

    public MyView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL);
        mPP = new Path();
        mLast = new PointF(0, 0);

//        setOnTouchListener(new OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                return onTouchEvent(event);
//            }
//        });
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {


        Log.e("AAAA","YES");

        int action = event.getAction();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mLast.set(event.getRawX(), event.getRawY());
                break;
            case MotionEvent.ACTION_MOVE:
            {
                PointF cur = new PointF(event.getRawX(), event.getRawY());
                newY = cur.y - mLast.y;
                Log.e("AAAAA","y="+newY);
                invalidate();
            }

                break;
            case MotionEvent.ACTION_UP:
            {
                newY = DEFAULT_Y;
                invalidate();
            }
                break;


        }


        return true;
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        DEFAULT_Y = getHeight();
        newY = DEFAULT_Y;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPP.reset();
        mPP.moveTo(0, DEFAULT_Y);
        mPP.quadTo(getWidth() / 2, newY+DEFAULT_Y, getWidth(), DEFAULT_Y);
        mPP.close();

        canvas.drawPath(mPP, mPaint);

        Log.e("DDDDD","dy="+DEFAULT_Y+" ,new="+DEFAULT_Y+newY);
    }
}
