package com.kzm.chat.ui.view.login;

public interface ILoginEvent {


    /**
     * 登录校验
     * @param userId
     * @param userPassword
     */
    void doLoginCheck(String userId,String userPassword);
}
