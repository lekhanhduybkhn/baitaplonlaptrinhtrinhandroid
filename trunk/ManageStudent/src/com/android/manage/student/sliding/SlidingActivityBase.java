package com.android.manage.student.sliding;

import android.view.View;
import android.view.ViewGroup.LayoutParams;

public interface SlidingActivityBase {
	
	public void setBehindContentView(View v, LayoutParams p);

	public SlidingMenu getSlidingMenu();
		
	public void toggle();
	
	public void showAbove();
	
	public void showBehind();
	
}
