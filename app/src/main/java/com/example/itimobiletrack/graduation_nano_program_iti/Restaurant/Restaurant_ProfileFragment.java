package com.example.itimobiletrack.graduation_nano_program_iti.Restaurant;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.itimobiletrack.graduation_nano_program_iti.R;


public class Restaurant_ProfileFragment extends Fragment {

    GridView gridView;
    static final String[] MOBILE_OS = new String[] {
            "Android", "iOS","Windows", "Blackberry" };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_restaurant__profile, container, false);
        gridView = (GridView) v.findViewById(R.id.gridView1);
        gridView.setAdapter(new ImageAdapterGrid(getActivity(), MOBILE_OS));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {



                Intent i=new Intent(getActivity(),SendRateActivity.class);
                startActivity(i);
            }
        });
        return v;

    }



}
