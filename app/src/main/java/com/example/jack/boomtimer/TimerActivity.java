package com.example.jack.boomtimer;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class TimerActivity extends Activity {

    private int seconds;
    private String time_str;
    private BoomGame game;
    TextView tv_time;
    TextView tv_round;
    TextView tv_hostage;
    Button pause_button;
    Resources res;
    Timer countdown;
    boolean paused;
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

        pause_button = (Button)findViewById(R.id.pause_button);

        res = getResources();

        timer_handler = new Handler();

        tv_round.setText(Integer.toString(game.getCurrentRound()));
        tv_hostage.setText(Integer.toString(game.getHostages()));

        pause_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleTimer();
                pause_button.setText(paused ? res.getText(R.string.resume_timer) : res.getText(R.string.pause_timer));
            }
        });

        setTimer();
        updateTime();

        countdown = new Timer();
        countdown.schedule(new TimerTask() {
            @Override
            public void run() {
                if(seconds > 0 && !paused) {
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
        paused = false;
    }

    private void pauseTimer() {
        paused = true;
    }

    private void resumeTimer() {
        paused = false;
    }

    private void toggleTimer() {
        paused = !paused;
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
