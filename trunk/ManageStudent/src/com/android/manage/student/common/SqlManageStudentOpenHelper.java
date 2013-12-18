package com.android.manage.student.common;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.android.manage.student.base.SlidingSherlockFragmentActivity;
import com.android.manage.student.object.AttendanceItem;
import com.android.manage.student.object.ManageClass;
import com.android.manage.student.object.ManagerBTL;
import com.android.manage.student.object.Student;

public class SqlManageStudentOpenHelper extends SQLiteOpenHelper {
	@SuppressLint("SdCardPath")
	private static final String DB_PATH = "/data/data/"
			+ "com.android.manage.student.common" + "/databases/";
	private static final String DATABASE_NAME = "manage_student.db";
	private static final int SCHEMA_VERSION = 1;

	/**
	 * Bang Baitaplon tao string de luu gia tri truong cua table project
	 */
	private static final String TABLE_PROJECT = "TABLE_PROJECT";
	private static final String ID_PROJECT = "ID_PROJECT";
	private static final String MA_PROJECT = "MA_PROJECT";

	private static final String TEN_PROJECT = "TEN_PROJECT";
	private static final String DISC_PROJECT = "DISC_PROJECT";

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

	/**
	 * Bang diem danh
	 */

	private static final String TABLE_ATTENDANCE = "TABLE_ATTENDANCE";
	private static final String ID_ATTENDANCE = "ID_ATTENDANCE";
	private static final String DATE_ATTENDANCE = "DATE_ATTENDANCE";
	private static final String ATTENDANCE = "ATTENDANCE";
	private static final String NOTE_ATTENDANCE = "NOTE_ATTENDANCE";

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

		String create_attendance = "CREATE TABLE IF NOT EXISTS "
				+ TABLE_ATTENDANCE + "(" + ID_ATTENDANCE
				+ " INTEGER PRIMARY KEY AUTOINCREMENT," + DATE_ATTENDANCE
				+ " VARCHAR(20)," + ATTENDANCE + " INTEGER," + NOTE_ATTENDANCE
				+ " VARCHAR(250)," + ID_LOP + " INTEGER," + ID_SINH_VIEN
				+ " INTEGER," + " FOREIGN KEY (" + ID_LOP + ") REFERENCES "
				+ TABLE_CLASS + " (" + ID_LOP + ")," + " FOREIGN KEY ("
				+ ID_SINH_VIEN + ") REFERENCES " + TABLE_STUDENT + " ("
				+ ID_SINH_VIEN + ")" + ")";

		String create_project = "CREATE TABLE IF NOT EXISTS " + TABLE_PROJECT
				+ "(" + ID_PROJECT + " INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ MA_PROJECT + " VARCHAR(20)," + TEN_PROJECT + " VARCHAR(20),"
				+ DISC_PROJECT + " VARCHAR(500)" + ")";

