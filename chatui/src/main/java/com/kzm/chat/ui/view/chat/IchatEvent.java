package com.kzm.chat.ui.view.chat;

import java.util.Date;

public interface IchatEvent {

    /**
     * 聊天窗口退出操作
     */
    void  doQuit();


    /**
     * 发送消息按钮
     * @param userId 用户id
     * @param talkId  对话id(好友id/群组id)
     * @param talkType 对话框类型:0,好友 ,1 群组合
     * @param msg 发送消息内容
     * @param msgType 消息类型 0文字消息,1固定表情
     * @param msgDate 发送消息时间
     */
    void doSendMsg(String userId, String talkId, Integer talkType, String msg, Integer msgType, Date msgDate);
    
    
}
