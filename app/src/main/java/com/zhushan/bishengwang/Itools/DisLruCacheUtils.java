package com.zhushan.bishengwang.Itools;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

/**
 * Created by Administrator on 2015/12/7.
 */
public class DisLruCacheUtils {

    private static  DisLruCacheUtils disLruCacheUtils;
    private  DiskLruCacheHelper diskLruCacheHelper;
    public static final int BITMAP=1;
    public static final int STRING=2;
    public static final int BYTE=3;
    public static final int DRAWABLE=4;
    public static final int JSON=5;
    public static final int JSONARRAY=6;
    public static final int SEREABLE=7;
    public static DisLruCacheUtils getInstance()
    {
        if (disLruCacheUtils==null)
        {
            disLruCacheUtils = new DisLruCacheUtils();

        }

        return disLruCacheUtils;
    }


    public Object getValue(Context context,String fielnamename,int typaValue)
    {
        try {
            DiskLruCacheHelper  diskLruCacheHelper = new DiskLruCacheHelper(context);
            if(typaValue==BITMAP)
            {
                return diskLruCacheHelper.getAsBitmap(fielnamename);
            }else if(typaValue==STRING)
            {
                return diskLruCacheHelper.getAsString(fielnamename);
            }else if (typaValue==BYTE)
            {
                return diskLruCacheHelper.getAsBytes(fielnamename);
            }

            else if (typaValue==DRAWABLE)
            {
                return diskLruCacheHelper.getAsDrawable(fielnamename);
            }

            else if (typaValue==JSON)
            {
                return diskLruCacheHelper.getAsJson(fielnamename);
            }

            else if (typaValue==JSONARRAY)
            {
                return diskLruCacheHelper.getAsJSONArray(fielnamename);
            }

            else if (typaValue==SEREABLE)
            {
                return diskLruCacheHelper.getAsSerializable(fielnamename);
            }
           // return diskLruCacheHelper.getAsBitmap(fielnamename);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteAll(Context context)
    {
        try {
            DiskLruCacheHelper  diskLruCacheHelper = new DiskLruCacheHelper(context);

            diskLruCacheHelper.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File getDirectory(Context context)
    {
        try {
            DiskLruCacheHelper  diskLruCacheHelper = new DiskLruCacheHelper(context);
            return diskLruCacheHelper.getDirectory();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void flush(Context context)
    {
        try {
            DiskLruCacheHelper  diskLruCacheHelper = new DiskLruCacheHelper(context);
            diskLruCacheHelper.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Boolean removeByKey(Context context,String key)
    {
        try {
            DiskLruCacheHelper  diskLruCacheHelper = new DiskLruCacheHelper(context);
            return diskLruCacheHelper.remove(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void putValue(Context context,String fielnamename,Object object)
    {
        try {
            DiskLruCacheHelper  diskLruCacheHelper = new DiskLruCacheHelper(context);
            if (object instanceof Bitmap) {
                diskLruCacheHelper.put(fielnamename, (Bitmap) object);
                Log.i("www", "tupian" + diskLruCacheHelper.getAsBitmap(fielnamename));
            }else if (object instanceof String)
            {
                diskLruCacheHelper.put(fielnamename,(String)object);

            }else if (object instanceof Drawable)
            {
                diskLruCacheHelper.put(fielnamename,(Drawable)object);
            }else if(object instanceof byte[])
            {
                diskLruCacheHelper.put(fielnamename,(byte[])object);
            }else if (object instanceof JSONArray)
            {
                diskLruCacheHelper.put(fielnamename,(JSONArray)object);
            }else if (object instanceof JSONObject)
            {
                diskLruCacheHelper.put(fielnamename,(JSONObject)object);
            }else if (object instanceof Serializable)
            {
                diskLruCacheHelper.put(fielnamename,(Serializable)object);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
