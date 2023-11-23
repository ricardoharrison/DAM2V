package com.rittz.fourmonsters;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentMonster#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentMonster extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static final String[] NAMES = {"Walter", "Jesse", "Gus", "Mike", "Skylar", "Walter Jr.", "Mike", "Saul"};
    private static int MAX_NUMBER_OF_LIMBS = 20, MAX_COLOR_RANGE = 256;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentMonster() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentMonster.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentMonster newInstance(String param1, String param2) {
        FragmentMonster fragment = new FragmentMonster();
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

    TextView textViewMonster;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_monster, container, false);

        textViewMonster = layout.findViewById(R.id.textViewMonster);

        textViewMonster.setOnClickListener(view -> {
            Monster monster = new Monster(generateRandomMonsterName(NAMES), generateRandomNumberOfLimbs(), generateRandomColor());
            textViewMonster.setBackgroundColor(monster.getRecommendedBackgroundColor());
            textViewMonster.setTextColor(monster.getColor());
            textViewMonster.setText(monster.toString());


        });

        return layout;
    }

    private String generateRandomMonsterName(String[] names) {
        int randomPositionWithinArray = (int)(Math.random() * names.length);
        return names[randomPositionWithinArray];
    }

    private int generateRandomNumberOfLimbs() {
        return (int)(Math.random() * MAX_NUMBER_OF_LIMBS + 1);
    }

    private int generateRandomColor() {
        int randomR = (int)(Math.random() * MAX_COLOR_RANGE);
        int randomG = (int)(Math.random() * MAX_COLOR_RANGE);
        int randomB = (int)(Math.random() * MAX_COLOR_RANGE);
        return Color.rgb(randomR, randomG, randomB);
    }
}