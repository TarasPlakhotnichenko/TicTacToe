package ru.tarasplakhotnichenko.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
//import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_main);
        //load(state);
        //Log.i(MainActivity.class.getName(), "onCreate");
    }

/*
    private  void load(Bundle state) {
        final String ANS = "answers";
        Integer[][] answers = memory.getCurrentGameField();
        if (state != null) {
            //
        }
    }


    @Override
    protected void onResume(){
       super.onResume();
       Log.i(MainActivity.class.getName(), "onResume");
    }

    @Override
    protected void onStart(){
       super.onStart();
       Log.i(MainActivity.class.getName(), "onStart");
    }


    @Override
    protected void onPause() {
       super.onPause();
       Log.i(MainActivity.class.getName(), "onPause");
    }
    */

    //@Override
    //Envoked every time app is getting inactive  that is  paused, stopped or destroyed
    /*
    protected void onSaveInstanceState (Bundle state) {
        ArrayList<Integer> gameField = new ArrayList<>();
        super.onSaveInstanceState(state);
        Log.i(MainActivity.class.getName(), "onSaveInstanceState");
        //Transition from 2D array to ArrayList
        for (int j = 0; j < memory.getCurrentGameField().length; j++) {
            for (int i = 0; i < memory.getCurrentGameField().length; i++) {
                gameField.add(memory.getCurrentGameField()[j][i]);
            }
        }
        state.putIntegerArrayList("gameField",gameField);
        state.putIntegerArrayList("getQueue", (ArrayList<Integer>) memory.getCurrentMoveQueue());
    }

     */

    //---------------------------------------------------------------------------------

    private Memory memory = new Memory(
            Arrays.asList(R.id.button1, R.id.button2, R.id.button3, R.id.button4,  R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9));


    public void answer(View view) {
        //plain vanilla string name for a view id
        //Toast.makeText(this, view.getResources().getResourceEntryName(view.getId()), Toast.LENGTH_LONG).show();

        //Magic id number2string
        Toast.makeText(this, "Button tapped: " + String.valueOf(view.getId()), Toast.LENGTH_LONG).show();

        if (memory.checkLegalMove(view.getId())) {
            memory.addAnswer(view.getId());
            ((Button) view).setText("X");
            if (!memory.isFinish() && !memory.isWinner()) {
                Button btn = MainActivity.this.findViewById(memory.rnd());
                ((Button) btn).setText("0");

            } else {
                memory.cleanSeq();
                if (memory.getTheWinner() != null) {
                    msg("Game over: " + memory.getTheWinner() + " wins");
                }
                else
                {
                    msg("Game over: parity");
                }
            }
        } else  {
            Toast.makeText(this, String.valueOf(view.getId()) + " illegal move", Toast.LENGTH_LONG).show();
        }

    }

    private void msg(String text) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder.setTitle("Game Results");

        alertDialogBuilder
                .setMessage(text)
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        setContentView(R.layout.activity_main);
                        //onStart();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

}

