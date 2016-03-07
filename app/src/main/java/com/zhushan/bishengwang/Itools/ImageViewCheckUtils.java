package com.zhushan.bishengwang.Itools;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.zhushan.bishengwang.Constance.SharePreferenceConstance;
import com.zhushan.bishengwang.Constance.XioamishuConstance;
import com.zhushan.bishengwang.R;

/**
 * Created by Administrator on 2015/12/22.
 */
public class ImageViewCheckUtils {

    private static ImageViewCheckUtils imageViewCheckUtils;
    private ImageViewCheckUtils(){}
    public static ImageViewCheckUtils getInstance()
    {
        if (imageViewCheckUtils==null)
        {
            synchronized (ImageViewCheckUtils.class)
            {
                if (imageViewCheckUtils==null)
                {
                    imageViewCheckUtils = new ImageViewCheckUtils();

                }

            }

        }
        return imageViewCheckUtils;
    }


    public void MainFirstVisibly(final Activity context,final ImageView...img)
    {
                img[0].setVisibility(View.VISIBLE);
                Animation animation =  AnimationUtils.loadAnimation(context, R.anim.main_tag_anim);
                img[0].startAnimation(animation);
                ImageUtil.setImageByPath(img[0], SharePreferenceUtils.getInstance(context, XioamishuConstance.XIOAMISHUTB).getSharePreferenceString(XioamishuConstance.XIAOMISHUIMGONE, null), ImageUtil.ISCIRCLE);
                img[1].setVisibility(View.GONE);
                img[2].setVisibility(View.GONE);
                img[3].setVisibility(View.GONE);

    }

    public void MainSecondVisibly(final Activity context,final ImageView...img)
    {
                img[1].setVisibility(View.VISIBLE);
                Animation animation =  AnimationUtils.loadAnimation(context, R.anim.main_tag_anim);
                img[1].startAnimation(animation);
                ImageUtil.setImageByPath(img[1], SharePreferenceUtils.getInstance(context, XioamishuConstance.XIOAMISHUTB).getSharePreferenceString(XioamishuConstance.XIAOMISHUIMTWO, null), ImageUtil.ISCIRCLE);
                img[0].setVisibility(View.GONE);
                img[2].setVisibility(View.GONE);
                img[3].setVisibility(View.GONE);
    }

    public void MainThreeVisibly(final Activity context,final ImageView...img)
    {
                ImageUtil.setImageByPath(img[2], SharePreferenceUtils.getInstance(context, XioamishuConstance.XIOAMISHUTB).getSharePreferenceString(XioamishuConstance.XIAOMISHUIMGTHREE, null), ImageUtil.ISCIRCLE);
        Animation animation =  AnimationUtils.loadAnimation(context, R.anim.main_tag_anim);
        img[2].startAnimation(animation);
                img[2].setVisibility(View.VISIBLE);
                img[1].setVisibility(View.GONE);
                img[0].setVisibility(View.GONE);
                img[3].setVisibility(View.GONE);
    }

    public void MainFoureVisibly(final Activity context,final ImageView...img)
    {
                img[3].setVisibility(View.VISIBLE);
                ImageUtil.setImageByPath(img[3], SharePreferenceUtils.getInstance(context, XioamishuConstance.XIOAMISHUTB).getSharePreferenceString(XioamishuConstance.XIAOMISHUIMFORE, null), ImageUtil.ISCIRCLE);
                Animation animation =  AnimationUtils.loadAnimation(context, R.anim.main_tag_anim);
                img[3].startAnimation(animation);
                img[1].setVisibility(View.GONE);
                img[2].setVisibility(View.GONE);
                img[0].setVisibility(View.GONE);
    }


    public int ShuaiIscheck(final Context context,final ImageView...img)
    {
        img[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img[0].setImageResource(R.mipmap.shuai_on);
                img[1].setImageResource(R.mipmap.qiu_off);
                img[2].setImageResource(R.mipmap.jiu_off);
                img[3].setImageResource(R.mipmap.zhi_off);
           SharePreferenceUtils.getInstance(context, SharePreferenceConstance.USERNAME).CreateSharePreference(SharePreferenceConstance.CATEID,2);
            }
        });
        return  2;
    }

    public int  qiuIscheck(final Context context,final ImageView...img)
    {
        img[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img[0].setImageResource(R.mipmap.shuai_off);
                img[1].setImageResource(R.mipmap.qiu_on);
                img[2].setImageResource(R.mipmap.jiu_off);
                img[3].setImageResource(R.mipmap.zhi_off);
                SharePreferenceUtils.getInstance(context, SharePreferenceConstance.USERNAME).CreateSharePreference(SharePreferenceConstance.CATEID, 1);
            }
        });


        return  1;
    }

    public int  JiuIscheck(final Context context,final ImageView...img)
    {
        img[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img[0].setImageResource(R.mipmap.shuai_off);
                img[1].setImageResource(R.mipmap.qiu_off);
                img[2].setImageResource(R.mipmap.jiu_on);
                img[3].setImageResource(R.mipmap.zhi_off);
                SharePreferenceUtils.getInstance(context, SharePreferenceConstance.USERNAME).CreateSharePreference(SharePreferenceConstance.CATEID, 3);
            }
        });

        return  3;
    }

    public int  ZhiIscheck(final Context context,final ImageView...img)
    {
        img[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img[0].setImageResource(R.mipmap.shuai_off);
                img[1].setImageResource(R.mipmap.qiu_off);
                img[2].setImageResource(R.mipmap.jiu_off);
                img[3].setImageResource(R.mipmap.zhi_on);
                SharePreferenceUtils.getInstance(context, SharePreferenceConstance.USERNAME).CreateSharePreference(SharePreferenceConstance.CATEID, 4);
            }
        });

        return  4;
    }


}
