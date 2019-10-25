package com.suchana.atten;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.suchana.atten.database.DatabaseHelper;
import com.suchana.atten.models.Student;

import java.util.ArrayList;
import java.util.List;

public class TakeAttendance extends AppCompatActivity {

    private RelativeLayout name_layout;
    private RecyclerView rv1;
    private RecyclerView.LayoutManager layoutManager;
    private List<Student> students;
    private String subjectId;
    private DatabaseHelper dbHelper;
    private Button updateMarksButton;
    private AttenAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_attendance);
        rv1 =findViewById(R.id.rv1);
        updateMarksButton = findViewById(R.id.upload_student_attendance);
        Intent intent = getIntent();
        subjectId = intent.getStringExtra("subjectId");
        Log.e("subjectId",subjectId);
        layoutManager = new LinearLayoutManager(this);
        rv1.setLayoutManager(layoutManager);
        //rv1.setHasFixedSize(true);
        initializeData();
        initializeAdapter();
        dbHelper.viewAttendance(subjectId);
        updateMarksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.updateAttendance(adapter.getPresent_rollNo_list());
                dbHelper.viewAttendance(subjectId);
            }
        });
    }
    private void initializeData(){
        dbHelper = new DatabaseHelper(this);
        students = new ArrayList<>();
        students = dbHelper.getStudentsForSubject(subjectId);
    }

    private void initializeAdapter(){
        adapter = new AttenAdapter(students);
        rv1.setAdapter(adapter);
    }
}
