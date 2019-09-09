package com.dist.fun;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2019-09-08 21:18
 * Author: xupp
 * Email: xupp@dist.com.cn
 * Desc：
 */
import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;
public class Game {
    Player player=new Player();
    Player computer=new Player();
    int rounds= 0;
    public  void main() {
        printWelcome();
        printMenu();
        printMenu();



    }

    public  void printWelcome() {
        System.out.println("\"welcome to 21 with primes game!\"");
    }

    public  void printMenu() {
        System.out.println("press 1 to register a player");
        System.out.println("press 2 to start a new game");
        System.out.println("press 3 view a help menu");
        System.out.println("press 4 exit");
        boolean flag= true;

        Scanner console = new Scanner(System.in);
        int choice = console.nextInt();
        while(flag)
        {
            switch(choice) {
                case 1:
                    System.out.println("you have press 1 to register a name");
                    System.out.println("Please enter your name");
                    nameCheck();

                    break;
                case 2:
                    System.out.println(" new game started");
                    game();
                    break;
                case 3:
                    System.out.println("you have seen a help menu");
                    break;
                case 4:
                    System.out.println("you have exit the game");

                    flag=false;

                    break;
                default:
                    System.out.println("Please enter number between 1-4 again");
            }
        }

    }
    public void nameCheck() {
        Scanner console = new Scanner(System.in);
        String playerName = console.nextLine();
        while (playerName.trim().length() == 0 ||
                playerName.length() != playerName.trim().length() ||
                playerName.length() < 3 ||
                playerName.length() > 10) {
            System.out.println("Wrong input again");
            playerName = console.nextLine();
        }
        System.out.println("Hello "+playerName);
        player.setName(playerName);
        computer.setName("Computer");
    }

    public void SelectStart()
    {
        Scanner console= new Scanner(System.in);
        console.nextLine();

    }

    public void game()
    {
        System.out.println("please enter the rounds");
        Scanner console = new Scanner(System.in);
        this.rounds = console.nextInt();
        for(int i=1;i<=rounds;i++){
            boolean flag = true;
            int first=getFirst();
            player.setTheScore();
            computer.setTheScore();
            System.out.println("new round");
            while(flag){

                int playervalue=0;
                int computervalue=0;
                int playerTotalScore=0;
                int computerTotalScore=0;

                player.printTheValue();
                computer.printTheValue();
                if(first==0){
                    playervalue=console.nextInt();
                    if(Arrays.asList(1,2,3,5,7).contains(Integer.valueOf((playervalue)))){
                        player.addScore(playervalue);
                    }else {
                        continue;
                    }
                    if(this.player.getTile().length==0||this.player.getScore()>=21){
                        flag=false;
                        player.setLastTilePlayed(player.getTileByValue(playervalue));
                        player.setRoundsWon(player.getRoundsWon()+1);

                        player.setScore(player.getScore()+5);
                        if(player.getTileByValue(5)!=null){
                            player.setScore(player.getScore()-3);
                        }
                        if(computer.getTileByValue(5)!=null){
                            computer.setScore(computer.getScore()-3);
                        }
                    }
                    player.removeByTileValue(playervalue);
                    Random rand = new Random();
                    computervalue=computer.getTile()[rand.nextInt(computer.getTile().length)].getValue();
                    computer.addScore(computervalue);

                    System.out.println(this.player.getTile().length);

                    if(this.computer.getTile().length==1||this.computer.getScore()>=21){
                        flag=false;
                        computer.setLastTilePlayed(computer.getTileByValue(playervalue));
                        computer.setRoundsWon(computer.getRoundsWon()+1);

                        computer.setScore(player.getScore()+5);
                        if(player.getTileByValue(5)!=null){
                            player.setScore(player.getScore()-3);
                        }
                        if(computer.getTileByValue(5)!=null){
                            computer.setScore(computer.getScore()-3);
                        }
                    }
                    computer.removeByTileValue(computervalue);
                    playerTotalScore=computer.getScore()+player.getScore();
                }else{

                    playervalue=console.nextInt();
                    if(!Arrays.asList(1,2,3,5,7).contains(Integer.valueOf((playervalue)))){
                        continue;
                    }

                    Random rand = new Random();
                    computervalue=computer.getTile()[rand.nextInt(computer.getTile().length)].getValue();
                    computer.addScore(computervalue);
                    if(this.computer.getTile().length==0||this.computer.getScore()>=21){
                        flag=false;
                        computer.setLastTilePlayed(computer.getTileByValue(playervalue));
                        computer.setRoundsWon(computer.getRoundsWon()+1);
                        computer.setScore(player.getScore()+5);
                        if(player.getTileByValue(5)!=null){
                            player.setScore(player.getScore()-3);
                        }
                        if(computer.getTileByValue(5)!=null){
                            computer.setScore(computer.getScore()-3);
                        }
                    }
                    computer.removeByTileValue(computervalue);
                    if(Arrays.asList(1,2,3,5,7).contains(Integer.valueOf((playervalue)))){
                        player.addScore(playervalue);
                    }
                    System.out.println(this.player.getTile().length);
                    if(this.player.getTile().length==1||this.player.getScore()>=21){
                        flag=false;
                        player.setLastTilePlayed(player.getTileByValue(playervalue));
                        player.setRoundsWon(player.getRoundsWon()+1);
                        player.setScore(player.getScore()+5);
                        if(player.getTileByValue(5)!=null){
                            player.setScore(player.getScore()-3);
                        }
                        if(computer.getTileByValue(5)!=null){
                            computer.setScore(computer.getScore()-3);
                        }
                    }
                    player.removeByTileValue(playervalue);
                    computerTotalScore=computer.getScore()+player.getScore();

                }

                System.out.println("Human player plays tile" +playervalue+". Game Total is now" +playerTotalScore+
                        ". Human player score is "+player.getScore()+".Computer player plays tile "+computervalue+
                        ". Game Total is now "+computerTotalScore+". Computer player score is "+computer.getScore());
            }
            if(computer.getRoundsWon()>player.getRoundsWon()){
                System.out.println("Computer win");
            }
            else if(computer.getRoundsWon()<player.getRoundsWon()){
                System.out.println("Hunman win");

            }else{
                System.out.println("THere is a draw game");
            }

        }


    }
    public int getFirst()
    {
        Scanner console = new Scanner(System.in);
        System.out.println("please press enter to decide who goes first");
        console.nextLine();
        RNG rng = new RNG (1,4);
        int playOneNumber=rng.getRandomNumber();
        int playTwoNumber=rng.getRandomNumber();
        while( playOneNumber ==playTwoNumber)
        {

            playOneNumber=rng.getRandomNumber();
            playTwoNumber=rng.getRandomNumber();
        }

        if(playOneNumber > playTwoNumber)
        {
            System.out.println("Player go first");
            System.out.println("");
            return 0;
        }
        else
        {
            System.out.println("Computer go first");
            System.out.println("");
            return 1;
        }


    }

    public static void main(String[] args) {
        new Game().game();

    }
}

