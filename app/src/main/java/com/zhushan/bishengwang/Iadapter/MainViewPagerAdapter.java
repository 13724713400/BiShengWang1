package com.zhushan.bishengwang.Iadapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2015/11/30.
 */
public class MainViewPagerAdapter extends FragmentPagerAdapter{
    private FragmentManager fm;
    private List<Fragment> fragmentList;
    public MainViewPagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fm = fm;
        this.fragmentList = fragmentList;

    }

    @Override
    public Fragment getItem(int position) {
        // TODO Auto-generated method stub
        Fragment fragment = null;
        fragment=fragmentList.get(position);
        Bundle bundle = new Bundle();
        bundle.putString("id", ""+position);
        fragment.setArguments(bundle);
        return fragment;
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return fragmentList.size();
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // TODO Auto-generated method stub

        Fragment fragment=(Fragment) super.instantiateItem(container, position);
        fm.beginTransaction().show(fragment).commit();
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        /**
         * 隐藏要销毁的item
         */
        Fragment fragment = fragmentList.get(position);
        fm.beginTransaction().hide(fragment).commit();
    }

}

