package com.zhushan.bishengwang.Iselfview;

import android.widget.GridView;

import com.handmark.pulltorefresh.library.PullToRefreshListView;

public  class PulltoListview extends PullToRefreshListView
    {
        public PulltoListview(android.content.Context context,
                              android.util.AttributeSet attrs)
        {
            super(context, attrs);
        }

        /**
         * 设置不滚动
         */
        public void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
        {
            int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                    MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, expandSpec);  
        }  
    }  