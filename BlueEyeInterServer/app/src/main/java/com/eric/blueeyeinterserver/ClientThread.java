package com.eric.blueeyeinterserver;

import com.eric.blueeye.proto.MsgInfo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientThread implements Runnable {
    private Socket socket;
    public ClientThread(Socket client){
        this.socket = client;
        new Thread(this).start();
    }
    @Override
    public void run(){
        System.out.println("Server Thread run");
        try{
            while (true){
                ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                MsgInfo obj = (MsgInfo)input.readObject();
                if(obj.getMsgType().equals(MsgInfo.MsgTypeEnum.GETFPS)){
                    String content = obj.getContent();
                    System.out.println("Recv getfps cmd package:"+content);
                    Double fps =GetFPS(content);
                    MsgInfo resp = new MsgInfo();
                    resp.setMsgType(MsgInfo.MsgTypeEnum.FPS_RESP);
                    resp.setFps(fps);
                    ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                    output.writeObject(resp);
                    output.flush();
                }
                else if(obj.getMsgType().equals(MsgInfo.MsgTypeEnum.GETCPU)){

                }
                else if(obj.getMsgType().equals(MsgInfo.MsgTypeEnum.GETMEM)){

                }

            }


        }
        catch (IOException e){

        }
        catch (ClassNotFoundException e){

        }
    }
    private Double GetFPS(String packagename){
        Double fps = Double.NaN;
        String window_name = SurfaceFlingerHelper.GetRealWindowName(packagename);
        System.out.println(window_name);
        if(window_name == null){
            return fps;
        }
        System.out.println("Start Get surfaceflinger");

        //1 .dump
        if(SurfaceFlingerHelper.dumpFrameLatency(window_name,true)){
            fps = SurfaceFlingerHelper.getFrameRate();
        }
        //2, clear buffer
        SurfaceFlingerHelper.clearBuffer(window_name);


        return fps;
    }
}
