package com.example.thelordoftheringscharacterselector;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;

public class WeaponSelectorActivity extends AppCompatActivity {

    ImageView ivSword, ivRing, ivBow, ivBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weapon_selector);


        ivSword = findViewById(R.id.ivSword);
        ivRing = findViewById(R.id.ivRing);
        ivBow = findViewById(R.id.ivBow);
        ivBack = findViewById(R.id.ivBack);

        Intent intent = getIntent();
        String playerTag = intent.getStringExtra(MainActivity.PLAYER_TAG);

        if (LOTRWeapons.SWORD.isSelected()){
            ivSword.setColorFilter(Color.DKGRAY);
        }
        if (LOTRWeapons.RING.isSelected()){
            ivRing.setColorFilter(Color.DKGRAY);
        }
        if (LOTRWeapons.BOW.isSelected()){
            ivBow.setColorFilter(Color.DKGRAY);
        }



        ivSword.setOnClickListener(v -> {
            if (LOTRWeapons.SWORD.isSelected()){

            } else {
                handleWeaponSelection(MainActivity.PLAYER_TAG, playerTag,
                        MainActivity.WEAPON_TAG, LOTRWeapons.SWORD.toString());
                LOTRWeapons.SWORD.setSelected(true);
                LOTRWeapons.RING.setSelected(false);
                LOTRWeapons.BOW.setSelected(false);

            }
        });

        ivRing.setOnClickListener(v -> {
            if (LOTRWeapons.RING.isSelected()){

            } else {
                handleWeaponSelection(MainActivity.PLAYER_TAG, playerTag,
                        MainActivity.WEAPON_TAG, LOTRWeapons.RING.toString());
                LOTRWeapons.RING.setSelected(true);
                LOTRWeapons.SWORD.setSelected(false);
                LOTRWeapons.BOW.setSelected(false);
            }

        });

        ivBow.setOnClickListener(v -> {
            if (LOTRWeapons.BOW.isSelected()){

            }else {
                handleWeaponSelection(MainActivity.PLAYER_TAG, playerTag,
                        MainActivity.WEAPON_TAG, LOTRWeapons.BOW.toString());
                LOTRWeapons.BOW.setSelected(true);
                if (LOTRWeapons.SWORD.isSelected()){

                } else {
                    LOTRWeapons.SWORD.setSelected(false);
                }
                if (LOTRWeapons.RING.isSelected()){

                } else {
                    LOTRWeapons.RING.setSelected(false);

                }
            }

        });

        ivBack.setOnClickListener(v -> {
            finish();
        });



    }

    public void handleWeaponSelection(String PLAYER_TAG, String playerTag,
                                         String WEAPON_TAG, String weaponName){
        Intent intent = new Intent();
        intent.putExtra(MainActivity.PLAYER_TAG, playerTag);
        intent.putExtra(MainActivity.WEAPON_TAG, weaponName);
        setResult(RESULT_OK, intent);
        finish();

    }

}