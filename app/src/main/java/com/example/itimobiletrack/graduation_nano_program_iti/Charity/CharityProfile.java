package com.example.itimobiletrack.graduation_nano_program_iti.Charity;

import android.app.Dialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.itimobiletrack.graduation_nano_program_iti.Login.LoginFragment;
import com.example.itimobiletrack.graduation_nano_program_iti.Login.LoginRegisterActivity;
import com.example.itimobiletrack.graduation_nano_program_iti.R;
import com.example.itimobiletrack.graduation_nano_program_iti.Restaurant.RestaurantProfile;
import com.example.itimobiletrack.graduation_nano_program_iti.Web.webServices;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

public class CharityProfile extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener
{

    private webServices web ;
    private Drawer drawer;
    private AccountHeader headerResult;
    android.support.v4.app.FragmentTransaction transaction;
    android.support.v4.app.FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charity_profile);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        /*load home fragment*/
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.replace(R.id.content_main,new HomeFragment(),new HomeFragment().getTag());
        transaction.commit();
        /*/*load home fragment*/


        web =new webServices();
        web.sharedPreferences = getSharedPreferences("load_data" , 0);
        toolbar.setTitle(web.sharedPreferences.getString("typename" , "******"));
        setTitle(web.sharedPreferences.getString("typename" , "******"));


        headerResult= new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.drawerback)
                .addProfiles(
                        new ProfileDrawerItem().withName(web.sharedPreferences.getString("typename" , "******")).withEmail(web.sharedPreferences.getString("email" , "******")).withIcon(getResources().getDrawable(R.drawable.mainlogo))
                ).build();


        final PrimaryDrawerItem homePage = new PrimaryDrawerItem().withIdentifier(1).withName("Home").withIcon(R.drawable.home);
        final PrimaryDrawerItem memberPage = new PrimaryDrawerItem().withIdentifier(2).withName("Members").withIcon(R.drawable.members);
        final PrimaryDrawerItem editPage = new PrimaryDrawerItem().withIdentifier(3).withName("Edit Profile").withIcon(R.drawable.edit);
        final PrimaryDrawerItem aboutPage = new PrimaryDrawerItem().withIdentifier(3).withName("About").withIcon(R.drawable.information);
        final PrimaryDrawerItem logoutPage = new PrimaryDrawerItem().withIdentifier(3).withName("Logout").withIcon(R.drawable.logout);
//
//        TextView txtTypeName = (TextView) headerView.findViewById(R.id.textView2);
//        TextView txtEmail = (TextView) headerView.findViewById(R.id.textView);
//        txtTypeName.setText(web.sharedPreferences.getString("typename" , "******"));
//        txtEmail.setText(web.sharedPreferences.getString("email" , "******"));

        drawer = new DrawerBuilder().withActivity(this).withTranslucentStatusBar(true)
                .withActionBarDrawerToggle(true).withAccountHeader(headerResult).withToolbar(toolbar).addDrawerItems(
                        homePage,
                        new DividerDrawerItem(),
                        memberPage,
                        new DividerDrawerItem(),
                        editPage,
                        new DividerDrawerItem(),
                        aboutPage,
                        new DividerDrawerItem(),
                        logoutPage,
                        new DividerDrawerItem()
                ).withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        android.support.v4.app.FragmentTransaction transaction;
                        android.support.v4.app.FragmentManager manager;
                        Toast.makeText(CharityProfile.this, ""+position, Toast.LENGTH_SHORT).show();
                        //position = drawer.getCurrentSelectedPosition();
                        switch (position){
                            case 1:
                                //toolbar.setTitle("Home");
                                Toast.makeText(CharityProfile.this, "HOME SCREEN", Toast.LENGTH_SHORT).show();
                                manager = getSupportFragmentManager();
                                manager.popBackStack();
                                transaction = manager.beginTransaction();
                                transaction.replace(R.id.content_main,new HomeFragment());
                                transaction.commit();
                                break;
                            case 3:
                                //toolbar.setTitle("Members");
                                Toast.makeText(CharityProfile.this, "settings", Toast.LENGTH_SHORT).show();
                                manager = getSupportFragmentManager();
                                manager.popBackStack();
                                transaction = manager.beginTransaction();
                                transaction.replace(R.id.content_main,new MembersFragment(),new MembersFragment().getTag());
                                transaction.addToBackStack("");
                                transaction.commit();
                                break;
                            case 5:
                                //toolbar.setTitle("Help");
                                Toast.makeText(CharityProfile.this, "help", Toast.LENGTH_SHORT).show();
                                manager = getSupportFragmentManager();
                                manager.popBackStack();
                                transaction = manager.beginTransaction();
                                transaction.replace(R.id.content_main,new EditFragment(),new EditFragment().getTag());
                                transaction.addToBackStack("");
                                transaction.commit();
                                break;
                            case 7:
                                final Dialog dialog = new Dialog(CharityProfile.this) ;
                                dialog.setContentView(R.layout.dialog_about);
                                dialog.setTitle("About");
                                dialog.show();
                                break;
                            case 9:
                                SharedPreferences shared = getSharedPreferences("load_data", 0);
                                SharedPreferences.Editor editor = shared.edit();
                                editor.clear();
                                editor.commit();
                                Intent intent = new Intent(CharityProfile.this,LoginRegisterActivity.class);
                                startActivity(intent);
                                finish();
                            default:
                                manager = getSupportFragmentManager();
                                transaction = manager.beginTransaction();
                                transaction.replace(R.id.content_main,new HomeFragment());
                                transaction.commit();
                        }
                        return false;
                    }
                }).build();


    }


    @Override
    public void onBackPressed() {
        SharedPreferences shared = getSharedPreferences("load_data", 0);
        String user = shared.getString("username", "");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
                return;
            }else{
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

    @Override
    public void onBackStackChanged() {

    }
}
