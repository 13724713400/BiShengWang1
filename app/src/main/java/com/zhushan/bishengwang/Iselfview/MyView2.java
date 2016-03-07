package com.zhushan.bishengwang.Iselfview;



import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;

import android.view.View;
import android.view.View.OnClickListener;

import com.zhushan.bishengwang.R;

public class MyView2 extends View{
	private Paint paint,paint2;
	private boolean isopen;
	public void setIsopen(boolean isopen) {
		this.isopen = isopen;
		if(isopen)
		{
	      isopen=!isopen;
	      paint2.setColor(getResources().getColor(R.color.order_paymentStatus));
		}else{
		 isopen=!isopen;
		 paint2.setColor(getResources().getColor(R.color.pointunchecked));
		}
		invalidate();
	}
	
	public MyView2(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public MyView2(Context context) {
		this(context,null);
		// TODO Auto-generated constructor stub
	}

	public MyView2(Context context, AttributeSet attrs) {
		this(context, attrs,0);
		init();	
		}
	

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		setMeasuredDimension(30, 30);
	}
	@Override
	public void draw(Canvas canvas) {

		canvas.drawCircle(15, 15, 6, paint2);
		
	}
	private void init() {
		
		paint2 = new Paint();
		paint2.setAntiAlias(true);
		paint2.setStyle(Style.FILL);
		paint2.setColor(getResources().getColor(R.color.pointunchecked));
	}
	  
	

}
