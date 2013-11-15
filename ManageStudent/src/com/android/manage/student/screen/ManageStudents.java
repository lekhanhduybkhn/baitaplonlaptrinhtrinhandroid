package com.android.manage.student.screen;

import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.android.manage.student.adapter.StudentAdapter;
import com.android.manage.student.base.ManageStudentFragment;
import com.android.manage.student.base.SlidingSherlockFragmentActivity;
import com.android.manage.student.common.FragmentControler;
import com.android.manage.student.common.SqlManageStudentOpenHelper;
import com.android.manage.student.object.ManageClass;
import com.android.manage.student.object.Student;
import com.android.manage.student.R;

public class ManageStudents extends ManageStudentFragment implements
		OnClickListener {
	private String TAG = ManageStudents.class.toString();
	private SlidingSherlockFragmentActivity shlFrgAct;
	private SqlManageStudentOpenHelper sqlHelper;

	private TextView tvInfoStudent;
	private ListView listStudent;
	private StudentAdapter adapter;
	private ManageClass mClass;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		shlFrgAct = (SlidingSherlockFragmentActivity) getSherlockActivity();
		Bundle bundle = getArguments();
		sqlHelper = new SqlManageStudentOpenHelper(shlFrgAct);
		if (bundle != null) {
			mClass = (ManageClass) bundle.getSerializable("class_serializable");
		}

		if (containerView == null) {
			containerView = inflater.inflate(R.layout.manage_students, null);
			tvInfoStudent = (TextView) containerView
					.findViewById(R.id.txtInfoStudent);
			listStudent = (ListView) containerView
					.findViewById(R.id.listStudent);
			adapter = new StudentAdapter(shlFrgAct, new ArrayList<Student>());
			listStudent.setAdapter(adapter);
			tvInfoStudent.setOnClickListener(ManageStudents.this);
			listStudent.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int position, long id) {
					// TODO Auto-generated method stub
					shlFrgAct.showMenuDialogStudent(ManageStudents.this, position);
				}
			});
		}
	}

	@Override
	public void onResume() {
		super.onResume();
		shlFrgAct.settingActionBar(TAG,mClass);
		shlFrgAct.getTitle_screen_text().setText(mClass.getTenLop());
		resetData();
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		sqlHelper.close();

	}

	public void resetData() {
		List<Student> list = new ArrayList<Student>();
		list = sqlHelper.getListStudentByIdClass(mClass.getIdLop());
		if (list.size() > 0) {
			tvInfoStudent.setVisibility(View.INVISIBLE);
			listStudent.setVisibility(View.VISIBLE);
		} else {
			tvInfoStudent.setVisibility(View.VISIBLE);
			listStudent.setVisibility(View.INVISIBLE);
		}
		adapter.setData(list);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == tvInfoStudent) {
			Bundle bundle = new Bundle();
			bundle.putSerializable("class_serializable", mClass);
			AddNewOrUpdateStudent addorUpdate = new AddNewOrUpdateStudent();
			addorUpdate.setAdd(true);
			addorUpdate.setArguments(bundle);
			FragmentControler.replaceWithAddToBackStackAnimation(shlFrgAct,
					addorUpdate, TAG);
			return;
		}
	}
	
	public void showDetailStudent(int position)
	{
	}
	
	public void editStudent(int position)
	{
		Student student = (Student) listStudent.getAdapter().getItem(position);
		Bundle bundle = new Bundle();
		bundle.putSerializable("student_serializable", student);
		bundle.putSerializable("class_serializable", mClass);
		AddNewOrUpdateStudent addorUpdate = new AddNewOrUpdateStudent();
		addorUpdate.setAdd(false);
		addorUpdate.setArguments(bundle);
		FragmentControler.replaceWithAddToBackStackAnimation(shlFrgAct,
				addorUpdate, TAG);
	}
	
	public void removeStudent(int position)
	{
		Student student = (Student) listStudent.getAdapter().getItem(position);
		sqlHelper.deleteStudent(student.getId(),student.getIdRegisterClass());
		resetData();
	}
}
