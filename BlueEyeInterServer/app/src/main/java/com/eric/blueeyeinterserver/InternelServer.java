package com.eric.blueeyeinterserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class InternelServer {
    private int PORT;
    public InternelServer(int port){
        this.PORT = port;
    }

    public void init(){
        System.out.println("Server start init");
        try{
            ServerSocket serverSocket = new ServerSocket(PORT);
            while (true){
                Socket client = serverSocket.accept();
                System.out.println("Client accepted");
                new ClientThread(client);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
