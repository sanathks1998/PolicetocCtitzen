package com.example.policetocctitzen;
import android.os.Bundle;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;


public class SendSms2 extends Activity {


    GPSTracker gps;
    String gpse,gpsl;
String msg,em1s,em2s;
    Firebase reference1;
    Button wemergecy,walert,hrss,ma,sec;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.womenemergency);
        Firebase.setAndroidContext(this);
        reference1 = new Firebase("https://mchat-b2462.firebaseio.com/womenregister/" + UserDetails.username + "_" + UserDetails.chatWith);

        wemergecy=(Button)findViewById(R.id.wemergency);
        walert=(Button)findViewById(R.id.walert);
        hrss=(Button)findViewById(R.id.hrss);
        ma=(Button)findViewById(R.id.ma);
        sec=(Button)findViewById(R.id.sec);
        hrss.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                msg="Harrasment";



            }
        });
        sec.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                msg="Security ALert";
                //ty.setText(msg);
            }
        });
        ma.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                msg="Medical Alert";

            }
        });
        wemergecy.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                gps = new GPSTracker(SendSms2.this);

                // check if GPS enabled
                if (gps.canGetLocation()) {

                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();
                    gpsl=latitude+"";
                    gpse=longitude+"";

                    // \n is for new line
                    Toast.makeText(getApplicationContext(), "Your Location is - \nLat: "
                            + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();

                }
            }
        });

        //Performing action on button click
        walert.setOnClickListener(new OnClickListener() {


            @Override
            public void onClick(View arg0) {

                reference1.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        Map map = dataSnapshot.getValue(Map.class);
                        String ri=msg+" latitude:"+gpsl+" longitude:"+gpse;
                        String em1s = map.get("enumbera").toString();
                        String userName = map.get("user").toString();
                        String em2s = map.get("enumberb").toString();

                        Intent intent=new Intent(getApplicationContext(),SendSms2.class);
                        PendingIntent pi=PendingIntent.getActivity(getApplicationContext(), 0, intent,0);

                        SmsManager sms=SmsManager.getDefault();
                        sms.sendTextMessage(em1s, null, ri, pi,null);
                        sms.sendTextMessage(em2s, null, ri, pi,null);
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


                //Getting intent and PendingIntent instance

            }
        });
    }




}