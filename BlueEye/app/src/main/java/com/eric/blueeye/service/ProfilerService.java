package com.eric.blueeye.service;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

import com.eric.blueeye.R;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class ProfilerService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    public static final String ACTION_END_PROFILER = "COMMAND_END";
    final String TAG = "Blue Eye";

    // TODO: Rename parameters
    private static final String EXTRA_PARAM1 = "TimeInterval";
    private static final String EXTRA_PARAM2 = "com.eric.blueeye.service.extra.PARAM2";

    public ProfilerService() {
        super("ProfilerService");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionFoo(Context context, String param1, String param2) {
        Intent intent = new Intent(context, ProfilerService.class);
        intent.setAction(context.getResources().getString(R.string.ACTION_START_PROFILER));
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionBaz(Context context, String param1, String param2) {
        Intent intent = new Intent(context, ProfilerService.class);
        intent.setAction(ACTION_END_PROFILER);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        final String cmd_start = this.getResources().getString(R.string.ACTION_START_PROFILER);
        if (intent != null) {
            final String action = intent.getAction();
            if (cmd_start.equals(action)) {
                final String time_interval = intent.getStringExtra(EXTRA_PARAM1);
                handleStartMsg(time_interval);
            } else if (ACTION_END_PROFILER.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionBaz(param1, param2);
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleStartMsg(String time_interval) {
        // TODO: Handle action Foo
        Integer interval = Integer.parseInt(time_interval);
        Log.d(TAG,"Time Interval:"+time_interval);

    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBaz(String param1, String param2) {
        // TODO: Handle action Baz
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
