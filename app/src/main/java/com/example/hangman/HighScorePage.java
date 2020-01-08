package com.example.hangman;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.gsm.GsmCellLocation;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class HighScorePage extends AppCompatActivity {

    private ArrayList<String> highscore = new ArrayList<>();
    private ArrayList<String> date = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score_page);

        loadScore();

        initRecyclerView();
    }

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.highscoreRV);
        RVAdapterLogic rvAdapterLogic = new RVAdapterLogic(date, highscore);
        recyclerView.setAdapter(rvAdapterLogic);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void loadScore(){
        SharedPreferences sharedPreferences = getSharedPreferences("highscore", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("high",null);
        String json2 = sharedPreferences.getString("date", null);
        Type type = new TypeToken<ArrayList<String>>(){}.getType();
        highscore = gson.fromJson(json, type);
        date = gson.fromJson(json2, type);

    }
}
