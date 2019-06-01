package com.example.policetocctitzen;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.appcompat.app.AppCompatActivity;

public class Scanners extends AppCompatActivity implements View.OnClickListener {

    private Button btnScanQRCode;
    private TextView txtFruit, txtSize, txtColor;

    //QR Code Scanner Object
    private IntentIntegrator qrScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        btnScanQRCode = (Button) findViewById(R.id.btnScanQRCode);
        txtFruit = (TextView) findViewById(R.id.txtFruitName);
        txtSize = (TextView) findViewById(R.id.txtFruitSize);
        txtColor = (TextView) findViewById(R.id.txtFruitColor);

        //Initialize the Scan Object
        qrScan = new IntentIntegrator(this);

        //OnClickListener Attached to the Button
        btnScanQRCode.setOnClickListener(this);
    }

    //Getting the scan results
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            //Check to see if QR Code has nothing in it
            if (result.getContents() == null) {
                Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();
            } else {
                //QR Code contains some data
                try {
                    //Convert the QR Code Data to JSON
                    JSONObject obj = new JSONObject(result.getContents());
                    //Set up the TextView Values using the data from JSON
                    txtFruit.setText(obj.getString("number"));
                    txtSize.setText(obj.getString("type"));
                    txtColor.setText(obj.getString("fine"));
                } catch (JSONException e) {
                    e.printStackTrace();
                    //In case of exception, display whatever data is available on the QR Code
                    //This can be caused due to the format MisMatch of the JSON
                    Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onClick(View view) {

        //initiating the qr code scan
        qrScan.initiateScan();

    }
}

