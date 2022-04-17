package com.kzm.chat.ui.view.login;

public class LoginEventDefine {
    
    private LoginInit loginInit;
    private ILoginEvent loginEvent;
    private ILoginMethod loginMethod;

    public LoginEventDefine(LoginInit loginInit, ILoginEvent loginEvent, ILoginMethod loginMethod) {
        this.loginInit = loginInit;
        this.loginEvent = loginEvent;
        this.loginMethod = loginMethod;
        loginInit.move();
        //事件绑定
        min();
        quit();
        doEventLogin();
    }

    /**
     * 事件,最小化
     */
    private void min(){
        loginInit.login_min_btn.setOnAction(event->{
            loginInit.setIconified(true);
        });
    }
    
    private void quit(){
        loginInit.login_close_btn.setOnAction(event->{
            loginInit.close();
            System.exit(0);
        });
    }
    
    private void doEventLogin(){
        loginInit.login_btn.setOnAction(event->{
            loginEvent.doLoginCheck(loginInit.userNameFiled.getText(),loginInit.passwordField.getText());
            loginMethod.doLoginSuccess(); //todo 在channel中
        });
    }
    
    
}
