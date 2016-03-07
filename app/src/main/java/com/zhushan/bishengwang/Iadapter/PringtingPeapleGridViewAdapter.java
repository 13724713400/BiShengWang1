package com.zhushan.bishengwang.Iadapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhushan.bishengwang.Constance.HttpConstance;
import com.zhushan.bishengwang.Constance.SharePreferenceConstance;
import com.zhushan.bishengwang.Entry.PrintingPeapleData;
import com.zhushan.bishengwang.Itools.IntentUtils;
import com.zhushan.bishengwang.Itools.PositionMySeft;
import com.zhushan.bishengwang.Itools.SharePreferenceUtils;
import com.zhushan.bishengwang.R;
import com.zhushan.bishengwang.activity.Activity_printingPeapleDetails;

import java.util.List;

/**
 * Created by Administrator on 2015/12/17.
 */
public class PringtingPeapleGridViewAdapter extends CommonBaseAdapter<PrintingPeapleData>{
    private double lat,lng;
    public PringtingPeapleGridViewAdapter(Activity context, List datas, int resourceId) {
        super(context, datas, resourceId);
        lat =  Double.parseDouble(SharePreferenceUtils.getInstance(context, SharePreferenceConstance.POSITIONNAME).getSharePreferenceString(SharePreferenceConstance.POSITIONNALAT,null));
        lng =  Double.parseDouble(SharePreferenceUtils.getInstance(context, SharePreferenceConstance.POSITIONNAME).getSharePreferenceString(SharePreferenceConstance.POSITIONNALNG,null));

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        CommonViewHolder commonViewHolder = CommonViewHolder.getInstance(context,convertView,parent,resourceId);
       ImageView pringtingpeaple_gridview_item_touxiang =  commonViewHolder.getView(R.id.pringtingpeaple_gridview_item_touxiang);
       TextView pringtingpeaple_gridview_item_Name =  commonViewHolder.getView(R.id.pringtingpeaple_gridview_item_Name);
       ImageView pringtingpeaple_gridview_item_sex =  commonViewHolder.getView(R.id.pringtingpeaple_gridview_item_sex);
       TextView pringtingpeaple_gridview_item_distance =  commonViewHolder.getView(R.id.pringtingpeaple_gridview_item_distance);
       TextView pringtingpeaple_gridview_item_zhiwei =  commonViewHolder.getView(R.id.pringtingpeaple_gridview_item_zhiwei);
        if (datas.get(position).getHead_thumb().contains("Uploads")) {
            commonViewHolder.setCircleImageUrlByGlide(context, HttpConstance.URL + datas.get(position).getHead_thumb(), pringtingpeaple_gridview_item_touxiang);
        }else{
            commonViewHolder.setCircleImageUrlByGlide(context, datas.get(position).getHead_thumb(), pringtingpeaple_gridview_item_touxiang);
        }
        commonViewHolder.setText(R.id.pringtingpeaple_gridview_item_Name,datas.get(position).getNickname())
                .setText(R.id.pringtingpeaple_gridview_item_zhiwei,datas.get(position).getRule());
                if(!datas.get(position).getLat().equals("")) {
                    commonViewHolder .
                    setText(R.id.pringtingpeaple_gridview_item_distance, String.valueOf(PositionMySeft.getDistance(lat, lng, Double.parseDouble(datas.get(position).getLat()), Double.parseDouble(datas.get(position).getLng()))) + " km");
                }
        switch (datas.get(position).getSex())
        {
            case "男":
                pringtingpeaple_gridview_item_sex.setImageResource(R.mipmap.icon_male);
                break;

            case "女":
                pringtingpeaple_gridview_item_sex.setImageResource(R.mipmap.icon_female);
                break;
        }
        commonViewHolder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("user_id",datas.get(position).getUser_id());
                bundle.putString("flag","1");
                IntentUtils.getInstance().startToAnoterActivity(context, Activity_printingPeapleDetails.class,bundle);
            }
        });
        commonViewHolder.getConvertView().setBackgroundResource(R.drawable.bg);
        return commonViewHolder.getConvertView();
    }
}
