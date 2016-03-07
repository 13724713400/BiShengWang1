/*
package com.zhushan.bishengwang.Itools;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.zhushan.bishengwang.R;

*/
/**
 * Created by Administrator on 2015/12/9.
 *//*

public class BiaduMapUtils {

    public void myPosition(BaiduMap mBaiduMap)
    {
        mBaiduMap.setMyLocationEnabled(true);
// 构造定位数据
        MyLocationData locData = new MyLocationData.Builder()
                .accuracy(location.getRadius())
                        // 此处设置开发者获取到的方向信息，顺时针0-360
                .direction(100).latitude(location.getLatitude())
                .longitude(location.getLongitude()).build();
// 设置定位数据
        mBaiduMap.setMyLocationData(locData);
// 设置定位图层的配置（定位模式，是否允许方向信息，用户自定义定位图标）
        mCurrentMarker = BitmapDescriptorFactory
                .fromResource(R.mipmap.liuyanhead);
        MyLocationConfiguration config = new MyLocationConfiguration(mCurrentMode, true, mCurrentMarker);
        mBaiduMap.setMyLocationConfiguration();
// 当不需要定位图层时关闭定位图层
        mBaiduMap.setMyLocationEnabled(false);

    }


}
*/
