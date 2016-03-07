package com.zhushan.bishengwang.Iadapter;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhushan.bishengwang.Constance.HttpConstance;
import com.zhushan.bishengwang.Constance.SharePreferenceConstance;
import com.zhushan.bishengwang.Entry.PrintingPeapleData;
import com.zhushan.bishengwang.Ifactory.HttpFactory;
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
 * Created by Administrator on 2015/12/17.
 */
public class MyfocusPringtingPeapleAdapter extends CommonBaseAdapter<PrintingPeapleData> {

    private double lat,lng;
    public MyfocusPringtingPeapleAdapter(Activity context, List datas, int resourceId) {
        super(context, datas, resourceId);
         Log.i("www", "listview" + datas);
        lat =  Double.parseDouble(SharePreferenceUtils.getInstance(context, SharePreferenceConstance.POSITIONNAME).getSharePreferenceString(SharePreferenceConstance.POSITIONNALAT,null));
        lng =  Double.parseDouble(SharePreferenceUtils.getInstance(context, SharePreferenceConstance.POSITIONNAME).getSharePreferenceString(SharePreferenceConstance.POSITIONNALNG,null));
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        CommonViewHolder commonViewHolder = CommonViewHolder.getInstance(context,convertView,parent,resourceId);
        ImageView printing_item_touxiangl = commonViewHolder.getView(R.id.printing_item_touxiangl);
    //    TextView printing_item_diz = commonViewHolder.getView(R.id.printing_item_diz);
        TextView printing_item_age = commonViewHolder.getView(R.id.userage);
        TextView printing_item_Name = commonViewHolder.getView(R.id.printing_item_Name);
       ImageView printing_item_sex =  commonViewHolder.getView(R.id.printing_item_sex);
       ImageView printing_item_type =  commonViewHolder.getView(R.id.printing_item_type);
       TextView printing_item_distance =  commonViewHolder.getView(R.id.printing_item_distance);
      // final ImageView printing_item_guanzhu =  commonViewHolder.getView(R.id.printing_item_guanzhu);
      // TextView printing_item_zhiwei =  commonViewHolder.getView(R.id.printing_item_zhiwei);
     //  TextView printing_item_gongsi =  commonViewHolder.getView(R.id.printing_item_gongsi);
      // TextView printing_item_guanzhutxt =  commonViewHolder.getView(R.id.printing_item_guanzhutxt);
       // TextView printing_item_huoyuezhi = commonViewHolder.getView(R.id.printing_item_huoyuezhi);
        //printing_item_guanzhu.setImageResource(R.mipmap.iconffocusonofftwo);
        if (datas.get(position).isFocus())
        {
            //printing_item_guanzhu.setImageResource(R.mipmap.iconffocusonoff);

        }else{
            //printing_item_guanzhu.setImageResource(R.mipmap.iconffocusonofftwo);
        }

      /*  printing_item_guanzhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String token = SharePreferenceUtils.getInstance(context,SharePreferenceConstance.USERNAME).getSharePreferenceString(SharePreferenceConstance.USERTOKEN,null);

                if (token!=null) {
                    datas.get(position).setIsFocus(true);
                    //printing_item_guanzhu.setImageResource(R.mipmap.iconffocusonoff);
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put("token",token);
                    map.put("id",datas.get(position).getId());
                    map.put("type",String.valueOf(0));
                    HttpFactory.getInstance().focusdelete(context, map);
                    EventBus.getDefault().post(String.valueOf(3));

                }else{
                    IntentUtils.getInstance().startToAnoterActivity(context, Activity_Login.class,null);
                }

            }
        });*/

        commonViewHolder.setCircleImageUrlByGlide(context, HttpConstance.URL + datas.get(position).getHead_thumb(), printing_item_touxiangl);
        commonViewHolder.setText(R.id.printing_item_Name, datas.get(position).getNickname())
                .setText(R.id.userage, datas.get(position).getAge())
                //    .setText(R.id.printing_item_diz, datas.get(position).getArea())
               // .setText(R.id.printing_item_zhiwei,context.getResources().getString(R.string.zhiwei)+"  "+datas.get(position).getRule())
               // .setText(R.id.printing_item_gongsi,context.getResources().getString(R.string.gongsi)+"  "+datas.get(position).getCompany_name())
               // .setText(R.id.printing_item_guanzhutxt,context.getResources().getString(R.string.guanzhu)+" "+datas.get(position).getFoucsCount())
             //   .setText(R.id.printing_item_huoyuezhi, context.getResources().getString(R.string.huoyuezhitwo) + " " + datas.get(position).getPoint())
                .setText(R.id.printing_item_distance, String.valueOf(PositionMySeft.getDistance(lat, lng, Double.parseDouble(datas.get(position).getLat()), Double.parseDouble(datas.get(position).getLng())))+" km");

                        printing_item_type.setImageResource(R.mipmap.ren);
        switch (datas.get(position).getSex())
        {
            case "男":
                printing_item_sex.setImageResource(R.mipmap.icon_male);
                break;

            case "女":
                printing_item_sex.setImageResource(R.mipmap.icon_female);
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
