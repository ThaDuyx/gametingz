package com.example.hangman;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class WonPage extends AppCompatActivity implements View.OnClickListener {

    GameLogic logic = new GameLogic();
    TextView TWwordview;
    TextView TWtries;
    Button btnplayagan;
    Button btnGoBack;
    ImageView hangManpic;
    int numberguesses;
    int winHolder = 0;

    String theCorrectWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_won_page);

        hangManpic = findViewById(R.id.hangmanPic3);
        TWwordview = findViewById(R.id.textView7);
        TWtries = findViewById(R.id.textView11);
        btnplayagan = findViewById(R.id.button);
        btnGoBack = findViewById(R.id.button5);

        btnplayagan.setOnClickListener(this);
        btnGoBack.setOnClickListener(this);

        theCorrectWord = getIntent().getStringExtra("gameWord");
        numberguesses = getIntent().getIntExtra("Guesses", 0);

        TWwordview.setText(theCorrectWord);
        TWtries.setText(Integer.toString(numberguesses));

        if (numberguesses == 0) {
            hangManpic.setImageResource(R.drawable.galge);
        } else if (numberguesses == 1) {
            hangManpic.setImageResource(R.drawable.forkert1);
        } else if (numberguesses == 2) {
            hangManpic.setImageResource(R.drawable.forkert2);
        } else if (numberguesses == 3) {
            hangManpic.setImageResource(R.drawable.forkert3);
        } else if (numberguesses == 4) {
            hangManpic.setImageResource(R.drawable.forkert4);
        } else if (numberguesses == 5) {
            hangManpic.setImageResource(R.drawable.forkert5);
        } else if (numberguesses == 6) {
            hangManpic.setImageResource(R.drawable.forkert6);
        }

        SharedPreferences sp = getSharedPreferences("sharedPred", Activity.MODE_PRIVATE);
        SharedPreferences.Editor spe = sp.edit();
        winHolder = sp.getInt("wins", 0);
        winHolder++;
        spe.putInt("wins", winHolder);
        spe.apply();
    }

    @Override
    public void onClick(View v) {

        if(v == btnplayagan) {
            logic.nulstil();
            Intent intent = new Intent(this, GamePage.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, StartPage.class);
            startActivity(intent);
        }
    }
}
