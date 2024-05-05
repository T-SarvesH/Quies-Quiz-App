package com.example.mini_proj;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class RecieveQuiz extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recieve_quiz);
        Button b = findViewById(R.id.button6);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scan();
            }
        });
    }


    void scan()
    {
        ScanOptions scanopt = new ScanOptions();
        scanopt.setBeepEnabled(true);
        scanopt.setPrompt("scan");
        scanopt.setCameraId(0);
        scanopt.setDesiredBarcodeFormats(ScanOptions.QR_CODE);
        scanopt.setBarcodeImageEnabled(true);
        scanopt.setOrientationLocked(true);
        scanopt.setCaptureActivity(ScannerActivity.class);
        launcher.launch(scanopt);
        Log.d("ques", "scannd");
    }

    private final ActivityResultLauncher<ScanOptions> launcher = registerForActivityResult(new ScanContract(),
            result -> {
                String c;

                    c = result.getContents();
                    if (c == null) {
                        Toast.makeText(RecieveQuiz.this, "Cancelled", Toast.LENGTH_LONG).show();
                    } else {
                        String p=c;
                        parse(p);
                        Toast.makeText(RecieveQuiz.this, "Scanned: " + c, Toast.LENGTH_LONG).show();

                        Log.d("ques", c);

                    }

            });
    void parse(String z)
    {
        QuestionAnswer.correctAnswers.clear();
        QuestionAnswer.question.clear();
        QuestionAnswer.choices.clear();
        try {
            Log.d("ques", "jo" + z);
            byte[] output = z.getBytes(StandardCharsets.UTF_8);
            // Decompress the bytes
            String[] decompose = z.split("&&&");
            String[] questionsp = decompose[0].split("---");
            String[] choices = decompose[1].split("---");
            Log.d("ques", "ro1");
            String b = "";
            for (String n : questionsp) {
                b = n;
                if (n.charAt(n.length() - 1) == '\n') b = n.substring(0, n.length() - 1);
                if (n.charAt(0) == '\n') b = b.substring(1);
                QuestionAnswer.question.add(b);

            }
            Log.d("ques", "ro2");
            for (String n : choices) {
                b = n;
                if (n.charAt(n.length() - 1) == '\n') b = n.substring(0, n.length() - 1);
                if (n.charAt(0) == '\n') b = b.substring(1);
                ArrayList<String> choi = new ArrayList<>();
                String[] chchs = b.split("\n");
                choi.addAll(Arrays.asList(chchs));
                QuestionAnswer.choices.add(choi);

            }
            Log.d("ques", "ro3");
            Log.d("ques", Arrays.toString(decompose) +"mm1 ");
            b = decompose[2];
            Log.d("ques", b+"mmx ");
            if (decompose[2].charAt(decompose[2].length() - 1) == '\n')
                b = decompose[2].substring(0, decompose[2].length() - 1);
            Log.d("ques", b+" 1");
            if (decompose[2].charAt(0) == '\n') b = b.substring(1);Log.d("ques", b+" 1");
            String[] corr = b.split("\n");
            for (String n : corr) {
                QuestionAnswer.correctAnswers.add(Integer.parseInt(n));
            }
            Log.d("ques", "ro4");
        }catch (Exception e)
        {
            Log.d("ques", e.getMessage());
        }
        Intent i = new Intent(RecieveQuiz.this, QuizJoinerActivity.class);
        startActivity(i);
    }
}