package com.suchana.atten;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AttenAdapter extends RecyclerView.Adapter<AttenAdapter.PersonViewHolder>{

    private List<Student> students ;
    private Context context;
    private StudentItemListener studentListener;
    private Student student_instance;
    public List<Integer> present_rollNo_list;

    /*A ViewHolder is a holder for one object in the list*/
    public class PersonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
       public TextView name;
       public TextView roll_no;
       public boolean isPresent;

        private PersonViewHolder(View itemView,StudentItemListener listener) {
            super(itemView);
            context = itemView.getContext();
            studentListener = listener;
            name = (TextView)itemView.findViewById(R.id.name);
            roll_no = (TextView)itemView.findViewById(R.id.roll_no);
            isPresent = true;
            if(isPresent){
                present_rollNo_list.add(student_instance.getRoll());
                isPresent = false;
            }
            if(isPresent == false){
                present_rollNo_list.remove(student_instance.getRoll());

            }
            itemView.setOnClickListener(this);
        }

        //Make function to change viewholder background color
        @Override
        public void onClick(View view) {
            student_instance = students.get(getAdapterPosition());
            Toast toast =  Toast.makeText(context,student_instance.getName(),Toast.LENGTH_SHORT);
            toast.show();

            present_rollNo_list.add(student_instance.getRoll());
            //studentListener.markStudent();
        }
    }



    AttenAdapter(List<Student> students){
        this.students = students;
    }

    public int getItemCount() {
        return students.size();
    }

    public static interface StudentItemListener{
        public void markStudent();
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.student_list_item, viewGroup, false);
        PersonViewHolder pvh = new PersonViewHolder(v, new StudentItemListener() {
            @Override
            public void markStudent() {
                Toast toast = Toast.makeText(context,"hello2",Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        return pvh;
    }

    @Override
    public void onBindViewHolder(PersonViewHolder personViewHolder, int i) {
        personViewHolder.name.setText(students.get(i).name);
        personViewHolder.roll_no.setText(students.get(i).roll);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}