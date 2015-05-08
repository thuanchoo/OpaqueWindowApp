package com.example.thuan.opaquewindowapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TableRow;
import android.widget.Toast;

/* This class is used to select the room(s) that the window will be in
 * and will allow the user to enter that specific room to control the window
 * pane/ room state.
 */
public class SelectRoomPage extends Activity implements View.OnClickListener {

    TableRow tableRow1, tableRow2, tableRow3, tableRow4, tableRow5;
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

    /*This function allows the user to interact with the room options.
     * For the purpose of this project, only the main room is implemented
     * and all other rooms will return a message indicating that no
     * product is installed in that room.
     */
        public void onClick(View v) {
        switch(v.getId()) {
            case R.id.tableRow1:
                if (isInternetConnected()){
                    Intent intent = new Intent(SelectRoomPage.this, ControlRoomPage.class);
                    startActivity(intent);
                    SelectRoomPage.this.finish();
                }
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

        /* Check if internet is not connected; prompt user to enable internet
         * and try again.
        */
    public boolean isInternetConnected(){
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        WifiManager wifi = (WifiManager)getSystemService(Context.WIFI_SERVICE);
        //check wifi connectivity
        if (!wifi.isWifiEnabled()){
            //check data connectivity
            if (cm.getNetworkInfo(0).getState() == NetworkInfo.State.DISCONNECTED ||
                    cm.getNetworkInfo(1).getState() == NetworkInfo.State.DISCONNECTING) {
                Toast.makeText(getApplicationContext(), "Please connect to internet before continuing",
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }

    /*This function alerts the user that there are no devices in the selected room.
     * This will prompt a message indicating so and allows the user to go back to
     * select another room.
     */
    private void doesRoomExist () {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        dialogBuilder
                .setMessage("No devices found in this room.")
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


