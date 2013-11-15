package com.android.manage.student.object;

import java.io.Serializable;

public class ManageClass implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idLop;
	private String maLop;
	private String tenLop;
	private String maHocPhan;
	private String tenHocPhan;
	private String phongHoc;

	public ManageClass(String maLop, String tenLop, String maHocPhan,
			String tenHocPhan, String phongHoc) {
		setMaHocPhan(maHocPhan);
		setMaLop(maLop);
		setPhongHoc(phongHoc);
		setTenHocPhan(tenHocPhan);
		setTenLop(tenLop);
	}
	
	public ManageClass(int idLop,String maLop, String tenLop, String maHocPhan,
			String tenHocPhan, String phongHoc) {
		setIdLop(idLop);
		setMaHocPhan(maHocPhan);
		setMaLop(maLop);
		setPhongHoc(phongHoc);
		setTenHocPhan(tenHocPhan);
		setTenLop(tenLop);
	}

	public int getIdLop() {
		return idLop;
	}

	public void setIdLop(int idLop) {
		this.idLop = idLop;
	}

	public String getMaLop() {
		return maLop;
	}

	public void setMaLop(String maLop) {
		this.maLop = maLop;
	}

	public String getTenLop() {
		return tenLop;
	}

	public void setTenLop(String tenLop) {
		this.tenLop = tenLop;
	}

	public String getMaHocPhan() {
		return maHocPhan;
	}

	public void setMaHocPhan(String maHocPhan) {
		this.maHocPhan = maHocPhan;
	}

	public String getTenHocPhan() {
		return tenHocPhan;
	}

	public void setTenHocPhan(String tenHocPhan) {
		this.tenHocPhan = tenHocPhan;
	}

	public String getPhongHoc() {
		return phongHoc;
	}

	public void setPhongHoc(String phongHoc) {
		this.phongHoc = phongHoc;
	}

}
