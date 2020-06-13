package com.appstone.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class databaseHelper extends SQLiteOpenHelper {

//    public databaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
//        super(context, name, factory, version);
//    }

    private static String TABLE_NAME = "students";
    private static String COL_REG_NO = "reg_no";
    private static String COL_STUDENT_NAME = "student_name";
    private static String COL__BRANCH_NAME = "branch_name";
    private static String COL_BOOK_BORROWED = "book_borrowed";
    private static String COL_ISSUE_DATE = "issue_date";
    private static String COL_RETURN_DATE = "return_date";

    private static String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + COL_REG_NO + " INTEGER PRIMARY KEY," + COL_STUDENT_NAME + " TEXT," +
            COL__BRANCH_NAME + " TEXT," + COL_BOOK_BORROWED + " TEXT," + COL_ISSUE_DATE + " TEXT," + COL_RETURN_DATE + " TEXT)";

    public databaseHelper(Context context) {

        super(context, "student_details.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }

    public void insertDataToDatabase(Student student, SQLiteDatabase database) {
        ContentValues cv = new ContentValues();
        cv.put(COL_REG_NO, student.regno);
        cv.put(COL_STUDENT_NAME, student.Studentname);
        cv.put(COL__BRANCH_NAME, student.Studentbraanch);
        cv.put(COL_BOOK_BORROWED, student.bookborrowed);
        cv.put(COL_ISSUE_DATE, student.issuedate);
        cv.put(COL_RETURN_DATE, student.returnate);
        database.insert(TABLE_NAME, null, cv);
    }
    public void updateDatabase(Student student,SQLiteDatabase database){
        ContentValues cv= new ContentValues();
        cv.put(COL_STUDENT_NAME, student.Studentname);
        cv.put(COL__BRANCH_NAME,student.Studentbraanch);
        cv.put(COL_BOOK_BORROWED, student.bookborrowed);
        cv.put(COL_ISSUE_DATE, student.issuedate);
        cv.put(COL_RETURN_DATE, student.returnate);

        database.update(TABLE_NAME,cv, COL_REG_NO +" = "+ student.regno,null);

    }


    public ArrayList<Student> getDatafromDatabase(SQLiteDatabase database) {
        ArrayList<Student> Studentlist = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                Student data = new Student();
                data.regno = cursor.getInt(cursor.getColumnIndex(COL_REG_NO));
                data.Studentname = cursor.getString(cursor.getColumnIndex(COL_STUDENT_NAME));
                data.Studentbraanch = cursor.getString(cursor.getColumnIndex(COL__BRANCH_NAME));
                data.bookborrowed = cursor.getString(cursor.getColumnIndex(COL_BOOK_BORROWED));
                data.issuedate = cursor.getString(cursor.getColumnIndex(COL_ISSUE_DATE));
                data.returnate = cursor.getString(cursor.getColumnIndex(COL_RETURN_DATE));
                Studentlist.add(data);

            } while (cursor.moveToNext());

            cursor.close();
        }

        return Studentlist;

    }
    public void deleteDataFromDatabase(Student student,SQLiteDatabase database){
        database.delete(TABLE_NAME,COL_REG_NO+"="+student.regno,null);
    }


}
