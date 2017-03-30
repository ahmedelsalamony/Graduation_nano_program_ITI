package com.example.itimobiletrack.graduation_nano_program_iti.Member;

import android.app.Dialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
import com.example.itimobiletrack.graduation_nano_program_iti.PushNotification.ActivitySendPushNotification;
import com.example.itimobiletrack.graduation_nano_program_iti.PushNotification.EndPoints;
import com.example.itimobiletrack.graduation_nano_program_iti.PushNotification.MyVolley;
import com.example.itimobiletrack.graduation_nano_program_iti.R;
import com.example.itimobiletrack.graduation_nano_program_iti.Web.webServices;

import java.util.HashMap;
import java.util.Map;

public class MemberProfile extends AppCompatActivity {
    ListView listView;
    private webServices web;
    String tasks[] = null;
    int my_id;
    String type_Name;
    String note[] =null;
    ProgressDialog progressDialog;
    Dialog taskdialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_profile);
        web = new webServices();
        progressDialog =new ProgressDialog(MemberProfile.this);
        web.sharedPreferences = getSharedPreferences("load_data", 0);

        setTitle(web.sharedPreferences.getString("username", "******"));
        my_id = web.sharedPreferences.getInt("id", 2017);
        // Get ListView object from xml
        listView = (ListView) findViewById(R.id.list);
        type_Name=getIntent().getStringExtra("title");
        // Defined Array values to show in ListView

        tasks = new String[]{"Task From admin"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, tasks);


        // Assign adapter to ListView
        listView.setAdapter(adapter);

        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {


                taskdialog = new Dialog(MemberProfile.this);
                taskdialog.setContentView(R.layout.taskdialog);
                taskdialog.setTitle(type_Name);
                taskdialog.show();
                TextView taskName_tv = (TextView) taskdialog.findViewById(R.id.xtaskname);
                TextView taskQuantity_tv = (TextView) taskdialog.findViewById(R.id.xtasQuantity);
                TextView taskEstimatedTime_tv = (TextView) taskdialog.findViewById(R.id.xtaskEstimatedtime);


                Button acceptBtn = (Button) taskdialog.findViewById(R.id.xbtnAccept);
                Button rejectBtn = (Button) taskdialog.findViewById(R.id.xbtReject);
                String message = getIntent().getStringExtra("message");
                note= message.split("#");

                taskName_tv.setText(note[2]);
                taskQuantity_tv.setText(note[3]);
                taskEstimatedTime_tv.setText(note[4]);


                System.out.println("mahmoud belal" + getIntent().getStringExtra("message"));
                acceptBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        sendToCharity();
                        sendToResturant();

//

                        // To update Charity_tasks Table
                        web.addCharityTask(MemberProfile.this, Integer.parseInt(web.sharedPreferences.getString("charity_parent_id", "*****")), Integer.parseInt(note[1]));
                        //to update Tasks Table
                        web.updateTask(MemberProfile.this, my_id, Integer.parseInt(note[1]));





                    }
                });


                rejectBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });

            }

        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.check_done, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.checkbox) {

            if (item.isChecked()) {
                item.setChecked(false);
                Toast.makeText(this, "unchecked", Toast.LENGTH_SHORT).show();
            } else {
                item.setChecked(true);
                Toast.makeText(this, "Task done", Toast.LENGTH_SHORT).show();
            }


        }
        return true;
    }

    // TODO
    public void sendToResturant (){


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
                params.put("message", web.sharedPreferences.getString("username", "******"));

                params.put("typename", type_Name);

                return params;
            }
        };

        MyVolley.getInstance(this).

                addToRequestQueue(stringRequest);


    }


    // TODO
    public void sendToCharity() {

        progressDialog.setMessage("Sending Push");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, EndPoints.URL_SEND_SINGLE_PUSH,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        Toast.makeText(MemberProfile.this, ""+note[0] + "     "+note[1], Toast.LENGTH_SHORT).show();
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
                params.put("message", "to charity ");

                params.put("typename", note[0]);

                return params;
            }
        };

        MyVolley.getInstance(this).

                addToRequestQueue(stringRequest);


    }



}
