package com.kzm.chat.client.application;

import com.kzm.chat.ui.view.chat.IchatMethod;
import com.kzm.chat.ui.view.login.ILoginMethod;

public class UIService {

    private ILoginMethod loginMethod;

    private IchatMethod chat;

    public ILoginMethod getLoginMethod() {
        return loginMethod;
    }

    public void setLoginMethod(ILoginMethod loginMethod) {
        this.loginMethod = loginMethod;
    }

    public IchatMethod getChat() {
        return chat;
    }

    public void setChat(IchatMethod chat) {
        this.chat = chat;
    }
}
