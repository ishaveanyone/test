package com.dist.fun;

/**
 * Write a description of class RNG here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class RNG {
    private int maximumValue;
    private int minimumValue;


    public RNG(int maximumValue, int minimumValue) {
        this.maximumValue = maximumValue;
        this.minimumValue = minimumValue;

    }

    public int getMaximumValue() {
        return maximumValue;
    }

    public void setMaximumValue(int maximumValue) {
        this.maximumValue = maximumValue;
    }

    public int getMinimumValue() {
        return minimumValue;
    }

    public void setMinimumValue(int minimumValue) {
        this.minimumValue = minimumValue;
    }

    public  int getRandomNumber()
    {


        return (int)(Math.random() * (maximumValue - minimumValue + 1) + minimumValue);
    }
}




