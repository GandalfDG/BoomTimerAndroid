package com.example.jack.boomtimer;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

public class TimerButton extends Button {
    public enum timerButtonState {PAUSE, RESUME, NEXT_ROUND};
    private timerButtonState state;

    public TimerButton(Context context) {
        super(context);
        setState(timerButtonState.PAUSE);
    }

    public TimerButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setState(timerButtonState.PAUSE);
    }

    public timerButtonState getState() {
        return state;
    }

    public void switchState() {
        if(state == timerButtonState.PAUSE) setState(timerButtonState.RESUME);
        else if(state == timerButtonState.RESUME) setState(timerButtonState.PAUSE);
    }

    public void setState(timerButtonState state) {
        this.state = state;
        switch(state) {
            case PAUSE:
                this.setText(R.string.pause_timer);
                break;
            case RESUME:
                this.setText(R.string.resume_timer);
                break;
            case NEXT_ROUND:
                this.setText(R.string.next_round);
                break;
        }
    }
}
