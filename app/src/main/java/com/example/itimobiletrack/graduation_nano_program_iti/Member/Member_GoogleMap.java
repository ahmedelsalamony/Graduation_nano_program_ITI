package com.example.itimobiletrack.graduation_nano_program_iti.Member;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.itimobiletrack.graduation_nano_program_iti.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Member_GoogleMap extends Fragment {

    Button done;
    public Member_GoogleMap() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v=  inflater.inflate(R.layout.fragment_member__google_map, container, false);


      done = (Button) v.findViewById(R.id.xDoneBtn);
       done.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {



           }
       });

        return  v;
    }

}
