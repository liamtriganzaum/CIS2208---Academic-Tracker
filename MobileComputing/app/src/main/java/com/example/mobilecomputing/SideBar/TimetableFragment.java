package com.example.mobilecomputing.SideBar;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.mobilecomputing.DBHelper;
import com.example.mobilecomputing.LectureActivity;
import com.example.mobilecomputing.R;

public class TimetableFragment extends Fragment {

    //button when clicked takes you to lectureactivity
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_timetable, container, false);

        Button openLecture = (Button) v.findViewById(R.id.lecturebutton);
        openLecture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), LectureActivity.class);
                startActivity(in);
            }
        });

        return v;
    }
}
