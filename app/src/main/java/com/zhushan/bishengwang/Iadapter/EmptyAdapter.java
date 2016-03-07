package com.zhushan.bishengwang.Iadapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




import android.content.Context;
import android.widget.Adapter;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

import com.zhushan.bishengwang.R;

public class EmptyAdapter {

	private static List<Map<String,String>> list;
	public static ListAdapter getInstanceAdapter(Context context)
	{
		initData();
		SimpleAdapter Adapter = new SimpleAdapter(context, list, R.layout.emptylistview, new String[]{"empty"}, new int[]{R.id.emptyData});
		return Adapter;
		
	}
	private static void initData() {
		list = new ArrayList<Map<String,String>>();
		Map<String, String> map = new HashMap<String, String>();
		map.put("empty", "暂无数据");
		list.add(map);
		
	}
}
