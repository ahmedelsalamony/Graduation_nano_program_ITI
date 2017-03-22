package com.example.itimobiletrack.graduation_nano_program_iti;

import android.app.Dialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import static com.example.itimobiletrack.graduation_nano_program_iti.R.layout.dialog;

public class RestaurantProfile extends AppCompatActivity {
    FragmentManager fragmentManager;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_profile);


        fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Restaurant_ProfileFragment restaurantProfileFragment = new Restaurant_ProfileFragment();
        fragmentTransaction.add(R.id.xContainer, restaurantProfileFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        //imageView = (ImageView) findViewById(R.id.ximageView2);


        //----------------------------------------------------
        // EditProfile fragment

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
            case R.id.fragment_about:

                //Toast.makeText(this, "aboutdialog", Toast.LENGTH_SHORT).show();
                final Dialog dialog = new Dialog(RestaurantProfile.this);
                dialog.setContentView(R.layout.aboutinfodialog);
                dialog.setTitle("About Us");
                dialog.show();


                break;
            case R.id.fragment_edit_restaurant_profile:
                Toast.makeText(this, "this is edit profile fragment", Toast.LENGTH_SHORT).show();

                fragmentManager = getFragmentManager();
                FragmentTransaction  fragmentTransaction = fragmentManager.beginTransaction();
                EditRestaurantProfile editProfile = new EditRestaurantProfile();
                fragmentTransaction.replace(R.id.xContainer, editProfile);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

                break;
            case R.id.fragment_logout:
                // Intent intent = new Intent(this,Login.class);
                //startActivity(intent);
                Toast.makeText(this, "you will go to Login Screen", Toast.LENGTH_SHORT).show();


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




//
//            final  Dialog dialog2 = new Dialog(RestaurantProfile.this);
//            setContentView(R.layout.dialog2);
//            dialog2.setTitle("Rate Dialog");
//            dialog2.show();
//            dialog2.onBackPressed();

        }


}






















