package com.example.hangman;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    GameLogic logic = new GameLogic();
    Button guessBtn;
    EditText editGuess;
    TextView wordFrame;
    TextView guessFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        wordFrame = findViewById(R.id.wordFrame);
        guessFrame = findViewById(R.id.guessFrame);
        guessBtn = findViewById(R.id.guessBtn);
        editGuess = findViewById(R.id.editGuess);

        wordFrame.setText(logic.getSynligtOrd());
        guessBtn.setOnClickListener(this);
        guessFrame.setText("");
        logic.logStatus();


    }

    @Override
    public void onClick(View v) {

        String guess = editGuess.getText().toString();
        if (guess.length() != 1) {
            editGuess.setError("1 letter at a time");
            return;
        }
        logic.gætBogstav(guess);
        editGuess.setText("");

        reset();
    }

    private void reset(){

        wordFrame.setText(logic.getSynligtOrd());
        guessFrame.setText("\n\nDu har " + logic.getAntalForkerteBogstaver() + " forkerte:" + logic.getBrugteBogstaver());

        if (logic.erSpilletVundet()) {
            guessFrame.setText("\nDu har vundet");
        }
        if (logic.erSpilletTabt()) {
            wordFrame.setText("Du har tabt, ordet var : " + logic.getOrdet());
        }

    }

}


