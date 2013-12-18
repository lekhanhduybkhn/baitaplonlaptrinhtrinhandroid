package com.android.manage.student.dialog;

import com.android.manage.student.R;
import com.android.manage.student.base.SlidingSherlockFragmentActivity;
import com.android.manage.student.screen.ScreenManageClass;
import com.android.manage.student.screen.ScreenManagerProject;

import android.app.Dialog;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class DialogContextMenuAddEditProject extends Dialog implements
		android.view.View.OnClickListener {
	private TextView tvDetail;
	private TextView tvEdit;
	private TextView tvDelete;
	private ScreenManagerProject manage_Project;
	private int position;

	public DialogContextMenuAddEditProject(SlidingSherlockFragmentActivity shl,
			ScreenManagerProject manage_project, int position) {
		super(shl);
		this.setCancelable(true);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dialog_menu_project);
		// TODO Auto-generated constructor stub
		
		tvDetail = (TextView) findViewById(R.id.menu_detail);
		tvDetail.setOnClickListener(this);

		tvEdit = (TextView) findViewById(R.id.menu_edit);
		tvEdit.setOnClickListener(this);

		tvDelete = (TextView) findViewById(R.id.menu_delete);
		tvDelete.setOnClickListener(this);
		this.manage_Project = manage_project;
		this.position = position;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		
		if (v == tvDetail) {
			manage_Project.detailProject(position);
			dismiss();
		}

		if (v == tvEdit)

		{
			manage_Project.editProject(position);
			dismiss();
		}

		if (v == tvDelete) {
			manage_Project.deleteProject(position);
			dismiss();
		}
	}

}
