package com.example.hangman;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.ref.WeakReference;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class GamePage extends AppCompatActivity implements View.OnClickListener {

    ArrayList<String> hs = new ArrayList<>();
    ArrayList<String> date = new ArrayList<>();
    GameLogic logic = new GameLogic();
    Button guessBtn;
    Button backBtn;
    EditText editGuess;
    TextView wordFrame;
    TextView guessFrame;
    TextView pointsFrame;
    ImageView hangManpic;
    ImageView correctword;
    ImageView wrongword;
    int score;
    String scoreStr;
    String dateStr;
    String guessChecker;
    ArrayList<String> guessLetters = new ArrayList<>();


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
        pointsFrame = findViewById(R.id.pointsView);

        wrongword = findViewById(R.id.wrongImg);
        correctword = findViewById(R.id.correctImg);
        correctword.setVisibility(View.GONE);
        wrongword.setVisibility(View.GONE);

        wordFrame.setText(logic.getSynligtOrd());
        guessBtn.setOnClickListener(this);
        backBtn.setOnClickListener(this);
        guessFrame.setText("Guess the Word");
        pointsFrame.setText("Points: ");

        //Brugt til AsyncTask metoden
        new wordsFromSheets(this, logic).execute();
        loadScore();
    }

    // Jeg sad i noget tid og prøvede at lave AsyncTask opgaven men uden held.
    // Men en af hjælpelærene udgav en metode på discord som endte med at ligne meget hvad han havde lavet.
    // Nedenstående kode er taget der fra. Jeg startede med at prøve at gøre det selv.
    // Men endte til sidst med at gøre det som den udgivne metode.
    //------------------------------------------------------------------
    private void setWord() {
        wordFrame.setText(logic.getSynligtOrd());
    }

    @SuppressLint("SetTextI18n")
    private void showErrorMessage() {
        wordFrame.setText("Could not retrieve words");
    }
    @SuppressLint("SetTextI18n")
    private void showWaitMessage(){ wordFrame.setText("Please wait...");}

    private static class wordsFromSheets extends AsyncTask<String, String, Exception> {

        private final WeakReference<GamePage> activityRef;
        private final GameLogic logic;

        private wordsFromSheets(GamePage activity, GameLogic logic) {
            this.activityRef = new WeakReference<>(activity);
            this.logic = logic;
        }

        @Override
        protected Exception doInBackground(String... strings) {
            try {
                activityRef.get().showWaitMessage();
                logic.hentOrdFraRegneark("2");
                logic.nulstil();
            } catch (Exception e) {
                return e;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Exception e) {
            if (activityRef.get() != null) {
                if (e != null) {
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

        if (v == guessBtn) {
             String guess = editGuess.getText().toString();
             guessChecker = guess;

            if (guess.length() != 1) {
                editGuess.setError("1 letter at a time");
                return;
            }

            editGuess.setError(null);
            logic.gætBogstav(guess);
            if (logic.erSidsteBogstavKorrekt() && !guessLetters.contains(guessChecker)) {
                crossFadeAnimation(correctword, correctword);
            } else if (!logic.erSidsteBogstavKorrekt() && !guessLetters.contains(guessChecker)) {
                crossFadeAnimation(wrongword, wrongword);
            }
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

            calculatePoints(score);
            updateScreen();


        } else if (v == backBtn) {
            Intent intent = new Intent(this, StartPage.class);
            startActivity(intent);
        }
    }

    private void saveScore(){
        SharedPreferences sharedPreferences = getSharedPreferences("highscore", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(hs);
        String json2 = gson.toJson(date);
        editor.putString("high", json);
        editor.putString("date", json2);
        editor.apply();
    }

    private void loadScore(){
        SharedPreferences sharedPreferences = getSharedPreferences("highscore", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("high",null);
        String json2 = sharedPreferences.getString("date", null);
        Type type = new TypeToken<ArrayList<String>>(){}.getType();
        hs = gson.fromJson(json, type);
        date = gson.fromJson(json2, type);

    }

    private int calculatePoints(int points){
        if(logic.erSidsteBogstavKorrekt() && !guessLetters.contains(guessChecker)){
            score = points + 10;
        } else if (!logic.erSidsteBogstavKorrekt() && !guessLetters.contains(guessChecker)){
            score = points - 5;
        }
        guessLetters.add(guessChecker);
        scoreStr = Integer.toString(score);
        pointsFrame.setText("Points: " + scoreStr);
        return score;
    }

    private void setDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        dateStr = sdf.format(new Date());
    }

    private void updateScreen() {
        wordFrame.setText(logic.getSynligtOrd());
        guessFrame.setText("\n\nDu har " + logic.getAntalForkerteBogstaver() + " forkerte:" + logic.getBrugteBogstaver());

        if (logic.erSpilletVundet()) {
            setDate();
            date.add(dateStr);
            hs.add(scoreStr);
            saveScore();
            Intent intent = new Intent(this, WonPage.class);
            intent.putExtra("gameWord", logic.getOrdet());
            intent.putExtra("Guesses", logic.getAntalForkerteBogstaver() + logic.getOrdet().length());
            startActivity(intent);
        }
        if (logic.erSpilletTabt()) {
            setDate();
            date.add(dateStr);
            hs.add(scoreStr);
            saveScore();
            Intent intent = new Intent(this, LostPage.class);
            intent.putExtra("gameWord", logic.getOrdet());
            startActivity(intent);
        }
    }

    // Ved denne animation er der taget meget inspiration fra dette eksempel fra stackoverflow:
    // https://stackoverflow.com/a/33265477/11614540
    // Der er ændret meget lidt til at få en flowy fade-in fade-out
    //------------------------------------------------------------------
    private void crossFadeAnimation(final View fadeInTarget, final View fadeOutTarget) {
        AnimatorSet mAnimationSet = new AnimatorSet();
        ObjectAnimator fadeOut = ObjectAnimator.ofFloat(fadeOutTarget, View.ALPHA, 1f, 0f);
        fadeOut.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                fadeOutTarget.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
        fadeOut.setInterpolator(new LinearInterpolator());

        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(fadeInTarget, View.ALPHA, 0f, 1f);
        fadeIn.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                fadeInTarget.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }

        });
        fadeIn.setInterpolator(new LinearInterpolator());
        mAnimationSet.setDuration((long) 500);
        //Dette er ændret så i stedet for at køre animationen samtidig sker det efter hinanden
        mAnimationSet.playSequentially(fadeIn,fadeOut);
        mAnimationSet.start();
    }
    //------------------------------------------------------------------

}


