package com.example.nukeseeker;


import java.util.Random;


public class Minemanager {
    private int row = 4;
    private int column = 6;
    private int numberofmines = 6;
    private Mine[] [] mines;
    //Supports Singleton class
    //Private constructor
    Random rand=new Random();

    private Minemanager(){

    }
    private static Minemanager instance;
    //Returns a single instance of the board
    public static Minemanager getInstance(){
        if(instance==null){
            instance= new Minemanager();

        }
        return instance;
    }
    //Setter and Getter for class mine manager


    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getNumberofmines() {
        return numberofmines;
    }

    public void setNumberofmines(int numberofmines) {
        this.numberofmines = numberofmines;
    }

    public Mine getMines(int x , int y) {
        return mines[x][y];
    }

    public void populateMine(){
        mines=new Mine[row][column];
        for(int i=0;i<row;i++){
            for(int j=0;j<column;j++){
                mines[i][j]=new Mine(false,false);
            }
        }
        for(int i=0;i<numberofmines;i++){
            int rownum = 0;
            int colnum = 0;
            rownum = rand.nextInt(row);
            colnum=rand.nextInt(column);
            mines[rownum][colnum]=new Mine(false,true);
        }


    }


}
