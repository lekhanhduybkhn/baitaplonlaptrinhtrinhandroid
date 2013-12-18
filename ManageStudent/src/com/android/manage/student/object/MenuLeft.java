package com.android.manage.student.object;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.android.manage.student.R;
import com.android.manage.student.base.ManageStudentFragment;
import com.android.manage.student.base.SlidingSherlockFragmentActivity;
import com.android.manage.student.common.FragmentControler;
import com.android.manage.student.screen.ManageAttendance;
import com.android.manage.student.screen.ScreenManageClass;
import com.android.manage.student.screen.ScreenManagerProject;
import com.android.manage.student.screen.SplashScreen;

public class MenuLeft extends ManageStudentFragment implements OnClickListener {
	private String TAG = SplashScreen.class.toString();
	private SlidingSherlockFragmentActivity shlFrgAtv;

	private TextView manage_list_student;
	private TextView ic_manage_student_attendance;
	private TextView manage_project;
	private TextView manage_student_goal;
	private TextView manage_student_calendar;

	// manage calenda
	private TextView calendar_mon;
	private TextView calendar_tue;
	private TextView calendar_wed;
	private TextView calendar_thu;
	private TextView calendar_fri;
	private TextView calendar_sat;
	private TextView calendar_sun;

	private TextView tool_setting;
	private TextView tool_about;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		shlFrgAtv = (SlidingSherlockFragmentActivity) getSherlockActivity();
		shlFrgAtv.settingActionBar(TAG);
		if (containerView == null) {
			containerView = inflater.inflate(R.layout.menu_left, null);

			// manage list student
			manage_list_student = (TextView) containerView
					.findViewById(R.id.manage_list_student);
			manage_list_student.setOnClickListener(this);
			manage_list_student.setText(shlFrgAtv.getResources().getString(
					R.string.ic_manage_list));
			manage_list_student.setCompoundDrawablesWithIntrinsicBounds(
					R.drawable.ic_manager_list_student, 0, 0, 0);

			// manage student attendance
			ic_manage_student_attendance = (TextView) containerView
					.findViewById(R.id.ic_manage_student_attendance);
			ic_manage_student_attendance.setOnClickListener(this);
			ic_manage_student_attendance.setText(shlFrgAtv.getResources()
					.getString(R.string.ic_manage_attendance));
			ic_manage_student_attendance
					.setCompoundDrawablesWithIntrinsicBounds(
							R.drawable.ic_manage_student, 0, 0, 0);

			// manage student project
			manage_project = (TextView) containerView
					.findViewById(R.id.manage_project);
			manage_project.setOnClickListener(this);
			manage_project.setText(shlFrgAtv.getResources().getString(
					R.string.ic_manage_project));
			manage_project.setCompoundDrawablesWithIntrinsicBounds(
					R.drawable.ic_manager_project, 0, 0, 0);

			// manage student goal
			manage_student_goal = (TextView) containerView
					.findViewById(R.id.manage_student_goal);
			manage_student_goal.setOnClickListener(this);
			manage_student_goal.setText(shlFrgAtv.getResources().getString(
					R.string.ic_manage_goal));
			manage_student_goal.setCompoundDrawablesWithIntrinsicBounds(
					R.drawable.ic_manage_quan, 0, 0, 0);

			// manage student calendar
			manage_student_calendar = (TextView) containerView
					.findViewById(R.id.manage_student_calendar);
			manage_student_calendar.setOnClickListener(this);
			manage_student_calendar.setText(shlFrgAtv.getResources().getString(
					R.string.ic_manage_week));
			manage_student_calendar.setCompoundDrawablesWithIntrinsicBounds(
					R.drawable.ic_manage_calenda, 0, 0, 0);

			// calendar mon
			calendar_mon = (TextView) containerView
					.findViewById(R.id.calendar_mon);
			calendar_mon.setOnClickListener(this);
			calendar_mon.setText(shlFrgAtv.getResources().getString(
					R.string.mon));
			calendar_mon.setCompoundDrawablesWithIntrinsicBounds(
					R.drawable.mon, 0, 0, 0);

			calendar_tue = (TextView) containerView
					.findViewById(R.id.calendar_tue);
			calendar_tue.setOnClickListener(this);
			calendar_tue.setText(shlFrgAtv.getResources().getString(
					R.string.tue));
			calendar_tue.setCompoundDrawablesWithIntrinsicBounds(
					R.drawable.tue, 0, 0, 0);

			calendar_wed = (TextView) containerView
					.findViewById(R.id.calendar_wed);
			calendar_wed.setOnClickListener(this);
			calendar_wed.setText(shlFrgAtv.getResources().getString(
					R.string.wed));
			calendar_wed.setCompoundDrawablesWithIntrinsicBounds(
					R.drawable.wed, 0, 0, 0);

			calendar_thu = (TextView) containerView
					.findViewById(R.id.calendar_thu);
			calendar_thu.setOnClickListener(this);
			calendar_thu.setText(shlFrgAtv.getResources().getString(
					R.string.thu));
			calendar_thu.setCompoundDrawablesWithIntrinsicBounds(
					R.drawable.thu, 0, 0, 0);

			calendar_fri = (TextView) containerView
					.findViewById(R.id.calendar_fri);
			calendar_fri.setOnClickListener(this);
			calendar_fri.setText(shlFrgAtv.getResources().getString(
					R.string.fri));
			calendar_fri.setCompoundDrawablesWithIntrinsicBounds(
					R.drawable.fri, 0, 0, 0);

			calendar_sat = (TextView) containerView
					.findViewById(R.id.calendar_sat);
			calendar_sat.setOnClickListener(this);
			calendar_sat.setText(shlFrgAtv.getResources().getString(
					R.string.sat));
			calendar_sat.setCompoundDrawablesWithIntrinsicBounds(
					R.drawable.sat, 0, 0, 0);

			calendar_sun = (TextView) containerView
					.findViewById(R.id.calendar_sun);
			calendar_sun.setOnClickListener(this);
			calendar_sun.setText(shlFrgAtv.getResources().getString(
					R.string.sun));
			calendar_sun.setCompoundDrawablesWithIntrinsicBounds(
					R.drawable.sun, 0, 0, 0);

			tool_setting = (TextView) containerView
					.findViewById(R.id.tool_setting);
			tool_setting.setOnClickListener(this);
			tool_setting.setText(shlFrgAtv.getResources().getString(
					R.string.setting));
			tool_setting.setCompoundDrawablesWithIntrinsicBounds(
					R.drawable.ic_settting, 0, 0, 0);

			tool_about = (TextView) containerView.findViewById(R.id.tool_about);
			tool_about.setOnClickListener(this);
			tool_about.setText(shlFrgAtv.getResources().getString(
					R.string.about));
			tool_about.setCompoundDrawablesWithIntrinsicBounds(
					R.drawable.ic_about, 0, 0, 0);
		}
	}

	@Override
	public void onClick(View v) {
		shlFrgAtv.showAbove();

		// di chuyen sang man hinh moi
		String nameClass = shlFrgAtv.getNameClassOfCurrentScr();
		// TODO Auto-generated method stub
		if (v == manage_list_student) {
			if (nameClass.equals(ScreenManageClass.class.toString())) {
				return;
			}
			ScreenManageClass manageClass = new ScreenManageClass();
			manageClass.setType(0);
			FragmentControler
					.replaceWithPopAllBackStack(shlFrgAtv, manageClass);
		}

		if (v == ic_manage_student_attendance) {
			ManageAttendance manageAttendance = new ManageAttendance();
			FragmentControler
					.replaceWithPopAllBackStack(shlFrgAtv, manageAttendance);
		}

		if (v == manage_project) {
			ScreenManagerProject manager_project = new ScreenManagerProject();

			FragmentControler
					.replaceWithPopAllBackStack(shlFrgAtv, manager_project);
		}

		if (v == manage_student_goal) {
			if (nameClass.equals(ScreenManageClass.class.toString())) {
				return;
			}
			ScreenManageClass manageClass = new ScreenManageClass();
			manageClass.setType(3);
			FragmentControler
					.replaceWithPopAllBackStack(shlFrgAtv, manageClass);
		}

		if (v == manage_student_calendar) {
			
		}

		if (v == calendar_mon) {

		}

		if (v == calendar_tue) {

		}

		if (v == calendar_wed) {

		}

		if (v == calendar_thu) {

		}

		if (v == calendar_fri) {

		}

		if (v == calendar_sat) {

		}

		if (v == calendar_sun) {

		}
		if (v == tool_setting) {

		}

		if (v == tool_about) {

		}
	}

	public void setOnItemSelect(int i) {

	}
}
