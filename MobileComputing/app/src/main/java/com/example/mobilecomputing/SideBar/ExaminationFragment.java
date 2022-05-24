package com.example.mobilecomputing.SideBar;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mobilecomputing.LectureActivity;
import com.example.mobilecomputing.R;

public class ExaminationFragment extends Fragment {

    //button when clicked takes you to examactivity
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_examination, container, false);

        Button openAssignment = (Button) v.findViewById(R.id.exambutton);
        openAssignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), ExamActivity.class);
                startActivity(in);
            }
        });

        return v;
    }
}