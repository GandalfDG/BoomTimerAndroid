package com.example.jack.boomtimer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    private int numPlayers = 6;
    private int numRounds = 3;

    private static final String TAG = "BoomTimer";
    private static final String PERSIST = "PersistanceFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* set up the player count number picker */
        final NumberPicker np_player_picker;
        np_player_picker = (NumberPicker)findViewById(R.id.player_picker);
        np_player_picker.setMinValue(6);
        np_player_picker.setMaxValue(50);
        np_player_picker.setWrapSelectorWheel(false);
        /* TODO set the player count on app start to what it previously was */

        /* set up the start game button */
        final Button start_button;
        start_button = (Button)findViewById(R.id.start_button);
        start_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent timerStartIntent = new Intent(MainActivity.this, TimerActivity.class);
                timerStartIntent.putExtra("numPlayers", numPlayers);
                timerStartIntent.putExtra("numRounds", numRounds);
                startActivity(timerStartIntent);
            }
        });

        /* set up the radio buttons for number of rounds */
        final RadioGroup rg_rounds;
        rg_rounds = (RadioGroup)findViewById(R.id.rounds_group);
        final RadioButton rb_rounds_3;
        rb_rounds_3 = (RadioButton)findViewById(R.id.rounds_3);
        final RadioButton rb_rounds_5;
        rb_rounds_5 = (RadioButton)findViewById(R.id.rounds_5);
        /* with fewer than 10 players, only 3 rounds are allowed */
        if(np_player_picker.getValue() <= 10) {
            rb_rounds_5.setChecked(false);
            rb_rounds_5.setEnabled(false);
            rb_rounds_3.setChecked(true);
            numRounds = 3; //TODO this is not a good place for numRounds to be initialized
        }
        /* otherwise 3 or 5 rounds may be selected */
        /* TODO in this case remember if 3 or 5 rounds was previously selected */
        else {
            rb_rounds_5.setEnabled(true);
            rb_rounds_5.setChecked(false);
            rb_rounds_3.setChecked(true);
        }
        Log.i(TAG, "Rounds = " + numRounds);

        /* set the callback for changing picker value. Set numPlayers and enable or disable 5 rounds */
        np_player_picker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                numPlayers = newVal;
                Log.i(TAG, "Players = " + numPlayers);
                if(newVal <= 10) {
                    rb_rounds_5.setChecked(false);
                    rb_rounds_3.setChecked(true);
                    rb_rounds_5.setEnabled(false);
                }
                else {
                    rb_rounds_5.setEnabled(true);
                }
            }
        });

        /* set the callbacks for the radio buttons. Only one can be activated at a time. */
        rg_rounds.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(rb_rounds_3.isChecked()) {
                    numRounds = 3;
                    Log.i(TAG, "Rounds = " + numRounds);
                }
                else if (rb_rounds_5.isChecked()){
                    numRounds = 5;
                    Log.i(TAG, "Rounds = " + numRounds);
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences persist = getSharedPreferences(PERSIST, 0);
        SharedPreferences.Editor editor = persist.edit();
        editor.putInt("players", numPlayers);
        editor.putInt("rounds", numRounds);
        editor.commit();
    }


}
