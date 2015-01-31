package com.example.thuan.opaquewindowapp;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.ImageView;


public class OpaqueSplashPage extends ActionBarActivity {

    AnimationDrawable windowAnimation;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opaque_splash_page);
        image = (ImageView) findViewById(R.id.window_animation);
        image.setBackgroundResource(R.drawable.splash_page_animation);
        windowAnimation = (AnimationDrawable) image.getBackground();
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        if(hasFocus) {
            windowAnimation.start();
        }
    }
}
