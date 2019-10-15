package com.example.hangman;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

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
        setContentView(R.layout.activity_main2);


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
        guessFrame.setText("");
        logic.logStatus();


    }

    @Override
    public void onClick(View v) {

        if( v == resetBtn && logic.erSpilletSlut()){
            logic.nulstil();
            resetBtn.setVisibility(View.GONE);
            wordFrame.setText(logic.getSynligtOrd());
            hangManpic.setImageResource(R.drawable.galge);
            guessFrame.setText("Guess the new word");
        }

        String guess = editGuess.getText().toString();
        if (guess.length() != 1) {
            editGuess.setError("1 letter at a time");
            return;
        }

        logic.gætBogstav(guess);
        editGuess.setText("");

        if(logic.getAntalForkerteBogstaver() == 0){
            hangManpic.setImageResource(R.drawable.galge);
        } else if(logic.getAntalForkerteBogstaver() == 1){
            hangManpic.setImageResource(R.drawable.forkert1);
        } else if(logic.getAntalForkerteBogstaver() == 2){
            hangManpic.setImageResource(R.drawable.forkert2);
        } else if(logic.getAntalForkerteBogstaver() == 3){
            hangManpic.setImageResource(R.drawable.forkert3);
        } else if(logic.getAntalForkerteBogstaver() == 4){
            hangManpic.setImageResource(R.drawable.forkert4);
        } else if(logic.getAntalForkerteBogstaver() == 5){
            hangManpic.setImageResource(R.drawable.forkert5);
        } else if(logic.getAntalForkerteBogstaver() == 6){
            hangManpic.setImageResource(R.drawable.forkert6);
        }

       /* if(logic.erSpilletSlut()){
            logic.nulstil();
        }
        if(guessBtn.getText().toString() == "Play"){
            logic.nulstil();
            guessBtn.setText("Guess");
        }*/

        updateScreen();
    }

    private void updateScreen(){

        wordFrame.setText(logic.getSynligtOrd());
        guessFrame.setText("\n\nDu har " + logic.getAntalForkerteBogstaver() + " forkerte:" + logic.getBrugteBogstaver());

        if (logic.erSpilletVundet()) {
            guessFrame.setText("\nDu har vundet");
            //guessBtn.setText("Play");
            resetBtn.setVisibility(View.VISIBLE);
        }
        if (logic.erSpilletTabt()) {
            wordFrame.setText("Du har tabt, ordet var : " + logic.getOrdet());
            //guessBtn.setText("Play");
            resetBtn.setVisibility(View.VISIBLE);
        }

    }

}


