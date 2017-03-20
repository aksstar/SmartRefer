package com.example.aakash.smartrefer;

/**
 * Created by Home on 3/10/2017.
 */


import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Recipie extends AppCompatActivity {

    private WebView webView;
    public String url;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipie);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if(extras != null)
             url = extras.getString("url"); // retrieve the data using keyName
        webView = (WebView) findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
        //actionBar.show();
    }

}