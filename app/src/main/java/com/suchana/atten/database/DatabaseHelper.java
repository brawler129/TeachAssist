package com.suchana.atten.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.suchana.atten.models.Student;
import com.suchana.atten.models.Subject;
import com.suchana.atten.models.SubjectDbHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "teachAssist_db";
    private List<String> subjects = new ArrayList<>();
//    private List<Student> students = new ArrayList<>();

    public DatabaseHelper(Context context,List<String> subjects) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.subjects = subjects;
    }

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        for (int i = 0; i < subjects.size(); i++) {
            SubjectDbHelper subject = new SubjectDbHelper(subjects.get(i));
            sqLiteDatabase.execSQL(subject.CREATE_TABLE);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //Drop older table if existed
        for (int j = 0; j < subjects.size(); j++) {
            SubjectDbHelper subject = new SubjectDbHelper(subjects.get(j));
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + subject.TABLE_NAME);
        }
        // Create tables again
        onCreate(sqLiteDatabase);
    }

    public void insertInitialAttendance(Map<String, List<Student>> studentsPerSubject) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        Student student = new Student();
        for (Map.Entry<String, List<Student>> entry : studentsPerSubject.entrySet()) {
            String subject = entry.getKey();
            subject = "'" + subject +"'";
            List<Student> students = entry.getValue();
            for (int i = 0; i < students.size(); i++) {
                student = students.get(i);
                values.put(SubjectDbHelper.COLUMN_REGDNO, student.getRegdno());
                values.put(SubjectDbHelper.COLUMN_NAME, student.getName());
                values.put(SubjectDbHelper.COLUMN_ROLLNO, student.getRoll());
                values.put(SubjectDbHelper.COLUMN_ATTEN, student.getSubject1_at());
                db.insert(subject, null, values);
            }
        }
    }

    public void viewAttendance(String subjectId) {
        SQLiteDatabase db = this.getWritableDatabase();
            subjectId = "'" + subjectId + "'";
            Cursor cursor = db.rawQuery("select * from " + subjectId,null);
            if(cursor.moveToFirst()) {
                while(!cursor.isAfterLast()) {
                    String name = cursor.getString(cursor.getColumnIndex(SubjectDbHelper.COLUMN_NAME));
                    int attendance = cursor.getInt(cursor.getColumnIndex(SubjectDbHelper.COLUMN_ATTEN));
                    Log.e(name, String.valueOf(attendance));
                    cursor.moveToNext();
                }
            }
            db.close();
    }


    //Get Student list for the particular subject that will be rendered after passing the list to recycler view
    public List<Student> getStudentsForSubject(String subjectId){
        List<Student> students = new ArrayList<>();
        subjectId = "'" + subjectId + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor  cursor = db.rawQuery("select * from " +subjectId,null);
        Log.e("cursor size:",String.valueOf(cursor.getCount()));
        if(cursor.moveToFirst()){
            while(!cursor.isAfterLast()){
                    String name = cursor.getString(cursor.getColumnIndex(SubjectDbHelper.COLUMN_NAME));
                    Integer rollno = cursor.getInt(cursor.getColumnIndex(SubjectDbHelper.COLUMN_ROLLNO));
                    Integer subject1_at = cursor.getInt(cursor.getColumnIndex(SubjectDbHelper.COLUMN_ATTEN));
                    Integer regdno = cursor.getInt(cursor.getColumnIndex(SubjectDbHelper.COLUMN_REGDNO));
                    students.add(new Student(name,rollno,regdno,subject1_at));
                    cursor.moveToNext();
            }
        }
        return students;
    }

    public void updateAttendance(List<Integer> presentRollList){
        SQLiteDatabase db = this.getWritableDatabase();
        for(int i = 0;i<presentRollList.size();i++){
            Cursor cursor = db.query("'CS-1701-A'",
                    new String[]{SubjectDbHelper.COLUMN_ATTEN},
                    SubjectDbHelper.COLUMN_ROLLNO + "=?",
                    new String[]{String.valueOf(presentRollList.get(i))}, null, null, null, null);
            if(cursor != null)cursor.moveToFirst();
            int attendance = cursor.getInt(cursor.getColumnIndex(SubjectDbHelper.COLUMN_ATTEN));
            ContentValues values = new ContentValues();
            values.put(SubjectDbHelper.COLUMN_ATTEN,attendance + 1);
            db.update("'CS-1701-A'",values, SubjectDbHelper.COLUMN_ROLLNO +"="+String.valueOf(presentRollList.get(i)),null);
        }
    }
}
