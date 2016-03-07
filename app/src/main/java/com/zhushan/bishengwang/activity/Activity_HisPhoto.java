package com.zhushan.bishengwang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zhushan.bishengwang.Constance.SharePreferenceConstance;
import com.zhushan.bishengwang.Entry.MyPhotoData;
import com.zhushan.bishengwang.Entry.MyPhotoEntry;
import com.zhushan.bishengwang.Iadapter.EmptyAdapter;
import com.zhushan.bishengwang.Iadapter.MyPhotoAdapterThree;
import com.zhushan.bishengwang.Iadapter.MyPhotoAdapterTwo;
import com.zhushan.bishengwang.Ifactory.HttpFactory;
import com.zhushan.bishengwang.Itools.BackActivity;
import com.zhushan.bishengwang.Itools.CameraUtils;
import com.zhushan.bishengwang.Itools.IntentUtils;
import com.zhushan.bishengwang.Itools.SharePreferenceUtils;
import com.zhushan.bishengwang.Iview.MainVu;
import com.zhushan.bishengwang.R;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.greenrobot.event.EventBus;

public class Activity_HisPhoto extends Basetivity {

    private MainVu mainVu = new MainVu();
    private ImageView myphoto_addimg;
    private RecyclerView myphoto_add_recyclerview;
    private ArrayList<String> list;
    private MyPhotoAdapterThree myPhotoAdapterTwo;
    private List<MyPhotoData> myPhotoDatas2 = new ArrayList<MyPhotoData>();
    private PullToRefreshListView myphoto_pulltorefreshlistview;
    private boolean isFirst;
    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(mainVu.initByLayout(this, R.layout.activity_activity__my_photo));
        initView();
        initData();
        initListener();

    }
    public void onEventMainThread(MyPhotoEntry myPhotoEntry)
    {

        if (myPhotoEntry.getData()==null)
        {
            Toast.makeText(Activity_HisPhoto.this, "已无数据", Toast.LENGTH_SHORT).show();
            myphoto_pulltorefreshlistview.onRefreshComplete();
            return;
        }
        if(myPhotoEntry.getData()!=null) {

            myPhotoDatas2.addAll(myPhotoEntry.getData());
        }
        if (myPhotoDatas2.size()==0)
        {
            myphoto_pulltorefreshlistview.setAdapter(EmptyAdapter.getInstanceAdapter(Activity_HisPhoto.this));
        }else{

            if (!isFirst) {
                isFirst = !isFirst;
                Log.i("www"," dtata"+myPhotoDatas2);
                myPhotoAdapterTwo = new MyPhotoAdapterThree(Activity_HisPhoto.this, myPhotoDatas2, R.layout.myphoto_item);
                myphoto_pulltorefreshlistview.setAdapter(myPhotoAdapterTwo);
            }else{
                myPhotoAdapterTwo.notifyDataSetChanged();
                myphoto_pulltorefreshlistview.onRefreshComplete();
            }
        }


    }


    @Override
    public void initData() {
        id =0;
        mainVu.getItemView(R.id.myPhoto_today).setVisibility(View.GONE);
        mainVu.getItemView(R.id.myPhoto_add).setVisibility(View.GONE);
        BackActivity.finishActivity(this,R.id.myphoto_back);
        Bundle bundle = getIntent().getExtras();
        if (bundle!=null)
        {
            mainVu.getItemView(R.id.myPhoto_today).setVisibility(View.GONE);
            mainVu.getItemView(R.id.myPhoto_add).setVisibility(View.GONE);
            ((TextView)mainVu.getItemView(R.id.myphoto_titles)).setText("Ta的相册");
            String user_id =  bundle.getString("user_id");
            HashMap<String,String> map = new HashMap<String,String>();
            map.put("user_id", user_id);
            HttpFactory.getInstance().myGallery(this,map);

        }
    }

    @Override
    public void initListener() {


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void initView() {
        EventBus.getDefault().register(this);
       // myphoto_addimg =  mainVu.getItemView(R.id.myphoto_addimg);
        myphoto_pulltorefreshlistview =  mainVu.getItemView(R.id.myphoto_pulltorefreshlistview);

    }

}
