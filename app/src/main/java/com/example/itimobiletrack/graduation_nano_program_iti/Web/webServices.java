package com.example.itimobiletrack.graduation_nano_program_iti.Web;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.itimobiletrack.graduation_nano_program_iti.Charity.CharityProfile;
import com.example.itimobiletrack.graduation_nano_program_iti.Charity.MembersFragment;
import com.example.itimobiletrack.graduation_nano_program_iti.Login.LoginRegisterActivity;
import com.example.itimobiletrack.graduation_nano_program_iti.Member.MemberProfile;
import com.example.itimobiletrack.graduation_nano_program_iti.PushNotification.EndPoints;
import com.example.itimobiletrack.graduation_nano_program_iti.PushNotification.SharedPrefManager;
import com.example.itimobiletrack.graduation_nano_program_iti.R;
import com.example.itimobiletrack.graduation_nano_program_iti.Restaurant.EditRestaurantProfile;
import com.example.itimobiletrack.graduation_nano_program_iti.Restaurant.RestaurantProfile;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


public class webServices {

    public SharedPreferences sharedPreferences ;
    public SharedPreferences.Editor editor;





    private  GetProfileInfo getProfileInfo ;
    private ProgressDialog mProgressDialog;

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
    public static String CHARITY_PARENT_ID = "charity_parent_id";
    public static String KEY_TOKEN = "token";

  // For table Tasks
    public static String RESTAURANT_ID = "restaurant_id";
    public static String REQUESTSTAUS = "request_status";
    public static String TASKQUANTITY = "task_quantity";
    public static String TASKESTIMATEDTIME = "task_estimated_time";
    public static String MEMBER_ID = "member_id";


   //for table rate
    public static String rate_charity="rate_charity";
    public static String rate_restaurant="rate_restaurant";
    public static String User_Id="user_id";
    public static String charity_Name="charity_name";
    public static String restaurant_Name="restaurant_name";
    public static String rate_Type="type";
// For table Charity_tasks
    public static String TASK_ID = "task_id";


    // TODO Tag that use it to know type of WebService   >> you can here put the TAG  of your method Name ------//
    public static String TAG = "tag";

    public static String ADD_USER_TAG = "add_user";
    public static String USERLOGINTAG = "user_login";
    public static String FORGETUSERPASSWORD = "forget_password_user";
    public static String GETMEMBERDATA = "getMember";
    public static String GETMEMBERDATAOFDIALOG="getMemberDataOfDialog";
    public static String DELTEMEMBER="delete_member";
    public static String UPDATECHARITY="update_charity";
    public static String GETALLCHARITY="getAllCharity";
    public static String UPDATERESTAURANT="update_rest";
    public static String ADDTASK="add_request_toTask";
    public static String GETTASKSPOSTED="getAllRequsts";
    public static String UPDATETOKEN="update_token";
    public static String UPDATETASK="update_Task";
    public static String ADDCHARITYTASK="add_Charity_Task";
    public static String CHARITY_RATE_BY_USER="rateCharityByRestaurant";
    public static String CHECK_STATUS="check_status";




    private RequestQueue queue;
    private String url = "https://re-restaurant.000webhostapp.com/uploads/re_database/re_tags.php";


