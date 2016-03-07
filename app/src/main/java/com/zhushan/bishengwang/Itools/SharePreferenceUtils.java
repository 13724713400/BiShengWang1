package com.zhushan.bishengwang.Itools;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


import android.R.integer;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;

public class SharePreferenceUtils {

	private static SharePreferenceUtils preferenceUtils;
	private SharePreferenceUtils(){};
	private static SharedPreferences sharedPreferences;
	public static SharePreferenceUtils getInstance(Context activity,String name)
	{
		if(preferenceUtils==null)
		{
			preferenceUtils = new SharePreferenceUtils();
			sharedPreferences = activity.getSharedPreferences(name, Context.MODE_PRIVATE);
		}
		return preferenceUtils;

	}

	public Editor CreateSharePreference(String key,Object value)
	{

		Editor editor = sharedPreferences.edit();

		if(value instanceof String)
		{
			editor.putString(key, (String)value);
		}

		if(value instanceof Integer)
		{

			editor.putInt(key, (Integer)value);
		}

		if(value instanceof Boolean)
		{
			editor.putBoolean(key, (Boolean)value);
		}

		if(value instanceof Float)
		{

			editor.putFloat(key, (Float)value);
		}

		if(value instanceof Long)
		{

			editor.putLong(key, (Long)value);

		}

		editor.commit();
		return editor;

	}
	
	public String getSharePreferenceString(String key,String defValue)
	{
		return sharedPreferences.getString(key, defValue);
	}
	
	public Integer getSharePreferenceInt(String key,int defValue)
	{
		return sharedPreferences.getInt(key, defValue);
		
	}
	
	public boolean getSharePreferenceBoolean(String key,boolean defValue)
	{
		return sharedPreferences.getBoolean(key, defValue);
		
	}
	
	public void getAllData()
	{
		HashMap map =  (HashMap) sharedPreferences.getAll();
		Iterator it = map.entrySet().iterator();
		while(it.hasNext())
		{
		Entry entry = 	(Entry) it.next();
	    Object o = entry.getValue();
		}
	}
	public boolean isContainData(String key)
	{
		return sharedPreferences.contains(key);	
	}
	public Editor deleteData(String key)
	{
		Editor editor = sharedPreferences.edit();
		editor.remove(key);
		editor.commit();
		return editor;
	}
	public void deleteAllData()
	{
		sharedPreferences.edit().clear().commit();
	}
}
