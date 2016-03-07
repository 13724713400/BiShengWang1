package com.zhushan.bishengwang.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app. FragmentStatePagerAdapter;
/**
 * Created by Administrator on 2016/2/2.
 */
public class MyPagerAdapter1 extends FragmentStatePagerAdapter {
    protected static final String[] SUB_FRAGMENT = new String[] { "Fragment1_1", "Fragment1_2",
            "Fragment1_3" }; // 对应于每个大Fragment的小Fragment

    private int mCount = SUB_FRAGMENT.length;

    public MyPagerAdapter1(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (0 == position) {
            return new Fragment_entire();
        } else if (1 == position) {
            return new Fragment_flock();
        } else if (2 == position) {
            return new BlankFragment();
        } else {
            System.out.println("创建子Fragment1_" + position + "失败");
            return null;
        }
    }

    @Override
    public int getCount() {
        return mCount;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return SUB_FRAGMENT[position % mCount];
    }
}