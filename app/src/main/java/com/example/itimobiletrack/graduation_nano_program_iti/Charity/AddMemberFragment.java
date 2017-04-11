package com.example.itimobiletrack.graduation_nano_program_iti.Charity;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.Selection;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.itimobiletrack.graduation_nano_program_iti.PushNotification.SharedPrefManager;
import com.example.itimobiletrack.graduation_nano_program_iti.R;
import com.example.itimobiletrack.graduation_nano_program_iti.Web.webServices;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;


public class AddMemberFragment extends Fragment {

    TextView txtTitle;
    EditText edtUserName,edtPassword,edtConfirmPassword,edtEmail,edtPhone,edtAddress;
    boolean flag = false;
    //Intent intent;
    public Place startAddress;
    private webServices web;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_add_member, parent, false);


        web=new webServices();
        web.sharedPreferences=getActivity().getSharedPreferences("load_data" , 0 );
       /* txtTitle=(TextView)v.findViewById(R.id.xTitle);
        Typeface custom_font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/title.ttf");
        txtTitle.setTypeface(custom_font);*/
        edtUserName=(EditText)v.findViewById(R.id.xUserName);
        edtPassword=(EditText)v.findViewById(R.id.xPassword);
        edtConfirmPassword=(EditText)v.findViewById(R.id.xConfirmPassword);
        edtEmail=(EditText)v.findViewById(R.id.xEmail);
        edtPhone=(EditText)v.findViewById(R.id.xPhone);
        edtAddress=(EditText)v.findViewById(R.id.xAddress);


        Button btn=(Button)v.findViewById(R.id.xbtnsave);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String userName = edtUserName.getText().toString();
                String password = edtPassword.getText().toString();
                String email = edtEmail.getText().toString();
                String phone = edtPhone.getText().toString();
                String address = edtAddress.getText().toString();
                String type = "Member";

                String typeName = web.sharedPreferences.getString("typename" , "******");
                int strCharity=web.sharedPreferences.getInt("id" , 0);
                int charity_Id=strCharity;

                if (edtUserName.getText().toString().trim().equals("")){
                    edtUserName.setError("This Field is required");
                    edtUserName.requestFocus();
                }
                else if(edtUserName.getText().toString().length() < 3){
                    edtUserName.setError("Minimum 3 characters");
                    edtUserName.requestFocus();
                }else if (edtPassword.getText().toString().trim().equals("")){
                    edtPassword.setError("This Field is required");
                    edtPassword.requestFocus();
                }
                else if (edtPassword.getText().toString().length() < 4){
                    edtPassword.setError("Too short Minimum 4 characters");
                    edtPassword.requestFocus();
                }else if (!edtConfirmPassword.getText().toString().trim().equals(password)) {
                    edtConfirmPassword.setError("Password Mismatch");
                    edtConfirmPassword.requestFocus();
                }else if (edtConfirmPassword.getText().toString().trim().equals("")){
                    edtConfirmPassword.setError("This Field is required");
                    edtConfirmPassword.requestFocus();
                }else if (edtEmail.getText().toString().trim().equals("")){
                    edtEmail.setError("This Field is required");
                    edtEmail.requestFocus();
                }else if (!isValidEmailAddress(edtEmail.getText().toString().trim())){
                    edtEmail.setError("Not valid Email");
                    edtEmail.requestFocus();
                }else if (edtPhone.getText().toString().trim().toString().equals("")){
                    edtPhone.setError("This Field is required");
                    edtPhone.requestFocus();
                }else if (edtPhone.getText().toString().trim().toString().length() != 11){
                    edtPhone.setError("Not valid Mobile number");
                    edtPhone.requestFocus();
                }else if (edtAddress.getText().toString().trim().equals("")){
                    edtAddress.setError("This Field is required");
                    edtAddress.requestFocus();
                }else{
//                    android.support.v4.app.FragmentManager manager = getActivity().getSupportFragmentManager();
//                    manager.popBackStack();
//                    android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();
//                    transaction.replace(R.id.content_main,new MembersFragment(),new MembersFragment().getTag());
//                    transaction.commit();
                    String token = SharedPrefManager.getInstance(getActivity()).getDeviceToken();
                    web.addMember((AppCompatActivity) getActivity(), userName, password, email, phone, address, type, typeName, 0,charity_Id ,token);

                    android.support.v4.app.FragmentManager manager = getActivity().getSupportFragmentManager();
                    manager.popBackStack();
                    android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();
                    transaction.replace(R.id.content_main,new MembersFragment(),new MembersFragment().getTag());
                    transaction.commit();

                }

            }
        });


        edtAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag = false;
                Intent intent = null;
                try {
                    intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY).build(getActivity());
                    startActivityForResult(intent, 1);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "GooglePlayServicesRepairableException", Toast.LENGTH_SHORT).show();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "GooglePlayServicesNotAvailableException", Toast.LENGTH_SHORT).show();
                }

            }
        });

        return v;
    }


    public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                    startAddress = PlaceAutocomplete.getPlace(getActivity(), data);
                    edtAddress.setText(startAddress.getAddress());


            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(getActivity(), data);
                Log.e("Tag", status.getStatusMessage());

            }

            else {
                resultCode = RESULT_CANCELED;
            }

             }

    }


            }
