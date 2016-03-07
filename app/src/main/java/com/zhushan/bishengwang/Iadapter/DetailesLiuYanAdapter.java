package com.zhushan.bishengwang.Iadapter;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhushan.bishengwang.Constance.HttpConstance;
import com.zhushan.bishengwang.Constance.SharePreferenceConstance;
import com.zhushan.bishengwang.Entry.CommentData;
import com.zhushan.bishengwang.Itools.DateUtils;
import com.zhushan.bishengwang.Itools.IntentUtils;
import com.zhushan.bishengwang.Itools.SharePreferenceUtils;
import com.zhushan.bishengwang.R;
import com.zhushan.bishengwang.activity.Activity_Login;
import com.zhushan.bishengwang.activity.Activity_sendmsg;

import java.util.List;

/**
 * Created by Administrator on 2015/12/16.
 */
public class DetailesLiuYanAdapter extends CommonBaseAdapter<CommentData> {
    public DetailesLiuYanAdapter(Context context, List datas, int resourceId) {
        super(context, datas, resourceId);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        CommonViewHolder commonViewHolder = CommonViewHolder.getInstance(context,convertView,parent,resourceId);
        ImageView detailes_liuyanitem_touxiang = commonViewHolder.getView(R.id.detailes_liuyanitem_touxiang);
        TextView detailes_liuyanitem_Name = commonViewHolder.getView(R.id.detailes_liuyanitem_Name);
        ImageView detailes_liuyanitem_sex =  commonViewHolder.getView(R.id.detailes_liuyanitem_sex);
        ImageView detailes_liuyanitem_type = commonViewHolder.getView(R.id.detailes_liuyanitem_type);
        TextView detailes_liuyanitem_sixin =  commonViewHolder.getView(R.id.detailes_liuyanitem_sixin);
        TextView detailes_liuyanitem_content   = commonViewHolder.
        getView(R.id.detailes_liuyanitem_content);
        TextView detailes_liuyanitem_date = commonViewHolder.getView(R.id.detailes_liuyanitem_date);
        TextView detailes_liuyanitem_address = commonViewHolder.
        getView(R.id.detailes_liuyanitem_address);

        if (datas.get(position).getHead_thumb().contains("Uploads")) {
            commonViewHolder.setCircleImageUrlByGlide(context, HttpConstance.URL + datas.get(position).getHead_thumb(), detailes_liuyanitem_touxiang);
        }else{
            commonViewHolder.setCircleImageUrlByGlide(context, datas.get(position).getHead_thumb(), detailes_liuyanitem_touxiang);
        }
        commonViewHolder.setText(R.id.detailes_liuyanitem_Name,datas.get(position).getNickname())
                .setText(R.id.detailes_liuyanitem_content,datas.get(position).getComment())
                .setText(R.id.detailes_liuyanitem_date, DateUtils.getDateToString(Long.parseLong(datas.get(position).getAddtime()+"000")))
                .setText(R.id.detailes_liuyanitem_address,datas.get(position).getArea());


        if (datas.get(position).getSex()!=null) {
            switch (datas.get(position).getSex()) {
                case "男":
                    detailes_liuyanitem_sex.setImageResource(R.mipmap.icon_male);

                    break;

                case "女":
                    detailes_liuyanitem_sex.setImageResource(R.mipmap.icon_female);
                    break;

            }
        }
        if (datas.get(position).getUser_type()!=null) {
            switch (Integer.parseInt(datas.get(position).getUser_type())) {
                case 0:
                    detailes_liuyanitem_type.setImageResource(R.mipmap.cai);
                    break;

                case 1:
                    detailes_liuyanitem_type.setImageResource(R.mipmap.ren);
                    break;

                case 2:
                    detailes_liuyanitem_type.setImageResource(R.mipmap.shang);
                    break;


            }


        }
        commonViewHolder.getView(R.id.detailes_liuyanitem_sixin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String token2 = SharePreferenceUtils.getInstance(context, SharePreferenceConstance.USERNAME).getSharePreferenceString(SharePreferenceConstance.USERTOKEN, null);
                if (token2 != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString("user_id",datas.get(position).getUser_send_id());
                    IntentUtils.getInstance().startToAnoterActivity(context,Activity_sendmsg.class,bundle);
                } else {
                    IntentUtils.getInstance().startToAnoterActivity(context, Activity_Login.class, null);
                }
            }
        });
        return commonViewHolder.getConvertView();
    }
}
