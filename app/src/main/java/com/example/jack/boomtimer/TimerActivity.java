package com.example.jack.boomtimer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class TimerActivity extends AppCompatActivity {

    private int numPlayers;
    private int numRounds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        numPlayers = getIntent().getIntExtra("numPlayers", 6);
        numRounds = getIntent().getIntExtra("numRounds", 3);

        EditText test = (EditText)findViewById(R.id.editText);
        test.setText("players: " + numPlayers + " rounds: " + numRounds);
    }
}
