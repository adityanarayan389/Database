package com.appstone.database;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Size;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText metRegno;
    private EditText metName;
    private EditText metBranch;
    private EditText metBookBorrowed;
    private EditText metStartdate;
    private EditText metEnddate;
    private Button mbtnEnterdata;

    private databaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        metRegno=findViewById(R.id.et_reg_no);
        metName=findViewById(R.id.et_name);
        metBookBorrowed=findViewById(R.id.et_book_borrowed);
        metBranch=findViewById(R.id.et_branch);
        metStartdate=findViewById(R.id.et_issue_date);
        metEnddate=findViewById(R.id.et_return_date);

        mbtnEnterdata=findViewById(R.id.btn_enter_data);
        Button mbtnViewDsta=findViewById(R.id.btn_view_data);

        dbHelper=new databaseHelper(this);
        Bundle data=getIntent().getExtras();
        final boolean isEdit =data.getBoolean("IS_EDIT");
        Student editStudentValue= (Student) data.getSerializable("STUDENT");

        mbtnEnterdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String regNovalue=metRegno.getText().toString();
                String studentnameValue= metName.getText().toString();
                String branchValue= metBranch.getText().toString();
                String bookBorrowedvalue=metBookBorrowed.getText().toString();
                String issueDatevalue=metStartdate.getText().toString();
                String returnDatevalue= metEnddate.getText().toString();

                int Regno=0;
                if (!regNovalue.isEmpty()){
                    Regno=Integer.parseInt(regNovalue);
                }

                Student studentdetails=new Student();
                studentdetails.regno=Regno;
                studentdetails.Studentname=studentnameValue;
                studentdetails.Studentbraanch=branchValue;
                studentdetails.bookborrowed=bookBorrowedvalue;
                studentdetails.issuedate=issueDatevalue;
                studentdetails.returnate= returnDatevalue;

                if (!isEdit) {
                    dbHelper.insertDataToDatabase(studentdetails, dbHelper.getWritableDatabase());

                }else {
                    dbHelper.updateDatabase(studentdetails,dbHelper.getWritableDatabase());
                    setResult(Activity.RESULT_OK);
                    finish();

                }
                metBookBorrowed.setText("");
                metName.setText("");
                metRegno.setText("");
                metBranch.setText("");
                metStartdate.setText("");
                metEnddate.setText("");
            }
        });

        mbtnViewDsta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              startActivity(new Intent(MainActivity.this,StudentListActivity.class));
            }
        });



        if (isEdit){
            metRegno.setText(String.valueOf(editStudentValue.regno));
            metName.setText(editStudentValue.Studentname);
            metBranch.setText(editStudentValue.Studentbraanch);
            metBookBorrowed.setText(editStudentValue.bookborrowed);
            metStartdate.setText(editStudentValue.issuedate);
            metEnddate.setText(editStudentValue.returnate);

            metRegno.setEnabled(false);
            mbtnEnterdata.setText("update");
        }




    }
}
