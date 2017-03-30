package com.example.itimobiletrack.graduation_nano_program_iti.Member;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.itimobiletrack.graduation_nano_program_iti.R;
import com.example.itimobiletrack.graduation_nano_program_iti.Web.webServices;




public class MemberProfile extends AppCompatActivity {
    ListView listView ;

    private webServices web;
    String tasks[]= null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_profile);
         web=new webServices();
         web.sharedPreferences =getSharedPreferences("load_data" , 0);

        setTitle(web.sharedPreferences.getString("username" , "******"));
        // Get ListView object from xml
        listView = (ListView) findViewById(R.id.list);

        // Defined Array values to show in ListView

                  tasks =new String[]{"Task From admin"};
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, tasks);


        // Assign adapter to ListView
        listView.setAdapter(adapter);

        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {



                final Dialog taskdialog = new Dialog(MemberProfile.this);
                taskdialog.setContentView(R.layout.taskdialog);
                taskdialog.setTitle("Task Details");
                taskdialog.show();
                TextView taskName_tv= (TextView) taskdialog.findViewById(R.id.xtaskname);
                TextView taskQuantity_tv= (TextView) taskdialog.findViewById(R.id.xtasQuantity);
                TextView taskEstimatedTime_tv= (TextView) taskdialog.findViewById(R.id.xtaskEstimatedtime);


                Button acceptBtn= (Button) taskdialog.findViewById(R.id.xbtnAccept);
                Button rejectBtn= (Button) taskdialog.findViewById(R.id.xbtReject);
               String message = getIntent().getStringExtra("message");
                final String note[]=message.split("#");

                taskName_tv.setText(note[1]);
                taskQuantity_tv.setText(note[2]);
                taskEstimatedTime_tv.setText(note[3]);


                System.out.println("BELAL" +getIntent().getStringExtra("message") );
                acceptBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        // To update Charity_tasks Table
                       web. addCharityTask(MemberProfile.this ,Integer.parseInt(web.sharedPreferences.getString("charity_parent_id" , "*****")) ,Integer.parseInt(note[0]));
                        //to update Tasks Table
                      web. updateTask(MemberProfile.this ,  web.sharedPreferences.getInt("id", 2017),Integer.parseInt(note[0]));







                    }
                });


                rejectBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });

            }

        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.check_done, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.checkbox){

            if(item.isChecked()){
                item.setChecked(false);
                Toast.makeText(this, "unchecked", Toast.LENGTH_SHORT).show();
            }
            else{
                item.setChecked(true);
                Toast.makeText(this, "Task done", Toast.LENGTH_SHORT).show();
            }


        }
        return true;
    }
}
