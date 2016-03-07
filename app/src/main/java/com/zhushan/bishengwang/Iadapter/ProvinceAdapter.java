package com.zhushan.bishengwang.Iadapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.zhushan.bishengwang.Entry.ProvinceData;
import com.zhushan.bishengwang.Itools.IntentUtils;
import com.zhushan.bishengwang.R;
import com.zhushan.bishengwang.activity.Activity_city;

import java.util.List;

/**
 * Created by Administrator on 2016/1/25.
 */
public class ProvinceAdapter extends CommonBaseAdapter<ProvinceData> {
    private String flag;
    public ProvinceAdapter(String flag,Activity context, List datas, int resourceId) {
        super(context, datas, resourceId);
        this.flag = flag;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        CommonViewHolder commonViewHolder = CommonViewHolder.getInstance(context,convertView,parent,resourceId);
        commonViewHolder.setText(R.id.item_province,datas.get(position).getRegion_name());
        commonViewHolder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("id",datas.get(position).getRegion_id());
                bundle.putString("flag",flag);
                IntentUtils.getInstance().startToAnoterActivity(context, Activity_city.class,bundle);
            }
        });
        return commonViewHolder.getConvertView();
    }
}
