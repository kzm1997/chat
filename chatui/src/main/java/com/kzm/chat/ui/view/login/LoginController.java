package com.kzm.chat.ui.view.login;

import com.kzm.chat.ui.view.chat.IchatMethod;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController extends LoginInit implements ILoginMethod {
    
    private IchatMethod  chat;
    
    private LoginView loginView;
    private LoginEventDefine loginEventDefine;
    
    public LoginController(ILoginEvent loginEvent,IchatMethod chat){
        super(loginEvent);
        this.chat=chat;
        
    }
    


    @Override
    public void initView() {
        loginView=new LoginView(this,loginEvent);
    }

    @Override
    public void initEventDefine() {
     loginEventDefine=new LoginEventDefine(this,loginEvent,this);
    }

    public void doShow() {
        super.show();
    }

    public void doLoginError() {
        errorLabel.setVisible(true);
    }

    public void doLoginSuccess() {
     //关闭登录窗口 ,打开聊天窗口
        close();
        chat.doShow();
    }

}
