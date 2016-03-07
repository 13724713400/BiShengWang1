package com.zhushan.bishengwang.Fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zhushan.bishengwang.Constance.SharePreferenceConstance;
import com.zhushan.bishengwang.Entry.DataList;
import com.zhushan.bishengwang.Entry.PrintingPeapleData;

import com.zhushan.bishengwang.Entry.TypographerData;
import com.zhushan.bishengwang.Entry.TypographerEntry;
import com.zhushan.bishengwang.Entry.TypograpterEntry;
import com.zhushan.bishengwang.Iadapter.EmptyAdapter;


import com.zhushan.bishengwang.Iadapter.RunRankAdapter;
import com.zhushan.bishengwang.Iadapter.TypegrapterAdapterTwo;
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
public class Fragmnet_typographer_listview extends Fragment {


    private  List<PrintingPeapleData> printingPeapleData2 = new ArrayList<PrintingPeapleData>();
    private List<DataList> dataList2 = new ArrayList<DataList>();
    private PullToRefreshListView typographer_pulltorefreshlistview;
   // private TypegrapterAdapterTwo runRankAdapter;
    private RunRankAdapter runRankAdapter2;
    private TypographerData typographerData1;
    private boolean isFirst,isTwoFist;
    private  HashMap<String,String> map;
    private TypographerEntry typographerEntry=null;
    private int index;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View view =  inflater.inflate(R.layout.typograpther_listview,container,false);
        initView(view);
        initData();
        return view;
    }

  /*  public void onEventMainThread(ShuaiXuanEntry s)
    {
        if (s.isPress())
        {
            Log.i("www","清理数据");
            index=-1;
            printingPeapleData2.clear();
           // shuaiXuanEntry = s;
        }
    }*/
    private void initData() {


        typographerEntry = (TypographerEntry) getArguments().getSerializable("typographerEntry");

        typographerEntry.setFilter1("distance");
        typographerEntry.setLat(SharePreferenceUtils.getInstance(getActivity(), SharePreferenceConstance.POSITIONNAME).getSharePreferenceString(SharePreferenceConstance.POSITIONNALAT, null));
        typographerEntry.setLng(SharePreferenceUtils.getInstance(getActivity(), SharePreferenceConstance.POSITIONNAME).getSharePreferenceString(SharePreferenceConstance.POSITIONNALNG, null));
        typographerEntry.setFilter3("");
        map = new HashMap<String,String>();
        map.put("page",String.valueOf(0));
        map.put("filter1",typographerEntry.getFilter1());
        map.put("lat",typographerEntry.getLat());
        map.put("lng",typographerEntry.getLng());
        map.put("filter3",typographerEntry.getFilter3());
        if (typographerEntry.getCity()!=null) {
            map.put("city", typographerEntry.getCity());
        }
        HttpFactory.getInstance().TypographerFragment(getActivity(), map);
        typographer_pulltorefreshlistview.setMode(PullToRefreshBase.Mode.PULL_UP_TO_REFRESH);
        typographer_pulltorefreshlistview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
              //  shuaiXuanEntry.setIsPress(false);
                typographerEntry.setIsPress(false);
                typographerEntry.setIsCheck(false);
                index++;
                map.put("page", String.valueOf(index));
                map.put("filter1",typographerEntry.getFilter1());
                map.put("lat",typographerEntry.getLat());
                map.put("lng",typographerEntry.getLng());
                map.put("filter3", typographerEntry.getFilter3());
                map.put("city",typographerEntry.getCity());
                HttpFactory.getInstance().TypographerFragment(getActivity(), map);
            }
        });

    }

    public void onEventMainThread(TypographerEntry typographerEntry)
    {
        Log.i("www","qingli");
        if (typographerEntry.isCheck())
        {
            index=0;
            dataList2.clear();
            Log.i("www","qingli");
        }

    }
   public void onEventMainThread(TypograpterEntry response) {

       Log.i("www","response"+response);
       Log.i("www","因熟人列表");
       if (typographerEntry.isCheck())
       {
           index=0;
           dataList2.clear();
       }
        typographerData1 =  response.getData();
        Log.i("www","yinshuanshang"+response);
        if (typographerData1.getDataList().size()==0)
        {
            Log.i("www", "数据加载完");
            Toast.makeText(getActivity(), "亲！没有数据哦", Toast.LENGTH_SHORT).show();
            typographer_pulltorefreshlistview.onRefreshComplete();
        }
       dataList2.addAll(typographerData1.getDataList());

        if (dataList2.size()==0)
        {
            typographer_pulltorefreshlistview.setAdapter(EmptyAdapter.getInstanceAdapter(getActivity()));

        }else{
                if (!isFirst)
                {
                  isFirst =!isFirst;
                    if (dataList2.size()>3) {
                        dataList2.add(2, new DataList());
                        dataList2.get(2).setIsCheck(true);
                    }

                    runRankAdapter2 = new RunRankAdapter(getActivity(),response.getData().getBanner(),dataList2);
                    typographer_pulltorefreshlistview.setAdapter(runRankAdapter2);
                }else {

                    if (typographerEntry.isPress())
                    {

                      if (dataList2.size()>3) {
                            dataList2.add(2, new DataList());
                            dataList2.get(2).setIsCheck(true);
                        }
                        runRankAdapter2 = new RunRankAdapter(getActivity(),response.getData().getBanner(),dataList2);
                        typographer_pulltorefreshlistview.setAdapter(runRankAdapter2);
                    }else {
                        typographer_pulltorefreshlistview.onRefreshComplete();
                        runRankAdapter2.notifyDataSetChanged();
                    }


                }
            }

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
   EventBus.getDefault().unregister(this);

    }

    private void initView( View view) {

     EventBus.getDefault().register(this);
        typographer_pulltorefreshlistview = (PullToRefreshListView) view.findViewById(R.id.typographer_pulltorefreshlistview);
    }



}
