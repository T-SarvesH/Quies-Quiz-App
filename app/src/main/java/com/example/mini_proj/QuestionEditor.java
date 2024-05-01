package com.example.mini_proj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.example.mini_proj.databinding.QuestionBinding;

import java.util.ArrayList;

public class QuestionEditor extends AppCompatActivity {

    QuestionBinding binding;


    boolean edit=false;
    int q=0;
    int y;
    ArrayList<EditText> texts = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = QuestionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.button4.getBackground().setAlpha(64);
        binding.button4.setTextColor(getResources().getColor(R.color.disablefg));
        Bundle c = getIntent().getExtras();
        q= c.getInt("quesno", 0);
        edit = c.getBoolean("editmode", false);
        if (texts.size()<4) {
            texts.add(binding.editTextText);
            texts.add(binding.editTextText2);
            texts.add(binding.editTextText3);
            texts.add(binding.editTextText4);
        }
        //FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        if(c!=null) {
            edit = c.getBoolean("editmode", false);
            q = c.getInt("quesno");
        }
        if (edit)
        {
            //Toast.makeText(getContext(), "edit", Toast.LENGTH_SHORT).show();
            //Toast.makeText(getContext(), QuestionAnswer.question.get(q), Toast.LENGTH_SHORT).show();

            (new Handler()).postDelayed((Runnable)(new Runnable() {
                public void run() {
                    binding.editTextTextMultiLine.setText(QuestionAnswer.question.get(q));
                    y=0;
                    Log.d("pp", String.valueOf(texts.size()));
                    for (int k=0; k<texts.size();k++)
                    {
                        Log.d("pp", String.valueOf(y));
                        texts.get(k).setText(QuestionAnswer.choices.get(q).get(k));
                    }
                    y=0;
                    int crr = QuestionAnswer.correctAnswers.get(q);
                    switch (crr)
                    {
                        case 1:
                            binding.radioGroup.check(R.id.radioButton4);
                            break;
                        case 2:
                            binding.radioGroup.check(R.id.radioButton3);
                            break;
                        case 3:
                            binding.radioGroup.check(R.id.radioButton2);
                            break;
                        case 4:
                            binding.radioGroup.check(R.id.radioButton);
                            break;
                    }
                }
            }), 200);


        }
        binding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                binding.button4.setEnabled(true);binding.button4.getBackground().setAlpha(255);
                binding.button4.setTextColor(getResources().getColor(R.color.textbutt));
            }
        });
        Intent t = new Intent(this, EditorActivity.class);
        binding.button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int u= binding.radioGroup.getCheckedRadioButtonId();int v=0;
                if(u==R.id.radioButton4) v=1;
                else if (u==R.id.radioButton3)  v=2;
                else if (u==R.id.radioButton2) v=3;
                else if (u==R.id.radioButton) v=4;
                if(edit)
                {
                    QuestionAnswer.question.set(q, binding.editTextTextMultiLine.getText().toString());
                    for (EditText e : texts)
                    {
                        QuestionAnswer.choices.get(q).set(y, e.getText().toString());y++;
                    }
                    y=0;

                    QuestionAnswer.correctAnswers.set(q, v);
                    EditorActivity.rcyca.notifyItemChanged(q);
                }else
                {
                    ArrayList<String> charry = new ArrayList<>();
                    QuestionAnswer.question.add(binding.editTextTextMultiLine.getText().toString());
                    for (EditText e : texts)
                    {
                        charry.add(e.getText().toString());
                    }y=0;
                    QuestionAnswer.choices.add(charry);
                    QuestionAnswer.correctAnswers.add(v);
                    EditorActivity.rcyca.notifyItemInserted(QuestionAnswer.question.size());
                }
                edit=false;
                q=0;

                //binding.editTextTextMultiLine.se;



                //if(edInterface!=null)
                // {
                // Toast.makeText(getContext(), "iactive", Toast.LENGTH_SHORT).show();
                //    edInterface.note(q, edit);
                //}else {
                // Toast.makeText(getContext(), "inactive", Toast.LENGTH_SHORT).show();
                //}
                finish();
            }

        });
    }
}