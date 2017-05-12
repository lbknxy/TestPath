package com.arcsoft.testpath.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by lbb9756 on 2017/5/12.
 */

public class MyView4 extends View {


    private Canvas mCanvas1;
    private Canvas mCanvas2;

    private Paint mPaint;
    private Bitmap mSource;
    private int mWidth;
    private int mHeight;

    private PorterDuffXfermode mXfermode;

    public MyView4(Context context) {
        this(context,null);
    }

    public MyView4(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyView4(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context);
    }


    private void init(Context context){
//        mSource = BitmapFactory.decodeResource(context.getResources(),R.id.dr)

        int width = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,160,
                context.getResources().getDisplayMetrics());
        int height = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,160,
                context.getResources().getDisplayMetrics());

        mSource = Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
        mCanvas1 = new Canvas(mSource);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);


        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
    }


    @Override
    protected void onDraw(Canvas canvas) {

        drawBitmap();
        canvas.drawBitmap(mSource,0,0,null);

    }


    private void drawBitmap(){

        mCanvas1.drawColor(Color.parseColor("#559A9E9F"));
        mPaint.setColor(Color.GREEN);
        mCanvas1.drawCircle(mWidth/2,mHeight/2,160,mPaint);
        mPaint.setXfermode(mXfermode);
        mPaint.setColor(Color.RED);
        mCanvas1.drawCircle(mWidth/2-90,mHeight/2-90,120,mPaint);
        mPaint.setXfermode(null);
    }



    private void init2(){

    }



}
