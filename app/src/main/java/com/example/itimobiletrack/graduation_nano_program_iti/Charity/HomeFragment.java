 package com.example.itimobiletrack.graduation_nano_program_iti.Charity;

        import android.app.Dialog;
        import android.app.FragmentManager;
        import android.app.FragmentTransaction;
        import android.app.ListFragment;
        import android.app.ProgressDialog;
        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.support.design.widget.Snackbar;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.view.animation.Animation;
        import android.view.animation.AnimationUtils;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.ListView;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.android.volley.AuthFailureError;
        import com.android.volley.Request;
        import com.android.volley.Response;
        import com.android.volley.VolleyError;
        import com.android.volley.toolbox.StringRequest;
        import com.example.itimobiletrack.graduation_nano_program_iti.PushNotification.EndPoints;
        import com.example.itimobiletrack.graduation_nano_program_iti.PushNotification.MyVolley;
        import com.example.itimobiletrack.graduation_nano_program_iti.R;
        import com.example.itimobiletrack.graduation_nano_program_iti.Restaurant.ImageAdapterGrid;
        import com.example.itimobiletrack.graduation_nano_program_iti.Web.request_interface;
        import com.example.itimobiletrack.graduation_nano_program_iti.Web.webServices;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.util.HashMap;
        import java.util.Map;


 /**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

     /*hive part*/
     private static final String ARG_PARAM1 = "param1";
     private static final String ARG_PARAM2 = "param2";

     // TODO: Rename and change types of parameters
     private String mParam1;
     private String mParam2;
     /*/*hive part*/
     private  StringBuffer typeNameBuffer;
     private  StringBuffer task_quantity;
     private  webServices web;
     String[] requests = null;
     String address[]=null;
     String quantity[]=null;
     String x[];
     String   charity_NameType;
     int charity_parent_id;
     ListView requests_list;
     CardViewAdapter adapter;

     private ProgressDialog progressDialog;
       public HomeFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        final View view = inflater.inflate(R.layout.fragment_home, container, false);
//        adapter=new CardViewAdapter();
        requests_list  = (ListView) view.findViewById(R.id.home_requests);

        web= new webServices();
        progressDialog =new ProgressDialog(getActivity());


        typeNameBuffer =new StringBuffer();
         task_quantity=new StringBuffer();
        web.sharedPreferences=getActivity().getSharedPreferences("load_data" ,0);
        charity_NameType=web.sharedPreferences.getString("typename" , "******");
        charity_parent_id =  web.sharedPreferences.getInt("id" , 0);

        System.out.println(charity_parent_id);
        Log.d("***" , ""+charity_parent_id);




        web.getAllRequests(getActivity() , new request_interface() {
            @Override
            public void onResponse(String response) {

                try {

                    JSONObject jsonResponse = new JSONObject(response);
                    JSONArray jsonArray = jsonResponse.getJSONArray("charity");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject search_object = jsonArray.getJSONObject(i);
                        typeNameBuffer.append(search_object.getString("typename") + "#");
                        task_quantity.append(charity_NameType+":"+search_object.getInt("id")+":"+search_object.getString("address")+":"+search_object.getString("task_quantity")+":"+search_object.getString("task_estimated_time")+ "#");

                    }


                    requests = typeNameBuffer.toString().split("#");
                    adapter = new CardViewAdapter(getActivity(),requests);

                    quantity = task_quantity.toString().split("#");


                    requests_list.setAdapter(adapter);



                } catch (JSONException e) {
                   // Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError() {
                HomeFragment homeFragment =new HomeFragment();
                android.support.v4.app.FragmentManager manager = getActivity().getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.content_main,homeFragment).commit();
                //Snackbar.make(getActivity().findViewById(android.R.id.content), "Internet Connection Fair", Snackbar.LENGTH_LONG).show();

            }

        });


        requests_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               final Dialog dialog = new Dialog( getActivity()) ;
                dialog.setContentView(R.layout.task_details_dialog);
                dialog.setTitle("Order Details");


                final TextView address= (TextView) dialog.findViewById(R.id.xRestaurantAddress);
                final TextView taskQuantity= (TextView) dialog.findViewById(R.id.xTaskQuantity);
                final TextView taskEstimatedTime= (TextView) dialog.findViewById(R.id.xtaskEstimatedtime);
                Button   assignBtn= (Button) dialog.findViewById(R.id.xAssignTaskBtn);


                 x = quantity[position].split(":");
                address.setText(x[2]);
                taskQuantity.setText(x[3]);
                taskEstimatedTime.setText(x[4]);
                dialog.show();



                assignBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

//
                        System.out.println(requests[0]);

                        progressDialog.setMessage("Sending.......");
                        progressDialog.show();

                        web.sharedPreferences= getActivity().getSharedPreferences("load_data" , 0);
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, EndPoints.URL_SEND_TO_MEMBERS,
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
                                params.put("title",requests[0] );


                                params.put("message",x[0] +"#"+x[1]+"#"+ address.getText().toString() + "#" +taskQuantity.getText().toString() +"#"+taskEstimatedTime.getText().toString()+"#");
                                params.put("charity_parent_id", ""+charity_parent_id);


//                            if (!TextUtils.isEmpty(image))
//                                params.put("image", image);
                                return params;
                            }
                        };

                        MyVolley.getInstance(getActivity()).addToRequestQueue(stringRequest);





                    } // end dialog
                });




            }
        });

        // Inflate the layout for this fragment
        return view;
    }

}
