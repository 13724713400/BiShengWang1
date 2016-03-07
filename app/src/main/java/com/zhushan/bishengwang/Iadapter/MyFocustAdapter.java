package com.zhushan.bishengwang.Iadapter;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhushan.bishengwang.Constance.HttpConstance;
import com.zhushan.bishengwang.Constance.SharePreferenceConstance;
import com.zhushan.bishengwang.Entry.MyFocusTypographerData;
import com.zhushan.bishengwang.Ifactory.HttpFactory;
import com.zhushan.bishengwang.Itools.CallPhoneUtils;
import com.zhushan.bishengwang.Itools.IntentUtils;
import com.zhushan.bishengwang.Itools.PositionMySeft;
import com.zhushan.bishengwang.Itools.SharePreferenceUtils;
import com.zhushan.bishengwang.R;
import com.zhushan.bishengwang.activity.Activity_Login;
import com.zhushan.bishengwang.activity.Activity_printingPeapleDetails;

import java.util.HashMap;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2016/1/15.
 */
public class MyFocustAdapter extends CommonBaseAdapter<MyFocusTypographerData> {
    private double lat,lng;
    public MyFocustAdapter(Context context, List<MyFocusTypographerData> datas, int resourceId) {
        super(context, datas, resourceId);
        lat =  Double.parseDouble(SharePreferenceUtils.getInstance(context, SharePreferenceConstance.POSITIONNAME).getSharePreferenceString(SharePreferenceConstance.POSITIONNALAT,null));
        lng =  Double.parseDouble(SharePreferenceUtils.getInstance(context, SharePreferenceConstance.POSITIONNAME).getSharePreferenceString(SharePreferenceConstance.POSITIONNALNG,null));
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        CommonViewHolder commonViewHolder = CommonViewHolder.getInstance(context,convertView,parent,resourceId);
       ImageView typographer_item_touxiang =  commonViewHolder.getView(R.id.typographer_item_touxiang);
        TextView typographer_item_text1 = commonViewHolder.getView(R.id.typographer_item_text1);
        TextView typographer_item_text2 = commonViewHolder.getView(R.id.typographer_item_text2);
        TextView typographer_item_text3 = commonViewHolder.getView(R.id.typographer_item_text3);
      final ImageView typographer_item_call =  commonViewHolder.getView(R.id.typographer_item_call);
        commonViewHolder.setImageUrlByGlide(context, HttpConstance.URL+datas.get(position).getImg_thumb(),typographer_item_touxiang);
        commonViewHolder.setText(R.id.typographer_item_address, datas.get(position).getCompany_area());


        if(!datas.get(position).getLat().equals("")&&!datas.get(position).getLng().equals("")) {

            commonViewHolder.setText(R.id.typographer_item_distance, String.valueOf(PositionMySeft.getDistance(lat, lng, Double.parseDouble(datas.get(position).getLat()), Double.parseDouble(datas.get(position).getLng()))) + "km");
        }

        boolean isContaint = datas.get(position).getMark().toString().contains(",");
        boolean isEmpty = datas.get(position).getMark().toString().equals("");
        if (!isContaint&&!isEmpty)
        {
            typographer_item_text1.setVisibility(View.VISIBLE);
            typographer_item_text1.setText(datas.get(position).getMark().toString());
        }
        if (isContaint) {
            String[] type2 = datas.get(position).getMark().toString().split(",");
            for (int i = 0; i < type2.length; i++) {
                switch (i) {
                    case 0:
                        typographer_item_text1.setVisibility(View.VISIBLE);
                        typographer_item_text1.setText(type2[0]);
                        break;
                    case 1:
                        typographer_item_text2.setVisibility(View.VISIBLE);
                        typographer_item_text1.setVisibility(View.VISIBLE);
                        typographer_item_text1.setText(type2[0]);
                        typographer_item_text2.setText(type2[1]);
                        break;
                    case 2:
                        typographer_item_text3.setVisibility(View.VISIBLE);
                        typographer_item_text2.setVisibility(View.VISIBLE);
                        typographer_item_text1.setVisibility(View.VISIBLE);
                        typographer_item_text1.setText(type2[0]);
                        typographer_item_text2.setText(type2[1]);
                        typographer_item_text3.setText(type2[2]);
                        break;

                }
            }
        }
        if (datas.get(position).isCall())
        {
            typographer_item_call.setImageResource(R.mipmap.iconffocusonoff);

        }else{
            typographer_item_call.setImageResource(R.mipmap.iconffocusonofftwo);
        }

        typographer_item_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String token = SharePreferenceUtils.getInstance(context, SharePreferenceConstance.USERNAME).getSharePreferenceString(SharePreferenceConstance.USERTOKEN, null);

                if (token != null) {
                    datas.get(position).setIsCall(true);
                    typographer_item_call.setImageResource(R.mipmap.iconffocusonoff);
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put("token", token);
                    map.put("id", datas.get(position).getId());
                    map.put("type", String.valueOf(0));
                    HttpFactory.getInstance().focusdelete(context, map);
                    EventBus.getDefault().post(String.valueOf(4));

                } else {
                    IntentUtils.getInstance().startToAnoterActivity(context, Activity_Login.class, null);
                }

            }
        });

        commonViewHolder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("user_id", datas.get(position).getUser_id());
                bundle.putString("flag", "2");
                IntentUtils.getInstance().startToAnoterActivity(context, Activity_printingPeapleDetails.class, bundle);
            }
        });

        return commonViewHolder.getConvertView();
    }
}
