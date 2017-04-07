package com.example.itimobiletrack.graduation_nano_program_iti.Charity;

import android.app.Dialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.itimobiletrack.graduation_nano_program_iti.Login.LoginRegisterActivity;
import com.example.itimobiletrack.graduation_nano_program_iti.R;
import com.example.itimobiletrack.graduation_nano_program_iti.Web.webServices;

public class CharityProfile extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,FragmentManager.OnBackStackChangedListener {


    FragmentManager manager;
    private webServices web ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charity_profile);

        web =new webServices();
        web.sharedPreferences = getSharedPreferences("load_data" , 0);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        HomeFragment homeFragment = new HomeFragment();
        manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.content_main,homeFragment);
        transaction.addToBackStack("home");
        transaction.commit();
        //manager.beginTransaction().replace(R.id.content_main,homeFragment,homeFragment.getTag()).commit();
       //getSupportActionBar().setHomeAsUpIndicator(R.drawable.green);

//
//          toolbar.setTitle(getIntent().getStringExtra("typename"));
//          setTitle(getIntent().getStringExtra("typename"));


          toolbar.setTitle(web.sharedPreferences.getString("typename" , "******"));
          setTitle(web.sharedPreferences.getString("typename" , "******"));


        /*Default fragment*/

//        /*the floating action button declaration and action*/
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                /*Action*/
//            }
//        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        View headerView = navigationView.getHeaderView(0);
        TextView txtTypeName = (TextView) headerView.findViewById(R.id.textView2);
        TextView txtEmail = (TextView) headerView.findViewById(R.id.textView);



         txtTypeName.setText(web.sharedPreferences.getString("typename" , "******"));

         txtEmail.setText(web.sharedPreferences.getString("email" , "******"));

    }

    @Override
    public void onBackPressed() {
        LoginRegisterActivity loginRegisterActivity=new LoginRegisterActivity();
        SharedPreferences shared = getSharedPreferences("load_data", 0);
        String user = shared.getString("username", "");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            HomeFragment homeFragment = new HomeFragment();
            FragmentManager manager = getFragmentManager();
            //manager.beginTransaction().replace(R.id.content_main,homeFragment,homeFragment.getTag()).commit();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.content_main,homeFragment);
            //transaction.addToBackStack("home");
            transaction.commit();
        } else if (id == R.id.nav_members) {
            MembersFragment membersFragment = new MembersFragment();
            FragmentManager manager = getFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.content_main,membersFragment,membersFragment.getTag());
            transaction.addToBackStack("members");
            transaction.commit();
            //manager.beginTransaction().replace(R.id.content_main,membersFragment,membersFragment.getTag()).commit();

        } else if (id == R.id.nav_edit) {
            EditFragment editFragment = new EditFragment();
            FragmentManager manager = getFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.content_main,editFragment,editFragment.getTag());
            transaction.addToBackStack("edit");
            transaction.commit();
            //transaction.replace(R.id.content_main,editFragment,editFragment.getTag()).commit();

        } else if (id == R.id.nav_about) {

            final Dialog dialog = new Dialog(this) ;
            dialog.setContentView(R.layout.dialog_about);
            dialog.setTitle("About");
            dialog.show();

        } else if (id == R.id.nav_logout) {

            SharedPreferences shared = getSharedPreferences("load_data", 0);
            SharedPreferences.Editor editor = shared.edit();
            editor.clear();
            editor.commit();
            Toast.makeText(this,shared.getString("username","") +"from logout", Toast.LENGTH_SHORT).show();
            Intent intent  = new Intent(CharityProfile.this, LoginRegisterActivity.class);
            startActivity(intent);
            this.finish();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackStackChanged() {
//        FragmentTransaction transaction = manager.beginTransaction();
//        transaction.replace(R.id.content_main,new HomeFragment()).commit();
    }
}
