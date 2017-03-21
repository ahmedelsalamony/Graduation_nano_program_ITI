package com.example.itimobiletrack.graduation_nano_program_iti;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

/**
 * Created by ahmed on 3/21/2017.
 */

public class RegisterFragment extends Fragment {

    TextView txtTitle;
    EditText edtUserName,edtPassword,edtConfirmPassword,edtEmail,edtPhone,edtAddress,edtCharity;
    RadioButton rdCharity,rdRestaurant;
    boolean flag = false;
    Intent intent;
    public Place startAddress;
    TextInputLayout layCharity;


    private  webServices web;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.activity_registeration, parent, false);


        web=new webServices();

        txtTitle=(TextView)v.findViewById(R.id.xTitle);
        edtUserName=(EditText)v.findViewById(R.id.xUserName);
        edtPassword=(EditText)v.findViewById(R.id.xPassword);
        edtConfirmPassword=(EditText)v.findViewById(R.id.xConfirmPassword);
        edtEmail=(EditText)v.findViewById(R.id.xEmail);
        edtPhone=(EditText)v.findViewById(R.id.xPhone);
        edtAddress=(EditText)v.findViewById(R.id.xAddress);
        edtCharity=(EditText)v.findViewById(R.id.xCharityName);
        rdCharity=(RadioButton)v.findViewById(R.id.xrdCharity);
        rdRestaurant=(RadioButton)v.findViewById(R.id.xrdRestaurant);
        layCharity=(TextInputLayout)v.findViewById(R.id.input_layout_CharityName) ;
        Button btn=(Button)v.findViewById(R.id.xbtnsave);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "registerationnnn", Toast.LENGTH_SHORT).show();
            }
        });
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
                    intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY).build(getActivity());
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
                startActivityForResult(intent, 1);
            }
        });
        return v;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {

                if (flag == false) {
                    startAddress = PlaceAutocomplete.getPlace(getActivity(), data);
                    edtAddress.setText(startAddress.getAddress());
                    Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT).show();
                }

            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(getActivity(), data);
                Log.e("Tag", status.getStatusMessage());

            } else if (resultCode == RESULT_CANCELED) {
            }
        }
    }




    // TODO SignUP Action to database
  public  void registerToDatabse(View view){

      String userName=edtUserName.getText().toString();
      String password=edtPassword.getText().toString();
      String email=edtEmail.getText().toString();
      String phone=edtPhone.getText().toString();
      String address=edtAddress.getText().toString();
      String type=null;

      if(rdRestaurant.isChecked()){
          type=rdRestaurant.getText().toString();
      }
      else{
          type=rdCharity.getText().toString();
      }

     String typeName =edtCharity.getText().toString();


    web.addUser(getActivity(),userName,password,email,phone,address,type,typeName,0);



  }




}
