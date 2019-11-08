package com.example.hangman;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HelpPage extends AppCompatActivity implements View.OnClickListener {

    int counter = 1;
    Button rightBtn;
    Button leftBtn;
    TextView helpTV;
    TextView countTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_page);

        rightBtn = findViewById(R.id.rightBtn);
        leftBtn = findViewById(R.id.leftBtn);
        helpTV = findViewById(R.id.helpTV);
        countTV = findViewById(R.id.countTV);

        rightBtn.setOnClickListener(this);
        leftBtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        if(v == rightBtn){
            counter++;
            if(counter>4){
                counter = 4;
            }
        } else if(v == leftBtn){
            counter--;
            if(counter<1){
                counter = 1;
            }
        }

        switch (counter){
            case 1:
                helpTV.setText("This game is about guessing the word depicted on the screen by the empty lines.");
                countTV.setText("1/4");
                break;
            case 2: helpTV.setText("After 7 wrong guesses you'll lose and the man will be hanged.");
                countTV.setText("2/4");
                break;
            case 3: helpTV.setText("Guess one word at a time.");
                countTV.setText("3/4");
                break;
            case 4: helpTV.setText("After a game is lost or won. Press play again to get a new word.");
                countTV.setText("4/4");
                break;
        }
    }
}
