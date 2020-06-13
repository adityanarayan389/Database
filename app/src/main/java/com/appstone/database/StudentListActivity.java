package com.appstone.database;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class StudentListActivity extends AppCompatActivity implements StudentListAdapter.StudentListClickListner {


   private databaseHelper dbHelper;
   private RecyclerView mRcstudentList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

         mRcstudentList = findViewById(R.id.rc_student_List);
        mRcstudentList.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        dbHelper = new databaseHelper(this);

        getDataFromDatabase();
    }

    private void getDataFromDatabase(){
        ArrayList<Student> students = dbHelper.getDatafromDatabase(dbHelper.getReadableDatabase());

        StudentListAdapter adapter = new StudentListAdapter(this, students);
        adapter.setListner(this);
        mRcstudentList.setAdapter(adapter);

    }

    @Override
    public void onEditClicked(Student student) {
        Toast.makeText(this,"EditClicked",Toast.LENGTH_LONG).show();
        Intent editIntent=new Intent(StudentListActivity.this,MainActivity.class);
        editIntent.putExtra("STUDENT",student);
        editIntent.putExtra("IS_EDIT",true);
        startActivityForResult(editIntent,1234);
    }




    @Override
    public void onDeleteClicked(Student student) {
        dbHelper.deleteDataFromDatabase(student,dbHelper.getWritableDatabase());
        getDataFromDatabase();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1234 && resultCode == Activity.RESULT_OK){
            getDataFromDatabase();
        }
    }
}
