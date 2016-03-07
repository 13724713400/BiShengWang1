package com.zhushan.bishengwang.Fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zhushan.bishengwang.Constance.SharePreferenceConstance;
import com.zhushan.bishengwang.Entry.PrintingPeapleData;
import com.zhushan.bishengwang.Entry.PrintingPeapleEntry;
import com.zhushan.bishengwang.Entry.ShuaiXuanEntry;
import com.zhushan.bishengwang.Iadapter.EmptyAdapter;
import com.zhushan.bishengwang.Iadapter.PringtingPeapleAdapter;
import com.zhushan.bishengwang.Ifactory.HttpFactory;
import com.zhushan.bishengwang.Itools.PopuwindowUtils;
import com.zhushan.bishengwang.Itools.SharePreferenceUtils;
import com.zhushan.bishengwang.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2016/1/7.
 */
public class Fragmnet_pringtingpeaple_list extends Fragment {

    private PullToRefreshListView pringtingpeaple_pulltorefreshlistview;
    private PringtingPeapleAdapter pringtingPeapleAdapter;
    private  List<PrintingPeapleData> printingPeapleData2 = new ArrayList<PrintingPeapleData>();
    private HashMap<String,String> map;
    private ShuaiXuanEntry shuaiXuanEntry;
    private int index;
    private boolean isFirst;
    private boolean isCheck;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View view =  inflater.inflate(R.layout.printingpeaple_listview,container,false);
        initView(view);
        initData();

        return view;
    }

    public void onEventMainThread(ShuaiXuanEntry s)
    {
        if (s.isPress())
        {
            Log.i("www","清理数据");
            index=-1;
            printingPeapleData2.clear();
            shuaiXuanEntry = s;
        }
    }
    private void initData() {
        isCheck = true;
        shuaiXuanEntry = (ShuaiXuanEntry) getArguments().getSerializable("shuaiXuanEntry");
        map = new HashMap<String, String>();
        String token = SharePreferenceUtils.getInstance(getActivity(), SharePreferenceConstance.USERNAME).getSharePreferenceString(SharePreferenceConstance.USERTOKEN, null);
        if (token!=null)
        {
            map.put("token", SharePreferenceUtils.getInstance(getActivity(), SharePreferenceConstance.USERNAME).getSharePreferenceString(SharePreferenceConstance.USERTOKEN, null));
        }
        map.put("page",
                String.valueOf(0));
        map.put("sex", shuaiXuanEntry.getSex());
        map.put("label", shuaiXuanEntry.getLabel());
        map.put("filter1", shuaiXuanEntry.getOrderField());
        map.put("lat",shuaiXuanEntry.getLat());
        map.put("lng",shuaiXuanEntry.getLng());

            map.put("city", shuaiXuanEntry.getCity());

        HttpFactory.getInstance().PrintingPeaple(getActivity(), map);
        pringtingpeaple_pulltorefreshlistview.setMode(PullToRefreshBase.Mode.PULL_UP_TO_REFRESH);
        pringtingpeaple_pulltorefreshlistview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                shuaiXuanEntry.setIsPress(false);
                ++index;
                Log.i("www","index"+index);
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



    public void onEventMainThread(PrintingPeapleEntry printingPeapleEntry) {
        List<PrintingPeapleData> printingPeapleData =  printingPeapleEntry.getData();
        Log.i("www", "印刷人列表");
        if (printingPeapleData.size()==0)
        {
            Log.i("www", "数据加载完");
            Toast.makeText(getActivity(), "亲！没有数据哦", Toast.LENGTH_SHORT).show();
        }
        printingPeapleData2.addAll(printingPeapleData);

        if (printingPeapleData2.size()==0)
        {
            pringtingpeaple_pulltorefreshlistview.setAdapter(EmptyAdapter.getInstanceAdapter(getActivity()));

        }else{


            if (!isFirst) {
                isFirst = !isFirst;
                pringtingPeapleAdapter = new PringtingPeapleAdapter(getActivity(), printingPeapleData2, R.layout.printingpeple_listview_item);
                pringtingpeaple_pulltorefreshlistview.setAdapter(pringtingPeapleAdapter);
            }else{
                if (shuaiXuanEntry.isPress()) {

                    pringtingPeapleAdapter = new PringtingPeapleAdapter(getActivity(), printingPeapleData2, R.layout.printingpeple_listview_item );
                    pringtingpeaple_pulltorefreshlistview.setAdapter(pringtingPeapleAdapter);
                }else {
                    pringtingPeapleAdapter.notifyDataSetChanged();
                    pringtingpeaple_pulltorefreshlistview.onRefreshComplete();
                }
            }

        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        isCheck = false;
    }

    private void initView( View view) {

        EventBus.getDefault().register(this);
        pringtingpeaple_pulltorefreshlistview = (PullToRefreshListView) view.findViewById(R.id.pringtingpeaple_pulltorefreshlistview);
    }



}
