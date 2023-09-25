package com.rittz.guessit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    final int MAX_LIVES = 7;
    final int MIN_LIVES = 0;
    int randomNumber = (int)(Math.random()*101);
    int lives = MAX_LIVES;
    boolean gameOver = false;

    Button btGuessIt, btRestart;
    EditText etInsertNumber;
    TextView tvClues, tvLives;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btGuessIt = findViewById(R.id.buttonGuessIt);
        btRestart = findViewById(R.id.buttonRestart);
        etInsertNumber = findViewById(R.id.editTextInsertNumber);
        tvClues = findViewById(R.id.textViewClues);
        tvLives = findViewById(R.id.textViewLives);

        tvLives.setText(getString(R.string.remaining_attempts) + lives);

        btGuessIt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (lives > 0) {
                    lives -= 1;
                }
                tvLives.setText(getString(R.string.remaining_attempts) + lives);  //aquí era necesario getString

                if ((lives <= MIN_LIVES & (randomNumber != Integer.parseInt(etInsertNumber.getText().toString()) ) )
                        || gameOver) {
                    tvClues.setText(R.string.game_over);                         //aquí no lo es, ??
                    gameOver = true;
                } else if (randomNumber > Integer.parseInt(etInsertNumber.getText().toString()) && !gameOver) {
                    tvClues.setText(R.string.higher_clue);
                } else if (randomNumber < Integer.parseInt(etInsertNumber.getText().toString()) && !gameOver) {
                    tvClues.setText(R.string.lower_clue);
                } else if (randomNumber == Integer.parseInt(etInsertNumber.getText().toString()) && !gameOver) {
                    tvClues.setText(R.string.win_message);
                }
            }
        });

        btRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                randomNumber = (int) (Math.random() * 101);
                lives = MAX_LIVES;
                tvClues.setText("");
                tvLives.setText(getString(R.string.remaining_attempts) + lives);
                etInsertNumber.setText("");
                gameOver = false;
            }
        });
    }
}