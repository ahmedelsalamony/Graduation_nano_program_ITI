package com.example.itimobiletrack.graduation_nano_program_iti;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import static android.support.design.R.styleable.TextInputLayout;

public class Registeration extends AppCompatActivity {

    TextView txtTitle;
    EditText edtUserName,edtPassword,edtConfirmPassword,edtEmail,edtPhone,edtAddress,edtCharity;
    RadioButton rdCharity,rdRestaurant;
    TextInputLayout layCharity;


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
        rdCharity=(RadioButton)findViewById(R.id.xrdCharity);
        rdRestaurant=(RadioButton)findViewById(R.id.xrdRestaurant);
        layCharity=(TextInputLayout)findViewById(R.id.input_layout_CharityName);
        txtTitle= (TextView) findViewById(R.id.xTitle);




        layCharity.setVisibility(View.INVISIBLE);
        edtCharity.setVisibility(View.INVISIBLE);



        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/title.ttf");
        txtTitle.setTypeface(custom_font);


        rdCharity.setOnTouchListener(new View.OnTouchListener() {
               @Override
               public boolean onTouch(View view, MotionEvent motionEvent) {

                   layCharity.setVisibility(View.VISIBLE);
                   edtCharity.setVisibility(View.VISIBLE);
                   edtCharity.setHint("Charity name");
                   return false;
               }
           });

        rdRestaurant.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                layCharity.setVisibility(View.VISIBLE);
                edtCharity.setVisibility(View.VISIBLE);
                edtCharity.setHint("Restaurant name");
                return false;
            }
        });

        }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Registeration.this,LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
