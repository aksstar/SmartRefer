package com.example.aakash.smartrefer;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DoThings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do_things);
        Button x = (Button) findViewById(R.id.button);
        x.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Scanactivty.class);
                startActivity(intent);
            }
        });
        Button y = (Button) findViewById(R.id.button6);
        y.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), dashboardaddd.class);
                startActivity(intent);
            }
        });
        Button z = (Button) findViewById(R.id.button7);
        z.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String url = "http://www.karmayog.org/nonprofits/npogriddisplay.asp?r=476";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        Button zx = (Button) findViewById(R.id.button5);
        zx.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), addthings.class);
                startActivity(intent);
            }
        });
        Button zxx = (Button) findViewById(R.id.button3);
        zxx.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), shoppinglist.class);
                startActivity(intent);
            }
        });
        Button zxxx = (Button) findViewById(R.id.button8);
        zxxx.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/results?search_query=healthy+recipes+for+kids")));
            }
        });
        Button zxxxx = (Button) findViewById(R.id.button2);
        zxxxx.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String url = "http://projectrefrigerator.pe.hu/rindex.php";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        Button zxxxxx = (Button) findViewById(R.id.button10);
        zxxxxx.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String url = "http://www.biogas-india.com/contact.php";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        Button zxxxxxx = (Button) findViewById(R.id.button11);
        zxxxxxx.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CardDemoActivity.class);
                startActivity(intent);
            }
        });
    }
}
