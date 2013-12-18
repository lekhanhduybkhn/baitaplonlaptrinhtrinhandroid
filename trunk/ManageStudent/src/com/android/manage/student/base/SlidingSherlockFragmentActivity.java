package com.android.manage.student.base;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager.BackStackEntry;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.android.manage.student.R;
import com.android.manage.student.common.FragmentControler;
import com.android.manage.student.dialog.DialogContextMenuAddEditClass;
import com.android.manage.student.dialog.DialogContextMenuAddEditProject;
import com.android.manage.student.dialog.DialogContextMenuAddEditStudent;
import com.android.manage.student.dialog.DialogGeneral;
import com.android.manage.student.object.ManageClass;
import com.android.manage.student.object.MenuLeft;
import com.android.manage.student.screen.AddNewOrUpdateClass;
import com.android.manage.student.screen.AddNewOrUpdateProject;
import com.android.manage.student.screen.AddNewOrUpdateStudent;
import com.android.manage.student.screen.DetailClass;
import com.android.manage.student.screen.ManageAttendance;
import com.android.manage.student.screen.ManageAttendanceDetail;
import com.android.manage.student.screen.ManageStudents;
import com.android.manage.student.screen.ScreenManageClass;
import com.android.manage.student.screen.ScreenManagerProject;
import com.android.manage.student.sliding.SlidingActivityBase;
import com.android.manage.student.sliding.SlidingActivityHelper;
import com.android.manage.student.sliding.SlidingMenu;

