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
import com.example.mobilecomputing.R;

public class AssignmentActivity extends AppCompatActivity {

    EditText code, subject, date, time;
    Button add, view, delete;
    DBHelper DB;

    //the add, view and delete methods for assignment
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment);

        code = findViewById(R.id.code);
        subject = findViewById(R.id.subject);
        date = findViewById(R.id.date);
        time = findViewById(R.id.time);
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
                String timeTXT = time.getText().toString();

                Boolean checkinsertdata = DB.insertAssignmentdata(codeTXT, subjectTXT, dateTXT, timeTXT);
                if(checkinsertdata==true)
                    Toast.makeText(AssignmentActivity.this, "New Assignment Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(AssignmentActivity.this, "New Assignment Not Inserted", Toast.LENGTH_SHORT).show();
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getAssignmentdata();
                if(res.getCount()==0){
                    Toast.makeText(AssignmentActivity.this, "No Assignment Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Code :"+res.getString(0)+"\n");
                    buffer.append("Subject :"+res.getString(1)+"\n");
                    buffer.append("Date :"+res.getString(2)+"\n");
                    buffer.append("Submission Time :"+res.getInt(3)+"\n\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(AssignmentActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Assignment Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String codeTXT = code.getText().toString();
                Boolean checkdeletedata = DB.deleteAssignmentdata(codeTXT);
                if(checkdeletedata==true)
                    Toast.makeText(AssignmentActivity.this, "Assignment Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(AssignmentActivity.this, "Assignment Not Deleted", Toast.LENGTH_SHORT).show();
            }
        });
    }
}