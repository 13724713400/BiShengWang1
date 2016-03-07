package com.zhushan.bishengwang.Iadapter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhushan.bishengwang.Constance.HttpConstance;
import com.zhushan.bishengwang.Constance.SharePreferenceConstance;
import com.zhushan.bishengwang.Entry.MyPublicData;
import com.zhushan.bishengwang.Ifactory.HttpFactory;
import com.zhushan.bishengwang.Itools.AlertDialogUtils;
import com.zhushan.bishengwang.Itools.DateUtils;
import com.zhushan.bishengwang.Itools.IntentUtils;
import com.zhushan.bishengwang.Itools.SharePreferenceUtils;
import com.zhushan.bishengwang.R;
import com.zhushan.bishengwang.activity.Activity_detailes;

import java.util.HashMap;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2015/12/16.
 */
public class MyPublishiAdapter extends CommonBaseAdapter<MyPublicData> {

    private boolean isFirst,isTwo,isThree;
    private   AlertDialogUtils alertDialogUtils = null;
    public MyPublishiAdapter(Context context, List datas, int resourceId) {
        super(context, datas, resourceId);

        alertDialogUtils = new AlertDialogUtils(context, R.layout.alertdialog);
        alertDialogUtils.showDialog(true);
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        CommonViewHolder commonViewHolder = CommonViewHolder.getInstance(context,convertView,parent,resourceId);
        ImageView mypulishi_item_shuai = commonViewHolder.getView(R.id.mypulishi_item_shuai);
        TextView mypulishi_item_date = commonViewHolder.getView(R.id.mypulishi_item_date);
        TextView mypulishi_item_titles = commonViewHolder.getView(R.id.mypulishi_item_titles);
        TextView mypulishi_item_content = commonViewHolder.getView(R.id.mypulishi_item_content);
        ImageView mypulishi_item_img1 = commonViewHolder.getView(R.id.mypulishi_item_img1);
        ImageView mypulishi_item_img2 = commonViewHolder.getView(R.id.mypulishi_item_img2);
        ImageView mypulishi_item_img3 = commonViewHolder.getView(R.id.mypulishi_item_img3);
        TextView mypulishi_item_address = commonViewHolder.getView(R.id.mypulishi_item_address);
        TextView mypulishi_item_guanzhu = commonViewHolder.getView(R.id.mypulishi_item_guanzhu);
        TextView mypulishi_item_liulan = commonViewHolder.getView(R.id.mypulishi_item_liulan);
        TextView mypulishi_item_liuyan = commonViewHolder.getView(R.id.mypulishi_item_liuyan);
        TextView mypulishi_item_shixiaodelete =  commonViewHolder.getView(R.id.mypulishi_item_shixiaodelete);

        if (datas.get(position).getImg()!=null) {

            if (datas.get(position).getImg().size()==1) {
                mypulishi_item_img1.setVisibility(View.VISIBLE);
                commonViewHolder
                        .setImageUrlByGlide(context, HttpConstance.URL + datas.get(position).getImg().get(0).getImg_thumb(), mypulishi_item_img1);
                mypulishi_item_img2.setVisibility(View.INVISIBLE);
                mypulishi_item_img3.setVisibility(View.INVISIBLE);

            }

            if (datas.get(position).getImg().size()==2) {
                mypulishi_item_img1.setVisibility(View.VISIBLE);
                mypulishi_item_img2.setVisibility(View.VISIBLE);
                commonViewHolder
                        .setImageUrlByGlide(context, HttpConstance.URL + datas.get(position).getImg().get(0).getImg_thumb(), mypulishi_item_img1)
                        .setImageUrlByGlide(context, HttpConstance.URL + datas.get(position).getImg().get(1).getImg_thumb(), mypulishi_item_img2);
                mypulishi_item_img3.setVisibility(View.INVISIBLE);
            }

            if (datas.get(position).getImg().size()==3) {
                mypulishi_item_img1.setVisibility(View.VISIBLE);
                mypulishi_item_img2.setVisibility(View.VISIBLE);
                mypulishi_item_img3.setVisibility(View.VISIBLE);
                commonViewHolder
                        .setImageUrlByGlide(context, HttpConstance.URL + datas.get(position).getImg().get(0).getImg_thumb(), mypulishi_item_img1)
                        .setImageUrlByGlide(context, HttpConstance.URL + datas.get(position).getImg().get(1).getImg_thumb(), mypulishi_item_img2)
                        .setImageUrlByGlide(context, HttpConstance.URL + datas.get(position).getImg().get(2).getImg_thumb(), mypulishi_item_img3);

            }

        }

     switch (Integer.parseInt(datas.get(position).getCate_id()))
     {
         case 2:
             mypulishi_item_shuai.setImageResource(R.mipmap.shuai);
             break;
         case 1:
             mypulishi_item_shuai.setImageResource(R.mipmap.qiu);
             break;
         case 3:
             mypulishi_item_shuai.setImageResource(R.mipmap.jiu);
             break;
         case 4:
             mypulishi_item_shuai.setImageResource(R.mipmap.zhi);
             break;
     }

        switch (datas.get(position).getStatus())
        {
            case 0:
                commonViewHolder.getView(R.id.mypulishi_item_shixiao_img).setVisibility(View.VISIBLE);
                commonViewHolder.getView(R.id.mypulishi_item_shixiaodelete).setVisibility(View.VISIBLE);

                commonViewHolder.getView(R.id.mypulishi_item_shixiaodelete).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                            alertDialogUtils.showdialg();

                        alertDialogUtils.getView(R.id.sure).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                HashMap<String, String> map = new HashMap<String, String>();
                                map.put("token", SharePreferenceUtils.getInstance(context, SharePreferenceConstance.USERNAME).getSharePreferenceString(SharePreferenceConstance.USERTOKEN, null));
                                map.put("id", datas.get(position).getId());
                                HttpFactory.getInstance().myPublishiDel(context, map,Integer.parseInt(datas.get(position).getCate_id()));
                                alertDialogUtils.dissView();
                            }
                        });

                        alertDialogUtils.getView(R.id.cancle).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                alertDialogUtils.dissView();
                            }
                        });
                    }
                });

                break;
            case 1:
                commonViewHolder.getView(R.id.mypulishi_item_shixiao_img).setVisibility(View.GONE);
                commonViewHolder.getView(R.id.mypulishi_item_shixiaodelete).setVisibility(View.GONE);
                break;

        }

        commonViewHolder.setText(R.id.mypulishi_item_date,DateUtils.getDateToString(Long.parseLong(datas.get(position).getAddtime()+"000")))
                .setText(R.id.mypulishi_item_titles,datas.get(position).getTitle())
                .setText(R.id.mypulishi_item_content,datas.get(position).getContent())
                .setText(R.id.mypulishi_item_address,datas.get(position).getArea())
                .setText(R.id.mypulishi_item_guanzhu,context.getResources().getString(R.string.guanzhu)+" "+datas.get(position).getFoucsCount())
                .setText(R.id.mypulishi_item_liuyan,context.getResources().getString(R.string.detailes_liuyan)+" "+datas.get(position).getMessageCount())
                .setText(R.id.mypulishi_item_liulan, context.getResources().getString(R.string.detailes_liulan) + " " + datas.get(position).getHit());

        mypulishi_item_shixiaodelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    alertDialogUtils.showdialg();


                alertDialogUtils.getView(R.id.sure).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HashMap<String, String> map = new HashMap<String, String>();
                        map.put("token", SharePreferenceUtils.getInstance(context, SharePreferenceConstance.USERNAME).getSharePreferenceString(SharePreferenceConstance.USERTOKEN, null));
                        map.put("id", datas.get(position).getId());
                        HttpFactory.getInstance().myPublishiDel(context, map,Integer.parseInt(datas.get(position).getCate_id()));
                        alertDialogUtils.dissView();
                    }
                });

                alertDialogUtils.getView(R.id.cancle).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialogUtils.dissView();
                    }
                });

            }
        });

        commonViewHolder.getConvertView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                    alertDialogUtils.showdialg();
                alertDialogUtils.getView(R.id.sure).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HashMap<String, String> map = new HashMap<String, String>();
                        map.put("token", SharePreferenceUtils.getInstance(context, SharePreferenceConstance.USERNAME).getSharePreferenceString(SharePreferenceConstance.USERTOKEN, null));
                        map.put("id", datas.get(position).getId());
                        HttpFactory.getInstance().myPublishiDel(context, map,Integer.parseInt(datas.get(position).getCate_id()));
                        alertDialogUtils.dissView();
                    }
                });

                alertDialogUtils.getView(R.id.cancle).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialogUtils.dissView();
                    }
                });

                return false;
            }
        });


     /*   commonViewHolder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("id", datas.get(position).getId());
                IntentUtils.getInstance().startToAnoterActivity(context, Activity_detailes.class, bundle);
            }
        });*/


        return commonViewHolder.getConvertView();
    }
}
