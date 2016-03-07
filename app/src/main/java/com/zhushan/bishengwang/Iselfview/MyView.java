package com.zhushan.bishengwang.Iselfview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.OnScaleGestureListener;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewConfiguration;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.ImageView;
import android.widget.Toast;

import com.zhushan.bishengwang.R;

public class MyView extends ImageView implements OnGlobalLayoutListener,OnScaleGestureListener,OnTouchListener{
	private Matrix matrix;
	private ScaleGestureDetector scaleGestureDetector;
	private int touchSlop;
	private GestureDetector gestureDetector;
	public MyView(final Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		matrix = new Matrix();
		scaleGestureDetector = new ScaleGestureDetector(context, this);

		setOnTouchListener(this);
		touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
		gestureDetector = new GestureDetector(context, new SimpleOnGestureListener(){
			@Override
			public boolean onDoubleTap(MotionEvent e) {
				float x = e.getX();
				float y = e.getY();
				float currScale = getCurrScale();
				//缩小
				if (currScale>=doubleScale) {
					//matrix.postScale(defScale/currScale, defScale/currScale, x, y);
					post(new SlowScale(x, y, defScale));
				}else{ 
					post(new SlowScale(x, y, doubleScale));
					//matrix.postScale(doubleScale/currScale, doubleScale/currScale, x, y);
				}
				checkBorderByScale();
				setImageMatrix(matrix);
				return true;
			}

			@Override
			public boolean onSingleTapConfirmed(MotionEvent e) {

				((Activity)context).finish();
				((Activity)context).overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
				return true;

			};
		});


	}
	class SlowScale implements Runnable{
		private float x,y,targetScale;
		private float tempScale;//临时缩放比例
		private static final float BIGGER = 1.05f;
		private static final float SMALLER = 0.95f;
		public SlowScale(float x, float y, float targetScale) {
			this.x = x;
			this.y = y;
			this.targetScale = targetScale;
			float scale = getCurrScale();
			if (scale>=targetScale) {
				tempScale = SMALLER;
			}else{
				tempScale = BIGGER;
			}
		}

