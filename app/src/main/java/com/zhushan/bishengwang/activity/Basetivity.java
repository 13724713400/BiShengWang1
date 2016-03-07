package com.zhushan.bishengwang.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;


import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.zhushan.bishengwang.Iaplication.TBaplication;
import com.zhushan.bishengwang.Itools.PrintCallStatckUtils;
import com.zhushan.bishengwang.Itools.StatusBarCompat;
import com.zhushan.bishengwang.R;
import com.zhy.autolayout.AutoLayoutActivity;

public abstract  class Basetivity extends AutoLayoutActivity{

  /*  protected BroadcastReceiver myBroadCastReiceiVer = new BroadcastReceiver(){
        @Override
        public void onReceive(Context context, Intent intent) {
           Log.i("www","www"+getLocalClassName()) ;
                finish();
        }
    };*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_basetivity);
       /* AutoLayout.getInstance().auto(this);*/
       /* View bv = this.findViewById(android.R.id.title);
        ((TextView) bv).setTextColor(Color.WHITE);
        ((View) bv.getParent()).setBackgroundColor(Color.RED);*/
/*
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.zhushan.mybroadcast");
       this.registerReceiver(myBroadCastReiceiVer,intentFilter);*/
       TBaplication.list.add(this);
        PrintCallStatckUtils.getInstance().printCallStatck();
        PrintCallStatckUtils.getInstance().getMemoryInfo();
        /**
         * 使用19以上
         */
        StatusBarCompat.compat(this, getResources().getColor(R.color.textcoloryellow));
    }

    public abstract void initView();
    public abstract void initData();
    public abstract void initListener();
    @Override
    protected void onResume() {
        super.onResume();
        Log.i("www","onResume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("www", "onDestroy");
        //unregisterReceiver(myBroadCastReiceiVer);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("www", "onPause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("www", "onRestart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("www", "onStop");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("www", "onStart");
    }


}
