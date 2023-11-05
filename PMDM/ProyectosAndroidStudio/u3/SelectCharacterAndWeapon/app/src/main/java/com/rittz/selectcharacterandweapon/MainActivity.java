package com.rittz.selectcharacterandweapon;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;

enum SelectionType {
    CHARACTER, WEAPON;
}
public class MainActivity extends AppCompatActivity {
    static final int TOTAL_PLAYERS = 2;
    static final int PLAYER1_TAG_NUMBER = 0, PLAYER2_TAG_NUMBER = 1;
    static final String INFO_PLAYER_CALLING = "MainActivity.playerCalling";
    ImageButton imageButtonCharP1, imageButtonCharP2, imageButtonWeapP1, imageButtonWeapP2;

    final static String INFO_SELECTED_CHAR_IMAGES = "MainActivity.selectedCharacterImages";
    ArrayList<Integer> selectedCharacterImages = new ArrayList<>();
    final static String INFO_SELECTED_WEAP_IMAGES = "MainActivity.selectedWeaponImages";
    ArrayList<Integer> selectedWeaponImages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selectedCharacterImages.add(PLAYER1_TAG_NUMBER, null);
        selectedCharacterImages.add(PLAYER1_TAG_NUMBER, null);

        imageButtonCharP1 = findViewById(R.id.imageButtonCharP1);
        imageButtonCharP2 = findViewById(R.id.imageButtonCharP2);
        imageButtonWeapP1 = findViewById(R.id.imageButtonWeapP1);
        imageButtonWeapP2 = findViewById(R.id.imageButtonWeapP2);

        imageButtonCharP1.setImageResource(R.drawable.character);
        imageButtonCharP2.setImageResource(R.drawable.character);
        imageButtonWeapP1.setImageResource(R.drawable.weapon);
        imageButtonWeapP2.setImageResource(R.drawable.weapon);

        imageButtonCharP1.setTag(SelectionType.CHARACTER);
        imageButtonCharP2.setTag(SelectionType.CHARACTER);
        imageButtonWeapP1.setTag(SelectionType.WEAPON);
        imageButtonWeapP2.setTag(SelectionType.WEAPON);

        ActivityResultLauncher<Intent> launcherActivity2 = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode() == Activity2.RESULT_OK){
                    Intent data = result.getData();
                    //[ViewVaribaleName].setText(data.getStringExtra([ActivityName].[CONSTANT]));
                } if (result.getResultCode() == Activity2.RESULT_CANCELED){

                } else {
                    //otro error
                }

            }
        });

        ActivityResultLauncher<Intent> launcherActivity3= registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode() == Activity3.RESULT_OK){
                    Intent data = result.getData();
                    data.putIntegerArrayListExtra(INFO_SELECTED_WEAP_IMAGES, selectedWeaponImages);
                    //[ViewVaribaleName].setText(data.getStringExtra([ActivityName].[CONSTANT]));
                } if (result.getResultCode() == Activity3.RESULT_CANCELED){

                } else {
                    //otro error
                }

            }
        });

        View.OnClickListener handler = view -> {
            if(view.getTag() == SelectionType.CHARACTER){
                Intent intent = new Intent(MainActivity.this, Activity2.class);
                intent.putExtra(INFO_SELECTED_CHAR_IMAGES, selectedCharacterImages);
                intent.putExtra(INFO_PLAYER_CALLING, PLAYER1_TAG_NUMBER);
                launcherActivity2.launch(intent);
            } else if (view.getTag() == SelectionType.WEAPON){
                Intent intent = new Intent(MainActivity.this, Activity3.class);
                intent.putExtra(INFO_SELECTED_WEAP_IMAGES, selectedWeaponImages);
                intent.putExtra(INFO_PLAYER_CALLING, PLAYER2_TAG_NUMBER);
                launcherActivity3.launch(intent);
            }
        };

        imageButtonCharP1.setOnClickListener(handler);
        imageButtonCharP2.setOnClickListener(handler);
        imageButtonWeapP1.setOnClickListener(handler);
        imageButtonWeapP2.setOnClickListener(handler);

    }
}