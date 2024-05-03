package com.example.mini_proj;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.journeyapps.barcodescanner.CaptureActivity;
public class ScannerActivity extends CaptureActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("ques", "Qr code scanned");
        //setContentView(R.layout.activity_scanner);
    }
}