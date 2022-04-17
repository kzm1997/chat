package com.kzm.chat.ui.view.chat;

import javafx.scene.control.Button;

import java.awt.*;

public class ChatEventDefine {
    
    private ChatInit chatInit;
    private IchatEvent chatEvent;
    private IchatMethod chatMethod;

    public ChatEventDefine(ChatInit chatInit, IchatEvent chatEvent, IchatMethod chatMethod) {
        this.chatInit = chatInit;
        this.chatEvent = chatEvent;
        this.chatMethod = chatMethod;
        
        chatInit.move();
        min();
        quit();
        
        
    }
    
    private void min(){
        chatInit.$("group_bar_chat_min", Button.class).setOnAction(event -> {
            chatInit.setIconified(true);
        });
        
        chatInit.$("group_bar_friend_min",Button.class).setOnAction(event -> {
            chatInit.setIconified(true);
        });
    }
    
    private void quit(){
        chatInit.$("group_bar_chat_close",Button.class).setOnAction(event -> {
            chatEvent.doQuit();
            chatInit.close();
            System.exit(0);
        });

        chatInit.$("group_bar_friend_close", Button.class).setOnAction(event -> {
            chatEvent.doQuit();
            chatInit.close();
            System.exit(0);
        });
    }
}
