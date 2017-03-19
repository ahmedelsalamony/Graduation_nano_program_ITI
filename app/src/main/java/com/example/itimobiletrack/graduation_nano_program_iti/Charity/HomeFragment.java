 package com.example.itimobiletrack.graduation_nano_program_iti.Charity;

        import android.app.Dialog;
        import android.app.FragmentManager;
        import android.app.FragmentTransaction;
        import android.app.ProgressDialog;
        import android.os.Bundle;
        import android.app.Fragment;
        import android.support.design.widget.Snackbar;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
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
        import com.example.itimobiletrack.graduation_nano_program_iti.Restaurant.Restaurant_ProfileFragment;
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
   private  StringBuffer typeNameBuffer;
     private  StringBuffer task_quantity;
    private  webServices web;
     String[] requests = null;
     String address[]=null;
     String quantity[]=null;
     String x[];
     private ProgressDialog progressDialog;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        web= new webServices();
        progressDialog =new ProgressDialog(getActivity());

        typeNameBuffer =new StringBuffer();
         task_quantity=new StringBuffer();
        final View view = inflater.inflate(R.layout.fragment_home, container, false);


        final ListView requests_list = (ListView) view.findViewById(R.id.home_requests);


        web.getAllRequests(getActivity() , new request_interface() {
            @Override
            public void onResponse(String response) {

                try {

                    JSONObject jsonResponse = new JSONObject(response);
                    JSONArray jsonArray = jsonResponse.getJSONArray("charity");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject search_object = jsonArray.getJSONObject(i);
                        typeNameBuffer.append(search_object.getString("typename") + "#");
                        task_quantity.append(search_object.getInt("id")+":"+search_object.getString("address")+":"+search_object.getString("task_quantity")+":"+search_object.getString("task_estimated_time")+ "#");

                    }


                    requests = typeNameBuffer.toString().split("#");
                    quantity = task_quantity.toString().split("#");

                    ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, requests);

                    requests_list.setAdapter(listViewAdapter);



                } catch (JSONException e) {
                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError() {
                Snackbar.make(getActivity().findViewById(android.R.id.content), "Internet Connection Fair", Snackbar.LENGTH_LONG).show();

            }

        });


        requests_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               final Dialog dialog = new Dialog( getActivity()) ;
                dialog.setContentView(R.layout.task_details_dialog);
                dialog.setTitle("Task Details");


                final TextView address= (TextView) dialog.findViewById(R.id.xRestaurantAddress);
                final TextView taskQuantity= (TextView) dialog.findViewById(R.id.xTaskQuantity);
                final TextView taskEstimatedTime= (TextView) dialog.findViewById(R.id.xtaskEstimatedtime);
                Button   assignBtn= (Button) dialog.findViewById(R.id.xAssignTaskBtn);


                 x = quantity[position].split(":");
                address.setText(x[1]);
                taskQuantity.setText(x[2]);
                taskEstimatedTime.setText(x[3]);
                dialog.show();



                assignBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

//                        final String title =web.sharedPreferences.getString("typename" , "******");
//                        final String quantity =edtFoodQ.getText().toString() ;
                        // final String image = editTextImage.getText().toString();

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
                                params.put("title","Task From Charity" );
                                params.put("message",x[0]+"#"+ address.getText().toString() + "#" +taskQuantity.getText().toString() +"#"+taskEstimatedTime.getText().toString()+"#");
                                params.put("charity_parent_id", ""+web.sharedPreferences.getInt("id" , 0));


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

    /*this method will be called when deleting an member*/
    public void deleteItem(int memberPosition){

    }

    /*this method will be called when adding new member*/
    public void addMember(){

    }


}
