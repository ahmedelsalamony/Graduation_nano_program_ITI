package com.example.itimobiletrack.graduation_nano_program_iti.Charity;


import android.graphics.Typeface;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.itimobiletrack.graduation_nano_program_iti.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DefaultNoRequestsFragment extends Fragment {


    public DefaultNoRequestsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View v=inflater.inflate(R.layout.fragment_default_no_requests, container, false);

        TextView txNoRequests= (TextView) v.findViewById(R.id.xNoRequestsTextView);
        Typeface custom_font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/title.ttf");
        txNoRequests.setTypeface(custom_font);

        return  v;

    }

}
