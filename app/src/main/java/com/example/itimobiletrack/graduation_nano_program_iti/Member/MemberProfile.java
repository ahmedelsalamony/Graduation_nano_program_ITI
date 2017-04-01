package com.example.itimobiletrack.graduation_nano_program_iti.Member;

import android.content.Intent;
import android.os.Bundle;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.itimobiletrack.graduation_nano_program_iti.R;

public class MemberProfile extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_profile);
        FragmentManager fm =getFragmentManager();
        FragmentTransaction ft= fm.beginTransaction();
        ft.replace(R.id.xactivity_main , new MemberReceiveNotification());
        ft.commit();
    }



//    @Override
//    protected void onNewIntent(Intent intent) {
//
//        setIntent(intent);
///*
//        FragmentManager fm =getSupportFragmentManager();
//        FragmentTransaction ft= fm.beginTransaction();
//        ft.replace(R.id.activity_main , new MemberReceiveNotification());
//        ft.commit();
//*/
//
//
//    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.check_done, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.checkbox) {

            if (item.isChecked()) {
                item.setChecked(false);
                Toast.makeText(this, "unchecked", Toast.LENGTH_SHORT).show();
            } else {
                item.setChecked(true);
                Toast.makeText(this, "Task done", Toast.LENGTH_SHORT).show();
            }


        }
        return true;
    }


}
