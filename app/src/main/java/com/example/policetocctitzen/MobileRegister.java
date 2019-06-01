package com.example.policetocctitzen;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.Firebase;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MobileRegister extends AppCompatActivity {
    Button msubmits;
    Firebase reference2;
    EditText memail,mnum,mimei;
    String memails,mnums,mimeis;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mobileinfo);
        msubmits=(Button)findViewById(R.id.msubmit);
        mnum=(EditText)findViewById(R.id.mnum);
        mimei=(EditText)findViewById(R.id.mimei);
        memail=(EditText)findViewById(R.id.memail);
        reference2 = new Firebase("https://mchat-b2462.firebaseio.com/mobileregister/" + UserDetails.chatWith + "_" + UserDetails.username);
        msubmits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mnums = mnum.getText().toString();
                mimeis = mimei.getText().toString();
                memails = memail.getText().toString();
                if (!mnums.equals("")) {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("mobilenum", mnums);
                    map.put("user", UserDetails.username);
                    map.put("email", memails);
                    map.put("imei", mimeis);
                    reference2.push().setValue(map);
                    mnum.setText("");
                    memail.setText("");
                    mimei.setText("");
                }
            }
        });

    }
}
