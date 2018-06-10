package com.example.jack.boomtimer;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class TimerService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_RUN_TIMER = "com.example.jack.boomtimer.action.RUN_TIMER";

    // TODO: Rename parameters
    private static final String EXTRA_SECONDS = "com.example.jack.boomtimer.extra.SECONDS";

    public TimerService() {
        super("TimerService");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionRunTimer(Context context, String param1, String param2) {
        Intent intent = new Intent(context, TimerService.class);
        intent.setAction(ACTION_RUN_TIMER);
        intent.putExtra(EXTRA_SECONDS, param1);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_RUN_TIMER.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_SECONDS);
                handleActionFoo(param1);
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionFoo(String param1) {
        // TODO: Handle action Foo
        throw new UnsupportedOperationException("Not yet implemented");
    }

}
