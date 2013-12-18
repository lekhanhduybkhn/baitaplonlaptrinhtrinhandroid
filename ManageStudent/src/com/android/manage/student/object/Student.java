package com.android.manage.student.object;

import java.io.Serializable;

public class Student implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String mId;
	private String mName;
	private String mClass;
	private String mSex;
	private int idRegisterClass;

	public Student() {
		this("", "", "", "",0);
	}

	public Student(String mId, String mName, String mClass, String sex)
	{
		this.mId = mId;
		this.mName = mName;
		this.mClass = mClass;
		this.mSex = sex;
	}
	
	public Student(int id, String mId, String mName, String mClass, String sex,int idRegister) {
		this(mId, mName, mClass, sex,idRegister);
		setId(id);
	}

	public Student(String mId, String mName, String mClass, String sex,int idRegister) {
		this.mId = mId;
		this.mName = mName;
		this.mClass = mClass;
		this.mSex = sex;
		this.idRegisterClass = idRegister;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getmClass() {
		return mClass;
	}

	public void setmClass(String mClass) {
		this.mClass = mClass;
	}

	public String getmSex() {
		return mSex;
	}

	public void setmSex(String mSex) {
		this.mSex = mSex;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdRegisterClass() {
		return idRegisterClass;
	}

	public void setIdRegisterClass(int idRegisterClass) {
		this.idRegisterClass = idRegisterClass;
	}
}
