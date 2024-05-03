package com.example.mini_proj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.ArrayList;

public class QrDisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_display);

        try {
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.encodeBitmap(parse(),BarcodeFormat.QR_CODE, 700, 700);
            ImageView imageViewQRcode = (ImageView) findViewById(R.id.QR);
            imageViewQRcode.setImageBitmap(bitmap);
            //Log.d("ques",bun.getString("data", ""));

        }catch (Exception ignored){

        }
    }

    String parse()
    {
        StringBuilder stringbing = new StringBuilder();
        for (String s: QuestionAnswer.question)
        {
            stringbing.append(s);
            stringbing.append("?-");

        }

        stringbing.append("&&&");
        for (ArrayList<String> arr: QuestionAnswer.choices)
        {
            for (String s1: arr)
            {
                stringbing.append(s1);
                stringbing.append("\n");
            }
        }

        for (int s1: QuestionAnswer.correctAnswers)
        {
            stringbing.append(s1);
            stringbing.append("\n");
        }

        return stringbing.toString();

//        String quests=Qs.getText().toString();
//        String choice=choices.getText().toString();
//        String corret=correct.getText().toString();
//        String finall = quests+"&&&"+choice+"&&&"+corret;
//        Intent i = new Intent(Editor.this, Qrgen.class);
//        i.putExtra("data", finall);
//        startActivity(i);
    }

}