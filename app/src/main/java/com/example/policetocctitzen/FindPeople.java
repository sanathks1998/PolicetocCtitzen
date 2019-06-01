package com.example.policetocctitzen;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Map;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class FindPeople extends AppCompatActivity
{Button find;
    TextView vvehicle,vname,vphnum;
    EditText vnum;
    Firebase reference1;
    String vnum1,vvehicle1,vname1,vphnum1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_people);
find=(Button)findViewById(R.id.searches);
vnum=(EditText)findViewById(R.id.vnum);
        vvehicle=(TextView) findViewById(R.id.vvehcile);
        vname=(TextView)findViewById(R.id.vname);
        vphnum=(TextView) findViewById(R.id.vphnum);
        Firebase.setAndroidContext(this);
        reference1 = new Firebase("https://mchat-b2462.firebaseio.com/people/" + UserDetails.chatWith + "_" + UserDetails.username);

        find.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           vnum1=vnum.getText().toString();
        reference1.orderByChild("vnumber").equalTo(vnum1).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                 Map map = dataSnapshot.getValue(Map.class);
                    vname1= dataSnapshot1.child("name").getValue().toString();
                   vvehicle1=dataSnapshot1.child("vehicle").getValue().toString();
                    vphnum1=dataSnapshot1.child("phone").getValue().toString();
                   vphnum.setText(vphnum1);
                vvehicle.setText(vvehicle1);
                        vname.setText(vname1);
            }}

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
       }
   });
    }
}
