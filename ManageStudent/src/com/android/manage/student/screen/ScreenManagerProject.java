package com.android.manage.student.screen;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.actionbarsherlock.widget.SearchView.OnCloseListener;
import com.android.manage.student.R;
import com.android.manage.student.adapter.ProjectAdapter;
import com.android.manage.student.base.ManageStudentFragment;
import com.android.manage.student.base.SlidingSherlockFragmentActivity;
import com.android.manage.student.common.FragmentControler;
import com.android.manage.student.common.SqlManageStudentOpenHelper;
import com.android.manage.student.object.ManageClass;
import com.android.manage.student.object.ManagerBTL;

public class ScreenManagerProject extends ManageStudentFragment {
	private String TAG = ScreenManagerProject.class.toString();
	private SlidingSherlockFragmentActivity shlFrgAtvt;
	private SqlManageStudentOpenHelper sqlHelper;
	private TextView tvInfoProject;
	private ListView listproject;
	private ProjectAdapter adapter;
	private ManagerBTL btl;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		shlFrgAtvt = ((SlidingSherlockFragmentActivity) getSherlockActivity());
		sqlHelper = new SqlManageStudentOpenHelper(shlFrgAtvt);
		if (containerView == null) {
			containerView = inflater.inflate(R.layout.screen_manager_project,
					null);
			tvInfoProject = (TextView) containerView
					.findViewById(R.id.txtInfoClass);
			listproject = (ListView) containerView
					.findViewById(R.id.listProject);
			adapter = new ProjectAdapter(shlFrgAtvt,
					new ArrayList<ManagerBTL>());
			listproject.setAdapter(adapter);
		}
		listproject.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long id) {
				// TODO Auto-generated method stub
				shlFrgAtvt.showMenuDialogProject(ScreenManagerProject.this,
						position);
			}
		});

	}

	public void resetData() {
		List<ManagerBTL> list = sqlHelper.getbtl();
		/*
		 * if (list.size() > 0) { tvInfoProject.setVisibility(View.INVISIBLE);
		 * listproject.setVisibility(View.VISIBLE); } else {
		 * tvInfoProject.setVisibility(View.VISIBLE);
		 * listproject.setVisibility(View.INVISIBLE); }
		 */
		adapter.setData(list);
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		shlFrgAtvt.settingActionBar(TAG);
		resetData();

		// reset data
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		sqlHelper.close();
	}

	public void deleteProject(int position) {
		ManagerBTL mBTLtemp = (ManagerBTL) listproject.getAdapter().getItem(
				position);
		sqlHelper.deleteProject(mBTLtemp.getIdBTL());
		resetData();
	}

	public void editProject(int position) {
		ManagerBTL mBTLtemp = (ManagerBTL) listproject.getAdapter().getItem(
				position);
		Bundle bundle = new Bundle();
		bundle.putSerializable("project_serializable", mBTLtemp);
		AddNewOrUpdateProject editScreenproject = new AddNewOrUpdateProject();
		editScreenproject.setArguments(bundle);
		FragmentControler.replaceWithAddToBackStackAnimation(shlFrgAtvt,
				editScreenproject, TAG);
	}

	public void detailProject(int position) {
		ManagerBTL mBTLtemp = (ManagerBTL) listproject.getAdapter().getItem(
				position);
		Bundle bundle = new Bundle();
		bundle.putSerializable("project_serializable", mBTLtemp);
		Detail_Project detail = new Detail_Project();
		detail.setArguments(bundle);
		FragmentControler.replaceWithAddToBackStackAnimation(shlFrgAtvt,
				detail, TAG);
	}

}
