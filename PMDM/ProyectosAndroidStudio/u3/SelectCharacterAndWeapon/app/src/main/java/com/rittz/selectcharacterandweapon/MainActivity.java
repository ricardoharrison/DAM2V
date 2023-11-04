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

enum SelectionType {
    CHARACTER, WEAPON;
}
public class MainActivity extends AppCompatActivity {
    ImageButton imageButtonCharP1, imageButtonCharP2, imageButtonWeapP1, imageButtonWeapP2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                launcherActivity2.launch(intent);
            } else if (view.getTag() == SelectionType.WEAPON){
                Intent intent = new Intent(MainActivity.this, Activity3.class);
                launcherActivity3.launch(intent);
            }
        };

        imageButtonCharP1.setOnClickListener(handler);
        imageButtonCharP2.setOnClickListener(handler);
        imageButtonWeapP1.setOnClickListener(handler);
        imageButtonWeapP2.setOnClickListener(handler);

    }
}