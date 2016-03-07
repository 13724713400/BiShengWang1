package com.zhushan.bishengwang.Itools;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.android.volley.Network;

/**
 * Created by Administrator on 2015/11/14.
 */
public class NetUtils {

    private NetUtils()
    {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static boolean isConnected(Context context)
    {
         ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if(null!=connectivityManager)
            {
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                if (networkInfo==null)
                {
                    return false;
                }

                if(networkInfo.getState()==NetworkInfo.State.CONNECTED)
                {

                    return true;

                }
            }

        return false;
    }
    public static int getAPNType(Context context) {
        int netType = -1;
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo == null) {
            return netType;
        }
        return 0;
    }

        public static boolean iswifi(Context context)
        {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

            if(connectivityManager==null)
            {
                return false;
            }
            return connectivityManager.getActiveNetworkInfo().getType()==ConnectivityManager.TYPE_WIFI;
        }

    /**
     * 打开网络设置界面
     * @param activity
     */
        public static void openSetting(Activity activity)
        {
            Intent intent = new Intent("/");
            ComponentName componentName = new ComponentName("com.android.settings",
                    "com.android.settings.WirelessSettings");
            intent.setComponent(componentName);
            intent.setAction("android.intent.action.VIEW");
            activity.startActivityForResult(intent,0);
        }
}
