package com.rittz.pingpong;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentPong#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentPong extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final int INITIAL_SCORE = 0, MAX_SCORE = 21;
    private static final int MAX_COLOR_RANGE = 200;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentPong() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentPong.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentPong newInstance(String param1, String param2) {
        FragmentPong fragment = new FragmentPong();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    TextView textViewResult;
    Button buttonRed, buttonBlue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_pong, container, false);

        textViewResult = layout.findViewById(R.id.textViewResult);
        textViewResult.setBackgroundColor(generateRandomColor());

        buttonRed = layout.findViewById(R.id.buttonTeamRed);
        buttonBlue = layout.findViewById(R.id.buttonTeamBlue);

        buttonRed.setText(String.valueOf(INITIAL_SCORE));
        buttonBlue.setText(String.valueOf(INITIAL_SCORE));


        View.OnClickListener handler = view -> {
            Button button = (Button) view;
            String currentScore = button.getText().toString();
            int currenIntScore = Integer.parseInt(currentScore);
            currenIntScore += 1;
            button.setText(String.valueOf(currenIntScore));
            if(currenIntScore >= MAX_SCORE){
                textViewResult.setText(String.format(getResources().getString(R.string.final_msg), buttonRed.getText(), buttonBlue.getText()));
                buttonRed.setVisibility(View.GONE);
                buttonBlue.setVisibility(View.GONE);
                textViewResult.setVisibility(View.VISIBLE);
            }
        };

        buttonRed.setOnClickListener(handler);
        buttonBlue.setOnClickListener(handler);

        return layout;

    }

    private int generateRandomColor() {
        int randomA = (int)(Math.random() * MAX_COLOR_RANGE);
        int randomR = (int)(Math.random() * MAX_COLOR_RANGE);
        int randomG = (int)(Math.random() * MAX_COLOR_RANGE);
        int randomB = (int)(Math.random() * MAX_COLOR_RANGE);

        return Color.argb(randomA, randomR, randomG, randomB);
    }
}