package com.example.itimobiletrack.graduation_nano_program_iti;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


public class webServices {

    private  GetProfileInfo getProfileInfo ;


    // TODO Parameters Of Tables and WebService Function
    public static String ID = "id";
    public static String USERNAME = "username";
    public static String PASSWORD = "password";
    public static String EMAIL = "email";
    public static String PHONE = "phone";
    public static String ADDRESS = "address";
    public static String TYPE = "type";
    public static String TYPENAME = "typename";
    public static String STATUS = "status_of_member";

    // TODO Tag that use it to know type of WebService   >> you can here put the TAG  of your method Name ------//
    public static String TAG = "tag";
    public static String ADD_USER_TAG = "add_user";
    public static String USERLOGINTAG = "user_login";
    public static String FORGETUSERPASSWORD = "forget_password_user";




    private RequestQueue queue;
    private String url = "https://re-restaurant.000webhostapp.com/uploads/re_database/re_tags.php";


    // TODO Vars to get user loged  data  && Getter Setter






    // TODO Login Method ----------------------------------//
    public void  user_login(final Activity activity, final String username, final String password)
        {
            getProfileInfo = new GetProfileInfo();

        queue = Volley.newRequestQueue(activity);
        final StringRequest request = new StringRequest(com.android.volley.Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonObject=new JSONObject(response);
                    String login_response=jsonObject.getString("login_response");

                    if(login_response.equals("done")) {

                         ID = jsonObject.getString("user_id");
                         String userType = jsonObject.getString("type");


                        getProfileInfo.setTypeNameVar(jsonObject.getString("typename"));
                        getProfileInfo. setEmailVar(jsonObject.getString("email"));

                        if(userType.equals("Restaurant")){

                         Intent i =new Intent(activity,MainActivity.class);
                         activity.startActivity(i);

                        }
                        else if(userType.equals("Charity"))
                        {

                            Intent intent = new Intent(activity,CharityProfile.class);
                            intent.putExtra("typename",getProfileInfo.getTypeNameVar());
                            intent.putExtra("email",getProfileInfo.getEmailVar());
                            activity.startActivity(intent);


                        }
                        else
                        {
                            Toast.makeText(activity, "not any one", Toast.LENGTH_SHORT).show();

                        }


                    }
                    else if(login_response.equals("incorrect password")){
                        Toast.makeText(activity,"incorrect password please try again",Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(activity,"User Invalid. ",Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {

                }
            }


        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(activity,"connection error ",Toast.LENGTH_LONG).show();


            }
        }){
            protected java.util.Map<String, String> getParams() throws AuthFailureError {
                java.util.Map<String, String> params = new HashMap<String, String>();
                params.put(USERNAME, username);
                params.put(PASSWORD, password);
                params.put(TAG,USERLOGINTAG );
                return params;
            }

        };
        queue.add(request);

    }




    // TODO Register Method --------------------//

    public void addUser(final Activity activity, final String username, final String password, final String email,
                        final String phone, final String address, final String type ,final  String typename, final int status)
        {
        queue = Volley.newRequestQueue(activity);
        StringRequest request = new StringRequest(com.android.volley.Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(activity, " register done", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(activity, LoginRegisterPhase.class);
                activity.startActivity(intent);
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(activity, "reponse Failed.", Toast.LENGTH_SHORT).show();

            }
        })

        {
            @Override
            protected java.util.Map<String, String> getParams() throws AuthFailureError {
                java.util.Map<String, String> params = new HashMap<String, String>();
                params.put(USERNAME, username);
                params.put(PASSWORD, password);
                params.put(EMAIL, email);
                params.put(PHONE, phone);
                params.put(ADDRESS, address);
                params.put(TYPE, type);
                params.put(TYPENAME, typename);
                params.put(STATUS, ""+status);

                params.put(TAG,ADD_USER_TAG );
                return params;
            }
        };
        queue.add(request);
    }



    // TODO forget_password_user   Method
    public void forgetPasswordUser(final Activity activity, final String user_name,  final request_interface object)
        {
        queue = Volley.newRequestQueue(activity);
        StringRequest request = new StringRequest(com.android.volley.Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("RESPONSE",response);
                object.onResponse(response);
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                object.onError();

            }
        }) {
            @Override
            protected java.util.Map<String, String> getParams() throws AuthFailureError {
                java.util.Map<String, String> params = new HashMap<String, String>();
                params.put(USERNAME, user_name);
                params.put(TAG,FORGETUSERPASSWORD);
                return params;
            }


        };
        queue.add(request);

    }






}



