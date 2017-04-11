package com.example.itimobiletrack.graduation_nano_program_iti.Charity;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.itimobiletrack.graduation_nano_program_iti.R;
import com.example.itimobiletrack.graduation_nano_program_iti.Web.CircleTransform;
import com.example.itimobiletrack.graduation_nano_program_iti.Web.webServices;
import com.squareup.picasso.Picasso;

/**
 * Created by owner on 4/5/2017.
 */

public class CardViewAdapter extends BaseAdapter {
    private Context context;
    private String[] restaurantValues;
    String[] titles;
    int[] images;
    webServices web= new webServices();
    public String keyWord;



    public CardViewAdapter(Context context, String[] restaurantValues) {
        this.context=context;
        this.restaurantValues=restaurantValues;

    }
    @Override
    public int getCount() {
        /*return number of elements inside this array*/
        return restaurantValues.length;
    }
    @Override
    public Object getItem(int position) {
        /*return the item at posion -position-*/
        return null;
    }

    @Override
    public long getItemId(int position) {
        /*return the id of the row which in this case the index of the array*/
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.single_row_request,parent,false);
        View v;

        if(convertView == null) {
            v = new View(context);
            v = inflater.inflate(R.layout.single_row_request, null);
            TextView textView = (TextView) v.findViewById(R.id.xml_text);
            textView.setText(restaurantValues[position]);
            ImageView imageView = (ImageView) v.findViewById(R.id.xml_image);

            keyWord = restaurantValues[position];
            web = new webServices();
            web.sharedPreferences = context.getSharedPreferences("load_data", 0);
            String imagename = web.sharedPreferences.getString("username", "username");
            Log.d("keyword******",keyWord);

            Picasso.with(context).load("https://re-restaurant.000webhostapp.com/uploads/re_database/" + keyWord + ".jpg")
                    .transform(new CircleTransform()).placeholder(R.drawable.mainlogo).error(R.drawable.img2).into(imageView);
        }else {
            v = (View) convertView;
        }
        return v;
    }
}
