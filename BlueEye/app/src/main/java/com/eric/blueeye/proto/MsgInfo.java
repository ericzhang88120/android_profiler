package com.eric.blueeye.proto;

import java.io.Serializable;

public class MsgInfo implements Serializable {
    private MsgTypeEnum MsgType;
    private String Content;
    private Double fps;

    public MsgTypeEnum getMsgType() {
        return MsgType;
    }

    public void setMsgType(MsgTypeEnum msgType) {
        MsgType = msgType;
    }

    public Double getFps() {
        return fps;
    }

    public void setFps(Double fps) {
        this.fps = fps;
    }

    public enum MsgTypeEnum{
        GETFPS,GETCPU,GETMEM,FPS_RESP,CPU_RESP,MEM_RESP;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

}
