package com.example.aakash.smartrefer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

/**
 * Created by Anurag on 15-03-2017.
 */





public class shop extends Activity {

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

    }

}

