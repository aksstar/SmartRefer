package com.example.aakash.smartrefer;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class ragForm extends AppCompatActivity {
    private EditText rname;
    private EditText rarea;
    private EditText rno;
    private EditText rotherInfo;
    String rsname;
    String rsarea;
    String rsno;
    Button ragSubmit;
    String rsotherinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rag_form);
        rname = (EditText) findViewById(R.id.Rname);
        rarea = (EditText) findViewById(R.id.Rarea);
        rno = (EditText) findViewById(R.id.Rno);
        rotherInfo = (EditText) findViewById(R.id.RotherInfo);
    }
    public void invokeLogin(View view){
        rsname = rname.getText().toString();
        rsarea = rarea.getText().toString();
        rsno = rno.getText().toString();
        rsotherinfo = rotherInfo.getText().toString();

        login(rsname,rsarea,rsno,rsotherinfo);

    }

    private void login(final String rsname, String rsarea,String rsno,String rsotherinfo) {

        class LoginAsync extends AsyncTask<String, Void, String> {

            private Dialog loadingDialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loadingDialog = ProgressDialog.show(ragForm.this, "Please wait", "Loading...");
            }

            @Override
            protected String doInBackground(String... params) {
                String rsname = params[0];
                String rsarea = params[1];
                String rsno = params[2];
                String rsotherinfo = params[3];
                Log.i("imp","here we are working");
                InputStream is = null;
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("RagPickerName", rsname));
                nameValuePairs.add(new BasicNameValuePair("RagArea", rsarea));
                nameValuePairs.add(new BasicNameValuePair("RagMobileNo", rsno));
                nameValuePairs.add(new BasicNameValuePair("RagOtherInformation", rsotherinfo));
                String result = null;

                try{
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://projectrefrigerator.pe.hu/insertIntoRag.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    HttpResponse response = httpClient.execute(httpPost);

                    HttpEntity entity = response.getEntity();

                    is = entity.getContent();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);
                    StringBuilder sb = new StringBuilder();

                    String line = null;
                    while ((line = reader.readLine()) != null)
                    {
                        sb.append(line + "\n");
                    }
                    result = sb.toString();
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return result;
            }

            @Override
            protected void onPostExecute(String result){
                if(result==null)
                {
                    Toast.makeText(getApplicationContext(), "RESULT IS NULL", Toast.LENGTH_LONG).show();
                }
                else {
                    String s = result.trim();
                    loadingDialog.dismiss();
                    Log.i("notimp",s);
                    if (s.equalsIgnoreCase("success")) {
                        Toast.makeText(getApplicationContext(), "Register Successfull", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Please Try again Later", Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
        LoginAsync la = new LoginAsync();
        la.execute(rsname, rsarea,rsno,rsotherinfo);

    }


}
