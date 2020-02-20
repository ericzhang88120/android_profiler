package com.eric.blueeye.service;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

public class ServiceHandle extends Handler {
    private final String TAG = "Blue Eye";
    public ServiceHandle(BlueEyeService service, Looper service_looper)
    {
        super(service_looper);
    }
    @Override
    public void handleMessage(Message paramMessage)
    {
        try{
            int msgid = paramMessage.what;
            Log.d(TAG,Integer.toString(msgid));

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return;
        }
    }
}
