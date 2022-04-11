package com.kzm.chat.ui.view.login;

public interface ILoginMethod {

    /**
     * 打开登录窗口
     */
    void doShow();

    /**
     * 登录失败
     */
    void doLoginError();

    /**
     * 登录成功
     */
    void doLoginSuccess();
}
