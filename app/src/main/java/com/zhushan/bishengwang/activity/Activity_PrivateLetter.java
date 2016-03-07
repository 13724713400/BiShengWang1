package com.zhushan.bishengwang.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zhushan.bishengwang.Constance.SharePreferenceConstance;
import com.zhushan.bishengwang.Entry.MyMessageEntry;
import com.zhushan.bishengwang.Entry.PrivateData;
import com.zhushan.bishengwang.Entry.privateEntry;
import com.zhushan.bishengwang.Iadapter.EmptyAdapter;
import com.zhushan.bishengwang.Iadapter.MyMEssageAdapter;
import com.zhushan.bishengwang.Iadapter.MyPrivateAdapter;
import com.zhushan.bishengwang.Ifactory.HttpFactory;
import com.zhushan.bishengwang.Itools.BackActivity;
import com.zhushan.bishengwang.Itools.SharePreferenceUtils;
import com.zhushan.bishengwang.Iview.MainVu;
import com.zhushan.bishengwang.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.greenrobot.event.EventBus;

public class Activity_PrivateLetter extends Basetivity {

    private MainVu mainVu = new MainVu();
    private PullToRefreshListView privateletter_listView;
    private  HashMap<String,String> map;
    private int index;
    private List<PrivateData> privateDataList2 = new ArrayList<PrivateData>();
    private boolean isFirst;
    private MyPrivateAdapter myPrivateAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(mainVu.initByLayout(this,R.layout.activity_activity__private_letter));
        initView();
        initData();
        initListener();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void initView() {
        EventBus.getDefault().register(this);
        privateletter_listView =  mainVu.getItemView(R.id.privateletter_listView);

    }

    public void onEventMainThread(String s)
    {
        if (s.equals(String.valueOf(6)))
        {
            index=0;
            privateDataList2.clear();
            map = new HashMap<String,String>();
            map.put("token", SharePreferenceUtils.getInstance(this, SharePreferenceConstance.USERNAME).getSharePreferenceString(SharePreferenceConstance.USERTOKEN,null));
            map.put("page",String.valueOf(0));
            HttpFactory.getInstance().msgList(this, map);

        }

    }
  public void onEventMainThread(privateEntry privateEntry2)
    {
        if (privateEntry2.getData()==null)
        {
            Toast.makeText(this, "已无数据", Toast.LENGTH_SHORT).show();
            privateletter_listView.onRefreshComplete();
            return;
        }
        privateDataList2.addAll(privateEntry2.getData());
        if (privateDataList2.size()==0)
        {
            privateletter_listView.setAdapter(EmptyAdapter.getInstanceAdapter(this));
        }else{

            if (!isFirst) {
                isFirst = !isFirst;
                myPrivateAdapter = new MyPrivateAdapter(this, privateDataList2, R.layout.myprivate_listview_item);
                privateletter_listView.setAdapter(myPrivateAdapter);
            }else{
                myPrivateAdapter.notifyDataSetChanged();
                privateletter_listView.onRefreshComplete();
            }
        }


    }

    @Override
    public void initData() {
        BackActivity.finishActivity(this,R.id.private_letter_back);
        map = new HashMap<String,String>();
        map.put("token", SharePreferenceUtils.getInstance(this, SharePreferenceConstance.USERNAME).getSharePreferenceString(SharePreferenceConstance.USERTOKEN,null));
        map.put("page",String.valueOf(0));
        HttpFactory.getInstance().msgList(this, map);
        privateletter_listView.setMode(PullToRefreshBase.Mode.PULL_UP_TO_REFRESH);
        privateletter_listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                index++;
                map.put("token", SharePreferenceUtils.getInstance(Activity_PrivateLetter.this, SharePreferenceConstance.USERNAME).getSharePreferenceString(SharePreferenceConstance.USERTOKEN,null));
                map.put("page",String.valueOf(index));
                HttpFactory.getInstance().msgList(Activity_PrivateLetter.this, map);
            }
        });

    }

    @Override
    public void initListener() {

    }

}
