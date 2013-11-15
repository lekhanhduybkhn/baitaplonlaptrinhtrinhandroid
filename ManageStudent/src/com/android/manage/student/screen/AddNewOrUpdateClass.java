package com.android.manage.student.screen;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.android.manage.student.R;
import com.android.manage.student.base.ManageStudentFragment;
import com.android.manage.student.base.SlidingSherlockFragmentActivity;
import com.android.manage.student.common.SqlManageStudentOpenHelper;
import com.android.manage.student.object.ManageClass;

public class AddNewOrUpdateClass extends ManageStudentFragment implements
		OnClickListener {
	String TAG = AddNewOrUpdateClass.class.toString();
	SlidingSherlockFragmentActivity shlFrgAtv;

	private EditText tv_malop;
	private EditText tv_tenlop;
	private EditText tv_mahocphan;
	private EditText tv_tenhocphan;
	private EditText tv_phonghoc;
	private Button add_or_edit;
	private SqlManageStudentOpenHelper sqlHelper;
	private ManageClass mClass;
	private boolean add = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		shlFrgAtv = (SlidingSherlockFragmentActivity) getSherlockActivity();
		Bundle bundle = getArguments();
		if (bundle != null) {
			mClass = (ManageClass) bundle.getSerializable("class_serializable");
			shlFrgAtv.settingActionBar(TAG + "edit");
			add = false;
		} else {
			shlFrgAtv.settingActionBar(TAG + "add");
			add = true;
		}

		sqlHelper = new SqlManageStudentOpenHelper(shlFrgAtv);
		if (containerView == null) {
			containerView = inflater
					.inflate(R.layout.addnew_update_class, null);
			tv_malop = (EditText) containerView.findViewById(R.id.tv_malop);
			tv_tenlop = (EditText) containerView.findViewById(R.id.tv_tenlop);
			tv_mahocphan = (EditText) containerView
					.findViewById(R.id.tv_mahocphan);
			tv_tenhocphan = (EditText) containerView
					.findViewById(R.id.tv_tenhocphan);
			tv_phonghoc = (EditText) containerView
					.findViewById(R.id.tv_phonghoc);
			add_or_edit = (Button) containerView
					.findViewById(R.id.btn_add_update_class);
			add_or_edit.setOnClickListener(AddNewOrUpdateClass.this);
			if (!add) {
				tv_malop.setText(mClass.getMaLop());
				tv_mahocphan.setText(mClass.getMaHocPhan());
				tv_tenhocphan.setText(mClass.getTenHocPhan());
				tv_tenlop.setText(mClass.getTenLop());
				tv_phonghoc.setText(mClass.getPhongHoc());
				add_or_edit.setText(shlFrgAtv.getResources().getString(
						R.string.edit));
			} else {
				add_or_edit.setText(shlFrgAtv.getResources().getString(
						R.string.add));
			}
		}
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		Log.e("AddNewOrUpdateClass", "resume");
		super.onResume();
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		Log.e("AddNewOrUpdateClass", "destroy");
		super.onDestroy();
		sqlHelper.close();
	}

	@Override
	public void onClick(View v) {
		if (v == add_or_edit) {
			if (tv_malop.getText() == null
					|| tv_malop.getText().toString().length() <= 0) {
				shlFrgAtv.showPopUp(shlFrgAtv.getResources().getString(
						R.string.please_malop));
				return;
			}

			if (tv_tenlop.getText() == null
					|| tv_tenlop.getText().toString().length() <= 0) {
				shlFrgAtv.showPopUp(shlFrgAtv.getResources().getString(
						R.string.please_tenlop));
				return;
			}

			if (tv_mahocphan.getText() == null
					|| tv_mahocphan.getText().toString().length() <= 0) {
				shlFrgAtv.showPopUp(shlFrgAtv.getResources().getString(
						R.string.please_mahocphan));
				return;
			}

			if (tv_tenhocphan.getText() == null
					|| tv_tenhocphan.getText().toString().length() <= 0) {
				shlFrgAtv.showPopUp(shlFrgAtv.getResources().getString(
						R.string.please_tenhocphan));
				return;
			}

			if (tv_phonghoc.getText() == null
					|| tv_phonghoc.getText().toString().length() <= 0) {
				shlFrgAtv.showPopUp(shlFrgAtv.getResources().getString(
						R.string.please_phonghoc));
				return;
			}

			if (add) {

				ManageClass mClass = new ManageClass(tv_malop.getText()
						.toString(), tv_tenlop.getText().toString(),
						tv_mahocphan.getText().toString(), tv_tenhocphan
								.getText().toString(), tv_phonghoc.getText()
								.toString());
				sqlHelper.addClass(mClass);
			} else {
				ManageClass mClassTemp = new ManageClass(mClass.getIdLop(),tv_malop.getText()
						.toString(), tv_tenlop.getText().toString(),
						tv_mahocphan.getText().toString(), tv_tenhocphan
								.getText().toString(), tv_phonghoc.getText()
								.toString());
				sqlHelper.updateClass(mClassTemp);
			}
			// TODO Auto-generated method stub
			shlFrgAtv.onBackPressed();
		}
	}
}
