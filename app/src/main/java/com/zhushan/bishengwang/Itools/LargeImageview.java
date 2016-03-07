package com.zhushan.bishengwang.Itools;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.io.IOException;
import java.io.InputStream;


/**
 * Created by Administrator on 2015/12/7.
 */
public class LargeImageview extends View {
    private BitmapRegionDecoder decoder;
    private int mImageWith,mImageHeight;
    private volatile Rect mrecct = new Rect();
    private MoveGestureDetexttor detexttor;
    private static final BitmapFactory.Options options = new BitmapFactory.Options();
    static {
        options.inPreferredConfig = Bitmap.Config.RGB_565;
    }
    public LargeImageview(Context context) {
        super(context);
    }

    public LargeImageview(Context context, AttributeSet attrs) {
        super(context, attrs);
      //  init();
    }

   /* private void init() {
        detexttor = new MoveGestureDetexttor(getContext(),new MoveGestureDetexttor.SimpleMoveGestureDetector() {

           *//* @Override
            public boolean onMove(MoveGestureDetexttor detexttor) {

                int moveX = (int) detexttor.getMoveX();
                int moveY = (int) detexttor.getMoveY();

                if (mImageWith > getWidth()) {
                    mrecct.offset(-moveX, 0);
                    checkWodth();
                    invalidate();
                }

                if (mImageHeight>getHeight())
                {
                    mrecct.offset(0, -moveY);
                    checkHeight();
                    invalidate();

                }

                return true;

            }
        });


    }



    public LargeImageview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int with = getMeasuredWidth();
        int height = getMeasuredHeight();

        int imageWith = mImageWith;
        int imageHeight = mImageHeight;

        *//**//**
             * 默认直接显示图片的中心区域，
             * 是以左上角坐标为原点
             *//**//*
        mrecct.left = imageWith/2-with/2;
        mrecct.top = imageHeight/2-height/2;
        mrecct.right = mrecct.left+with;
        mrecct.bottom = mrecct.top+height;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        Bitmap bm = decoder.decodeRegion(mrecct, options);
        canvas.drawBitmap(bm,0,0,null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        detexttor.onToucEvent(event);
        return true;

    }

    private void checkHeight()
    {
        Rect rect = mrecct;
        int imageWith = mImageWith;
        int imageHeight = mImageHeight;

        if (rect.bottom>imageHeight)
        {
            rect.bottom = imageHeight;
            rect.top = imageHeight-getHeight();

        }

        if (rect.top<0)
        {
            rect.top = 0;
            rect.bottom = getHeight();

        }
    }

    private void checkWodth() {

        Rect rect = mrecct;

        int imageWith = mImageWith;
        int imageHeight = mImageHeight;

        if (rect.right>imageWith)
        {
            rect.right = imageWith;
            rect.left = imageWith-getWidth();

        }

        if (rect.left<0)
        {
            rect.left = 0;
            rect.right = getWidth();

        }

    }

    public void setInputStream(InputStream is)
    {
        try {
            decoder  = BitmapRegionDecoder.newInstance(is,false);
            BitmapFactory.Options options = new BitmapFactory.Options();
            *//**//**
             * inJustDecodeBounds 只得到图片的边框
             *
             * 然后得到图片长宽
             *//**//*
            options.inJustDecodeBounds  = true;
            BitmapFactory.decodeStream(is,null,options);
            mImageWith = options.outWidth;
            mImageHeight = options.outHeight;
            requestLayout();
            invalidate();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(is!=null)
            {

                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }*//*

        }
    }
*/
    }


