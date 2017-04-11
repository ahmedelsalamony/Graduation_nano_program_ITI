package com.example.itimobiletrack.graduation_nano_program_iti.Charity;


import android.app.Dialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.itimobiletrack.graduation_nano_program_iti.R;
import com.example.itimobiletrack.graduation_nano_program_iti.Web.request_interface;
import com.example.itimobiletrack.graduation_nano_program_iti.Web.webServices;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 */


public class MembersFragment extends Fragment {

    private FloatingActionButton fab;
    private webServices web;
    private  StringBuffer userNameBuffer;
    private  StringBuffer idBuffer;
    private String[] members ;



    public MembersFragment() {
        // Required empty public constructor
    }

    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_members, container, false);


        web = new webServices();
        web.sharedPreferences = getActivity().getSharedPreferences("load_data",0);
        userNameBuffer = new StringBuffer();
        idBuffer =new StringBuffer();

        final ListView members_list = (ListView) view.findViewById(R.id.members);
        fab = (FloatingActionButton) view.findViewById(R.id.floatingActionButton);



         // TODO call getMemberData


        Toast.makeText(getActivity()," before"+web.sharedPreferences.getInt("id",2017),Toast.LENGTH_SHORT).show();
        // TODO ERRRORRRRRRRRRRRR
            if(web.sharedPreferences.getInt("id",2017) != 0) {
                Toast.makeText(getActivity(),"after"+web.sharedPreferences.getInt("id",2017),Toast.LENGTH_SHORT).show();


                int id = web.sharedPreferences.getInt("id",2017);
                web.getMemberData(getActivity(), id, new request_interface() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            JSONObject jsonResponse = new JSONObject(response);
                            JSONArray jsonArray = jsonResponse.getJSONArray("search");
                            Log.i("json:  " ,""+jsonArray);

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject search_object = jsonArray.getJSONObject(i);
                                userNameBuffer.append(search_object.getString("username") + "#");
                                System.out.println(userNameBuffer.toString());
                            }


                            members = userNameBuffer.toString().split("#");
                            ArrayAdapter membersAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, members);
                            members_list.setAdapter(membersAdapter);


                        } catch (JSONException e) {
                           // Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError() {
//                        MembersFragment membersFragment = new MembersFragment();
//                        android.support.v4.app.FragmentManager manager= getActivity().getSupportFragmentManager();
//                        android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();
//                        transaction.replace(R.id.content_main,membersFragment).commit();
                        Snackbar.make(getActivity().findViewById(android.R.id.content), "Internet Connection Fair", Snackbar.LENGTH_LONG).show();

                    }
                });
            }else {

                Snackbar.make(getActivity().findViewById(android.R.id.content), "Internet Connection Fair  - Reload Again - ", Snackbar.LENGTH_LONG).show();

            }


           // TODO Action member_list
        members_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final Dialog dialog =new Dialog(getActivity());
                dialog.setContentView(R.layout.member_data_dialog);
                dialog.setTitle("Member Information");
                dialog.show();

                final TextView userName= (TextView) dialog.findViewById(R.id.xUserNameTextView);
                final TextView phone= (TextView) dialog.findViewById(R.id.xPhoneTextView);
                final TextView address= (TextView) dialog.findViewById(R.id.xAddressTextView);
                final TextView status= (TextView) dialog.findViewById(R.id.xStatusTextView);
                final Button   deleteBtn= (Button) dialog.findViewById(R.id.xDeleteBtn);

                // TODO call of member data ( username - phone - address - status )

                web.getMemberDataOfDialog(getActivity(), members[i], new request_interface() {
                    @Override
                    public void onResponse(String response) {

                     try{
                        JSONObject jsonObject = new JSONObject(response);
                        String login_response = jsonObject.getString("response");

                        if (login_response.equals("done")) {
                             userName.setText(jsonObject.getString("username"));
                            phone.setText(jsonObject.getString("phone"));
                            address.setText(jsonObject.getString("address"));
                            String memberStatus=jsonObject.getString("status_of_member");
                              if(memberStatus.equals("0")){
                                  status.setText("onLine");
                              }else {
                                  status.setText("offLone");
                              }
                    deleteBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            web.deleteMember((AppCompatActivity) getActivity(), userName.getText().toString());
                        }
                    });
                        }}catch (JSONException j){
                         Toast.makeText(getActivity(), ""+j.getMessage(), Toast.LENGTH_SHORT).show();
                     }}
                    @Override
                    public void onError() {

                    }
                });
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                android.support.v4.app.FragmentManager manager = getActivity().getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.content_main,new AddMemberFragment());
                transaction.addToBackStack("");
                transaction.commit();

            }
        });
        // Inflate the layout for this fragment
        return view;
    }

}
