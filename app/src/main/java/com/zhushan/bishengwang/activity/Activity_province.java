package com.zhushan.bishengwang.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.zhushan.bishengwang.Ifactory.HttpFactory;
import com.zhushan.bishengwang.Itools.BackActivity;
import com.zhushan.bishengwang.R;

public class Activity_province extends Basetivity {

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
        province_listview.setCacheColorHint(0);
        province_city = (TextView) findViewById(R.id.province_city);
    }

    @Override
    public void initData() {

        province_city.setText("省份");
        BackActivity.finishActivity(this,R.id.province_back);
        Bundle bundle =  getIntent().getExtras();
        String flag=null;
        if (bundle!=null)
        {
            flag = (String) bundle.get("flag");
        }
        HttpFactory.getInstance().province(flag,this,province_listview);
    }

    @Override
    public void initListener() {

    }
}
