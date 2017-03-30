package com.example.itimobiletrack.graduation_nano_program_iti.Login;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.itimobiletrack.graduation_nano_program_iti.R;

public class LoginRegisterActivity extends AppCompatActivity {

    private FragmentManager fm;
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register_phase);
        /*TextView txtTitle=(TextView)findViewById(R.id.xxxx);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/title.ttf");
        txtTitle.setTypeface(custom_font);*/


        // get fragment manager
         fm = getSupportFragmentManager();
        // add
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.xPlaceHolder, new LoginFragment());
        ft.addToBackStack(null);
        ft.commit();


    }


    public void toSignUp(View v){
        // replace
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.xPlaceHolder, new RegisterFragment());
        ft.addToBackStack(null);
        ft.commit();
    }


    public void toForgetPassword(View v){

        ForgetPass forgetPass=new ForgetPass();
        FragmentManager fm =getSupportFragmentManager();
        FragmentTransaction  ft=fm.beginTransaction();
        ft.replace(R.id.xPlaceHolder,forgetPass);
        //ft.addToBackStack(null);
        ft.commit();

    }

    @Override
    public void onBackPressed() {

//-------------------following lines are to handle when press back button on
// login fragment exit application ---------------------------//
        Intent i=new Intent(Intent.ACTION_MAIN);
        i.addCategory(Intent.CATEGORY_HOME);
        i.setFlags(i.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }
}
