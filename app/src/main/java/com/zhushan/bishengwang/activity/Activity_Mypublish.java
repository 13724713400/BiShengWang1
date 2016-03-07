package com.zhushan.bishengwang.activity;

import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.graphics.Matrix;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.zhushan.bishengwang.Fragment.Fragment_mypulish_jiu;
import com.zhushan.bishengwang.Fragment.Fragment_mypulish_qiu;
import com.zhushan.bishengwang.Fragment.Fragment_mypulish_shuai;
import com.zhushan.bishengwang.Fragment.Fragment_mypulish_zhi;
import com.zhushan.bishengwang.Iadapter.MainViewPagerAdapter;
import com.zhushan.bishengwang.Itools.BackActivity;
import com.zhushan.bishengwang.Itools.ImageUtil;
import com.zhushan.bishengwang.Itools.PopuwindowUtils;
import com.zhushan.bishengwang.Itools.UmengUtils;
import com.zhushan.bishengwang.Itools.ZxingUtils;
import com.zhushan.bishengwang.R;

import java.util.ArrayList;
import java.util.List;

public class Activity_Mypublish extends Basetivity implements View.OnClickListener {
    private UMSocialService umSocialService = UMServiceFactory.getUMSocialService("");
    private ImageView mypublish_share,mypublish_shuai_img,mypublish_qiu_img,mypublish_jiu_img,mypublish_zhi_img,mypublish_xiahuaxian;
    private ViewPager mypublishviewpaper;
    private int bitmapwidth,offset;
    private List<Fragment> listfragment;
    private TextView mypublish_titles;
    private int currIndex=0;
    private PopuwindowUtils popuwindowUtils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypublish);
        initView();
        InitImageView();

        initData();
        initListener();
    }
    private void InitImageView() {
        bitmapwidth= BitmapFactory.decodeResource(getResources(), R.mipmap.xian1).getWidth();
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int screewidth=metrics.widthPixels;
        /**
         * 图片两端的距离
         * 设置图片的起始位置
         */
        offset=(screewidth/4-bitmapwidth)/2;
        Matrix matrix = new Matrix();
        matrix.postTranslate(offset, 0);
        mypublish_xiahuaxian.setImageMatrix(matrix);
    }
    @Override
    public void initData() {
        popuwindowUtils = new PopuwindowUtils(Activity_Mypublish.this,R.layout.popuwindwo_item,false);
        BackActivity.finishActivity(Activity_Mypublish.this, R.id.mypublish_back);
        Bundle bundle = getIntent().getExtras();
        if (bundle!=null)
        {

            mypublish_titles.setText(getResources().getString(R.string.tadefabu));
        }
        listfragment = new ArrayList<Fragment>();
        Fragment_mypulish_shuai fragment_mypulish_shuai = new Fragment_mypulish_shuai();
        Fragment_mypulish_qiu fragment_mypulish_qiu = new Fragment_mypulish_qiu();
        Fragment_mypulish_jiu fragment_mypulish_jiu = new Fragment_mypulish_jiu();
        Fragment_mypulish_zhi fragment_mypulish_zhi = new Fragment_mypulish_zhi();
        listfragment.add(fragment_mypulish_shuai);
        listfragment.add(fragment_mypulish_qiu);
        listfragment.add(fragment_mypulish_jiu);
        listfragment.add(fragment_mypulish_zhi);

        mypublishviewpaper.setAdapter(new MainViewPagerAdapter(getSupportFragmentManager(), listfragment));

        mypublishviewpaper.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            int one=offset*2+bitmapwidth;
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                Animation animation = new TranslateAnimation(one*currIndex, one*position, 0, 0);
                currIndex=position;
                /**  i
                 * 设置图片的停留
                 */
                animation.setFillAfter(true);
                animation.setDuration(100);
                mypublish_xiahuaxian.startAnimation(animation);

                restore();
                switch (position)
                {
                    case 0:
                        mypublish_shuai_img.setImageResource(R.mipmap.shuai);
                        mypublish_xiahuaxian.setImageResource(R.mipmap.xian1);
                        break;
                    case 1:
                        mypublish_qiu_img.setImageResource(R.mipmap.qiu);
                        mypublish_xiahuaxian.setImageResource(R.mipmap.xian2);
                        break;
                    case 2:
                        mypublish_jiu_img.setImageResource(R.mipmap.jiu);
                        mypublish_xiahuaxian.setImageResource(R.mipmap.xian3);
                        break;
                    case 3:
                        mypublish_zhi_img.setImageResource(R.mipmap.zhi);
                        mypublish_xiahuaxian.setImageResource(R.mipmap.xian4);
                        break;

                }

            }

            private void restore() {
                mypublish_shuai_img.setImageResource(R.mipmap.shuai_offpublish);
                mypublish_qiu_img.setImageResource(R.mipmap.qiu_offpublish);
                mypublish_jiu_img.setImageResource(R.mipmap.jiu_offpublsih);
                mypublish_zhi_img.setImageResource(R.mipmap.zhi_offpublish);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void initListener() {
        mypublish_shuai_img.setOnClickListener(this);
        mypublish_qiu_img.setOnClickListener(this);
        mypublish_jiu_img.setOnClickListener(this);
        mypublish_zhi_img.setOnClickListener(this);
        mypublish_share.setOnClickListener(this);
      //  popuwindowUtils.getView(R.id.tofriends).setOnClickListener(this);
    }
    @Override
    public void initView() {
        mypublish_shuai_img = (ImageView) findViewById(R.id.mypublish_shuai_img);
        mypublish_qiu_img = (ImageView) findViewById(R.id.mypublish_qiu_img);
        mypublish_jiu_img = (ImageView) findViewById(R.id.mypublish_jiu_img);
        mypublish_zhi_img = (ImageView) findViewById(R.id.mypublish_zhi_img);
        mypublishviewpaper = (ViewPager) findViewById(R.id.mypublish_viewpaper);
        mypublish_xiahuaxian = (ImageView) findViewById(R.id.mypublish_xiahuaxian);
        mypublish_titles = (TextView) findViewById(R.id.mypublish_titles);
        mypublish_share = (ImageView) findViewById(R.id.mypublish_share);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.mypublish_share:
                popuwindowUtils.showPopuwindow(mypublish_share);
                break;

          /*  case R.id.tofriends:
                AlertDialog alertDialog3 = null;
                Bitmap bitmap = ZxingUtils.getInstance().createQRImage(Activity_Mypublish.this,"www.baidu.com",400,400, BitmapFactory.decodeResource(getResources(), R.mipmap.login_logo), ImageUtil.getSDPath()+"touxiang.png",null);
                UmengUtils.getInstance().setShareContentFriends(Activity_Mypublish.this, umSocialService,bitmap);
                UmengUtils.getInstance().showCustomUI(Activity_Mypublish.this, umSocialService, alertDialog3);
                break;*/

            case R.id.mypublish_shuai_img:

                mypublishviewpaper.setCurrentItem(0);
                break;

            case R.id.mypublish_qiu_img:
                mypublishviewpaper.setCurrentItem(1);
                break;

            case R.id.mypublish_jiu_img:
                mypublishviewpaper.setCurrentItem(2);
                break;

            case R.id.mypublish_zhi_img:
                mypublishviewpaper.setCurrentItem(3);
                break;

        }
    }
}
