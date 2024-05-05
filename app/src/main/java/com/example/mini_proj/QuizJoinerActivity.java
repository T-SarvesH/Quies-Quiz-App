package com.example.mini_proj;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.preference.PreferenceManager;

import com.example.mini_proj.databinding.ActivityQuizJoinerBinding;

import java.util.ArrayList;

public class QuizJoinerActivity extends AppCompatActivity implements View.OnClickListener {

    private AppBarConfiguration appBarConfiguration;
    private ActivityQuizJoinerBinding binding;
    public static String resu;

    TextView totalQuestionsTextView;
    TextView questionTextView;
    Button ansA, ansB, ansC, ansD;
    Button submitBtn;

    int score=0;
    int totalQuestion = QuestionAnswer.question.size();
    int currentQuestionIndex = 0;
    int selectedAnswer = 0;
    ArrayList<Integer> map =  new ArrayList<Integer>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityQuizJoinerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        map.add(R.id.ans_A);
        map.add(R.id.ans_B);
        map.add(R.id.ans_C);
        map.add(R.id.ans_D);

        totalQuestionsTextView = findViewById(R.id.total_question);
        questionTextView = findViewById(R.id.question);
        ansA = findViewById(R.id.ans_A);
        ansB = findViewById(R.id.ans_B);
        ansC = findViewById(R.id.ans_C);
        ansD = findViewById(R.id.ans_D);
        submitBtn = findViewById(R.id.submit_btn);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        submitBtn.setOnClickListener(this);

        totalQuestionsTextView.setText("Total questions : "+totalQuestion);

        loadNewQuestion();

        //setSupportActionBar(binding.toolbar);

        //NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_quiz_joiner);
        //appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
    }
    @Override
    public void onClick(View view) {
        int tc =getResources().getColor(R.color.bluebg);
        int bg=getResources().getColor(R.color.ccl);
        int corc =getResources().getColor(R.color.bluebgdark);
        ansA.setBackgroundColor(bg);
        ansB.setBackgroundColor(bg);
        ansC.setBackgroundColor(bg);
        ansD.setBackgroundColor(bg);
        ansA.setTextColor(tc);
        ansB.setTextColor(tc);
        ansC.setTextColor(tc);
        ansD.setTextColor(tc);

        Button clickedButton = (Button) view;
        if(clickedButton.getId()==R.id.submit_btn){
            if(selectedAnswer+1==(QuestionAnswer.correctAnswers.get(currentQuestionIndex))){
                score++;
            }
            currentQuestionIndex++;
            loadNewQuestion();


        }else {
            selectedAnswer = map.indexOf(clickedButton.getId());
            clickedButton.setBackgroundColor(corc);
        }

    }

    void loadNewQuestion(){

        if(currentQuestionIndex == totalQuestion ){
            finishQuiz();
            return;
        }

        questionTextView.setText(QuestionAnswer.question.get(currentQuestionIndex));
        ansA.setText((CharSequence) QuestionAnswer.choices.get(currentQuestionIndex).get(0));
        ansB.setText((CharSequence) QuestionAnswer.choices.get(currentQuestionIndex).get(1));
        ansC.setText((CharSequence) QuestionAnswer.choices.get(currentQuestionIndex).get(2));
        ansD.setText((CharSequence) QuestionAnswer.choices.get(currentQuestionIndex).get(3));

    }

    void finishQuiz(){
        String passStatus = "";
        if(score > totalQuestion*0.60){
            passStatus = "Passed";
        }else{
            passStatus = "Failed";
        }
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage(sp.getString("signature","")+"'s score is "+ score+" out of "+ totalQuestion)
                .setPositiveButton("Go to Home Page",(dialogInterface, i) -> restartQuiz() )
                .setCancelable(false)
                .show();


    }
    void restartQuiz(){
        score = 0;
        currentQuestionIndex =0;
        Intent i = new Intent(QuizJoinerActivity.this,MainActivity.class);
        startActivity(i);
    }



    /*
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_quiz_joiner);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }*/


}