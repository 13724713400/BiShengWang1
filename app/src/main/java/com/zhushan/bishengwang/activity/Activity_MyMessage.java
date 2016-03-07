package com.zhushan.bishengwang.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zhushan.bishengwang.Constance.SharePreferenceConstance;
import com.zhushan.bishengwang.Entry.CommentEntry;
import com.zhushan.bishengwang.Entry.MyMessageData;
import com.zhushan.bishengwang.Entry.MyMessageEntry;
import com.zhushan.bishengwang.Iadapter.DetailesLiuYanAdapter;
import com.zhushan.bishengwang.Iadapter.EmptyAdapter;
import com.zhushan.bishengwang.Iadapter.MyMEssageAdapter;
import com.zhushan.bishengwang.Ifactory.HttpFactory;
import com.zhushan.bishengwang.Itools.BackActivity;
import com.zhushan.bishengwang.Itools.SharePreferenceUtils;
import com.zhushan.bishengwang.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.greenrobot.event.EventBus;

public class Activity_MyMessage extends Basetivity {

    private int index;
    private PullToRefreshListView mymessage_liuyan_listView;
    private List<MyMessageData> myMessageDatas2 = new ArrayList<MyMessageData>();
    private boolean isFirst;
    private MyMEssageAdapter myMEssageAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity__my_message);
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
        mymessage_liuyan_listView  = (PullToRefreshListView) findViewById(R.id.mymessage_liuyan_listView);

    }
    public void onEventMainThread(String s)
    {
        if (s.equals(String.valueOf(7)))
        {
            index=0;
            myMessageDatas2.clear();
            HashMap<String,String> map = new HashMap<String,String>();
            map.put("token", SharePreferenceUtils.getInstance(this, SharePreferenceConstance.USERNAME).getSharePreferenceString(SharePreferenceConstance.USERTOKEN,null));
            map.put("page",String.valueOf(0));
            HttpFactory.getInstance().MyComment(this, map);

        }

    }
    public void onEventMainThread(MyMessageEntry myMessageEntry)
    {
        if (myMessageEntry.getData()==null)
        {
            Toast.makeText(this, "已无数据", Toast.LENGTH_SHORT).show();
            mymessage_liuyan_listView.onRefreshComplete();
            return;
        }
        if (myMessageEntry.getData()!=null) {
            myMessageDatas2.addAll(myMessageEntry.getData());
        }
        if (myMessageDatas2==null)
        {
            mymessage_liuyan_listView.setAdapter(EmptyAdapter.getInstanceAdapter(this));
        }else{

            if (!isFirst) {
                isFirst = !isFirst;
                Log.i("www", " dtata" + myMessageDatas2);
                myMEssageAdapter = new MyMEssageAdapter(this, myMessageDatas2, R.layout.mymessage_listview_item);
                mymessage_liuyan_listView.setAdapter(myMEssageAdapter);
            }else{
                myMEssageAdapter.notifyDataSetChanged();
                mymessage_liuyan_listView.onRefreshComplete();
            }
        }


    }

    @Override
    public void initData() {
        Log.i("www","我的留言");
        BackActivity.finishActivity(this,R.id.mymessage_item_back);
       final HashMap<String,String> map = new HashMap<String,String>();
        map.put("token", SharePreferenceUtils.getInstance(this, SharePreferenceConstance.USERNAME).getSharePreferenceString(SharePreferenceConstance.USERTOKEN,null));
        map.put("page",String.valueOf(0));
        HttpFactory.getInstance().MyComment(this, map);
        mymessage_liuyan_listView.setMode(PullToRefreshBase.Mode.PULL_UP_TO_REFRESH);
        mymessage_liuyan_listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                index++;
                map.put("page",String.valueOf(index));
                HttpFactory.getInstance().MyComment(Activity_MyMessage.this, map);

            }
        });
    }

    @Override
    public void initListener() {

    }
}
