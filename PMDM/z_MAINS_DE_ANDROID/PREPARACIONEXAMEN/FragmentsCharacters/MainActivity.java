package com.rittz.fragmentscharacters;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MyFragment.OnCharacterSelected {

    ImageView imageView1, imageView2;
    MyFragment fragment1, fragment2;

    ArrayList<MyFragment> myFragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);

        imageView1.setImageResource(R.drawable.elf);
        imageView2.setImageResource(R.drawable.elf);

        fragment1 = (MyFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView2);
        fragment2 = (MyFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView3);

        myFragments.add(fragment1);
        myFragments.add(fragment2);

    }

    @Override
    public void characterSelected(GameCharacters gameCharacter, MyFragment fragment) {


        int switchOption;
        if(fragment == fragment1){
            switchOption = 1;
        } else if (fragment == fragment2) {
            switchOption = 2;
        } else {
            return;
        }

        switch(switchOption){
            case 1:
                resetImageViewList(fragment2.imageViewList);
                if(gameCharacter == GameCharacters.ZOMBIE){
                    imageView1.setImageResource(R.drawable.zombie);
                    fragment2.imageViewChar1.setClickable(false);
                    fragment2.imageViewChar1.setColorFilter(Color.GRAY);
                }
                if(gameCharacter == GameCharacters.MAGE){
                    imageView1.setImageResource(R.drawable.mage);
                    fragment2.imageViewChar2.setClickable(false);
                    fragment2.imageViewChar2.setColorFilter(Color.GRAY);
                }
                if(gameCharacter == GameCharacters.ELF){
                    imageView1.setImageResource(R.drawable.elf);
                    fragment2.imageViewChar3.setClickable(false);
                    fragment2.imageViewChar3.setColorFilter(Color.GRAY);
                }
                break;
            case 2:
                resetImageViewList(fragment1.imageViewList);
                if(gameCharacter == GameCharacters.ZOMBIE){
                    imageView2.setImageResource(R.drawable.zombie);
                    fragment1.imageViewChar1.setClickable(false);
                    fragment1.imageViewChar1.setColorFilter(Color.GRAY);
                }
                if(gameCharacter == GameCharacters.MAGE){
                    imageView2.setImageResource(R.drawable.mage);
                    fragment1.imageViewChar2.setClickable(false);
                    fragment1.imageViewChar2.setColorFilter(Color.GRAY);
                }
                if(gameCharacter == GameCharacters.ELF){
                    imageView2.setImageResource(R.drawable.elf);
                    fragment1.imageViewChar3.setClickable(false);
                    fragment1.imageViewChar3.setColorFilter(Color.GRAY);
                }
                break;
            default:
                Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
                break;
        }

    }

    void resetImageViewList(ArrayList<ImageView> list){
        for(ImageView img : list){
            img.setColorFilter(null);
            img.setClickable(true);
        }
    }
}