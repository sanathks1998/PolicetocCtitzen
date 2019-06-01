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

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class FindMobile extends AppCompatActivity {
    EditText find1;
    TextView f1,f2,f3;
    Button searchf;
    Firebase reference1;
    String f1s,f2s,f3s,find1s;
    GPSTracker gps;
    String gpse,gpsl;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mobilefind);
        Firebase.setAndroidContext(this);
        reference1 = new Firebase("https://mchat-b2462.firebaseio.com/mobileregister/" + UserDetails.chatWith + "_" + UserDetails.username);
        find1=(EditText)findViewById(R.id.find1);
        searchf=(Button)findViewById(R.id.searchf);

        f2=(TextView)findViewById(R.id.f2);
        f3=(TextView)findViewById(R.id.f3);
        searchf.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                find1s=find1.getText().toString();
                reference1.orderByChild("mobilenum").equalTo(find1s).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                            f2s= dataSnapshot1.child("imei").getValue().toString();
                            f2.setText(f2s);
                            f3s= dataSnapshot1.child("mobilenum").getValue().toString();
                            f3.setText(f3s);

                        }
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });
            }

        });

    }
}
