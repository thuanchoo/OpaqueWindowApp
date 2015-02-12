package com.example.thuan.opaquewindowapp;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;


public class OpaqueSplashPage extends Activity {

    ImageView image;
    private final long SPLASH_DISPLAY_LENGTH = 5000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opaque_splash_page);
        image = (ImageView) findViewById(R.id.window_animation);
        final AnimationDrawable windowAnimation = (AnimationDrawable) image.getDrawable();

        image.post(
                new Runnable() {
                    @Override
                    public void run() {
                        windowAnimation.start();
                    }
                });

        try {
            Thread.sleep(SPLASH_DISPLAY_LENGTH);
        }catch(Exception e)
        {
            e.printStackTrace();
        }

        //open next tragment code

    }
}
