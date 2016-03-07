package com.zhushan.bishengwang.Itools;

import android.content.Context;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2015/12/7.
 */
public abstract  class BaseGestureDetector {

    protected  boolean mGesttureInProgress;
    protected MotionEvent mCurrentMotionEvent;
    protected  MotionEvent mPreMotionEvent;
    protected Context context;

    public BaseGestureDetector(Context context)
    {
        context  = context;

    }

    public boolean onToucEvent(MotionEvent event)
    {
        if (!mGesttureInProgress)
        {
            handleStartProgreeEvent(event);
        }else{
            handleInProgressEvent(event);
        }
        return true;
    }

    protected abstract void handleInProgressEvent(MotionEvent event);

    protected abstract void handleStartProgreeEvent(MotionEvent event);

    protected  abstract  void updateStateByEvent(MotionEvent event);

    protected void resetState()
    {
        if (mPreMotionEvent!=null)
        {
            mPreMotionEvent.recycle();
            mPreMotionEvent = null;
        }

        if (mCurrentMotionEvent!=null)
        {
            mCurrentMotionEvent.recycle();
            mCurrentMotionEvent = null;
        }
        mGesttureInProgress = false;
    }


}
