package com.zhushan.bishengwang.Iadapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhushan.bishengwang.R;

import java.util.List;

/**
 * Created by Administrator on 2015/12/17.
 */
public class TypegrapherAdapter extends CommonBaseAdapter {
    public TypegrapherAdapter(Context context, List datas, int resourceId) {
        super(context, datas, resourceId);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CommonViewHolder commonViewHolder = CommonViewHolder.getInstance(context,convertView,parent,resourceId);
        ImageView typographer_item_touxiang =  commonViewHolder.getView(R.id.typographer_item_touxiang);
        TextView typographer_item_Name =  commonViewHolder.getView(R.id.typographer_item_Name);
        TextView typographer_item_address =  commonViewHolder.getView(R.id.typographer_item_address);
        TextView typographer_item_distance =  commonViewHolder.getView(R.id.typographer_item_distance);
        ImageView typographer_item_call =  commonViewHolder.getView(R.id.typographer_item_call);
        return commonViewHolder.getConvertView();
    }
}
