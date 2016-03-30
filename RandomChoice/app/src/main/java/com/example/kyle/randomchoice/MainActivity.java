package com.example.kyle.randomchoice;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

    private TextView tvSubmit;

    private EditText etNumChoices;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        tvSubmit = (TextView) findViewById(R.id.tv_submit_num_choices);
        etNumChoices = (EditText) findViewById(R.id.et_num_choices);

        tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    int numChoices = Integer.parseInt(etNumChoices.getText().toString());

                    if (numChoices < 2) {
                        Context context = getApplicationContext();
                        CharSequence text = "Enter at least 2 choices";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }//end if
                    else if (numChoices > 100) {
                        Context context = getApplicationContext();
                        CharSequence text = "The max number of choices is 100";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }//end else if
                    else {

                        Intent intent = new Intent(MainActivity.this, EnterChoiceActivity.class);
                        intent.putExtra("numChoices", numChoices);
                        startActivity(intent);
                    }//end else
                } catch (NumberFormatException e) {
                    Context context = getApplicationContext();
                    CharSequence text = "Enter whole numbers only";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }//end catch
            }//end onClick
        });//end tvSubmit.setOnClickListener
    }//end onCreate method
}//end MainActivity class
