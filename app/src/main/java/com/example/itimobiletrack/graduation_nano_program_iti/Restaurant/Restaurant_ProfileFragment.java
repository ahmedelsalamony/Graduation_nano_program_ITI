package com.example.itimobiletrack.graduation_nano_program_iti.Restaurant;

import android.app.Dialog;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.itimobiletrack.graduation_nano_program_iti.R;


public class Restaurant_ProfileFragment extends Fragment implements View.OnClickListener {
    Button btnSend, btnCancel;
    private RatingBar ratingBar;
    private Button btnSubmit;
    EditText edtFoodQ, edtEstimatedTime;
    Dialog dialog;
    GridView gridView;
    static final String[] MOBILE_OS = new String[] {
            "Android", "iOS","Windows", "Blackberry" };
    FloatingActionButton btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_restaurant__profile, container, false);
        gridView = (GridView) v.findViewById(R.id.gridView1);
        gridView.setAdapter(new ImageAdapterGrid(getActivity(), MOBILE_OS));
        btn = (FloatingActionButton) v.findViewById(R.id.xbtnSendToAll);
        btn.setOnClickListener(this);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                Intent i = new Intent(getActivity(), SendRateActivity.class);
                startActivity(i);
            }
        });
        return v;

    }

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
