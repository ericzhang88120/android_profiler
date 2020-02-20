package com.eric.blueeye.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;

import com.eric.blueeye.R;

public class BlueEyeService extends Service {
    public BlueEyeService() {
    }
    @Override
    public void onCreate(){
        CreateNotification();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        return super.onStartCommand(intent,flags,startId);
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
    }

    private void CreateNotification(){
        Notification.Builder builder;
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O)
        {
            String NOTIFICATION_CHANNEL_ID = "com.example.simpleapp";
            String channelName = "My Background Service";
            NotificationChannel chan = new NotificationChannel(NOTIFICATION_CHANNEL_ID, channelName, NotificationManager.IMPORTANCE_NONE);
            chan.setLightColor(Color.BLUE);
            chan.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
            NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            assert manager != null;
            manager.createNotificationChannel(chan);
            builder = new Notification.Builder((Context)this,NOTIFICATION_CHANNEL_ID);
        }
        else
        {
            builder = new Notification.Builder((Context)this);
        }
        builder.setSmallIcon(R.drawable.ic_launcher_foreground);
        builder.setContentTitle("Blue Eye service");
        builder.setContentText("Blue Eye service running");
        Intent intent = new Intent();
        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.addFlags(Intent.FLAG_RECEIVER_FOREGROUND);
        intent.setData(Uri.fromParts("package", getPackageName(), null));
        builder.setContentIntent(PendingIntent.getActivities((Context)this, 0, new Intent[] { intent }, PendingIntent.FLAG_UPDATE_CURRENT));
        startForeground(100, builder.build());
    }
}
