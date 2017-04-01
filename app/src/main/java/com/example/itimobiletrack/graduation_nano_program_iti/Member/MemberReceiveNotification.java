package com.example.itimobiletrack.graduation_nano_program_iti.Member;


import android.app.Dialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.itimobiletrack.graduation_nano_program_iti.PushNotification.EndPoints;
import com.example.itimobiletrack.graduation_nano_program_iti.PushNotification.MyVolley;
import com.example.itimobiletrack.graduation_nano_program_iti.R;
import com.example.itimobiletrack.graduation_nano_program_iti.Web.request_interface;
import com.example.itimobiletrack.graduation_nano_program_iti.Web.webServices;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */

public class MemberReceiveNotification extends Fragment {

    Button acceptBtn;
    ListView listView;
    TextView noTaskTv;
    private webServices web;
    String tasks[] = null;
    int my_id;
    String type_Name;
    String note[] ={};
    ProgressDialog progressDialog;
    Dialog taskdialog;
    int xx;

    static  String address ,quantity , time;


    String status=null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
     View v=  inflater.inflate(R.layout.fragment_member_receive_notification, container, false);


        web = new webServices();
        progressDialog =new ProgressDialog(getActivity());
        web.sharedPreferences = getActivity().getSharedPreferences("load_data", 0);
        noTaskTv= (TextView) v.findViewById(R.id.xNoTasksSent_tv);



        Typeface custom_font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/title.ttf");
        noTaskTv.setTypeface(custom_font);

        noTaskTv.setVisibility(View.INVISIBLE);
        getActivity().setTitle(web.sharedPreferences.getString("username", "******"));
        xx=  Integer.parseInt(web.sharedPreferences.getString("charity_parent_id", "*****"));

        my_id = web.sharedPreferences.getInt("id", 2017);

        // Get ListView object from xml
        listView = (ListView) v.findViewById(R.id.list);
        type_Name=getActivity().getIntent().getStringExtra("title");
        // Defined Array values to show in ListView

        // TODO Chack for request_status




        tasks = new String[]{"Task From admin"};



        if(type_Name == null){

            listView.setVisibility(View.INVISIBLE);
            noTaskTv.setVisibility(View.VISIBLE);


        }


        else {



            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_list_item_1, android.R.id.text1, tasks);

            listView.setAdapter(adapter);
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    String message = getActivity().getIntent().getStringExtra("message");

                    note = message.split("#");

                    taskdialog = new Dialog(getActivity());
                    taskdialog.setContentView(R.layout.taskdialog);
                    taskdialog.setTitle(type_Name);
                    taskdialog.show();

                    TextView taskName_tv = (TextView) taskdialog.findViewById(R.id.xtaskname);
                    TextView taskQuantity_tv = (TextView) taskdialog.findViewById(R.id.xtasQuantity);
                    TextView taskEstimatedTime_tv = (TextView) taskdialog.findViewById(R.id.xtaskEstimatedtime);

                    // TODO   >> Handle Exception

                     acceptBtn = (Button) taskdialog.findViewById(R.id.xbtnAccept);
                     Button rejectBtn = (Button) taskdialog.findViewById(R.id.xbtReject);



                    taskName_tv.setText(note[2]);
                    taskQuantity_tv.setText(note[3]);
                    taskEstimatedTime_tv.setText(note[4]);

                    address=note[2];
                    quantity=note[3];
                    time=note[4];



                    // TODO Check







                    // End Check

          acceptBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {


                            web.checkRequestTask(getActivity(), Integer.parseInt(note[1]), new request_interface() {
                                @Override
                                public void onResponse(String response) {

                                    try {
                                        JSONObject jsonResponse = new JSONObject(response);
                                        Toast.makeText(getActivity(), ""+jsonResponse.toString(), Toast.LENGTH_SHORT).show();
                                        JSONArray jsonArray = jsonResponse.getJSONArray("check");

                                        Toast.makeText(getActivity(), ""+jsonArray.toString(), Toast.LENGTH_SHORT).show();

                                        for (int i = 0; i < jsonArray.length(); i++) {
                                            JSONObject search_object = jsonArray.getJSONObject(i);
                                            status =search_object.getString("request_status") ;
                                            Toast.makeText(getActivity(), "" + status.toString(), Toast.LENGTH_SHORT).show();
                                        }

                                        if(status.equals("Confirmed")){
                                            acceptBtn.setVisibility(View.INVISIBLE);

                                        }
                                        else {


                                            // To update Charity_tasks Table
                                            web.updateTask(getActivity(), my_id, Integer.parseInt(note[1]));
                                            web.addCharityTask(getActivity(), xx, Integer.parseInt(note[1]));


                                            sendToRstaurant();
                                            sendToCharity();
                                            //to update Tasks Table
                                            taskdialog.dismiss();
                                            FragmentManager fragmentManager = getActivity().getFragmentManager();
                                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                            fragmentTransaction.replace(R.id.xactivity_main, new Member_GoogleMap()).commit();


                                        }


                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }

                                @Override
                                public void onError() {

                                    Toast.makeText(getActivity(), "error y basha ", Toast.LENGTH_SHORT).show();

                                }
                            });




//
//-----------------------------------------------------------






                        }
                    });


                    rejectBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    });


            }

        });



return   v;

    }



    // TODO
    public void sendToCharity (){


        progressDialog.setMessage("Sending Push");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, EndPoints.URL_SEND_SINGLE_PUSH,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("title", note[0]);
                params.put("message", "to charity");

                params.put("typename", note[0]);

                return params;
            }
        };

        MyVolley.getInstance(getActivity()).

                addToRequestQueue(stringRequest);


    }


    // TODO
    public void sendToRstaurant() {

        progressDialog.setMessage("Sending Push");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, EndPoints.URL_SEND_SINGLE_PUSH,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("title", type_Name);
                params.put("message", "to Restaurant ");

                params.put("typename", type_Name);

                return params;
            }
        };

        MyVolley.getInstance(getActivity()).addToRequestQueue(stringRequest);

    }

public  void checked()
    {

    }


}
