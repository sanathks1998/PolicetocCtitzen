package com.example.policetocctitzen;



import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.annotation.Nullable;

public class MainActivity extends Activity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        final String user=(String) b.get("user");
        final String pass=(String) b.get("pass");

        Button ms=(Button) findViewById(R.id.ms);
        Button we=(Button) findViewById(R.id.we);
        Button logout=(Button) findViewById(R.id.logout);
        Button viewsubs=(Button) findViewById(R.id.viewsub);

        Button addsubs=(Button) findViewById(R.id.addsub);
        Button  numhr=(Button) findViewById(R.id.numhrs);

        numhr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://mchat-b2462.firebaseio.com/users.json";
                final ProgressDialog pd = new ProgressDialog(MainActivity.this);
                pd.setMessage("Loading...");
                pd.show();

                StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){
                    @Override
                    public void onResponse(String s) {
                        if(s.equals("null")){
                            Toast.makeText(MainActivity.this, "user not found", Toast.LENGTH_LONG).show();
                        }
                        else{
                            try {
                                JSONObject obj = new JSONObject(s);

                                if(!obj.has(user)){
                                    Toast.makeText(MainActivity.this, "user not found", Toast.LENGTH_LONG).show();
                                }
                                else if(obj.getJSONObject(user).getString("password").equals(pass)){
                                    UserDetails.username = user;
                                    UserDetails.password = pass;
                                    startActivity(new Intent(MainActivity.this, Users.class));
                                }
                                else {
                                    Toast.makeText(MainActivity.this, "incorrect password", Toast.LENGTH_LONG).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        pd.dismiss();
                    }
                },new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        System.out.println("" + volleyError);
                        pd.dismiss();
                    }
                });

                RequestQueue rQueue = Volley.newRequestQueue(MainActivity.this);
                rQueue.add(request);


            }
        });
        addsubs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,
                        WomenRegister.class);
                startActivity(i);

            }
        });
        ms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,
                        MobileRegister.class);
                startActivity(i);

            }
        });
        we.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,
                        SendSms2.class);
                startActivity(i);

            }
        });

        viewsubs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,
                        FileComplaints.class);
                startActivity(i);

            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,
                        Login.class);
                startActivity(i);
            }
        });



    }
    public void policetocCitizen(View view){
        Intent browserIntent=new Intent(Intent.ACTION_VIEW, Uri.parse("http://indianhelpline.com"));
        startActivity(browserIntent);
    }}