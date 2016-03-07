package com.zhushan.bishengwang.activity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.zhushan.bishengwang.Ifactory.HttpFactory;
import com.zhushan.bishengwang.Itools.BackActivity;
import com.zhushan.bishengwang.R;

import java.util.HashMap;

public class Activity_city extends Basetivity {

    private ListView province_listview;
    private TextView province_city;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_province);
        initView();
        initData();
        initListener();
    }

    @Override
    public void initView() {
        province_listview = (ListView) findViewById(R.id.province_listview);
        province_city = (TextView) findViewById(R.id.province_city);
        province_listview.setCacheColorHint(0);

    }

    @Override
    public void initData() {
        province_city.setText("市");
        BackActivity.finishActivity(this,R.id.province_back);
        Bundle bundle = getIntent().getExtras();
        if (bundle!=null) {
            HashMap<String,String> map = new HashMap<String,String>();
            map.put("province_id",bundle.getString("id"));

            HttpFactory.getInstance().city(bundle.getString("flag"),this, map,province_listview);
        }
    }

    @Override
    public void initListener() {

    }
}
