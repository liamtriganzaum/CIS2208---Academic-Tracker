package com.example.mobilecomputing;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class LectureActivity extends AppCompatActivity {

    EditText day, code, subject, start, finish;
    Button add, view, delete;
    DBHelper DB;

    //the add, view and delete methods for lectures
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecture);

        code = findViewById(R.id.code);
        day = findViewById(R.id.day);
        subject = findViewById(R.id.subject);
        start = findViewById(R.id.starttime);
        finish = findViewById(R.id.endtime);
        add = findViewById(R.id.add);
        view = findViewById(R.id.view);
        delete = findViewById(R.id.delete);
        DB = new DBHelper(this);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String codeTXT = code.getText().toString();
                String dayTXT = day.getText().toString();
                String subjectTXT = subject.getText().toString();
                String startTXT = start.getText().toString();
                String finishTXT = finish.getText().toString();

                Boolean checkinsertdata = DB.insertLecturedata(codeTXT, dayTXT, subjectTXT, startTXT, finishTXT);
                if(checkinsertdata==true)
                    Toast.makeText(LectureActivity.this, "New Lecture Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(LectureActivity.this, "New Lecture Not Inserted", Toast.LENGTH_SHORT).show();
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getLecturedata();
                if(res.getCount()==0){
                    Toast.makeText(LectureActivity.this, "No Lecture Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Code :"+res.getString(0)+"\n");
                    buffer.append("Day :"+res.getString(1)+"\n");
                    buffer.append("Subject :"+res.getString(2)+"\n");
                    buffer.append("Start time :"+res.getInt(3)+"\n");
                    buffer.append("End time :"+res.getInt(4)+"\n\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(LectureActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Lecture Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String codeTXT = code.getText().toString();
                Boolean checkdeletedata = DB.deleteLecturedata(codeTXT);
                if(checkdeletedata==true)
                    Toast.makeText(LectureActivity.this, "Lecture Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(LectureActivity.this, "Lecture Not Deleted", Toast.LENGTH_SHORT).show();
            }
        });
    }
}