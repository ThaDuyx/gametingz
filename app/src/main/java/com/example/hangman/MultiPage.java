package com.example.hangman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MultiPage extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_page);

        String[] lande = {"denmark", "norway", "sweden", "finland", "netherlands", "italy", "germany", "france", "spain", "portugal", "nepal", "india", "china", "japan", "thailand"};

        AdapterLogic adapter = new AdapterLogic(this, R.layout.list_layout, lande);
        ListView listView = findViewById(R.id.word_list);
        listView.setOnItemClickListener(this);
        listView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        String text = parent.getItemAtPosition(position).toString();

        Intent intent = new Intent(this, MultiGamePage.class);
        intent.putExtra("theWordToBeGuessed", text);
        startActivity(intent);

    }
}
