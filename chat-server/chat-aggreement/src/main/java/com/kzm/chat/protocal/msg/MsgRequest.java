package com.kzm.chat.protocal.msg;

import com.kzm.chat.protocal.Command;
import com.kzm.chat.protocal.Packet;

import java.util.Date;

public class MsgRequest extends Packet {

    private String userId; //用户id
    private String friendId; //好友id
    private String msgText; //传输消息内容
    private Integer msgType; //消息类型, 0文字游戏,1表情
    private Date msgDate; //消息时间

    public MsgRequest(){

    }

    public MsgRequest(String userId, String friendId, String msgText, Integer msgType, Date msgDate) {
        this.userId = userId;
        this.friendId = friendId;
        this.msgText = msgText;
        this.msgType = msgType;
        this.msgDate = msgDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
        return Command.MsgRequest;
    }
}
