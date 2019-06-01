package com.example.policetocctitzen;

import android.Manifest;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;


public class FileComplaint extends AppCompatActivity {

    EditText comp;
    Button btnShowLocation;
    private static final int REQUEST_CODE_PERMISSION = 2;
    String mPermission = Manifest.permission.ACCESS_FINE_LOCATION;
    Firebase reference1,reference2;
    Button submis;
    String gpse,gpsl;
    double latitude,longitude;
    // GPSTracker class
    GPSTracker gps;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filecomplaint);
        submis = (Button) findViewById(R.id.submitcom);
        comp = (EditText) findViewById(R.id.comp);
        btnShowLocation = (Button) findViewById(R.id.button);
        reference1 = new Firebase("https://mchat-b2462.firebaseio.com/filecomplaint/" + UserDetails.username + "_" + UserDetails.chatWith);
        reference2 = new Firebase("https://mchat-b2462.firebaseio.com/filecomplaint/" + UserDetails.chatWith + "_" + UserDetails.username);

        // show location button click event
        btnShowLocation.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // create class object
                gps = new GPSTracker(FileComplaint.this);

                // check if GPS enabled
                if (gps.canGetLocation()) {

                     latitude = gps.getLatitude();
                    longitude = gps.getLongitude();
                    gpsl=latitude+"";
                    gpse=longitude+"";
                    // \n is for new line
                    Toast.makeText(getApplicationContext(), "Your Location is - \nLat: "
                            + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();


                } else {
                    // can't get location
                    // GPS or Network is not enabled
                    // Ask user to enable GPS/network in settings
                    gps.showSettingsAlert();
                }

            }
        });
        submis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comps = comp.getText().toString();
                if (!comps.equals("")) {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("filecomplaints", comps);
                    map.put("latitude", gpsl);
                    map.put("longitude", gpse);
                    map.put("user", UserDetails.username);
                    reference1.push().setValue(map);
                    reference2.push().setValue(map);
                    comp.setText("");
                }
            }
        });

    }}