package com.example.kyle.randomchoice;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class EnterChoiceActivity extends Activity {

    private int numChoices;

    private TextView tvSubmitChoices;

    private LinearLayout choicesContainer;

    private ArrayList<EditText> etChoices;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_choice);

        tvSubmitChoices = (TextView) findViewById(R.id.tv_submit_choices);
        choicesContainer = (LinearLayout) findViewById(R.id.choices_container);

        numChoices = getIntent().getIntExtra("numChoices", -1);

        etChoices = new ArrayList<EditText>();

        //TODO figure out scroll. Big space after submit and cant see first thing if large when keyboard shows
        for (int i = 0; i < numChoices; i += 1) {
            EditText etChoice = (EditText) LayoutInflater.from(this).inflate(R.layout.et_choices, choicesContainer, false);
            etChoices.add(etChoice);
            choicesContainer.addView(etChoice);
        }//end for loop

        tvSubmitChoices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] choices = new String[numChoices];
                boolean isEmpty = false;
                for (int i = 0; i < etChoices.size(); i += 1) {
                    String choice = etChoices.get(i).getText().toString();
                    if (choice.equals(""))
                        isEmpty = true;
                    else
                        choices[i] = choice;
                }//end for loop

                if (isEmpty) {
                    Context context = getApplicationContext();
                    CharSequence text = "Fill out all choices";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }//end if
                else {
                    Intent intent = new Intent(EnterChoiceActivity.this, ViewChoiceActivity.class);
                    intent.putExtra("choices", choices);
                    startActivity(intent);
                }//end else
            }//end onClick
        });//end tvSubmitChoices.setOnClickListener
    }//end onCreate method
}//end EnterChoiceActivity class