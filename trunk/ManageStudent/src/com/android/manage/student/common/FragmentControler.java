package com.android.manage.student.common;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentManager.BackStackEntry;

import com.actionbarsherlock.app.SherlockFragment;
import com.android.manage.student.base.ManageStudentFragment;
import com.android.manage.student.base.SlidingSherlockFragmentActivity;
import com.android.manage.student.R;
public class FragmentControler {

	/**
	 * replace new fragment to screen, but don't add to {@link BackStackEntry}
	 * 
	 * @param frgManager
	 * @param shlFragment
	 */
	public static void replaceDontAddToBackStack(
			SlidingSherlockFragmentActivity shlFrgAtv,
			SherlockFragment shlFragment) {
		shlFrgAtv.getSupportFragmentManager().beginTransaction()
				.replace(R.id.content_parent_layout, shlFragment).commit();
	}

	/**
	 * replace new fragment to screen, and add to {@link BackStackEntry}
	 * 
	 * @param frgManager
	 * @param psdlFragment
	 * @param nameClass
	 */
	public static void replaceWithAddToBackStack(
			SlidingSherlockFragmentActivity shlFrgAtv,
			ManageStudentFragment psdlFragment, String nameClass) {
		shlFrgAtv.getSupportFragmentManager().beginTransaction()
				.replace(R.id.content_parent_layout, psdlFragment, nameClass)
				.addToBackStack(nameClass).commit();
	}

	/**
	 * replace new fragment to screen, and add to {@link BackStackEntry}
	 * 
	 * @param frgManager
	 * @param psdlFragment
	 * @param nameClass
	 */
	public static void replaceWithAddToBackStackAnimation(
			SlidingSherlockFragmentActivity shlFrgAtv,
			ManageStudentFragment psdlFragment, String nameClass) {
		FragmentTransaction ft = shlFrgAtv.getSupportFragmentManager()
				.beginTransaction();
		ft.setCustomAnimations(R.anim.fragment_slide_left_enter,
				R.anim.fragment_slide_left_exit,
				R.anim.fragment_slide_right_enter,
				R.anim.fragment_slide_right_exit);
		ft.replace(R.id.content_parent_layout, psdlFragment, nameClass);
		ft.addToBackStack(nameClass);
		ft.commit();

	}

	/**
	 * replace new fragment to screen, and remove all {@link BackStackEntry}
	 * 
	 * @param frgManager
	 * @param shlFragment
	 */
	public static void replaceWithPopAllBackStack(
			SlidingSherlockFragmentActivity shlFrgAtv,
			SherlockFragment shlFragment) {
		shlFrgAtv.getSupportFragmentManager().popBackStack(null,
				FragmentManager.POP_BACK_STACK_INCLUSIVE);
		shlFrgAtv.getSupportFragmentManager().beginTransaction()
				.replace(R.id.content_parent_layout, shlFragment).commit();
	}

}
