package com.example.itimobiletrack.graduation_nano_program_iti.Restaurant;


import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;
import com.example.itimobiletrack.graduation_nano_program_iti.R;



public class SendRateActivity extends AppCompatActivity {

    Button btnSend, btnCancel;
    private RatingBar ratingBar;
    private Button btnSubmit;
    EditText edtFoodQ,edtEstimatedTime;
    Dialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_rate);
    }




    public void showRateToast(int position) {
        Toast.makeText(this, "start" + position, Toast.LENGTH_SHORT).show();
    }

    public void rateCharity(View v) {
        final Dialog dialog = new Dialog(SendRateActivity.this);
        dialog.setContentView(R.layout.dialog2);
        ratingBar = (RatingBar) dialog.findViewById(R.id.ratingBar);
        btnSubmit = (Button) dialog.findViewById(R.id.btnSubmit);

        addListenerOnRatingBar();
        addListenerOnButton();
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.setTitle("Rate Charity");
        dialog.show();
    }
    public void addListenerOnRatingBar() {

        //if rating value is changed,
        //display the current rating value in the result (textview) automatically
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {

             /*   txtRatingValue.setText(String.valueOf(rating));*/
                showRateToast((int) rating);
            }
        });
    }

    public void addListenerOnButton() {

        //if click on me, then display the current rating value.

        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

          Toast.makeText(SendRateActivity.this, String.valueOf(ratingBar.getRating()), Toast.LENGTH_SHORT).show();

            }

        });

    }
}
