package com.dist.fun;

import java.util.*;
public class Tile {
    private int score;
    private int value;
    public Tile(){
        this.score=0;
        this.value=0;

    }

    public Tile(int score, int value) {
        this.score = score;
        this.value = value;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}