		@Override
		public void run() {
			matrix.postScale(tempScale, tempScale, x, y);
			checkBorderByScale();
			setImageMatrix(matrix);
			if ((getCurrScale()>targetScale&&tempScale<1.0f)||
					getCurrScale()<targetScale&&tempScale>1.0f) {
				postDelayed(this, 20);
			}else{
				matrix.postScale(targetScale/getCurrScale(), targetScale/getCurrScale(), x, y);
				checkBorderByScale();
				setImageMatrix(matrix);
			}
		}
		
	}
	public MyView(Context context, AttributeSet attrs) {
		this(context, attrs,0);
	}
	public MyView(Context context) {
		this(context,null);
	}
	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();
		getViewTreeObserver().addOnGlobalLayoutListener(this);
	}
	@SuppressWarnings("deprecation")
	@Override
	protected void onDetachedFromWindow() {
		super.onDetachedFromWindow();
		getViewTreeObserver().removeGlobalOnLayoutListener(this);
	}
	private boolean isInited;
	private int width,height,dw,dh;//with,height:控件的宽高   dw,dh图片的宽高
	private float defScale,doubleScale,maxScale;
	@Override
	public void onGlobalLayout() {
		if (!isInited) {
			width = getWidth();
			height = getHeight();
			
			Drawable drawable = getDrawable();
			if (drawable==null) {
				return;
			}
			dw = drawable.getIntrinsicWidth();
			dh = drawable.getIntrinsicHeight();
			
			float scaleX = width*1.0f/dw;
			float scaleY = height*1.0f/dh;
			float scale  = 1.0f;
			if ((dw>width&&dh>height)||(dw<width&&dh<height)) {
				scale = Math.min(scaleX, scaleY);
			}else if(dw>width||dh>height){
				scale = (scaleX>scaleY)?scaleY:scaleX;
			}
			defScale = scale;
			doubleScale = defScale*2;
			maxScale = defScale*4;
			float dx = (width-dw)/2;
			float dy = (height-dh)/2;
			matrix.postTranslate(dx, dy);
			matrix.postScale(defScale, defScale, width/2, height/2);
			setImageMatrix(matrix);
			isInited = true;
		}
	}
	
	private float getCurrScale(){
		float[] values = new float[9];
		matrix.getValues(values );
		return values[Matrix.MSCALE_X];
	}
	@Override
	public boolean onScale(ScaleGestureDetector detector) {
		float scaleFactor = detector.getScaleFactor();
		float currScale = getCurrScale();
		float scale = currScale*scaleFactor;
		//当前的缩放值比最大缩放值小  想放大     当前的缩放值大于初始缩放值  想缩小
		if ((currScale<maxScale&&scaleFactor>1.0f)||
				(currScale>defScale&&scaleFactor<1.0f)) {
			if (scale>maxScale) {
				scaleFactor = maxScale/currScale;
			}
			if (scale<defScale) {
				scaleFactor = defScale/currScale;
			}
			matrix.postScale(scaleFactor, scaleFactor, detector.getFocusX(), detector.getFocusY());
			checkBorderByScale();
			setImageMatrix(matrix);
		}
		return true;
	}
	private RectF getMatrixRectF(){
		RectF rectF = new RectF(0, 0, dw, dh);
		
		matrix.mapRect(rectF);
		return rectF;
	}
	private void checkBorderByScale() {
		RectF rectF = getMatrixRectF();
		float dx=0 ,dy = 0;
		//防止出现白边 缩小
		if (rectF.width()>=width) {
			if (rectF.left>0) {
				dx = -rectF.left;
			}
			if (rectF.right<width) {
				dx = width - rectF.right;
			}
		}
		if (rectF.height()>=height) {
			if (rectF.top>0) {
				dy = -rectF.top;
			}
			if (rectF.bottom<height) {
				dy = height-rectF.bottom;
			}
		}
		//图片居中
		if (rectF.width()<width) {
			dx = width/2f -rectF.right+rectF.width()/2f;
		}
		if (rectF.height()<height) {
			dy = height/2f-rectF.bottom+rectF.height()/2f;
		}
		matrix.postTranslate(dx, dy);
		
	}

	@Override
	public boolean onScaleBegin(ScaleGestureDetector detector) {
		// 返回true
		return true;
	}

	@Override
	public void onScaleEnd(ScaleGestureDetector detector) {
		
	}
	private int prePointerCount;
	private float preX,preY;
	private boolean isDrag;
	private boolean checkLeftAndRight,checkTopAndBottom;
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if (gestureDetector.onTouchEvent(event)) {
			return true;
		}
		scaleGestureDetector.onTouchEvent(event);
		int pointerCount = event.getPointerCount();
		//记录中心点的坐标
		float x =0,y = 0;
		for(int i = 0 ; i<pointerCount;i++){
			x+= event.getX(i);
			y+= event.getY(i);
		}
		x/=pointerCount;
		y/=pointerCount;
		if (prePointerCount!=pointerCount) {
			preX = x;
			preY = y;
		}
		prePointerCount = pointerCount;
	
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			isDrag = false;
			break;
		case MotionEvent.ACTION_MOVE:
			RectF rectF = getMatrixRectF();
			float dx = x - preX;
			float dy = y - preY;
			if (!isDrag) {
				checkLeftAndRight = checkTopAndBottom = true;
				isDrag = checkIsDrag(dx,dy);
			}
			if (isDrag) {
				if (rectF.width()<width) {
					checkLeftAndRight = false;
					dx = 0;
				}
				if (rectF.height()<height) {
					checkTopAndBottom = false;
					dy = 0 ;
				}
				matrix.postTranslate(dx, dy);
				checkBorderByTransLate();
				setImageMatrix(matrix);
			}
			preX = x;
			preY = y;
			break;
		case MotionEvent.ACTION_UP:
			prePointerCount = 0;
			break;
		}
		return true;
	}

	private void checkBorderByTransLate() {
		RectF rectF = getMatrixRectF();
		float dx=0,dy=0;
		if (checkLeftAndRight&&rectF.left>0) {
			dx = -rectF.left;
		}
		if (checkLeftAndRight&&rectF.right<width) {
			dx = width - rectF.right;
		}
		if (checkTopAndBottom&&rectF.top>0) {
			dy = -rectF.top;
		}
		
		if (checkTopAndBottom&&rectF.bottom<height) {
			dy = height-rectF.bottom;
		}
		matrix.postTranslate(dx, dy);
	}

	private boolean checkIsDrag(float dx, float dy) {
		return Math.sqrt(dx*dx+dy*dy)>touchSlop;
	}

}
