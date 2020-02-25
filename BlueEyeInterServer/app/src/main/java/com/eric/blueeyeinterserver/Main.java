package com.eric.blueeyeinterserver;

public class Main {
    static public void main(String[] args){
        System.out.println("BlueEye Internel Service Start");
        InternelServer server = new InternelServer(6565);
        server.init();
    }
}
