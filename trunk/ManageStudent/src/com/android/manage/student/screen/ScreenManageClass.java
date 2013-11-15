package com.android.manage.student.screen;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.android.manage.student.adapter.ClassAdapter;
import com.android.manage.student.base.ManageStudentFragment;
import com.android.manage.student.base.SlidingSherlockFragmentActivity;
import com.android.manage.student.common.FragmentControler;
import com.android.manage.student.common.SqlManageStudentOpenHelper;
import com.android.manage.student.object.ManageClass;
import com.android.manage.student.R;

public class ScreenManageClass extends ManageStudentFragment implements
		OnClickListener {
	private String TAG = ScreenManageClass.class.toString();
	private SlidingSherlockFragmentActivity shlFrgAtv;
	private SqlManageStudentOpenHelper sqlHelper;

	private TextView tvInfoClass;
	private ListView listClass;
	private ClassAdapter adapter;
	private int type;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		shlFrgAtv = ((SlidingSherlockFragmentActivity) getSherlockActivity());
		sqlHelper = new SqlManageStudentOpenHelper(shlFrgAtv);
		if (containerView == null) {
			containerView = inflater
					.inflate(R.layout.screen_manage_class, null);
			tvInfoClass = (TextView) containerView
					.findViewById(R.id.txtInfoClass);
			tvInfoClass.setOnClickListener(ScreenManageClass.this);
			listClass = (ListView) containerView.findViewById(R.id.listClass);
			adapter = new ClassAdapter(shlFrgAtv, new ArrayList<ManageClass>());
			listClass.setAdapter(adapter);
			if (sqlHelper.getCountClass() > 0) {
				tvInfoClass.setVisibility(View.INVISIBLE);
				listClass.setVisibility(View.VISIBLE);
			} else {
				tvInfoClass.setVisibility(View.VISIBLE);
				listClass.setVisibility(View.INVISIBLE);
			}
			listClass.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int position, long id) {
					// TODO Auto-generated method stub
					shlFrgAtv.showMenuDialog(ScreenManageClass.this, position);
				}
			});
		}
	}

	@Override
	public void onResume() {
		super.onResume();
		Log.e("ScreenManageClass", "resume");
		shlFrgAtv.settingActionBar(TAG + String.valueOf(type));
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

	@Override
	public void onClick(View v) {
		if (v == tvInfoClass) {
			AddNewOrUpdateClass addOrUpdate = new AddNewOrUpdateClass();
			FragmentControler.replaceWithAddToBackStackAnimation(shlFrgAtv,
					addOrUpdate, TAG);
			return;
		}
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getType() {
		return this.type;
	}

	public void viewClass(int position) {
		ManageClass mClass = (ManageClass) listClass.getAdapter().getItem(
				position);
		Bundle bundle = new Bundle();
		bundle.putSerializable("class_serializable", mClass);
		ManageStudents mStudent = new ManageStudents();
		mStudent.setArguments(bundle);
		FragmentControler.replaceWithAddToBackStackAnimation(shlFrgAtv,
				mStudent, TAG);
	}

	public void deleteClass(int position) {
		ManageClass mClassTemp = (ManageClass) listClass.getAdapter().getItem(
				position);
		sqlHelper.deleteClass(mClassTemp.getIdLop());
		resetData();
	}

	public void editClass(int position) {
		ManageClass mClass = (ManageClass) listClass.getAdapter().getItem(
				position);
		Bundle bundle = new Bundle();
		bundle.putSerializable("class_serializable", mClass);
		AddNewOrUpdateClass editScreen = new AddNewOrUpdateClass();
		editScreen.setArguments(bundle);
		FragmentControler.replaceWithAddToBackStackAnimation(shlFrgAtv,
				editScreen, TAG);
	}

	public void detailClass(int position) {
		ManageClass mClass = (ManageClass) listClass.getAdapter().getItem(
				position);
		Bundle bundle = new Bundle();
		bundle.putSerializable("class_serializable", mClass);
		DetailClass detail = new DetailClass();
		detail.setArguments(bundle);
		FragmentControler.replaceWithAddToBackStackAnimation(shlFrgAtv, detail,
				TAG);
	}
}
