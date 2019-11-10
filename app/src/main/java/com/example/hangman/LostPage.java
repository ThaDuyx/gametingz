package com.example.hangman;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LostPage extends AppCompatActivity implements View.OnClickListener {

    GameLogic logic = new GameLogic();
    TextView TWLost;
    Button btnPlayAgain;
    Button btnGoBack;
    String theCorrectWord;
    int looseHolder = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_page);

        TWLost = findViewById(R.id.textView10);
        btnPlayAgain = findViewById(R.id.button2);
        btnGoBack = findViewById(R.id.button4);
        btnPlayAgain.setOnClickListener(this);
        btnGoBack.setOnClickListener(this);

        theCorrectWord = getIntent().getStringExtra("gameWord");

        TWLost.setText(theCorrectWord);

        SharedPreferences sp = getSharedPreferences("sharedPred", Activity.MODE_PRIVATE);
        SharedPreferences.Editor spe = sp.edit();
        looseHolder = sp.getInt("losses", 0);
        looseHolder++;
        spe.putInt("losses", looseHolder);
        spe.apply();

    }

    @Override
    public void onClick(View v) {

        if(v == btnPlayAgain) {
            logic.nulstil();
            Intent intent = new Intent(this, GamePage.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, StartPage.class);
            startActivity(intent);
        }
    }
}