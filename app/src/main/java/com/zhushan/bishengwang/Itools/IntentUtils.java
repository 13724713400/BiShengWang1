package com.zhushan.bishengwang.Itools;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2015/11/18.
 */
public class IntentUtils {
    private IntentUtils()
    {
    }
    private static IntentUtils intentUtils;
    public static IntentUtils getInstance()
    {
        if (intentUtils==null)
        {
            intentUtils = new IntentUtils();
        }
        return  intentUtils;
    }
    public  void startToAnoterActivity(Context NaoActivity,Class<?> cl,Bundle bundle)
    {
        Intent intent = new Intent(NaoActivity,cl);
        if(bundle!=null)
        {
            intent.putExtras(bundle);
        }
        NaoActivity.startActivity(intent);

    }
    public  void startActivityForResult(Activity NaoActivity,Class<?> cl,Bundle bundle,int ResultCode)
    {
        Intent intent = new Intent();
        if(bundle!=null)
        {
            intent.putExtras(bundle);
            //NaoActivity.startActivityForResult(intent, ResultCode, bundle);
            NaoActivity.finish();
        }else {
            NaoActivity.startActivityForResult(intent, ResultCode);
            NaoActivity.finish();
        }
    }

    public static boolean isIntentAvailable(Context context, String action)
    {      final PackageManager packageManager = context.getPackageManager();
        final Intent intent = new Intent(action);
        List<ResolveInfo> list = packageManager.queryIntentActivities(intent,             PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }
}
