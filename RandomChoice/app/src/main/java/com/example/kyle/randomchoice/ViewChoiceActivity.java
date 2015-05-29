package com.example.kyle.randomchoice;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class ViewChoiceActivity extends Activity {

    private String[] choices;

    private TextView tvRandomChoice;

    private TextView tvNewChoice;

    private TextView tvReset;

    private int numNewChoicePresses = 0;

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
                if (numNewChoicePresses <= choices.length / 4) {
                    numNewChoicePresses += 1;
                    tvRandomChoice.setText(getRandomChoice());
                }//end if
                else {
                    Context context = getApplicationContext();
                    CharSequence text = "You have pressed that too many times. Start over for more choices";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                    tvNewChoice.setVisibility(View.GONE);
                }//end else
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
