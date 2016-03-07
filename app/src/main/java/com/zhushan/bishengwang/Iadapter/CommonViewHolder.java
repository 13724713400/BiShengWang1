package com.zhushan.bishengwang.Iadapter;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.zhushan.bishengwang.Itools.CircleTransform;
import com.zhushan.bishengwang.Itools.GlideCircleTransform;
import com.zhushan.bishengwang.Itools.GlideRoundTransform;
import com.zhushan.bishengwang.R;
import com.zhy.autolayout.utils.AutoUtils;


import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class CommonViewHolder {
	private View convertView;
	private SparseArray<View> views;
	public CommonViewHolder(Context context, ViewGroup parent, int resource) {
		this.views = new SparseArray<View>();
		this.convertView = LayoutInflater.from(context).inflate(resource, parent,false);
		this.convertView.setTag(this);
		AutoUtils.autoSize(convertView);
	}
	public static CommonViewHolder getInstance(Context context,View convertView,ViewGroup parent,int resource){
		if (convertView==null) {
			 return new CommonViewHolder(context,parent,resource);
		}else{
			return (CommonViewHolder) convertView.getTag();
		}
	}
	public View getConvertView() {
		return convertView;
	}
	/**
	 * viewholder.textView = convertView.findViewById(id);
	 */
	public <T extends View> T getView(int id){
		View view = views.get(id);
		if (view ==null) {
			view = convertView.findViewById(id);
			views.put(id, view);
		}
		return (T) view;
	}
	public CommonViewHolder setText(int id,CharSequence text){
		TextView textView = getView(id);
		textView.setText(text);
		return this;
	}
	public CommonViewHolder setImageUrl(Context context,String path,ImageView imageView)
	{
		Picasso.with(context)
		.load(path)
				.fit()
				.centerCrop()
		.into(imageView);
		return this;
	}

	/**
	 * 圆形图片
	 * @param context 如果传入activity 就会维护图片的生命周期
	 * @param path
	 * @param imageView
	 * @return
	 */
	public CommonViewHolder setCircleImageUrlByGlide(Context context,String path,ImageView imageView)
	{
		if (path==null)
		{
			return null;

		}
		Glide.with(context)
				.load(path)
				.transform(new GlideCircleTransform(context)).placeholder(R.mipmap.logotwo).into(imageView);
		return this;
	}

	/**
	 * 圆角图片
	 * @param context
	 * @param path
	 * @param imageView
	 * @return
	 */
	public CommonViewHolder setRoundImageUrlByGlide(Context context,String path,ImageView imageView)
	{
		Glide.with(context)
				.load(path)

				.transform(new GlideRoundTransform(context, 25)).into(imageView);
		return this;
	}

	public CommonViewHolder setImageUrlByGlide(Context context,String path,ImageView imageView)
	{
		if (path!=null)
		{
			Glide.with(context)
					.load(path)
					.placeholder(R.mipmap.logotwo)
					.into(imageView);
/*
			Picasso.with(context)
					.load(path)
					.fit()
					.centerCrop()
					.into(imageView);*/
		}

		return this;
	}

	public CommonViewHolder setImageIdByGlide(Context context,int path,ImageView imageView)
	{
		if (path==0)
		{
			Glide.with(context)
					.load(path)
					.into(imageView);
		}

		return this;
	}


	public CommonViewHolder setRoundImageViewImageUrl(Context context,String path,ImageView imageView)
	{
		Picasso.with(context)
		.load(path)
				.fit()
				.centerCrop()
				.transform(new CircleTransform(12))
		.into(imageView);
		return this;
	}


}
