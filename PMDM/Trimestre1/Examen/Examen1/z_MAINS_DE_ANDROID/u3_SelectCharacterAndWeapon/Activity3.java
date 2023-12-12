package com.rittz.selectcharacterandweapon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class Activity3 extends AppCompatActivity {
    static final int DEFAULT_VALUE = -1;
    static final String INFO_SELECTED_PLAYER = "Activity3.selectedPlayer";
    public enum Weapon {
        BLASTER, TRIDENT, STAFF, SHURIKEN;
    }
    ImageButton imageButtonWeapSel1, imageButtonWeapSel2, imageButtonWeapSel3, imageButtonWeapSel4;
    Button buttonCancelWeap, buttonClearWeap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        imageButtonWeapSel1 = findViewById(R.id.imageButtonWeapSel1);
        imageButtonWeapSel2 = findViewById(R.id.imageButtonWeapSel2);
        imageButtonWeapSel3 = findViewById(R.id.imageButtonWeapSel3);
        imageButtonWeapSel4 = findViewById(R.id.imageButtonWeapSel4);

        buttonClearWeap = findViewById(R.id.buttonClearWeap);
        buttonCancelWeap = findViewById(R.id.buttonCancelWeap);

        imageButtonWeapSel1.setImageResource(R.drawable.blaster);
        imageButtonWeapSel2.setImageResource(R.drawable.trident);
        imageButtonWeapSel3.setImageResource(R.drawable.staff);
        imageButtonWeapSel4.setImageResource(R.drawable.shuriken);

        imageButtonWeapSel1.setTag(Weapon.BLASTER);
        imageButtonWeapSel2.setTag(Weapon.TRIDENT);
        imageButtonWeapSel3.setTag(Weapon.STAFF);
        imageButtonWeapSel4.setTag(Weapon.SHURIKEN);

        Intent intent = getIntent();
        ArrayList<String> selectedWeaponImages = intent.getStringArrayListExtra(MainActivity.INFO_SELECTED_WEAP_IMAGES);
        int selectedPlayer = intent.getIntExtra(MainActivity.INFO_PLAYER_CALLING, DEFAULT_VALUE);

        //set current player selection position to null so it lets choose the same weapon again
        selectedWeaponImages.set(selectedPlayer, null);

        //adding filters for other players' active selections
        for(String selectedWeaponImage : selectedWeaponImages){
            if(selectedWeaponImage == null){
                continue;
            }
            if(Weapon.valueOf(selectedWeaponImage) == Weapon.BLASTER){
                imageButtonWeapSel1.setColorFilter(Color.GRAY);
            } else if (Weapon.valueOf(selectedWeaponImage) == Weapon.TRIDENT){
                imageButtonWeapSel2.setColorFilter(Color.GRAY);
            } else if (Weapon.valueOf(selectedWeaponImage) == Weapon.STAFF){
                imageButtonWeapSel3.setColorFilter(Color.GRAY);
            } else if (Weapon.valueOf(selectedWeaponImage) == Weapon.SHURIKEN){
                imageButtonWeapSel4.setColorFilter(Color.GRAY);
            }
        }

        View.OnClickListener handler = view -> {
            if(!selectedWeaponImages.contains(view.getTag().toString())){
                selectedWeaponImages.set(selectedPlayer, view.getTag().toString());
                Intent data = new Intent();
                data.putExtra(INFO_SELECTED_PLAYER, selectedPlayer);
                data.putExtra(MainActivity.INFO_SELECTED_WEAP_IMAGES, selectedWeaponImages);
                setResult(Activity3.RESULT_OK, data);
                finish();
            } else {
                Toast.makeText(Activity3.this, "This weapon is already selected by other player", Toast.LENGTH_LONG).show();
            }
        };

        imageButtonWeapSel1.setOnClickListener(handler);
        imageButtonWeapSel2.setOnClickListener(handler);
        imageButtonWeapSel3.setOnClickListener(handler);
        imageButtonWeapSel4.setOnClickListener(handler);

        buttonClearWeap.setOnClickListener(view -> {
            Intent data = new Intent();
            data.putExtra(INFO_SELECTED_PLAYER, selectedPlayer);
            setResult(RESULT_FIRST_USER, data);
            finish();
        });

        buttonCancelWeap.setOnClickListener(view -> {
            setResult(Activity3.RESULT_CANCELED);
            finish();
        });

    }
}