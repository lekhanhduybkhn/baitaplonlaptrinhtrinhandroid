package com.android.manage.student.screen;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;

import com.android.manage.student.R;
import com.android.manage.student.base.ManageStudentFragment;
import com.android.manage.student.base.SlidingSherlockFragmentActivity;
import com.android.manage.student.common.FragmentControler;

public class SplashScreen extends ManageStudentFragment implements
		OnClickListener {
	String TAG = SplashScreen.class.toString();
	SlidingSherlockFragmentActivity shlFrgAtv;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		shlFrgAtv = (SlidingSherlockFragmentActivity) getSherlockActivity();
		shlFrgAtv.settingActionBar(TAG);
		if (containerView == null) {
			containerView = inflater.inflate(R.layout.splash_screen, null);
		}
		Handler handler = new Handler();
		// run a thread after 2 seconds to start the home screen
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				// make sure we close the splash screen so the user won't come
				// back when it presses back key
				ScreenManageClass newScr = new ScreenManageClass();
				FragmentControler.replaceWithPopAllBackStack(shlFrgAtv, newScr);
				return;
			}
		}, 1000);
	}

	@Override
	public void onClick(View v) {
	}
}
