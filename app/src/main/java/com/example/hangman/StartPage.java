package com.example.hangman;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StartPage extends AppCompatActivity implements View.OnClickListener {

    Button startGameBtn;
    Button helpBtn;
    TextView winHolder;
    TextView looseHolder;
    int wonHolder;
    int lostHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);

        winHolder = findViewById(R.id.textView12);
        looseHolder = findViewById(R.id.textView15);

        startGameBtn = findViewById(R.id.startGameBtn);
        helpBtn = findViewById(R.id.helpBtn);

        startGameBtn.setOnClickListener(this);
        helpBtn.setOnClickListener(this);

        SharedPreferences sp = getSharedPreferences("sharedPred", Activity.MODE_PRIVATE);
        wonHolder = sp.getInt("wins",0);
        winHolder.setText(Integer.toString(wonHolder));

        lostHolder = sp.getInt("losses",0);
        looseHolder.setText(Integer.toString(lostHolder));







    }

    @Override
    public void onClick(View v) {

        if(v == startGameBtn){
          Intent intent = new Intent(this, GamePage.class);
          startActivity(intent);

        } else if(v == helpBtn){
            Intent intent = new Intent(this, HelpPage.class);
            startActivity(intent);
        }
    }

}

