package com.android.manage.student.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.manage.student.R;
import com.android.manage.student.base.SlidingSherlockFragmentActivity;
import com.android.manage.student.common.SqlManageStudentOpenHelper;
import com.android.manage.student.object.AttendanceItem;
import com.android.manage.student.object.GroupAttendanceItem;

public class AttendanceAdapter extends BaseExpandableListAdapter {

	private List<GroupAttendanceItem> listGroupItemAttend = new ArrayList<GroupAttendanceItem>();
	private LayoutInflater inflater;
	private SqlManageStudentOpenHelper sqliteOpenHelper;
	private SlidingSherlockFragmentActivity shlFrgAct;

	public AttendanceAdapter(List<GroupAttendanceItem> listGroupItemAttend,
			SlidingSherlockFragmentActivity shlFrgAct,
			SqlManageStudentOpenHelper sqliteOpenHelper) {
		this.listGroupItemAttend = listGroupItemAttend;
		inflater = (LayoutInflater) shlFrgAct
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.shlFrgAct = shlFrgAct;
		this.sqliteOpenHelper = sqliteOpenHelper;
	}

	// phuong thuc setData cho list
	public void setData(List<GroupAttendanceItem> listGroupItemAttend) {
		this.listGroupItemAttend = listGroupItemAttend;
		this.notifyDataSetChanged();
	}

	/**
	 * phuong thuc nay tra lai 1 child cua
	 */
	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return this.listGroupItemAttend.get(groupPosition).getAttendanceList()
				.get(childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	public class ItemHolder {
		public TextView txtDate;
		public CheckBox chbAttent;
		public EditText edtNote;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		View row = convertView;
		final ItemHolder itemHolder;
		if (row == null) {
			row = inflater.inflate(R.layout.item_attendance, null);
			itemHolder = new ItemHolder();
			itemHolder.txtDate = (TextView) row.findViewById(R.id.txtDate);
			itemHolder.chbAttent = (CheckBox) row.findViewById(R.id.chbAttent);
			itemHolder.edtNote = (EditText) row.findViewById(R.id.edtNote);
			row.setTag(itemHolder);
		} else {
			itemHolder = (ItemHolder) row.getTag();
		}
		final AttendanceItem item = listGroupItemAttend.get(groupPosition)
				.getAttendanceList().get(childPosition);

		/*if (itemHolder.edtNote.getVisibility() == View.VISIBLE) {
			itemHolder.edtNote.addTextChangedListener(new TextWatcher() {

				@Override
				public void afterTextChanged(Editable s) {
					// TODO Auto-generated method stub
					sqliteOpenHelper.setNoteAttendance(item.get_id(),
							s.toString());
					item.set_note(s.toString());
				}

				@Override
				public void beforeTextChanged(CharSequence s, int start,
						int count, int after) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onTextChanged(CharSequence s, int start,
						int before, int count) {

				}

			});
		}
		itemHolder.chbAttent
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						if (isChecked) {
							sqliteOpenHelper.setAttendanceById(item.get_id(), 1);
							item.set_attendant(true);
						} else {
							sqliteOpenHelper.setAttendanceById(item.get_id(), 0);
							item.set_attendant(false);
						}
					}
				});*/

		itemHolder.txtDate.setText(item.get_date());
		itemHolder.chbAttent.setChecked(item.is_attendant());
		itemHolder.edtNote.setText(item.get_note());
		return (View) row;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return this.listGroupItemAttend.get(groupPosition).getAttendanceList()
				.size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return this.listGroupItemAttend.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return this.listGroupItemAttend.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return groupPosition;
	}

	public class GroupHolder {
		public TextView txtStt = null;
		public TextView txtName = null;
		public CheckBox chbAttendance = null;
		public ImageView imgvNote;
		public LinearLayout lnlnoteStudent;
		public EditText edtNoteStudent;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		View row = convertView;
		final GroupHolder groupHolder;
		final GroupAttendanceItem groupItem = listGroupItemAttend
				.get(groupPosition);
		if (row == null) {
			row = inflater.inflate(R.layout.group_item_attendance, null);
			groupHolder = new GroupHolder();
			groupHolder.txtName = (TextView) row.findViewById(R.id.txtName);
			groupHolder.chbAttendance = (CheckBox) row
					.findViewById(R.id.chbAttendance);
			groupHolder.imgvNote = (ImageView) row.findViewById(R.id.imgvNote);
			groupHolder.lnlnoteStudent = (LinearLayout) row
					.findViewById(R.id.lnlnoteStudent);
			groupHolder.edtNoteStudent = (EditText) row
					.findViewById(R.id.edtNoteStudent);
			groupHolder.txtStt = (TextView) row.findViewById(R.id.txtStt);
			row.setTag(groupHolder);
		} else {
			groupHolder = (GroupHolder) row.getTag();
		}

		groupHolder.lnlnoteStudent.setVisibility(View.GONE);
		groupHolder.imgvNote.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (groupHolder.lnlnoteStudent.getVisibility() == View.GONE
						|| groupHolder.lnlnoteStudent.getVisibility() == View.INVISIBLE) {
					groupHolder.lnlnoteStudent.setVisibility(View.VISIBLE);
					groupHolder.edtNoteStudent
							.addTextChangedListener(new TextWatcher() {

								@Override
								public void afterTextChanged(Editable s) {
									// TODO Auto-generated method stub
									sqliteOpenHelper.setNoteAttendance(
											groupItem.getAttendanceItem()
													.get_id(), s.toString());
								}

								@Override
								public void beforeTextChanged(CharSequence s,
										int start, int count, int after) {
									// TODO Auto-generated method stub

								}

								@Override
								public void onTextChanged(CharSequence s,
										int start, int before, int count) {

								}

							});
				} else {
					groupHolder.lnlnoteStudent.setVisibility(View.GONE);
				}
			}
		});
		groupHolder.chbAttendance
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						if (isChecked) {
							sqliteOpenHelper.setAttendanceById(groupItem
									.getAttendanceItem().get_id(), 1);
						} else {
							sqliteOpenHelper.setAttendanceById(groupItem
									.getAttendanceItem().get_id(), 0);
						}
					}
				});

		groupHolder.txtStt.setText(String.valueOf(groupPosition + 1) + ".");
		groupHolder.txtName.setText(groupItem.getStudent().getmName());
		groupHolder.chbAttendance.setChecked(groupItem.getAttendanceItem()
				.is_attendant());
		groupHolder.edtNoteStudent.setText(groupItem.getAttendanceItem()
				.get_note());
		return (View) row;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

}
