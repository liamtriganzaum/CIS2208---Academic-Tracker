package com.example.mobilecomputing.SideBar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mobilecomputing.DBHelper;
import com.example.mobilecomputing.LectureActivity;
import com.example.mobilecomputing.R;

public class ExamActivity extends AppCompatActivity {

    EditText code, subject, date, start, finish, status;
    Button add, view, delete;
    DBHelper DB;

    //the add, view and delete methods for exams
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);

        code = findViewById(R.id.code);
        subject = findViewById(R.id.subject);
        date = findViewById(R.id.date);
        start = findViewById(R.id.starttime);
        finish = findViewById(R.id.endtime);
        status = findViewById(R.id.status);
        add = findViewById(R.id.add);
        view = findViewById(R.id.view);
        delete = findViewById(R.id.delete);
        DB = new DBHelper(this);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String codeTXT = code.getText().toString();
                String subjectTXT = subject.getText().toString();
                String dateTXT = date.getText().toString();
                String startTXT = start.getText().toString();
                String finishTXT = finish.getText().toString();
                String statusTXT = status.getText().toString();

                Boolean checkinsertdata = DB.insertExamdata(codeTXT, subjectTXT, dateTXT, startTXT, finishTXT, statusTXT);
                if(checkinsertdata==true)
                    Toast.makeText(ExamActivity.this, "New Exam Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(ExamActivity.this, "New Exam Not Inserted", Toast.LENGTH_SHORT).show();
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getExamdata();
                if(res.getCount()==0){
                    Toast.makeText(ExamActivity.this, "No Exam Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Code :"+res.getString(0)+"\n");
                    buffer.append("Subject :"+res.getString(1)+"\n");
                    buffer.append("Date :"+res.getString(2)+"\n");
                    buffer.append("Start time :"+res.getInt(3)+"\n");
                    buffer.append("End time :"+res.getInt(4)+"\n");
                    buffer.append("Status :"+res.getString(5)+"\n\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(ExamActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Exam Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String codeTXT = code.getText().toString();
                Boolean checkdeletedata = DB.deleteExamdata(codeTXT);
                if(checkdeletedata==true)
                    Toast.makeText(ExamActivity.this, "Exam Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(ExamActivity.this, "Exam Not Deleted", Toast.LENGTH_SHORT).show();
            }
        });
    }
}