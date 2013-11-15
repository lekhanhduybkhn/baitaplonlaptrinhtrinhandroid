package com.android.manage.student.object;

import android.os.Bundle;
import android.view.View;

import com.android.manage.student.base.ManageStudentFragment;
import com.android.manage.student.base.SlidingSherlockFragmentActivity;
import com.android.manage.student.common.FragmentControler;
import com.android.manage.student.screen.SplashScreen;
import com.android.manage.student.R;

public class ContentParent extends ManageStudentFragment {
	private SlidingSherlockFragmentActivity shlFrgAtv;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		shlFrgAtv = (SlidingSherlockFragmentActivity) getSherlockActivity();
		shlFrgAtv.getSupportActionBar().hide();
		if (containerView == null) {
			containerView = inflater.inflate(R.layout.content_parent, null);
		}
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		// lan nao khoi dong cung se vao man hinh splash dau tien. Sau do moi di
		// chuyen theo logic. Vi the ta dat ham nay trong onViewCreated
		FragmentControler.replaceDontAddToBackStack(shlFrgAtv,
				new SplashScreen());
	}
}
