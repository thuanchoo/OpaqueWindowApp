package com.example.thuan.opaquewindowapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;


/* This is a simple splash page that will open immediately and display to the user
 * once they open the application. The splash screen will remain on-screen for 5 seconds
 * where it will display an animation of a window with one pane blacked out, indicating
 * the transparency versus opaque. After 5 seconds, this fragment will finish and transfer
 * over to the main control page where the user may select which room to operate on.
 */
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
