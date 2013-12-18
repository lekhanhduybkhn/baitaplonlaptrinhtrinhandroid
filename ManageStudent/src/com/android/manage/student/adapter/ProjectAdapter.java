package com.android.manage.student.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.actionbarsherlock.internal.view.menu.ListMenuItemView;
import com.android.manage.student.R;
import com.android.manage.student.adapter.ClassAdapter.StudentHolder;
import com.android.manage.student.base.SlidingSherlockFragmentActivity;
import com.android.manage.student.common.SqlManageStudentOpenHelper;
import com.android.manage.student.object.ManageClass;
import com.android.manage.student.object.ManagerBTL;

public class ProjectAdapter extends BaseAdapter{
	private LayoutInflater inflater;
	List<ManagerBTL> data = null;
	SqlManageStudentOpenHelper slqHelper;
	
	public ProjectAdapter(SlidingSherlockFragmentActivity shlfrgAct,List<ManagerBTL> data)
	{
		this.data=data;
		inflater = (LayoutInflater) shlfrgAct
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		// TODO Auto-generated constructor stub
	}
	public void setData(List<ManagerBTL> data) {
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
		return data.get(position).getIdBTL();
		
	}
	
	
	class ProjectHolder
	
	{
		public TextView nameproject=null;
		public TextView inforproject=null;
		
	}
	
	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View row= convertView;
		final ProjectHolder holder;
		if (row == null) {
			row = inflater.inflate(R.layout.row_project, null);
			holder = new ProjectHolder();
			holder.nameproject = (TextView) row.findViewById(R.id.nameProject);
			holder.inforproject = (TextView) row.findViewById(R.id.infoProject);
			row.setTag(holder);
		} else {
			holder = (ProjectHolder) row.getTag();
		}
		holder.nameproject.setText(data.get(position).getTenBTL());
		holder.inforproject.setText(data.get(position).getMaBTL());
		return (View) row;
	
	}
 	
	
	}
	
	
