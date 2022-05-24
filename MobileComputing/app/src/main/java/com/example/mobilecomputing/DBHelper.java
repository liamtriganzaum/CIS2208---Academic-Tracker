package com.example.mobilecomputing;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    //database creation
    public DBHelper(Context context) {
        super(context, "Userdata.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase DB) {
        //table creation
        DB.execSQL("create Table Lectures(code TEXT primary key, day TEXT, subject TEXT, start TEXT, finish TEXT)");
        DB.execSQL("create Table Assignment(code TEXT primary key, subject TEXT, date TEXT, time TEXT)");
        DB.execSQL("create Table Exam(code TEXT primary key, subject TEXT, date TEXT, start TEXT, finish TEXT, status TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int ii) {
        DB.execSQL("drop Table if exists Lectures");
        DB.execSQL("drop Table if exists Assignment");
        DB.execSQL("drop Table if exists Exam");
    }
    //insert data for lectures method
    public Boolean insertLecturedata(String code, String day, String subject, String start, String finish)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("code", code);
        contentValues.put("day", day);
        contentValues.put("subject", subject);
        contentValues.put("start", start);
        contentValues.put("finish", finish);
        long result=DB.insert("Lectures", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }
    //insert data for assignments method
    public Boolean insertAssignmentdata(String code, String subject, String date, String time)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("code", code);
        contentValues.put("subject", subject);
        contentValues.put("date", date);
        contentValues.put("time", time);
        long result=DB.insert("Assignment", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }
    //insert data for exams method
    public Boolean insertExamdata(String code, String subject, String date, String start, String finish, String status)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("code", code);
        contentValues.put("subject", subject);
        contentValues.put("date", date);
        contentValues.put("start", start);
        contentValues.put("finish", finish);
        contentValues.put("status", status);
        long result=DB.insert("Exam", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }
    //get data for lectures
    public Cursor getLecturedata ()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Lectures", null);
        return cursor;
    }
    //get data for assignments
    public Cursor getAssignmentdata ()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Assignment", null);
        return cursor;
    }
    //get data for exams
    public Cursor getExamdata ()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Exam", null);
        return cursor;
    }
    //delete data for lectures
    public Boolean deleteLecturedata (String code)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Lectures where code = ?", new String[]{code});
        if (cursor.getCount() > 0) {
            long result = DB.delete("Lectures", "code=?", new String[]{code});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
    //delete data for assignment
    public Boolean deleteAssignmentdata (String code)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Assignment where code = ?", new String[]{code});
        if (cursor.getCount() > 0) {
            long result = DB.delete("Assignment", "code=?", new String[]{code});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
    //delete data for exam
    public Boolean deleteExamdata (String code)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Exam where code = ?", new String[]{code});
        if (cursor.getCount() > 0) {
            long result = DB.delete("Exam", "code=?", new String[]{code});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
}