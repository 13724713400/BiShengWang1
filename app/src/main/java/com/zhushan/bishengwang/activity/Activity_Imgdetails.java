package com.zhushan.bishengwang.activity;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.zhushan.bishengwang.Constance.HttpConstance;
import com.zhushan.bishengwang.Entry.DetailsEntry;
import com.zhushan.bishengwang.Entry.GalleryDataTwo;
import com.zhushan.bishengwang.Entry.Img;
import com.zhushan.bishengwang.Entry.ImgData;
import com.zhushan.bishengwang.Iadapter.ViewPaperAdapter;
import com.zhushan.bishengwang.Iadapter.ViewPaperAdapterThree;
import com.zhushan.bishengwang.Iadapter.ViewPaperAdapterTwo;
import com.zhushan.bishengwang.R;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2016/1/7.
 */
public class Activity_Imgdetails extends Basetivity{

    private List<View> imageViewList;
    private ViewPager detailes_item_viewpaper;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_imgitem);
        initView();
        initData();
        initListener();

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    @Override
    public void initView() {
        detailes_item_viewpaper = (ViewPager) findViewById(R.id.detailes_item_viewpaper);
    }

   /* public void onEventMainThread(List<Img> imgList)
    {
        Log.i("www","传递的图片"+imgList);
        if (imgList!=null)
        {
            detailes_item_viewpaper.setAdapter(new ViewPaperAdapterTwo(this,imgList));
        }
    }*/

    @Override
    public void initData() {
        Bundle bundle = getIntent().getExtras();
        int index =  bundle.getInt("index");

        if (bundle.getParcelableArrayList("detailsEntry2")!=null)
        {
            ArrayList<ImgData> detailsEntry =  bundle.getParcelableArrayList("detailsEntry2");
            detailes_item_viewpaper.setAdapter(new ViewPaperAdapter(this,detailsEntry));
        }

        if (bundle.getParcelableArrayList("HompageItemAdapter")!=null)
        {
            ArrayList<Img> imgList = bundle.getParcelableArrayList("HompageItemAdapter");
            Log.i("www","imgList"+imgList);
            detailes_item_viewpaper.setAdapter(new ViewPaperAdapterTwo(this,imgList));
        }

        if (bundle.getParcelableArrayList("Activity_MyPhoto")!=null)
        {
            ArrayList<GalleryDataTwo> imgList = bundle.getParcelableArrayList("Activity_MyPhoto");
            detailes_item_viewpaper.setAdapter(new ViewPaperAdapterThree(this,imgList));
        }

        detailes_item_viewpaper.setCurrentItem(index);

    }

    @Override
    public void initListener() {

    }
}
