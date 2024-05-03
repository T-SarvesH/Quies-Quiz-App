package com.example.mini_proj;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class QrDisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_display);
        Bundle bun = getIntent().getExtras();
        assert bun != null;

        try {
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.encodeBitmap(bun.getString("data", ""), BarcodeFormat.QR_CODE, 700, 700);
            ImageView imageViewQRcode = (ImageView) findViewById(R.id.QR);
            imageViewQRcode.setImageBitmap(bitmap);
            Log.d("ques",bun.getString("data", ""));

        }catch (Exception ignored){

        }
    }
}