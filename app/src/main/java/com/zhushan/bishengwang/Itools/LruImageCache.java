package com.zhushan.bishengwang.Itools;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.v4.util.LruCache;
import android.util.Log;

import com.android.volley.toolbox.ImageLoader.ImageCache;

public class LruImageCache implements ImageCache {

	private static LruCache<String, Bitmap> mMemoryCache;

	private static LruImageCache imageCache;

	private LruImageCache()
	{
		int maxmemory  = (int)Runtime.getRuntime().maxMemory();

		mMemoryCache = new LruCache<String, Bitmap>(maxmemory/8)
				{
			@Override
			protected int sizeOf(String key, Bitmap bitmap) {
				// TODO Auto-generated method stub
				return bitmap.getRowBytes()*bitmap.getHeight();
			}
			};
	}

	public static LruImageCache instance()
	{
		if(imageCache==null)
		{
			imageCache = new LruImageCache();
			
			
		}
		return imageCache;
	}

	@Override
	public Bitmap getBitmap(String arg0) {
		Log.i("www","从缓存哪数据");
		return mMemoryCache.get(arg0);
	}

	@Override
	public void putBitmap(String arg0, Bitmap arg1) {
		if(getBitmap(arg0)==null)
		{
			
			mMemoryCache.put(arg0, arg1);
		}

	}
	





}
