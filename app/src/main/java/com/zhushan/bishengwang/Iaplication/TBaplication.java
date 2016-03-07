package com.zhushan.bishengwang.Iaplication;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.HttpClientStack;
import com.android.volley.toolbox.HttpStack;
import com.android.volley.toolbox.Volley;
import com.baidu.location.LocationClient;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.radar.RadarSearchManager;
import com.squareup.okhttp.OkHttpClient;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.zhushan.bishengwang.Constance.ConstanceTag;

import com.zhushan.bishengwang.Itools.PositionMySeft;
import com.zhushan.bishengwang.Itools.PreferencesCookieStore;
import com.zhushan.bishengwang.Itools.UmengUtils;

import org.apache.http.impl.client.DefaultHttpClient;
import org.xutils.x;


import java.util.ArrayList;

/**
 * Created by Administrator on 2015/11/28.
 */
public class TBaplication extends Application{
    public static ArrayList<Activity> list = new ArrayList<Activity>();
    private static Context context;
    public  static Context getInstance()
    {
        return context;
    }
    public static RequestQueue requestQueue,mRequestQueue;
    public static  UMSocialService mController;
    public static LocationClient locationClient;
    public static RadarSearchManager radarSearchManager;
    public static OkHttpClient okHttpClient;
    public  static SQLiteDatabase getWritableDatabase;
    public  static SQLiteDatabase getReadableDatabase;
    private PositionMySeft positionMySeft;
    @Override
    public void onCreate() {
        super.onCreate();
        context  = getApplicationContext();
       requestQueue = Volley.newRequestQueue(this);
        x.Ext.init(this);
        //是否输出日志
       x.Ext.setDebug(true);
        SDKInitializer.initialize(getApplicationContext());
        mController = UMServiceFactory
                .getUMSocialService("com.umeng.login");
        locationClient = new LocationClient(this.getApplicationContext());
    //   getRequestQueue();
        Log.i("www", "aplication");
        radarSearchManager = RadarSearchManager.getInstance();
        okHttpClient = new OkHttpClient();
       // DbHelpUtils  dbHelpUtils = new DbHelpUtils(this, ConstanceTag.SQLDB,null,1);
//         getWritableDatabase = dbHelpUtils.getWritableDatabase();
        // getReadableDatabase = dbHelpUtils.getReadableDatabase();

    }

 private RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            DefaultHttpClient httpclient = new DefaultHttpClient();
            org.apache.http.client.CookieStore cookieStore = new PreferencesCookieStore(this);
            httpclient.setCookieStore(cookieStore);
            HttpStack httpStack = new HttpClientStack(httpclient);
            mRequestQueue = Volley.newRequestQueue(getApplicationContext(),httpStack);
        }
        return mRequestQueue;
    }
    public static void exit()
    {
        for(Activity activity:list)
        {
            activity.finish();
            Log.i("www","activity"+activity);
        }

        System.exit(0);

    }



}
