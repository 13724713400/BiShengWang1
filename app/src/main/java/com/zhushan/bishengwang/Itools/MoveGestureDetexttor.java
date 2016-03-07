package com.zhushan.bishengwang.Itools;

import android.content.Context;
import android.graphics.PointF;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2015/12/8.
 */
public class MoveGestureDetexttor extends BaseGestureDetector {

    private PointF mCurrentPointer;
    private PointF mPrePointer;
    private PointF mDeltaPointer  = new PointF();
    private PointF mExtenalPointer = new PointF();

    private OnMoveGestureListener mListener;

    public MoveGestureDetexttor(Context context,OnMoveGestureListener mListener)
    {
        super(context);
        mListener = mListener;

    }
    @Override
    protected void handleInProgressEvent(MotionEvent event) {

       int actionCode =  event.getAction()&MotionEvent.ACTION_MASK;

        switch (actionCode)
        {
            case MotionEvent.ACTION_CANCEL:

                case MotionEvent.ACTION_UP:
                    mListener.onMoveend(this);

                    resetState();
                    break;
            case MotionEvent.ACTION_MOVE:

                updateStateByEvent(event);
                boolean update = mListener.onMove(this);
                if (update)
                {
                    mPreMotionEvent.recycle();
                    mPreMotionEvent = MotionEvent.obtain(event);

                }
                break;
        }

    }

    @Override
    protected void handleStartProgreeEvent(MotionEvent event) {

        int actionCode = event.getAction()&MotionEvent.ACTION_MASK;
        switch (actionCode)
        {
            case MotionEvent.ACTION_DOWN:
                resetState();
                mPreMotionEvent = MotionEvent.obtain(event);
                updateStateByEvent(event);
                break;
            case MotionEvent.ACTION_MOVE:
                mGesttureInProgress = mListener.onMoveBegin(this);
                break;

        }

    }

    @Override
    protected void updateStateByEvent(MotionEvent event) {

        final MotionEvent prev = mPreMotionEvent;

        mPrePointer = caculateFocalPointer(prev);
        mCurrentPointer = caculateFocalPointer(event);

        boolean mSkipThisMovent = prev.getPointerCount()!=event.getPointerCount();

        mExtenalPointer.x = mSkipThisMovent?0:mCurrentPointer.x-mPrePointer.x;
        mExtenalPointer.y = mSkipThisMovent?0:mCurrentPointer.y-mPrePointer.y;


    }

    /**
     * 多指触控得到中心点
     * @param event
     * @return
     */
    private PointF caculateFocalPointer(MotionEvent event) {

        final int count = event.getPointerCount();
        float x = 0,y=0;

        for (int  i =0;i<count;i++)
        {
            x+=event.getX(i);
            y+=event.getY(i);

        }

        x/=count;
        y/=count;

        return new PointF(x,y);
    }

    public float getMoveX()
    {
        return  mExtenalPointer.x;
    }

    public float getMoveY()
    {
        return  mExtenalPointer.y;
    }


    public interface  OnMoveGestureListener
    {
        public  boolean onMoveBegin(MoveGestureDetexttor detexttor);
        public boolean onMove(MoveGestureDetexttor detexttor);
        public void onMoveend(MoveGestureDetexttor detexttor);
    }

    public static class SimpleMoveGestureDetector implements OnMoveGestureListener
    {

        @Override
        public boolean onMoveBegin(MoveGestureDetexttor detexttor) {
            return true;
        }

        @Override
        public boolean onMove(MoveGestureDetexttor detexttor) {
            return false;
        }

        @Override
        public void onMoveend(MoveGestureDetexttor detexttor) {

        }
    }

}
