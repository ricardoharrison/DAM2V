package com.example.thelordoftheringscharacterselector;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class CharacterSelectorActivity extends AppCompatActivity {

    ImageView ivFrodo, ivGandalf, ivLegolas, ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_selector);

        ivFrodo = findViewById(R.id.ivFrodo);
        ivGandalf = findViewById(R.id.ivGandalf);
        ivLegolas = findViewById(R.id.ivLegolas);
        ivBack = findViewById(R.id.ivBack);

        Intent intent = getIntent();
        String playerTag = intent.getStringExtra(MainActivity.PLAYER_TAG);

        ivFrodo.setOnClickListener((v -> {
            handleCharacterSelection(MainActivity.PLAYER_TAG, playerTag ,
                                        MainActivity.CHARACTER_TAG, LOTRCharacters.FRODO.toString());
        }));

        ivGandalf.setOnClickListener(v -> {
            handleCharacterSelection(MainActivity.PLAYER_TAG, playerTag,
                                        MainActivity.CHARACTER_TAG, LOTRCharacters.GANDALF.toString());
        });

        ivLegolas.setOnClickListener(v -> {
            handleCharacterSelection(MainActivity.PLAYER_TAG, playerTag,
                                        MainActivity.CHARACTER_TAG,LOTRCharacters.LEGOLAS.toString());
        });

        ivBack.setOnClickListener(v -> {
            finish();
        });



    }

    public void handleCharacterSelection(String PLAYER_TAG, String playerTag,
                                        String CHARACTER_TAG, String charName){
        Intent intent = new Intent();
        intent.putExtra(MainActivity.PLAYER_TAG, playerTag);
        intent.putExtra(MainActivity.CHARACTER_TAG, charName);
        setResult(RESULT_OK, intent);
        finish();

    }
}