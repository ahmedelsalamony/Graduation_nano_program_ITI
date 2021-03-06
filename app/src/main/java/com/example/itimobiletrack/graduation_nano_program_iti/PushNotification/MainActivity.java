package com.example.itimobiletrack.graduation_nano_program_iti.PushNotification;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.itimobiletrack.graduation_nano_program_iti.R;
import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //defining views
    private Button buttonRegister;
    private EditText editTextEmail;
    private ProgressDialog progressDialog;
    private Button buttonSendPush;

    //URL to RegisterDevice.php
    private static final String URL_REGISTER_DEVICE = "http://172.16.5.112/FcmExample/RegisterDevice.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getting views from xml
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        buttonRegister = (Button) findViewById(R.id.buttonRegister);
        buttonSendPush=(Button)findViewById(R.id.buttonSendNotification);

        //adding listener to view
        buttonRegister.setOnClickListener(this);
        buttonSendPush.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == buttonRegister){
            sendTokenToServer();
        }
        //starting send notification activity
        if(view == buttonSendPush){
            startActivity(new Intent(this, ActivitySendPushNotification.class));
        }

    }

    private void sendTokenToServer() {
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Registering Device...");
        progressDialog.show();

        final String token=SharedPrefManager.getInstance(this).getDeviceToken();
        final String email=editTextEmail.getText().toString();

        if (token == null){
            progressDialog
                    .dismiss();
            Toast.makeText(this, "there is no token generated", Toast.LENGTH_SHORT).show();
            return;
        }
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGISTER_DEVICE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject obj = new JSONObject(response);
                            Toast.makeText(MainActivity.this, obj.getString("message"), Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("token", token);
                return params;
            } };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);




    }
}

//
