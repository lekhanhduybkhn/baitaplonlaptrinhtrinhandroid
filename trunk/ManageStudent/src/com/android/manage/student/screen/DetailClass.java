package com.android.manage.student.screen;

import android.os.Bundle;
import android.widget.TextView;

import com.android.manage.student.R;
import com.android.manage.student.base.ManageStudentFragment;
import com.android.manage.student.base.SlidingSherlockFragmentActivity;
import com.android.manage.student.object.ManageClass;

public class DetailClass extends ManageStudentFragment {
	private String TAG = DetailClass.class.toString();
	private SlidingSherlockFragmentActivity shlFrgAtv;

	private TextView txtMalop;
	private TextView txtTenLop;
	private TextView txtMaHocPhan;
	private TextView txtTenHocPhan;
	private TextView txtPhongHoc;

	private ManageClass mClass = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		shlFrgAtv = ((SlidingSherlockFragmentActivity) getSherlockActivity());
		Bundle bundle = getArguments();
		if (bundle != null) {
			mClass = (ManageClass) bundle.getSerializable("class_serializable");
		}
		if (containerView == null) {
			containerView = inflater.inflate(R.layout.detailclass, null);
			txtMalop = (TextView) containerView.findViewById(R.id.txt_malop);
			txtMaHocPhan = (TextView) containerView
					.findViewById(R.id.txt_mahocphan);
			txtTenHocPhan = (TextView) containerView
					.findViewById(R.id.txt_tenhocphan);
			txtTenLop = (TextView) containerView.findViewById(R.id.txt_tenlop);
			txtPhongHoc = (TextView) containerView
					.findViewById(R.id.txt_phonghoc);

			if (mClass != null) {
				txtMalop.setText(mClass.getMaLop());
				txtTenLop.setText(mClass.getTenLop());
				txtMaHocPhan.setText(mClass.getMaHocPhan());
				txtTenHocPhan.setText(mClass.getTenHocPhan());
				txtPhongHoc.setText(mClass.getPhongHoc());
			}
		}
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		shlFrgAtv.settingActionBar(TAG);
		super.onResume();
	}
}
