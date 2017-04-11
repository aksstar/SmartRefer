package com.example.aakash.smartrefer;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class wastein extends AppCompatActivity {
    Button button1,button2,button3,button4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wastein);
        button1 =(Button) findViewById(R.id.button12);
        button2 =(Button) findViewById(R.id.button13);
        button3 =(Button) findViewById(R.id.button14);
        button4 =(Button) findViewById(R.id.button15);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MapsActivity1.class);
                // intent.putExtra("url","http://projectrefrigerator.pe.hu/rindex.php");
                v.getContext().startActivity(intent);

            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Recipie.class);
                intent.putExtra("url","http://projectrefrigerator.pe.hu/windex.php");
                v.getContext().startActivity(intent);

            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ragForm.class);
                v.getContext().startActivity(intent);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), manure_home.class);
                v.getContext().startActivity(intent);

            }
        });
    }

}
