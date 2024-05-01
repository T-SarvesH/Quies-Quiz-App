package com.example.mini_proj;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mini_proj.databinding.ActivityEditorBinding;

public class EditorActivity extends AppCompatActivity implements I /*implements EditorInterface*/ {


    private AppBarConfiguration appBarConfiguration;
    private ActivityEditorBinding binding;
    static FragmentManager fm;
    public static  int ques;
    public static RecyclerView.Adapter<EditorAdapter.ViewHolder> rcyca;
    RecyclerView u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityEditorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if (QuestionAnswer.question.size()>0){binding.button5.setEnabled(true);
            binding.button5.getBackground().setAlpha(255);
            binding.button5.setTextColor(getResources().getColor(R.color.textbutt));}
        else{binding.button5.getBackground().setAlpha(64);binding.button5.setTextColor(getResources().getColor(R.color.disablefg));}
        rcyca=new EditorAdapter(this, this);
        u = findViewById(R.id.recycv);
        u.setHasFixedSize(false);
        u.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        u.setAdapter(rcyca);
        Intent i = new Intent(this, QuestionEditor.class);
        Intent gen = new Intent(this, QrDisplayActivity.class);
        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(QuestionAnswer.question.size()>0){binding.button5.setEnabled(true);binding.button5.getBackground().setAlpha(255);
                    binding.button5.setTextColor(getResources().getColor(R.color.textbutt));}
                Bundle b = new Bundle();
                i.putExtra("quesno", QuestionAnswer.question.size());
                i.putExtra("editmode", false);

                //Toast.makeText(EditorActivity.this, "iyy", Toast.LENGTH_SHORT).show();128
                startActivity(i);
            }
        });
        binding.button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(gen);
            }
        });





/*
        setSupportActionBar(binding.toolbar);
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();*/

        //NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_editor);
        //appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);


    }

    @Override
    public void enable() {
        Toast.makeText(this, "loln", Toast.LENGTH_SHORT).show();
        if(QuestionAnswer.question.size()>0) {
            Toast.makeText(this, "lolna", Toast.LENGTH_SHORT).show();
            binding.button5.setEnabled(true);
            binding.button5.getBackground().setAlpha(255);
            binding.button5.setTextColor(getResources().getColor(R.color.textbutt));
        }
        else{ binding.button5.setEnabled(false);binding.button5.getBackground().setAlpha(64);binding.button5.setTextColor(getResources().getColor(R.color.disablefg));}
    }




/*
    @Override
    public void note(int qn, boolean update) {
        if (update)
        {
            rcyca.notifyItemChanged(qn);
        }else
        {
            rcyca.notifyItemInserted(qn);
        }
    }

    @Override
    public void notifyy(int i) {
        rcyca.notifyItemRemoved(i);
    }
*/
    /*
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_editor);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }*/
}