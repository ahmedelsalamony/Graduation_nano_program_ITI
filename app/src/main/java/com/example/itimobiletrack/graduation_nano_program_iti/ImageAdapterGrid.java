package com.example.itimobiletrack.graduation_nano_program_iti;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by ahmed on 3/22/2017.
 */

public class ImageAdapterGrid extends BaseAdapter {
    private Context context;
    private final String[] CharityValues;

    public ImageAdapterGrid(Context context, String[] CharityValues) {
        this.context=context;
        this.CharityValues =CharityValues;
    }

    @Override
    public int getCount() {
        return CharityValues.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertview, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v;
        if (convertview == null){
            v=new View(context);
            v=inflater.inflate(R.layout.customgridview,null);
// set value into textview
            TextView textView = (TextView) v
                    .findViewById(R.id.grid_item_label);
            textView.setText(CharityValues[position]);

            // set image based on selected text
            ImageView imageView = (ImageView) v
                    .findViewById(R.id.grid_item_image);


            String mobile = CharityValues[position];

            if (mobile.equals("Windows")) {
                imageView.setImageResource(R.drawable.green);
            } else if (mobile.equals("iOS")) {
                imageView.setImageResource(R.drawable.green);
            } else if (mobile.equals("Blackberry")) {
                imageView.setImageResource(R.drawable.green);
            } else {
                imageView.setImageResource(R.drawable.green);
            }

        } else {
            v = (View) convertview;
        }

        return v;
    }
}
