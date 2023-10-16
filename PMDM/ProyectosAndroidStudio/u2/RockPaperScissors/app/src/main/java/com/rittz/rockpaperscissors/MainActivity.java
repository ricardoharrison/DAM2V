package com.rittz.rockpaperscissors;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final int INITIAL_SCORE = 0, WINNING_SCORE = 3;
    private final float SIZE_24 = 24;
    boolean gameEnded = false;
    Button buttonRestart;
    ImageView imageViewRock, imageViewPaper, imageViewScissors, imageViewChoiceCpu;
    TextView textViewScoreUser, textViewScoreCpu, textViewCpuChoice, textViewFinalMsg;

    public enum Selection{
        ROCK, PAPER, SCISSORS;
    }

    public Integer playRockPaperScissors(Selection userSel, Selection cpuSel){
        Integer result = null;
        switch(userSel){
            case ROCK:
                if(cpuSel == Selection.ROCK){
                    result = 0;
                } else if(cpuSel == Selection.PAPER) {
                    result = -1;
                } else if(cpuSel == Selection.SCISSORS){
                    result = 1;
                }
                break;
            case PAPER:
                if(cpuSel == Selection.ROCK){
                    result = 1;
                } else if(cpuSel == Selection.PAPER) {
                    result = 0;
                } else if(cpuSel == Selection.SCISSORS){
                    result = -1;
                }
                break;
            case SCISSORS:
                if(cpuSel == Selection.ROCK){
                    result = -1;
                } else if(cpuSel == Selection.PAPER) {
                    result = 1;
                } else if(cpuSel == Selection.SCISSORS){
                    result = 0;
                }
                break;
            default:
                break;
        }
        return result;
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

        textViewScoreUser.setText(String.valueOf(INITIAL_SCORE));
        textViewScoreCpu.setText(String.valueOf(INITIAL_SCORE));

        //tags del enum
        imageViewRock.setTag(Selection.ROCK);
        imageViewPaper.setTag(Selection.PAPER);
        imageViewScissors.setTag(Selection.SCISSORS);

        View.OnClickListener rockPaperScissorsHandler = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!gameEnded) {

                    //creating the enums
                    Selection userSelection = null, cpuSelection = null;

                    //rolling for the CPU
                    int randomNumber = (int) (Math.random() * 3 + 1);
                    switch (randomNumber) {
                        case 1:
                            cpuSelection = Selection.ROCK;
                            imageViewChoiceCpu.setImageResource(R.drawable.rock);
                            break;
                        case 2:
                            cpuSelection = Selection.PAPER;
                            imageViewChoiceCpu.setImageResource(R.drawable.paper);
                            break;
                        case 3:
                            cpuSelection = Selection.SCISSORS;
                            imageViewChoiceCpu.setImageResource(R.drawable.scissors);
                            break;
                        default:
                            break;
                    }

                    //assigning the selection made by the user
                    Selection selection = Selection.valueOf(view.getTag().toString());
                    Integer result = null;
                    switch (selection) {
                        case ROCK:
                            userSelection = Selection.ROCK;
                            break;
                        case PAPER:
                            userSelection = Selection.PAPER;
                            break;
                        case SCISSORS:
                            userSelection = Selection.SCISSORS;
                            break;
                        default:
                            break;
                    }
                    result = playRockPaperScissors(userSelection, cpuSelection);
                    int currentScore;
                    switch (result) {
                        case 1:
                            currentScore = Integer.parseInt(textViewScoreUser.getText().toString());
                            currentScore++;
                            textViewScoreUser.setText(String.valueOf(currentScore));
                            textViewFinalMsg.setText(R.string.msg_user_scored);
                            break;
                        case -1:
                            currentScore = Integer.parseInt(textViewScoreCpu.getText().toString());
                            currentScore++;
                            textViewScoreCpu.setText(String.valueOf(currentScore));
                            textViewFinalMsg.setText(R.string.msg_cpu_scored);
                            break;
                        case 0:
                            textViewFinalMsg.setText(R.string.msg_tie);
                            break;
                        default:
                            break;
                    }
                    if(Integer.parseInt(textViewScoreUser.getText().toString()) >= WINNING_SCORE){
                        gameEnded = true;
                        textViewFinalMsg.setTextSize(SIZE_24);
                        textViewFinalMsg.setText(R.string.msg_won);
                    } else if(Integer.parseInt(textViewScoreCpu.getText().toString()) >= WINNING_SCORE) {
                        gameEnded = true;
                        textViewFinalMsg.setTextSize(SIZE_24);
                        textViewFinalMsg.setText(R.string.msg_lost);
                    }

                    textViewCpuChoice.setVisibility(View.VISIBLE);
                }
            }
        };

        imageViewRock.setOnClickListener(rockPaperScissorsHandler);
        imageViewPaper.setOnClickListener(rockPaperScissorsHandler);
        imageViewScissors.setOnClickListener(rockPaperScissorsHandler);

        buttonRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textViewCpuChoice.setVisibility(View.GONE);
                textViewScoreUser.setText(String.valueOf(INITIAL_SCORE));
                textViewScoreCpu.setText(String.valueOf(INITIAL_SCORE));
            }
        });
    }
}