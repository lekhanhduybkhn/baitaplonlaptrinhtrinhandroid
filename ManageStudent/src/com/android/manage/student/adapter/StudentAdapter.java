package com.android.manage.student.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.manage.student.R;
import com.android.manage.student.base.SlidingSherlockFragmentActivity;
import com.android.manage.student.common.SqlManageStudentOpenHelper;
import com.android.manage.student.object.Student;

public class StudentAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	List<Student> data = null;
	SqlManageStudentOpenHelper slqHelper;

	public StudentAdapter(SlidingSherlockFragmentActivity shlFrgAct,
			List<Student> data) {
		// TODO Auto-generated constructor stub
		this.data = data;
		inflater = (LayoutInflater) shlFrgAct
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public void setData(List<Student> data) {
		this.data = data;
		this.notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return (Object) data.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return data.get(position).getId();
	}

	class StudentHolder {
		public ImageView icon_sex;
		public TextView name_student = null;
		public TextView class_student = null;
		public TextView stt = null;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		final StudentHolder holder;
		if (row == null) {
			row = inflater.inflate(R.layout.row_student, null);
			holder = new StudentHolder();
			holder.icon_sex = (ImageView) row.findViewById(R.id.iconSex);
			holder.name_student = (TextView) row.findViewById(R.id.nameRow);
			holder.class_student = (TextView) row.findViewById(R.id.classRow);
			holder.stt = (TextView) row.findViewById(R.id.stt);
			row.setTag(holder);
		} else {
			holder = (StudentHolder) row.getTag();
		}

		holder.name_student.setText(data.get(position).getmName());
		holder.class_student.setText(data.get(position).getmClass());
		holder.stt.setText(String.valueOf(position + 1));
		if (data.get(position).getmSex().equals("FEMALE")) {
			holder.icon_sex.setImageResource(R.drawable.ic_female);
		} else {
			holder.icon_sex.setImageResource(R.drawable.ic_male);
		}
		return (View) row;
	}

}
