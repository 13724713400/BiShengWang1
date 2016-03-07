package com.zhushan.bishengwang.Itools;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.os.Vibrator;
import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.DistanceUtil;
import com.zhushan.bishengwang.Constance.ConstanceTag;
import com.zhushan.bishengwang.Constance.HandlerConstance;
import com.zhushan.bishengwang.Constance.SharePreferenceConstance;
import com.zhushan.bishengwang.Iaplication.TBaplication;
import com.zhushan.bishengwang.Ifactory.HttpFactory;
import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;
import org.simple.eventbus.ThreadMode;

import java.net.URL;
import java.text.DecimalFormat;

/**
 * Created by Administrator on 2015/12/12.
 */
public class PositionMySeft {
    private Context context;
    public interface getPosition
    {
        void getposition(double lat,double lng);
    }

    private getPosition getPosition;

    public void setGetPosition(PositionMySeft.getPosition getPosition) {
        this.getPosition = getPosition;
    }

    public void initPosition(Activity context, LocationClient mLocationClient) {

        this.context = context;
        MyLocationListener mMyLocationListener = new MyLocationListener(context);
        mLocationClient.registerLocationListener(mMyLocationListener);
        Vibrator mVibrator = (Vibrator) context.getSystemService(Service.VIBRATOR_SERVICE);
    }
    public void StartPosition(LocationClient mLocationClient) {
        initLocation(mLocationClient);
        mLocationClient.start();
        mLocationClient.requestLocation();


        //getDistance();
    }
    public void AddressChangeTolocation(Activity context,String address)
    {
        String url = "http://api.map.baidu.com/geocoder/v2/?address="+address+"&output=json&ak=QqT269VndwONZhfzeURGejBG&callback=showLocation";
        OkHttpUtils.getAsyn(context, url, OkHttpUtils.ADREESSCHANGE);
    }
    public static float getDistance(double mLat1,double mLon1,double mLat2,double mLon2)
    {
        LatLng pt1 = new LatLng(mLat1, mLon1);
        LatLng pt2 = new LatLng(mLat2, mLon2);
        double m = (DistanceUtil.getDistance(pt1,pt2));
        int m2 =  (int)Math.floor(m);
        float km =m2/1000f;
        float km2= Math.round(km*10)/10.0F;
        return km2;
    }
    private void initLocation(LocationClient mLocationClient) {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系，
        //int span=1000;
        // option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        // option.setIsNeedAddress(checkGeoLocation.isChecked());//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        option.setIgnoreKillProcess(true);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        mLocationClient.setLocOption(option);
    }
    public class MyLocationListener implements BDLocationListener {

        private Activity context;
        private EventBus eventBus;

        public MyLocationListener(Activity context) {
            this.context = context;


        }

        @Override
        public void onReceiveLocation(BDLocation location) {

            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            if (latitude == 4.9E-324 || longitude == 4.9E-324) {
                latitude = latitude / 1e6;
                longitude = longitude / 1e6;
            }
            StringBuffer sb = new StringBuffer(256);
            sb.append("time : ");
            sb.append(location.getTime());
            sb.append("\nerror code : ");
            sb.append(location.getLocType());
            sb.append("\nlatitude : ");
            sb.append(location.getLatitude());
            sb.append("\nlontitude : ");
            sb.append(location.getLongitude());
            sb.append("\nradius : ");
            sb.append(location.getRadius());
            if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
                sb.append("\nspeed : ");
                sb.append(location.getSpeed());// 单位：公里每小时
                sb.append("\nsatellite : ");
                sb.append(location.getSatelliteNumber());
                sb.append("\nheight : ");
                sb.append(location.getAltitude());// 单位：米
                sb.append("\ndirection : ");
                sb.append(location.getDirection());
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());
                sb.append("\ndescribe : ");
                sb.append("gps定位成功");

            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());
                //运营商信息
                sb.append("\noperationers : ");
                sb.append(location.getOperators());
                sb.append("\ndescribe : ");
                sb.append("网络定位成功");
            } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
                sb.append("\ndescribe : ");
                sb.append("离线定位成功，离线定位结果也是有效的");
            } else if (location.getLocType() == BDLocation.TypeServerError) {
                sb.append("\ndescribe : ");
                sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                sb.append("\ndescribe : ");
                sb.append("网络不同导致定位失败，请检查网络是否通畅");
            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                sb.append("\ndescribe : ");
                sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
            }
            Log.i("www", "定位" + sb.toString());
            String url = "http://api.map.baidu.com/geocoder/v2/?ak=QqT269VndwONZhfzeURGejBG&callback=renderReverse&location=" + location.getLatitude() + "," + location.getLongitude() + "&output=json&pois=0";
            //     getPosition.getposition(location.getLatitude(),location.getLongitude());
            OkHttpUtils.getAsyn(context, url, OkHttpUtils.ADREESS);
            SharePreferenceUtils.getInstance(context,SharePreferenceConstance.POSITIONNAME).CreateSharePreference(SharePreferenceConstance.POSITIONNALAT, String.valueOf(location.getLatitude()));
            SharePreferenceUtils.getInstance(context,SharePreferenceConstance.POSITIONNAME).CreateSharePreference(SharePreferenceConstance.POSITIONNALNG,String.valueOf(location.getLongitude()));
          /*  SurroungdingRetar surroungdingRetar = new SurroungdingRetar(context);
            surroungdingRetar.initSurroung(TBaplication.radarSearchManager,latitude,longitude);
            surroungdingRetar.getSurroundingInfo(TBaplication.radarSearchManager,latitude,longitude);*/

        }
    }

}
