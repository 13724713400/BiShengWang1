package com.zhushan.bishengwang.Iadapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zhushan.bishengwang.Constance.HttpConstance;
import com.zhushan.bishengwang.Entry.ImgData;
import com.zhushan.bishengwang.R;

import java.util.List;

/**
 * Created by Administrator on 2016/1/7.
 */
public class ViewPaperAdapter extends PagerAdapter {

    private List<ImgData> imageViewList;
    private Context context;

    public ViewPaperAdapter( Context context,List<ImgData> imageViewList)
    {
        this.imageViewList = imageViewList;
        this.context = context;
        Log.i("www","tupain"+imageViewList.size());

    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        Log.i("www","进来222");
        View view2 =  LayoutInflater.from(context).inflate(R.layout.details_imgitemtwo,null);
        ImageView imageView = (ImageView) view2.findViewById(R.id.item_img);
        imageView.setScaleType(ImageView.ScaleType.MATRIX);
        Glide.with(context).load(HttpConstance.URL + imageViewList.get(position).getImg())
               // .placeholder(R.mipmap.logotwo)
                .fitCenter()
                .into(imageView);

        container.addView(view2);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity)context).finish();
            }
        });
        return   view2;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }

    @Override
    public int getCount() {
        return imageViewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
}
