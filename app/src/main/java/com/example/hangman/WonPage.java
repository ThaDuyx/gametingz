package com.example.hangman;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WonPage extends AppCompatActivity implements View.OnClickListener {

    TextView TWwordview;
    TextView TWtries;
    Button btnplayagan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_won_page);


        TWwordview = findViewById(R.id.textView7);
        TWtries = findViewById(R.id.textView8);
        btnplayagan = findViewById(R.id.button2);

        btnplayagan.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {



    }
}
