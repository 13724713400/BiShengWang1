package com.zhushan.bishengwang.Itools;

import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.Adapter;

/**
 * Created by Administrator on 2015/12/12.
 */
public class SwipeLayoutUtils {

    private static SwipeLayoutUtils swipeLayoutUtils;

    private SwipeLayoutUtils(){}

    public static SwipeLayoutUtils getInstance()
    {
        if (swipeLayoutUtils==null)
        {
            swipeLayoutUtils = new SwipeLayoutUtils();

        }

        return swipeLayoutUtils;

    }

    public void showSwipeLayout(SwipeRefreshLayout swipeRefreshLayout)
    {
        if (swipeRefreshLayout==null)
        {
            return;
        }
        swipeRefreshLayout.setColorSchemeColors(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {


            }
        });


    }


}
