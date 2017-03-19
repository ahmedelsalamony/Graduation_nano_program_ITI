package com.example.itimobiletrack.graduation_nano_program_iti.Member;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.itimobiletrack.graduation_nano_program_iti.R;

public class MemberProfile extends AppCompatActivity {
    ListView listView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_profile);


        setTitle(getIntent().getStringExtra("username"));
        // Get ListView object from xml
        listView = (ListView) findViewById(R.id.list);

        // Defined Array values to show in ListView
        String[] tasks = new String[] { "Task 1 from Charity 1", "Task 2 from Charity 1", "Task 3 from Charity 1", "Task 4 from Charity 1",
        };



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

                Button acceptBtn= (Button) taskdialog.findViewById(R.id.xbtnAccept);
                Button rejectBtn= (Button) taskdialog.findViewById(R.id.xbtReject);

                acceptBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

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
