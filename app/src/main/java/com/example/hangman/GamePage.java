package com.example.hangman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class GamePage extends AppCompatActivity implements View.OnClickListener {

    GameLogic logic = new GameLogic();
    Button guessBtn;
    Button resetBtn;
    EditText editGuess;
    TextView wordFrame;
    TextView guessFrame;
    ImageView hangManpic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_page);


        wordFrame = findViewById(R.id.wordFrame);
        guessFrame = findViewById(R.id.guessFrame);
        guessBtn = findViewById(R.id.guessBtn);
        resetBtn = findViewById(R.id.resetBtn);
        editGuess = findViewById(R.id.editGuess);
        hangManpic = findViewById(R.id.hangmanPic);

        wordFrame.setText(logic.getSynligtOrd());
        guessBtn.setOnClickListener(this);
        resetBtn.setOnClickListener(this);
        resetBtn.setVisibility(View.GONE);
        guessFrame.setText("Guess the Word");
        logic.logStatus();
    }

    @Override
    public void onClick(View v) {

        if( v == resetBtn && logic.erSpilletSlut()){
            /*logic.nulstil();
            resetBtn.setVisibility(View.GONE);
            wordFrame.setText(logic.getSynligtOrd());
            hangManpic.setImageResource(R.drawable.galge);
            guessFrame.setText("Guess the new word");
            editGuess.setError(null);*/
        } else if(v == guessBtn) {
            String guess = editGuess.getText().toString();

            if (guess.length() != 1) {
                editGuess.setError("1 letter at a time");
                return;
            }

            editGuess.setError(null);
            logic.gætBogstav(guess);
            editGuess.setText("");

            if (logic.getAntalForkerteBogstaver() == 0) {
                hangManpic.setImageResource(R.drawable.galge);
            } else if (logic.getAntalForkerteBogstaver() == 1) {
                hangManpic.setImageResource(R.drawable.forkert1);
            } else if (logic.getAntalForkerteBogstaver() == 2) {
                hangManpic.setImageResource(R.drawable.forkert2);
            } else if (logic.getAntalForkerteBogstaver() == 3) {
                hangManpic.setImageResource(R.drawable.forkert3);
            } else if (logic.getAntalForkerteBogstaver() == 4) {
                hangManpic.setImageResource(R.drawable.forkert4);
            } else if (logic.getAntalForkerteBogstaver() == 5) {
                hangManpic.setImageResource(R.drawable.forkert5);
            } else if (logic.getAntalForkerteBogstaver() == 6) {
                hangManpic.setImageResource(R.drawable.forkert6);
            }

            updateScreen();
        }
    }

    private void updateScreen(){

        wordFrame.setText(logic.getSynligtOrd());
        guessFrame.setText("\n\nDu har " + logic.getAntalForkerteBogstaver() + " forkerte:" + logic.getBrugteBogstaver());

        if (logic.erSpilletVundet()) {
            Intent intent = new Intent(this, WonPage.class);
            intent.putExtra("gameWord", logic.getOrdet());
            intent.putExtra("Guesses", logic.getAntalForkerteBogstaver() + logic.getOrdet().length());
            startActivity(intent);
        }
        if (logic.erSpilletTabt()) {
            Intent intent = new Intent(this, LostPage.class);
            intent.putExtra("gameWord", logic.getOrdet());
            startActivity(intent);
        }

    }

}


