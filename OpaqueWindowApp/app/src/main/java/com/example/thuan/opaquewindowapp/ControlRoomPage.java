package com.example.thuan.opaquewindowapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.widget.Toast;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.net.HttpURLConnection;
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

        /* Check if internet is connected. If it's not connected, prompt user to enable internet
         * and try again.
        */
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo data = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        if (cm.getNetworkInfo(0).getState() == NetworkInfo.State.CONNECTED ||
                cm.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTING ||
                data.isConnected()) {
            Toast.makeText(getApplicationContext(), "Internet is connected",Toast.LENGTH_SHORT).show();

        }
        // If internet is not enabled, display error message and kick user out of page
        else if (cm.getNetworkInfo(0).getState() == NetworkInfo.State.DISCONNECTED ||
                cm.getNetworkInfo(1).getState() == NetworkInfo.State.DISCONNECTING) {
            Toast.makeText(getApplicationContext(), "Internet is not connected",Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(ControlRoomPage.this, SelectRoomPage.class);
            startActivity(intent);
            ControlRoomPage.this.finish();
        }
        /*Check if website is available. If not, output message and return to main page */
        if(isSiteAvailable())
            Toast.makeText(getApplicationContext(), "Server connection: Success",Toast.LENGTH_SHORT).show();
        else{
            Toast.makeText(getApplicationContext(), "Cannot connect to server, try again later",Toast.LENGTH_SHORT).show();
        }

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

    boolean isSiteAvailable(){
        try {
            URL url = new URL ("http://192.168.43.17");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();
            urlConnection.disconnect();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }




}
