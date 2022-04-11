package com.kzm.chat.client;

import com.kzm.chat.ui.view.login.ILoginMethod;
import com.kzm.chat.ui.view.login.LoginController;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application extends javafx.application.Application {
    
    private Logger logger= LoggerFactory.getLogger(Application.class);
    
    
    public void start(Stage primaryStage) throws Exception {
        
        //登录组件
        ILoginMethod login=new LoginController();
        login.doShow();
        
        
    }
}
