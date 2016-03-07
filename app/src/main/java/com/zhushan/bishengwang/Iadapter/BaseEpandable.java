package com.zhushan.bishengwang.Iadapter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.content.Context;
import android.text.method.CharacterPickerDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.zhushan.bishengwang.Entry.DirectorSettingData;
import com.zhushan.bishengwang.Entry.DirectorSettingEntry;
import com.zhushan.bishengwang.R;
import com.zhy.autolayout.utils.AutoUtils;

public class BaseEpandable extends BaseExpandableListAdapter {
	private List<DirectorSettingData> Chiledlistnews;
	private Context context;
	public BaseEpandable(List<DirectorSettingData> listnews,Context context)
	{
		this.Chiledlistnews = listnews;
		this.context=context;
	}
	@Override
	public Object getChild(int groupPosition, int childPosition) {
		
		return Chiledlistnews.get(groupPosition).getLabel_list().get(childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		
		return childPosition;
	}

	@Override
	public View getChildView( int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		if(convertView==null)
		{
		    convertView=LayoutInflater.from(context).inflate(R.layout.director_setting_chiled, parent,false);
			AutoUtils.autoSize(convertView);
		}
		     GridView gridview= (GridView) convertView.findViewById(R.id.director_setting_chiled_gv);
		gridview.setAdapter(new Director_setting_ItemAdapter(context, Chiledlistnews.get(groupPosition).getLabel_list(), R.layout.director_setting_chiled_item));
			return convertView;
	}
	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return Chiledlistnews.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return Chiledlistnews.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		if(convertView==null)
		{
		convertView=LayoutInflater.from(context).inflate(R.layout.director_setting_group,parent,false);
			AutoUtils.autoSize(convertView);
		}
		TextView textView=(TextView) convertView.findViewById(R.id.director_setting_group_tt);
		ImageView director_setting_group_img = (ImageView) convertView.findViewById(R.id.director_setting_group_img);
		textView.setText(Chiledlistnews.get(groupPosition).getCate_name());
		if(isExpanded)
		{
			director_setting_group_img.setImageResource(R.mipmap.btn_arrow_up);
		}else{
			director_setting_group_img.setImageResource(R.mipmap.btn_arrow_downtwo);
		}


		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
	     
		return true;
	}

}
