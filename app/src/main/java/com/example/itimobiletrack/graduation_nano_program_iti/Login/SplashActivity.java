package com.example.itimobiletrack.graduation_nano_program_iti.Login;

import android.content.Intent;

import com.daimajia.androidanimations.library.Techniques;
import com.example.itimobiletrack.graduation_nano_program_iti.R;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;


public class SplashActivity extends AwesomeSplash {

    @Override
    public void initSplash(ConfigSplash configSplash) {
            /* you don't have to override every property */

        //Customize Circular Reveal
        configSplash.setBackgroundColor(R.color.primary); //any color you want form colors.xml
        configSplash.setAnimCircularRevealDuration(300); //int ms
        configSplash.setAnimCircularRevealDuration(300); //int ms
        configSplash.setRevealFlagX(Flags.REVEAL_RIGHT);  //or Flags.REVEAL_LEFT
        configSplash.setRevealFlagY(Flags.REVEAL_BOTTOM); //or Flags.REVEAL_TOP

        //Choose LOGO OR PATH; if you don't provide String value for path it's logo by default

        //Customize Logo
        configSplash.setLogoSplash(R.drawable.mainlogo); //or any other drawable
        configSplash.setAnimLogoSplashDuration(300); //int ms
        configSplash.setAnimLogoSplashDuration(300); //int ms
        configSplash.setAnimLogoSplashTechnique(Techniques.FlipInX);
        configSplash.setPathSplashFillColor(R.color.colorAccent);
        //choose one form Techniques (ref: https://github.com/daimajia/AndroidViewAnimations)


/*
        //Customize Path
        configSplash.setPathSplash(SyncStateContract.Constants.CONTENT_DIRECTORY); //set path String
        configSplash.setOriginalHeight(400); //in relation to your svg (path) resource
        configSplash.setOriginalWidth(400); //in relation to your svg (path) resource
        configSplash.setAnimPathStrokeDrawingDuration(1000);
        configSplash.setPathSplashStrokeSize(3); //I advise value be <5
        configSplash.setPathSplashStrokeColor(R.color.accent); //any color you want form colors.xml
        configSplash.setAnimPathFillingDuration(1000);
        configSplash.setPathSplashFillColor(R.color.Wheat); //path object filling color
*/



        //Customize Title
        configSplash.setTitleSplash("SharEat");
        configSplash.setTitleTextColor(R.color.WhiteSmoke);
        configSplash.setTitleTextSize(60f); //float value
        configSplash.setAnimTitleDuration(500);
        configSplash.setAnimTitleDuration(1500);
        configSplash.setAnimTitleTechnique(Techniques.FlipInX);
        configSplash.setTitleFont("fonts/title.ttf"); //provide string to your font located in assets/fonts/

    }

    //--------------------this refer to the next screen after splash screen finished----------------------//
    @Override
    public void animationsFinished() {
        Intent i =new Intent(SplashActivity.this,LoginRegisterActivity.class);
        startActivity(i);
        finish();
    }
}
