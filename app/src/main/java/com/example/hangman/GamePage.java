package com.example.hangman;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import java.lang.ref.WeakReference;

public class GamePage extends AppCompatActivity implements View.OnClickListener {

    GameLogic logic = new GameLogic();
    Button guessBtn;
    Button backBtn;
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
        backBtn = findViewById(R.id.button3);
        editGuess = findViewById(R.id.editGuess);
        hangManpic = findViewById(R.id.hangmanPic);

        wordFrame.setText(logic.getSynligtOrd());
        guessBtn.setOnClickListener(this);
        backBtn.setOnClickListener(this);
        guessFrame.setText("Guess the Word");

        //Brugt til AsyncTask metoden
        new wordsFromSheets(this, logic).execute();
    }

    // Jeg sad i noget tid og prøvede at lave AsyncTask opgaven men uden held.
    // Men en af hjælpelærene udgav en metode på discord som endte med at ligne meget hvad han havde lavet.
    // Nedenstående kode er taget der fra. Jeg startede med at prøve at gøre det selv.
    // Men endte til sidst med at gøre det som den udgivne metode.
    //------------------------------------------------------------------
   private void setWord(){wordFrame.setText(logic.getSynligtOrd());}

    @SuppressLint("SetTextI18n")
    private void showErrorMessage() {
        wordFrame.setText("Could not retrieve words");
    }

    private static class wordsFromSheets extends AsyncTask<String, String, Exception>{

        private final WeakReference<GamePage> activityRef;
        private final GameLogic logic;

        private wordsFromSheets(GamePage activity, GameLogic logic) {
            this.activityRef = new WeakReference<>(activity);
            this.logic = logic;
        }

        @Override
        protected Exception doInBackground(String... strings) {
            try {
                logic.hentOrdFraRegneark("2");
                logic.nulstil();
            } catch (Exception e) {
                return e;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Exception e) {
            if(activityRef.get() != null){
                if (e != null){
                    activityRef.get().showErrorMessage();
                } else {
                    activityRef.get().setWord();
                }
            }
        }
    }
    //------------------------------------------------------------------

    @Override
    public void onClick(View v) {

        if(v == guessBtn) {
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
        } else if(v == backBtn){
            Intent intent = new Intent(this, StartPage.class);
            startActivity(intent);
        }
    }

    private void updateScreen(){
        wordFrame.setText(logic.getSynligtOrd());
        guessFrame.setText("\n\nDu har " + logic.getAntalForkerteBogstaver() + " forkerte:" + logic.getBrugteBogstaver());

        if (logic.erSpilletVundet()) {
            Intent intent = new Intent(this, WonPage.class);
            intent.putExtra("gameWord", logic.getOrdet());
            intent.putExtra("Guesses", logic.getAntalForkerteBogstaver() + logic.getOrdet().length());
            finish();
            startActivity(intent);
        }
        if (logic.erSpilletTabt()) {
            Intent intent = new Intent(this, LostPage.class);
            intent.putExtra("gameWord", logic.getOrdet());
            startActivity(intent);
        }
    }
}


