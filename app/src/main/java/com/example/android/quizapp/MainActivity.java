package com.example.android.quizapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RadioGroup radioGroup01;
    RadioButton radio01, radio02, radio03, radio04;
    CheckBox cBox01, cBox02, cBox03, cBox04;
    EditText eText01, eText02;
    TextView textView01;
    Button button01, button02;

    String score01;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        radioGroup01 = (RadioGroup) findViewById(R.id.q1RGRP);
        radio01 = (RadioButton) findViewById(R.id.q1RBTNA);
        radio02 = (RadioButton) findViewById(R.id.q1RBTNB);
        radio03 = (RadioButton) findViewById(R.id.q1RBTNC);
        radio04 = (RadioButton) findViewById(R.id.q1RBTND);

        cBox01 = (CheckBox) findViewById(R.id.q2CBXA);
        cBox02 = (CheckBox) findViewById(R.id.q2CBXB);
        cBox03 = (CheckBox) findViewById(R.id.q2CBXC);
        cBox04 = (CheckBox) findViewById(R.id.q2CBXD);

        eText01 = (EditText) findViewById(R.id.q3TXT);
        eText02 = (EditText) findViewById(R.id.q4TXT);

        textView01 = (TextView) findViewById(R.id.quizScore);
        button01 = (Button) findViewById(R.id.fBTN);
        button02 = (Button) findViewById(R.id.rBTN);


    }

    public void calculate(View v) {

        button01.setVisibility(View.INVISIBLE);
        button02.setVisibility(View.VISIBLE);

        int trueAnswer = 0, falseAnswer = 0;

        String ansMes = getString(R.string.ansMes);
        String wrongMes = getString(R.string.wrongMes);
        //question1
        switch (radioGroup01.getCheckedRadioButtonId()) {
            case R.id.q1RBTNA: {
                radio01.setTextColor(Color.parseColor("#388E3C"));
                trueAnswer = trueAnswer + 1;
                break;
            }
            case R.id.q1RBTNB: {
                radio02.setTextColor(Color.RED);
                falseAnswer = falseAnswer + 1;
                Toast.makeText(this, wrongMes + " 1", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.q1RBTNC: {
                radio03.setTextColor(Color.RED);
                Toast.makeText(this, wrongMes + " 1", Toast.LENGTH_SHORT).show();
                falseAnswer = falseAnswer + 1;
                break;
            }
            case R.id.q1RBTND: {
                radio04.setTextColor(Color.RED);
                Toast.makeText(this, wrongMes + " 1", Toast.LENGTH_SHORT).show();
                falseAnswer = falseAnswer + 1;
                break;
            }
            default:

                Toast.makeText(this, ansMes + " 1", Toast.LENGTH_SHORT).show();
        }

        //question2

        if (cBox01.isChecked() || cBox02.isChecked() || cBox03.isChecked() || cBox04.isChecked()) {

            if (cBox01.isChecked())//true
            {
                cBox01.setTextColor(Color.parseColor("#388E3C"));

            }

            if (cBox02.isChecked())//false
            {
                cBox02.setTextColor(Color.RED);
                falseAnswer = falseAnswer + 1;
                Toast.makeText(this, wrongMes + " 2", Toast.LENGTH_SHORT).show();

            } else {

                if (cBox01.isChecked() && cBox03.isChecked() && cBox04.isChecked()) {
                    trueAnswer = trueAnswer + 1;

                } else
                    Toast.makeText(this, "Please Select All True Answers-Question 2", Toast.LENGTH_SHORT).show();

            }

            if (cBox03.isChecked())//true
            {
                cBox03.setTextColor(Color.parseColor("#388E3C"));
            }
            if (cBox04.isChecked())//false
            {
                cBox04.setTextColor(Color.parseColor("#388E3C"));
            }

        } else {

            Toast.makeText(this, ansMes + " 2", Toast.LENGTH_SHORT).show();
        }

        //question3

        String q3answer = eText01.getText().toString();

        if (!q3answer.equals("")) {

            if (q3answer.toLowerCase().equals("russia")) {

                trueAnswer = trueAnswer + 1;
                ;
                eText01.setTextColor(Color.parseColor("#388E3C"));
            } else {
                falseAnswer = falseAnswer + 1;
                eText01.setTextColor(Color.RED);
                Toast.makeText(this, wrongMes + " 3", Toast.LENGTH_SHORT).show();

            }
        } else {

            Toast.makeText(this, ansMes + " 3", Toast.LENGTH_SHORT).show();
        }


        //question4
        String q4answer = eText02.getText().toString();

        if (!q4answer.equals("")) {

            if (q4answer.toLowerCase().equals("turkey")) {
                trueAnswer = trueAnswer + 1;
                eText02.setTextColor(Color.parseColor("#388E3C"));
            } else {
                falseAnswer = falseAnswer + 1;
                eText02.setTextColor(Color.RED);
                Toast.makeText(this, wrongMes + " 4", Toast.LENGTH_SHORT).show();
            }
        } else {

            Toast.makeText(this, ansMes + " 4", Toast.LENGTH_SHORT).show();
        }

        score01 = "True Answer: " + trueAnswer + " False Answer: " + falseAnswer;
        textView01.setText(score01);
        textView01.setTextColor(Color.BLUE);


    }

    public void restart(View v) {

        button02.setVisibility(View.INVISIBLE);
        button01.setVisibility(View.VISIBLE);

        Intent i = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        finish();
        startActivity(i);
    }

    public void shareScore(View v) {

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Hi My Quiz App Score " + score01);
        sendIntent.setType("text/plain");
        startActivity(sendIntent);

    }
}
