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
import android.webkit.WebChromeClient;
import android.widget.Toast;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;

/* This is the control page for the main room selected.
 * This fragment will allow the user to control the product in
 * the specified room by connecting the to the web page that hosts
 * the GUI for the controls and communication between the application
 * and the product.
 */
public class ControlRoomPage extends Activity {

    /*Create webview*/
    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    //allow back button to bo back to select room page
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(ControlRoomPage.this, SelectRoomPage.class));
        finish();

    }
    /* On resume, if there is no internet connection, kick user out of the control room page*/
    @Override
    public void onResume(){
        super.onResume();
        if(!isInternetConnected()){
            Toast.makeText(getApplicationContext(), "Lost internet Connection",
                    Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ControlRoomPage.this, SelectRoomPage.class);
            startActivity(intent);
            ControlRoomPage.this.finish();
        }
    }

    /* To obtain the GUI, a webview is used to display the web page inside the
     * application. In order for the application to control the product, proper
     * internet connection is required. The following code will determine if
     * there is a proper internet connection and indicate so. If there is no internet
     * connection, the website will not be able to load, thus the user will have to
     * refresh the page.
     */
    WebView webview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_room_page);

        webview=(WebView)findViewById(R.id.webView);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setWebViewClient(new MyWebViewClient());

        final Context myApp = this;

/* Set web client to allow javascript messages from the website*/
        webview.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, final android.webkit.JsResult result)
            {
                new AlertDialog.Builder(myApp)
                        .setTitle("          System Message")
                        .setMessage(message)
                        .setPositiveButton(android.R.string.ok,
                                new AlertDialog.OnClickListener()
                                {
                                    public void onClick(DialogInterface dialog, int which)
                                    {
                                        result.confirm();
                                    }
                                })
                        .setCancelable(false)
                        .create()
                        .show();
                return true;
            };
        });
        //open the connection and display to user
        openURL();
    }
     /* Function that Opens the URL in webview browser within app.
      * Since we know exactly what IP the site is, the application will
      * attempt to load that website.
      */
    private void openURL() {

        webview.loadUrl("http://192.168.43.17");
        webview.requestFocus();
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

}
