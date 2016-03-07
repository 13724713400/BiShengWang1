package com.zhushan.bishengwang.Iadapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.zhushan.bishengwang.Entry.DirectorSettingEntry;
import com.zhushan.bishengwang.Entry.Label_list;
import com.zhushan.bishengwang.R;

import java.util.List;

/**
 * Created by Administrator on 2015/12/21.
 */
public class Director_setting_ItemAdapter extends CommonBaseAdapter<Label_list> {

    public Director_setting_ItemAdapter(Context context, List<Label_list> datas, int resourceId) {
        super(context, datas, resourceId);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
      final  CommonViewHolder commonViewHolder = CommonViewHolder.getInstance(context,convertView,parent,resourceId);
        commonViewHolder.setText(R.id.director_setting_chiled_item_bt, datas.get(position).getLabel_name());
        if (datas.get(position).ischeck())
        {
            commonViewHolder.getView(R.id.director_setting_chiled_item_bt).setBackgroundResource(R.drawable.director_item_bg_check);

        }else{
            commonViewHolder.getView(R.id.director_setting_chiled_item_bt).setBackgroundResource(R.drawable.director_item_bg);
        }

        commonViewHolder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!datas.get(position).ischeck()) {
                    datas.get(position).setIscheck(true);
                    commonViewHolder.getView(R.id.director_setting_chiled_item_bt).setBackgroundResource(R.drawable.director_item_bg_check);
                } else {
                    datas.get(position).setIscheck(false);
                    commonViewHolder.getView(R.id.director_setting_chiled_item_bt).setBackgroundResource(R.drawable.director_item_bg);
                }
            }
        });
        return commonViewHolder.getConvertView();
    }
}
