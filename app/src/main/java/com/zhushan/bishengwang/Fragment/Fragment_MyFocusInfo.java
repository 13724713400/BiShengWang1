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
import com.zhushan.bishengwang.Iadapter.EmptyAdapter;
import com.zhushan.bishengwang.Iadapter.HompageItemAdapter;
import com.zhushan.bishengwang.Iadapter.MyFocusItemAdapter;
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
public class Fragment_MyFocusInfo extends Fragment {

    private MainVu mainVu = new MainVu();
    private PullToRefreshListView myfocus_listview;
    private MyFocusItemAdapter hompageItemAdapter;
    private int index;
    private boolean isFirst;
    private  List<HomePageInfoData> homePageInfoDatas2 = new ArrayList<HomePageInfoData>();
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
        map.put("listType",String.valueOf(1));
        map.put("page",String.valueOf(0));
        HttpFactory.getInstance().focusList(getActivity(), map);
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
                map.put("listType",String.valueOf(1));
                map.put("page",String.valueOf(index));
                HttpFactory.getInstance().focusList(getActivity(), map);
            }
        });

    }

    public void onEventMainThread(String s)
    {
        if (s.equals(String.valueOf(2)))
        {
            HashMap<String,String> map = new HashMap<String,String>();
            map.put("token", SharePreferenceUtils.getInstance(getActivity(),SharePreferenceConstance.USERNAME).getSharePreferenceString(SharePreferenceConstance.USERTOKEN,null));
            map.put("listType",String.valueOf(1));
            map.put("page",String.valueOf(0));
            HttpFactory.getInstance().focusList(getActivity(), map);
            index=0;
            homePageInfoDatas2.clear();
        }

    }
    public void onEventMainThread( List<HomePageInfoData> homePageInfoDatas) {
      /*  Log.i("www","传递的数据"+homePageInfoEntry);
        List<HomePageInfoData> homePageInfoDatas =  homePageInfoEntry.getData();
*/
        if (homePageInfoDatas.size()==0)
        {
            Log.i("www", "数据加载完");
            Toast.makeText(getActivity(), "亲！没有数据哦", Toast.LENGTH_SHORT).show();
            myfocus_listview.onRefreshComplete();
        }
        homePageInfoDatas2.addAll(homePageInfoDatas);

        if (homePageInfoDatas2.size()==0)
        {
            myfocus_listview.setAdapter(EmptyAdapter.getInstanceAdapter(getActivity()));

        }else{
            if (!isFirst) {
                isFirst = !isFirst;
                hompageItemAdapter = new MyFocusItemAdapter(getActivity(), homePageInfoDatas2, R.layout.homepage_listview_item);
                myfocus_listview.setAdapter(hompageItemAdapter);
            }else{

                    hompageItemAdapter.notifyDataSetChanged();
                    myfocus_listview.onRefreshComplete();
            }

        }
    }

}