		db.execSQL(create);
		db.execSQL(create_student);
		db.execSQL(create_attendance);
		db.execSQL(create_project);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLASS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_ATTENDANCE);
		onCreate(db);
	}

	/**
	 * them moi 1 BTL vao database
	 */

	public void addBTL(ManagerBTL mProject) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(MA_PROJECT, mProject.getMaBTL());
		values.put(TEN_PROJECT, mProject.getTenBTL());
		values.put(DISC_PROJECT, mProject.getDiscBTL());
		db.insert(TABLE_PROJECT, ID_PROJECT, values);
		db.close();

	}

	/**
	 * update 1 class
	 * 
	 */

	public int updateProject(ManagerBTL mBTL) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(ID_PROJECT, mBTL.getIdBTL());
		values.put(MA_PROJECT, mBTL.getMaBTL());
		values.put(TEN_PROJECT, mBTL.getTenBTL());
		values.put(DISC_PROJECT, mBTL.getDiscBTL());
		return db.update(TABLE_PROJECT, values, ID_PROJECT + " = ?",
				new String[] { String.valueOf(mBTL.getIdBTL()) });

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
	 * xoa 1 project
	 * 
	 * @param idproject
	 */
	public void deleteProject(int idProject)

	{
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_PROJECT, ID_PROJECT + "=?",
				new String[] { String.valueOf(idProject) });
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

	/**
	 * get All Manage Class
	 * 
	 * @return
	 */
	public List<ManagerBTL> getbtl() {
		List<ManagerBTL> list = new ArrayList<ManagerBTL>();
		String select = "SELECT * FROM " + TABLE_PROJECT;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(select, null);
		if (cursor.moveToFirst()) {
			do {
				ManagerBTL mBtl = new ManagerBTL(Integer.parseInt(cursor
						.getString(0)), cursor.getString(1),
						cursor.getString(2), cursor.getString(3));
				list.add(mBtl);
			} while (cursor.moveToNext());

		}
		return list;

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

	public List<AttendanceItem> getAllAttendanceItemByDateAndClass(int idClass,
			String date) {
		List<AttendanceItem> list = new ArrayList<AttendanceItem>();
		String selectQuery = "SELECT * FROM "
				+ TABLE_ATTENDANCE
				+ " JOIN "
				+ TABLE_STUDENT
				+ " ON TABLE_ATTENDANCE.ID_SINH_VIEN = TABLE_STUDENT.ID_SINH_VIEN "
				+ "WHERE (ID_LOP" + "=" + String.valueOf(idClass)
				+ " AND DATE_ATTENDANCE=?)";
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, new String[] { date });
		if (cursor.moveToFirst()) {
			do {
				AttendanceItem item = new AttendanceItem();
				item.set_id(Integer.parseInt(cursor.getString(0)));
				item.set_date(cursor.getString(1));
				int attent = Integer.parseInt(cursor.getString(2));
				if (attent == 0) {
					item.set_attendant(false);
				} else {
					item.set_attendant(true);
				}
				item.set_note(cursor.getString(3));
				item.setIdClass(Integer.parseInt(cursor.getString(4)));

				Student student = new Student(Integer.parseInt(cursor
						.getString(6)), cursor.getString(7),
						cursor.getString(8), cursor.getString(9),
						cursor.getString(10), Integer.parseInt(cursor
								.getString(11)));
				item.setIdStudent(student.getId());
				item.set_student(student);
				list.add(item);
			} while (cursor.moveToNext());
		}
		return list;
	}

	/**
	 * Them moi 1 hang vao bang diem danh
	 */
	public void addAttendance(AttendanceItem attendanceItem) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(DATE_ATTENDANCE, attendanceItem.get_date());
		values.put(ID_SINH_VIEN, attendanceItem.get_student().getId());
		values.put(ID_LOP, attendanceItem.get_class().getIdLop());
		values.put(ATTENDANCE, 0);
		values.put(NOTE_ATTENDANCE, attendanceItem.get_note());
		db.insert(TABLE_ATTENDANCE, ID_ATTENDANCE, values);
		db.close();
	}

	public List<AttendanceItem> getHisAttendanceItem(int idSinhvien,
			int idClass, String date) {
		List<AttendanceItem> list = new ArrayList<AttendanceItem>();
		String selectQuery = "SELECT * FROM " + TABLE_ATTENDANCE
				+ " WHERE (ID_LOP" + "=" + String.valueOf(idClass)
				+ " AND ID_SINH_VIEN=" + String.valueOf(idSinhvien)
				+ " AND DATE_ATTENDANCE <> ?)";
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, new String[] { date });
		if (cursor.moveToFirst()) {
			do {
				AttendanceItem item = new AttendanceItem();
				item.set_id(Integer.parseInt(cursor.getString(0)));
				item.set_date(cursor.getString(1));
				int attent = Integer.parseInt(cursor.getString(2));
				if (attent == 0) {
					item.set_attendant(false);
				} else {
					item.set_attendant(true);
				}
				item.set_note(cursor.getString(3));
				list.add(item);
			} while (cursor.moveToNext());
		}
		return list;
	}

	/**
	 * get sum row in table class
	 * 
	 * @return
	 */
	public int getCountClass() {
		String countQuery = "SELECT  * FROM " + TABLE_CLASS;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		return cursor.getCount();
	}

	public int getCountStudentByIdClass(int idClass) {
		return 0;
	}

	public int setAttendanceById(int id, int attendance) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(ATTENDANCE, attendance);
		return db.update(TABLE_ATTENDANCE, values, ID_ATTENDANCE + " = ?",
				new String[] { String.valueOf(id) });
	}

	public int setNoteAttendance(int id, String note) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(NOTE_ATTENDANCE, note);
		return db.update(TABLE_ATTENDANCE, values, ID_ATTENDANCE + " = ?",
				new String[] { String.valueOf(id) });
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
