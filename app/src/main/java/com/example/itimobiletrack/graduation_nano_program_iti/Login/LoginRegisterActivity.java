package com.example.itimobiletrack.graduation_nano_program_iti.Login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.itimobiletrack.graduation_nano_program_iti.Charity.CharityProfile;
import com.example.itimobiletrack.graduation_nano_program_iti.R;
import com.example.itimobiletrack.graduation_nano_program_iti.Restaurant.RestaurantProfile;

public class LoginRegisterActivity extends AppCompatActivity {

    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register_phase);


        SharedPreferences shared = getSharedPreferences("load_data", 0);
        String user = shared.getString("username", "");
        Toast.makeText(this, "user ==== " + user, Toast.LENGTH_SHORT).show();
        //------TODO when shared is empity-------------//
        if (user.equals("")) {
            Toast.makeText(this, "shared is empity", Toast.LENGTH_SHORT).show();
        }
        //--------------------------------------------//
        else {
            String usertype = shared.getString("type", "");
            if (usertype.equals("Restaurant")) {
                Intent i = new Intent(LoginRegisterActivity.this, RestaurantProfile.class);
                startActivity(i);
                this.finish();
            } else if (usertype.equals("Charity")) {
                Intent i = new Intent(LoginRegisterActivity.this, CharityProfile.class);
                startActivity(i);
                this.finish();
            }
        }
        // get fragment manager
        fm = getSupportFragmentManager();
        // add
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.xPlaceHolder, new LoginFragment());
        ft.addToBackStack("logingrament");
        ft.commit();
        //contextOfApplication = getApplicationContext();
    }


    public void toSignUp(View v) {
        // replace
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.xPlaceHolder, new RegisterFragment());
        ft.addToBackStack(null);
        ft.commit();
    }


    public void toForgetPassword(View v) {

        ForgetPass forgetPass = new ForgetPass();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.xPlaceHolder, forgetPass);
        ft.addToBackStack(null);
        ft.commit();
//////////////////
    }

    @Override
    public void onBackPressed() {


            int count = getFragmentManager().getBackStackEntryCount();
            if (fm.findFragmentByTag("logingrament") != null)
                count++;
            if (count == 0) {
                getFragmentManager().popBackStack();
                finish();
                //additional code
            } else if (count == 2) {
                super.onBackPressed();
            }

        }

    }
