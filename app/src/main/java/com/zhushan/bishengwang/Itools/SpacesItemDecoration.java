package com.zhushan.bishengwang.Itools;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.State;
import android.view.View;

public class SpacesItemDecoration extends RecyclerView.ItemDecoration {

	private int space;

	public SpacesItemDecoration(int space)
	{
		this.space = space;
	}
	@Override
	public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
			State state) {
		outRect.left=space;
		outRect.right=space;
		outRect.bottom=space;
		if(parent.getChildAdapterPosition(view)==0){
			outRect.top=space;
		}
	}
}
