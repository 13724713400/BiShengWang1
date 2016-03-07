package com.zhushan.bishengwang.Itools;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.util.Pair;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.Request;

import com.zhushan.bishengwang.Constance.SharePreferenceConstance;
import com.zhushan.bishengwang.Entry.AddressChangeEntry;
import com.zhushan.bishengwang.Iaplication.TBaplication;
import com.zhy.autolayout.utils.AutoUtils;
import com.zhy.http.okhttp.OkHttpClientManager;
import com.zhy.http.okhttp.callback.ResultCallback;
import com.zhy.http.okhttp.request.OkHttpRequest;

import org.json.JSONException;
import org.json.JSONObject;

import de.greenrobot.event.EventBus;


/**
 * Created by Administrator on 2015/12/14.
 */
public  class  OkHttpUtils {

    private static OkHttpUtils okHttpUtils;
    public static final int ADREESSCHANGE=1;
    public static final int ADREESS=2;
    public static final int OTHER=3;
    private OkHttpUtils(){}
    public static OkHttpUtils getInstance()
    {
        if (okHttpUtils==null) {
            synchronized (OkHttpUtils.class) {
                if (okHttpUtils == null) {
                    okHttpUtils = new OkHttpUtils();

                }
            }
        }
        return okHttpUtils;
    }
    private ResultCallback<String> stringResultCallback = new MyResultCallback<String>()//
    {
        @Override
        public void onError(Request request, Exception e)
        {

            Log.e("TAG", "onError , e = " + e.getMessage());
        }
        @Override
        public void onResponse(String response)
        {
             Log.e("TAG", "onResponse , response = " + response);

        }
        @Override
        public void inProgress(float progress)
        {
           /* mProgressBar.setProgress((int) (100 * progress));*/
        }
    };
    public abstract class MyResultCallback<T> extends ResultCallback<T>
    {


        @Override
        public void onBefore(Request request)
        {
            super.onBefore(request);

        }

        @Override
        public void onAfter()
        {
            super.onAfter();

        }
    }
    /**
     * get的方法
     * @param context
     * @param
     * @param url
     */
    private void getFromHttp(final Activity context,String url, final int requestype)
    {

        if (!NetUtils.isConnected(context))
        {
            Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
            return;
        }
        new OkHttpRequest.Builder()
                .url(url)
                .get(new MyResultCallback<String>() {
                    @Override
                    public void onError(Request request, Exception e) {

                        Log.i("www","错误信息"+request.url());
                    }
                    @Override
                    public void onResponse(String response) {
                        Log.i("www", "test" + response);
                        AddressChangeEntry addressChangeEntry = null;
                        if (requestype==ADREESSCHANGE) {
                            String responce1 = response.replace("showLocation&&showLocation(", "");
                            String responce2 = responce1.replace(")", "");
                            Gson gson = new Gson();
                             addressChangeEntry = gson.fromJson(responce2, new TypeToken<AddressChangeEntry>() {
                            }.getType());
                            SharePreferenceUtils.getInstance(context, SharePreferenceConstance.POSITIONNAME).CreateSharePreference(SharePreferenceConstance.POSITIONNALAT,String.valueOf(addressChangeEntry.getResult().getLocation().getLat()));
                            SharePreferenceUtils.getInstance(context, SharePreferenceConstance.POSITIONNAME).CreateSharePreference(SharePreferenceConstance.POSITIONNALNG,String.valueOf(addressChangeEntry.getResult().getLocation().getLng()));
                          //  Log.i("www", "addressChangeEntry" + addressChangeEntry.getResult().getLocation().getLng() + "   " + addressChangeEntry.getResult().getLocation().getLat());
                        }

                        if (requestype==ADREESS)
                        {

                            String responce2 = response.replace("renderReverse&&renderReverse", "").replace(")", "").replace("(", "");
                            JSONObject jsonObject = null;
                            try {
                                jsonObject = new JSONObject(responce2);
                                JSONObject object=jsonObject.getJSONObject("result");
                                String name = object.getString("formatted_address");
                                JSONObject text = object.getJSONObject("addressComponent");
                                String city = text.getString("city");
                                String province = text.getString("province");
                                SharePreferenceUtils.getInstance(context, SharePreferenceConstance.POSITIONNAME).CreateSharePreference(SharePreferenceConstance.POSITIONPROVICE,province);
                                SharePreferenceUtils.getInstance(context, SharePreferenceConstance.POSITIONNAME).CreateSharePreference(SharePreferenceConstance.POSITIONNADREESS,name);
                                SharePreferenceUtils.getInstance(context, SharePreferenceConstance.POSITIONNAME).CreateSharePreference(SharePreferenceConstance.POSITIONCITY,city);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }



                        }

                      // EventBus.getDefault().post(addressChangeEntry);
                    }
                });
    }


    private void getFromHttp(final Activity context,String url, ResultCallback resultCallback) {

        if (!NetUtils.isConnected(context)) {
            Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
            return;
        }
        new OkHttpRequest.Builder()
                .url(url)
                .get(resultCallback);

    }

    private void getFromHttpCallback(Context context,String url,ResultCallback resultCallback)
    {

        if (!NetUtils.isConnected(context))
        {
            Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
            return;
        }
        new OkHttpRequest.Builder()
                .url(url)
                .get(resultCallback);

    }
    private void postFromHttp(Context context,Map<String, String> params,String url,ResultCallback resultCallback)
    {
        if (!NetUtils.isConnected(context))
        {
            Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
            return;
        }
    Log.i("www","resultCallback"+resultCallback);
        Log.i("www","请求参数"+params);
        new OkHttpRequest.Builder()
                .url(url)
                .params(params)
                .post(resultCallback);
    }
    private void multiFileUpload(Context context, Map<String, String> params, String url, ResultCallback resultCallback, Pair...p)
    {
        Log.i("www","jinlai2");
     if (!NetUtils.isConnected(context))
        {
            Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
            return;
        }

        Log.i("www","请求参数=========================="+params);

                new OkHttpRequest.Builder()
                        .url(url)
                        .files(p)
                        .params(params)
                        .upload(resultCallback);



    }

    /**
     * 文件下载
     * @param context
     * @param url
     * @param desdir
     * @param filename
     */
    private void downloadFile(Context context,String url,String desdir,String filename)
    {
        if (!NetUtils.isConnected(context))
        {
        Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
        return;
     }
    new OkHttpRequest.Builder()
            .url(url)
            .destFileDir(desdir)
            .destFileName(filename)
            .download(stringResultCallback);
    }

    public static void getAsynCallback(Context context,String url,ResultCallback resultCallback)
    {
        getInstance().getFromHttpCallback(context, url, resultCallback);
    }

    public static  void  postAsyn(Context context,Map<String, String> params,String url,ResultCallback resultCallback)
    {
        getInstance().postFromHttp(context, params, url, resultCallback);

    }
    public static  void getAsyn(Activity context,String url,int requstype)
    {
        getInstance().getFromHttp(context, url, requstype);
    }

    public static  void getAsyn(Activity context,String url,ResultCallback resultCallback)
    {
        getInstance().getFromHttp(context, url,resultCallback);
    }

    public static void downloadAsyn(Context context,String url,String desdir,String filename)
    {
        getInstance().downloadFile(context,url,desdir,filename);
    }
    public static void multiFileUploadAsyn(Context context,Map<String, String> params,String url,ResultCallback resultCallback,Pair...p)
    {
        Log.i("www","jinlai1");
        getInstance().multiFileUpload(context,params,url,resultCallback,p);
    }


}
