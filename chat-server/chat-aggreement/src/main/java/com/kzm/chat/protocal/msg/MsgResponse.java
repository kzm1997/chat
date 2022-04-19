package com.kzm.chat.protocal.msg;

import com.kzm.chat.protocal.Command;
import com.kzm.chat.protocal.Packet;

import java.util.Date;

public class MsgResponse extends Packet {

    private String friendId; //好友id
    private String msgText; //传输消息内容
    private Integer msgType; //消息类型,0,文字游戏,1固定表情
    private Date msgDate; //消息时间


    public MsgResponse() {

    }

    public MsgResponse(String friendId, String msgText, Integer msgType, Date msgDate) {
        this.friendId = friendId;
        this.msgText = msgText;
        this.msgType = msgType;
        this.msgDate = msgDate;
    }

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    public String getMsgText() {
        return msgText;
    }

    public void setMsgText(String msgText) {
        this.msgText = msgText;
    }

    public Integer getMsgType() {
        return msgType;
    }

    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }

    public Date getMsgDate() {
        return msgDate;
    }

    public void setMsgDate(Date msgDate) {
        this.msgDate = msgDate;
    }

    @Override
    public Byte getCommand() {
        return Command.MsgResponse;
    }
}
