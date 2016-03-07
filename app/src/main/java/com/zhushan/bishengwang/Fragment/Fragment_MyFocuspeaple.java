package com.zhushan.bishengwang.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zhushan.bishengwang.Constance.SharePreferenceConstance;
import com.zhushan.bishengwang.Entry.HomePageInfoData;
import com.zhushan.bishengwang.Entry.HomePageInfoEntry;
import com.zhushan.bishengwang.Entry.PrintingPeapleData;
import com.zhushan.bishengwang.Entry.PrintingPeapleEntry;
import com.zhushan.bishengwang.Entry.PrintingPeapleEntryTwo;
import com.zhushan.bishengwang.Iadapter.EmptyAdapter;
import com.zhushan.bishengwang.Iadapter.HompageItemAdapter;
import com.zhushan.bishengwang.Iadapter.MyfocusPringtingPeapleAdapter;
import com.zhushan.bishengwang.Iadapter.PringtingPeapleAdapter;
import com.zhushan.bishengwang.Ifactory.HttpFactory;
import com.zhushan.bishengwang.Itools.SharePreferenceUtils;
import com.zhushan.bishengwang.Iview.MainVu;
import com.zhushan.bishengwang.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2016/1/15.
 */
public class Fragment_MyFocuspeaple extends Fragment {

    private MainVu mainVu = new MainVu();
    private PullToRefreshListView myfocus_listview;
    private HompageItemAdapter hompageItemAdapter;
    private boolean isFirst;
    private int index;
    private MyfocusPringtingPeapleAdapter pringtingPeapleAdapter;
    private  List<PrintingPeapleData> printingPeapleData2 = new ArrayList<PrintingPeapleData>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = mainVu.initByLayout(getActivity(), R.layout.myfocusinfo);
        initView();
        initData();
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void initView() {
        EventBus.getDefault().register(this);
        myfocus_listview = mainVu.getItemView(R.id.myfocus_listview);
    }

    private void initData() {
        HashMap<String,String> map = new HashMap<String,String>();
        map.put("token", SharePreferenceUtils.getInstance(getActivity(),SharePreferenceConstance.USERNAME).getSharePreferenceString(SharePreferenceConstance.USERTOKEN,null));
        map.put("listType",String.valueOf(2));
        map.put("page",String.valueOf(0));
        HttpFactory.getInstance().focusListPeaple(getActivity(), map);
        myfocus_listview.setMode(PullToRefreshBase.Mode.PULL_UP_TO_REFRESH);
        myfocus_listview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                index++;
                HashMap<String,String> map = new HashMap<String,String>();
                map.put("token", SharePreferenceUtils.getInstance(getActivity(),SharePreferenceConstance.USERNAME).getSharePreferenceString(SharePreferenceConstance.USERTOKEN,null));
                map.put("listType",String.valueOf(2));
                map.put("page",String.valueOf(index));
                HttpFactory.getInstance().focusListPeaple(getActivity(), map);
            }
        });
    }
    public void onEventMainThread(String s)
    {
        if (s.equals(String.valueOf(3)))
        {
            Log.i("www","跟新数据");
            HashMap<String,String> map = new HashMap<String,String>();
            map.put("token", SharePreferenceUtils.getInstance(getActivity(),SharePreferenceConstance.USERNAME).getSharePreferenceString(SharePreferenceConstance.USERTOKEN,null));
            map.put("listType",String.valueOf(2));
            map.put("page", String.valueOf(0));
            HttpFactory.getInstance().focusListPeaple(getActivity(), map);
            index=0;
            printingPeapleData2.clear();
        }

    }
    public void onEventMainThread(PrintingPeapleEntryTwo PrintingPeapleEntry) {
       List<PrintingPeapleData> printingPeapleData =  PrintingPeapleEntry.getData();
           // Log.i("www","printingPeapleEntry"+printingPeapleEntry);
        if (printingPeapleData.size()==0)
        {
            Log.i("www", "数据加载完");
            Toast.makeText(getActivity(), "亲！没有数据哦", Toast.LENGTH_SHORT).show();
        }
        printingPeapleData2.addAll(printingPeapleData);

        if (printingPeapleData2.size()==0)
        {
            myfocus_listview.setAdapter(EmptyAdapter.getInstanceAdapter(getActivity()));

        }else{


            if (!isFirst) {
                isFirst = !isFirst;
                pringtingPeapleAdapter = new MyfocusPringtingPeapleAdapter(getActivity(), printingPeapleData2, R.layout.printingpeple_listview_item);
                myfocus_listview.setAdapter(pringtingPeapleAdapter);
            }else{

                    pringtingPeapleAdapter.notifyDataSetChanged();
                    myfocus_listview.onRefreshComplete();

            }

        }
    }

}
