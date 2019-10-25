package com.suchana.atten.models;

import java.lang.ref.SoftReference;

public class SubjectDbHelper {
    //SubjectDbHelper id is used as the table name
    public  String TABLE_NAME;
    public static final String COLUMN_REGDNO = "regdno";
    public static final  String COLUMN_NAME = "name";
    public static final String COLUMN_ROLLNO = "rollno";
    public static final String COLUMN_ATTEN = "attendance";
    public String CREATE_TABLE;

    public SubjectDbHelper(String subjectid){
        this.TABLE_NAME = subjectid;
         CREATE_TABLE = "CREATE TABLE '"+ this.TABLE_NAME +"'(" +
                                        COLUMN_REGDNO +" INTEGER PRIMARY KEY," +
                                        COLUMN_NAME +" TEXT," + COLUMN_ROLLNO +" INTEGER," +
                                        COLUMN_ATTEN + " INTEGER" + ")";

    }



}
