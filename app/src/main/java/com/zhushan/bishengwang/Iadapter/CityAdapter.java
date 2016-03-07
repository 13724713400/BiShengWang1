package com.zhushan.bishengwang.Iadapter;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.zhushan.bishengwang.Entry.CityeData;
import com.zhushan.bishengwang.Entry.ProvinceData;
import com.zhushan.bishengwang.Itools.IntentUtils;
import com.zhushan.bishengwang.MainActivity;
import com.zhushan.bishengwang.R;

import java.util.List;

/**
 * Created by Administrator on 2016/1/25.
 */
public class CityAdapter extends CommonBaseAdapter<CityeData> {
    private String flag;
    public CityAdapter(String flag,Context context, List datas, int resourceId) {
        super(context, datas, resourceId);
        this.flag = flag;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        CommonViewHolder commonViewHolder = CommonViewHolder.getInstance(context,convertView,parent,resourceId);
        commonViewHolder.setText(R.id.item_province,datas.get(position).getCity());
        commonViewHolder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (flag)
                {
                    case "Fragment_homepage":
                        Bundle bundle = new Bundle();
                        bundle.putString("Fragment_homepage",datas.get(position).getCity());
                        bundle.putString("flag",flag);
                        IntentUtils.getInstance().startToAnoterActivity(context, MainActivity.class, bundle);
                        break;

                    case "Fragment_pringtingpeaple":
                        Bundle bundle2 = new Bundle();
                        bundle2.putString("Fragment_pringtingpeaple",datas.get(position).getCity());
                        bundle2.putString("flag",flag);
                        IntentUtils.getInstance().startToAnoterActivity(context, MainActivity.class, bundle2);
                        break;

                    case "Fragment_typographer":
                        Bundle bundle3 = new Bundle();
                        bundle3.putString("Fragment_typographer",datas.get(position).getCity());
                        bundle3.putString("flag",flag);
                        IntentUtils.getInstance().startToAnoterActivity(context, MainActivity.class, bundle3);
                        break;


                }

            }
        });
        return commonViewHolder.getConvertView();
    }
}
