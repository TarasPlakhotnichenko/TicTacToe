package ru.tarasplakhotnichenko.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

//import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
//import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
//import android.view.ViewDebug;
import android.widget.Button;
import android.widget.Toast;
import java.util.Arrays;
//import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

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
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

}

