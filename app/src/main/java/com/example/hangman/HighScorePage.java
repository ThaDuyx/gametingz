package com.example.hangman;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;

public class HighScorePage extends AppCompatActivity {


    // Til løsning af dette er denne youtube video blevet brugt til at lære om RecyclerViews
    // Desuden med brugen af shared preferences
    // --- https://www.youtube.com/watch?v=Vyqz_-sJGFk
    //-----------------------------------------------------------------------------------------------
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
        if(json != null){
            date = gson.fromJson(json2, type);
            highscore = gson.fromJson(json, type);
            Collections.reverse(date);
            Collections.reverse(highscore);
        }
    }
}
