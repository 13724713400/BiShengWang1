package com.zhushan.bishengwang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.zhushan.bishengwang.Fragment.Fragment_authentication_pringtingpp;
import com.zhushan.bishengwang.Fragment.Fragment_authentication_typographer;
import com.zhushan.bishengwang.Iadapter.MainViewPagerAdapter;
import com.zhushan.bishengwang.Itools.BackActivity;
import com.zhushan.bishengwang.Itools.CameraUtils;
import com.zhushan.bishengwang.Itools.TextViewChangeState;
import com.zhushan.bishengwang.Iview.MainVu;
import com.zhushan.bishengwang.R;
import com.zhy.autolayout.utils.AutoUtils;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Activity_certificatedinfomation extends Basetivity implements View.OnClickListener {

    private ViewPager certificated_vp;
    private TextView certificated_antentication_typographer_rb,certificated_antentication_printingpeaple_rb;
    private List<Fragment> fragmentList;
    private MainVu mainVu = new MainVu();
    public Activity_certificatedinfomation() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(mainVu.initByLayout(this,R.layout.activity_certificated_information));
        initView();
        initData();
        initListener();
    }

    @Override
    public void initData() {
        BackActivity.finishActivity(this,R.id.certificated_antentication_back);
        fragmentList = new ArrayList<Fragment>();
        Fragment_authentication_pringtingpp fragment_authentication_pringtingpp = new Fragment_authentication_pringtingpp();
        Fragment_authentication_typographer fragment_authentication_typographer = new Fragment_authentication_typographer();
        fragmentList.add(fragment_authentication_pringtingpp);
        fragmentList.add(fragment_authentication_typographer);
        certificated_vp.setAdapter(new MainViewPagerAdapter(getSupportFragmentManager(), fragmentList));
        certificated_vp.setCurrentItem(0);
        certificated_antentication_printingpeaple_rb.setBackgroundColor(getResources().getColor(R.color.publishdesignrequirement_et_luyin));
        certificated_antentication_typographer_rb.setBackgroundColor(getResources().getColor(R.color.zonghepaixu_textbgtwo));
    }
    @Override
    public void initListener() {
        certificated_antentication_typographer_rb.setOnClickListener(this);
        certificated_antentication_printingpeaple_rb.setOnClickListener(this);
        certificated_vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                switch (position)
                {
                    case 0:
                        certificated_antentication_printingpeaple_rb.setBackgroundColor(getResources().getColor(R.color.publishdesignrequirement_et_luyin));
                        certificated_antentication_typographer_rb.setBackgroundColor(getResources().getColor(R.color.zonghepaixu_textbgtwo));
                        break;
                    case 1:
                        certificated_antentication_typographer_rb.setBackgroundColor(getResources().getColor(R.color.publishdesignrequirement_et_luyin));
                        certificated_antentication_printingpeaple_rb.setBackgroundColor(getResources().getColor(R.color.zonghepaixu_textbgtwo));
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

        certificated_vp = mainVu.getItemView(R.id.certificated_vp);
        certificated_antentication_printingpeaple_rb = mainVu.getItemView(R.id.certificated_antentication_printingpeaple_rb);
        certificated_antentication_typographer_rb = mainVu.getItemView(R.id.certificated_antentication_typographer_rb);


    }


    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.certificated_antentication_printingpeaple_rb:

                certificated_vp.setCurrentItem(0);

                break;
            case R.id.certificated_antentication_typographer_rb:
                certificated_vp.setCurrentItem(1);
                break;

        }
    }
}
