package com.zhushan.bishengwang.Itools;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;

import com.squareup.picasso.Transformation;

public class CircleTransform implements Transformation { 
	private int RoundPx;
	public CircleTransform(int RoundPx)
	{
		this.RoundPx = RoundPx;
		
	}
	@Override  
	public Bitmap transform(Bitmap source) {  
	/*int size = Math.min(source.getWidth(), source.getHeight());  
	   
	int x = (source.getWidth() - size) / 2;  
	int y = (source.getHeight() - size) / 2;  
	  
	Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, size, size);  
	if (squaredBitmap != source) {  
	source.recycle();  
	}  */
/*	  
	Bitmap bitmap = Bitmap.createBitmap(size, size, source.getConfig());  
	  
	Canvas canvas = new Canvas(bitmap);  
	Paint paint = new Paint();  
	BitmapShader shader = new BitmapShader(squaredBitmap,  
	BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);  
	paint.setShader(shader);  
	paint.setAntiAlias(true);  
	  
	float r = size / 2f;  
//	canvas.drawCircle(r, r, r, paint);
	 final RectF rectF = new RectF(new Rect(0, 0, source.getWidth(),
   		  source.getHeight()));
	final float roundPx = 44;
	canvas.drawRoundRect(rectF, roundPx, roundPx, paint);*/
	
	  Bitmap bitmap = Bitmap.createBitmap(source.getWidth(),
			  source.getHeight(),source.getConfig());
      Canvas canvas = new Canvas(bitmap);                
      final Paint paint = new Paint();
      final Rect rect = new Rect(0, 0, source.getWidth(),
    		  source.getHeight());       
      final RectF rectF = new RectF(new Rect(0, 0, source.getWidth(),
    		  source.getHeight()));
      
      paint.setAntiAlias(true);
      canvas.drawARGB(0, 0, 0, 0);
      paint.setColor(Color.BLACK);       
      canvas.drawRoundRect(rectF, RoundPx, RoundPx, paint);
      paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));            
      final Rect src = new Rect(0, 0, source.getWidth(),
    		  source.getHeight());       
      canvas.drawBitmap(source, src, rect, paint);  
      source.recycle();
	return bitmap; 

	}

	@Override
	public String key() {
		// TODO Auto-generated method stub
		return "circle";
	}


}
