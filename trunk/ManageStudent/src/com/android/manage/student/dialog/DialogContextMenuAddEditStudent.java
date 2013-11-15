package com.android.manage.student.dialog;

import com.android.manage.student.R;
import com.android.manage.student.base.SlidingSherlockFragmentActivity;
import com.android.manage.student.screen.ManageStudents;

import android.app.Dialog;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class DialogContextMenuAddEditStudent extends Dialog implements
		android.view.View.OnClickListener {
	private TextView tvList;
	private TextView tvDetail;
	private TextView tvEdit;
	private TextView tvDelete;
	private ManageStudents manage_student;
	private int position;

	public DialogContextMenuAddEditStudent(SlidingSherlockFragmentActivity shl,
			ManageStudents manage_student, int position) {
		super(shl);
		this.setCancelable(true);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dialog_menu);
		// TODO Auto-generated constructor stub
		tvList = (TextView) findViewById(R.id.menu_list);
		// tvList.setOnClickListener(this);
		tvList.setVisibility(View.GONE);

		tvDetail = (TextView) findViewById(R.id.menu_detail);
		tvDetail.setOnClickListener(this);

		tvEdit = (TextView) findViewById(R.id.menu_edit);
		tvEdit.setOnClickListener(this);

		tvDelete = (TextView) findViewById(R.id.menu_delete);
		tvDelete.setOnClickListener(this);
		this.manage_student = manage_student;
		this.position = position;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == tvDetail) {
			manage_student.showDetailStudent(position);
			dismiss();
		}

		if (v == tvEdit)
		{
			manage_student.editStudent(position);
			dismiss();
		}

		if (v == tvDelete) {
			manage_student.removeStudent(position);
			dismiss();
		}
	}

}
