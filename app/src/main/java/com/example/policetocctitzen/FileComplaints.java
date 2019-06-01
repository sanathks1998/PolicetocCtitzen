package com.example.policetocctitzen;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;

import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;

public class FileComplaints extends AppCompatActivity {
    EditText comp;
    Button btnShowLocation;
    private static final int REQUEST_CODE_PERMISSION = 2;
    String mPermission = Manifest.permission.ACCESS_FINE_LOCATION;
    Firebase reference1, reference2;
    Button submis;

    // GPSTracker class
    GPSTracker gps;
    String gpse,gpsl;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filecomplaint);
        submis = (Button) findViewById(R.id.submitcom);
        comp = (EditText) findViewById(R.id.comp);
        btnShowLocation = (Button) findViewById(R.id.button);
        Firebase.setAndroidContext(this);
        reference1 = new Firebase("https://mchat-b2462.firebaseio.com/filecomplaint/" + UserDetails.username + "_" + UserDetails.chatWith);
        reference2 = new Firebase("https://mchat-b2462.firebaseio.com/filecomplaint/" + UserDetails.chatWith + "_" + UserDetails.username);

        // show location button click event
        btnShowLocation.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // create class object
                gps = new GPSTracker(FileComplaints.this);

                // check if GPS enabled
                if (gps.canGetLocation()) {

                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();
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
                    map.put("user", UserDetails.username);
                    map.put("latitude", gpsl);
                    map.put("longitude", gpse);
                    reference1.push().setValue(map);
                    reference2.push().setValue(map);
                    comp.setText("");
                }
            }
        });

    }
}
