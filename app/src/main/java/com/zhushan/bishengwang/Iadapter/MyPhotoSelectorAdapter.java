package com.zhushan.bishengwang.Iadapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.zhushan.bishengwang.Entry.MyPhotoSelectorEntry;
import com.zhushan.bishengwang.R;

import java.util.List;

/**
 * Created by Administrator on 2016/1/19.
 */
public class MyPhotoSelectorAdapter extends CommonBaseAdapter<MyPhotoSelectorEntry> {
    public MyPhotoSelectorAdapter(Context context, List datas, int resourceId) {
        super(context, datas, resourceId);

        Log.i("www","大小"+datas.size());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CommonViewHolder commonViewHolder = CommonViewHolder.getInstance(context,convertView,parent,resourceId);
        ImageView imageView = commonViewHolder.getView(R.id.myphoto_itemtwo);
        if (datas.get(position).getId()!=0)
        {
            imageView.setImageResource(R.mipmap.purchase);
        }
        commonViewHolder.setImageUrlByGlide(context,datas.get(position).getPath(),imageView);
        return commonViewHolder.getConvertView();
    }
}
