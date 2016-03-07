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
import com.zhushan.bishengwang.Entry.HomePageInfoData;
import com.zhushan.bishengwang.Ifactory.HttpFactory;
import com.zhushan.bishengwang.Itools.CallPhoneUtils;
import com.zhushan.bishengwang.Itools.DateUtils;
import com.zhushan.bishengwang.Itools.IntentUtils;
import com.zhushan.bishengwang.Itools.PositionMySeft;
import com.zhushan.bishengwang.Itools.SharePreferenceUtils;
import com.zhushan.bishengwang.R;
import com.zhushan.bishengwang.activity.Activity_Login;
import com.zhushan.bishengwang.activity.Activity_detailes;

import java.util.HashMap;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2015/12/16.
 */
public class MyFocusItemAdapter extends CommonBaseAdapter<HomePageInfoData>   {
    private double lat,lng;
    private PositionMySeft positionMySeft;
    public MyFocusItemAdapter(final Activity context, List datas, int resourceId) {
        super(context, datas, resourceId);
        lat =  Double.parseDouble(SharePreferenceUtils.getInstance(context, SharePreferenceConstance.POSITIONNAME).getSharePreferenceString(SharePreferenceConstance.POSITIONNALAT,null));
        lng =  Double.parseDouble(SharePreferenceUtils.getInstance(context, SharePreferenceConstance.POSITIONNAME).getSharePreferenceString(SharePreferenceConstance.POSITIONNALNG,null));

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        CommonViewHolder commonViewHolder = CommonViewHolder.getInstance(context,convertView,parent,resourceId);
        ImageView homepage_item_touxiang = commonViewHolder.getView(R.id.homepage_item_touxiang);
        TextView homepage_item_Name =  commonViewHolder.getView(R.id.homepage_item_Name);
        ImageView homepage_item_sex =  commonViewHolder.getView(R.id.homepage_item_sex);
       final ImageView homepage_item_gunazhuimg =  commonViewHolder.getView(R.id.homepage_item_gunazhuimg);
        final ImageView homepage_item_call =  commonViewHolder.getView(R.id.homepage_item_call);
        ImageView homepage_item_shuai =  commonViewHolder.getView(R.id.homepage_item_shuai);
        TextView homepage_item_date =  commonViewHolder.getView(R.id.homepage_item_date);
        TextView homepage_item_distance =  commonViewHolder.getView(R.id.homepage_item_distance);
        TextView homepage_item_content_titles =  commonViewHolder.getView(R.id.homepage_item_content_titles);
        TextView homepage_item_content =  commonViewHolder.getView(R.id.homepage_item_content);
        ImageView homepage_item_img1 =  commonViewHolder.getView(R.id.homepage_item_img1);
        ImageView homepage_item_img2 =  commonViewHolder.getView(R.id.homepage_item_img2);
        ImageView homepage_item_img3 =  commonViewHolder.getView(R.id.homepage_item_img3);
        TextView homepage_item_address =  commonViewHolder.getView(R.id.homepage_item_address);
        TextView homepage_item_guanzhu =  commonViewHolder.getView(R.id.homepage_item_guanzhu);
        TextView homepage_item_liulan =  commonViewHolder.getView(R.id.homepage_item_liulan);
        TextView homepage_item_liuyan =  commonViewHolder.getView(R.id.homepage_item_liuyan);
       ImageView homepage_item_type =  commonViewHolder.getView(R.id.homepage_item_type);
        homepage_item_gunazhuimg.setImageResource(R.mipmap.iconffocusonofftwo);
        Log.i("www","data"+datas);
        if (datas.get(position).isFocus())
        {
            homepage_item_gunazhuimg.setImageResource(R.mipmap.iconffocusonoff);

        }else{
            homepage_item_gunazhuimg.setImageResource(R.mipmap.iconffocusonofftwo);
        }
        homepage_item_gunazhuimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String token = SharePreferenceUtils.getInstance(context,SharePreferenceConstance.USERNAME).getSharePreferenceString(SharePreferenceConstance.USERTOKEN,null);

                if (token!=null) {
                    datas.get(position).setIsFocus(true);
                    homepage_item_gunazhuimg.setImageResource(R.mipmap.iconffocusonoff);
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put("token",token);
                    map.put("id",datas.get(position).getId());
                    map.put("type",String.valueOf(1));
                    HttpFactory.getInstance().focusdelete(context, map);

                    EventBus.getDefault().post(String.valueOf(2));

                }else{
                    IntentUtils.getInstance().startToAnoterActivity(context, Activity_Login.class,null);
                }

            }
        });




        if (datas.get(position).getImg()!=null) {

            if (datas.get(position).getImg().size()==1) {
                homepage_item_img1.setVisibility(View.VISIBLE);
                commonViewHolder.setCircleImageUrlByGlide(context, HttpConstance.URL + datas.get(position).getHead_thumb(), homepage_item_touxiang)
                        .setImageUrlByGlide(context, HttpConstance.URL + datas.get(position).getImg().get(0).getImg_thumb(), homepage_item_img1);
                homepage_item_img2.setVisibility(View.INVISIBLE);
                homepage_item_img3.setVisibility(View.INVISIBLE);

            }

            if (datas.get(position).getImg().size()==2) {
                homepage_item_img1.setVisibility(View.VISIBLE);
                homepage_item_img2.setVisibility(View.VISIBLE);
                commonViewHolder.setCircleImageUrlByGlide(context, HttpConstance.URL + datas.get(position).getHead_thumb(), homepage_item_touxiang)
                        .setImageUrlByGlide(context, HttpConstance.URL + datas.get(position).getImg().get(0).getImg_thumb(), homepage_item_img1)
                        .setImageUrlByGlide(context, HttpConstance.URL + datas.get(position).getImg().get(1).getImg_thumb(), homepage_item_img2);
                homepage_item_img3.setVisibility(View.INVISIBLE);
            }

            if (datas.get(position).getImg().size()==3) {
                homepage_item_img1.setVisibility(View.VISIBLE);
                homepage_item_img2.setVisibility(View.VISIBLE);
                homepage_item_img3.setVisibility(View.VISIBLE);
                commonViewHolder.setCircleImageUrlByGlide(context, HttpConstance.URL + datas.get(position).getHead_thumb(), homepage_item_touxiang)
                        .setImageUrlByGlide(context, HttpConstance.URL + datas.get(position).getImg().get(0).getImg_thumb(), homepage_item_img1)
                        .setImageUrlByGlide(context, HttpConstance.URL + datas.get(position).getImg().get(1).getImg_thumb(), homepage_item_img2)
                        .setImageUrlByGlide(context, HttpConstance.URL + datas.get(position).getImg().get(2).getImg_thumb(), homepage_item_img3);

            }

        }
        if (datas.get(position).getNickname()!=null&&datas.get(position).getTitle()!=null&& datas.get(position).getContent()!=null&&datas.get(position).getArea()!=null&&datas.get(position).getFoucsCount()!=null&&datas.get(position).getHit()!=null&& datas.get(position).getMessageCount()!=null&&datas.get(position).getLat()!=null&&datas.get(position).getLng()!=null&&datas.get(position).getAddtime()!=null) {


            commonViewHolder.setText(R.id.homepage_item_Name, datas.get(position).getNickname())
                    .setText(R.id.homepage_item_content_titles, datas.get(position).getTitle())
                    .setText(R.id.homepage_item_content, datas.get(position).getContent())
                    .setText(R.id.homepage_item_address, datas.get(position).getArea())
                    .setText(R.id.homepage_item_guanzhu, context.getResources().getString(R.string.detailes_tuijian) + "  " + datas.get(position).getFoucsCount())
                    .setText(R.id.homepage_item_liulan, context.getResources().getString(R.string.detailes_liulan) + "  " + datas.get(position).getHit())
                    .setText(R.id.homepage_item_liuyan, context.getResources().getString(R.string.detailes_liuyan) + "  " + datas.get(position).getMessageCount());

                    if(!datas.get(position).getLat().equals("")&&!datas.get(position).getLng().equals("")) {

                        commonViewHolder.setText(R.id.homepage_item_distance, String.valueOf(positionMySeft.getDistance(lat, lng, Double.parseDouble(datas.get(position).getLat()), Double.parseDouble(datas.get(position).getLng()))) + "km");
                    }
            homepage_item_date.setText(DateUtils.getDateToString(Long.parseLong(datas.get(position).getAddtime() + "000")) + " ");
        }
       switch (datas.get(position).getUser_type())
           {
           case 0:
               homepage_item_type.setImageResource(R.mipmap.cai);
               break;

           case 1:
               homepage_item_type.setImageResource(R.mipmap.ren);
               break;

           case 2:
               homepage_item_type.setImageResource(R.mipmap.shang);
               break;
       }


        if (datas.get(position).getSex()!=null) {
            if (datas.get(position).getSex().trim().equals("男")) {
                homepage_item_sex.setImageResource(R.mipmap.icon_male);

            }
            if (datas.get(position).getSex().trim().equals("女")) {
                homepage_item_sex.setImageResource(R.mipmap.icon_female);

            }
        }

        if (datas.get(position).getCate_id()!=null) {
            switch (datas.get(position).getCate_id()) {
                case "2":
                    homepage_item_shuai.setImageResource(R.mipmap.xiaoshuai);
                    break;

                case "1":
                    homepage_item_shuai.setImageResource(R.mipmap.qiu);
                    break;

                case "3":
                    homepage_item_shuai.setImageResource(R.mipmap.jiu);
                    break;

                case "4":
                    homepage_item_shuai.setImageResource(R.mipmap.zhi);
                    break;
            }

        }

        if (datas.get(position).isCall())
        {
            homepage_item_call.setImageResource(R.mipmap.icon_phone_on1);
        }else
        {
            homepage_item_call.setImageResource(R.mipmap.icon_phone_off);
        }

        homepage_item_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homepage_item_call.setImageResource(R.mipmap.icon_phone_on2);
                CallPhoneUtils.getInstance().call(datas.get(position).getTelephone(),context,homepage_item_call,datas,position);
            }
        });

      commonViewHolder.getConvertView().setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Bundle bundle = new Bundle();
              bundle.putString("id", datas.get(position).getId());
              IntentUtils.getInstance().startToAnoterActivity(context, Activity_detailes.class, bundle);
          }
      });
        commonViewHolder.getConvertView().setBackgroundResource(R.drawable.bg);
        return commonViewHolder.getConvertView();
    }

}
