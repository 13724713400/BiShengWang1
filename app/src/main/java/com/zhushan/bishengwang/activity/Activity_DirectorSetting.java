package com.zhushan.bishengwang.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;

import com.zhushan.bishengwang.Constance.SharePreferenceConstance;
import com.zhushan.bishengwang.Entry.DirectorSettingData;
import com.zhushan.bishengwang.Entry.DirectorSettingEntry;
import com.zhushan.bishengwang.Entry.Label_list;
import com.zhushan.bishengwang.Iadapter.BaseEpandable;
import com.zhushan.bishengwang.Iadapter.DirectorSettingAdapter;
import com.zhushan.bishengwang.Ifactory.HttpFactory;
import com.zhushan.bishengwang.Itools.BackActivity;

import com.zhushan.bishengwang.Itools.SharePreferenceUtils;
import com.zhushan.bishengwang.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.greenrobot.event.EventBus;

public class Activity_DirectorSetting extends Basetivity implements View.OnClickListener{

    private List<String> list;
    private ArrayList<List<DirectorSettingEntry>> childList;
    private ExpandableListView expandableListView;
    private DirectorSettingEntry directorSettingEntry;
    private Button director_setting_sure;
    private List<DirectorSettingData> directorSettingDataTwo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity__director_setting);
        initView();
        initData();
        initListener();
    }
    @Override
    public void initView() {
        EventBus.getDefault().register(this);
        expandableListView = (ExpandableListView) findViewById(R.id.director_setting_el);
        director_setting_sure = (Button) findViewById(R.id.director_setting_sure);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void initData() {
        BackActivity.finishActivity(this, R.id.director_setting_back);
        HashMap<String,String> map = new HashMap<String,String>();
       // String token =  SharePreferenceUtils.getInstance(this, SharePreferenceConstance.USERNAME).getSharePreferenceString(SharePreferenceConstance.USERTOKEN,null);
        HttpFactory.getInstance().DirectorSetting(expandableListView,this,map);
    }
    public void onEventMainThread( List<DirectorSettingData> directorSettingData)
    {
        directorSettingDataTwo =  directorSettingData;

    }
    @Override
    public void initListener() {
        director_setting_sure.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.director_setting_sure:
                StringBuilder stringBuilder = new StringBuilder();
                for (DirectorSettingData directorSettingData:directorSettingDataTwo)
                {
                    for (Label_list label_list:directorSettingData.getLabel_list())
                    {
                        if (label_list.ischeck())
                        {
                            label_list.getLabel_name();
                            stringBuilder.append(label_list.getLabel_name()+",");
                        }
                    }

                }
                EventBus.getDefault().post(stringBuilder);
                Activity_DirectorSetting.this.finish();

                break;

        }
    }
}
