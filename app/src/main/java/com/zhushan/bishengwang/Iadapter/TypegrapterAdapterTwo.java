package com.zhushan.bishengwang.Iadapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhushan.bishengwang.Constance.HttpConstance;
import com.zhushan.bishengwang.Entry.Banner;
import com.zhushan.bishengwang.Entry.DataList;
import com.zhushan.bishengwang.R;

import java.util.List;

/**
 * Created by Administrator on 2016/1/12.
 */
public class TypegrapterAdapterTwo extends CommonBaseAdapter<DataList> {
    private Banner banner;
    private boolean isFirst;
    public TypegrapterAdapterTwo(Context context, List<DataList> datas, int resourceId,Banner banner,boolean isFirst) {
        super(context, datas, resourceId);
        this.banner = banner;
        this.isFirst = isFirst;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CommonViewHolder commonViewHolder  = CommonViewHolder.getInstance(context,convertView,parent,resourceId);
        ImageView typographer_item_touxiang = commonViewHolder.getView(R.id.typographer_item_touxiang);

        TextView titles = commonViewHolder.getView(R.id.typographer_item_Name);
        TextView distance= commonViewHolder.getView(R.id.typographer_item_distance);
        TextView address = commonViewHolder.getView(R.id.typographer_item_address);
        TextView text1 = commonViewHolder.getView(R.id.typographer_item_text1);
        TextView textt2 = commonViewHolder.getView(R.id.typographer_item_text2);
        TextView tText3 = commonViewHolder.getView(R.id.typographer_item_text3);
        ImageView typographer_item_call = commonViewHolder.getView(R.id.typographer_item_call);
        commonViewHolder.setImageUrlByGlide(context, HttpConstance.URL + datas.get(position).getImg_thumb(), typographer_item_touxiang);
        commonViewHolder.setText(R.id.typographer_item_Name,datas.get(position).getCompany_name())
                .setText(R.id.typographer_item_address,datas.get(position).getCompany_area());
        if (datas.get(position).isCheck())
        {

                ((ImageView) commonViewHolder.getView(R.id.typographer_bananer)).setVisibility(View.VISIBLE);
                commonViewHolder.setImageUrlByGlide(context, HttpConstance.URL + banner.getImg(), (ImageView) commonViewHolder.getView(R.id.typographer_bananer));
                typographer_item_touxiang.setVisibility(View.INVISIBLE);
                distance.setVisibility(View.INVISIBLE);
                address.setVisibility(View.INVISIBLE);
                text1.setVisibility(View.INVISIBLE);
                textt2.setVisibility(View.INVISIBLE);
                typographer_item_call.setVisibility(View.GONE);
                tText3.setVisibility(View.INVISIBLE);

        }
        return commonViewHolder.getConvertView();
    }
}
