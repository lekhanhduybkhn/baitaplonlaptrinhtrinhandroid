package com.android.manage.student.object;

import java.io.Serializable;

public class ManagerBTL implements Serializable{

	private static final long serialVersionUID = 1L;
	private int idBTL;
	private String maBTL;
	private String tenBTL;
	private String discBTL;
	
	public ManagerBTL(int idBTL,String maBTL, String tenBTL ,String discBTL) {
		setIdBTL(idBTL);
		setMaBTL(maBTL);
		setTenBTL(tenBTL);
		setDiscBTL(discBTL);	
	}
	
	public ManagerBTL(String maBTL, String tenBTL ,String discBTL) {
		setMaBTL(maBTL);
		setTenBTL(tenBTL);
		setDiscBTL(discBTL);
	}
	
	public String getDiscBTL() {
		return discBTL;
	}

	public void setDiscBTL(String discBTL) {
		this.discBTL = discBTL;
	}

	public int getIdBTL() {
		return idBTL;
	}
	public void setIdBTL(int idBTL) {
		this.idBTL = idBTL;
	}
	public String getMaBTL() {
		return maBTL;
	}
	public void setMaBTL(String maBTL) {
		this.maBTL = maBTL;
	}
	public String getTenBTL() {
		return tenBTL;
	}
	public void setTenBTL(String tenBTL) {
		this.tenBTL = tenBTL;
	}
	
}
