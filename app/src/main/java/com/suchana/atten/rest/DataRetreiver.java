package com.suchana.atten.rest;

import android.content.Context;
import android.util.Log;

import com.suchana.atten.database.DatabaseHelper;
import com.suchana.atten.models.Student;
import com.suchana.atten.models.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class DataRetreiver {


    private ApiInterface apiService = APIClient.getClient().create(ApiInterface.class);
    public Map<String,List<Student>> studentsPerSubject;
    public List<Subject> subjects = new ArrayList<>();
    private DatabaseHelper dbHelper;
    private List<Integer> presentRollList = new ArrayList<>();


    public void getInitialStudentList(String username, final Context context){

        Call<Map<String,List<Student>>> call = apiService.getInitialStudentList(username);
        call.enqueue(new Callback<Map<String, List<Student>>>() {
            @Override
            public void onResponse(Call<Map<String, List<Student>>> call, Response<Map<String, List<Student>>> response) {
                studentsPerSubject = response.body();
                for(Map.Entry<String,List<Student>> entry : studentsPerSubject.entrySet() ){
                    String subject_id = entry.getKey();
                    subjects.add(new Subject(subject_id));
                }
//                dbHelper = new DatabaseHelper(context,subjects);
//                dbHelper.insertInitialAttendance(studentsPerSubject);
//                dbHelper.updateAttendance(presentRollList);
//                dbHelper.viewAttendance(studentsPerSubject);

            }

            @Override
            public void onFailure(Call<Map<String, List<Student>>> call, Throwable t) {
                // Log error here since request failed
                //studentsPerSubject = null;
                Log.e("Network Failure","WHATT???");
                Log.e(TAG, t.toString());
            }
        });
    };

}
