package com.example.jack.boomtimer;

import android.app.Activity;
import android.os.Handler;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class TimerActivity extends Activity {

    private int seconds;
    private String time_str;
    private BoomGame game;
    private TextView tv_time;
    private TextView tv_round;
    private TextView tv_hostage;
    private TimerButton pause_button;
    private Timer countdown;
    private boolean paused;
    private boolean game_over;
    private Handler timer_event_handler;

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
        game_over = false;
        tv_time = (TextView)findViewById(R.id.time);
        tv_round = (TextView)findViewById(R.id.current_round);
        tv_hostage = (TextView)findViewById(R.id.current_hostages);

        pause_button = (TimerButton)findViewById(R.id.pause_button);

        timer_event_handler = new Handler();

        pause_button.setOnClickListener(pauseButtonListener);
        
        startRound();
    }

    private void startRound() {
        setTimer();
        tv_round.setText(Integer.toString(game.getCurrentRound()));
        tv_hostage.setText(Integer.toString(game.getHostages()));
        pause_button.setState(TimerButton.timerButtonState.PAUSE);
        countdown = new Timer();
        countdown.schedule(createTimerTask(), 0, 1000);
        paused = false;
    }
    
    private void prepareNextRound() {
        pause_button.setState(TimerButton.timerButtonState.NEXT_ROUND);
        countdown.cancel();
        paused = true;
        try {
            game.nextRound();
            setTimer();
            updateTime();
        } catch (Exception e) {
            game_over = true;
            pause_button.setState(TimerButton.timerButtonState.GAME_OVER);
           // gameOver();
        }
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
    
    private View.OnClickListener pauseButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(pause_button.getState() != TimerButton.timerButtonState.NEXT_ROUND) {
                toggleTimer();
                pause_button.switchState();
            }
            else {
                pause_button.setState(TimerButton.timerButtonState.PAUSE);
                if(!game_over) {
                    startRound();
                }
            }
        }
    };
    
    private TimerTask createTimerTask() {
        return new TimerTask() {
            @Override
            public void run() {
                if (seconds > 0 && !paused) {
                    seconds--;
                    timer_event_handler.post(new Runnable() {
                        @Override
                        public void run() {
                            updateTime();
                        }
                    });
                }
                //TODO launch activity for between rounds
                else if (seconds <= 0) {
                    timer_event_handler.post(new Runnable() {
                        @Override
                        public void run() {
                            prepareNextRound();
                        }
                    });
                }

            }
        };
    }

    private void setTimer() {
        seconds = (game.getNumRounds() - (game.getCurrentRound() - 1)) * 60;
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
}
