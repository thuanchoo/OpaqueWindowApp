package com.example.thuan.opaquewindowapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;



public class OpaqueSplashPage extends Activity {

    ImageView image;
    private final int SPLASH_DISPLAY_LENGTH = 5000;
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

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(OpaqueSplashPage.this, SelectRoomPage.class);
                startActivity(intent);
                OpaqueSplashPage.this.finish();
            }
        },SPLASH_DISPLAY_LENGTH);

    }
}