    // TODO Login Method ----------------------------------//
    public void  user_login(final Activity activity, final String username, final String password)
        {
            getProfileInfo = new GetProfileInfo();

        queue = Volley.newRequestQueue(activity);
        final StringRequest request = new StringRequest(com.android.volley.Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {


                    sharedPreferences = activity.getSharedPreferences("load_data",0);
                    editor = sharedPreferences.edit();

                    JSONObject jsonObject=new JSONObject(response);
                    String login_response=jsonObject.getString("login_response");

                    if(login_response.equals("done")) {

                         ID = jsonObject.getString("user_id");
                         String userType = jsonObject.getString("type");

                        getProfileInfo. setUser_id(jsonObject.getString("user_id"));
                        getProfileInfo. setTypeNameVar(jsonObject.getString("typename"));
                        getProfileInfo. setType(jsonObject.getString("type"));
                        getProfileInfo. setTypeNameVar(jsonObject.getString("typename"));
                        getProfileInfo. setEmailVar(jsonObject.getString("email"));
                        getProfileInfo. setUserName(jsonObject.getString("username"));
                        getProfileInfo. setPassword(jsonObject.getString("password"));
                        getProfileInfo. setPhone(jsonObject.getString("phone"));
                        getProfileInfo. setAddress(jsonObject.getString("address"));
                        getProfileInfo. setCharity_parent_id(jsonObject.getString("charity_parent_id"));
                        if(userType.equals("Restaurant")){

                             editor.putInt("id" ,Integer.parseInt(getProfileInfo.getUser_id()));
                             editor.putString("typename" ,getProfileInfo.getTypeNameVar() );
                             editor.putString("type" ,getProfileInfo.getType() );

                             editor.putString("email" , getProfileInfo.getEmailVar());
                             editor.putString("username" , getProfileInfo.getUserName());
                             editor.putString("password" , getProfileInfo.getPassword());
                             editor.putString("phone" , getProfileInfo.getPhone());
                             editor.putString("address" , getProfileInfo.getAddress());
                             editor.commit();

                            sharedPreferences=activity.getSharedPreferences("load_data" , 0);
                            new webServices().updateToken(activity ,SharedPrefManager.getInstance(activity).getDeviceToken() ,sharedPreferences.getString("username" , "******"));
                            Intent intent = new Intent(activity,RestaurantProfile.class);
                            activity.startActivity(intent);
                        }
                        else if(userType.equals("Charity"))
                        {



                            editor.putInt("id" ,Integer.parseInt(getProfileInfo.getUser_id()));
                            editor.putString("typename" ,getProfileInfo.getTypeNameVar() );
                            editor.putString("type" ,getProfileInfo.getType() );
                            editor.putString("email" , getProfileInfo.getEmailVar());
                            editor.putString("username" , getProfileInfo.getUserName());
                            editor.putString("password" , getProfileInfo.getPassword());
                            editor.putString("phone" , getProfileInfo.getPhone());
                            editor.putString("address" , getProfileInfo.getAddress());
                            editor.putString("charity_parent_id" , getProfileInfo.getCharity_parent_id());
                            editor.commit();




                  // TODO  Call of update_token

                    sharedPreferences=activity.getSharedPreferences("load_data" , 0);
                     new webServices().updateToken(activity ,SharedPrefManager.getInstance(activity).getDeviceToken() ,sharedPreferences.getString("username" , "******"));
                            Intent intent = new Intent(activity,CharityProfile.class);
                            activity.startActivity(intent);



                        }
                        else if(userType.equals("Member"))
                        {


                            editor.putInt("id" ,Integer.parseInt(getProfileInfo.getUser_id()));
                            editor.putString("typename" ,getProfileInfo.getTypeNameVar() );
                            editor.putString("type" ,getProfileInfo.getType() );
                            editor.putString("email" , getProfileInfo.getEmailVar());
                            editor.putString("username" , getProfileInfo.getUserName());
                            editor.putString("password" , getProfileInfo.getPassword());
                            editor.putString("phone" , getProfileInfo.getPhone());
                            editor.putString("address" , getProfileInfo.getAddress());
                            editor.putString("token" , getProfileInfo.getToken());
                            editor.putString("charity_parent_id" , getProfileInfo.getCharity_parent_id());
                            editor.commit();


                            sharedPreferences=activity.getSharedPreferences("load_data" , 0);
                            new webServices().updateToken(activity ,SharedPrefManager.getInstance(activity).getDeviceToken() ,sharedPreferences.getString("username" , "******"));
                            Intent intent = new Intent(activity,MemberProfile.class);

                            activity.startActivity(intent);


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
                Snackbar.make(activity.findViewById(android.R.id.content), "Internet Connection", Snackbar.LENGTH_LONG).show();

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
                        final String phone, final String address, final String type ,final  String typename, final int status
                        ,final int charityParentId ,   final  String keyToken)
        {



        sharedPreferences =activity.getSharedPreferences("register_data" , 0);
        editor =sharedPreferences.edit();

        queue = Volley.newRequestQueue(activity);
        StringRequest request = new StringRequest(com.android.volley.Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                editor.putString("username_r",username);
                editor.putString("password_r" , password);
                editor.putString("token_r" , keyToken);
                editor.commit();



                Intent intent = new Intent(activity, LoginRegisterActivity.class);
                activity.startActivity(intent);
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Snackbar.make(activity.findViewById(android.R.id.content), "Internet Connection", Snackbar.LENGTH_LONG).show();
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
                params.put(CHARITY_PARENT_ID, ""+charityParentId);
                params.put(KEY_TOKEN, keyToken);

                params.put(TAG,ADD_USER_TAG );
                return params;
            }
        };
        queue.add(request);
    }


    // TODO Add Memeber


    public void addMember(final Activity activity, final String username, final String password, final String email,
                        final String phone, final String address, final String type ,final  String typename, final int status
            ,final int charityParentId , final  String keyToken)
    {
        queue = Volley.newRequestQueue(activity);
        StringRequest request = new StringRequest(com.android.volley.Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Snackbar.make(activity.findViewById(android.R.id.content), "Member added Successfully.", Snackbar.LENGTH_LONG).show();

                FragmentManager fm =activity.getFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                ft.replace(R.id.content_main,new MembersFragment());
                ft.commit();

            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Snackbar.make(activity.findViewById(android.R.id.content), "Internet Connection", Snackbar.LENGTH_LONG).show();
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
                params.put(CHARITY_PARENT_ID, ""+charityParentId);
                params.put(KEY_TOKEN, ""+keyToken);

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




    // TODO getMemberData
    public void getMemberData(final Activity activity, final int charity_parent_id, final request_interface object) {
        queue = Volley.newRequestQueue(activity);
        StringRequest request = new StringRequest(com.android.volley.Request.Method.POST, url, new Response.Listener<String>() {



            @Override
            public void onResponse(String response) {
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
                params.put(CHARITY_PARENT_ID, ""+charity_parent_id);
                params.put(TAG, GETMEMBERDATA);
                return params;
            }


        };
        queue.add(request);

    }




    //==============================================================================//
    public void getMemberDataOfDialog(Activity activity, final String userName, final request_interface request_interface){
        queue = Volley.newRequestQueue(activity);
        StringRequest request = new StringRequest(com.android.volley.Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                request_interface.onResponse(response);

            }

        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                request_interface.onError();


            }
        }){
            @Override
            protected java.util.Map<String, String> getParams() throws AuthFailureError {
                java.util.Map<String, String> params = new HashMap<String, String>();
                params.put(USERNAME, userName);
                params.put(TAG,GETMEMBERDATAOFDIALOG );
                return params;
            }
        };
        queue.add(request);

    }

    //=======================================================================================//

    // TODO Delete Member
    public void deleteMember(final Activity activity, final String username)
    {
        queue = Volley.newRequestQueue(activity);
        StringRequest request = new StringRequest(com.android.volley.Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                Snackbar.make(activity.findViewById(android.R.id.content), "Member Deleted Successfully.", Snackbar.LENGTH_LONG).show();

                FragmentManager fm =activity.getFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                ft.replace(R.id.content_main,new MembersFragment());
                ft.commit();

            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Snackbar.make(activity.findViewById(android.R.id.content), "Internet Connection", Snackbar.LENGTH_LONG).show();
            }
        })

        {
            @Override
            protected java.util.Map<String, String> getParams() throws AuthFailureError {
                java.util.Map<String, String> params = new HashMap<String, String>();
                params.put(USERNAME, username);
                params.put(TAG,DELTEMEMBER );
                return params;
            }
        };
        queue.add(request);
    }


     //===================================================================================//

    //TODO Update Charity data
    public void updateCharity(final Activity activity,final  String type ,final String  oldUserName,final String userName, final String password , final String phone , final String address)
    {
        queue = Volley.newRequestQueue(activity);
        StringRequest request = new StringRequest(com.android.volley.Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                  Intent intent =new Intent(activity,LoginRegisterActivity.class);
                  activity.startActivity(intent);

            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Snackbar.make(activity.findViewById(android.R.id.content), "Internet Connection", Snackbar.LENGTH_LONG).show();
            }
        })

