package com.example.itimobiletrack.graduation_nano_program_iti.Charity;


import android.graphics.Typeface;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.itimobiletrack.graduation_nano_program_iti.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class AboutFragment extends Fragment {

    ImageView image;



    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        image = (ImageView) getActivity().findViewById(R.id.xlogo);
        image.setImageResource(R.drawable.green);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_about, container, false);


        TextView txtTitle= (TextView) v.findViewById(R.id.xaboutTxtView);

             Typeface custom_font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/title.ttf");
             txtTitle.setTypeface(custom_font);

    return  v;
    }

}