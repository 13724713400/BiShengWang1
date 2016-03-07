package com.zhushan.bishengwang.Itools;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.radar.RadarNearbyInfo;
import com.baidu.mapapi.radar.RadarNearbyResult;
import com.baidu.mapapi.radar.RadarNearbySearchOption;
import com.baidu.mapapi.radar.RadarSearchError;
import com.baidu.mapapi.radar.RadarSearchListener;
import com.baidu.mapapi.radar.RadarSearchManager;
import com.baidu.mapapi.radar.RadarUploadInfo;

/**
 * Created by Administrator on 2015/12/14.
 */
public class SurroungdingRetar implements RadarSearchListener {
    private Context context;
    public SurroungdingRetar( Context context)
    {
        this.context = context;
    }
    public void getSurroundingInfo(  RadarSearchManager radarSearchManager,double mlat,double mLon)
    {
        LatLng pt = new LatLng(mlat,mLon);
        RadarNearbySearchOption option = new RadarNearbySearchOption().centerPt(pt).pageNum(10).radius(20000);
//发起查询请求
        radarSearchManager.nearbyInfoRequest(option);

    }

    public void destroyinfo( RadarSearchManager mManager)
    {
        mManager.removeNearbyInfoListener(this);
//清除用户信息
        mManager.clearUserInfo();
//释放资源
        mManager.destroy();
        mManager = null;
    }
    public void initSurroung(  RadarSearchManager radarSearchManager ,double mlat,double mLon)
    {
        /**
         * 当前的位置信息
         */
        LatLng latLng = new LatLng(mlat,mLon);
       radarSearchManager.addNearbyInfoListener(this);
        radarSearchManager.setUserID("");
        RadarUploadInfo info = new RadarUploadInfo();
        info.comments="备注信息";
        info.pt = latLng;
        radarSearchManager.uploadInfoRequest(info);
    }
    @Override
    public void onGetNearbyInfoList(RadarNearbyResult radarNearbyResult, RadarSearchError radarSearchError) {
     if (radarNearbyResult==null)
     {
         return;

     }
        if(radarNearbyResult.infoList==null)
        {
            return;
        }
        for (RadarNearbyInfo info:radarNearbyResult.infoList)
        {
            Log.i("www","周边用户信息"+info.distance);
            Toast.makeText(context,"周边的结果"+info.distance,Toast.LENGTH_LONG).show();
        }
        if (radarSearchError == RadarSearchError.RADAR_NO_ERROR) {
           // Log.i("www","周边的结果"+radarNearbyResult.toString());
            Toast.makeText(context, "查询周边成功", Toast.LENGTH_LONG)
                    .show();
            //获取成功，处理数据
        } else {
            Log.i("www","查询周边失败"+radarSearchError);
            //获取失败
            Toast.makeText(context, "查询周边失败", Toast.LENGTH_LONG)
                    .show();
        }
    }

    @Override
    public void onGetUploadState(RadarSearchError radarSearchError) {
        if (radarSearchError == RadarSearchError.RADAR_NO_ERROR) {
            //上传成功
            Toast.makeText(context, "单次上传位置成功", Toast.LENGTH_LONG)
                    .show();
        } else {
            //上传失败
            Toast.makeText(context, "单次上传位置失败", Toast.LENGTH_LONG)
                    .show();
        }

    }

    @Override
    public void onGetClearInfoState(RadarSearchError radarSearchError) {

    }
}
