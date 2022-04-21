package com.kzm.chat.protocal.msg;

import com.kzm.chat.protocal.Command;
import com.kzm.chat.protocal.Packet;

import java.util.Date;

public class MsgGroupResponse extends Packet {

    private String talkId;  //对话框Id
    private String userId; //群员用户Id
    private String userNickName; //群员用户昵称
    private String userHead; //群员用户头像
    private String msg;  //群员用户发送消息内容
    private Integer msgType; //消息内容 0文字消息 1固定表情
    private Date msgDate;

    public MsgGroupResponse() {

    }

    public String getTalkId() {
        return talkId;
    }

    public void setTalkId(String talkId) {
        this.talkId = talkId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
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

    public MsgGroupResponse(String talkId, String userId, String userNickName, String userHead, String msg, Integer msgType, Date msgDate) {
        this.talkId = talkId;
        this.userId = userId;
        this.userNickName = userNickName;
        this.userHead = userHead;
        this.msg = msg;
        this.msgType = msgType;
        this.msgDate = msgDate;
    }


    @Override
    public Byte getCommand() {
        return Command.MsgGroupResponse;
    }
}
