package com.example.itimobiletrack.graduation_nano_program_iti.Charity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.itimobiletrack.graduation_nano_program_iti.Web.GetProfileInfo;
import com.example.itimobiletrack.graduation_nano_program_iti.Login.LoginRegisterActivity;
import com.example.itimobiletrack.graduation_nano_program_iti.R;

public class CharityProfile extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private GetProfileInfo getProfileInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charity_profile);

        getProfileInfo = new GetProfileInfo();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        HomeFragment homeFragment = new HomeFragment();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.content_main,homeFragment,homeFragment.getTag()).commit();
//        getSupportActionBar().setHomeAsUpIndicator(R.drawable.green);


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

         txtTypeName.setText(getIntent().getStringExtra("typename"));

         txtEmail.setText(getIntent().getStringExtra("email"));

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
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
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_main,homeFragment,homeFragment.getTag()).commit();
        } else if (id == R.id.nav_members) {
            MembersFragment membersFragment = new MembersFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_main,membersFragment,membersFragment.getTag()).commit();

        } else if (id == R.id.nav_edit) {
            EditFragment editFragment = new EditFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_main,editFragment,editFragment.getTag()).commit();

        } else if (id == R.id.nav_about) {
            AboutFragment aboutFragment = new AboutFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_main,aboutFragment,aboutFragment.getTag()).commit();

        } else if (id == R.id.nav_logout) {
            Intent intent  = new Intent(CharityProfile.this, LoginRegisterActivity.class);
            startActivity(intent);
            finish();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
