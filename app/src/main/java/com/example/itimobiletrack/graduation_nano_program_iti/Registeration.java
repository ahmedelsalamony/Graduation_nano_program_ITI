package com.example.itimobiletrack.graduation_nano_program_iti;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class Registeration extends AppCompatActivity {

    TextView txtTitle;
    EditText edtUserName,edtPassword,edtConfirmPassword,edtEmail,edtPhone,edtAddress,edtCharity,edtRestaurant;
    RadioButton rdCharity,rdRestaurant;
    TextInputLayout layCharity,layRestaurant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);
        txtTitle=(TextView)findViewById(R.id.xTitle);
        edtUserName=(EditText)findViewById(R.id.xUserName);
        edtPassword=(EditText)findViewById(R.id.xPassword);
        edtConfirmPassword=(EditText)findViewById(R.id.xConfirmPassword);
        edtEmail=(EditText)findViewById(R.id.xEmail);
        edtPhone=(EditText)findViewById(R.id.xPhone);
        edtAddress=(EditText)findViewById(R.id.xAddress);
        edtCharity=(EditText)findViewById(R.id.xCharityName);
        edtRestaurant=(EditText)findViewById(R.id.xRestuarantName);
        rdCharity=(RadioButton)findViewById(R.id.xrdCharity);
        rdRestaurant=(RadioButton)findViewById(R.id.xrdRestaurant);
        layCharity=(TextInputLayout)findViewById(R.id.input_layout_CharityName);
        layRestaurant=(TextInputLayout)findViewById(R.id.input_layout_RestuarantName);
        edtRestaurant.setVisibility(View.INVISIBLE);
        edtCharity.setVisibility(View.INVISIBLE);

        if (rdCharity.isChecked()) {
            edtCharity.setVisibility(View.VISIBLE);
        } else if (!rdCharity.isChecked()){
            edtRestaurant.setVisibility(View.VISIBLE);
            edtCharity.setVisibility(View.VISIBLE);
        }else if (rdRestaurant.isChecked()){
            edtCharity.setVisibility(View.GONE);
            edtRestaurant.setVisibility(View.VISIBLE);
        }else if (!rdRestaurant.isChecked()){
            edtRestaurant.setVisibility(View.VISIBLE);
            edtCharity.setVisibility(View.VISIBLE);
        }

    }
}
