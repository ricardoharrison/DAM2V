package com.rittz.rockpaperscissors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class MainActivity extends AppCompatActivity {

    private final int INITIAL_SCORE = 0, WINNING_SCORE = 3;
    private final float SIZE_BIG = 24, SIZE_MEDIUM = 16;
    final long WIN_VIBRATION_TIME = 500;
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

    public void triggerRestartActions(){
        textViewCpuChoice.setVisibility(View.GONE);
        imageViewChoiceCpu.setVisibility(View.GONE);
        textViewFinalMsg.setText(" ");
        textViewScoreUser.setText(String.valueOf(INITIAL_SCORE));
        textViewScoreCpu.setText(String.valueOf(INITIAL_SCORE));
        textViewFinalMsg.setTextSize(SIZE_MEDIUM);
        gameEnded = false;
    }

    public void showConfirmationDialog(){
        final String ALERT_DIALOG_TITLE = getResources().getString(R.string.alert_dialog_title);
        final String ALERT_DIALOG_MSG = getResources().getString(R.string.alert_dialog_msg);
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(ALERT_DIALOG_TITLE)
                .setMessage(ALERT_DIALOG_MSG)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        triggerRestartActions();
                        dialog.cancel();
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .show();
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

        //enum gets linked to "tag" property
        imageViewRock.setTag(Selection.ROCK);
        imageViewPaper.setTag(Selection.PAPER);
        imageViewScissors.setTag(Selection.SCISSORS);

        //image resources
        imageViewRock.setImageResource(R.drawable.rock);
        imageViewPaper.setImageResource(R.drawable.paper);
        imageViewScissors.setImageResource(R.drawable.scissors);
        View.OnClickListener rockPaperScissorsHandler = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!gameEnded) {

                    //creating the enums
                    Selection userSelection = null, cpuSelection = null;

                    //rolling for the CPU

                    int randomNumber = (int) (Math.random() * Selection.values().length + 1);
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
                    imageViewChoiceCpu.setVisibility(View.VISIBLE);

                    //assigning the selection made by the user
                    Selection selection = Selection.valueOf(view.getTag().toString());
                    userSelection = selection;
                    Integer result = null;
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
                    if (Integer.parseInt(textViewScoreUser.getText().toString()) >= WINNING_SCORE) {
                        gameEnded = true;
                        textViewFinalMsg.setTextSize(SIZE_BIG);
                        textViewFinalMsg.setText(R.string.msg_won);

                        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

                        // Check if the device has a vibrator
                        if (vibrator.hasVibrator()) {
                            // Create a VibrationEffect (for Android API 26 and later)
                            VibrationEffect vibrationEffect = VibrationEffect.createOneShot(WIN_VIBRATION_TIME, VibrationEffect.DEFAULT_AMPLITUDE);

                            // Vibrate with the created VibrationEffect
                            vibrator.vibrate(vibrationEffect);
                        }
                    }

                    else if(Integer.parseInt(textViewScoreCpu.getText().toString()) >= WINNING_SCORE) {
                        gameEnded = true;
                        textViewFinalMsg.setTextSize(SIZE_BIG);
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

                if (!(Integer.parseInt(textViewScoreUser.getText().toString()) == INITIAL_SCORE &&
                        Integer.parseInt(textViewScoreCpu.getText().toString()) == INITIAL_SCORE) &&
                        !(Integer.parseInt(textViewScoreUser.getText().toString()) == WINNING_SCORE ||
                                Integer.parseInt(textViewScoreCpu.getText().toString()) == WINNING_SCORE)) {
                    showConfirmationDialog();
                } else {
                    triggerRestartActions();
                }
            }
        });
    }
}