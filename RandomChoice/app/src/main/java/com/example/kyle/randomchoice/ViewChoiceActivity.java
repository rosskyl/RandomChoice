package com.example.kyle.randomchoice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;


public class ViewChoiceActivity extends Activity {

    private String[] choices;

    private TextView tvRandomChoice;

    private TextView tvNewChoice;

    private TextView tvReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_choice);

        tvRandomChoice = (TextView) findViewById(R.id.tv_random_choice);
        tvNewChoice = (TextView) findViewById(R.id.tv_new_choice);
        tvReset = (TextView) findViewById(R.id.tv_reset);

        choices = getIntent().getStringArrayExtra("choices");

        tvRandomChoice.setText(getRandomChoice());

        tvNewChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvRandomChoice.setText(getRandomChoice());
            }//end onClick
        });//end tvNewChoice.setOnClickListener

        tvReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewChoiceActivity.this, MainActivity.class);
                startActivity(intent);
            }//end onClick
        });//end tvReset.setOnClickListener
    }//end onCreate method

    private String getRandomChoice() {
        Random random = new Random();

        return choices[random.nextInt(choices.length)];
    }//end getRandomChoice method
}//end ViewChoiceActivity class
