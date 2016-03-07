
package com.zhushan.bishengwang.Itools;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.baidu.mapapi.utils.DistanceUtil;
/*import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

import com.lidroid.xutils.http.client.HttpRequest;*/
import com.zhushan.bishengwang.Constance.HandlerConstance;
import com.zhushan.bishengwang.Constance.UserConstance;
import com.zhushan.bishengwang.Iaplication.TBaplication;

import de.greenrobot.event.EventBus;

public class VolleyUtils {
	private static VolleyUtils volleyUtils;
	private VolleyUtils(){}
	public static VolleyUtils getInsance()
	{
		if (volleyUtils==null)
		{
			volleyUtils = new VolleyUtils();
		}
		return volleyUtils;
	}

	/*public void getJsonfXutilsPost(String url,RequestParams requestParams)
	{
		HttpUtils httpUtils = new HttpUtils();
		httpUtils.send(HttpRequest.HttpMethod.POST, url, new RequestCallBack<String>() {
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				Log.i("www","responce"+responseInfo);
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				Log.i("www","error"+error);
			}
		});
	}
*/


	public  void getJsonFromHttp(final Context context, final String tag, String url) {
		if(!NetUtils.isConnected(context))
		{
			Toast.makeText(context,"网络异常",Toast.LENGTH_LONG).show();
			return;
		}
		RequestQueue requestQueue = TBaplication.requestQueue;
		Log.i("www", "地址===" + url);

		StringRequest stringRequest = new StringRequest(url, new Listener<String>() {
			@Override
			public void onResponse(String respString) {
				Log.i("www","respString"+respString);
				if (respString != null) {
					EventBus.getDefault().post(respString);
				}
			}
		}, new ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError arg0) {
				System.out.println("cuowu" + arg0.getMessage());
				Toast.makeText(context, "网络异常", Toast.LENGTH_LONG).show();
			}
		});
		stringRequest.setRetryPolicy(new DefaultRetryPolicy(HandlerConstance.CUD_SOCKET_TIMEOUT, HandlerConstance.MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
		requestQueue.add(stringRequest);
	}

	public  void getBitmapFromHttp(final Context context, final String tag, String url) {
		if(!NetUtils.isConnected(context))
		{
			Toast.makeText(context,"网络异常",Toast.LENGTH_LONG).show();
			return;
		}
		RequestQueue requestQueue = TBaplication.requestQueue;
		Log.i("www", "地址===" + url);

		ImageRequest imageRequest = new ImageRequest(url, new Listener<Bitmap>() {
			@Override
			public void onResponse(Bitmap response) {
				DisLruCacheUtils.getInstance().putValue(context, UserConstance.USERIMG,response);
			}
		},0,0,Config.ARGB_8888, new ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				Log.i("www","error"+error.getMessage());
			}
		});
		imageRequest.setRetryPolicy(new DefaultRetryPolicy(HandlerConstance.CUD_SOCKET_TIMEOUT, HandlerConstance.MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
		requestQueue.add(imageRequest);
	}

	public  void getJsonFromHttpPost(final EventBus eventBus,final Context context, String url, final HashMap<String, String> map,final String tag) {
		RequestQueue requestQueue = TBaplication.requestQueue;
		StringRequest stringRequest = new StringRequest(Method.POST, url, new Listener<String>() {
			@Override
			public void onResponse(String respString) {
				if (respString==null)
				{
					return;
				}
				EventBus.getDefault().post(respString);
			}
		}, new ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError arg0) {

				Toast.makeText(context, "网络异常", Toast.LENGTH_LONG).show();
			}
		}) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {

				System.out.println("参数" + map);
				return map;
			}
		};
		stringRequest.setRetryPolicy(new DefaultRetryPolicy(HandlerConstance.CUD_SOCKET_TIMEOUT, HandlerConstance.MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
		requestQueue.add(stringRequest);
	}


}
	


