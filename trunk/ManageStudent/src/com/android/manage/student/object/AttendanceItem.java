package com.android.manage.student.object;

import java.io.Serializable;

public class AttendanceItem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int _id;
	private String _date;
	private Student _student;
	private ManageClass _class;
	private boolean _attendant;
	private String _note;
	private int idStudent;
	private int idClass;
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	public String get_date() {
		return _date;
	}
	public void set_date(String _date) {
		this._date = _date;
	}
	public Student get_student() {
		return _student;
	}
	public void set_student(Student _student) {
		this._student = _student;
	}
	public ManageClass get_class() {
		return _class;
	}
	public void set_class(ManageClass _class) {
		this._class = _class;
	}
	public boolean is_attendant() {
		return _attendant;
	}
	public void set_attendant(boolean _attendant) {
		this._attendant = _attendant;
	}
	public String get_note() {
		return _note;
	}
	public void set_note(String _note) {
		this._note = _note;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public int getIdStudent() {
		return idStudent;
	}
	public void setIdStudent(int idStudent) {
		this.idStudent = idStudent;
	}
	public int getIdClass() {
		return idClass;
	}
	public void setIdClass(int idClass) {
		this.idClass = idClass;
	}
}
