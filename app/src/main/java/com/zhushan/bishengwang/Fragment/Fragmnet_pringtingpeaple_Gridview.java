package com.zhushan.bishengwang.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zhushan.bishengwang.Constance.SharePreferenceConstance;
import com.zhushan.bishengwang.Entry.PrintingPeapleData;
import com.zhushan.bishengwang.Entry.PrintingPeapleEntry;
import com.zhushan.bishengwang.Entry.ShuaiXuanEntry;
import com.zhushan.bishengwang.Iadapter.EmptyAdapter;
import com.zhushan.bishengwang.Iadapter.PringtingPeapleAdapter;
import com.zhushan.bishengwang.Iadapter.PringtingPeapleGridViewAdapter;
import com.zhushan.bishengwang.Ifactory.HttpFactory;
import com.zhushan.bishengwang.Itools.SharePreferenceUtils;
import com.zhushan.bishengwang.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2016/1/7.
 */
public class Fragmnet_pringtingpeaple_Gridview extends Fragment {

    private PullToRefreshGridView pringtingpeaple_pulltorefreshGridView;
    private PringtingPeapleGridViewAdapter pringtingPeapleGridViewAdapter;
    private  List<PrintingPeapleData> printingPeapleData2 = new ArrayList<PrintingPeapleData>();
    private HashMap<String,String> map;
    private ShuaiXuanEntry shuaiXuanEntry;
    private int index;
    private boolean isFirst;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View view =  inflater.inflate(R.layout.printingpeaple_gridview,container,false);
        initView(view);
        initData();
        return view;
    }

    private void initData() {

        shuaiXuanEntry = (ShuaiXuanEntry) getArguments().getSerializable("shuaiXuanEntry");
        map = new HashMap<String, String>();
        String token =  SharePreferenceUtils.getInstance(getActivity(), SharePreferenceConstance.USERNAME).getSharePreferenceString(SharePreferenceConstance.USERTOKEN, null);
        if (token!=null)
        {
            map.put("token", SharePreferenceUtils.getInstance(getActivity(), SharePreferenceConstance.USERNAME).getSharePreferenceString(SharePreferenceConstance.USERTOKEN, null));
        }

        map.put("page", String.valueOf(0));
        map.put("sex", shuaiXuanEntry.getSex());
        map.put("label", shuaiXuanEntry.getLabel());
        map.put("filter1", shuaiXuanEntry.getOrderField());
        map.put("lat",shuaiXuanEntry.getLat());
        map.put("lng",shuaiXuanEntry.getLng());
        map.put("city",shuaiXuanEntry.getCity());
        HttpFactory.getInstance().PrintingPeaple(getActivity(), map);
        pringtingpeaple_pulltorefreshGridView.setMode(PullToRefreshBase.Mode.PULL_UP_TO_REFRESH);
        pringtingpeaple_pulltorefreshGridView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<GridView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {


            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {
                index++;
                shuaiXuanEntry.setIsPress(false);
                map.put("page", String.valueOf(index));
                map.put("sex", shuaiXuanEntry.getSex());
                map.put("label", shuaiXuanEntry.getLabel());
                map.put("filter1", shuaiXuanEntry.getOrderField());
                map.put("lat",shuaiXuanEntry.getLat());
                map.put("lng",shuaiXuanEntry.getLng());
                map.put("city",shuaiXuanEntry.getCity());
                HttpFactory.getInstance().PrintingPeaple(getActivity(), map);


            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void initView( View view) {
        EventBus.getDefault().register(this);
        pringtingpeaple_pulltorefreshGridView = (PullToRefreshGridView) view.findViewById(R.id.pringtingpeaple_pulltorefreshGridView);
    }
    public void onEventMainThread(ShuaiXuanEntry s)
    {
       if (s.isPress())
       {
           Log.i("www","清理数据");
           index=-1;
           printingPeapleData2.clear();
           shuaiXuanEntry =s;
       }

    }

  public void onEventMainThread(PrintingPeapleEntry printingPeapleEntry) {
        List<PrintingPeapleData> printingPeapleData =  printingPeapleEntry.getData();

        if (printingPeapleData.size()==0)
        {
            Log.i("www", "数据加载完");
            Toast.makeText(getActivity(), "亲！没有数据哦", Toast.LENGTH_SHORT).show();
        }
        printingPeapleData2.addAll(printingPeapleData);
        Log.i("www", "删除" + printingPeapleData2.size());
      Log.i("www","选中"+shuaiXuanEntry.isPress());

        if (printingPeapleData2.size()==0)
        {
            pringtingpeaple_pulltorefreshGridView.setAdapter(EmptyAdapter.getInstanceAdapter(getActivity()));

        }else{

            if (!isFirst) {
                isFirst = !isFirst;
                Log.i("www","加载数据");
                pringtingPeapleGridViewAdapter = new PringtingPeapleGridViewAdapter(getActivity(), printingPeapleData2, R.layout.printingpeaple_gridview_item);
                pringtingpeaple_pulltorefreshGridView.setAdapter(pringtingPeapleGridViewAdapter);
            }else{
                if (shuaiXuanEntry.isPress())
                {
                    pringtingPeapleGridViewAdapter = new PringtingPeapleGridViewAdapter(getActivity(), printingPeapleData2, R.layout.printingpeaple_gridview_item);
                    pringtingpeaple_pulltorefreshGridView.setAdapter(pringtingPeapleGridViewAdapter);
                }else {
                    pringtingPeapleGridViewAdapter.notifyDataSetChanged();
                    pringtingpeaple_pulltorefreshGridView.onRefreshComplete();
                }
            }

        }


    }

}
