package com.kzm.chat.protocal.login;

import com.kzm.chat.protocal.Command;
import com.kzm.chat.protocal.Packet;

public class LoginRequest extends Packet {

    private String userId;
    private String userPassword;

    public LoginRequest(String userId, String userPassword) {
        this.userId = userId;
        this.userPassword = userPassword;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Override
    public Byte getCommand() {
        return Command.LoginRequest;
    }
}
