package com.example.jack.boomtimer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class TimerActivity extends AppCompatActivity {

    private int numPlayers;
    private int numRounds, currentRound;
    private int seconds;

    private static final int[][] hostages = {{1, 1, 1, 0, 0},
                                             {1, 1, 1, 2, 2},
                                             {1, 1, 2, 2, 3},
                                             {1, 1, 2, 3, 4},
                                             {1, 2, 3, 4, 5}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        //TODO reload state in case app was closed and reopened
        numPlayers = getIntent().getIntExtra("numPlayers", 6);
        numRounds = getIntent().getIntExtra("numRounds", 3);

        TextView tv_time = (TextView)findViewById(R.id.time);
        TextView tv_round = (TextView)findViewById(R.id.current_round);
        TextView tv_hostage = (TextView)findViewById(R.id.current_hostages);

    }

    private void set_timer() {
        seconds = (numRounds - (currentRound - 1)) * 60;
    }

    private int get_hostages() {
        int groupCode = -1;
        if(numPlayers <= 10) {
            groupCode = 0;
        }
        else if(numPlayers <= 13) {
            groupCode = 1;
        }
        else if(numPlayers <= 17) {
            groupCode = 2;
        }
        else if(numPlayers <= 21) {
            groupCode = 3;
        }
        else {
            groupCode = 4;
        }

        return hostages[groupCode][numRounds - (currentRound - 1)];
    }
}
