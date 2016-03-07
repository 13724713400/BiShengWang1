package com.zhushan.bishengwang.Iview;
import android.app.Activity;
import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.zhushan.bishengwang.Itools.BackActivity;
import com.zhushan.bishengwang.Itools.CircleTransform;
import com.zhushan.bishengwang.Itools.GlideCircleTransform;
import com.zhushan.bishengwang.Itools.GlideRoundTransform;
import com.zhushan.bishengwang.R;

public class MainVu {
	private View Layoutview;	
	private SparseArray<View> views;
	private onclickChangeData changeData;
	public void setChangeData(onclickChangeData changeData) {
		this.changeData = changeData;
	}
	public MainVu()
	{
		views = new SparseArray<View>();
	}
	public void init(LayoutInflater inflater, ViewGroup group) {
		//Layoutview = inflater.inflate(R.layout.main_icon_layout, null);
	}
	public View initByLayout(Context context,int LayoutId) {
		Layoutview = LayoutInflater.from(context).inflate(LayoutId,null);

		return Layoutview;

	}

	public View getView() {

		return Layoutview;
	}
	public <T extends View> T getItemView(int id)
	{	
		View view = views.get(id);
		if(view==null)
		{
			view = Layoutview.findViewById(id);
			views.put(id, view);
		}
		return (T) view;
	}
	public void setIntentData(Object object)
	{

	}
	public MainVu setWidgetOnclistenner(int id,int PreessWidget)
	{
		View view = getItemView(id);
		view.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {			
				changeData.change();		
			}
		});
		return this;
	}
	public MainVu setViewVisible(int id,boolean isVisible)
	{
		View view = getItemView(id);
		if(isVisible)
		{
			view.setVisibility(View.VISIBLE);
		}else{
			view.setVisibility(View.GONE);
		}
		return this;
	}
	public void setListViewAdapter(int ListViewId,ListAdapter adapter)
	{
		ListView listView = getItemView(ListViewId);
		listView.setAdapter(adapter);

	}
	public void setNotifyDataChange(BaseAdapter adapter)
	{
		adapter.notifyDataSetChanged();
	}
	public void setActivityGoback(Activity activity,int id)
	{
		BackActivity.finishActivity(activity, id);

	}
	public MainVu setText(TextView textViewextView,CharSequence text)
	{
		textViewextView.setText(text);
		return this;
	}

	public MainVu setTextById(int id,CharSequence text)
	{
		((TextView)getItemView(id)).setText(text);
		return this;
	}
	public MainVu setImageUrl(ImageView imageView,Context context,String path)
	{
		Glide.with(context)
		.load(path)
		.into(imageView);
		return this;
	}

	public MainVu setImageUrlById(int id,Context context,String path)
	{
		if (path==null)
		{
			return null;
		}
		Glide.with(context)
				.load(path)
				.into((ImageView) getItemView(id));
		return this;
	}
	public MainVu setRoundImageUrlByGlideById(int id,Context context,String path,int pix)
	{
		Glide.with(context)
				.load(path)

				.transform(new GlideRoundTransform(context, pix)).into((ImageView) getItemView(id));
		return this;
	}

	public MainVu setCircleImageUrl(Context context,String path,ImageView imageView)
	{

	/*	Glide.with(context)
				.load(path)
				.placeholder(R.mipmap.logotwo)
				.transform(new GlideCircleTransform(context)).into(imageView);*/
		return this;
	}
	interface  onclickChangeData
	{
		void change();

	}
}