        {
            @Override
            protected java.util.Map<String, String> getParams() throws AuthFailureError {
                java.util.Map<String, String> params = new HashMap<String, String>();

                params.put(TYPE, type);
                params.put(USERNAME, oldUserName);

                params.put(USERNAME, userName);
                params.put(PASSWORD, password);
                params.put(PHONE, phone);
                params.put(ADDRESS, address);

                params.put(TAG,UPDATECHARITY );
                return params;
            }
        };
        queue.add(request);
    }



    // TODO   getAllCharity
    public void getAllCharity(final Activity activity, final String type, final request_interface object) {
        queue = Volley.newRequestQueue(activity);
        StringRequest request = new StringRequest(com.android.volley.Request.Method.POST, url, new Response.Listener<String>() {



            @Override
            public void onResponse(String response) {
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
                params.put(TYPE, type);
                params.put(TAG, GETALLCHARITY);
                return params;
            }


        };
        queue.add(request);

    }





    //=========================================================//
    // TODO Update_rest

    //TODO Update Charity data
    public void update_restaurant(final Activity activity,final  String type ,final String  oldUserName,final String userName, final String password , final String phone , final String address ,final  String email)
    {
        queue = Volley.newRequestQueue(activity);
        StringRequest request = new StringRequest(com.android.volley.Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Intent intent =new Intent(activity,LoginRegisterActivity.class);
                activity.startActivity(intent);

            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Snackbar.make(activity.findViewById(android.R.id.content), "Internet Connection", Snackbar.LENGTH_LONG).show();
            }
        })

        {
            @Override
            protected java.util.Map<String, String> getParams() throws AuthFailureError {
                java.util.Map<String, String> params = new HashMap<String, String>();

                params.put(TYPE, type);
                params.put(USERNAME, oldUserName);

                params.put(USERNAME, userName);
                params.put(PASSWORD, password);
                params.put(PHONE, phone);
                params.put(ADDRESS, address);
                params.put(EMAIL, email);

                params.put(TAG,UPDATERESTAURANT );
                return params;
            }
        };
        queue.add(request);
    }



    //======================================================================================================//

    public void addTask(final Activity activity, final int restaurant_id, final String request_status, final String task_quantity,
                        final String task_estimated_time, final int  member_id)
    {



        queue = Volley.newRequestQueue(activity);
        StringRequest request = new StringRequest(com.android.volley.Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Snackbar.make(activity.findViewById(android.R.id.content), "Internet Connection", Snackbar.LENGTH_LONG).show();
            }
        })

        {
            @Override
            protected java.util.Map<String, String> getParams() throws AuthFailureError {
                java.util.Map<String, String> params = new HashMap<String, String>();
                params.put(RESTAURANT_ID, ""+restaurant_id);
                params.put(REQUESTSTAUS, request_status);
                params.put(TASKQUANTITY, task_quantity);
                params.put(TASKESTIMATEDTIME, task_estimated_time);
                params.put(MEMBER_ID, ""+member_id);


                params.put(TAG,ADDTASK );
                return params;
            }
        };
        queue.add(request);
    }
