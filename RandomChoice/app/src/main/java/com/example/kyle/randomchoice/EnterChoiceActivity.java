package com.example.kyle.randomchoice;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class EnterChoiceActivity extends Activity {

    private int numChoices;

    private TextView tvSubmitChoices;

    private LinearLayout choicesContainer;

    private ArrayList<EditText> etChoices;

    private ImageView ivAdd;

    private ImageView ivRemove;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_choice);

        tvSubmitChoices = (TextView) findViewById(R.id.tv_submit_choices);
        choicesContainer = (LinearLayout) findViewById(R.id.choices_container);
        ivAdd = (ImageView) findViewById(R.id.iv_add);
        ivRemove = (ImageView) findViewById(R.id.iv_remove);

        numChoices = getIntent().getIntExtra("numChoices", -1);

        etChoices = new ArrayList<EditText>();

        for (int i = 0; i < numChoices; i += 1) {
            EditText etChoice = (EditText) LayoutInflater.from(this).inflate(R.layout.et_choices, choicesContainer, false);
            etChoices.add(etChoice);
            choicesContainer.addView(etChoice);
            etChoice.setHint("Choice " + (i+1));
        }//end for loop

        tvSubmitChoices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] choices = new String[numChoices];
                boolean isEmpty = false;
                for (int i = 0; i < numChoices; i += 1) {
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

        ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numChoices > 100) {
                    Context context = getApplicationContext();
                    CharSequence text = "The max number of choices is 100";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }//end if
                else {
                    numChoices += 1;
                    if (numChoices <= etChoices.size()) {
                        etChoices.get(numChoices-1).setVisibility(View.VISIBLE);
                    }//end if
                    else {
                        Context context = getApplicationContext();
                        EditText etChoice = (EditText) LayoutInflater.from(context).inflate(R.layout.et_choices, choicesContainer, false);
                        etChoices.add(etChoice);
                        choicesContainer.addView(etChoice);
                    }//end else
                }//end else
            }//end onClick
        });//end ivAdd.setOnClickListener

        ivRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etChoices.get(numChoices-1).setVisibility(View.GONE);
                numChoices -= 1;
            }//end onClick
        });//end ivRemove.setOnClickListener
    }//end onCreate method
}//end EnterChoiceActivity class