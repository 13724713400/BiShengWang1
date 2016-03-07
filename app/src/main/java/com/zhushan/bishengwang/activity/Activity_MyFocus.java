package com.zhushan.bishengwang.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.zhushan.bishengwang.Fragment.Fragment_MyFocusInfo;
import com.zhushan.bishengwang.Fragment.Fragment_MyFocuspeaple;
import com.zhushan.bishengwang.Fragment.Fragment_MyFocustypographer;
import com.zhushan.bishengwang.Fragment.Fragment_authentication_pringtingpp;
import com.zhushan.bishengwang.Fragment.Fragment_authentication_typographer;
import com.zhushan.bishengwang.Iadapter.MainViewPagerAdapter;
import com.zhushan.bishengwang.Itools.BackActivity;
import com.zhushan.bishengwang.Iview.MainVu;
import com.zhushan.bishengwang.R;

import java.util.ArrayList;
import java.util.List;

public class Activity_MyFocus extends Basetivity implements View.OnClickListener {

    private ViewPager myfocus_info_vp;
    private TextView myfocus_info,myfocus_peaple,myfocus_typographer;
    private List<Fragment> fragmentList;
    private MainVu mainVu = new MainVu();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(mainVu.initByLayout(this,R.layout.activity_myfocus_information));
        initView();
        initData();
        initListener();
    }

    @Override
    public void initData() {
        BackActivity.finishActivity(this, R.id.myfocus_info_back);
        fragmentList = new ArrayList<Fragment>();

        Fragment_MyFocusInfo fragment_myFocusInfo = new Fragment_MyFocusInfo();
        Fragment_MyFocuspeaple fragment_myFocuspeaple = new Fragment_MyFocuspeaple();
        Fragment_MyFocustypographer fragment_myFocustypographer = new Fragment_MyFocustypographer();
        fragmentList.add(fragment_myFocusInfo);
        fragmentList.add(fragment_myFocuspeaple);
        fragmentList.add(fragment_myFocustypographer);
        myfocus_info_vp.setAdapter(new MainViewPagerAdapter(getSupportFragmentManager(), fragmentList));
        myfocus_info_vp.setCurrentItem(0);
        myfocus_info.setBackgroundColor(getResources().getColor(R.color.publishdesignrequirement_et_luyin));
        myfocus_peaple.setBackgroundColor(getResources().getColor(R.color.zonghepaixu_textbgtwo));
        myfocus_typographer.setBackgroundColor(getResources().getColor(R.color.zonghepaixu_textbgtwo));
    }
    @Override
    public void initListener() {
        myfocus_info.setOnClickListener(this);
        myfocus_peaple.setOnClickListener(this);
        myfocus_typographer.setOnClickListener(this);
        myfocus_info_vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                switch (position)
                {
                    case 0:
                        myfocus_info.setBackgroundColor(getResources().getColor(R.color.publishdesignrequirement_et_luyin));
                        myfocus_peaple.setBackgroundColor(getResources().getColor(R.color.zonghepaixu_textbgtwo));
                        myfocus_typographer.setBackgroundColor(getResources().getColor(R.color.zonghepaixu_textbgtwo));
                        break;
                    case 1:
                        myfocus_info.setBackgroundColor(getResources().getColor(R.color.zonghepaixu_textbgtwo));
                        myfocus_peaple.setBackgroundColor(getResources().getColor(R.color.publishdesignrequirement_et_luyin));
                        myfocus_typographer.setBackgroundColor(getResources().getColor(R.color.zonghepaixu_textbgtwo));
                        break;
                    case 2:
                        myfocus_info.setBackgroundColor(getResources().getColor(R.color.zonghepaixu_textbgtwo));
                        myfocus_peaple.setBackgroundColor(getResources().getColor(R.color.zonghepaixu_textbgtwo));
                        myfocus_typographer.setBackgroundColor(getResources().getColor(R.color.publishdesignrequirement_et_luyin));
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    @Override
    public void initView() {

        myfocus_info_vp = mainVu.getItemView(R.id.myfocus_info_vp);
        myfocus_info = mainVu.getItemView(R.id.myfocus_info);
        myfocus_peaple = mainVu.getItemView(R.id.myfocus_pealple);
        myfocus_typographer = mainVu.getItemView(R.id.myfocus_typographer);

    }


    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.myfocus_info:

                myfocus_info_vp.setCurrentItem(0);

                break;
            case R.id.myfocus_pealple:
                myfocus_info_vp.setCurrentItem(1);
                break;

            case R.id.myfocus_typographer:
                myfocus_info_vp.setCurrentItem(2);
                break;

        }
    }
}
