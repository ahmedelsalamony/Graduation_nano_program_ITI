package com.example.itimobiletrack.graduation_nano_program_iti.Restaurant;

import android.app.Activity;
import android.app.Dialog;
import android.support.v4.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RatingBar;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.itimobiletrack.graduation_nano_program_iti.PushNotification.EndPoints;
import com.example.itimobiletrack.graduation_nano_program_iti.PushNotification.MyVolley;
import com.example.itimobiletrack.graduation_nano_program_iti.R;
import com.example.itimobiletrack.graduation_nano_program_iti.Web.request_interface;
import com.example.itimobiletrack.graduation_nano_program_iti.Web.webServices;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class Restaurant_ProfileFragment extends Fragment implements View.OnClickListener {
    private ProgressDialog progressDialog;
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
    String rate_type;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_restaurant__profile, container, false);
        gridView = (GridView) v.findViewById(R.id.gridView1);
        btn = (FloatingActionButton) v.findViewById(R.id.xbtnSendToAll);
        web =new webServices();
        web.sharedPreferences = getActivity().getSharedPreferences("load_data" , 0);
        charityBuffer=new StringBuffer();

        progressDialog = new ProgressDialog(getActivity());
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
                //set value of charity name depending on OnItemClick of gridView
                String charityname=MOBILE_OS[position];
                rateCharity(v,charityname);
            }
        });

        return v;

    }
    public void rateCharity(View v, final String charityname) {
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog2);
        ratingBar = (RatingBar) dialog.findViewById(R.id.ratingBar);
        btnSubmit = (Button) dialog.findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String x=web.sharedPreferences.getString("typename","default-restaurant-name");
                String y=web.sharedPreferences.getString("type","hhhhhhhhhh");
                Toast.makeText(getActivity(), y, Toast.LENGTH_SHORT).show();
                web.sharedPreferences=getActivity().getSharedPreferences("load_data",0);
                web.addRateCharityByRestaurant(getActivity(), (int) ratingBar.getRating(),0,
                        web.sharedPreferences.getInt("id",2017),charityname,x,y);
                Toast.makeText(getActivity(), String.valueOf(ratingBar.getRating()), Toast.LENGTH_SHORT).show();
            }
        });
       // dialog.setTitle("Rate Charity");
        dialog.show();
    }

    // TODO Send Request Function From Restaurant To Charities
    public void showConfirmSendToast() {
        Toast.makeText(getActivity(), "send successfully", Toast.LENGTH_SHORT).show();
    }



    @Override
    public void onClick(View view) {
        dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.sendrequesttocharitydialog);
        dialog.setTitle("Send Request");
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



                // TODO Call of  Send Notification to All charities

                    final String title =web.sharedPreferences.getString("typename" , "******");
                    final String quantity =edtFoodQ.getText().toString() ;
                   // final String image = editTextImage.getText().toString();

                    progressDialog.setMessage("Sending.......");
                    progressDialog.show();

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, EndPoints.URL_SEND_MULTIPLE_PUSH,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    progressDialog.dismiss();
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {

                                }
                            }) {
                        @Override
                        protected Map<String,String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
                            params.put("title", title);
                            params.put("message", quantity);

                            return params;
                        }
                    };

                    MyVolley.getInstance(getActivity()).addToRequestQueue(stringRequest);



                  // TODO Add Request To Table Task

               web.addTask(getActivity(),web.sharedPreferences.getInt("id" , 0),"Posted",quantity,
                       edtEstimatedTime.getText().toString(),0);


                }


            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

      //  dialog.setTitle(" request to charity ");
        dialog.show();

    }
}
