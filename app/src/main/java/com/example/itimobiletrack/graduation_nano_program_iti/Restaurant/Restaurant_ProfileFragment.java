package com.example.itimobiletrack.graduation_nano_program_iti.Restaurant;

import android.app.Dialog;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.Snackbar;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.itimobiletrack.graduation_nano_program_iti.R;
import com.example.itimobiletrack.graduation_nano_program_iti.Web.request_interface;
import com.example.itimobiletrack.graduation_nano_program_iti.Web.webServices;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;




public class Restaurant_ProfileFragment extends Fragment implements View.OnClickListener {

    String[] MOBILE_OS ;

    webServices web;
    StringBuffer charityBuffer;

    Button btnSend, btnCancel;
    private RatingBar ratingBar;
    private Button btnSubmit;
    EditText edtFoodQ, edtEstimatedTime;
    Dialog dialog;
    GridView gridView;
    FloatingActionButton btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_restaurant__profile, container, false);
        gridView = (GridView) v.findViewById(R.id.gridView1);
        btn = (FloatingActionButton) v.findViewById(R.id.xbtnSendToAll);
        web =new webServices();
        charityBuffer=new StringBuffer();


     //TODO Call getAllCharity
        web.getAllCharity(getActivity(), "Charity" , new request_interface() {
            @Override
            public void onResponse(String response) {

                try {

                    JSONObject jsonResponse = new JSONObject(response);
                    JSONArray jsonArray = jsonResponse.getJSONArray("charity");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject search_object = jsonArray.getJSONObject(i);
                        charityBuffer.append(search_object.getString("typename") + "#");

                    }


                    MOBILE_OS = charityBuffer.toString().split("#");

                    gridView.setAdapter(new ImageAdapterGrid(getActivity(), MOBILE_OS));


                    btn.setOnClickListener(Restaurant_ProfileFragment.this);



                } catch (JSONException e) {
                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError() {
                Snackbar.make(getActivity().findViewById(android.R.id.content), "Internet Connection Fair", Snackbar.LENGTH_LONG).show();

            }

        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                Intent i = new Intent(getActivity(), SendRateActivity.class);
                startActivity(i);
            }
        });

        return v;

    }


    // TODO Send Request Function From Restaurant To Charities
    public void showConfirmSendToast() {
        Toast.makeText(getActivity(), "send successfully", Toast.LENGTH_SHORT).show();
    }



    @Override
    public void onClick(View view) {
        dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.sendrequesttocharitydialog);
        btnSend = (Button) dialog.findViewById(R.id.xbtnConfirmSend);
        btnCancel = (Button) dialog.findViewById(R.id.xCancel);
        edtFoodQ = (EditText) dialog.findViewById(R.id.xFoodQ);
        edtEstimatedTime = (EditText) dialog.findViewById(R.id.xEstimatedTime);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtFoodQ.getText().toString().trim().equals("")) {
                    edtFoodQ.setError("enter valid text");
                } else if (edtEstimatedTime.getText().toString().trim().equals("")) {
                    edtEstimatedTime.setError("enter valid time");
                } else {
                    showConfirmSendToast();
                    dialog.dismiss();
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.setTitle(" request to charity ");
        dialog.show();

    }
}
