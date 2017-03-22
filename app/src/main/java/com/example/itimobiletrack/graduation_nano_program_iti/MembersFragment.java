package com.example.itimobiletrack.graduation_nano_program_iti;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MembersFragment extends Fragment {


    public MembersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_members, container, false);

        String[] members = {"mahmod","ahmed","mohamed","mostafa","shady","omar","kareem","said","salem","nour","peter","hossam"};

        ListView members_list = (ListView) view.findViewById(R.id.members);

        ArrayAdapter<String> membersAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,members);


        members_list.setAdapter(membersAdapter);
        // Inflate the layout for this fragment
        return view;
    }

}
