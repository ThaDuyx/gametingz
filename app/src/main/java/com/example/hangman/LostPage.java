package com.example.hangman;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LostPage extends AppCompatActivity implements View.OnClickListener {

    TextView TWLost;
    Button btnPlayAgain;
    Button btnGoBack;
    String theCorrectWord;
    int looseHolder = 1;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_page);


        mp = MediaPlayer.create(this, R.raw.wilhelmscream);
        mp.start();


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
            Intent intent = new Intent(this, GamePage.class);
            startActivity(intent);
            finish();
        } else {
            Intent intent = new Intent(this, StartPage.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, StartPage.class);
        startActivity(intent);
        finish();
    }
}
