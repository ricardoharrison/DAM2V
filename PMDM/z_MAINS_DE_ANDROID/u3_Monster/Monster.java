package com.rittz.monster;

import android.graphics.Color;

import java.io.Serializable;
import java.util.ArrayList;

public class Monster implements Serializable {
    static final String HEAD = "*", BODY = "O", LEFT_LIMB = "/", RIGHT_LIMB = "\\";
    static final int LEFT_ARMS_POSITION = 0, RIGHT_ARMS_POSITION = 1, LEFT_LEGS_POSITION = 2, RIGHT_LEGS_POSITION = 3;
    String name;
    int numberOfLimbs, leftArms, rightArms, leftLegs, rightLegs, color;

    public Monster(String name, int numberOfLimbs, int color) {
        this.name = name.toUpperCase();
        this.numberOfLimbs = numberOfLimbs;
        this.color = color;
        ArrayList<Integer> limbsDistribution = distributeLimbs();
        this.leftArms = limbsDistribution.get(LEFT_ARMS_POSITION);
        this.rightArms = limbsDistribution.get(RIGHT_ARMS_POSITION);
        this.leftLegs = limbsDistribution.get(LEFT_LEGS_POSITION);
        this.rightLegs = limbsDistribution.get(RIGHT_LEGS_POSITION);
    }

    public String getName() {
        return name;
    }

    public int getNumberOfLimbs() {
        return numberOfLimbs;
    }

    public int getColor() {
        return color;
    }

    public ArrayList<Integer> distributeLimbs() {
        ArrayList<Integer> limbsDistribution = new ArrayList<>();
        int remaininglimbs = numberOfLimbs;

        int leftArms = (int) (Math.random() * remaininglimbs);
        remaininglimbs -= leftArms;
        limbsDistribution.add(leftArms);

        int rightArms = (int) (Math.random() * remaininglimbs);
        remaininglimbs -= rightArms;
        limbsDistribution.add(rightArms);

        int leftLegs = (int) (Math.random() * remaininglimbs);
        remaininglimbs -= leftLegs;
        limbsDistribution.add(leftLegs);

        int rightLegs = remaininglimbs;
        limbsDistribution.add(rightLegs);

        return limbsDistribution;
    }


    @Override
    public String toString() {

        //drawing upper limbs
        String result = name + "\n\n" + HEAD + "\n";
        if(leftArms < rightArms){
            for (int i = 0; i < rightArms - leftArms; i++){
                result = result + " ";
            }
        }
        for (int i = 0; i < leftArms; i++){
            result = result + LEFT_LIMB;
        }
        result = result + " " + BODY + " ";
        for (int i = 0; i < rightArms; i++){
            result = result + RIGHT_LIMB;
        }
        if(rightArms < leftArms){
            for (int i = 0; i < leftArms - rightArms; i++){
                result = result + " ";
            }
        }
        result = result + "\n";

        //drawing lower limbs
        if(leftLegs < rightLegs){
            for (int i = 0; i < rightLegs - leftLegs; i++){
                result = result + " ";
            }
        }
        for (int i = 0; i < leftLegs; i++){
            result = result + LEFT_LIMB;
        }
        result = result + "   ";
        for (int i = 0; i < rightLegs; i++){
            result = result + RIGHT_LIMB;
        }
        if(rightLegs < leftLegs){
            for (int i = 0; i < leftLegs - rightLegs; i++){
                result = result + " ";
            }
        }
        result = result + "\n";

        return result;
    }
}
