package com.zhushan.bishengwang.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.zhushan.bishengwang.R;

public class Activity_BaiduSerch extends AppCompatActivity {

    private MapView mMapView;
    private BaiduMap baiduMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity__baidu_serch);
        mMapView = (MapView) findViewById(R.id.bmapView);
        initData();
    }

    private void initData() {

        baiduMap = mMapView.getMap();
        baiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mMapView.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();

        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();

        mMapView.onPause();
    }
}




