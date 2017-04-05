package com.example.itimobiletrack.graduation_nano_program_iti.Restaurant;

import android.app.Dialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.itimobiletrack.graduation_nano_program_iti.Charity.AboutFragment;
import com.example.itimobiletrack.graduation_nano_program_iti.Login.LoginRegisterActivity;
import com.example.itimobiletrack.graduation_nano_program_iti.R;
import com.example.itimobiletrack.graduation_nano_program_iti.Web.webServices;

public class RestaurantProfile extends AppCompatActivity {
    android.support.v4.app.FragmentManager fragmentManager;
    ImageView imageView;
     Toolbar toolbar;
     private  webServices web ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_profile);
        web =new webServices();
           web.sharedPreferences =getSharedPreferences("load_data",0);
        setTitle(web.sharedPreferences.getString("typename" , "******"));
        fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
       Restaurant_ProfileFragment restaurant_profileFragment = new Restaurant_ProfileFragment();
        fragmentTransaction.replace(R.id.xContainer, restaurant_profileFragment);
        fragmentTransaction.addToBackStack(String.valueOf(R.layout.fragment_login));
        fragmentTransaction.commit();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.fragment_restaurant_profile:
                Restaurant_ProfileFragment restaurant_profileFragment = new Restaurant_ProfileFragment();
                android.support.v4.app.FragmentManager fm =  getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.xContainer,restaurant_profileFragment).addToBackStack("tag");
                ft.commit();
                break;


            case R.id.fragment_about:

                AboutFragment aboutFragment =new AboutFragment();
                fm = getSupportFragmentManager();
                ft = fm.beginTransaction();
                ft.replace(R.id.xContainer , aboutFragment).addToBackStack("tag");
                ft.commit();
                break;


            case R.id.fragment_edit_restaurant_profile:

                fragmentManager = getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                EditRestaurantProfile editProfile = new EditRestaurantProfile();
                fragmentTransaction.replace(R.id.xContainer, editProfile);
                fragmentTransaction.addToBackStack("tag");
                fragmentTransaction.commit();
                break;



            case R.id.fragment_logout:
                SharedPreferences shared = getSharedPreferences("load_data", 0);
                SharedPreferences.Editor editor = shared.edit();
                editor.clear();
                editor.commit();
               Toast.makeText(this,shared.getString("username","") +"from logout", Toast.LENGTH_SHORT).show();
                 Intent intent = new Intent(RestaurantProfile.this,LoginRegisterActivity.class);
                startActivity(intent);
                this.finish();

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






















