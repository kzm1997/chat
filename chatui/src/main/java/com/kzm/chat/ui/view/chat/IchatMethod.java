package com.kzm.chat.ui.view.chat;


import java.util.Date;

public interface IchatMethod {

    /**
     * 打开窗口
     */
    void doShow();

    /**
     * 设置登录用户头像
     * @param userId
     * @param userNickName
     * @param userHead
     */
    void setUserInfo(String userId,String userNickName,String userHead);


    /**
     *  填充对话框列表
     * @param talkIdx 对话框位置 首位0 默认-1
     * @param talkType  对话框类型 好友0,群组1 
     * @param talkId   对话框ID 
     * @param talkName  对话框名称
     * @param talkHead 对话框头像
     * @param talkSketch  对话框通信简述
     * @param talkDate 对话框通信时间
     * @param selected 选中
     */
    void addTalkBox(int talkIdx,Integer talkType,String talkId,String talkName,String talkHead,String talkSketch,Date talkDate,Boolean selected);


    /**
     * 填充对话框消息[自己的消息]
     *
     * @param talkId   对话框ID[用户ID]
     * @param msg      消息
     * @param msgType  消息类型；0文字消息、1固定表情
     * @param msgData  时间
     * @param idxFirst 是否设置首位
     * @param selected 是否选中
     * @param isRemind 是否提醒
     */
    void addTalkMsgRight(String talkId, String msg, Integer msgType, Date msgData, Boolean idxFirst, Boolean selected, Boolean isRemind);


    /**
     * 填充对话框消息-好友
     * @param talkId
     * @param msg
     * @param msgType
     * @param msgDate
     * @param idxFirst
     * @param selected
     * @param isRemind
     */
    void  addTalkMsgUserLeft(String talkId, String msg, Integer msgType, Date msgDate,Boolean idxFirst,Boolean selected,Boolean isRemind);


    /**
     * 填充对话框消息-群组[别人的消息]
     * @param talkId 对话框id[群组id]
     * @param userId 用户id[群员]
     * @param userNickName  用户昵称
     * @param userHead 用户头像
     * @param msg  消息
     * @param msgType  消息类型 0文字消息,1固定表情
     * @param msgDate  时间
     * @param idxFirst 是否设置首位
     * @param selected 是否选中
     * @param isRemind 是否提醒
     */
    void addTalkMsgGroupLeft(String talkId,String userId,String userNickName,String userHead,String msg,Integer msgType,Date msgDate,Boolean idxFirst,Boolean selected,Boolean isRemind);
    
}
