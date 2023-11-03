package com.rittz.letteranalysis;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class Activity2 extends AppCompatActivity {
    private static final Integer INITIAL_VALUE = 1;
    static final String INFO_ARRAY = "Activity2.myList";
    private static final int TOTAL_TOP_LETTERS = 3;
    Button buttonReturn;
    TextView textViewDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        buttonReturn = findViewById(R.id.buttonReturn);
        textViewDisplay = findViewById(R.id.textViewDisplay);

        Intent intent = getIntent();
        String receivedText = intent.getStringExtra(MainActivity.INFO_TEXT);

        char[] receivedCharacters = receivedText.toCharArray();

        Map<Character, Integer> myMap = new LinkedHashMap<>();

        for(Character character : receivedCharacters){
            if(Character.isAlphabetic(character)){
                if(myMap.containsKey(character)){
                    int value = myMap.getOrDefault(character, INITIAL_VALUE);
                    value++;
                    myMap.put(character, value);
                } else {
                    myMap.put(character, INITIAL_VALUE);
                }
            }
        }

        String displayedText = "The text contains the following letters:";

        for(Map.Entry entry : myMap.entrySet()){
            displayedText = displayedText + "\nLetter " + myMap.get(entry) + " was found " + myMap.getOrDefault(entry, INITIAL_VALUE) + " time/s";
        }

        textViewDisplay.setText(displayedText);

        buttonReturn.setOnClickListener(view -> {
            Intent data = new Intent(this, MainActivity.class);
            ArrayList<Map.Entry<Character, Integer>> myList = new ArrayList<>(myMap.entrySet());
            myList.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));
            String sentString = "The top " + TOTAL_TOP_LETTERS + " letters are: ";
            for(int i = 0; i > TOTAL_TOP_LETTERS; i++){
                sentString = sentString + "/nLetter " + myList.get(i).getKey() + " was found " + myList.get(i).getValue() + " times.";
            }
        });

    }
}