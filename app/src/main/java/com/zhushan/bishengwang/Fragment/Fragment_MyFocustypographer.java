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
import com.zhushan.bishengwang.Entry.DataList;
import com.zhushan.bishengwang.Entry.HomePageInfoData;
import com.zhushan.bishengwang.Entry.HomePageInfoEntry;
import com.zhushan.bishengwang.Entry.MyFocusTypographerData;
import com.zhushan.bishengwang.Entry.MyFocusTypographerEntry;
import com.zhushan.bishengwang.Entry.TypographerData;
import com.zhushan.bishengwang.Entry.TypograpterEntry;
import com.zhushan.bishengwang.Iadapter.EmptyAdapter;
import com.zhushan.bishengwang.Iadapter.HompageItemAdapter;
import com.zhushan.bishengwang.Iadapter.MyFocustAdapter;
import com.zhushan.bishengwang.Iadapter.RunRankAdapter;
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
public class Fragment_MyFocustypographer extends Fragment {

    private MainVu mainVu = new MainVu();
    private PullToRefreshListView myfocus_listview;
    private HompageItemAdapter hompageItemAdapter;
    private  List<MyFocusTypographerData> dataList2 = new ArrayList<MyFocusTypographerData>();
    private MyFocustAdapter runRankAdapter;
    private boolean isFirst;
    private int index;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view =  mainVu.initByLayout(getActivity(), R.layout.myfocusinfo);
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
        map.put("listType",String.valueOf(3));
        map.put("page",String.valueOf(0));
        HttpFactory.getInstance().focusListtypographer(getActivity(), map);
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
                map.put("listType",String.valueOf(3));
                map.put("page",String.valueOf(index));
                HttpFactory.getInstance().focusListtypographer(getActivity(), map);

            }
        });
    }

    public void onEventMainThread(String s)
    {
        Log.i("www","shuaxin");
        if (s.equals(String.valueOf(4)))
        {
            index=0;
            dataList2.clear();
            HashMap<String,String> map = new HashMap<String,String>();
            map.put("token", SharePreferenceUtils.getInstance(getActivity(),SharePreferenceConstance.USERNAME).getSharePreferenceString(SharePreferenceConstance.USERTOKEN,null));
            map.put("listType",String.valueOf(3));
            map.put("page", String.valueOf(index));
            HttpFactory.getInstance().focusListtypographer(getActivity(), map);


        }

    }
    public void onEventMainThread(MyFocusTypographerEntry myFocusTypographerEntry) {
        if (myFocusTypographerEntry.getData().size()==0)
        {
            Log.i("www", "数据加载完");
            Toast.makeText(getActivity(), "亲！没有数据哦", Toast.LENGTH_SHORT).show();
            myfocus_listview.onRefreshComplete();
        }
        dataList2.addAll(myFocusTypographerEntry.getData());

        if (dataList2.size()==0)
        {
            myfocus_listview.setAdapter(EmptyAdapter.getInstanceAdapter(getActivity()));

        }else{

            if (!isFirst) {
                isFirst = !isFirst;
                runRankAdapter = new MyFocustAdapter(getActivity(), dataList2,R.layout.typographer_listview_item);
                myfocus_listview.setAdapter(runRankAdapter);
            }else{
                myfocus_listview.onRefreshComplete();
                runRankAdapter.notifyDataSetChanged();
                }


            }
        }



}
