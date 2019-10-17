package com.suchana.atten;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

public class TakeAttendance extends AppCompatActivity {

    private RelativeLayout name_layout;
    private RecyclerView rv1;
    private RecyclerView.LayoutManager layoutManager;
    private List<Student> students;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_attendance);
        rv1 =findViewById(R.id.rv1);
        layoutManager = new LinearLayoutManager(this);
        rv1.setLayoutManager(layoutManager);
        AttenAdapter adapter = new AttenAdapter(students);
        rv1.setAdapter(adapter);
        rv1.setHasFixedSize(true);
        initializeData();
        initializeAdapter();
    }
    private void initializeData(){
        students = new ArrayList<>();
        students.add(new Student("Aman Mishra", 1));
        students.add(new Student("Devesh Pradhan", 2));
        students.add(new Student("Devyanshu Pradhan", 4));
    }

    private void initializeAdapter(){
        AttenAdapter adapter = new AttenAdapter(students);
        rv1.setAdapter(adapter);
    }
}
