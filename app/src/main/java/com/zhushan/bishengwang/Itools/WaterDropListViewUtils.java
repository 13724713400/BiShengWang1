package com.zhushan.bishengwang.Itools;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zhushan.bishengwang.Iselfview.WaterDropListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/12/18.
 */
public class WaterDropListViewUtils {


    private static WaterDropListViewUtils waterDropListViewUtils;
    private WaterDropListViewUtils(){}
    public static WaterDropListViewUtils getInstance()
    {
        if (waterDropListViewUtils==null)
        {
            synchronized (WaterDropListViewUtils.class)
            {
                if (waterDropListViewUtils==null)
                {

                    waterDropListViewUtils = new WaterDropListViewUtils();

                }

            }

        }
        return waterDropListViewUtils;
    }

    public void initWaterView(Context context,final PullToRefreshListView pullToRefreshListView,ListAdapter adapter) {
        final List<String> data = new ArrayList<String>();
        data.add("To see a world in a grain of sand,");
        data.add("And a heaven in a wild flower,");
        data.add("Hold infinity in the palm of your hand,");
        data.add("And eternity in an hour.");
        pullToRefreshListView.setMode(PullToRefreshBase.Mode.PULL_UP_TO_REFRESH);
      /*  ILoadingLayout startLabels = pullToRefreshListView
                .getLoadingLayoutProxy(true, false);
      *//* *//**//* startLabels.setPullLabel("下拉刷新...");// 刚下拉时，显示的提示
        startLabels.setRefreshingLabel("正在载入...");// 刷新时
        startLabels.setReleaseLabel("放开刷新...");// 下来达到一定距离时，显示的提示*/
        final Handler handler = new Handler()
        {
            @Override
            public void handleMessage(Message msg) {
               switch (msg.what)
               {
                   case 1:
                       pullToRefreshListView.onRefreshComplete();
                       break;
               }
            }
        };
        ILoadingLayout endLabels = pullToRefreshListView.getLoadingLayoutProxy(
                false, true);
        endLabels.setPullLabel("上拉刷新...");// 刚下拉时，显示的提示
        endLabels.setRefreshingLabel("正在载入...");// 刷新时
        endLabels.setReleaseLabel("放开刷新...");// 下来达到一定距离时，显示的提示
        final ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, data);
        pullToRefreshListView.setAdapter(adapter1);
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {

            int i = 0;
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                i++;
                data.add("上拉刷新"+i);
                adapter1.notifyDataSetChanged();
                handler.sendEmptyMessageDelayed(1,1000);
            }
        });
    }




}
