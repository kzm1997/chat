package com.kzm.chat.client;

import com.kzm.chat.client.event.ChatEvent;
import com.kzm.chat.client.event.LoginEvent;
import com.kzm.chat.ui.view.chat.ChatController;
import com.kzm.chat.ui.view.chat.IchatMethod;
import com.kzm.chat.ui.view.login.ILoginMethod;
import com.kzm.chat.ui.view.login.LoginController;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.event.ChangeEvent;

public class Application extends javafx.application.Application {
    
    private Logger logger= LoggerFactory.getLogger(Application.class);
    
    
    public void start(Stage primaryStage) throws Exception {

        IchatMethod chat=new ChatController(new ChatEvent());
        
        //登录组件
        ILoginMethod login=new LoginController(new LoginEvent(),chat);
        login.doShow();
        
        
    }
}
