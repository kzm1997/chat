package com.kzm.chat.ui.view.chat;

import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;


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


    /**
     * 事件处理；删除指定对话框
     *
     * @param userId 用户ID
     * @param talkId 对话框ID
     */
    void doEventDelTalkUser(String userId, String talkId);


    /**
     * 事件处理,查询用户到列表
     * @param userId
     * @param listView
     */
    void addFriendLuck(String userId, ListView<Pane> listView);


    /**
     * 事件处理；好友搜索[搜索后结果调用添加：addLuckFriend]
     *
     * @param userId 用户ID
     * @param text   搜索关键字
     */
    void doFriendLuckSearch(String userId, String text);
    
    
}
