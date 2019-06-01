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

public class AddPeople extends AppCompatActivity {

    Button sub;
    EditText avnum,avehicle,aname,aphnum;
    Firebase reference1;
    String avnum1,avehicle1,aname1,aphnum1;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_people);
        sub=(Button)findViewById(R.id.Submit);
        avnum=(EditText)findViewById(R.id.anum);
        aname=(EditText)findViewById(R.id.aname);
        aphnum=(EditText)findViewById(R.id.aphnum);
        avehicle=(EditText)findViewById(R.id.avehicle);
        Firebase.setAndroidContext(this);
        reference1 = new Firebase("https://mchat-b2462.firebaseio.com/people/" + UserDetails.chatWith + "_" + UserDetails.username);


        sub.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                avehicle1 = avehicle.getText().toString();
                aname1 = aname.getText().toString();
                aphnum1=aphnum.getText().toString();
                avnum1 = avnum.getText().toString();
                if (!avnum1.equals("")&&!avehicle1.equals("")&&!aname1.equals("")&&!aphnum1.equals("")) {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("name", aname1);
                    map.put("vehicle", avehicle1);
                    map.put("vnumber", avnum1);
                    map.put("phone", aphnum1);
                    reference1.push().setValue(map);

                    aname.setText("");
                    avehicle.setText("");
                    aphnum.setText("");
                    avnum.setText("");

                }
            }
        });
    }
}
