package com.example.itimobiletrack.graduation_nano_program_iti.Charity;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.itimobiletrack.graduation_nano_program_iti.R;
import com.example.itimobiletrack.graduation_nano_program_iti.Web.webServices;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddMemberFragment extends Fragment {

    TextView txtTitle;
    EditText edtUserName,edtPassword,edtConfirmPassword,edtEmail,edtPhone,edtAddress;
    String strUserName, strPassword, strConfirm, strEmail, strPhone, strAddress, strCharity;

    boolean flag = false;
    Intent intent;
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

        txtTitle=(TextView)v.findViewById(R.id.xTitle);
        Typeface custom_font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/title.ttf");
        txtTitle.setTypeface(custom_font);
        edtUserName=(EditText)v.findViewById(R.id.xUserName);
        edtPassword=(EditText)v.findViewById(R.id.xPassword);
        edtConfirmPassword=(EditText)v.findViewById(R.id.xConfirmPassword);
        edtEmail=(EditText)v.findViewById(R.id.xEmail);
        edtPhone=(EditText)v.findViewById(R.id.xPhone);
        edtAddress=(EditText)v.findViewById(R.id.xAddress);


        strUserName = edtUserName.getText().toString();
        strPassword = edtPassword.getText().toString();
        strConfirm = edtConfirmPassword.getText().toString();
        strEmail = edtEmail.getText().toString();
        strPhone = edtPhone.getText().toString();
        strAddress = edtAddress.getText().toString();


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
                String typeName = getActivity().getIntent().getStringExtra("typename");
                strCharity=getActivity().getIntent().getStringExtra("id");
                int charity_Id=Integer.parseInt(strCharity);

                if (edtUserName.getText().toString().trim().equals("")){
                    edtUserName.setError("enter valid username");
                }else if (edtPassword.getText().toString().trim().equals("")){
                    edtPassword.setError("enter valid password");
                }else if (!edtConfirmPassword.getText().toString().trim().equals(password)) {
                    edtConfirmPassword.setError("password not match");
                }else if (edtConfirmPassword.getText().toString().trim().equals("")){
                    edtConfirmPassword.setError("error ");
                }else if (edtPhone.getText().toString().trim().toString().equals("")){
                    edtPhone.setError("enter valid phone");
                }else if (edtAddress.getText().toString().trim().equals("")){
                    edtAddress.setError("enter valid address");
                }else if (!isValidEmailAddress(edtEmail.getText().toString().trim())){
                    edtEmail.setError("enter valid email");
                }else{

                    web.addMember(getActivity(), userName, password, email, phone, address, type, typeName, 0,charity_Id ,"amshdjjsjiwwkkskskskkskk");

                }



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


}
