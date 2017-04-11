package com.example.itimobiletrack.graduation_nano_program_iti.Restaurant;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.itimobiletrack.graduation_nano_program_iti.Charity.AboutFragment;
import com.example.itimobiletrack.graduation_nano_program_iti.Login.LoginRegisterActivity;
import com.example.itimobiletrack.graduation_nano_program_iti.R;
import com.example.itimobiletrack.graduation_nano_program_iti.Web.webServices;

public class RestaurantProfile extends AppCompatActivity {

     android.support.v4.app.FragmentManager fragmentManager;
     ImageView imageView;
     Toolbar toolbar;
     private  webServices web;
      public   static boolean flag = false;
     android.support.v7.app.ActionBar actionBar;

int i =0;
    Restaurant_ProfileFragment restaurant_profileFragment;

    android.support.v4.app.FragmentManager fm ;
    android.support.v4.app.FragmentTransaction ft ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionBar = getSupportActionBar();
        setContentView(R.layout.activity_restaurant_profile);



        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
//        getSupportActionBar().setDisplayShowHomeEnabled(false);
//        getSupportActionBar().setHomeButtonEnabled(false);


        web = new webServices();
        web.sharedPreferences = getSharedPreferences("load_data", 0);
        setTitle(web.sharedPreferences.getString("typename", "******"));


         restaurant_profileFragment = new Restaurant_ProfileFragment();

        android.support.v4.app.FragmentManager fm =  getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.xContainer, restaurant_profileFragment);
        fragmentTransaction.commit();


    }


//==============================================================


    @Override
    public void onBackPressed() {
       // super.onBackPressed();
     i++;
    if(i==1){
        Toast.makeText(this, "press again to Exit.", Toast.LENGTH_SHORT).show();

    }else if(i==2){

        finish();

    }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        if(flag==true) {
            inflater.inflate(R.menu.menu, menu);
        }else if(flag==false) {
            inflater.inflate(R.menu.menu_alternative, menu);


        }
     return true;

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        android.support.v4.app.FragmentManager fm =  getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction =fm.beginTransaction();
        switch (item.getItemId()) {

            case android.R.id.home:
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);

                fragmentTransaction.replace(R.id.xContainer,restaurant_profileFragment).commit();
                break;


            case R.id.fragment_about:

                flag=false;
//                final Dialog dialog_about = new Dialog(this);
//                dialog_about.setContentView(R.layout.dialog_about);
//                dialog_about.show();
//                AboutFragment aboutFragment =new AboutFragment();
//
//                fm = getSupportFragmentManager();
//                fragmentTransaction = fm.beginTransaction();
//                fragmentTransaction.replace(R.id.xContainer , aboutFragment);
//                fragmentTransaction.commit();


                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//                getSupportActionBar().setDisplayShowHomeEnabled(false);
//                getSupportActionBar().setHomeButtonEnabled(false);
                final Dialog dialog = new Dialog(this) ;
                dialog.setContentView(R.layout.dialog_about);
                dialog.setTitle("About");
                dialog.show();
                break;



            case  R.id.fragment_edit_restaurant_profile:

                EditRestaurantProfile editProfile = new EditRestaurantProfile();
                fragmentTransaction.replace(R.id.xContainer, editProfile);
//                fragmentTransaction.addToBackStack("tag");
                fragmentTransaction.commit();

                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//                getSupportActionBar().setDisplayShowHomeEnabled(false);
//                getSupportActionBar().setHomeButtonEnabled(false);


                break;


            case R.id.fragment_logout:

                SharedPreferences shared = getSharedPreferences("load_data", 0);
                SharedPreferences.Editor editor = shared.edit();
                editor.clear();
                editor.commit();


                Intent intent = new Intent(RestaurantProfile.this,LoginRegisterActivity.class);
                 startActivity(intent);
                 this.finish();
                //===========================================

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
        }

        return super.onOptionsItemSelected(item);
    }



    public void onClick(View v) {
        final Dialog dialog1 = new Dialog(RestaurantProfile.this);
        setContentView(R.layout.dialog1);

        dialog1.show();

        switch (v.getId())
        {
            case R.id.xbtnsend_request:
            final Dialog dialog = new Dialog(RestaurantProfile.this);
            setContentView(R.layout.aboutinfodialog);
            dialog.setTitle("Request Dialog");
            dialog.show();
                break;
            case R.id.xbtnRate:
                final Dialog dialog2 = new Dialog(RestaurantProfile.this);
                setContentView(R.layout.dialog2);
                dialog2.show();
                break;

        }


        }


}






















