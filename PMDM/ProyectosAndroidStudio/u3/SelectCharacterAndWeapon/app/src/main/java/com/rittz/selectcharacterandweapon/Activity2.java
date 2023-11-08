package com.rittz.selectcharacterandweapon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;

public class Activity2 extends AppCompatActivity {
    static final int DEFAULT_VALUE = -1;
    static final String INFO_SELECTED_PLAYER = "Activity2.selectedPlayer";
    public enum PlayableCharacter {
        HERO, MAGE, ELF, ZOMBIE;
    }
    ImageButton imageButtonCharSel1, imageButtonCharSel2, imageButtonCharSel3, imageButtonCharSel4;
    Button buttonClear, buttonCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        imageButtonCharSel1 = findViewById(R.id.imageButtonCharSel1);
        imageButtonCharSel2 = findViewById(R.id.imageButtonCharSel2);
        imageButtonCharSel3 = findViewById(R.id.imageButtonCharSel3);
        imageButtonCharSel4 = findViewById(R.id.imageButtonCharSel4);

        buttonClear = findViewById(R.id.buttonClearChar);
        buttonCancel = findViewById(R.id.buttonCancelChar);

        imageButtonCharSel1.setImageResource(R.drawable.heroe);
        imageButtonCharSel2.setImageResource(R.drawable.mage);
        imageButtonCharSel3.setImageResource(R.drawable.elf);
        imageButtonCharSel4.setImageResource(R.drawable.zombie);

        imageButtonCharSel1.setTag(PlayableCharacter.HERO);
        imageButtonCharSel2.setTag(PlayableCharacter.MAGE);
        imageButtonCharSel3.setTag(PlayableCharacter.ELF);
        imageButtonCharSel4.setTag(PlayableCharacter.ZOMBIE);

        Intent intent = getIntent();
        ArrayList<String> selectedCharacterImages = intent.getStringArrayListExtra(MainActivity.INFO_SELECTED_CHAR_IMAGES);
        int selectedPlayer = intent.getIntExtra(MainActivity.INFO_PLAYER_CALLING, DEFAULT_VALUE);

        //set current player selection position to null so it lets choose the same character again
        selectedCharacterImages.set(selectedPlayer, null);

        //adding filters for other players' active selections
        for(String selectedCharacterImage : selectedCharacterImages){
            if(selectedCharacterImage == null){
                continue;
            }
            if(PlayableCharacter.valueOf(selectedCharacterImage) == PlayableCharacter.HERO){
                imageButtonCharSel1.setColorFilter(Color.GRAY);
            } else if (PlayableCharacter.valueOf(selectedCharacterImage) == PlayableCharacter.MAGE){
                imageButtonCharSel2.setColorFilter(Color.GRAY);
            } else if (PlayableCharacter.valueOf(selectedCharacterImage) == PlayableCharacter.ELF){
                imageButtonCharSel3.setColorFilter(Color.GRAY);
            } else if (PlayableCharacter.valueOf(selectedCharacterImage) == PlayableCharacter.ZOMBIE){
                imageButtonCharSel4.setColorFilter(Color.GRAY);
            }
        }

        View.OnClickListener handler = view -> {
            if(!selectedCharacterImages.contains(view.getTag().toString())){
                selectedCharacterImages.set(selectedPlayer, view.getTag().toString());
                Intent data = new Intent();
                data.putExtra(INFO_SELECTED_PLAYER, selectedPlayer);
                data.putExtra(MainActivity.INFO_SELECTED_CHAR_IMAGES, selectedCharacterImages);
                setResult(Activity2.RESULT_OK, data);
                finish();
            }
        };

        imageButtonCharSel1.setOnClickListener(handler);
        imageButtonCharSel2.setOnClickListener(handler);
        imageButtonCharSel3.setOnClickListener(handler);
        imageButtonCharSel4.setOnClickListener(handler);

        buttonClear.setOnClickListener(view -> {
            Intent data = new Intent();
            data.putExtra(INFO_SELECTED_PLAYER, selectedPlayer);
            setResult(Activity2.RESULT_FIRST_USER, data);
            finish();
        });

        buttonCancel.setOnClickListener(view -> {
            setResult(Activity2.RESULT_CANCELED);
            finish();
        });
    }
}