package com.rittz.pingpong;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements FragmentPong.OnGameEndListener {

    FragmentPong fragment1, fragment2, fragment3, fragment4;

    final int NUMBER_OF_FRAGMENTS = 4;

    final String COURT1_NAME = "Court 1", COURT2_NAME = "Court 2", COURT3_NAME = "Court 3", COURT4_NAME = "Court 4";

    TextView textViewFinalScores;

    int unfinishedMatches = NUMBER_OF_FRAGMENTS;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment1 = (FragmentPong) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView1);
        fragment2 = (FragmentPong) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView2);
        fragment3 = (FragmentPong) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView3);
        fragment4 = (FragmentPong) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView4);

        textViewFinalScores = findViewById(R.id.textViewFinalScores);
        textViewFinalScores.setText("RESULTS:\n\n");
        textViewFinalScores.setVisibility(View.GONE);

        fragment1.setCourtName(COURT1_NAME);
        fragment2.setCourtName(COURT2_NAME);
        fragment3.setCourtName(COURT3_NAME);
        fragment4.setCourtName(COURT4_NAME);

    }

    @Override
    public void onGameEnd(CharSequence score) {
        textViewFinalScores.append(score + "\n");
        unfinishedMatches -= 1;
        if(unfinishedMatches == 0){
            textViewFinalScores.setVisibility(View.VISIBLE);
        }
    }
}