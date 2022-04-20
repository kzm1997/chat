package com.kzm.chat.protocal.login;

import com.kzm.chat.protocal.Command;
import com.kzm.chat.protocal.Packet;
import com.kzm.chat.protocal.login.dto.ChatTalkDto;

import java.util.ArrayList;
import java.util.List;

public class LoginResponse extends Packet {

    private boolean success; //登录反馈
    private String userId; //用户ID
    private String userHead; //用户头像
    private String userNickName; //用户昵称

    private List<ChatTalkDto> chatTalkList = new ArrayList<>();     // 聊天对话框数据[success is true]



    public LoginResponse(){}

    public LoginResponse(boolean success){
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public List<ChatTalkDto> getChatTalkList() {
        return chatTalkList;
    }

    public void setChatTalkList(List<ChatTalkDto> chatTalkList) {
        this.chatTalkList = chatTalkList;
    }

    @Override
    public Byte getCommand() {
        return Command.LoginResponse;
    }
}
