package com.example.policetocctitzen;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Scanner;

import androidx.annotation.Nullable;

public class MainActivityPolice extends Activity {
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_police);
        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        final String user=(String) b.get("user");
        final String pass=(String) b.get("pass");
        Button mf=(Button) findViewById(R.id.mf);
        Button findpeop=(Button) findViewById(R.id.findpeoples);
        Button addpeop=(Button) findViewById(R.id.addpeoples);
        Button showcomp=(Button) findViewById(R.id.showcomp);
        Button logout=(Button) findViewById(R.id.logout);
        Button codegen=(Button) findViewById(R.id.codegenerate);
        Button  numhr=(Button) findViewById(R.id.numhrs);
        Button  readcode=(Button) findViewById(R.id.readcode);
        Button  sms=(Button) findViewById(R.id.sms);
        numhr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://mchat-b2462.firebaseio.com/users.json";
                final ProgressDialog pd = new ProgressDialog(MainActivityPolice.this);
                pd.setMessage("Loading...");
                pd.show();

                StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){
                    @Override
                    public void onResponse(String s) {
                        if(s.equals("null")){
                            Toast.makeText(MainActivityPolice.this, "user not found", Toast.LENGTH_LONG).show();
                        }
                        else{
                            try {
                                JSONObject obj = new JSONObject(s);

                                if(!obj.has(user)){
                                    Toast.makeText(MainActivityPolice.this, "user not found", Toast.LENGTH_LONG).show();
                                }
                                else if(obj.getJSONObject(user).getString("password").equals(pass)){
                                    UserDetails.username = user;
                                    UserDetails.password = pass;
                                    startActivity(new Intent(MainActivityPolice.this, Users.class));
                                }
                                else {
                                    Toast.makeText(MainActivityPolice.this, "incorrect password", Toast.LENGTH_LONG).show();
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

                RequestQueue rQueue = Volley.newRequestQueue(MainActivityPolice.this);
                rQueue.add(request);


            }
        });

        codegen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivityPolice.this,
                        QrCodeGen.class);
                startActivity(i);
            }
        });
        sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivityPolice.this,
                        SendSms.class);
                startActivity(i);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivityPolice.this,
                        Login.class);
                startActivity(i);
            }
        });

        readcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivityPolice.this,
                        Scanners.class);
                startActivity(i);
            }
        });
        showcomp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivityPolice.this,
                        ShowComplaint.class);
                startActivity(i);
            }
        });
        addpeop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivityPolice.this,
                        AddPeople.class);
                startActivity(i);
            }
        });
        findpeop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivityPolice.this,
                        FindPeople.class);
                startActivity(i);
            }
        });
        mf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivityPolice.this,
                        FindMobile.class);
                startActivity(i);
            }
        });
    }}
