package com.example.aakash.smartrefer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;

import org.json.JSONArray;
import org.json.JSONException;


/**
 * Created by Home on 1/22/2017.
 */

public class dashboardaddd extends AppCompatActivity implements AsyncResponse {
    EditText editText;
    String myJSON;
    JSONArray peoples = null;

    ArrayList<HashMap<String, String>> personList;
    private static final String TAG_RESULTS="result";
    ListView list;
    private static final String TAG_ID = "id";
    private static final String TAG_NAME = "name";
    ListAdapter adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
       // ListView list = (ListView) findViewById(R.id.listView);
        editText=(EditText)findViewById(R.id.txtInput);
        Button btAdd=(Button)findViewById(R.id.btAdd);
        Button delete= (Button) findViewById(R.id.del);
        PostResponseAsyncTask post = new PostResponseAsyncTask(this);
        list = (ListView) findViewById(R.id.listView);
        personList = new ArrayList<HashMap<String,String>>();
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap postData = new HashMap();
                postData.put("msg", editText.getText().toString() );

                PostResponseAsyncTask loginTask = new PostResponseAsyncTask(dashboardaddd.this, postData);
                loginTask.execute("http://aksstar.16mb.com/dashboard.php");
            }

        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
            }
        });

    }

    public void getData(){
        class GetDataJSON extends AsyncTask<String, Void, String>{

            @Override
            protected String doInBackground(String... params) {
                DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
                HttpPost httppost = new HttpPost("http://aksstar.16mb.com/listview.php");

                // Depends on your web service
                httppost.setHeader("Content-type", "application/json");

                InputStream inputStream = null;
                String result = null;
                try {
                    HttpResponse response = httpclient.execute(httppost);
                    HttpEntity entity = response.getEntity();

                    inputStream = entity.getContent();
                    // json is UTF-8 by default
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
                    StringBuilder sb = new StringBuilder();

                    String line = null;
                    while ((line = reader.readLine()) != null)
                    {
                        sb.append(line + "\n");
                    }
                    result = sb.toString();
                } catch (Exception e) {
                    // Oops
                }
                finally {
                    try{if(inputStream != null)inputStream.close();}catch(Exception squish){}
                }
                return result;
            }

            protected void showList(){
                try {
                    JSONObject jsonObj = new JSONObject(myJSON);
                    peoples = jsonObj.getJSONArray(TAG_RESULTS);
                    if(!personList.isEmpty())
                    {
                        personList.clear();
                    }
                    for(int i=0;i<peoples.length();i++){
                        JSONObject c = peoples.getJSONObject(i);
                        String id = c.getString(TAG_ID);
                      //  String name = c.getString(TAG_NAME);
                       // String address = c.getString(TAG_ADD);

                        HashMap<String,String> persons = new HashMap<String,String>();
                        persons.put(TAG_ID,id);
                      //  persons.put(TAG_NAME,name);
                   //     persons.put(TAG_ADD,address);

                        personList.add(persons);
                    }
                     adapter = new SimpleAdapter(
                            dashboardaddd.this, personList, R.layout.singlerow,
                            new String[]{TAG_ID/*,TAG_NAME*/},
                            new int[]{R.id.id/*, R.id.name*/}
                    );

                    list.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            protected void onPostExecute(String result){
                myJSON=result;
                showList();
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute();
    }


    @Override
    public void processFinish(String output) {
        if(output.equals("success")){
            Toast.makeText(this, "Login Successfully",
                    Toast.LENGTH_LONG).show();
        }
    }
}

