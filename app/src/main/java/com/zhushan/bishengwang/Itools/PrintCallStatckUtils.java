package com.zhushan.bishengwang.Itools;

import android.app.ActivityManager;
import android.util.Log;

/**
 * Created by Administrator on 2015/12/26.
 */
public class PrintCallStatckUtils {

    private static PrintCallStatckUtils printCallStatckUtils;
    private PrintCallStatckUtils()
    {
    }

    public static PrintCallStatckUtils getInstance()
    {
        if (printCallStatckUtils==null)
        {
            synchronized (PrintCallStatckUtils.class)
            {
                if (printCallStatckUtils==null)
                {
                    printCallStatckUtils = new PrintCallStatckUtils();

                }

            }

        }

        return printCallStatckUtils;
    }

    public  void printCallStatck()
    {
        Throwable throwable = new Throwable();

        StackTraceElement[] stackTraceElements = throwable.getStackTrace();

        if (stackTraceElements!=null)
        {
            for (int i = 0 ;i<stackTraceElements.length;i++)
            {
                Log.i("222","类名："+stackTraceElements[i].getClassName()+"数量"+stackTraceElements[i].getLineNumber()+"文件名"+stackTraceElements[i].getFileName()+"方法名"+stackTraceElements[i].getMethodName());

            }

        }


    }

    public  void getMemoryInfo()
    {
        long SECONDARY_SERVER_MEM;
        long mMB = 1024*1024;
        ActivityManager manager = null;
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        long totalMemory =   Runtime.getRuntime().totalMemory()/mMB;
        long freeMemory =   Runtime.getRuntime().freeMemory()/mMB;
        long maxMemory =   Runtime.getRuntime().maxMemory() / mMB;
        Log.i("www","总的内存大小"+totalMemory+"可用的内存大小"+"最大内存大小"+maxMemory);

    }


}
