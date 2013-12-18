package com.android.manage.student.screen;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;

import com.android.manage.student.R;
import com.android.manage.student.adapter.AttendanceAdapter;
import com.android.manage.student.base.ManageStudentFragment;
import com.android.manage.student.base.SlidingSherlockFragmentActivity;
import com.android.manage.student.common.SqlManageStudentOpenHelper;
import com.android.manage.student.object.AttendanceItem;
import com.android.manage.student.object.GroupAttendanceItem;
import com.android.manage.student.object.ManageClass;
import com.android.manage.student.object.Student;

public class ManageAttendanceDetail extends ManageStudentFragment {
	private String TAG = ManageAttendanceDetail.class.toString();
	private SlidingSherlockFragmentActivity shlFrgAtv;
	private ExpandableListView expListAttendance;
	private String dateAttendance;
	private ManageClass mClass = null;
	private SqlManageStudentOpenHelper sqlHelper;
	private List<Student> listStudent;
	private List<GroupAttendanceItem> list = new ArrayList<GroupAttendanceItem>();
	private AttendanceAdapter adapter;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		shlFrgAtv = ((SlidingSherlockFragmentActivity) getSherlockActivity());
		if (containerView == null) {
			containerView = inflater.inflate(
					R.layout.manage_atttendance_detail, null);
			expListAttendance = (ExpandableListView) containerView
					.findViewById(R.id.lvExp);
			expListAttendance.setIndicatorBounds(0, 40);
			Bundle bundle = getArguments();
			sqlHelper = new SqlManageStudentOpenHelper(shlFrgAtv);
			if (bundle != null) {
				mClass = (ManageClass) bundle
						.getSerializable("class_serializable");
				
			}
			SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
			dateAttendance = formatDate.format(new Date());
			
			/*Student student;
			for (int i = 0; i < 10; i++) {
				GroupAttendanceItem group = new GroupAttendanceItem();
				student = new Student("20090472", "Le Khanh Duy", "KTMT",
						"Male", 1);

				
				 * List<AttendanceItem> listItem = new
				 * ArrayList<AttendanceItem>(); for (int j = 0; j < 5; j++) {
				 * AttendanceItem item = new AttendanceItem();
				 * item.set_date("13/12/2013"); item.set_attendant(true);
				 * item.set_note("Tích cực phát biểu"); listItem.add(item); }
				 * group.setAttendanceList(listItem);
				 
				group.setStudent(student);
				list.add(group);
			}*/
			adapter = new AttendanceAdapter(list, shlFrgAtv,sqlHelper);
			expListAttendance.setAdapter(adapter);
		}
	}

	@Override
	public void onResume() {
		super.onResume();
		shlFrgAtv.settingActionBar(TAG
				+ shlFrgAtv.getResources().getString(
						R.string.manageattendancedetai) + dateAttendance);
		setData();
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		sqlHelper.close();
		Log.e("diem danh huy", "diem danh huy");
	}
	
	public void setData()
	{
		List<AttendanceItem> listAttendance = sqlHelper.getAllAttendanceItemByDateAndClass(mClass.getIdLop(),dateAttendance);
		if(listAttendance.size() > 0 && listAttendance != null)
		{
			//luc nay la danh sach diem danh da ton tai trong ngay hom day
			list = new ArrayList<GroupAttendanceItem>();
			for (int i = 0; i < listAttendance.size(); i++) {
				GroupAttendanceItem group = new GroupAttendanceItem();
				AttendanceItem item = listAttendance.get(i);
				group.setAttendanceItem(item);
				group.setStudent(item.get_student());
				group.setAttendanceItem(item);
				List<AttendanceItem> listHis = sqlHelper.getHisAttendanceItem(item.get_student().getId(),mClass.getIdLop(), dateAttendance);
				group.setAttendanceList(listHis);
				list.add(group);
			}
			adapter.setData(list);
		}else
		{
			//kiem tra xem lop hoc co null ko
			if (mClass != null) {
				//lay ve danh sach sinh vien theo lop hoc
				listStudent = sqlHelper.getListStudentByIdClass(mClass
						.getIdLop());
				list = new ArrayList<GroupAttendanceItem>();
				if (listStudent != null && listStudent.size() > 0) {
					for (int i = 0; i < listStudent.size(); i++) {
						GroupAttendanceItem group = new GroupAttendanceItem();
						Student student = listStudent.get(i);
						AttendanceItem attendanceItem = new AttendanceItem();
						attendanceItem.set_class(mClass);
						attendanceItem.set_student(student);
						attendanceItem.set_date(dateAttendance);
					    //them vao csdl
						sqlHelper.addAttendance(attendanceItem);
						//lay ra history attendance
						List<AttendanceItem> listHis = sqlHelper.getHisAttendanceItem(student.getId(),mClass.getIdLop(), dateAttendance);
						group.setAttendanceList(listHis);
						group.setAttendanceItem(attendanceItem);
						group.setStudent(student);
						list.add(group);
					}
				}
				adapter.setData(list);
			}
		}
	}
}
