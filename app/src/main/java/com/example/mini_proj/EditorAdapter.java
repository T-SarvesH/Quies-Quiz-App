package com.example.mini_proj;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EditorAdapter extends RecyclerView.Adapter<EditorAdapter.ViewHolder> {

    private final I anInterface;




    public Context cont;

    public EditorAdapter(/*AdapterInterface anInterface*/I anInterface, Context cont) {
        this.anInterface = anInterface;
        //this.anInterface = anInterface;
        this.cont=cont;
    }



    /*

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View rootView = LayoutInflater.from(cont)
                .inflate(R.layout.question, viewGroup, false);
        question=rootView.findViewById(R.id.editTextTextMultiLine);
        A=rootView.findViewById(R.id.editTextText);
        B=rootView.findViewById(R.id.editTextText2);
        C=rootView.findViewById(R.id.editTextText3);
        D=rootView.findViewById(R.id.editTextText4);
        correct=rootView.findViewById(R.id.editTextText5);
        return rootView;
    }
*/


/*
    public String getChoices()
    {
        return (A.getText().toString()+'\n'+B.getText().toString()+'\n'+C.getText().toString()+'\n'+D.getText().toString());
    }
    public String corr()
    {
        return (A.getText().toString()+'\n'+B.getText().toString()+'\n'+C.getText().toString()+'\n'+D.getText().toString());
    }*/



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.questhumb,parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String thb;
        if(QuestionAnswer.question.get(position).length()>18)
        {thb=QuestionAnswer.question.get(position).substring(0, 15)+"...";}
        else thb=QuestionAnswer.question.get(position);
        holder.q.setText(thb);
    }

    @Override
    public int getItemCount() {
        return QuestionAnswer.question.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder
    {

        TextView q;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);

            ImageButton b= itemView.findViewById(R.id.imageButton);
            q=itemView.findViewById(R.id.textView4);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent z = new Intent(cont, QuestionEditor.class);
                    z.putExtra("quesno", getAdapterPosition());
                    z.putExtra("editmode", true);
                    cont.startActivity(z);
                }
            });
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //if(anInterface!=null)
                    //{
                        if(getAdapterPosition()!=RecyclerView.NO_POSITION) {
                            QuestionAnswer.correctAnswers.remove(getAdapterPosition());
                            QuestionAnswer.choices.remove(getAdapterPosition());
                            QuestionAnswer.question.remove(getAdapterPosition());
                            EditorActivity.rcyca.notifyItemRemoved(getAdapterPosition());
                            if(anInterface!=null)
                            {
                                anInterface.enable();
                            }
                            //anInterface.notifyy(getAdapterPosition());
                        }
                   // }
                }
            });
        }

    }
}
