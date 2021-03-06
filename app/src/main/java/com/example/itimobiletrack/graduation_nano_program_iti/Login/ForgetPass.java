package com.example.itimobiletrack.graduation_nano_program_iti.Login;

import android.app.AlertDialog;
import android.support.v4.app.Fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.itimobiletrack.graduation_nano_program_iti.R;
import com.example.itimobiletrack.graduation_nano_program_iti.Web.request_interface;
import com.example.itimobiletrack.graduation_nano_program_iti.Web.webServices;
import com.labo.kaji.fragmentanimations.CubeAnimation;

import org.json.JSONException;
import org.json.JSONObject;

import static com.daimajia.androidanimations.library.BaseViewAnimator.DURATION;


/**
 * Created by ahmed on 3/21/2017.
 */

public class ForgetPass extends Fragment {

    private EditText edRestorePassword;
    private Button   buRestorePassword;
    private webServices web;
    private static final long DURATION = 600;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_forgetpass, parent, false);

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
                    if (userName.isEmpty()){
                        String errormsg = getActivity().getResources().getString (R.string.VemailRestore);
                        edRestorePassword.setError(errormsg);
                    }
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
                                            android.support.v4.app.FragmentManager fm=getFragmentManager();
                                            android.support.v4.app.FragmentTransaction ft=fm.beginTransaction();
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
    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return CubeAnimation.create(CubeAnimation.UP, enter, DURATION);
    }

}
