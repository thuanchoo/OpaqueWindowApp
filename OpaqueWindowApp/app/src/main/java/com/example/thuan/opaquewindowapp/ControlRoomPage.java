package com.example.thuan.opaquewindowapp;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class ControlRoomPage extends Activity implements View.OnClickListener {

    private Button winButton1, winButton2, winButton3, winButton4;
    ColorDrawable buttonColor;
    int colorID;

    boolean togglePane1 = false;
    boolean togglePane2 = false;
    boolean togglePane3 = false;
    boolean togglePane4 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_room_page);

        winButton1 = (Button) findViewById(R.id.winButton1);
        winButton2 = (Button) findViewById(R.id.winButton2);
        winButton3 = (Button) findViewById(R.id.winButton3);
        winButton4 = (Button) findViewById(R.id.winButton4);

        winButton1.setOnClickListener(this);
        winButton2.setOnClickListener(this);
        winButton3.setOnClickListener(this);
        winButton4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.winButton1:
                togglePane1 = changeWindow(v,togglePane1);
                break;
            case R.id.winButton2:
                togglePane2 = changeWindow(v,togglePane2);
                break;
            case R.id.winButton3:
                togglePane3 = changeWindow(v,togglePane3);
                break;
            case R.id.winButton4:
                togglePane4 = changeWindow(v,togglePane4);
                break;
            default:
                break;
        }
    }

    public boolean changeWindow(View v, boolean toggle) {

        if (toggle)
            v.setBackgroundResource(R.drawable.windowbutton);
        else
            v.setBackgroundResource(R.drawable.opaquewindow);
        return (!toggle);

    }

}
