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
    
    
}
