package com.example.itimobiletrack.graduation_nano_program_iti;

import android.content.Intent;
import android.os.Bundle;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import android.widget.Toast;


public class Registeration extends AppCompatActivity {

    TextView txtTitle;
    EditText edtUserName,edtRestaurant,edtPassword,edtConfirmPassword,edtEmail,edtPhone,edtAddress,edtCharity;
    RadioButton rdCharity,rdRestaurant;
    TextInputLayout  layRestaurant, layAddress;
    boolean flag = false;
    Intent intent;
    public Place startAddress;
    TextInputLayout layCharity,LayRestaurant;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);
        txtTitle=(TextView)findViewById(R.id.xTitle);
        edtUserName=(EditText)findViewById(R.id.xUserName);
        edtPassword=(EditText)findViewById(R.id.xPassword);
        edtConfirmPassword=(EditText)findViewById(R.id.xConfirmPassword);
        edtEmail=(EditText)findViewById(R.id.xEmail);
        edtPhone=(EditText)findViewById(R.id.xPhone);
        edtAddress=(EditText)findViewById(R.id.xAddress);
        edtCharity=(EditText)findViewById(R.id.xCharityName);
        rdCharity=(RadioButton)findViewById(R.id.xrdCharity);
        rdRestaurant=(RadioButton)findViewById(R.id.xrdRestaurant);
        layCharity=(TextInputLayout)findViewById(R.id.input_layout_CharityName) ;

    /*    if (rdCharity.isChecked()) {
            edtCharity.setVisibility(View.VISIBLE);
        } else if (!rdCharity.isChecked()){
            edtRestaurant.setVisibility(View.VISIBLE);
            edtCharity.setVisibility(View.VISIBLE);
        }else if (rdRestaurant.isChecked()){
            edtCharity.setVisibility(View.GONE);
            edtRestaurant.setVisibility(View.VISIBLE);
        }else if (!rdRestaurant.isChecked()){
            edtRestaurant.setVisibility(View.VISIBLE);
            edtCharity.setVisibility(View.VISIBLE);
        }
*/

        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/title.ttf");
        txtTitle.setTypeface(custom_font);


        rdCharity.setOnTouchListener(new View.OnTouchListener() {
               @Override
               public boolean onTouch(View view, MotionEvent motionEvent) {

                   layCharity.setVisibility(View.VISIBLE);
                   edtCharity.setVisibility(View.VISIBLE);
                   edtCharity.setHint("Charity name");
                   return false;
               }
           });

        rdRestaurant.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                layCharity.setVisibility(View.VISIBLE);
                edtCharity.setVisibility(View.VISIBLE);
                edtCharity.setHint("Restaurant name");
                return false;
            }
        });


        edtAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag = false;
                try {
                    intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY).build(Registeration.this);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
                startActivityForResult(intent, 1);
            }
        });


    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {

                if (flag == false) {
                    startAddress = PlaceAutocomplete.getPlace(this, data);
                    edtAddress.setText(startAddress.getAddress());
                    Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
                }

            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(this, data);
                Log.e("Tag", status.getStatusMessage());

            } else if (resultCode == RESULT_CANCELED) {
            }
        }
        }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Registeration.this,LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
