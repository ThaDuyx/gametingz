package com.example.hangman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartPage extends AppCompatActivity implements View.OnClickListener {

    Button startGameBtn;
    Button helpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);

        startGameBtn = findViewById(R.id.startGameBtn);
        helpBtn = findViewById(R.id.helpBtn);

        startGameBtn.setOnClickListener(this);
        helpBtn.setOnClickListener(this);

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