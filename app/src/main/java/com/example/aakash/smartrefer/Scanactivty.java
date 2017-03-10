package com.example.aakash.smartrefer;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

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
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Scanactivty extends AppCompatActivity implements View.OnClickListener {
    private Button scanBtn;
    private TextView formatTxt, contentTxt;
    private String items[] = {null,null,null};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canactivty);
        scanBtn = (Button)findViewById(R.id.scan_button);
        formatTxt = (TextView)findViewById(R.id.scan_format);
        contentTxt = (TextView)findViewById(R.id.scan_content);
        scanBtn.setOnClickListener(this);
    }
    public void onClick(View v) {
        if (v.getId() == R.id.scan_button) {
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();
        }
    }
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            String scanContent = scanningResult.getContents();
            String scanFormat = scanningResult.getFormatName();
            formatTxt.setText("FORMAT: " + scanFormat);
            contentTxt.setText("CONTENT: " + scanContent);
            Log.d("smr2", scanContent);
          //  String dataYouWant = StringUtils.substringBetween(scanContent, "'");
            //Log.d("smr",dataYouWant);
            Pattern pattern = Pattern.compile("'(.*?)'");
            Matcher matcher = pattern.matcher(scanContent);
            int counter=0;
            while (matcher.find())
            {
                if(matcher.group().length()!=0) {

                    Log.d("smr", "onActivityResult: " + matcher.group(1).trim());
                    items[counter++]= matcher.group(1).trim();

                }

                else{
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "No scan data received!", Toast.LENGTH_SHORT);
                    toast.show();
                    }
            }
            login(items[0],items[1],items[2]);
        }
    }
    private void login(final String id, String quantity, String name) {

        class LoginAsync extends AsyncTask<String, Void, String> {

            private Dialog loadingDialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loadingDialog = ProgressDialog.show(Scanactivty.this, "Please wait", "Loading...");
            }

            @Override
            protected String doInBackground(String... params) {
                String id = params[0];
                String qua = params[1];
                String name = params[2];
                Log.i("imp","here we are working");
                InputStream is = null;
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("id", id));
                nameValuePairs.add(new BasicNameValuePair("qua", qua));
                nameValuePairs.add(new BasicNameValuePair("name", name));
                String result = null;

                try{
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://projectrefrigerator.pe.hu/scanIns.php");
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
                        //Intent intent = new Intent(RegisterGlobal.this, LoginGlobal.class);
                        Toast.makeText(getApplicationContext(), "Data inserted Successfully", Toast.LENGTH_LONG).show();
                        //intent.putExtra(USER_NAME, username);
                        //finish();
                       // startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Error while inserting data", Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
        LoginAsync la = new LoginAsync();
        la.execute(id, quantity,name);

    }
}

