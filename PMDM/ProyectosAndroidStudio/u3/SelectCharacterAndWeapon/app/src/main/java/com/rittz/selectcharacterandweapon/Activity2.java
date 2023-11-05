package com.rittz.selectcharacterandweapon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class Activity2 extends AppCompatActivity {
    static final int DEFAULT_VALUE = -1;
    static final String INFO_SELECTED_CHAR = "Activity2.selectedChar";
    ImageButton imageButtonCharSel1, imageButtonCharSel2, imageButtonCharSel3, imageButtonCharSel4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        imageButtonCharSel1 = findViewById(R.id.imageButtonCharSel1);
        imageButtonCharSel2 = findViewById(R.id.imageButtonCharSel2);
        imageButtonCharSel3 = findViewById(R.id.imageButtonCharSel3);
        imageButtonCharSel4 = findViewById(R.id.imageButtonCharSel4);

        ArrayList<ImageButton> imageButtonsList = new ArrayList<>();
        imageButtonsList.add(imageButtonCharSel1);
        imageButtonsList.add(imageButtonCharSel2);
        imageButtonsList.add(imageButtonCharSel3);
        imageButtonsList.add(imageButtonCharSel4);


        imageButtonCharSel1.setImageResource(R.drawable.heroe);
        imageButtonCharSel2.setImageResource(R.drawable.mage);
        imageButtonCharSel3.setImageResource(R.drawable.elf);
        imageButtonCharSel4.setImageResource(R.drawable.zombie);

        Intent intent = getIntent();
        ArrayList<Integer> selectedCharacterImages = intent.getIntegerArrayListExtra(MainActivity.INFO_SELECTED_CHAR_IMAGES);
        Integer selectedPlayer = intent.getIntExtra(MainActivity.INFO_PLAYER_CALLING, DEFAULT_VALUE);

        selectedCharacterImages.set(selectedPlayer, null);

        for(int i = 0; i < selectedCharacterImages.size(); i++){
            if(i == selectedPlayer){
                continue;
            } else {
                imageButtonsList.get(i).setClickable(false);
            }
        }


        View.OnClickListener handler = view -> {
            Toast.makeText(Activity2.this, "Clicked", Toast.LENGTH_LONG).show();
            if(!selectedCharacterImages.contains(view.getId())){
                selectedCharacterImages.set(selectedPlayer, view.getId());
                Intent data = new Intent();
                data.putExtra(INFO_SELECTED_CHAR, view.getId());
                setResult(Activity2.RESULT_OK);
                finish();
            }
        };


    }
}