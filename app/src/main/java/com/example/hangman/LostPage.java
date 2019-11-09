package com.example.hangman;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class LostPage extends AppCompatActivity {

    TextView TWLost;
    Button btnPlayAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_page);

        TWLost.findViewById(R.id.textView10);
        btnPlayAgain.findViewById(R.id.button2);

        TWLost.setText(getIntent().getStringExtra("gameWord"));



    }
}
