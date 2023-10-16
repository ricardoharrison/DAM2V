package com.rittz.rockpaperscissors;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final int INITIAL_SCORE = 0;
    Button buttonRestart;
    ImageView imageViewRock, imageViewPaper, imageViewScissors, imageViewChoiceCpu;
    TextView textViewScoreUser, textViewScoreCpu, textViewCpuChoice, textViewFinalMsg;

    public enum Selection{
        ROCK, PAPER, SCISSORS;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonRestart = findViewById(R.id.buttonRestart);

        imageViewRock = findViewById(R.id.imageViewRock);
        imageViewPaper = findViewById(R.id.imageViewPaper);
        imageViewScissors = findViewById(R.id.imageViewScissors);
        imageViewChoiceCpu = findViewById(R.id.imageViewCpuChoice);

        textViewScoreUser = findViewById(R.id.textViewScoreUser);
        textViewScoreCpu = findViewById(R.id.textViewScoreCpu);
        textViewCpuChoice = findViewById(R.id.textViewCpuChoice);
        textViewFinalMsg = findViewById(R.id.textViewFinalMsg);

        textViewCpuChoice.setVisibility(View.GONE);

        textViewScoreUser.setText(INITIAL_SCORE);
        textViewScoreCpu.setText(INITIAL_SCORE);

        View.OnClickListener RockPaperScissorsHandler = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        }

        imageViewRock.setOnClickListener(new V);






    }
}