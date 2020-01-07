package com.example.hangman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MultiGamePage extends AppCompatActivity implements View.OnClickListener {

    GameLogic logic = new GameLogic();
    String wordGuess;

    Button goToPlay;
    Button goToMenu;
    Button guessBtn;
    EditText editGuess;
    TextView wordFrame;
    TextView guessFrame;
    ImageView hangManpic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_game_page);

        wordGuess = getIntent().getStringExtra("theWordToBeGuessed");
        logic.muligeOrd.add(0, wordGuess);
        logic.nulstil();

        wordFrame = findViewById(R.id.wordFrame2);
        guessFrame = findViewById(R.id.guessFrame2);
        guessBtn = findViewById(R.id.guessBtn2);
        editGuess = findViewById(R.id.editGuess2);
        hangManpic = findViewById(R.id.hangmanPic4);

        goToMenu = findViewById(R.id.menuBtn);
        goToMenu.setVisibility(View.GONE);
        goToPlay = findViewById(R.id.playAgainBtn);
        goToPlay.setVisibility(View.GONE);

        guessBtn.setOnClickListener(this);
        goToPlay.setOnClickListener(this);
        goToMenu.setOnClickListener(this);

        wordFrame.setText(logic.getSynligtOrd());
    }

    @Override
    public void onClick(View v) {

        if(v == guessBtn){
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
        } else if (v == goToPlay){
            Intent intent = new Intent(this, MultiPage.class);
            startActivity(intent);
        } else if (v == goToMenu){
            Intent intent = new Intent(this, StartPage.class);
            startActivity(intent);

        }
    }

    private void updateScreen(){
        wordFrame.setText(logic.getSynligtOrd());
        guessFrame.setText("\n\nYou have " + logic.getAntalForkerteBogstaver() + " wrong guesses \nUsed letters:" +  logic.getBrugteBogstaver());

        if (logic.erSpilletVundet()) {
            goToMenu.setVisibility(View.VISIBLE);
            goToPlay.setVisibility(View.VISIBLE);
            guessBtn.setVisibility(View.GONE);
            editGuess.setVisibility(View.GONE);
        }
        if (logic.erSpilletTabt()) {
            wordFrame.setText("The correct word was: " + logic.getOrdet());
            goToMenu.setVisibility(View.VISIBLE);
            goToPlay.setVisibility(View.VISIBLE);
            guessBtn.setVisibility(View.GONE);
            editGuess.setVisibility(View.GONE);
        }
    }
}