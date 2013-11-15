package com.android.manage.student.adapter;

import java.util.List;

import com.android.manage.student.common.SqlManageStudentOpenHelper;
import com.android.manage.student.object.ManageClass;
import com.android.manage.student.base.SlidingSherlockFragmentActivity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.android.manage.student.R;

public class ClassAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	List<ManageClass> data = null;
	SqlManageStudentOpenHelper slqHelper;

	public ClassAdapter(SlidingSherlockFragmentActivity shlFrgAct,
			List<ManageClass> data) {
		// TODO Auto-generated constructor stub
		this.data = data;
		inflater = (LayoutInflater) shlFrgAct
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public void setData(List<ManageClass> data) {
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
		return data.get(position).getIdLop();
	}

	class StudentHolder {
		public TextView name_class = null;
		public TextView info_class = null;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		final StudentHolder holder;
		if (row == null) {
			row = inflater.inflate(R.layout.row_class, null);
			holder = new StudentHolder();
			holder.name_class = (TextView) row.findViewById(R.id.nameClass);
			holder.info_class = (TextView) row.findViewById(R.id.infoClass);
			row.setTag(holder);
		} else {
			holder = (StudentHolder) row.getTag();
		}

		holder.name_class.setText(data.get(position).getTenLop());
		holder.info_class.setText(data.get(position).getPhongHoc());
		return (View) row;
	}

}
