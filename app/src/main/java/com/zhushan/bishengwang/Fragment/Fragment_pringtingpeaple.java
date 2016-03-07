package com.zhushan.bishengwang.Fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
//import android.view.animation.Animation;
//import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.facebook.controller.utils.ToastUtil;
import com.zhushan.bishengwang.Entry.PrintingPeapleData;
import com.zhushan.bishengwang.Entry.ShuaiXuanEntry;
import com.zhushan.bishengwang.Iadapter.PringtingPeapleAdapter;
import com.zhushan.bishengwang.Itools.PopuwindowUtils;
import com.zhushan.bishengwang.Iview.MainVu;
import com.zhushan.bishengwang.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.animation.TranslateAnimation;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup.LayoutParams;
import android.view.View.OnClickListener;
import android.widget.Toast;

/**
 * Created by Administrator on 2015/11/28.
 */
public class Fragment_pringtingpeaple extends Fragment {
	private ArrayList<Fragment> fragmentsList;


	private Fragment fragment1,fragment3,fragment2,fragmentById;
	private FragmentTransaction trasection;

	private int position_one;
	private int position_two;
	public int i = 0;
	private static final String TAG = "MainActivity";
//	private ImageView ivBottomLine;

	private int bottomLineWidth;
	private int offset = 0;
	private int position_three;
	TextView iv_bottom_line, iv_bottom_line1, iv_bottom_line2;
	TextView PrintPeople, homepage_quanbu;/* flock*/;
	private int currIndex = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_printingpeaple, container, false);

		InitTextView(v);
		return v;
	}

	public void InitTextView(View v) {
		//	mPager = (ViewPager) v.findViewById(R.id.vPager);
	/*	trasection =
				getChildFragmentManager().beginTransaction();*/
		fragment1 = new BlankFragment();
		fragment2 = new Fragment_entire();
		fragment3 = new Fragment_flock();
		Fragment[] fragment =new Fragment[]{fragment1,fragment2,fragment3};

	//	replaceFragment(fragment1);
		getChildFragmentManager().beginTransaction().add(R.id.vPager, fragment1).commit();
		iv_bottom_line = (TextView) v.findViewById(R.id.iv_bottom_line);
		iv_bottom_line1 = (TextView) v.findViewById(R.id.iv_bottom_line1);
		iv_bottom_line2 = (TextView) v.findViewById(R.id.iv_bottom_line2);

		iv_bottom_line.setBackgroundResource(R.color.main_hei);
		iv_bottom_line1.setBackgroundResource(R.color.textcoloryellow);
		iv_bottom_line2.setBackgroundResource(R.color.textcoloryellow);
		PrintPeople = (TextView) v.findViewById(R.id.PrintPeople);
		PrintPeople.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				iv_bottom_line.setBackgroundResource(R.color.main_hei);
				iv_bottom_line1.setBackgroundResource(R.color.textcoloryellow);
				iv_bottom_line2.setBackgroundResource(R.color.textcoloryellow);


			/*	trasection.remove(fragment1);*/
			/*	replaceFragment(fragment1);*/
				if(fragment2.isVisible()){
					switchContent(fragment2,fragment1);
					}else if(fragment3.isVisible()){
					switchContent(fragment3,fragment1);
				}else{
					switchContent(fragment1,fragment1);
				}
				/*getChildFragmentManager()
						.beginTransaction()
						.replace(R.id.vPager, fragment1)
						.commit();*/
				/*getChildFragmentManager().beginTransaction().hide(fragment1).hide(fragment2).hide(fragment3)
						.show(fragment1).commit();*/
			}
		});
		homepage_quanbu = (TextView) v.findViewById(R.id.homepage_quanbu);
		homepage_quanbu.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				iv_bottom_line1.setBackgroundResource(R.color.main_hei);
				iv_bottom_line.setBackgroundResource(R.color.textcoloryellow);
				iv_bottom_line2.setBackgroundResource(R.color.textcoloryellow);
				/*transaction = getChildFragmentManager().beginTransaction();
				transaction.add(R.id.vPager, fragment2).commit();*/
				/*getChildFragmentManager()
						.beginTransaction()
						.replace(R.id.vPager, fragment2)
						.commit();*/
				if(fragment1.isVisible()){
					switchContent(fragment1,fragment2);
				}else if(fragment3.isVisible()){
					switchContent(fragment3,fragment2);
				}else{
					switchContent(fragment2,fragment2);
				}
			/*	trasection.remove(fragment1);
				replaceFragment(fragment2);*/
			}
		});
	//	flock = (TextView) v.findViewById(R.id.flock);
	/*	flock.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(getActivity(), "你点击了群", Toast.LENGTH_SHORT).show();
				iv_bottom_line.setBackgroundResource(R.color.textcoloryellow);
				iv_bottom_line1.setBackgroundResource(R.color.textcoloryellow);
				iv_bottom_line2.setBackgroundResource(R.color.main_hei);
		*//*		getChildFragmentManager()
						.beginTransaction()
						.replace(R.id.vPager, fragment3)
						.commit();*//*
				if(fragment1.isVisible()){
					switchContent(fragment2,fragment3);
				}else if(fragment2.isVisible()){
					switchContent(fragment1,fragment3);
				}else{
					switchContent(fragment3,fragment3);
				}

			*//*	transaction = getChildFragmentManager().beginTransaction();
				transaction.add(R.id.vPager, fragment1).commit();*//*
				*//*trasection.remove(fragment1);
				replaceFragment(fragment3);*//*
				*//*getChildFragmentManager().beginTransaction().add(R.id.vPager, fragment3).hide(fragment1).hide(fragment2).hide(fragment3)
						.show(fragment3).commit();*//*
			}
		});*/
	}
	public void switchContent(Fragment from, Fragment to) {
		//Fragment[] fragment =new Fragment[]{fragment1,fragment2,fragment3};
	 fragmentById= getFragmentManager().findFragmentById(R.id.vPager);
		FragmentManager mFragmentMan =getChildFragmentManager();

		if (fragmentById != to) {
			fragmentById = to;
			FragmentTransaction transaction = mFragmentMan.beginTransaction();
			if (!to.isAdded()) {    // 先判断是否被add过
				transaction.hide(from).add(R.id.vPager, to).commit(); // 隐藏当前的fragment，add下一个到Activity中
			} else {
				transaction.hide(from).show(to).commit(); // 隐藏当前的fragment，显示下一个
			}
		}
	}
}
