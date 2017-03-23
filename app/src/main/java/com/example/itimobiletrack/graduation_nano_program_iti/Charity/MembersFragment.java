package com.example.itimobiletrack.graduation_nano_program_iti.Charity;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.itimobiletrack.graduation_nano_program_iti.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class MembersFragment extends Fragment {

    FloatingActionButton fab;

    public MembersFragment() {
        // Required empty public constructor
    }

    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        fab = (FloatingActionButton) getActivity().findViewById(R.id.floatingActionButton);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_members, container, false);

        String[] members = {"mahmod","ahmed","mohamed","mostafa","shady","omar","kareem","said","salem","nour","peter","hossam","123","456","whatever"};

        ListView members_list = (ListView) view.findViewById(R.id.members);

        ArrayAdapter<String> membersAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,members);


        members_list.setAdapter(membersAdapter);
        // Inflate the layout for this fragment
        return view;
    }

}
