package com.zhushan.bishengwang.Iadapter;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.zhushan.bishengwang.Entry.MyPhotoData;
import com.zhushan.bishengwang.Iselfview.MyGridView;
import com.zhushan.bishengwang.Itools.CameraUtils;
import com.zhushan.bishengwang.Itools.DateUtils;
import com.zhushan.bishengwang.R;

import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2016/1/12.
 */
public class MyPhotoAdapterThree extends CommonBaseAdapter<MyPhotoData> {
    private MyPhotoGridviewAdapter myPhotoGridviewAdapter;
    private Activity activity;
    public MyPhotoAdapterThree(Activity context, List<MyPhotoData> datas, int resourceId) {
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

        if ( datas.get(position).getGallery()!=null) {
            myPhotoGridviewAdapter = new MyPhotoGridviewAdapter(null,context, datas.get(position).getGallery(), R.layout.myphoto_listview_item);
        }
        ((MyGridView)commonViewHolder.getView(R.id.myphoto_gallery)).setAdapter(myPhotoGridviewAdapter);

        return commonViewHolder.getConvertView();
    }
}
