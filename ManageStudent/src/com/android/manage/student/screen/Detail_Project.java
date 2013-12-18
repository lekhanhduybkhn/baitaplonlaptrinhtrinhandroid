package com.android.manage.student.screen;

import android.os.Bundle;
import android.widget.TextView;

import com.android.manage.student.R;
import com.android.manage.student.base.ManageStudentFragment;
import com.android.manage.student.base.SlidingSherlockFragmentActivity;
import com.android.manage.student.object.ManageClass;
import com.android.manage.student.object.ManagerBTL;

public class Detail_Project extends ManageStudentFragment {
	private SlidingSherlockFragmentActivity shlFrgAtv;

	private TextView txtMadt;
	private TextView txtTendt;
	private TextView txtmota;

	private ManagerBTL mBTL = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		shlFrgAtv = ((SlidingSherlockFragmentActivity) getSherlockActivity());
		Bundle bundle = getArguments();
		if (bundle != null) {
			mBTL = (ManagerBTL) bundle.getSerializable("project_serializable");
		}
		if (containerView == null) {
			containerView = inflater.inflate(R.layout.detailproject, null);
			txtMadt = (TextView) containerView.findViewById(R.id.txt_madt);
			txtTendt = (TextView) containerView
					.findViewById(R.id.txt_tendt);
			
			txtmota = (TextView) containerView
					.findViewById(R.id.txt_mota);
			

			if (mBTL != null) {
				txtMadt.setText(mBTL.getMaBTL());
				txtTendt.setText(mBTL.getTenBTL());
				txtmota.setText(mBTL.getDiscBTL());
			}
		}
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		shlFrgAtv.settingActionBar(Detail_Project.class.toString());
		super.onResume();
	}
}
