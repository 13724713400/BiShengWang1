package com.zhushan.bishengwang.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.zhushan.bishengwang.Iselfview.MyView2;
import com.zhushan.bishengwang.Itools.DepthPageTransformer;
import com.zhushan.bishengwang.MainActivity;
import com.zhushan.bishengwang.R;


public class GuidActivity extends Basetivity {
	private ViewPager viewPager;
	private List<View> list;
	private MyAdapter adapter;
	private LinearLayout layout;
	private MyView2[] checkBoxs = new MyView2[3];
	private Button StartFamilyParty;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main2);
		initView();
		layout=(LinearLayout) findViewById(R.id.lineatlayout);
		init();
		viewPager=(ViewPager) findViewById(R.id.MyviewPager);
		adapter = new MyAdapter();
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {

				checkBoxs[position].setIsopen(true);
				for(int i=0;i<checkBoxs.length;i++)
				{
					if(i!=position)
					{
						checkBoxs[i].setIsopen(false);
					}
				}

				if(position==2)
				{
					StartFamilyParty.setVisibility(View.VISIBLE);

					Animation translateAnimation = AnimationUtils.loadAnimation(GuidActivity.this, R.anim.guidstartanimation);	
					translateAnimation.setInterpolator(new OvershootInterpolator());
					StartFamilyParty.setAnimation(translateAnimation);
					translateAnimation.setAnimationListener(new AnimationListener() {

						@Override
						public void onAnimationStart(Animation animation) {
							// TODO Auto-generated method stub

						}

						@Override
						public void onAnimationRepeat(Animation animation) {
							// TODO Auto-generated method stub

						}

						@Override
						public void onAnimationEnd(Animation animation) {	     
							int left = StartFamilyParty.getLeft();
							int top = StartFamilyParty.getTop();
							int width = StartFamilyParty.getWidth();
							int right = StartFamilyParty.getRight();
							int height = StartFamilyParty.getHeight();
							int bottom = StartFamilyParty.getBottom();
							StartFamilyParty.clearAnimation();
							StartFamilyParty.layout(left, top-100, right, bottom-100);
							StartFamilyParty.setOnClickListener(new OnClickListener() {

								@Override
								public void onClick(View v) {					
									setGuid();
									goHome();
									Toast.makeText(GuidActivity.this, "成功进入主界面", Toast.LENGTH_LONG).show();
								}
							});

						}
					});

				}else{
					StartFamilyParty.clearAnimation();
					StartFamilyParty.setVisibility(View.GONE);
				}

			}


			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {


			}

			@Override
			public void onPageScrollStateChanged(int arg0) {


			}
		});

		viewPager.setAdapter(adapter);
		viewPager.setPageTransformer(true,new DepthPageTransformer());
		checkBoxs[0].setIsopen(true);

	}
	@Override
	public void initView() {

		StartFamilyParty = (Button) findViewById(R.id.StartFamilyParty);
		StartFamilyParty.setVisibility(View.GONE);
	}

	@Override
	public void initData() {

	}

	@Override
	public void initListener() {

	}

	private void init() {
		int i=0;
		list = new ArrayList<View>();
		//View v=LayoutInflater.from(this).inflate(R.layout.loginitem, null);
		int[] images = new int[]{R.mipmap.one,R.mipmap.two,R.mipmap.three};
		for(i=0;i<3;i++)
		{

			ImageView imageView = new ImageView(this);
			checkBoxs[i] = new MyView2(this);
			layout.addView(checkBoxs[i]);
			imageView.setBackgroundResource(images[i]);
			imageView.setScaleType(ScaleType.CENTER_CROP);	
			list.add(imageView);



		}





	}



	private void goHome() {
		Intent intent = new Intent(GuidActivity.this,MainActivity.class);
		startActivity(intent);
		GuidActivity.this.finish();
	}

	private void setGuid() {
		SharedPreferences preferences=getSharedPreferences("first_pref", MODE_PRIVATE);
		Editor editor=preferences.edit();
		editor.putBoolean("key", true);
		editor.commit();
	}

	class MyAdapter extends PagerAdapter
	{



		@Override
		public Object instantiateItem(ViewGroup container, int position) {

			container.addView(list.get(position));
			return list.get(position);

		}
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {

			container.removeView(list.get(position));

		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}
		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {

			return arg0==arg1;
		}

	}
}
