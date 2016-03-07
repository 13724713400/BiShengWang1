package com.zhushan.bishengwang.Itools;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * 图片裁剪
 * Created by Administrator on 2015/12/9.
 */
public class ClipImageBorderView extends View {

    /**
     * 水平方向与View的边距
     */
    private int mHorizontalPadding = 20;

    private int mVerticalPadding;

    private int mBorderWith = 1;

    private int mWith;
    private Paint paint;

    private int mBorderColor = Color.parseColor("#ffffff");

    public ClipImageBorderView(Context context) {

        this(context,null);
    }



    public ClipImageBorderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        /**
         * 转换为dp
         */
        mHorizontalPadding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,mHorizontalPadding,getResources().getDisplayMetrics());

        mBorderWith = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,mBorderWith,getResources().getDisplayMetrics());

        paint = new Paint();
        paint.setAntiAlias(true);


    }

    public ClipImageBorderView(Context context, AttributeSet attrs) {
       this(context,attrs,0);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mWith = getWidth()-2*mHorizontalPadding;
        mVerticalPadding = (getHeight()-mWith)/2;

        paint.setColor(Color.parseColor("#aa000000"));
        paint.setStyle(Paint.Style.FILL);

        /**
         * 画左边的矩形
         */
        canvas.drawRect(0,0,mHorizontalPadding,getHeight(),paint);

        /**
         *
         */
        canvas.drawRect(getWidth()-mHorizontalPadding,0,getWidth(),getHeight(),paint);

        canvas.drawRect(mHorizontalPadding,0,getWidth()-mHorizontalPadding,mVerticalPadding,paint);

        canvas.drawRect(mHorizontalPadding,getHeight()-mVerticalPadding,getWidth()-mHorizontalPadding,getHeight(),paint);

        paint.setColor(mBorderColor);
        paint.setStrokeWidth(mBorderWith);
        canvas.drawRect(mHorizontalPadding,mVerticalPadding,getWidth()-mHorizontalPadding,getHeight()-mVerticalPadding,paint);





    }
}
