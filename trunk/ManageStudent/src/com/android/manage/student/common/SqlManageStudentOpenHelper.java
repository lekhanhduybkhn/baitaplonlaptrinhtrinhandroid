package com.android.manage.student.common;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.android.manage.student.base.SlidingSherlockFragmentActivity;
import com.android.manage.student.object.ManageClass;
import com.android.manage.student.object.Student;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class SqlManageStudentOpenHelper extends SQLiteOpenHelper {
	@SuppressLint("SdCardPath")
	private static final String DB_PATH = "/data/data/"
			+ "com.android.manage.student.common" + "/databases/";
	private static final String DATABASE_NAME = "manage_student.db";
	private static final int SCHEMA_VERSION = 1;

	/**
	 * bang class
	 */
	private static final String TABLE_CLASS = "TABLE_CLASS";
	private static final String ID_LOP = "ID_LOP";
	private static final String MA_LOP = "MA_LOP";
	private static final String TEN_LOP = "TEN_LOP";
	private static final String MA_HOC_PHAN = "MA_HOC_PHAN";
	private static final String TEN_HOC_PHAN = "TEN_HOC_PHAN";
	private static final String PHONG_HOC = "PHONG_HOC";

	/**
	 * bang student
	 */
	private static final String TABLE_STUDENT = "TABLE_STUDENT";
	private static final String ID_SINH_VIEN = "ID_SINH_VIEN";
	private static final String MA_SINH_VIEN = "MA_SINH_VIEN";
	private static final String TEN_SINH_VIEN = "TEN_SINH_VIEN";
	private static final String LOP_SINH_VIEN = "LOP_SINH_VIEN";
	private static final String GIOI_TINH = "GIOI_TINH";
	private static final String ID_LOP_DK = "ID_LOP_DK";

	public SqlManageStudentOpenHelper(SlidingSherlockFragmentActivity context) {
		super(context, DATABASE_NAME, null, SCHEMA_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String create = "CREATE TABLE IF NOT EXISTS " + TABLE_CLASS + "("
				+ ID_LOP + " INTEGER PRIMARY KEY AUTOINCREMENT," + MA_LOP
				+ " VARCHAR(20)," + TEN_LOP + " VARCHAR(100)," + MA_HOC_PHAN
				+ " VARCHAR(20)," + TEN_HOC_PHAN + " VARCHAR(100)," + PHONG_HOC
				+ " VARCHAR(20)" + ")";

		// TODO Auto-generated method stub
		String create_student = "CREATE TABLE IF NOT EXISTS " + TABLE_STUDENT
				+ "(" + ID_SINH_VIEN + " INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ MA_SINH_VIEN + " VARCHAR(20)," + TEN_SINH_VIEN
				+ " VARCHAR(100)," + LOP_SINH_VIEN + " VARCHAR(100),"
				+ GIOI_TINH + " VARCHAR(10)," + ID_LOP_DK + " INTEGER,"
				+ " FOREIGN KEY (" + ID_LOP_DK + ") REFERENCES " + TABLE_CLASS
				+ " (" + ID_LOP + ")" + ")";

		db.execSQL(create);
		db.execSQL(create_student);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLASS);
		onCreate(db);
	}

	/**
	 * them 1 lop hoc vao database
	 * 
	 * @param mClass
	 */
	public void addClass(ManageClass mClass) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(MA_LOP, mClass.getMaLop());
		values.put(TEN_LOP, mClass.getTenLop());
		values.put(MA_HOC_PHAN, mClass.getMaHocPhan());
		values.put(TEN_HOC_PHAN, mClass.getTenHocPhan());
		values.put(PHONG_HOC, mClass.getPhongHoc());
		db.insert(TABLE_CLASS, ID_LOP, values);
		db.close();
	}

	/**
	 * them moi 1 sinh vien vao 1 class
	 */
	public void addStudentClass(Student student, int idClass) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(MA_SINH_VIEN, student.getmId());
		values.put(TEN_SINH_VIEN, student.getmName());
		values.put(LOP_SINH_VIEN, student.getmClass());
		values.put(GIOI_TINH, student.getmSex());
		values.put(ID_LOP_DK, idClass);
		db.insert(TABLE_STUDENT, ID_SINH_VIEN, values);
		db.close();
	}

	/**
	 * update 1 class
	 * 
	 * @param mClass
	 * @return
	 */
	public int updateClass(ManageClass mClass) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(ID_LOP, mClass.getIdLop());
		values.put(MA_LOP, mClass.getMaLop());
		values.put(TEN_LOP, mClass.getTenLop());
		values.put(MA_HOC_PHAN, mClass.getMaHocPhan());
		values.put(TEN_HOC_PHAN, mClass.getTenHocPhan());
		values.put(PHONG_HOC, mClass.getPhongHoc());
		return db.update(TABLE_CLASS, values, ID_LOP + " = ?",
				new String[] { String.valueOf(mClass.getIdLop()) });
	}

	public int updateStudent(Student student) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(ID_SINH_VIEN, student.getId());
		values.put(MA_SINH_VIEN, student.getmId());
		values.put(TEN_SINH_VIEN, student.getmName());
		values.put(LOP_SINH_VIEN, student.getmClass());
		values.put(GIOI_TINH, student.getmSex());
		values.put(ID_LOP_DK, student.getIdRegisterClass());
		return db.update(TABLE_STUDENT, values, ID_SINH_VIEN + " = ?",
				new String[] { String.valueOf(student.getId()) });
	}

	/**
	 * xoa 1 class
	 * 
	 * @param idClass
	 */
	public void deleteClass(int idClass) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_CLASS, ID_LOP + " = ?",
				new String[] { String.valueOf(idClass) });
		db.close();
	}

	public void deleteStudent(int idStudent, int idClass) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(
				TABLE_STUDENT,
				ID_SINH_VIEN + " = ?" + " AND " + ID_LOP_DK + " = ?",
				new String[] { String.valueOf(idStudent),
						String.valueOf(idClass) });
		db.close();
	}

	/**
	 * lay 1 class tu co so du lieu
	 * 
	 * @param idClass
	 * @return
	 */
	public ManageClass getClassById(int idClass) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(TABLE_CLASS, new String[] { ID_LOP, MA_LOP,
				TEN_LOP, MA_HOC_PHAN, TEN_HOC_PHAN, PHONG_HOC }, ID_LOP + "=?",
				new String[] { String.valueOf(idClass) }, null, null, null,
				null);
		if (cursor != null)
			cursor.moveToFirst();
		ManageClass mClass = new ManageClass(Integer.parseInt(cursor
				.getString(0)), cursor.getString(1), cursor.getString(2),
				cursor.getString(3), cursor.getString(4), cursor.getString(5));
		// return contact
		return mClass;
	}

	public List<Student> getListStudentByIdClass(int idClass) {
		List<Student> list = new ArrayList<Student>();
		String selectQuery = "SELECT  * FROM " + TABLE_STUDENT + " WHERE "
				+ ID_LOP_DK + "=?";
		SQLiteDatabase db = this.getWritableDatabase();

		Cursor cursor = db.rawQuery(selectQuery,
				new String[] { String.valueOf(idClass) });
		if (cursor.moveToFirst()) {
			do {
				Student student = new Student(Integer.parseInt(cursor
						.getString(0)), cursor.getString(1),
						cursor.getString(2), cursor.getString(3),
						cursor.getString(4), Integer.parseInt(cursor
								.getString(5)));
				list.add(student);
			} while (cursor.moveToNext());
		}

		return list;
	}

	/**
	 * get All Manage Class
	 * 
	 * @return
	 */
	public List<ManageClass> getAll() {
		List<ManageClass> list = new ArrayList<ManageClass>();
		String selectQuery = "SELECT  * FROM " + TABLE_CLASS;
		SQLiteDatabase db = this.getWritableDatabase();

		Cursor cursor = db.rawQuery(selectQuery, null);
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				ManageClass mClass = new ManageClass(Integer.parseInt(cursor
						.getString(0)), cursor.getString(1),
						cursor.getString(2), cursor.getString(3),
						cursor.getString(4), cursor.getString(5));
				list.add(mClass);
			} while (cursor.moveToNext());
		}
		return list;
	}

	/**
	 * get sum row in table class
	 * 
	 * @return
	 */
	// Getting contacts Count
	public int getCountClass() {
		String countQuery = "SELECT  * FROM " + TABLE_CLASS;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		return cursor.getCount();
	}

	public int getCountStudentByIdClass(int idClass) {
		return 0;
	}

	public boolean checkDatabase() {
		boolean check = false;
		try {
			String myPath = DB_PATH + DATABASE_NAME;
			File dbfile = new File(myPath);
			// check =
			// SQLiteDatabase.openDatabase(myPath,null,SQLiteDatabase.OPEN_READWRITE);
			check = dbfile.exists();
		} catch (SQLiteException e) {
			System.out.println("Database doesn't exist");
		}

		return check;
	}
}