public class SlidingSherlockFragmentActivity extends SherlockFragmentActivity
		implements SlidingActivityBase {
	private SlidingActivityHelper mHelper;
	private ProgressDialog progressDialog;
	private MenuLeft menuLeft;

	// Custom ActionBar
	private TextView title_screen_text;
	private ImageView title_screen_leftbtn;
	private ImageView title_screen_addbtn;
	private RelativeLayout title_screen_actionbar;
	// private LinearLayout title_screen_parent_ll;

	// size of Devide
	private int screenWidth, screenHeight;

	// name class of Current Screen
	private String nameClass;

	private int actionBarHeight;

	public static String idScreen;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Google Analytics

		// get height of actionbar
		TypedValue tv = new TypedValue();
		if (getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
			actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data,
					getResources().getDisplayMetrics());
		}

		// hide ActionBar for splash screen
		getSupportActionBar().hide();

		// creat SlidingActivityHelper
		mHelper = new SlidingActivityHelper(this);
		mHelper.onCreate(savedInstanceState);

		// Create Menu left
		setBehindContentView(R.layout.menu_framelayout);
		menuLeft = new MenuLeft();
		getSupportFragmentManager().beginTransaction()
				.add(R.id.menu_framelayout_layout, menuLeft).commit();
		SlidingSherlockFragmentActivity.this.setSlidingActionBarEnabled(true);
		getSlidingMenu().setShadowWidthRes(R.dimen.dp5);
		getSlidingMenu().setShadowDrawable(R.drawable.shadow);
		getSlidingMenu().setBehindOffsetRes(R.dimen.dp64);
		getSlidingMenu().setBehindScrollScale(0.2f);
		getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		setContentView(R.layout.main);

		// custom action bar
		getSupportActionBar().setDisplayShowTitleEnabled(false);
		getSupportActionBar().setDisplayHomeAsUpEnabled(false);
		getSupportActionBar().setDisplayUseLogoEnabled(false);
		getSupportActionBar().setDisplayShowHomeEnabled(false);

		getSupportActionBar().setCustomView(R.layout.title_screen);
		getSupportActionBar().setDisplayShowCustomEnabled(true);
		title_screen_leftbtn = (ImageView) getSupportActionBar()
				.getCustomView().findViewById(R.id.title_screen_leftbtn);
		title_screen_addbtn = (ImageView) getSupportActionBar().getCustomView()
				.findViewById(R.id.title_screen_add_btn);
		title_screen_text = (TextView) getSupportActionBar().getCustomView()
				.findViewById(R.id.title_screen_text);
		title_screen_actionbar = (RelativeLayout) getSupportActionBar()
				.getCustomView().findViewById(R.id.title_screen_actionbar);
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
		try {
		} catch (IllegalArgumentException e) {
		}
	}

	public void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mHelper.onPostCreate(savedInstanceState);
	}

	public View findViewById(int id) {
		View v = super.findViewById(id);
		if (v != null)
			return v;
		return mHelper.findViewById(id);
	}

	public void setContentView(int id) {
		setContentView(getLayoutInflater().inflate(id, null));
	}

	public void setContentView(View v) {
		setContentView(v, new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
	}

	public void setContentView(View v, LayoutParams params) {
		super.setContentView(v, params);
		mHelper.registerAboveContentView(v, params);
	}

	public void setBehindContentView(int id) {
		setBehindContentView(getLayoutInflater().inflate(id, null));
	}

	public void setBehindContentView(View v) {
		setBehindContentView(v, new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
	}

	public void setBehindContentView(View v, LayoutParams params) {
		mHelper.setBehindContentView(v, params);
	}

	public SlidingMenu getSlidingMenu() {
		return mHelper.getSlidingMenu();
	}

	/**
	 * If menu is behind showing, above menu.
	 * <p>
	 * If menu is above showing, behind menu
	 * 
	 */
	public void toggle() {
		mHelper.toggle();
	}

	/**
	 * Hide MenuLeft
	 */
	public void showAbove() {
		mHelper.showAbove();
	}

	/**
	 * Show MenuLeft
	 */
	public void showBehind() {
		mHelper.showBehind();
	}

	public void setSlidingActionBarEnabled(boolean b) {
		mHelper.setSlidingActionBarEnabled(b);
	}

	public int getScreenWidth() {
		return screenWidth;
	}

	public void setScreenWidth(int screenWidth) {
		this.screenWidth = screenWidth;
	}

	public int getScreenHeight() {
		return screenHeight;
	}

	public void setScreenHeight(int screenHeight) {
		this.screenHeight = screenHeight;
	}

	public MenuLeft getMenuLeft() {
		return menuLeft;
	}

	@SuppressLint("DefaultLocale")
	public void settingActionBar(String nC, ManageClass mclass) {
		try {
			hidenKeyboard(getCurrentFocus());
		} catch (Exception e) {
		}

		nameClass = nC;
		title_screen_actionbar.setBackgroundColor(getResources().getColor(
				R.color.orange));

		if (nameClass.equals(ManageStudents.class.toString())) {
			addBackBtn();
			title_screen_leftbtn.setImageResource(R.drawable.ic_btn_back_white);
			showAddBtn(ManageStudents.class.toString(), mclass);
			getSupportActionBar().show();
		}
		// onTouch on Actionbar
		if (getSupportActionBar().isShowing()) {
			getSlidingMenu().mViewAbove.heightOfActionbar = actionBarHeight / 2;
		} else {
			getSlidingMenu().mViewAbove.heightOfActionbar = -1;
		}
	}

	@SuppressLint("DefaultLocale")
	public void settingActionBar(String nC) {
		try {
			hidenKeyboard(getCurrentFocus());
		} catch (Exception e) {
		}

		nameClass = nC;
		title_screen_actionbar.setBackgroundColor(getResources().getColor(
				R.color.orange));

		if (nameClass.equals(AddNewOrUpdateStudent.class.toString())) {
			addBackBtn();
			hideAddBtn();
			title_screen_leftbtn.setImageResource(R.drawable.ic_btn_back_white);
			getSupportActionBar().show();
		}

		// addClass
		if (nameClass.equals(AddNewOrUpdateClass.class.toString() + "add")) {
			addBackBtn();
			hideAddBtn();
			title_screen_leftbtn.setImageResource(R.drawable.ic_btn_back_white);
			title_screen_text.setText(getResources().getString(
					R.string.add_class));
			getSupportActionBar().show();
		}

		// addClass
		if (nameClass.equals(AddNewOrUpdateClass.class.toString() + "edit")) {
			addBackBtn();
			hideAddBtn();
			title_screen_leftbtn.setImageResource(R.drawable.ic_btn_back_white);
			title_screen_text.setText(getResources().getString(
					R.string.edit_class));
			getSupportActionBar().show();
		}

		// editClass
		if (nameClass.equals(DetailClass.class.toString())) {
			addBackBtn();
			hideAddBtn();
			title_screen_leftbtn.setImageResource(R.drawable.ic_btn_back_white);
			title_screen_text.setText(getResources().getString(
					R.string.detail_class));
			getSupportActionBar().show();
		}

		// ScreenManageClass 0
		if (nameClass.equals(ScreenManageClass.class.toString() + "0")) {
			addMenuBtn();
			showAddBtn(ScreenManageClass.class.toString(), null);
			getTitle_screen_text().setText(
					getResources().getString(R.string.ic_manage_list));
			getSupportActionBar().show();
		}

		// ManageAttendance
		if (nameClass.equals(ManageAttendance.class.toString())) {
			hideAddBtn();
			addMenuBtn();
			getTitle_screen_text().setText(
					getResources().getString(R.string.ic_manage_attendance));
			getSupportActionBar().show();
		}

		// ScreenManageClass 2
		if (nameClass.equals(ScreenManageClass.class.toString() + "3")) {
			addMenuBtn();
			showAddBtn(ScreenManageClass.class.toString(), null);
			getTitle_screen_text().setText(
					getResources().getString(R.string.ic_manage_goal));
			getSupportActionBar().show();
		}

		// ScreenManageClass 3
		if (nameClass.equals(ScreenManageClass.class.toString() + "2")) {
			addMenuBtn();
			showAddBtn(ScreenManageClass.class.toString(), null);
			getTitle_screen_text().setText(
					getResources().getString(R.string.ic_manage_project));
			getSupportActionBar().show();
		}

		// manage attendance detai
		if (nameClass.contains(ManageAttendanceDetail.class.toString())) {
			addBackBtn();
			hideAddBtn();
			getTitle_screen_text().setText(nameClass.substring(62));
			getSupportActionBar().show();
		}

		if (nameClass.equals(ScreenManagerProject.class.toString())) {
			addMenuBtn();
			showAddBtn(ScreenManagerProject.class.toString());
			getTitle_screen_text().setText(
					getResources().getString(R.string.ic_manage_project));
			getSupportActionBar().show();
		}

		if (nameClass.equals(AddNewOrUpdateProject.class.toString())) {
			hideAddBtn();
			addBackBtn();
			getTitle_screen_text().setText("Thêm mới bài tập lớn");
			getSupportActionBar().show();
		}

		if (nameClass.equals(AddNewOrUpdateProject.class.toString() + "edit")) {
			hideAddBtn();
			addBackBtn();
			getTitle_screen_text().setText("Chinh sửa bài tập lớn");
			getSupportActionBar().show();
		}

		// if Actionbar is not showing, set heightOfActionbar = -1. User can't
		// onTouch on Actionbar
		if (getSupportActionBar().isShowing()) {
			getSlidingMenu().mViewAbove.heightOfActionbar = actionBarHeight / 2;
		} else {
			getSlidingMenu().mViewAbove.heightOfActionbar = -1;
		}
	}

	@Override
	public void onBackPressed() {
		backEvent();
		super.onBackPressed();
	}

	/**
	 * xu ly su kien khi click nut Back tren man hinh hoac cua device
	 */
	public void backEvent() {
		int backStCount = getSupportFragmentManager().getBackStackEntryCount();
		if (backStCount > 0) {
			BackStackEntry backSE = getSupportFragmentManager()
					.getBackStackEntryAt(backStCount - 1);
			String nameStack = backSE.getName();
			if (nameStack != null && nameStack.length() > 0) {
				settingActionBar(nameStack);
			}
		}
	}

	/**
	 * add Menu Button to action bar
	 */
	public void addMenuBtn() {
		title_screen_leftbtn.setVisibility(View.VISIBLE);
		title_screen_leftbtn.setOnClickListener(null);
		title_screen_leftbtn.setImageResource(R.drawable.ic_actionbar_btn_menu);
		title_screen_leftbtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				toggle();
			}
		});
	}

	/**
	 * add Back Button to action bar
	 */
	public void addBackBtn() {
		title_screen_leftbtn.setVisibility(View.VISIBLE);
		title_screen_leftbtn.setOnClickListener(null);
		title_screen_leftbtn.setImageResource(R.drawable.ic_btn_back_white);
		title_screen_leftbtn.setOnClickListener(new OnClickListener() {
			@SuppressLint("ResourceAsColor")
			@Override
			public void onClick(View v) {
				SlidingSherlockFragmentActivity.this.onBackPressed();
			}
		});
	}

	public void showAddBtn(final String nameCl) {
		title_screen_addbtn.setVisibility(View.VISIBLE);
		title_screen_addbtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (nameCl.equals(ScreenManagerProject.class.toString())) {
					AddNewOrUpdateProject addorupdateProject = new AddNewOrUpdateProject();
					FragmentControler.replaceWithAddToBackStackAnimation(
							SlidingSherlockFragmentActivity.this,
							addorupdateProject, nameCl);
				}
			}
		});
	}
	
	public void hideAddBtn() {
		title_screen_addbtn.setVisibility(View.INVISIBLE);
		title_screen_addbtn.setOnClickListener(null);
	}

	public void showAddBtn(final String scr, final ManageClass mClass) {
		title_screen_addbtn.setVisibility(View.VISIBLE);
		title_screen_addbtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (scr.equals(ScreenManageClass.class.toString())) {
					AddNewOrUpdateClass addOrUpdate = new AddNewOrUpdateClass();
					FragmentControler.replaceWithAddToBackStackAnimation(
							SlidingSherlockFragmentActivity.this, addOrUpdate,
							scr);
				}

				if (scr.equals(ManageStudents.class.toString())) {
					Bundle bundle = new Bundle();
					bundle.putSerializable("class_serializable", mClass);
					AddNewOrUpdateStudent addorUpdate = new AddNewOrUpdateStudent();
					addorUpdate.setAdd(true);
					addorUpdate.setArguments(bundle);
					FragmentControler.replaceWithAddToBackStackAnimation(
							SlidingSherlockFragmentActivity.this, addorUpdate,
							scr);
				}
			}
		});
	}

	/**
	 * tra lai button add
	 * 
	 * @return
	 */
	public ImageView getAddbtn() {
		return this.title_screen_addbtn;
	}

	/**
	 * set invisible for all Left Button of action bar
	 */
	public void setInvisibleLeftBtn() {
		title_screen_leftbtn.setVisibility(View.INVISIBLE);
	}

	public TextView getTitle_screen_text() {
		return title_screen_text;
	}

	public ImageView getTitle_screen_leftbtn() {
		return title_screen_leftbtn;
	}

	public void setTitle_screen_leftbtn(ImageView title_screen_leftbtn) {
		this.title_screen_leftbtn = title_screen_leftbtn;
	}

	public void dismissProgressDialog() {
		if (progressDialog != null && progressDialog.isShowing()) {
			progressDialog.dismiss();
		}
	}

	public void showKeyboard(View view) {
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.showSoftInput(view, 0);
	}

	/**
	 * hiden Keyboard
	 * 
	 * @param view
	 */
	public void hidenKeyboard(View view) {
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
	}

	/**
	 * Free Memory
	 */
	public void freeMemory() {
		System.gc();
		Runtime.getRuntime().gc();
	}

	/**
	 * return Name Class of Current Screen
	 * 
	 * @return
	 */
	public String getNameClassOfCurrentScr() {
		return nameClass;
	}

	public RelativeLayout getTitle_screen_actionbar() {
		return title_screen_actionbar;
	}

	public void setOnClickActionBar(final View tabbarCustom) {
		title_screen_actionbar.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
			}
		});
	}

	public void setOnClickActionBar(final EditText et, final ImageView iv) {
		title_screen_actionbar.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
	}

	public LinearLayout getTitle_screen_parent_ll() {
		return null;
	}

	public interface OnOKLocationListener {
		public void onOK(boolean isOk);
	}

	public void showPopUp(String msg) {
		DialogGeneral dialogGeneral = new DialogGeneral(this, msg);
		dialogGeneral.show();
	}

	public void showMenuDialog(ScreenManageClass mClass, int position) {
		DialogContextMenuAddEditClass menu = new DialogContextMenuAddEditClass(
				this, mClass, position);
		menu.show();
	}

	public void showMenuDialogStudent(ManageStudents mStudent, int position) {
		DialogContextMenuAddEditStudent menu = new DialogContextMenuAddEditStudent(
				this, mStudent, position);
		menu.show();
	}
	
	public void showMenuDialogProject(ScreenManagerProject mProject,
			int position) {
		DialogContextMenuAddEditProject menu = new DialogContextMenuAddEditProject(
				this, mProject, position);
		menu.show();
	}

}
