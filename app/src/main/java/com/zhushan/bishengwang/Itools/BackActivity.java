package com.zhushan.bishengwang.Itools;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import com.zhushan.bishengwang.R;
public class BackActivity {
	public static void finishActivity(final Activity activity,int id)	
	{

		ImageView imageView = (ImageView) activity.findViewById(id);
		imageView.setBackgroundResource(R.drawable.back_bg);
		imageView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				activity.finish();
			}
		});
	}

}
