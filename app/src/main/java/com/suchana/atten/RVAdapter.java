package com.suchana.atten;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder>{

    private Context context;
    List<Person> persons;

    public class PersonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView sub_id;
        private TextView subject;
        private TextView section;
        private Button take;
        private Button vatten;
        private subjectItemListener subjectListener;
        private Integer test;


        public PersonViewHolder(View itemView,subjectItemListener subjectListener) {
            super(itemView);
            this.subjectListener = subjectListener;
            context = itemView.getContext();
            sub_id = (TextView)itemView.findViewById(R.id.sub_id);
            subject = (TextView)itemView.findViewById(R.id.subject);
            section = (TextView)itemView.findViewById(R.id.section);
            take = itemView.findViewById(R.id.take_attendance_button);
            vatten = itemView.findViewById(R.id.view_attendance_button);
            take.setOnClickListener(this);
            vatten.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v){
            if(v.getId() == take.getId()){
                test = getAdapterPosition();
                subjectListener.optionTakeAttendance(test);
            }

            else if(v.getId() == vatten.getId()){
                subjectListener.optionViewAttendance();
            }

        }

    }

    public static interface subjectItemListener{
        public void optionTakeAttendance(Integer pos);
        public void optionViewAttendance();
    }

    RVAdapter(List<Person> persons){
        this.persons = persons;
    }

    public int getItemCount() {
        return persons.size();
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.subject_list_item, viewGroup, false);
        RVAdapter.PersonViewHolder pvh = new PersonViewHolder(v, new subjectItemListener() {
            @Override
            public void optionTakeAttendance(Integer pos) {
                //Toast toast = Toast.makeText(context,pos.toString(),Toast.LENGTH_SHORT);
                //toast.show();
                Intent intent =new Intent(context,TakeAttendance.class);
                context.startActivity(intent);

            }

            @Override
            public void optionViewAttendance() {
                Toast toast = Toast.makeText(context,"View Attendance",Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        return pvh;
    }

    @Override
    public void onBindViewHolder(PersonViewHolder personViewHolder, int i) {
        personViewHolder.sub_id.setText(persons.get(i).sub_id);
        personViewHolder.subject.setText(persons.get(i).subject);
        personViewHolder.section.setText(persons.get(i).section);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}