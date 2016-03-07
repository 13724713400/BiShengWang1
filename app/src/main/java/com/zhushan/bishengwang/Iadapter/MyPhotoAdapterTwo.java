package com.zhushan.bishengwang.Iadapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;

import com.zhushan.bishengwang.Entry.MyPhotoData;
import com.zhushan.bishengwang.Iselfview.MyGridView;
import com.zhushan.bishengwang.Itools.CameraUtils;
import com.zhushan.bishengwang.Itools.DateUtils;
import com.zhushan.bishengwang.Itools.IntentUtils;
import com.zhushan.bishengwang.R;
import com.zhushan.bishengwang.activity.Activity_Imgdetails;
import com.zhushan.bishengwang.activity.Activity_MyPhoto;

import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2016/1/12.
 */
public class MyPhotoAdapterTwo extends CommonBaseAdapter<MyPhotoData> {
    private MyPhotoGridviewAdapter myPhotoGridviewAdapter;
    private Activity activity;
    public MyPhotoAdapterTwo(Activity context, List<MyPhotoData> datas, int resourceId) {
        super(context, datas, resourceId);
        this.activity = context;
    }

    @Override
    public View getView(int position, final View convertView, ViewGroup parent) {
        CommonViewHolder commonViewHolder = CommonViewHolder.getInstance(context,convertView,parent,resourceId);
        String date = datas.get(position).getAddtime().split("-")[2];
        String month =  datas.get(position).getAddtime().split("-")[1];
        commonViewHolder.setText(R.id.myphoto_describe,datas.get(position).getDescription())
                .setText(R.id.myphoto_date,date)
                .setText(R.id.myphoto_month,month);
        Log.i("www","今天"+DateUtils.getDateToString().toString());
      if (DateUtils.getDateToString().equals(datas.get(position).getAddtime()))
      {
          EventBus.getDefault().post(datas.get(0).getId());
          commonViewHolder.getView(R.id.myPhoto_add).setVisibility(View.VISIBLE);
          commonViewHolder.getView(R.id.myPhoto_add).setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  CameraUtils.photograph(activity, CameraUtils.CAPTURE1, CameraUtils.ACTION_PICK1);
              }
          });
       /*   ((MyGridView)commonViewHolder.getView(R.id.myphoto_gallery)).setOnItemClickListener(new AdapterView.OnItemClickListener() {
              @Override
              public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                  if (position!=0) {
                      Log.i("www","dianji");
                      Bundle bundle2 = new Bundle();
                      bundle2.putParcelableArrayList("Activity_MyPhoto", datas.get(position).getGallery());
                      bundle2.putInt("index", position);
                      IntentUtils.getInstance().startToAnoterActivity(context, Activity_Imgdetails.class, bundle2);
                      ((Activity) context).overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
                  }
              }
          });*/


      }else{
          commonViewHolder.getView(R.id.myPhoto_add).setVisibility(View.GONE);


      }

        if ( datas.get(position).getGallery()!=null) {
            myPhotoGridviewAdapter = new MyPhotoGridviewAdapter(datas.get(position).getId(),context, datas.get(position).getGallery(), R.layout.myphoto_listview_item);
        }
        ((MyGridView)commonViewHolder.getView(R.id.myphoto_gallery)).setAdapter(myPhotoGridviewAdapter);

        return commonViewHolder.getConvertView();
    }
}
