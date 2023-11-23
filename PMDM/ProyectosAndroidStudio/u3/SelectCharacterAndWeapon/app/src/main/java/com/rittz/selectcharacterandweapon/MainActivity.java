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
    static final int PLAYER1_TAG_NUMBER = 0, PLAYER2_TAG_NUMBER = 1;
    static final String INFO_PLAYER_CALLING = "MainActivity.playerCalling";
    ImageButton imageButtonCharP1, imageButtonCharP2, imageButtonWeapP1, imageButtonWeapP2;
    final static String INFO_SELECTED_CHAR_IMAGES = "MainActivity.selectedCharacterImages";
    ArrayList<String> selectedCharacterImages = new ArrayList<>();
    final static String INFO_SELECTED_WEAP_IMAGES = "MainActivity.selectedWeaponImages";
    ArrayList<String> selectedWeaponImages = new ArrayList<>();
    int selectedPlayer = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selectedCharacterImages.add(PLAYER1_TAG_NUMBER, null);
        selectedCharacterImages.add(PLAYER2_TAG_NUMBER, null);
        selectedWeaponImages.add(PLAYER1_TAG_NUMBER, null);
        selectedWeaponImages.add(PLAYER2_TAG_NUMBER, null);

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

        ActivityResultLauncher<Intent> launcherActivity3 = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode() == Activity3.RESULT_OK){
                    Intent data = result.getData();
                    int selectedPlayer = data.getIntExtra(Activity3.INFO_SELECTED_PLAYER, Activity3.DEFAULT_VALUE);
                    selectedWeaponImages = data.getStringArrayListExtra(INFO_SELECTED_WEAP_IMAGES);
                    String selectedWeapon = selectedWeaponImages.get(selectedPlayer);
                    loadWeaponImage(selectedWeapon, selectedPlayer, selectedWeaponImages);
                } else if (result.getResultCode() == Activity3.RESULT_FIRST_USER) {
                    Intent data = new Intent();
                    data.getIntExtra(Activity3.INFO_SELECTED_PLAYER, Activity3.DEFAULT_VALUE);
                    resetInitialWeaponImage(selectedPlayer, selectedWeaponImages);
                }
            }
        });

        ActivityResultLauncher<Intent> launcherActivity2 = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode() == Activity2.RESULT_OK){
                    Intent data = result.getData();
                    int selectedPlayer = data.getIntExtra(Activity2.INFO_SELECTED_PLAYER, Activity2.DEFAULT_VALUE);
                    selectedCharacterImages = data.getStringArrayListExtra(INFO_SELECTED_CHAR_IMAGES);
                    String selectedCharacter = selectedCharacterImages.get(selectedPlayer);
                    loadCharacterImage(selectedCharacter, selectedPlayer, selectedCharacterImages);
                } else if(result.getResultCode() == Activity2.RESULT_FIRST_USER) {
                    Intent data = result.getData();
                    int selectedPlayer = data.getIntExtra(Activity2.INFO_SELECTED_PLAYER, Activity2.DEFAULT_VALUE);
                    resetInitialCharacterImage(selectedPlayer, selectedCharacterImages);
                } else {
                    //otro error
                }
            }
        });

        View.OnClickListener handler = view -> {

            if(view.getId() == R.id.imageButtonCharP1 || view.getId() == R.id.imageButtonWeapP1){
                selectedPlayer = PLAYER1_TAG_NUMBER;
            } else if(view.getId() == R.id.imageButtonCharP2 || view.getId() == R.id.imageButtonWeapP2){
                selectedPlayer = PLAYER2_TAG_NUMBER;
            }

            if(view.getTag() == SelectionType.CHARACTER){
                Intent intent = new Intent(MainActivity.this, Activity2.class);
                intent.putExtra(MainActivity.INFO_PLAYER_CALLING, selectedPlayer);
                intent.putExtra(INFO_SELECTED_CHAR_IMAGES, selectedCharacterImages);
                launcherActivity2.launch(intent);
            } else if (view.getTag() == SelectionType.WEAPON){
                Intent intent = new Intent(MainActivity.this, Activity3.class);
                intent.putExtra(MainActivity.INFO_PLAYER_CALLING, selectedPlayer);
                intent.putExtra(INFO_SELECTED_WEAP_IMAGES, selectedWeaponImages);
                launcherActivity3.launch(intent);
            }
        };

        imageButtonCharP1.setOnClickListener(handler);
        imageButtonCharP2.setOnClickListener(handler);
        imageButtonWeapP1.setOnClickListener(handler);
        imageButtonWeapP2.setOnClickListener(handler);

    }

    private void resetInitialCharacterImage(int selectedPlayer, ArrayList<String> selectedCharacterImages) {
        if(selectedPlayer == PLAYER1_TAG_NUMBER){
            imageButtonCharP1.setImageResource(R.drawable.character);
            selectedCharacterImages.set(PLAYER1_TAG_NUMBER, null);
        } else if(selectedPlayer == PLAYER2_TAG_NUMBER){
            imageButtonCharP2.setImageResource(R.drawable.character);
            selectedCharacterImages.set(PLAYER2_TAG_NUMBER, null);
        }
    }

    private void resetInitialWeaponImage(int selectedPlayer, ArrayList<String> selectedWeaponImages) {
        if(selectedPlayer == PLAYER1_TAG_NUMBER){
            imageButtonWeapP1.setImageResource(R.drawable.weapon);
            selectedWeaponImages.set(PLAYER1_TAG_NUMBER, null);
        } else if (selectedPlayer == PLAYER2_TAG_NUMBER) {
            imageButtonWeapP2.setImageResource(R.drawable.weapon);
            selectedWeaponImages.set(PLAYER2_TAG_NUMBER, null);
        }
    }

    private void loadCharacterImage(String selectedCharacter, int selectedPlayer, ArrayList<String> selectedCharacterImages) {
        int selectedDrawable;
        Activity2.PlayableCharacter enumCharacter = Activity2.PlayableCharacter.valueOf(selectedCharacter);
        switch(enumCharacter){
            case HERO:
                selectedDrawable = R.drawable.heroe;
                break;
            case MAGE:
                selectedDrawable = R.drawable.mage;
                break;
            case ELF:
                selectedDrawable = R.drawable.elf;
                break;
            case ZOMBIE:
                selectedDrawable = R.drawable.zombie;
                break;
            default:
                selectedDrawable = -1;
                break;
        }
        if(selectedPlayer == PLAYER1_TAG_NUMBER){
            imageButtonCharP1.setImageResource(selectedDrawable);
        }
        if(selectedPlayer == PLAYER2_TAG_NUMBER){
            imageButtonCharP2.setImageResource(selectedDrawable);
        }
    }

    private void loadWeaponImage(String selectedWeapon, int selectedPlayer, ArrayList<String> selectedWeaponImages) {
        int selectedDrawable;
        Activity3.Weapon enumCharacter = Activity3.Weapon.valueOf(selectedWeapon);
        switch(enumCharacter){
            case BLASTER:
                selectedDrawable = R.drawable.blaster;
                break;
            case TRIDENT:
                selectedDrawable = R.drawable.trident;
                break;
            case STAFF:
                selectedDrawable = R.drawable.staff;
                break;
            case SHURIKEN:
                selectedDrawable = R.drawable.shuriken;
                break;
            default:
                selectedDrawable = -1;
                break;
        }
        if(selectedPlayer == PLAYER1_TAG_NUMBER){
            imageButtonWeapP1.setImageResource(selectedDrawable);
        }
        if(selectedPlayer == PLAYER2_TAG_NUMBER){
            imageButtonWeapP2.setImageResource(selectedDrawable);
        }
    }
}