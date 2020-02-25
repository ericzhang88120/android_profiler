package com.eric.blueeye.function;

import android.os.Handler;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.eric.blueeye.proto.MsgInfo;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

//thread used to get fps mem cpu
public class BlueEyeWorker extends Thread {
    private final String TAG="Blue Eye";
    private Handler handler;
    private Context context;
    private Socket socket ;
    private int Port = 6565;
    public BlueEyeWorker(Context context,Handler handler) {
        this.handler = handler;
        this.context = context;
        this.socket = null;
    }
    @Override
    public void run(){
        Log.d(TAG,"Start worker thread");
        if(!ConnectToService(6565)){
            Log.d(TAG,"Connect to the Internel Server fail");
            Toast.makeText(context, "连接internel server失败", Toast.LENGTH_SHORT).show();
        }
        if(socket!=null){
            while (true){
                Double fps = GetFPS("com.sina.weibo");
                if(!Double.isNaN(fps)){
                    Log.d(TAG,"FPS:"+fps);
                }
                else{
                    Log.d(TAG,"FPS is NAN");
                }

                try{
                    Thread.sleep(1000);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }

            }
        }


    }
    private Double GetFPS(String packagename){
        Double fps  = Double.NaN;
        MsgInfo msg = new MsgInfo();
        msg.setMsgType(MsgInfo.MsgTypeEnum.GETFPS);
        msg.setContent(packagename);

        try {
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            Log.d(TAG,"Send Get FPS reques");
            output.writeObject(msg);
            output.flush();

            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            MsgInfo m = (MsgInfo)in.readObject();
            if(m!=null){
                if(m.getMsgType()==MsgInfo.MsgTypeEnum.FPS_RESP){
                    fps = m.getFps();
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        finally {

        }

        return fps;
    }

    private boolean ConnectToService(int port){
        boolean res = false;
        int retry = 0;
        try{

            while(retry<3){
                socket = new Socket("127.0.0.1",6565);
                if(socket!=null) break;
                retry++;
            }
            if(socket == null) return false;
            return true;
        }
        catch (UnknownHostException e){
            System.out.println("客户端异常:" + e.getMessage());
            return false;
        }
        catch (IOException e){
            System.out.println("客户端IO异常:" + e.getMessage());
            return false;
        }

    }
}
