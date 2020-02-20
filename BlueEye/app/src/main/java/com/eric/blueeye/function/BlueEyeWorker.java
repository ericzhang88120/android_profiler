package com.eric.blueeye.function;

import android.os.Handler;
import android.content.Context;
import android.os.Message;

//thread used to get fps mem cpu
public class BlueEyeWorker extends Thread {
    private Handler handler;
    private Context context;
    public BlueEyeWorker(Context context,Handler handler) {
        this.handler = handler;
        this.context = context;
    }
    @Override
    public void run(){
        while (true){
            Message msg = new Message();
            msg.what = 100;
            this.handler.sendMessage(msg);
            try{
                Thread.sleep(1000);
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }

        }
    }
}
