package com.example.itimobiletrack.graduation_nano_program_iti.Login;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.itimobiletrack.graduation_nano_program_iti.R;

public class LoginRegisterActivity extends AppCompatActivity {

    private  FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register_phase);


        // get fragment manager
         fm = getFragmentManager();
        // add
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.xPlaceHolder, new LoginFragment());
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
        FragmentManager fm =getFragmentManager();
        FragmentTransaction  ft=fm.beginTransaction();
        ft.replace(R.id.xPlaceHolder,forgetPass);
        ft.addToBackStack(null);
        ft.commit();

    }



}