//----------------------add rate to charity by restaurant -------------------------------------------//
   public void addRateCharityByRestaurant(final Activity activity ,final int charity_rate,final int restaurant_rate ,
                 final int user_id,final String charity_name,final String restaurant_name,final String type){


       queue = Volley.newRequestQueue(activity);
       StringRequest request = new StringRequest(com.android.volley.Request.Method.POST, url, new Response.Listener<String>() {
           @Override
           public void onResponse(String response) {

               Toast.makeText(activity, "on response in rate action ", Toast.LENGTH_SHORT).show();
           }


       }, new Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError error) {
               Snackbar.make(activity.findViewById(android.R.id.content), "Internet Connection", Snackbar.LENGTH_LONG).show();
           }
       })

       {
           @Override
           protected java.util.Map<String, String> getParams() throws AuthFailureError {
               java.util.Map<String, String> params = new HashMap<String, String>();

               params.put(rate_charity, ""+charity_rate);
               params.put(rate_restaurant, ""+restaurant_rate);
               params.put(User_Id , ""+ user_id);
               params.put(charity_Name,charity_name);
               params.put(restaurant_Name,restaurant_name);
               params.put(rate_Type,type);


               params.put(TAG,CHARITY_RATE_BY_USER );
               return params;
           }
       };
       queue.add(request);
   }


    //========================================================

    // TODO GetAllRequests

    public void getAllRequests(final Activity activity, final request_interface object) {
        queue = Volley.newRequestQueue(activity);
        StringRequest request = new StringRequest(com.android.volley.Request.Method.POST, url, new Response.Listener<String>() {



            @Override
            public void onResponse(String response) {
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

                params.put(TAG, GETTASKSPOSTED);
                return params;
            }


        };
        queue.add(request);

    }


    //=========================================================//


   // TODO update Token


    public void updateToken(final Activity activity,final String token,  final String userName)
          {


              mProgressDialog =new ProgressDialog(activity);
              mProgressDialog.setMessage("Loading.....");
              mProgressDialog.show();
        queue = Volley.newRequestQueue(activity);
        StringRequest request = new StringRequest(com.android.volley.Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

              mProgressDialog .dismiss();
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Snackbar.make(activity.findViewById(android.R.id.content), "Internet Connection", Snackbar.LENGTH_LONG).show();
            }
        })

        {
            @Override
            protected java.util.Map<String, String> getParams() throws AuthFailureError {
                java.util.Map<String, String> params = new HashMap<String, String>();

                params.put(KEY_TOKEN, token);
                params.put(USERNAME, userName);
                params.put(TAG,UPDATETOKEN );
                return params;
            }
        };
        queue.add(request);
    }





    // TODO Upate _ Task


    public void updateTask(final Activity activity,final int member_id ,final int   id)
    {
        queue = Volley.newRequestQueue(activity);
        StringRequest request = new StringRequest(com.android.volley.Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Snackbar.make(activity.findViewById(android.R.id.content), "Now, you Are Accept the task", Snackbar.LENGTH_LONG).show();
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Snackbar.make(activity.findViewById(android.R.id.content), "Internet Connection", Snackbar.LENGTH_LONG).show();
            }
        })

        {
            @Override
            protected java.util.Map<String, String> getParams() throws AuthFailureError {
                java.util.Map<String, String> params = new HashMap<String, String>();

                params.put(MEMBER_ID, ""+member_id);
                params.put(ID, ""+id);
                params.put(TAG,UPDATETASK );
                return params;
            }
        };
        queue.add(request);
    }




    // TODO Add Charity _ Task

    public void addCharityTask(final Activity activity,final int charityParentId ,   final  int  task_id)
    {



        sharedPreferences =activity.getSharedPreferences("register_data" , 0);
        editor =sharedPreferences.edit();

        queue = Volley.newRequestQueue(activity);
        StringRequest request = new StringRequest(com.android.volley.Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Snackbar.make(activity.findViewById(android.R.id.content), "Internet Connection", Snackbar.LENGTH_LONG).show();
            }
        })

        {
            @Override
            protected java.util.Map<String, String> getParams() throws AuthFailureError {
                java.util.Map<String, String> params = new HashMap<String, String>();

                params.put(CHARITY_PARENT_ID, ""+charityParentId);
                params.put(TASK_ID, ""+task_id);

                params.put(TAG,ADDCHARITYTASK );
                return params;
            }
        };
        queue.add(request);
    }




    //=================================================================================================//

    //TODO check Request Task
    public void checkRequestTask(final Activity activity, final int id , final request_interface object) {
        queue = Volley.newRequestQueue(activity);
        StringRequest request = new StringRequest(com.android.volley.Request.Method.POST, url, new Response.Listener<String>() {



            @Override
            public void onResponse(String response) {
                object.onResponse(response);

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                object.onError();

            }
        })
        {
            @Override
            protected java.util.Map<String, String> getParams() throws AuthFailureError {
                java.util.Map<String, String> params = new HashMap<String, String>();
                params.put(ID, ""+id);
                params.put(TAG, CHECK_STATUS);
                return params;
            }
        };
        queue.add(request);

    }


}



