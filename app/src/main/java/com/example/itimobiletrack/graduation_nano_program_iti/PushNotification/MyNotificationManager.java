package com.example.itimobiletrack.graduation_nano_program_iti.PushNotification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.text.Html;
import android.widget.Toast;

import com.example.itimobiletrack.graduation_nano_program_iti.Charity.CharityProfile;
import com.example.itimobiletrack.graduation_nano_program_iti.Login.LoginRegisterActivity;
import com.example.itimobiletrack.graduation_nano_program_iti.Member.MemberProfile;
import com.example.itimobiletrack.graduation_nano_program_iti.R;
import com.example.itimobiletrack.graduation_nano_program_iti.Web.webServices;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ahmed on 3/26/2017.
 */

//this class to create the required method to show the notifications
public class MyNotificationManager {

private webServices web;
        public static final int ID_BIG_NOTIFICATION = 234;
        public static final int ID_SMALL_NOTIFICATION = 235;

        private Context mCtx;

        public MyNotificationManager(Context mCtx) {
            web =new webServices();
            web.sharedPreferences =mCtx.getSharedPreferences("load_data",0);
            this.mCtx = mCtx;
        }

        //the method will show a big notification with an image
        //parameters are title for message title, message for message text, url of the big image and an intent that will open
        //when you will tap on the notification
        public void showBigNotification(String title, String message, String url, Intent intent) {


            if(web.sharedPreferences.getString("typename" , "******").equals("******")){
                intent = new Intent(mCtx, LoginRegisterActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);

            }else {
                intent = new Intent(mCtx, CharityProfile.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
            }

            PendingIntent resultPendingIntent = PendingIntent.getActivity(mCtx, ID_BIG_NOTIFICATION, intent,PendingIntent.FLAG_UPDATE_CURRENT
                    );
            NotificationCompat.BigPictureStyle bigPictureStyle = new NotificationCompat.BigPictureStyle();
            bigPictureStyle.setBigContentTitle(title);
            bigPictureStyle.setSummaryText(Html.fromHtml(message).toString());
            bigPictureStyle.bigPicture(getBitmapFromURL(url));
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(mCtx);
            Notification notification;
            notification = mBuilder.setSmallIcon(R.mipmap.green).setTicker(title).setWhen(0)
//                    .setAutoCancel(true)
                    .setContentIntent(resultPendingIntent)
                    .setContentTitle(title)
                    .setStyle(bigPictureStyle)
                    .setSmallIcon(R.mipmap.green)
                    .setLargeIcon(BitmapFactory.decodeResource(mCtx.getResources(), R.mipmap.green))
                    .setContentText(message)
                    .build();

            notification.defaults |= Notification.DEFAULT_SOUND;
            notification.defaults |= Notification.DEFAULT_VIBRATE;

            notification.flags |= Notification.FLAG_AUTO_CANCEL;

            NotificationManager notificationManager = (NotificationManager) mCtx.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(ID_BIG_NOTIFICATION, notification);
        }

        //the method will show a small notification
        //parameters are title for message title, message for message text and an intent that will open
        //when you will tap on the notification
        public void showSmallNotification(String title, String message, Intent intent) {


            if(web.sharedPreferences.getString("typename" , "******").equals("******")){
                intent = new Intent(mCtx, LoginRegisterActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);


            }
            else if (web.sharedPreferences.getString("typename" , "******").equals("Charity")) {
                intent = new Intent(mCtx, CharityProfile.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);

            }
            else if (web.sharedPreferences.getString("typename" , "******").equals("Member")) {
                intent = new Intent(mCtx, MemberProfile.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                Toast.makeText(mCtx, "    ", Toast.LENGTH_SHORT).show();
            }
            PendingIntent resultPendingIntent =
                    PendingIntent.getActivity(
                            mCtx,
                            ID_SMALL_NOTIFICATION,
                            intent,
                            PendingIntent.FLAG_UPDATE_CURRENT
                    );


            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(mCtx);
            Notification notification;
            notification = mBuilder.setSmallIcon(R.mipmap.green).setTicker(title).setWhen(0)
                    .setAutoCancel(true)
                    .setContentIntent(resultPendingIntent)
                    .setContentTitle(title)
                    .setSmallIcon(R.mipmap.green)
                    .setLargeIcon(BitmapFactory.decodeResource(mCtx.getResources(), R.mipmap.green))
                    .setContentText(message)
                    .build();

            notification.defaults |= Notification.DEFAULT_SOUND;
            notification.defaults |= Notification.DEFAULT_VIBRATE;
            notification.flags |= Notification.FLAG_AUTO_CANCEL;

            NotificationManager notificationManager = (NotificationManager) mCtx.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(ID_SMALL_NOTIFICATION, notification);
        }

        //The method will return Bitmap from an image URL
        private Bitmap getBitmapFromURL(String strURL) {
            try {
                URL url = new URL(strURL);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);
                return myBitmap;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
}

