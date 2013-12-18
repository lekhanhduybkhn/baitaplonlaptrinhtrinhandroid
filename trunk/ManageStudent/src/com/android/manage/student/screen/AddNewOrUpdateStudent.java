package com.android.manage.student.screen;

import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.android.manage.student.adapter.ListStudent;
import com.android.manage.student.base.ManageStudentFragment;
import com.android.manage.student.base.SlidingSherlockFragmentActivity;
import com.android.manage.student.common.SqlManageStudentOpenHelper;
import com.android.manage.student.object.ManageClass;
import com.android.manage.student.object.Student;
import com.android.manage.student.R;

public class AddNewOrUpdateStudent extends ManageStudentFragment implements
		OnClickListener {
	private String TAG = AddNewOrUpdateStudent.class.toString();
	private SlidingSherlockFragmentActivity shlFrgAct;

	private EditText eTxtId;
	private EditText eTxtName;
	private EditText eTxtClass;
	private RadioGroup rdGrpSex;
	private Button btnAddOrUpdate;
	private ManageClass mClass;
	private Student student;
	private SqlManageStudentOpenHelper sqlHelper;
	private boolean add = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		shlFrgAct = (SlidingSherlockFragmentActivity) getSherlockActivity();
		Bundle bundle = getArguments();
		sqlHelper = new SqlManageStudentOpenHelper(shlFrgAct);
		if (bundle != null) {
			mClass = (ManageClass) bundle.getSerializable("class_serializable");
			if (!add) {
				student = (Student) bundle
						.getSerializable("student_serializable");
			}
		}
		if (containerView == null) {
			containerView = inflater.inflate(
					R.layout.add_new_or_update_student, null);
			eTxtId = (EditText) containerView.findViewById(R.id.idsv);
			eTxtName = (EditText) containerView.findViewById(R.id.name);
			eTxtClass = (EditText) containerView
					.findViewById(R.id.classStudent);
			rdGrpSex = (RadioGroup) containerView.findViewById(R.id.sex);
			btnAddOrUpdate = (Button) containerView
					.findViewById(R.id.btn_add_update_student);
			btnAddOrUpdate.setOnClickListener(this);
			if (!add) {
				eTxtId.setText(student.getmId());
				eTxtName.setText(student.getmName());
				eTxtClass.setText(student.getmClass());
				if (student.getmSex().equals("MALE")) {
					rdGrpSex.check(R.id.male);
				} else {
					rdGrpSex.check(R.id.female);
				}
				btnAddOrUpdate.setText(shlFrgAct.getResources().getString(
						R.string.edit));
			} else {
				btnAddOrUpdate.setText(shlFrgAct.getResources().getString(
						R.string.add));
			}
		}
	}

	@Override
	public void onResume() {
		super.onResume();
		if (add) {
			shlFrgAct.getTitle_screen_text().setText(
					shlFrgAct.getResources().getString(R.string.add_student));
		} else {
			shlFrgAct.getTitle_screen_text().setText(
					shlFrgAct.getResources().getString(R.string.edit_student));
		}
		shlFrgAct.settingActionBar(TAG);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		sqlHelper.close();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == btnAddOrUpdate) {
			if (eTxtId.getText() == null
					|| eTxtId.getText().toString().length() <= 0) {
				shlFrgAct.showPopUp(shlFrgAct.getResources().getString(
						R.string.entermasv));
				return;
			}

			if (eTxtName.getText() == null
					|| eTxtName.getText().toString().length() <= 0) {
				shlFrgAct.showPopUp(shlFrgAct.getResources().getString(
						R.string.enterhoten));
				return;
			}

			String id = eTxtId.getText().toString();
			String name = eTxtName.getText().toString();
			String classStudent = eTxtClass.getText().toString();
			String sex = "";
			switch (rdGrpSex.getCheckedRadioButtonId()) {
			case R.id.female:
				sex = "FEMALE";
				break;
			case R.id.male:
				sex = "MALE";
				break;
			default:
				break;
			}
			if (add) {
				/**
				Student student = new Student(id, name, classStudent, sex,
						mClass.getIdLop());
				sqlHelper.addStudentClass(student, mClass.getIdLop());*/
				List<Student> list = ListStudent.createListStudent();
				for (int i = 0; i <list.size(); i++) {
					sqlHelper.addStudentClass(list.get(i), mClass.getIdLop());
				}
				
			} else {
				Student studentTemp = new Student(student.getId(), id, name,
						classStudent, sex, student.getIdRegisterClass());
				sqlHelper.updateStudent(studentTemp);
			}
			shlFrgAct.onBackPressed();
		}
	}

	public void setAdd(boolean add) {
		this.add = add;
	}
}
