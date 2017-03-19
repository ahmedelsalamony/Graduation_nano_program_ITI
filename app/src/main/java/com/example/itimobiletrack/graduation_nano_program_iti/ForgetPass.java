package com.example.itimobiletrack.graduation_nano_program_iti;

import android.app.AlertDialog;
import android.app.Fragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ahmed on 3/21/2017.
 */

public class ForgetPass extends Fragment {

    private EditText edRestorePassword;
    private Button   buRestorePassword;
    private  webServices web;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.xlayoutforgetpass, parent, false);

        web =new webServices();
        edRestorePassword= (EditText) v.findViewById(R.id.xRestorePasswordEditText);
        buRestorePassword= (Button) v.findViewById(R.id.xRestorePasswordButton);

        buRestorePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(edRestorePassword.getText().toString().trim().equals(null))
                {
                    edRestorePassword.setError("enter username");
                }

                else
                {

                    String userName= edRestorePassword.getText().toString().trim();
                    // TODO  >> Call forget web Service
                    web.forgetPasswordUser(getActivity(), userName, new request_interface() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject password_json=new JSONObject(response);
                                String password_response= password_json.getString("response");
                                if(password_response.equals("found")){

                                    final AlertDialog.Builder dialog =new AlertDialog.Builder(getActivity());
                                    dialog.setCancelable(true);
                                    dialog.setTitle("RE-Restaurant");
                                    dialog.setIcon(R.drawable.green);
                                    dialog.setMessage("Your Correct password Sent to your E-mail");
                                    dialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {

                                            dialogInterface .dismiss();

                                            // TODO >> then Call Login fragment to Login again
                                            LoginFragment loginFragment =new LoginFragment();
                                            FragmentManager fm=getFragmentManager();
                                            FragmentTransaction ft=fm.beginTransaction();
                                            ft.addToBackStack(null);
                                            ft.replace(R.id.xPlaceHolder,loginFragment);
                                            ft.commit();


                                        }
                                    });

                                    dialog.show();
                                }
                                else
                                {
                                    Toast.makeText(getActivity(), " not found account with user name", Toast.LENGTH_LONG).show();

                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onError() {

                        }
                    });


                }


            }
        });

        return v;
    }


}
