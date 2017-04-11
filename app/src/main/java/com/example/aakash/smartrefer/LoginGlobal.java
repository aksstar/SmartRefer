package com.example.aakash.smartrefer;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

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


public class LoginGlobal extends AppCompatActivity {

    private EditText editTextUserName;
    private EditText editTextPassword;
    public Button registerbut;
    public static final String USER_NAME = "USERNAME";
    TextView txtanim;
    String username;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //login1 = (Button)findViewById(R.id.btnLogin);
        editTextUserName = (EditText) findViewById(R.id.etEmail);
        editTextPassword = (EditText) findViewById(R.id.etPass);
        animationhere();

       /* txtanim = (TextView)findViewById(R.id.animtext);
        animationhere();
        txtanim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animationhere();
            }
        });
       */ registerbut = (Button) findViewById(R.id.btnReg);
        registerbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginGlobal.this,RegisterGlobal.class));
            }
        });

    }

    public void animationhere(){
        YoYo.with(Techniques.Tada)
                .duration(999)
                .repeat(10)
                .playOn(findViewById(R.id.projname));
        YoYo.with(Techniques.ZoomIn)
                .duration(999)
                .repeat(3)
                .playOn(findViewById(R.id.etEmail));
        YoYo.with(Techniques.RubberBand)
                .duration(999)
                .repeat(3)
                .playOn(findViewById(R.id.etPass));
        YoYo.with(Techniques.Swing)
                .duration(999)
                .repeat(3)
                .playOn(findViewById(R.id.btnLogin));
        YoYo.with(Techniques.Shake)
                .duration(999)
                .repeat(3)
                .playOn(findViewById(R.id.btnReg));
    }


    public void invokeLogin(View view){
        username = editTextUserName.getText().toString();
        password = editTextPassword.getText().toString();

        login(username,password);

    }

    private void login(final String username, String password) {

        class LoginAsync extends AsyncTask<String, Void, String>{

            private Dialog loadingDialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loadingDialog = ProgressDialog.show(LoginGlobal.this, "Please wait", "Loading...");
            }

            @Override
            protected String doInBackground(String... params) {
                String uname = params[0];
                String pass = params[1];
                Log.i("imp","here we are working");
                InputStream is = null;
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("username", uname));
                nameValuePairs.add(new BasicNameValuePair("password", pass));
                String result = null;

                try{
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://aksstar.16mb.com/login.php");
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
                    if (s.equalsIgnoreCase("success")) {
                        Intent intent = new Intent(LoginGlobal.this, CardDemoActivity.class);
                        intent.putExtra(USER_NAME, username);
                        finish();
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid User Name or Password", Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
        LoginAsync la = new LoginAsync();
        la.execute(username, password);

    }

}