package com.android.manage.student.screen;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.EditText;

import com.android.manage.student.R;
import com.android.manage.student.base.ManageStudentFragment;
import com.android.manage.student.base.SlidingSherlockFragmentActivity;
import com.android.manage.student.common.SqlManageStudentOpenHelper;
import com.android.manage.student.object.ManageClass;
import com.android.manage.student.object.ManagerBTL;

public class AddNewOrUpdateProject extends ManageStudentFragment {

	String TAG = AddNewOrUpdateProject.class.toString();
	SlidingSherlockFragmentActivity shlFrgAtv;
	private EditText tv_maproject;
	private EditText tv_tenproject;
	private EditText tv_motaproject;
	private Button add_or_edit_project;
	private SqlManageStudentOpenHelper sqlHelper;
	private boolean add = false;
	private ManagerBTL manageBTL;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		shlFrgAtv = (SlidingSherlockFragmentActivity) getSherlockActivity();
		sqlHelper = new SqlManageStudentOpenHelper(shlFrgAtv);
		Bundle bunder = getArguments();
		if (bunder != null) {
			add = false;
			manageBTL = (ManagerBTL) bunder
					.getSerializable("project_serializable");
		} else {
			add = true;
		}
		if (containerView == null) {
			containerView = inflater.inflate(
					R.layout.add_new_or_update_project, null);
			add_or_edit_project = (Button) containerView
					.findViewById(R.id.btn_add_update_project);
			tv_maproject = (EditText) containerView.findViewById(R.id.madetai);
			tv_tenproject = (EditText) containerView
					.findViewById(R.id.tendetai);
			tv_motaproject = (EditText) containerView.findViewById(R.id.mota);
			if (add == false) {
				tv_maproject.setText(manageBTL.getMaBTL());
				tv_tenproject.setText(manageBTL.getTenBTL());
				tv_motaproject.setText(manageBTL.getDiscBTL());
				add_or_edit_project.setText(shlFrgAtv.getResources().getString(
						R.string.edit));
			} else {
				add_or_edit_project.setText(shlFrgAtv.getResources().getString(
						R.string.add));
			}
		}

		add_or_edit_project.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (tv_maproject.getText() == null
						|| tv_maproject.getText().toString().length() <= 0) {
					shlFrgAtv.showPopUp("vui long nhập mã bài tập lớn");
					return;
				}
				if (tv_tenproject.getText() == null
						|| tv_maproject.getText().toString().length() <= 0) {
					shlFrgAtv.showPopUp("vui lòng nhập tên bài tập lớn");
					return;
				}
				if (add) {
					// TODO Auto-generated method stub
					ManagerBTL mProject = new ManagerBTL(tv_maproject.getText()
							.toString(), tv_tenproject.getText().toString(),
							tv_motaproject.getText().toString());
					sqlHelper.addBTL(mProject);
				} else {
					ManagerBTL mProjecBtl = new ManagerBTL(
							manageBTL.getIdBTL(), tv_maproject.getText()
									.toString(), tv_tenproject.getText()
									.toString(), tv_motaproject.getText()
									.toString());

					sqlHelper.updateProject(mProjecBtl);
				}

				shlFrgAtv.onBackPressed();
			}

		});
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (add) {
			shlFrgAtv.settingActionBar(TAG);
		} else {
			shlFrgAtv.settingActionBar(TAG + "edit");
		}
	}
}
