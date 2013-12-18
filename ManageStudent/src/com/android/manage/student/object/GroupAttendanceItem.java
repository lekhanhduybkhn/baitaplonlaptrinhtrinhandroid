package com.android.manage.student.object;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GroupAttendanceItem implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Student student;
	private AttendanceItem attendanceItem;
	private List<AttendanceItem> attendanceList = new ArrayList<AttendanceItem>();
	
	public AttendanceItem getAttendanceItem() {
		return attendanceItem;
	}

	public void setAttendanceItem(AttendanceItem attendanceItem) {
		this.attendanceItem = attendanceItem;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public List<AttendanceItem> getAttendanceList() {
		return attendanceList;
	}

	public void setAttendanceList(List<AttendanceItem> attendanceList) {
		this.attendanceList = attendanceList;
	}

}
