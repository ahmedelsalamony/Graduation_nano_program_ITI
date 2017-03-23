package com.example.itimobiletrack.graduation_nano_program_iti.Charity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.itimobiletrack.graduation_nano_program_iti.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditFragment extends Fragment {

    Button save;
    Button cancel;


    public EditFragment() {
        // Required empty public constructor
    }

    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

    save = (Button) getActivity().findViewById(R.id.Save);
    save.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(getActivity(), "saving", Toast.LENGTH_SHORT).show();
        }
    });

    cancel = (Button) getActivity().findViewById(R.id.Cancel);
    cancel.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FragmentManager manager = getActivity().getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_main,new HomeFragment(),new HomeFragment().getTag()).commit();

        }
    });

}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit, container, false);
    }


}
