package com.example.itimobiletrack.graduation_nano_program_iti.Restaurant;

import android.app.Dialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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
    FragmentManager fragmentManager;
    ImageView imageView;
     Toolbar toolbar;
     private  webServices web ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_profile);
        //this line to delete shadow between actionbar and tool bar
        getSupportActionBar().setElevation(0);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setTitle("");

         TextView txt=(TextView)findViewById(R.id.xTxtTitleBar);


          web =new webServices();
           web.sharedPreferences =getSharedPreferences("load_data",0);

          txt.setText(web.sharedPreferences.getString("typename" , "******"));


        fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Restaurant_ProfileFragment restaurantProfileFragment = new Restaurant_ProfileFragment();
        fragmentTransaction.replace(R.id.xContainer, restaurantProfileFragment);
        fragmentTransaction.addToBackStack(null);
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

            case R.id.fragment_about:

                AboutFragment aboutFragment =new AboutFragment();
                FragmentManager fm =getFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                ft.replace(R.id.xContainer , aboutFragment);
                ft.commit();
                break;


            case R.id.fragment_edit_restaurant_profile:

                fragmentManager = getFragmentManager();
                FragmentTransaction  fragmentTransaction = fragmentManager.beginTransaction();
                EditRestaurantProfile editProfile = new EditRestaurantProfile();
                fragmentTransaction.replace(R.id.xContainer, editProfile);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;



            case R.id.fragment_logout:
                 Intent intent = new Intent(this,LoginRegisterActivity.class);
                startActivity(intent);

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






















