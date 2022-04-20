package com.kzm.chat.ui.view.chat;

import com.kzm.chat.ui.view.chat.data.TalkBoxData;
import com.sun.deploy.util.StringUtils;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
        barChat();  //聊天
        doEventTextSend(); //发送消息事件[键盘]
        doEventTouchSend();//发送消息事件[按钮]


    }

    private void barChat() {
        Button bar_chat = chatInit.$("bar_chat", Button.class);
        Pane group_bar_chat = chatInit.$("group_bar_chat", Pane.class);
        bar_chat.setOnAction(event -> {
            switchBarChat(bar_chat, group_bar_chat, true);
            switchBarFriend(chatInit.$("bar_friend", Button.class), chatInit.$("group_bar_friend", Pane.class), false);
        });
        bar_chat.setOnMouseEntered(event -> {
            Boolean visible = group_bar_chat.isVisible();
            if (visible) return;
            bar_chat.setStyle("-fx-background-image: url('/fxml/chat/img/system/chat_1.png')");
        });
        bar_chat.setOnMouseExited(event -> {
            boolean visible = group_bar_chat.isVisible();
            if (visible) return;
            bar_chat.setStyle("-fx-background-image: url('/fxml/chat/img/system/chat_0.png')");
        });
    }

    private void switchBarChat(Button bar_chat, Pane group_bar_chat, boolean toggle) {
        if (toggle) {
            bar_chat.setStyle("-fx-background-image: url('/fxml/chat/img/system/chat_2.png')");
            group_bar_chat.setVisible(true);
        } else {
            bar_chat.setStyle("-fx-background-image: url('/fxml/chat/img/system/chat_0.png')");
            group_bar_chat.setVisible(false);
        }
    }

    // 发送消息事件[按钮]
    private void doEventTouchSend() {
        javafx.scene.control.Label touch_send = chatInit.$("touch_send", Label.class);
        touch_send.setOnMousePressed(event -> {
            doEventSendMsg();
        });
    }

    // 切换：bar_friend
    private void switchBarFriend(Button bar_friend, Pane group_bar_friend, boolean toggle) {
        if (toggle) {
            bar_friend.setStyle("-fx-background-image: url('/fxml/chat/img/system/friend_2.png')");
            group_bar_friend.setVisible(true);
        } else {
            bar_friend.setStyle("-fx-background-image: url('/fxml/chat/img/system/friend_0.png')");
            group_bar_friend.setVisible(false);
        }
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
                doEventSendMsg();
            }
        });
    }


    private void doEventSendMsg() {
        TextArea txt_input = chatInit.$("txt_input", TextArea.class);
        MultipleSelectionModel selectionModel = chatInit.$("talkList", ListView.class).getSelectionModel();
        Pane selectedItem = (Pane) selectionModel.getSelectedItem();

        //对话信息
        TalkBoxData talkBoxData = (TalkBoxData) selectedItem.getUserData();
        String msg = txt_input.getText();

        if (null == msg || "".equals(msg) || "".equals(msg.trim())) {
            return;
        }
        Date msgDate = new Date();
        //发送消息
        chatEvent.doSendMsg(chatInit.userId, talkBoxData.getTalkId(), talkBoxData.getTalkType(), msg, 0, msgDate);

        //发送事件给自己添加消息 应该放到信道去
        chatMethod.addTalkMsgRight(talkBoxData.getTalkId(), msg, 0, msgDate, true, true, false);

        txt_input.clear();


    }


}
