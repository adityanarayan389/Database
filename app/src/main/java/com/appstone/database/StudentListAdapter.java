package com.appstone.database;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StudentListAdapter extends RecyclerView.Adapter<StudentListAdapter.StudentListHolder>{


   private Context context;
   private ArrayList<Student> students;
   private StudentListClickListner listner;

    public StudentListAdapter(Context context, ArrayList<Student> students){

        this.context=context;
        this.students=students;

    }

    public void setListner(StudentListClickListner listner){
        this.listner=listner;
    }

    @NonNull
    @Override
    public StudentListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.cell_student,parent,false);
        StudentListHolder holder=new StudentListHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull StudentListHolder holder, int position) {

        final Student currentStudent=students.get(position);
        holder.mtvRegno.setText(String.valueOf(currentStudent.regno));
        holder.mtvStudentName.setText(currentStudent.Studentname);
        holder.mtvStudentBranch.setText(currentStudent.Studentbraanch);
        holder.mtvBookBorowed.setText(currentStudent.bookborrowed);
        holder.mtvIssueDate.setText(currentStudent.issuedate);
        holder.mtvReturndate.setText(currentStudent.returnate);

        holder.mRlEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           if (listner != null){
               listner.onEditClicked(currentStudent);
           }
            }
        });

        holder.mRlDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listner!=null){
                    listner.onDeleteClicked(currentStudent);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    class  StudentListHolder extends RecyclerView.ViewHolder{
        private TextView mtvRegno;
        private TextView mtvStudentName;
        private TextView mtvStudentBranch;
        private TextView mtvBookBorowed;
        private TextView mtvIssueDate;
        private TextView mtvReturndate;

        private RelativeLayout mRlEdit;
        private RelativeLayout mRlDelete;


        public StudentListHolder(@NonNull View itemView) {
            super(itemView);
            mtvRegno=itemView.findViewById(R.id.tv_reg_title);
            mtvStudentName=itemView.findViewById(R.id.tv_student_name);
            mtvStudentBranch=itemView.findViewById(R.id.tv_student_branch);
            mtvBookBorowed=itemView.findViewById(R.id.tv_book_borrowed);
            mtvIssueDate=itemView.findViewById(R.id.tv_issue_date);
            mtvReturndate=itemView.findViewById(R.id.tv_return_date);

            mRlEdit=itemView.findViewById(R.id.rl_edit);
            mRlDelete=itemView.findViewById(R.id.rl_delete);



        }
    }
    public interface StudentListClickListner{
        void onEditClicked(Student student);
        void onDeleteClicked(Student student);
    }

}
