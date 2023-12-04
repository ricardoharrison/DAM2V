package com.example.thelordoftheringscharacterselector;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.result.ActivityResultCallback;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView ivTitle, ivChar1, ivChar2, ivWeapon1, ivWeapon2;

    public static final String PLAYER_TAG = "PLAYER_TAG";
    public static final String CHARACTER_TAG = "CHARACTER_TAG";
    public static final String WEAPON_TAG = "WEAPON_TAG";

    public static final String PLAYER_1 = "PLAYER_1";
    public static final String PLAYER_2 = "PLAYER_2";


    public String selectedPlayer = "";
    public String getSelectedItem = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivTitle = findViewById(R.id.ivTitle);
        ivChar1 = findViewById(R.id.ivChar1);
        ivChar2 = findViewById(R.id.ivChar2);
        ivWeapon1 = findViewById(R.id.ivWeapon1);
        ivWeapon2 = findViewById(R.id.ivWeapon2);
        ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {

            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode() == CharacterSelectorActivity.RESULT_OK){
                    Intent data = result.getData();
                    selectedPlayer = data.getStringExtra(PLAYER_TAG);

                    try {
                        getSelectedItem = data.getStringExtra(CHARACTER_TAG);
//                        LOTRCharacters c = LOTRCharacters.valueOf(getSelectedItem);
                        if(selectedPlayer.equals(PLAYER_1)){
                            if (getSelectedItem.equals(LOTRCharacters.FRODO.toString())){
                                ivChar1.setImageResource(R.drawable.frodo);
                            } else if (getSelectedItem.equals(LOTRCharacters.GANDALF.toString())) {
                                ivChar1.setImageResource(R.drawable.gandalf);
                            } else if (getSelectedItem.equals(LOTRCharacters.LEGOLAS.toString())) {
                                ivChar1.setImageResource(R.drawable.legolas);
                            }
                        } else if (selectedPlayer.equals(PLAYER_2)) {
                            if (getSelectedItem.equals(LOTRCharacters.FRODO.toString())){
                                ivChar2.setImageResource(R.drawable.frodo);
                            } else if (getSelectedItem.equals(LOTRCharacters.GANDALF.toString())) {
                                ivChar2.setImageResource(R.drawable.gandalf);
                            } else if (getSelectedItem.equals(LOTRCharacters.LEGOLAS.toString())) {
                                ivChar2.setImageResource(R.drawable.legolas);
                            }
                        }
                    } catch (Exception e){}
                } else if (result.getResultCode() == CharacterSelectorActivity.RESULT_CANCELED){
                } else {
                    //otro error
                }
            }
        });
        ActivityResultLauncher<Intent> launcher2 = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            public void onActivityResult(ActivityResult result) {
            if(result.getResultCode() == WeaponSelectorActivity.RESULT_OK){
                Intent data = result.getData();
                selectedPlayer = data.getStringExtra(PLAYER_TAG);
                try {
                    getSelectedItem = data.getStringExtra(WEAPON_TAG);
                    if(selectedPlayer.equals(PLAYER_1)){
                        if (getSelectedItem.equals(LOTRWeapons.SWORD.toString())){
                            ivWeapon1.setImageResource(R.drawable.sword);
                        } else if (getSelectedItem.equals(LOTRWeapons.RING.toString())) {
                            ivWeapon1.setImageResource(R.drawable.ring);
                        } else if (getSelectedItem.equals(LOTRWeapons.BOW.toString())) {
                            ivWeapon1.setImageResource(R.drawable.bow);
                        } else {
                            Log.e("MainActivity", "Error: WEAPON_TAG not found in data extras");
                        }
                    } else if (selectedPlayer.equals(PLAYER_2)) {
                        if (getSelectedItem.equals(LOTRWeapons.SWORD.toString())){
                            ivWeapon2.setImageResource(R.drawable.sword);
                        } else if (getSelectedItem.equals(LOTRWeapons.RING.toString())) {
                            ivWeapon2.setImageResource(R.drawable.ring);
                        } else if (getSelectedItem.equals(LOTRWeapons.BOW.toString())) {
                            ivWeapon2.setImageResource(R.drawable.bow);
                        }else {
                            Log.e("MainActivity", "Error: WEAPON_TAG not found in data extras");
                        }
                    }
                } catch (Exception e){
                    Log.e("MainActivity", "Error handling weapon selection: " + e.getMessage());

//                            Toast.makeText(MainActivity.this, "Couldnt reach the weapon", Toast.LENGTH_LONG).show();

                }
                //[ViewVaribaleName].setText(data.getStringExtra([Activity2].[CONSTANT]));
                // Activity2 es el nombre que se le ha dado a la actividad a la que se envía (de la que quieres recibir info)
            } else if (result.getResultCode() == WeaponSelectorActivity.RESULT_CANCELED ){
            } else {
                //otro error
            }
        }
    });


        ivChar1.setOnClickListener((v) ->{
            charSelectorLauncher(PLAYER_1, launcher);
        });

        ivChar2.setOnClickListener((v) ->{
            charSelectorLauncher(PLAYER_2, launcher);
        });

        ivWeapon1.setOnClickListener(v -> {
            weaponSelectorLauncher(PLAYER_1, launcher2);
        });

        ivWeapon2.setOnClickListener((v) ->{
            weaponSelectorLauncher(PLAYER_2, launcher2);
        });

        }

        public void charSelectorLauncher(String playerTag, ActivityResultLauncher<Intent> launcher){
            Intent intent = new Intent(this, CharacterSelectorActivity.class);
            intent.putExtra(PLAYER_TAG, playerTag);
            launcher.launch(intent);
        }

        public void weaponSelectorLauncher(String playerTag, ActivityResultLauncher<Intent> launcher2){
            Intent intent = new Intent(this, WeaponSelectorActivity.class);
            intent.putExtra(PLAYER_TAG, playerTag);
            launcher2.launch(intent);
        }


}