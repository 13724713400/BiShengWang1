package com.zhushan.bishengwang.Itools;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.Adapter;

/**
 * Created by Administrator on 2015/12/12.
 */
public class RecyclerViewUtils {
    public static void  showRecycleView(RecyclerView recyclerView,RecyclerView.Adapter adapter)
    {
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        /**
         * 分割线
         */
        SpacesItemDecoration decoration=new SpacesItemDecoration(16);
        recyclerView.addItemDecoration(decoration);
    }
}
