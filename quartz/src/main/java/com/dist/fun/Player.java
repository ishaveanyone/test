package com.dist.fun;
import java.util.Scanner;
import java.util.Arrays;

public class Player {
    private String name;
    private int score;
    private Tile lastTilePlayed;
    private int roundsWon;
    private Tile tile[];

    public  Player() {
        this.name = " ";
        this.score = 0;
        this.lastTilePlayed =new Tile() ;
        this.roundsWon = 1;
        this.tile = new Tile[5];
        this.setTheScore();
    }


    public  Player(String name, int score, Tile lastTilePlayed, int roundsWon, Tile[] tile)
    {
        this.name = name;
        this.score = score;
        this.lastTilePlayed = lastTilePlayed;
        this.roundsWon = roundsWon;
        this.tile = tile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Tile getLastTilePlayed() {
        return lastTilePlayed;
    }

    public void setLastTilePlayed(Tile lastTilePlayed) {
        this.lastTilePlayed = lastTilePlayed;
    }

    public int getRoundsWon() {
        return roundsWon;
    }

    public void setRoundsWon(int roundsWon) {
        this.roundsWon = roundsWon;
    }

    public Tile[] getTile() {
        return tile;
    }

    public void setTile(Tile[] tile) {
        this.tile = tile;
    }
    public void setTheScore()
    {
        this.tile=new Tile[5];
        tile[0]=new Tile(5,1);
        tile[1]=new Tile(4,2);
        tile[2]=new Tile(3,3);
        tile[3]=new Tile(2,5);
        tile[4]=new Tile(1,7);
    }
    public void printTheValue()
    {
        String str="the  tile[]={";
        for(int i=0;i<this.getTile().length;i++){
            if(i==this.getTile().length-1){
                str+=this.getTile()[i].getValue();
                continue;
            }
            str=str+this.getTile()[i].getValue()+",";
        }
        str+="}";
        System.out.println(str);

    }



    public void removeByTileValue(int value){

        int index=0;
        boolean flag=false;
        for(int i=0;i<this.getTile().length;i++){
            if(this.getTile()[i].getValue()==value){
                index=i;
                flag=true;
                break;
            }
        }
        if(flag){
            this.setTile(removeTheElement(this.getTile(),index));
        }

    }

    private Tile[] removeTheElement(Tile[] arr,
                                    int index)
    {



        Tile[] anotherArray = new Tile[arr.length - 1];

        int k=0;
        for (int i = 0; i < arr.length; i++) {

            if (i == index) {
                continue;
            }


            anotherArray[k++] = arr[i];
        }


        return anotherArray;
    }

    // Driver Code

    public void addScore(int value){
        for(int i=0;i<this.getTile().length;i++){
            if(this.getTile()[i].getValue()==value){
                this.setScore(this.getScore()+this.getTile()[i].getScore());
                break;
            }
        }
    }

    public Tile getTileByValue(int value){
        for(int i=0;i<this.getTile().length;i++){
            if(this.getTile()[i].getValue()==value){
                return this.getTile()[i];
            }
        }
        return null;
    }
}


