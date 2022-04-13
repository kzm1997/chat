package com.kzm.chat.ui.view.chat;


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
    
    
    
}
