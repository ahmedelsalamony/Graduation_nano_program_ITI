package com.example.itimobiletrack.graduation_nano_program_iti;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;


public class EditRestaurantProfile extends Fragment {
EditText edtUserName,edtPassword,edtConfirmPassword,edtPhone,edtAddress,edtCharity,edtEmail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_edit_restaurant_profile, container, false);
        edtUserName=(EditText)v.findViewById(R.id.xUserName);
        edtPassword=(EditText)v.findViewById(R.id.xPassword);
        edtConfirmPassword=(EditText)v.findViewById(R.id.xConfirmPassword);
        edtEmail=(EditText)v.findViewById(R.id.xEmail);
        edtPhone=(EditText)v.findViewById(R.id.xPhone);
        edtAddress=(EditText)v.findViewById(R.id.xAddress);
        edtCharity=(EditText)v.findViewById(R.id.xCharityName);
        if (edtUserName.getText().toString().trim().equals("")){
            edtUserName.setError("enter valid username");
        }else if (edtPassword.getText().toString().trim().equals("")){
            edtPassword.setError("enter valid password");
        }else if (!edtConfirmPassword.getText().toString().trim().equals(edtPassword.getText().toString())) {
            edtConfirmPassword.setError("password not match");
        }else if (edtConfirmPassword.getText().toString().trim().equals("")){
            edtConfirmPassword.setError("error ");
        }else if (edtPhone.getText().toString().trim().toString().equals("")){
            edtPhone.setError("enter valid phone");
        }else if (edtAddress.getText().toString().trim().equals("")){
            edtAddress.setError("enter valid address");
        }else if(edtCharity.getText().toString().trim().equals("")){
            edtCharity.setError("enter valid charity name");
        }else if (!isValidEmailAddress(edtEmail.getText().toString().trim())){
            edtEmail.setError("enter valid email");
        }else{

            Toast.makeText(getActivity(), "edit", Toast.LENGTH_SHORT).show();
        }
        return v;
    }
    public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }


}
