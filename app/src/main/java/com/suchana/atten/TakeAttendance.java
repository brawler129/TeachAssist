package com.suchana.atten;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
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
        rv1 =findViewById(R.id.student_list_rv);
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

                AlertDialog.Builder builder = new AlertDialog.Builder(TakeAttendance.this);

                // Set a title for alert dialog
                builder.setTitle("Update Attendance");

                // Ask the final question
                builder.setMessage("Are you sure?");

                // Set the alert dialog yes button click listener
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dbHelper.updateAttendance(adapter.getPresent_rollNo_list());
                        dbHelper.viewAttendance(subjectId);
                        //return to home.class
                    }
                });

                // Set the alert dialog no button click listener
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                AlertDialog dialog = builder.create();
                // Display the alert dialog on interface
                dialog.show();
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
