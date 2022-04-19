package com.kzm.chat.ui.view.chat;

import com.kzm.chat.ui.view.chat.data.TalkBoxData;
import com.sun.deploy.util.StringUtils;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

import java.awt.*;
import java.util.Date;

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

    private void min() {
        chatInit.$("group_bar_chat_min", Button.class).setOnAction(event -> {
            chatInit.setIconified(true);
        });

        chatInit.$("group_bar_friend_min", Button.class).setOnAction(event -> {
            chatInit.setIconified(true);
        });
    }

    private void quit() {
        chatInit.$("group_bar_chat_close", Button.class).setOnAction(event -> {
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

    /**
     * 发送消息事件
     */
    private void doEventTextSend() {
        TextArea txt_input = chatInit.$("txt_input", TextArea.class);
        txt_input.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {


            }
        });
    }


    private void doEventSendMsg() {
        TextArea txt_input = chatInit.$("txt_input", TextArea.class);
        MultipleSelectionModel selectionModel=chatInit.$("talkList", ListView.class).getSelectionModel();
        Pane selectedItem= (Pane) selectionModel.getSelectedItem();

        //对话信息
        TalkBoxData talkBoxData= (TalkBoxData) selectedItem.getUserData();
        String msg=txt_input.getText();

        if (null == msg || "".equals(msg) || "".equals(msg.trim())) {
            return;
        }
        Date msgDate = new Date();
        //发送消息
        chatEvent.doSendMsg(chatInit.userId,talkBoxData.getTalkId(),talkBoxData.getTalkType(),msg,0,msgDate);

        //发送事件给自己添加消息

        txt_input.clear();



    }


}
