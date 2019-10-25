package com.suchana.atten;


import android.provider.ContactsContract;
import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.suchana.atten.database.DatabaseHelper;
import com.suchana.atten.models.Student;

import java.util.List;
import java.util.Map;

public class JSONRenderer {
    public Map<String,List<Student>> studentPerSubject;

    public Map<String,List<Student>> createStudentSubjectJson(String subjectId, List<Student> students){
            Log.e(subjectId,String.valueOf(students.size()));
            studentPerSubject.put(subjectId,students);
            return studentPerSubject;
    }

}
