package com.example.thuan.opaquewindowapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.widget.Button;
import android.widget.Toast;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ControlRoomPage extends Activity {

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
    WebView webview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_room_page);

        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo data = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (cm.getNetworkInfo(0).getState() == NetworkInfo.State.CONNECTED ||
                cm.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTING ||
                data.isConnected()) {
            Toast.makeText(getApplicationContext(), "Internet is connected",Toast.LENGTH_SHORT).show();

        }

        else if (cm.getNetworkInfo(0).getState() == NetworkInfo.State.DISCONNECTED ||
                cm.getNetworkInfo(1).getState() == NetworkInfo.State.DISCONNECTING) {
            Toast.makeText(getApplicationContext(), "Internet is not connected",Toast.LENGTH_SHORT).show();

        }
        webview=(WebView)findViewById(R.id.webView);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setWebViewClient(new MyWebViewClient());

        final Context myApp = this;

/* Set web client to allow javascript messages */
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
    /** Opens the URL in a browser */
    private void openURL() {

        webview.loadUrl("http://192.168.43.17");
        webview.requestFocus();
    }



}
