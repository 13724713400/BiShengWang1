package com.zhushan.bishengwang.Iadapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.zhushan.bishengwang.Constance.HttpConstance;
import com.zhushan.bishengwang.Constance.SharePreferenceConstance;
import com.zhushan.bishengwang.Entry.MyMessageData;
import com.zhushan.bishengwang.Entry.PrivateData;
import com.zhushan.bishengwang.Ifactory.HttpFactory;
import com.zhushan.bishengwang.Itools.AlertDialogUtils;
import com.zhushan.bishengwang.Itools.DateUtils;
import com.zhushan.bishengwang.Itools.SharePreferenceUtils;
import com.zhushan.bishengwang.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/1/8.
 */
public class MyPrivateAdapter extends CommonBaseAdapter<PrivateData> {
    private AlertDialogUtils alertDialogUtils = null;
    private boolean isFirst,isTwo;
    public MyPrivateAdapter(Context context, List<PrivateData> datas, int resourceId) {
        super(context, datas, resourceId);
        alertDialogUtils = new AlertDialogUtils(context,R.layout.alertdialog);
        alertDialogUtils.showDialog(true);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        CommonViewHolder commonViewHolder = CommonViewHolder.getInstance(context,convertView,parent,resourceId);
       ImageView mymessage_item_head = commonViewHolder.getView(R.id.mymessage_item_head);
        ImageView mymessage_item_type = commonViewHolder.getView(R.id.mymessage_item_type);
        ImageView mymessage_item_sex = commonViewHolder.getView(R.id.mymessage_item_sex);
        if (datas.get(position).getHead_img().contains("Uploads")) {
            commonViewHolder.setCircleImageUrlByGlide(context, HttpConstance.URL + datas.get(position).getHead_img(), mymessage_item_head);
        }else{
            commonViewHolder.setCircleImageUrlByGlide(context, datas.get(position).getHead_img(), mymessage_item_head);
        }
        commonViewHolder.setText(R.id.mymessage_item_content,datas.get(position).getMessage())
                .setText(R.id.mymessage_item_date, DateUtils.getDateToString(Long.parseLong(datas.get(position).getAddtime()+"000")))
                .setText(R.id.mymessage_item_name, datas.get(position).getNickname());
        commonViewHolder.getView(R.id.mymessage_item_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialogUtils.showdialg();
                alertDialogUtils.getView(R.id.sure).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HashMap<String, String> map = new HashMap<String, String>();
                        map.put("token", SharePreferenceUtils.getInstance(context, SharePreferenceConstance.USERNAME).getSharePreferenceString(SharePreferenceConstance.USERTOKEN, null));
                        map.put("id", datas.get(position).getId());
                        HttpFactory.getInstance().msgDel(context, map);
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
                        HttpFactory.getInstance().msgDel(context, map);
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

        return commonViewHolder.getConvertView();
    }
}
