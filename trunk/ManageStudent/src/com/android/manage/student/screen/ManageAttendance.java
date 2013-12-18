package com.android.manage.student.screen;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.android.manage.student.R;
import com.android.manage.student.adapter.ClassAdapter;
import com.android.manage.student.base.ManageStudentFragment;
import com.android.manage.student.base.SlidingSherlockFragmentActivity;
import com.android.manage.student.common.FragmentControler;
import com.android.manage.student.common.SqlManageStudentOpenHelper;
import com.android.manage.student.object.ManageClass;

public class ManageAttendance extends ManageStudentFragment {
	private String TAG = ManageAttendance.class.toString();
	private SlidingSherlockFragmentActivity shlFrgAtv;
	private SqlManageStudentOpenHelper sqlHelper;

	private TextView tvInfoClass;
	private ListView listClass;
	private ClassAdapter adapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		shlFrgAtv = ((SlidingSherlockFragmentActivity) getSherlockActivity());
		sqlHelper = new SqlManageStudentOpenHelper(shlFrgAtv);
		if (containerView == null) {
			containerView = inflater
					.inflate(R.layout.screen_manage_class, null);
			listClass = (ListView) containerView.findViewById(R.id.listClass);
			tvInfoClass = (TextView) containerView.findViewById(R.id.txtInfoClass);
			adapter = new ClassAdapter(shlFrgAtv, new ArrayList<ManageClass>());
			listClass.setAdapter(adapter);

			tvInfoClass.setVisibility(View.INVISIBLE);
			listClass.setVisibility(View.VISIBLE);
			listClass.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int position, long id) {
					// TODO Auto-generated method stub
					ManageClass mClass = (ManageClass) listClass.getAdapter().getItem(
							position);
					Bundle bundle = new Bundle();
					bundle.putSerializable("class_serializable", mClass);
					ManageAttendanceDetail mDetail = new ManageAttendanceDetail();
					mDetail.setArguments(bundle);
					FragmentControler.replaceWithAddToBackStackAnimation(shlFrgAtv,
							mDetail, TAG);
				}
			});
		}
	}

	@Override
	public void onResume() {
		super.onResume();
		shlFrgAtv.settingActionBar(TAG);
		resetData();
	}

	/* lay lai du lieu tu database */
	public void resetData() {
		List<ManageClass> list = sqlHelper.getAll();
		if (list.size() > 0) {
			tvInfoClass.setVisibility(View.INVISIBLE);
			listClass.setVisibility(View.VISIBLE);
		} else {
			tvInfoClass.setVisibility(View.VISIBLE);
			listClass.setVisibility(View.INVISIBLE);
		}
		adapter.setData(list);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		sqlHelper.close();
	}

}