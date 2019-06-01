package com.example.policetocctitzen;

import android.app.ActionBar;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Links extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.links);
    }
    public void policetocCitizen(View view){
        Intent browserIntent=new Intent(Intent.ACTION_VIEW, Uri.parse("http://indianhelpline.com"));
        startActivity(browserIntent);
    }
}
