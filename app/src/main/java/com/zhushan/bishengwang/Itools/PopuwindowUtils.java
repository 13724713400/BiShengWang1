package com.zhushan.bishengwang.Itools;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.zhy.autolayout.utils.AutoUtils;

public class PopuwindowUtils {
	private  View contenview;
	private  PopupWindow popupWindow;
	private SparseArray<View> views;
	public PopuwindowUtils(Activity activity,int LayoutId,boolean isMathparent)
	{

		LayoutInflater layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		contenview = layoutInflater.inflate(LayoutId, null);
		AutoUtils.autoSize(contenview);
		views  = new SparseArray<View>();
		if(isMathparent) {
			popupWindow = new PopupWindow(contenview, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
		}else{
			popupWindow = new PopupWindow(contenview, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
		}
	}
	public  void showPopuwindow(View downview)
	{

		ColorDrawable colorDrawable = new ColorDrawable(0xb0000000);
		popupWindow.setBackgroundDrawable(colorDrawable);
		popupWindow.setOutsideTouchable(true);
		popupWindow.showAsDropDown(downview);
		//popupWindow.showAtLocation(activity.findViewById(UnderWifgetId),0,0,0);
	}
	public <T extends View> T getView(int id){
		View view = views.get(id);
		if (view ==null) {
			view = contenview.findViewById(id);
			views.put(id, view);
		}
		return (T) view;
	}
	public boolean isShowing()
	{
		return popupWindow.isShowing();
	}
	public void popuwindwodismiss()
	{
		popupWindow.dismiss();
	}
}
