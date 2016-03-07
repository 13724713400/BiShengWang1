package com.zhushan.bishengwang.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.zhushan.bishengwang.Iview.MainVu;
import com.zhushan.bishengwang.R;


/**
 * Created by Administrator on 2015/12/1.
 */
public class Fragment_detailes_shuai extends Fragment {

    private MainVu mainVu = new MainVu();
    private ImageView detailes_item_shuai,detailes_item_img1,detailes_item_img2,detailes_item_imgethree,detailes_item_recorder;
    private TextView detailes_item_titles,detailes_item_content,detailes_item_date,detailes_item_address,detailes_item_guanzhutext,detailes_item_liulan,detailes_item_liuyan,detailes_item_recorder_txt;
    private ListView detailes_item_liuyan_listView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       // View view =  inflater.inflate(R.layout.detailes_iten_listview,container,false);
       // AutoLayout.getInstance().auto(getActivity());
        mainVu.initByLayout(getActivity(),R.layout.detailes_iten_listview);
        initView();
        return mainVu.getView();
    }

    private void initView() {

        detailes_item_shuai = mainVu.getItemView(R.id.detailes_item_shuai);
        detailes_item_date = mainVu.getItemView(R.id.detailes_item_date);
        detailes_item_titles = mainVu.getItemView(R.id.detailes_item_titles);
        detailes_item_content =  mainVu.getItemView(R.id.detailes_item_content);
        detailes_item_img1 =  mainVu.getItemView(R.id.detailes_item_img1);
        detailes_item_img2 = mainVu.getItemView(R.id.detailes_item_img2);
        detailes_item_imgethree =  mainVu.getItemView(R.id.detailes_item_imgethree);
        detailes_item_recorder = mainVu.getItemView(R.id.detailes_item_recorder);
        detailes_item_recorder_txt = mainVu.getItemView(R.id.detailes_item_recorder_txt);
        detailes_item_address = mainVu.getItemView(R.id.detailes_item_address);
        detailes_item_guanzhutext =  mainVu.getItemView(R.id.detailes_item_guanzhutext);
        detailes_item_liulan = mainVu.getItemView(R.id.detailes_item_liulan);
        detailes_item_liuyan = mainVu.getItemView(R.id.detailes_item_liuyan);
        detailes_item_liuyan_listView = mainVu.getItemView(R.id.detailes_item_liuyan_listView);

    }


}
