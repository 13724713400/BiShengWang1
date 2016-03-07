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
import com.zhushan.bishengwang.Entry.JiuEntry;
import com.zhushan.bishengwang.Entry.MyPublicData;
import com.zhushan.bishengwang.Entry.MyPublicEntry;
import com.zhushan.bishengwang.Entry.MyPublicQiuEntry;
import com.zhushan.bishengwang.Entry.QiuEntry;
import com.zhushan.bishengwang.Iadapter.EmptyAdapter;
import com.zhushan.bishengwang.Iadapter.MyPublishiAdapter;
import com.zhushan.bishengwang.Ifactory.HttpFactory;
import com.zhushan.bishengwang.Itools.SharePreferenceUtils;
import com.zhushan.bishengwang.Iview.MainVu;
import com.zhushan.bishengwang.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.greenrobot.event.EventBus;


/**
 * Created by Administrator on 2015/12/1.
 */
public class Fragment_mypulish_qiu extends Fragment {
    private MainVu mainVu = new MainVu();
    private PullToRefreshListView pullToRefreshListView;
    private ListView mypublishi_shuai_listview;
    private  HashMap<String,String> map;
    private List<MyPublicData> myPublicDatas2 = new ArrayList<MyPublicData>();
    private MyPublishiAdapter myPublishiAdapter;
    private int index;
    private boolean isFirst;
    private  Bundle bundle;
    private boolean isGet;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mainVu.initByLayout(getActivity(), R.layout.publish_listview);
        initView();
      initData();
      // View view =  inflater.inflate(R.layout.publish_listview,container,false);
        return mainVu.getView();
    }
    private void initData() {

        map = new HashMap<String,String>();
         bundle = getActivity().getIntent().getExtras();
        if (bundle!=null)
        {
           String user_id =  bundle.getString("user_id");
            map.put("page",String.valueOf(0));
            map.put("cate_id",String.valueOf(1));
            map.put("user_id",user_id);
            HttpFactory.getInstance().HisPublicQiu(getActivity(),map,pullToRefreshListView);

        }else {

            map.put("token", SharePreferenceUtils.getInstance(getActivity(), SharePreferenceConstance.USERNAME).getSharePreferenceString(SharePreferenceConstance.USERTOKEN, null));
            map.put("page", String.valueOf(0));
            map.put("cate_id", String.valueOf(1));
            HttpFactory.getInstance().MyPublicQiu(getActivity(), map, pullToRefreshListView);
        }
        pullToRefreshListView.setMode(PullToRefreshBase.Mode.PULL_UP_TO_REFRESH);
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                index++;
                if (bundle != null) {
                    String user_id = bundle.getString("user_id");
                    map.put("page", String.valueOf(index));
                    map.put("cate_id", String.valueOf(1));
                    map.put("user_id", user_id);
                    HttpFactory.getInstance().HisPublicQiu(getActivity(), map, pullToRefreshListView);

                } else {

                    map.put("token", SharePreferenceUtils.getInstance(getActivity(), SharePreferenceConstance.USERNAME).getSharePreferenceString(SharePreferenceConstance.USERTOKEN, null));
                    map.put("page", String.valueOf(index));
                    map.put("cate_id", String.valueOf(1));
                    HttpFactory.getInstance().MyPublicQiu(getActivity(), map, pullToRefreshListView);
                }
            }
        });

    }

 public void onEventMainThread(QiuEntry s)
    {
        Log.i("www", "s" + s);
        isGet = true;
            index=0;
            myPublicDatas2.clear();
            map.put("token", SharePreferenceUtils.getInstance(getActivity(), SharePreferenceConstance.USERNAME).getSharePreferenceString(SharePreferenceConstance.USERTOKEN, null));
            map.put("page", String.valueOf(0));
            map.put("cate_id", String.valueOf(2));
        HttpFactory.getInstance().MyPublicQiu(getActivity(), map, pullToRefreshListView);



    }

    public void onEventMainThread(MyPublicQiuEntry myPublicEntry)
    {
        List<MyPublicData> myPublicDatas = myPublicEntry.getData();
        if (myPublicDatas==null)
        {
            Log.i("www", "加载完数据");
            Toast.makeText(getActivity(), "已无数据", Toast.LENGTH_LONG).show();
            pullToRefreshListView.onRefreshComplete();

        }
        if (myPublicDatas!=null) {
            myPublicDatas2.addAll(myPublicDatas);
        }


        if (myPublicDatas2.size()==0)
        {
            pullToRefreshListView.setAdapter(EmptyAdapter.getInstanceAdapter(getActivity()));

        }else{
            if (!isFirst) {
                isFirst = !isFirst;
                myPublishiAdapter = new MyPublishiAdapter(getActivity(), myPublicDatas, R.layout.publish_listview_item);
                pullToRefreshListView.setAdapter(myPublishiAdapter);
            }else{
                if (!isGet) {
                    myPublishiAdapter.notifyDataSetChanged();
                    pullToRefreshListView.onRefreshComplete();
                }else{
                    isGet = !isGet;
                    myPublishiAdapter = new MyPublishiAdapter(getActivity(), myPublicDatas, R.layout.publish_listview_item);
                    pullToRefreshListView.setAdapter(myPublishiAdapter);
                }
            }

        }



    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void initView() {
        EventBus.getDefault().register(this);
        pullToRefreshListView =  mainVu.getItemView(R.id.mypublishi_shuai_listview);

    }


}
