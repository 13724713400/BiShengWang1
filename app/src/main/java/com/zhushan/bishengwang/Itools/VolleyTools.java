package com.zhushan.bishengwang.Itools;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhushan.bishengwang.Iaplication.TBaplication;


import org.apache.http.protocol.HTTP;
import org.xutils.common.Callback;
import org.xutils.ex.HttpException;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.Map;

import de.greenrobot.event.EventBus;

public class VolleyTools<T> {
	private int what;
	private Class entry2;

	public VolleyTools(Class entry,int what)
	{
		this.entry2 = entry;
		this.what  = what;

	}
	private Handler handler = new Handler()
	{
		public void handleMessage(Message msg)
		{
			if(what==msg.what)
			{
				String responce = (String) msg.obj;
				Gson gson = new Gson();
				Log.i("respString", " " + parserTFromJson(responce, entry2));
			//	EventBus.getDefault().post(parserTFromJson(responce, entry2));
			}
		};

	};

	public  T parserTFromJson(String pJsonStr, Class pClass){
		T _T;
		try{
			if (!TextUtils.isEmpty(pJsonStr)){
				Gson _Gson = new Gson();
				_T = (T) _Gson.fromJson(pJsonStr, pClass);
			}else {
				_T = null;
			}
		}catch (Exception e){
			e.printStackTrace();
			_T = null;
		}
		return _T;
	}
	public void getJsonFromHttpPost(final Context context,String url, final Map map)
	{
		if(NetUtils.isConnected(context))
		{
		}else{
			Toast.makeText(context,"亲！网络断了哦，请检查网络设置",Toast.LENGTH_LONG);
			return;
		}
		final RequestQueue requestQueue = TBaplication.requestQueue;
		final StringRequest stringRequest = new StringRequest(url, new Listener<String>() {
			@Override
			public void onResponse(String respString) {
				if(respString!=null)
				{
					Log.i("respString",respString);
					Message message  = handler.obtainMessage();
					message.what = what;
					message.obj =  respString;
					handler.sendMessage(message);
				}
			}
		}, new ErrorListener() {		
			@Override
			public void onErrorResponse(VolleyError arg0) {
				System.out.println("错误的信息"+arg0.getMessage());
				Toast.makeText(context, "无法获取数据", Toast.LENGTH_LONG).show();
			}
		}){
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				return map;
			}
		};
		stringRequest.setRetryPolicy(new DefaultRetryPolicy(5000, 1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
		requestQueue.add(stringRequest);
	}


	//下载图片
	public void getJsonfXutilsPost(String url,RequestParams requestParams)
	{
		//


		// 有上传文件时使用multipart表单, 否则上传原始文件流.
// params.setMultipart(true);
// 上传文件方式 1
// params.uploadFile = new File("/sdcard/test.txt");
// 上传文件方式 2
// params.addBodyParameter("uploadFile", new File("/sdcard/test.txt"));


		Callback.Cancelable cancelable = x.http().post(requestParams, new Callback.CacheCallback<String>() {

			@Override
			public void onSuccess(String result) {
				Log.i("www", "上传成功=============================" + result);
				Message message = handler.obtainMessage();
				message.what = what;
				message.obj = result;
				handler.sendMessage(message);
			}

			@Override
			public void onFinished() {

				Log.i("www", "完成");
			}

			@Override
			public void onError(Throwable ex, boolean isOnCallback) {
				//网络错误
				Toast.makeText(x.app(), ex.getMessage(), Toast.LENGTH_LONG).show();
				Log.i("www", ex.getMessage());
				if (ex instanceof HttpException) {
					HttpException httpEx = (HttpException) ex;
					int responseCode = httpEx.getCode();
					String responseMsg = httpEx.getMessage();
					String errorResult = httpEx.getResult();

					Log.i("www", "responseMsg" + responseMsg + "errorResult" + errorResult);
				}

				Toast.makeText(x.app(), "错误" + ex.getMessage(), Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onCancelled(Callback.CancelledException cex) {
				Log.i("www", "取消" + cex.getMessage());
			}


			public boolean onCache(String result) {
				Toast.makeText(x.app(), "取消", Toast.LENGTH_SHORT).show();
				return false;
			}
		});




	}



}
