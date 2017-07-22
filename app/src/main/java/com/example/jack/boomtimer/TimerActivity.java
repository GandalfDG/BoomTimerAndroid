package com.example.jack.boomtimer;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class TimerActivity extends AppCompatActivity {

    private int seconds;
    private String time_str;
    private BoomGame game;
    TextView tv_time;
    TextView tv_round;
    TextView tv_hostage;
    Timer countdown;
    private Handler timer_handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        //TODO reload state in case app was closed and reopened
        int numPlayers;
        int numRounds;
        numPlayers = getIntent().getIntExtra("numPlayers", 6);
        numRounds = getIntent().getIntExtra("numRounds", 3);

        game = new BoomGame(numPlayers, numRounds);

        tv_time = (TextView)findViewById(R.id.time);
        tv_round = (TextView)findViewById(R.id.current_round);
        tv_hostage = (TextView)findViewById(R.id.current_hostages);

        timer_handler = new Handler();

        tv_round.setText(Integer.toString(game.getCurrentRound()));
        tv_hostage.setText(Integer.toString(game.getHostages()));

        setTimer();
        updateTime();

        countdown = new Timer();
        countdown.schedule(new TimerTask() {
            @Override
            public void run() {
                //TODO allow pausing
                if(seconds > 0) {
                    seconds--;
                    timer_handler.post(new Runnable() {
                        @Override
                        public void run() {
                            updateTime();
                        }
                    });
                }
                //TODO launch activity for between rounds
            }
        }, 0, 1000);
    }

    private void setTimer() {
        seconds = (game.getNumRounds() - (game.getCurrentRound() - 1)) * 60;
    }

    private void updateTime() {
        int min, sec;
        min = seconds / 60;
        sec = seconds % 60;
        if(sec > 9) {
            time_str = min + ":" + sec;
        }
        else {
            time_str = min + ":0" + sec;
        }
        tv_time.setText(time_str);
    }
}
