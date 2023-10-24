package com.rittz.icecream;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.ArrayList;

public class Activity2 extends AppCompatActivity {
    final static int DEFAULT_VALUE = 0, MAX_SCOOPS = 6;
    ImageView imageViewScoop1, imageViewScoop2, imageViewScoop3, imageViewScoop4, imageViewScoop5, imageViewScoop6, imageViewContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        Integer vanillaAmount = null, strawberryAmount = null, chocolateAmount = null;
        String selectedContainerToString = null;

        Intent i = getIntent();

        vanillaAmount = i.getIntExtra(MainActivity.INFO_VANILLA_AMOUNT, DEFAULT_VALUE);
        strawberryAmount = i.getIntExtra(MainActivity.INFO_STRAWBERRY_AMOUNT, DEFAULT_VALUE);
        chocolateAmount = i.getIntExtra(MainActivity.INFO_CHOCOLATE_AMOUNT, DEFAULT_VALUE);
        selectedContainerToString = i.getStringExtra(MainActivity.INFO_SELECTED_CONTAINER);

        imageViewScoop1 = findViewById(R.id.imageViewScoop1);
        imageViewScoop2 = findViewById(R.id.imageViewScoop2);
        imageViewScoop3 = findViewById(R.id.imageViewScoop3);
        imageViewScoop4 = findViewById(R.id.imageViewScoop4);
        imageViewScoop5 = findViewById(R.id.imageViewScoop5);
        imageViewScoop6 = findViewById(R.id.imageViewScoop6);
        imageViewContainer = findViewById(R.id.imageViewContainer);

        ArrayList<ImageView> imageList = new ArrayList<>();
        imageList.add(imageViewScoop1);
        imageList.add(imageViewScoop2);
        imageList.add(imageViewScoop3);
        imageList.add(imageViewScoop4);
        imageList.add(imageViewScoop5);
        imageList.add(imageViewScoop6);

        for(ImageView imageView : imageList){
            if(vanillaAmount > 0){
                imageView.setImageResource(R.drawable.vanilla);
                vanillaAmount--;
                continue;
            }
            if(strawberryAmount > 0){
                imageView.setImageResource(R.drawable.strawberry);
                strawberryAmount--;
                continue;
            }
            if(chocolateAmount > 0){
                imageView.setImageResource(R.drawable.chocolate);
                chocolateAmount--;
            }
        }

        switch(selectedContainerToString){
            case MainActivity.CONE_CONTAINER_NAME:
                imageViewContainer.setImageResource(R.drawable.cone);
                break;
            case MainActivity.CHOCO_CONE_CONTAINER_NAME:
                imageViewContainer.setImageResource(R.drawable.choco_cone);
                break;
            case MainActivity.SUNDAE_CONTAINER_NAME:
                imageViewContainer.setImageResource(R.drawable.sundae);
                break;
            default:
                break;
        }
    }
}