package com.example.policetocctitzen;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class WomenEmergency extends AppCompatActivity {
    Button wemergency,walert,wcall,wharss;
    String msg;

    Firebase reference1;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.womenemergency);
        wemergency=(Button)findViewById(R.id.wemergency);



        walert=(Button)findViewById(R.id.walert);
        Firebase.setAndroidContext(this);
        reference1 = new Firebase("https://mchat-b2462.firebaseio.com/womenregister/" + UserDetails.chatWith + "_" + UserDetails.username);

       /* wharss=(Button)findViewById(R.id.wharassment);
            wemergency.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            msg="Medical Emergency";
            reference1.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    Map map = dataSnapshot.getValue(Map.class);

                    String userName = map.get("user").toString();
                    String em1 = map.get("enumber1").toString();
                    String em2 = map.get("enumber2").toString();
                  Toast.makeText(getApplicationContext(),em1,Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(getApplicationContext(),WomenEmergency.class);
                    PendingIntent pi=PendingIntent.getActivity(getApplicationContext(), 0, intent,0);

                    //Get the SmsManager instance and call the sendTextMessage method to send message
                    SmsManager sms=SmsManager.getDefault();
                    sms.sendTextMessage(em1, null, msg, pi,null);
                    sms.sendTextMessage(em2, null, msg, pi,null);


             SmsManager smsr=SmsManager.getDefault();
                    smsr.sendTextMessage(em2, null, msg, pi,null);
                    Toast.makeText(getApplicationContext(), "Message Sent successfully!",
                            Toast.LENGTH_LONG).show();
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
});*/
        walert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                msg="Security Emergency";
                reference1.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        Map map = dataSnapshot.getValue(Map.class);

                        String userName = map.get("user").toString();
                        String em1 = map.get("enumber1").toString();
                        String em2 = map.get("enumber2").toString();
                        Intent intent=new Intent(getApplicationContext(),SendSms.class);
                        PendingIntent pi=PendingIntent.getActivity(getApplicationContext(), 0, intent,0);

                        //Get the SmsManager instance and call the sendTextMessage method to send message
                        SmsManager sms=SmsManager.getDefault();
                        sms.sendTextMessage(em1, null, msg, pi,null);


                        SmsManager smsr=SmsManager.getDefault();
                        smsr.sendTextMessage(em2, null, msg, pi,null);
                        Toast.makeText(getApplicationContext(), "Message Sent successfully!",
                                Toast.LENGTH_LONG).show();
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
      /*  wharss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                msg="Harassment";
                reference1.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        Map map = dataSnapshot.getValue(Map.class);

                        String userName = map.get("user").toString();
                        String em1 = map.get("enumber1").toString();
                        String em2 = map.get("enumber2").toString();
                        Intent intent=new Intent(getApplicationContext(),SendSms.class);
                        PendingIntent pi=PendingIntent.getActivity(getApplicationContext(), 0, intent,0);

                        //Get the SmsManager instance and call the sendTextMessage method to send message
                        SmsManager sms=SmsManager.getDefault();
                        sms.sendTextMessage(em1, null, msg, pi,null);


                        SmsManager smsr=SmsManager.getDefault();
                        smsr.sendTextMessage(em2, null, msg, pi,null);
                        Toast.makeText(getApplicationContext(), "Message Sent successfully!",
                                Toast.LENGTH_LONG).show();
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
        });*/
    }
}
