package com.suchana.atten;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.suchana.atten.database.DatabaseHelper;
import com.suchana.atten.models.Student;
import com.suchana.atten.models.Subject;
import com.suchana.atten.rest.APIClient;
import com.suchana.atten.rest.ApiInterface;
import com.suchana.atten.rest.DataRetreiver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class Home extends AppCompatActivity {

    private RecyclerView rv;
    private RecyclerView.LayoutManager layoutManager;
    private List<Subject> subjects = new ArrayList<>();
    private String username = "sunil001";
    private DataRetreiver dataretreiver;
    public Map<String,List<Student>> studentsPerSubject;
    public Map<String,List<Student>> studentsPerSubjectUpload  =  new HashMap<>();
    private DatabaseHelper dbHelper;
    private ApiInterface apiService = APIClient.getClient().create(ApiInterface.class);
    public List<Student> students = new ArrayList<>();
    private Button uploadAttendanceBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        rv =findViewById(R.id.rv);
        uploadAttendanceBtn = findViewById(R.id.upload_student_attendance);
        dbHelper = new DatabaseHelper(this);
        layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        rv.setHasFixedSize(true);
        dataretreiver = new DataRetreiver();
        dataretreiver.getInitialStudentList(username,this);
        initializeData();
        uploadAttendanceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadAttendance();
            }
        });
    }

    //Make use of Retrofit to retreive the list of subjects of the teacher
    private void initializeData(){
        Call<Map<String,List<Student>>> call = apiService.getInitialStudentList(username);
        call.enqueue(new Callback<Map<String, List<Student>>>() {
            @Override
            public void onResponse(Call<Map<String, List<Student>>> call, Response<Map<String, List<Student>>> response) {
                studentsPerSubject = response.body();
                for(Map.Entry<String,List<Student>> entry : studentsPerSubject.entrySet() ){
                    String subject = entry.getKey();
                    subjects.add(new Subject(subject));
                }
                initializeAdapter(subjects);
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


    private void initializeAdapter(List<Subject> subjects){
        RVAdapter adapter = new RVAdapter(subjects);
        rv.setAdapter(adapter);
    }

    private void uploadAttendance(){
        students = dbHelper.getStudentsForSubject("CS-1701-A");
        Log.e("Students Size",String.valueOf(students.get(0).getSubject1_at()));
        studentsPerSubjectUpload.put("CS-1701-A",students);
        Call<ResponseBody> call = apiService.uploadStudentAttendance(studentsPerSubjectUpload);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.e("response",String.valueOf(response.body()));
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Network Failure","Whatttt???");
            }
        });

    }



}


