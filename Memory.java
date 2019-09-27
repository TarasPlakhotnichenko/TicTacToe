package ru.tarasplakhotnichenko.tictactoe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
//import java.util.Set;

public class Memory {
    private Integer[][] btns2d = new Integer[3][3]; //a 2d game field of buttons converted from consequitive list of buttons -  btns
    private final List<Integer> btns; //list of buttons
    private List<Integer> sequence = new ArrayList<>(); //a queue of buttons pushed
    private Integer[][] game2d = new Integer[3][3]; //2d array of moves taken or in other words - values choiced - 1 or 0"
    private String theWinner;

    public Memory(List<Integer> btns) {
        //Init of 2d button array picked from list btns.
        //We need it to address a particular cell to be filled with a value of play's move.
        //game2d - is an array for moves
        //PS I did't know how to convert a list to 2d array so I did it this way:
        int count = 0;
        int j;
        for (j = 0; j < btns2d.length; j++) {
            for (int i = 0; i < btns.size() / 3; i++) {
                this.btns2d[j][i] = btns.get(count);
                count++;
            }
        }
        this.btns = btns;
    }


    public boolean checkLegalMove(Integer id){
        if (sequence.contains(id)) {
            return false;
        }
        return true;
    }

    //Fill the grid with user/bot's choice when he moves - either 1 or 0":
    //firstly find a button from btns2d array to define address;  put X (designated for 1) or O (designated for 0) value to game2d 2d array with the address of that button
    public void moveValue(Integer Button, Integer moveValue) {
        for (int i = 0; i < btns2d.length; i++) {
            for (int j = 0; j < btns2d.length; j++) {
                if (btns2d[i][j].equals(Button)) {
                    game2d[i][j] = moveValue;
                }
            }
        }
    }

    //We are filling common move queue with players moves - both a computer and a human. This particular method is intended for a human use
    public void addAnswer(Integer id) {
        sequence.add(id);
        moveValue(id, 1);
    }

    //Generating random button from available button pool when a computer's turn
    public Integer rnd() {
        Random rn = new Random();
        //List<Integer> btns = Array.aslist(btns2d); //array to list convert
        int rndButton;
        do {
            rndButton = btns.get(rn.nextInt(btns.size()));
        } while (sequence.contains(rndButton));

        sequence.add(rndButton);
        moveValue(rndButton, 0);
        return rndButton;
    }

    public boolean isFinish() {
        return sequence.size() == btns.size();
    }

    //Check out for triples of Xs or Os
    public boolean isWinner() {
        //rows
        HashSet<Integer> s1 = new HashSet();
        HashSet<Integer> s2 = new HashSet();
        HashSet<Integer> s3 = new HashSet();

        for (int i = 0; i < this.game2d.length; i++) {
            s1.add(this.game2d[i][0]);
            s2.add(this.game2d[i][1]);
            s3.add(this.game2d[i][2]);
            HashSet<Integer> intersection = new HashSet<Integer>(s1);
            intersection.retainAll(s2);
            intersection.retainAll(s3);
            if (intersection.contains(1) || intersection.contains(0)) {
                if (intersection.contains(1)) {
                    this.theWinner = "X";
                } else {
                    this.theWinner = "0";
                }
                return true;
            }
            s1.clear();
            s2.clear();
            s3.clear();
        }


        //columns
        s1 = new HashSet();
        s2 = new HashSet();
        s3 = new HashSet();
        for (int i = 0; i < this.game2d.length; i++) {

            s1.add(this.game2d[0][i]);
            s2.add(this.game2d[1][i]);
            s3.add(this.game2d[2][i]);
            HashSet<Integer> intersection = new HashSet<Integer>(s1);
            intersection.retainAll(s2);
            intersection.retainAll(s3);
            if (intersection.contains(1) || intersection.contains(0)) {
                if (intersection.contains(1)) {
                    this.theWinner = "X";
                } else {
                    this.theWinner = "0";
                }

                return true;
            }
            s1.clear();
            s2.clear();
            s3.clear();
        }


        //diags
        s1 = new HashSet();
        s2 = new HashSet();
        s3 = new HashSet();
        s1.add(this.game2d[0][0]);
        s2.add(this.game2d[1][1]);
        s3.add(this.game2d[2][2]);

        HashSet<Integer> intersection = new HashSet<Integer>(s1);
        intersection.retainAll(s2);
        intersection.retainAll(s3);

        if (intersection.contains(1) || intersection.contains(0)) {
            if (intersection.contains(1)) {
                this.theWinner = "X";
            } else {
                this.theWinner = "0";
            }
            return true;
        }
        s1.clear();
        s2.clear();
        s3.clear();

        s1 = new HashSet();
        s2 = new HashSet();
        s3 = new HashSet();
        s1.add(this.game2d[2][0]);
        s2.add(this.game2d[1][1]);
        s3.add(this.game2d[0][2]);

        intersection = new HashSet<Integer>(s1);
        intersection.retainAll(s2);
        intersection.retainAll(s3);

        if (( intersection.contains(1) || intersection.contains(0) )) {
            if (intersection.contains(1)) {
                this.theWinner = "X";
            } else {
                this.theWinner = "0";
            }
            return true;
        }
        return false;
    }
    //Cleaning common queue list and  array of moves taken
    public void cleanSeq() {
        sequence.clear();
        for( int i = 0; i < game2d.length; i++ ) {
            Arrays.fill(game2d[i], null);
        }
    }


    public String getTheWinner() {
        return this.theWinner;
    }

}

