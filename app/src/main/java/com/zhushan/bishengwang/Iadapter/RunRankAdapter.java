package com.zhushan.bishengwang.Iadapter;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.zhushan.bishengwang.Constance.HttpConstance;
import com.zhushan.bishengwang.Constance.SharePreferenceConstance;
import com.zhushan.bishengwang.Entry.Banner;
import com.zhushan.bishengwang.Entry.DataList;
import com.zhushan.bishengwang.Entry.TypographerData;
import com.zhushan.bishengwang.Itools.CallPhoneUtils;
import com.zhushan.bishengwang.Itools.IntentUtils;
import com.zhushan.bishengwang.Itools.PositionMySeft;
import com.zhushan.bishengwang.Itools.SharePreferenceUtils;
import com.zhushan.bishengwang.R;
import com.zhushan.bishengwang.activity.Activity_printingPeapleDetails;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

public  class RunRankAdapter extends BaseAdapter {
	 Context mContext;
	 LinearLayout linearLayout = null;
	 LayoutInflater inflater;
	 TextView tex;
	final int TYPE_1 = 0;
	final int TYPE_2 = 1;
	private List<DataList> dataList2;
	private Banner banner;
	private double lat,lng;
	private Context context;
	public RunRankAdapter(Context context,
							  Banner banner,List<DataList> dataList2) {
			this.banner = banner;
		this.context = context;
			this.mContext = context;
	    	this.dataList2 = dataList2;
		lat =  Double.parseDouble(SharePreferenceUtils.getInstance(context, SharePreferenceConstance.POSITIONNAME).getSharePreferenceString(SharePreferenceConstance.POSITIONNALAT,null));
		lng =  Double.parseDouble(SharePreferenceUtils.getInstance(context, SharePreferenceConstance.POSITIONNAME).getSharePreferenceString(SharePreferenceConstance.POSITIONNALNG,null));
		}

	@Override
	public int getCount() {
		return (dataList2.size());
	}

	@Override
	public Object getItem(int position) {
		return dataList2.get(position);
	}
	@Override
	public long getItemId(int position) {
		return position;
	}

	// 每个convert view都会调用此方法，获得当前所需要的view样式
	@Override
	public int getItemViewType(int position) {
		int p = position;
		if (dataList2.get(position).isCheck())
			return TYPE_2;
		else
			return TYPE_1;
	}

