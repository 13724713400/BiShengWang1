package com.zhushan.bishengwang.Iadapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.zhushan.bishengwang.Constance.HttpConstance;
import com.zhushan.bishengwang.Constance.SharePreferenceConstance;
import com.zhushan.bishengwang.Entry.GalleryDataTwo;
import com.zhushan.bishengwang.Ifactory.HttpFactory;
import com.zhushan.bishengwang.Iselfview.MyGridView;
import com.zhushan.bishengwang.Itools.AlertDialogUtils;
import com.zhushan.bishengwang.Itools.IntentUtils;
import com.zhushan.bishengwang.Itools.SharePreferenceUtils;
import com.zhushan.bishengwang.R;
import com.zhushan.bishengwang.activity.Activity_Imgdetails;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2016/1/12.
 */
public class MyPhotoGridviewAdapter extends CommonBaseAdapter<GalleryDataTwo> {
    private AlertDialogUtils alertDialogUtils;
    private String id;
    public MyPhotoGridviewAdapter(String id,Context context, final ArrayList datas, int resourceId) {
        super(context, datas, resourceId);
        alertDialogUtils = new AlertDialogUtils(context,R.layout.alertdialog);
        alertDialogUtils.showDialog(true);
        this.id = id;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        CommonViewHolder commonViewHolder = CommonViewHolder.getInstance(context,convertView,parent,resourceId);
        commonViewHolder.setImageUrlByGlide(context, HttpConstance.URL + datas.get(position).getMid_img(), (ImageView)commonViewHolder.getView(R.id.myphoto_item_img));

        if (id!=null) {
            commonViewHolder.getConvertView().setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    alertDialogUtils.showdialg();
                    alertDialogUtils.getView(R.id.sure).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            HashMap<String, String> map = new HashMap<String, String>();
                            map.put("token", SharePreferenceUtils.getInstance(context, SharePreferenceConstance.USERNAME).getSharePreferenceString(SharePreferenceConstance.USERTOKEN, null));
                            map.put("album_id", datas.get(position).getAlbum_id());
                            map.put("gallery_id", id);
                            map.put("album_count", String.valueOf(datas.size()));
                            HttpFactory.getInstance().MyPhotoDel(context, map);

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
        }

commonViewHolder.getConvertView().setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Log.i("www","dianji");
        Bundle bundle2 = new Bundle();
        bundle2.putParcelableArrayList("Activity_MyPhoto", (ArrayList<? extends Parcelable>) datas);
        bundle2.putInt("index", position);
        IntentUtils.getInstance().startToAnoterActivity(context, Activity_Imgdetails.class, bundle2);
        ((Activity)context).overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
    }
});
        return commonViewHolder.getConvertView();
    }
}
