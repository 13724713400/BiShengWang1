package com.zhushan.bishengwang.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.zhushan.bishengwang.Constance.SharePreferenceConstance;
import com.zhushan.bishengwang.Ifactory.HttpFactory;
import com.zhushan.bishengwang.Itools.IntentUtils;
import com.zhushan.bishengwang.Itools.SharePreferenceUtils;
import com.zhushan.bishengwang.Itools.UmengUtils;
import com.zhushan.bishengwang.Itools.Utils;
import com.zhushan.bishengwang.MainActivity;
import com.zhushan.bishengwang.R;

import java.util.HashMap;

public class Activity_setting extends Basetivity implements View.OnClickListener{
    private UMSocialService mController = UMServiceFactory
            .getUMSocialService("com.umeng.login");
    private TextView exits_login,apkversion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_setting);
        initView();
        initData();
        initListener();
    }

    @Override
    public void initView() {
        String token = SharePreferenceUtils.getInstance(Activity_setting.this, SharePreferenceConstance.USERNAME).getSharePreferenceString(SharePreferenceConstance.USERTOKEN, null);
        exits_login = (TextView) findViewById(R.id.exits_login);
        SharedPreferences sp =Activity_setting.this.getSharedPreferences("config.txt", Context.MODE_PRIVATE);
        String tel = sp.getString("name",null);
        if(tel==null){
            exits_login.setVisibility(View.GONE);
        }else{
            exits_login.setVisibility(View.VISIBLE);
        }

        apkversion = (TextView) findViewById(R.id.apkversion);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        exits_login.setOnClickListener(this);
        apkversion.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.exits_login:
                UmengUtils.getInstance().cancleLogin(Activity_setting.this, mController, SHARE_MEDIA.QQ);

                    /*HashMap<String, String> map = new HashMap<String, String>();
                  *//*  map.put("token", SharePreferenceUtils.getInstance(Activity_setting.this, SharePreferenceConstance.USERNAME).getSharePreferenceString(SharePreferenceConstance.USERTOKEN, null));
                    HttpFactory.getInstance().UerOut(Activity_setting.this, map);*//*
                    IntentUtils.getInstance().startToAnoterActivity(Activity_setting.this, MainActivity.class, null);*/

                saveToSharedPreference(null,null,null);
                IntentUtils.getInstance().startToAnoterActivity(Activity_setting.this, MainActivity.class, null);
                break;

            case R.id.apkversion:

                String version = Utils.getAppVersiontwo(Activity_setting.this);
                Toast.makeText(Activity_setting.this, "当前版本"+version, Toast.LENGTH_SHORT).show();
                break;

        /*    case R.id.exits_login:

                break;

            case R.id.exits_login:

                break;*/

        }
    }

    public String saveToSharedPreference(String  name,String imgUrl ,String sex){
        SharedPreferences sp =Activity_setting.this.getSharedPreferences("config.txt", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("name",name);
        editor.putString("imgUrl",imgUrl);
        editor.putString("sex",sex);
        editor.putBoolean("success",true);
        editor.commit();
        String tel = sp.getString("name","没有获取到值");

        //  Toast.makeText(getActivity(),tel,Toast.LENGTH_SHORT).show();
        return  tel;
    }
}
