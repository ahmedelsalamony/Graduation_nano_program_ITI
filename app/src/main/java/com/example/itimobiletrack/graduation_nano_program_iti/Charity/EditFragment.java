package com.example.itimobiletrack.graduation_nano_program_iti.Charity;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


import com.example.itimobiletrack.graduation_nano_program_iti.R;
import com.example.itimobiletrack.graduation_nano_program_iti.Web.webServices;




/**
 * A simple {@link Fragment} subclass.
 */
public class EditFragment extends Fragment {

    Button save;
    Button cancel;

    EditText edUserName , edPassword , edPhone , edAddress;

    webServices web;

    public EditFragment() {
        // Required empty public constructor
    }

    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v= inflater.inflate(R.layout.fragment_edit, container, false);
        web=new webServices();
         web.sharedPreferences = getActivity().getSharedPreferences("load_data" ,0 );
        Snackbar.make(getActivity().findViewById(android.R.id.content), "If you update yor data you need to login again.", Snackbar.LENGTH_LONG).show();





        save = (Button) v.findViewById(R.id.Save);
        edUserName= (EditText)v.findViewById(R.id.xUserNameEdit);
        edPassword= (EditText)v.findViewById(R.id.xPasswordEdit);
        edAddress= (EditText)v.findViewById(R.id.xAddressEdit);
        edPhone= (EditText)v.findViewById(R.id.xPhoneEdit);




//        cancel = (Button) v.findViewById(R.id.Cancel);
//        cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                android.support.v4.app.FragmentManager manager = getActivity().getSupportFragmentManager();
//                android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();
//                transaction.replace(R.id.,new HomeFragment()).commit();
//            }
//        });

        edUserName.setText(web.sharedPreferences.getString("username" , "******"));
        edPassword.setText(web.sharedPreferences.getString("password" , "******"));
        edPhone.setText(web.sharedPreferences.getString("phone" , "******"));
        edAddress.setText(web.sharedPreferences.getString("address" , "******"));


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 web.updateCharity(getActivity()
                 ,"Charity",web.sharedPreferences.getString("username" , "******")
                , edUserName.getText().toString()
                , edPassword.getText().toString()
                , edPhone.getText().toString() , edAddress.getText().toString());

                System.out.println(web.sharedPreferences.getString("username" , "******"));
            }
        });

       return  v;

    }




}