	@Override
	public int getViewTypeCount() {
		return 2;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		 ViewHolder1 holder1 = null;
		ViewHolder2 viewHolder2 = null;
		int type = getItemViewType(position);
		if (convertView == null) {
			inflater = LayoutInflater.from(mContext);
			// 按当前所需的样式，确定new的布局
			switch (type) {
				case TYPE_1:
					 holder1 = new ViewHolder1();
					convertView  = inflater.inflate(R.layout.typographer_listview_item,parent,false);
					AutoUtils.autoSize(convertView);
					holder1.head = (ImageView) convertView.findViewById(R.id.typographer_item_touxiang);
					 holder1.call  = (ImageView) convertView.findViewById(R.id.typographer_item_call);
					holder1.titles = (TextView) convertView.findViewById(R.id.typographer_item_Name);
					holder1.distance= (TextView) convertView.findViewById(R.id.typographer_item_distance);
					holder1.address = (TextView) convertView.findViewById(R.id.typographer_item_address);
					holder1.text1 = (TextView) convertView.findViewById(R.id.typographer_item_text1);
					holder1.textt2 = (TextView) convertView.findViewById(R.id.typographer_item_text2);
					holder1.tText3 = (TextView) convertView.findViewById(R.id.typographer_item_text3);
					convertView.setTag(holder1);
					break;
				case TYPE_2:
					viewHolder2 = new ViewHolder2();
					convertView = inflater.inflate(R.layout.typographer_listview_itemlogo,parent,false);
					AutoUtils.autoSize(convertView);
					viewHolder2.logo  = (ImageView) convertView.findViewById(R.id.typographer_item_logo);
					convertView.setTag(viewHolder2);
					break;

			}
			}else{
				switch (type) {
					case TYPE_1:
						holder1 = (ViewHolder1) convertView.getTag();
						break;
					case TYPE_2:
						viewHolder2 = (ViewHolder2) convertView.getTag();
						break;

				}
			}
			// 设置资源  
			switch (type) {
				case TYPE_1:
					if(!dataList2.get(position).getLat().equals("")) {
						holder1.distance.setText(String.valueOf(PositionMySeft.getDistance(lat, lng, Double.parseDouble(dataList2.get(position).getLat()), Double.parseDouble(dataList2.get(position).getLng()))) + "km");
					}
					if (!dataList2.get(position).getImg_thumb().equals("")) {
						holder1.head.setScaleType(ImageView.ScaleType.FIT_XY);
						Glide.with(mContext)
								.load(HttpConstance.URL + dataList2.get(position).getImg_thumb())
								.into(holder1.head);
					}
					holder1.titles.setText(dataList2.get(position).getCompany_name());
					if (dataList2.get(position).getMark().toString().equals(""))
					{
						holder1.text1.setVisibility(View.GONE);
						holder1.textt2.setVisibility(View.GONE);
						holder1.tText3.setVisibility(View.GONE);

					}
					boolean isContaint = dataList2.get(position).getMark().toString().contains(",");
					boolean isEmpty = dataList2.get(position).getMark().toString().equals("");
					if (!isContaint&&!isEmpty)
					{
						holder1.text1.setVisibility(View.VISIBLE);
						holder1.text1.setText(dataList2.get(position).getMark().toString());
					}
					if (isContaint) {
						String[] type2 = dataList2.get(position).getMark().toString().split(",");
						for (int i = 0; i < type2.length; i++) {
							switch (i) {
								case 0:
									holder1.text1.setVisibility(View.VISIBLE);
									holder1.text1.setText(type2[0]);
									break;
								case 1:
									holder1.textt2.setVisibility(View.VISIBLE);
									holder1.text1.setVisibility(View.VISIBLE);
									holder1.text1.setText(type2[0]);
									holder1.textt2.setText(type2[1]);
									break;
								case 2:
									holder1.tText3.setVisibility(View.VISIBLE);
									holder1.textt2.setVisibility(View.VISIBLE);
									holder1.text1.setVisibility(View.VISIBLE);
									holder1.text1.setText(type2[0]);
									holder1.textt2.setText(type2[1]);
									holder1.tText3.setText(type2[2]);
									break;

							}
						}
					}
					if (dataList2.get(position).isCall())
					{
						holder1.call .setImageResource(R.mipmap.icon_phone_on1);
					}else
					{
						holder1.call .setImageResource(R.mipmap.icon_phone_off);
					}

					holder1.call .setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							((ImageView)v).setImageResource(R.mipmap.icon_phone_on2);
							CallPhoneUtils.getInstance().callTwo(dataList2.get(position).getCompany_telephone(), context, ((ImageView)v), dataList2, position);
						}
					});
					holder1.address.setText(dataList2.get(position).getCompany_area());
					convertView.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							Bundle bundle = new Bundle();
							bundle.putString("user_id", dataList2.get(position).getUser_id());
							bundle.putString("flag","2");
							IntentUtils.getInstance().startToAnoterActivity(mContext, Activity_printingPeapleDetails.class, bundle);
						}
					});

					convertView.setBackgroundResource(R.drawable.bg);
					break;
				case TYPE_2:
					Glide.with(mContext)
						.load(HttpConstance.URL + banner.getImg())
							.into(viewHolder2.logo);
					break;

			}

			return convertView;
		}

	}
	class ViewHolder1 {

		ImageView head;
		 ImageView call;
		TextView titles;
		TextView text1;
		TextView textt2;
		TextView tText3;
		TextView address;
		TextView distance;


	}

class ViewHolder2 {

	ImageView logo;



}

