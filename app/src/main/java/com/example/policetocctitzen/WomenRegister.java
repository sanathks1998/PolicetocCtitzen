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

public class WomenRegister extends AppCompatActivity
{
    Button submits;
    Firebase reference2;
    EditText emer1, emer2;
    String emr1s,emr2s;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.womensafety);
        submits=(Button)findViewById(R.id.submits);
        emer1=(EditText)findViewById(R.id.emer1);
        emer2=(EditText)findViewById(R.id.emer2);
        reference2 = new Firebase("https://mchat-b2462.firebaseio.com/womenregister/" + UserDetails.chatWith + "_" + UserDetails.username);
        submits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emr1s = emer1.getText().toString();
                emr2s = emer2.getText().toString();
                if (!emr1s.equals("")) {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("enumbera", emr1s);
                    map.put("user", UserDetails.username);
                    map.put("enumberb", emr2s);
                    reference2.push().setValue(map);
                    emer1.setText("");
                    emer2.setText("");
                }
            }
        });

    }
}
