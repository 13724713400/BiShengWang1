package com.zhushan.bishengwang.Iadapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhushan.bishengwang.R;

import java.util.List;

/**
 * Created by Administrator on 2015/12/19.
 */
public class MainMyTipItemAdapter extends CommonBaseAdapter {
    public MainMyTipItemAdapter(Context context, List datas, int resourceId) {
        super(context, datas, resourceId);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        CommonViewHolder commonViewHolder = CommonViewHolder.getInstance(context,convertView,parent,resourceId);
        ImageView main_mytip_touxiang =  commonViewHolder.getView(R.id.main_mytip_touxiang);
        TextView textView = commonViewHolder.getView(R.id.main_mytip_titles);
        TextView main_mytip_content  = commonViewHolder.getView(R.id.main_mytip_content);
        ImageView main_mytip_isUsed = commonViewHolder.getView(R.id.main_mytip_isUsed);
        return commonViewHolder.getConvertView();
    }
}
