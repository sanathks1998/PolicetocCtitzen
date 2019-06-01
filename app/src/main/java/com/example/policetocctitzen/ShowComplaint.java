package com.example.policetocctitzen;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;

public class ShowComplaint extends AppCompatActivity {
    TextView show,users,longs,lats;
    Firebase reference1,reference2;
    Button node;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_complaint);
        show=(TextView)findViewById(R.id.show);
        users=(TextView)findViewById(R.id.users);
        lats=(TextView)findViewById(R.id.lats);
        longs=(TextView)findViewById(R.id.longs);
        node=(Button)findViewById(R.id.node);
        Firebase.setAndroidContext(this);
        reference1 = new Firebase("https://mchat-b2462.firebaseio.com/filecomplaint/" + UserDetails.username + "_" + UserDetails.chatWith);
        node.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


        reference1.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Map map = dataSnapshot.getValue(Map.class);
                String complainter = map.get("filecomplaints").toString();
                String userName = map.get("user").toString();
                String longitude = map.get("longitude").toString();
                String latitude = map.get("latitude").toString();
                show.setText(complainter);
                lats.setText(latitude);
                longs.setText(longitude);
                users.setText(userName);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }

        });
            }
        });
            }
}