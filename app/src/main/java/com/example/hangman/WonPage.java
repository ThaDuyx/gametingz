package com.example.hangman;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class WonPage extends AppCompatActivity implements View.OnClickListener {

    TextView TWwordview;
    TextView TWtries;
    Button btnplayagan;
    ImageView hangManpic;
    int age;
    String theCorrectWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_won_page);

        hangManpic = findViewById(R.id.hangmanPic3);
        TWwordview = findViewById(R.id.textView7);
        TWtries = findViewById(R.id.textView11);
        btnplayagan = findViewById(R.id.button);

        btnplayagan.setOnClickListener(this);

        theCorrectWord = getIntent().getStringExtra("gameWord");
        age = getIntent().getIntExtra("Guesses", 0);

        TWwordview.setText(theCorrectWord);
        TWtries.setText(Integer.toString(age));

        if (age == 0) {
            hangManpic.setImageResource(R.drawable.galge);
        } else if (age == 1) {
            hangManpic.setImageResource(R.drawable.forkert1);
        } else if (age == 2) {
            hangManpic.setImageResource(R.drawable.forkert2);
        } else if (age == 3) {
            hangManpic.setImageResource(R.drawable.forkert3);
        } else if (age == 4) {
            hangManpic.setImageResource(R.drawable.forkert4);
        } else if (age == 5) {
            hangManpic.setImageResource(R.drawable.forkert5);
        } else if (age == 6) {
            hangManpic.setImageResource(R.drawable.forkert6);
        }
    }

    @Override
    public void onClick(View v) {

        /*logic.nulstil();
            resetBtn.setVisibility(View.GONE);
            wordFrame.setText(logic.getSynligtOrd());
            hangManpic.setImageResource(R.drawable.galge);
            guessFrame.setText("Guess the new word");
            editGuess.setError(null);*/

    }
}
