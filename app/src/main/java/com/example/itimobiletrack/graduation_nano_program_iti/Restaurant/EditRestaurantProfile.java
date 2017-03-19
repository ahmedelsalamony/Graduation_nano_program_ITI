package com.example.itimobiletrack.graduation_nano_program_iti.Restaurant;


import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.itimobiletrack.graduation_nano_program_iti.R;
import com.example.itimobiletrack.graduation_nano_program_iti.Web.webServices;


public class EditRestaurantProfile extends Fragment {
EditText edtUserName,edtPassword,edtConfirmPassword,edtPhone,edtAddress,edtCharity,edtEmail;
Button bEditRestaurant;
   webServices web;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_edit_restaurant_profile, container, false);


        Snackbar.make(getActivity().findViewById(android.R.id.content), "If you update yor data you need to login again.", Snackbar.LENGTH_LONG).show();

        web=new webServices();
        bEditRestaurant= (Button) v.findViewById(R.id.xEditRestaurantFinish);
        edtUserName=(EditText)v.findViewById(R.id.xUserName);
        edtPassword=(EditText)v.findViewById(R.id.xPassword);
        edtConfirmPassword=(EditText)v.findViewById(R.id.xConfirmPassword);
        edtEmail=(EditText)v.findViewById(R.id.xEmail);
        edtPhone=(EditText)v.findViewById(R.id.xPhone);
        edtAddress=(EditText)v.findViewById(R.id.xAddress);
        edtCharity=(EditText)v.findViewById(R.id.xCharityName);




        edtUserName.setText(getActivity().getIntent().getStringExtra("username"));
        edtPassword.setText(getActivity().getIntent().getStringExtra("password"));
        edtEmail.setText(getActivity().getIntent().getStringExtra("email"));
        edtPhone.setText(getActivity().getIntent().getStringExtra("phone"));
        edtAddress.setText(getActivity().getIntent().getStringExtra("address"));


        bEditRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (edtUserName.getText().toString().trim().equals("")){
                    edtUserName.setError("enter valid username");
                }else if (edtPassword.getText().toString().trim().equals("")){
                    edtPassword.setError("enter valid password");
                }
                 else if (edtPhone.getText().toString().trim().toString().equals("")){
                    edtPhone.setError("enter valid phone");
                }else if (edtAddress.getText().toString().trim().equals("")){
                    edtAddress.setError("enter valid address");
                }else if (!isValidEmailAddress(edtEmail.getText().toString().trim())){
                    edtEmail.setError("enter valid email");
                }else{

                    web.update_restaurant(getActivity(),"Restaurant",getActivity().getIntent().getStringExtra("username"),edtUserName.getText().toString(),edtPassword.getText().toString(),edtPhone.getText().toString(),edtAddress.getText().toString(),edtEmail.getText().toString());
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


}
