package com.android.manage.student.dialog;

import com.android.manage.student.R;
import com.android.manage.student.base.SlidingSherlockFragmentActivity;
import com.android.manage.student.screen.ScreenManageClass;

import android.app.Dialog;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class DialogContextMenuAddEditClass extends Dialog implements
		android.view.View.OnClickListener {
    private TextView tvList;
	private TextView tvDetail;
	private TextView tvEdit;
	private TextView tvDelete;
	private ScreenManageClass manage_class;
	private int position;

	public DialogContextMenuAddEditClass(SlidingSherlockFragmentActivity shl,
			ScreenManageClass manage_class, int position) {
		super(shl);
		this.setCancelable(true);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dialog_menu);
		// TODO Auto-generated constructor stub
		tvList =  (TextView) findViewById(R.id.menu_list);
		tvList.setOnClickListener(this);
		
		tvDetail = (TextView) findViewById(R.id.menu_detail);
		tvDetail.setOnClickListener(this);

		tvEdit = (TextView) findViewById(R.id.menu_edit);
		tvEdit.setOnClickListener(this);

		tvDelete = (TextView) findViewById(R.id.menu_delete);
		tvDelete.setOnClickListener(this);
		this.manage_class = manage_class;
		this.position = position;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		if(v == tvList)
		{
			manage_class.viewClass(position);
			dismiss();
		}
		
		if (v == tvDetail) {
			manage_class.detailClass(position);
			dismiss();
		}

		if (v == tvEdit)

		{
			manage_class.editClass(position);
			dismiss();
		}

		if (v == tvDelete) {
			manage_class.deleteClass(position);
			dismiss();
		}
	}

}
