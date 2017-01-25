/*
package com.example.aakash.smartrefer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import static android.text.TextUtils.isEmpty;
*/
/*
public class Dashboard extends AppCompatActivity {

    ArrayAdapter<String> adapter;
    EditText editText;
    ArrayList<String> itemList;
    private DbHelper db;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        String[] items={};
        db = new DbHelper(this);
        itemList=new ArrayList<String>(Arrays.asList(items));
        adapter= new ArrayAdapter<String>(this, R.layout.singlerow, R.id.txtview,itemList);
        ListView listV=(ListView)findViewById(R.id.list);
        listV.setAdapter(adapter);
        editText=(EditText)findViewById(R.id.txtInput);
        Button btAdd=(Button)findViewById(R.id.btAdd);
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String newItem=editText.getText().toString();
                itemList.add(newItem);
                adapter.notifyDataSetChanged();
              //  db.addmessage(newItem);
               // if(db.dashemp())
                {
                    adapter.notifyDataSetChanged();
                }

            }

        });
        Button delete= (Button) findViewById(R.id.del);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newItem=Integer.parseInt(editText.getText().toString());
               // String inttem = newItem.toString();
                if(isEmpty(editText.getText().toString()))
                {
                    Toast.makeText(Dashboard.this, "Please enter row no. eg:1,2,3",
                            Toast.LENGTH_LONG).show ();
                }
                else {
                    itemList.remove(newItem);
                }
                adapter.notifyDataSetChanged();
            }

        });

    }

}
*/
