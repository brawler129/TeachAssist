package com.suchana.atten;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

public class TakeAttendance extends AppCompatActivity {

    private RelativeLayout name_layout;
    private RecyclerView rv1;
    private RecyclerView.LayoutManager layoutManager;
    private List<Student> students;
    private Button update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_attendance);
        update=findViewById(R.id.update);
        rv1 =findViewById(R.id.rv1);
        layoutManager = new LinearLayoutManager(this);
        rv1.setLayoutManager(layoutManager);
        AttenAdapter adapter = new AttenAdapter(students);
        rv1.setAdapter(adapter);
        rv1.setHasFixedSize(true);
        initializeData();
        initializeAdapter();//Redundant Function
//        name_layout=findViewById(R.id.name_attendance);
//        name_layout.setOnClickListener(new View.OnClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//            @Override
//            public void onClick(View view) {
//                if(name_layout.getBackground()==getDrawable(R.drawable.present_bg)){
//                    name_layout.setBackground(getDrawable(R.drawable.absent_bg));
//                }
//                else
//                    name_layout.setBackground(getDrawable(R.drawable.present_bg));
//            }
//        });
    }
    private void initializeData(){
        students = new ArrayList<>();
        students.add(new Student("Aman Mishra", 1));
        students.add(new Student("Devesh Pradhan", 2));
        students.add(new Student("Devyanshu Pradhan", 3));
    }

    private void initializeAdapter(){
        AttenAdapter adapter = new AttenAdapter(students);
        rv1.setAdapter(adapter);
    }
}
