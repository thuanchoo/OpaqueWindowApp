package com.example.thuan.opaquewindowapp;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableRow;


public class SelectRoomPage extends Activity implements View.OnClickListener {

    TableRow tableRow1, tableRow2, tableRow3, tableRow4, tableRow5;

    boolean roomExists = false;
    final Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_room_page);

        tableRow1 = (TableRow) findViewById(R.id.tableRow1);
        tableRow2 = (TableRow) findViewById(R.id.tableRow2);
        tableRow3 = (TableRow) findViewById(R.id.tableRow3);
        tableRow4 = (TableRow) findViewById(R.id.tableRow4);
        tableRow5 = (TableRow) findViewById(R.id.tableRow5);

        tableRow1.setOnClickListener(this);
        tableRow2.setOnClickListener(this);
        tableRow3.setOnClickListener(this);
        tableRow4.setOnClickListener(this);
        tableRow5.setOnClickListener(this);


    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.tableRow1:
                Intent intent = new Intent(SelectRoomPage.this, ControlRoomPage.class);
                startActivity(intent);
                SelectRoomPage.this.finish();
                break;
            case R.id.tableRow2:
                doesRoomExist();
                break;
            case R.id.tableRow3:
                doesRoomExist();
                break;
            case R.id.tableRow4:
                doesRoomExist();
                break;
            case R.id.tableRow5:
                doesRoomExist();
                break;
            default:
                break;

        }
    }



    private void doesRoomExist () {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        dialogBuilder
                .setMessage("No Insteon device found in this room.")
                .setCancelable(false)
                .setNegativeButton("Take me back", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();


    }

}


